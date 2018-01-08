package edu.uconn.c3pro.server.api.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * HTTP/1.1 POST /c3pro/fhirenc/*
 * {
 *   "message":{{The encrypted fhir resource}},
 *   "symmetric_key": {{The encrypted AES symmetric key used to encrypt the message}},
 *   "key_id": {{The rsa key id used to encrypt the symmetric key}},
 *   "version": {{0.9.0 or 1.0.2}}
 * }
 * 
 **/

public class EncryptedMessage {
	private String message;
	private String symmetric_key;
	private String key_id;
	private String version;
	
	public EncryptedMessage() {		
	}

	public EncryptedMessage(String message, String symmetric_key, String key_id, String version) {
		this.message = message;
		this.symmetric_key = symmetric_key;
		this.key_id = key_id;
		this.version = version;
	}

	@JsonGetter("message")
	public String getMessage() {
		return message;
	}

	@JsonSetter("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonGetter("symmetric_key")
	public String getSymmetric_key() {
		return symmetric_key;
	}

	@JsonSetter("symmetric_key")
	public void setSymmetric_key(String symmetric_key) {
		this.symmetric_key = symmetric_key;
	}

	@JsonGetter("key_id")
	public String getKey_id() {
		return key_id;
	}

	@JsonSetter("key_id")
	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}

	@JsonGetter("version")
	public String getVersion() {
		return version;
	}

	@JsonSetter("version")
	public void setVersion(String version) {
		this.version = version;
	}

}
