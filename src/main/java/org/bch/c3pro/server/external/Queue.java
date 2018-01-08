package org.bch.c3pro.server.external;

import java.security.PublicKey;

import org.bch.c3pro.server.exception.C3PROException;

import edu.uconn.c3pro.server.api.entities.EncryptedMessage;

/**
 * Interface for queue access
 * @author CHIP-IHL
 */
public interface Queue {
	public void sendMessage(EncryptedMessage message) throws C3PROException;
}
