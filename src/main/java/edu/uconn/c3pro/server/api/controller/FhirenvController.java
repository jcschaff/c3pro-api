package edu.uconn.c3pro.server.api.controller;

import org.bch.c3pro.server.exception.C3PROException;
import org.bch.c3pro.server.external.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uconn.c3pro.server.api.entities.EncryptedMessage;

@Controller
public class FhirenvController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Queue messageQueue;
	
	/**
	 * HTTP/1.1 POST /c3pro/fhirenc/*
     * {
     *   "message":{{The encrypted fhir resource}},
     *   "symmetric_key": {{The encrypted AES symmetric key used to encrypt the message}},
     *   "key_id": {{The rsa key id used to encrypt the symmetric key}},
     *   "version": {{0.9.0 or 1.0.2}}
     * }
     * 
	 * @param message
	 * @return
	 */
	
    @RequestMapping(method = RequestMethod.POST, value = "/c3pro/fhirenc/*", produces="application/fhir+json")
    public ResponseEntity<HttpStatus> sendMessageToQueue(@RequestBody EncryptedMessage message) {
    		try {
    			if (message.getKey_id()==null) {
    				logger.error("still have a null pkey_id");
    				return ResponseEntity.ok(HttpStatus.OK);
    			}
            messageQueue.sendMessage(message);
        } catch (C3PROException e) {
            e.printStackTrace();
			logger.warn("failed to validate apple receipt");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    		return ResponseEntity.ok(HttpStatus.OK);
    }
}