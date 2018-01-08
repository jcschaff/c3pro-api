package org.bch.c3pro.server.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.bch.c3pro.server.exception.C3PROException;

/**
 * Class that manages the configuration parameters
 * @author CHIP-IHL
 */
public class AppConfig {

    public static final String CONFIG_PROPERTIES_FILE=    "config.properties";

    public static final String AWS_SQS_URL =              "app.aws.sqs.url";
    public static final String AWS_SQS_URL_DEFAULT =              "https://sqs.us-east-1.amazonaws.com/157844197903/testQ";
    public static final String AWS_SQS_PROFILE =          "app.aws.sqs.profile";
    public static final String AWS_SQS_PROFILE_DEFAULT =          "default";
    public static final String AWS_SQS_REGION =           "app.aws.sqs.region";
    public static final String AWS_SQS_REGION_DEFAULT =           "us-east-1";

    public static final String AWS_S3_PROFILE =           "app.aws.s3.profile";
    public static final String AWS_S3_PROFILE_DEFAULT =           "default";
    public static final String AWS_S3_BUCKET_NAME =       "app.aws.s3.bucket";
    public static final String AWS_S3_BUCKET_NAME_DEFAULT =       "c3probuckettest";
    public static final String AWS_S3_REGION =            "app.aws.s3.region";
    public static final String AWS_S3_REGION_DEFAULT =            "us-east-1";

    // The name of the public key file found in the S3 bucket and its uuid
    public static final String SECURITY_PUBLICKEY =       "app.security.publickey";
    public static final String SECURITY_PUBLICKEY_ID =       "app.security.publickey.id";

    // The key posted in the metadata part of the message to SQS. The value will containt th encrypted symetric key
    // to decrypt the message
    public static final String SECURITY_METADATAKEY =           "app.security.metadatakey";
    public static final String SECURITY_METADATAKEY_DEFAULT =           "pkey";
    public static final String SECURITY_METADATAKEYID =         "app.security.metadatakeyid";
    public static final String SECURITY_METADATAKEYID_DEFAULT =         "pkey_id";

    public static final String SECURITY_PRIVATEKEY_ALG =        "app.security.secretkey.algorithm";
    public static final String SECURITY_PRIVATEKEY_BASEALG =    "app.security.secretkey.basealgorithm";

    public static final String SECURITY_PRIVATEKEY_SIZE =       "app.security.secretkey.size";
    public static final String SECURITY_PUBLICKEY_ALG =         "app.security.publickey.algorithm";
    public static final String SECURITY_PUBLICKEY_BASEALG =     "app.security.publickey.basealgorithm";

    public static final String FHIR_METADATA_VERSION =          "app.fhir.metadata.version";
    public static final String FHIR_METADATA_VERSION_DEFAULT =          "version";

    public static final String UTF = "UTF-8";
    // Whether we encrypt the messages when sent to the queue; yes - no
    public static final String SECURITY_ENCRYPTION_ENABLED =    "app.security.encryption.enabled";

    public static final String APP_IOS_ID =                     "app.ios.id";
    public static final String APP_IOS_VERIF_ENDPOINT =         "app.ios.verification.endpoint";
    public static final String APP_IOS_VERIF_TEST_ENDPOINT=     "app.ios.verificationtest.endpoint";


    public static final String APP_FILENAME_MAPCOUNT =          "app.mapcount.s3.filename";

    public static final String PORT_SMTP=                       "app.port.smtp";
    public static final String PORT_SMTP_DEFAULT=                       "25";
    public static final String HOST_SMTP =                      "app.host.smtp";
    public static final String HOST_SMTP_DEFAULT =                      "127.0.0.1";
    public static final String MAIL_RECEIPTIENT =               "app.recipient.smtp";
    public static final String MAIL_RECEIPTIENT_DEFAULT =               "schaff@uchc.edu";

    public static final String FHIR_VERSION_DEFAULT =           "app.fhir.version.default";

    private static Properties prop = new Properties();
    /**
     * Upload the configuration from config.properties files
     */
    private static void uploadConfiguration() throws C3PROException {
        InputStream input = null;

        try {
            String filename = CONFIG_PROPERTIES_FILE;
            input = AppConfig.class.getResourceAsStream(filename);
            if (input == null) {
                throw new C3PROException("No " + filename + " found!");
            }
            prop.load(input);

        } catch (IOException ex) {
            throw new C3PROException("", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new C3PROException("", e);
                }
            }
        }
    }

    /**
     * Get the value of the system property
     * @param key The key of the property
     * @param defaultValue The default value of the property
     * @return The value of the property.
     */
    public static String getProp(String key, String defaultValue) {
    		String propertyValue = System.getProperty(key, defaultValue);
   		return propertyValue;
    }
    
//    /**
//     * Get the value of the system property
//     * @param key The key of the property
//     * @return The value of the property.
//     * @throws C3PROException If the property key does not exist.
//     */
//    public static String getProp(String key) throws C3PROException {
//    		String propertyValue = System.getProperty(key);
//    		if (propertyValue==null) {
//    			throw new C3PROException("system property "+key+" not defined");
//    		}
//    		return propertyValue;
//    }
}
