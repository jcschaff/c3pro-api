package org.bch.c3pro.server.external;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bch.c3pro.server.config.AppConfig;
import org.bch.c3pro.server.exception.C3PROException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import edu.uconn.c3pro.server.api.entities.EncryptedMessage;

/**
 * Implements the access to a Amazon SQS queue
 * @author CHIP-IHL
 */
public class SQSAccess implements Queue {

    private AmazonSQS sqs = null;
    Log log = LogFactory.getLog(SQSAccess.class);

    private void pushMessage(String msg, String key, String uuid, String version) throws C3PROException{
        setCredentials();
        // We send the encrypted message to the Queue. We Base64 encode it
        String sqs_url = AppConfig.getProp(AppConfig.AWS_SQS_URL, AppConfig.AWS_SQS_URL_DEFAULT);
		SendMessageRequest mse = new SendMessageRequest(sqs_url, msg);
        System.out.println(sqs_url);

        // Add SQS Elem metadata: encrypted symmetric key
        MessageAttributeValue atr = new MessageAttributeValue();
        atr.setStringValue(key);
        atr.setDataType("String");
        String securityMetadata = AppConfig.getProp(AppConfig.SECURITY_METADATAKEY, AppConfig.SECURITY_METADATAKEY_DEFAULT);
		mse.addMessageAttributesEntry(securityMetadata, atr);

        // Add SQS Elem metadata: public key uuid
        atr = new MessageAttributeValue();
        atr.setStringValue(uuid);
        atr.setDataType("String");
        String metakeyid = AppConfig.getProp(AppConfig.SECURITY_METADATAKEYID, AppConfig.SECURITY_METADATAKEYID_DEFAULT);
		mse.addMessageAttributesEntry(metakeyid, atr);

        atr = new MessageAttributeValue();
        atr.setStringValue(version);
        atr.setDataType("String");
        String fhirMetadataVersion = AppConfig.getProp(AppConfig.FHIR_METADATA_VERSION, AppConfig.FHIR_METADATA_VERSION_DEFAULT);
		mse.addMessageAttributesEntry(fhirMetadataVersion, atr);

        try {
            this.sqs.sendMessage(mse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new C3PROException(e.getMessage(), e);
        }

    }

 
    /**
     * Sends an ALREADY encrypted message to the SQS (See documentation)
     * @param resource  The resource
     * @param UUIDKey   the if of the key
     * @param version   The version
     * @throws C3PROException In case access to SQS is not possible
     */
    public void sendMessage(EncryptedMessage message)
            throws C3PROException {
    		String resource = message.getMessage();
    		String key = message.getSymmetric_key();
    		String UUIDKey = message.getKey_id();
    		String version = message.getVersion();
        pushMessage(resource, key, UUIDKey, version);
    }

    private void setCredentials() throws C3PROException {
        if (this.sqs == null) {
            AWSCredentials credentials = null;
            try {
                String sqsProfileName = AppConfig.getProp(AppConfig.AWS_SQS_PROFILE, AppConfig.AWS_SQS_PROFILE_DEFAULT);
                System.setProperty("aws.profile", sqsProfileName);
                System.out.println(sqsProfileName);
                credentials = new ProfileCredentialsProvider().getCredentials();
            } catch (Exception e) {
                e.printStackTrace();
                throw new C3PROException(
                        "Cannot load the credentials from the credential profiles file. " +
                                "Please make sure that the credentials file is at the correct " +
                                "location (~/.aws/credentials), and is in valid format.",
                        e);
            }
            this.sqs = new AmazonSQSClient(credentials);
            String regionName = AppConfig.getProp(AppConfig.AWS_SQS_REGION, AppConfig.AWS_SQS_REGION_DEFAULT);
			System.out.println(regionName);
            Region usWest2 = Region.getRegion(Regions.fromName(regionName));
            sqs.setRegion(usWest2);
        }
    }
}
