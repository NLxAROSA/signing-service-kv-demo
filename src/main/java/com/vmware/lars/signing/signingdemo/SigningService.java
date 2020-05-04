package com.vmware.lars.signing.signingdemo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.models.KeyOperationResult;
import com.microsoft.azure.keyvault.webkey.JsonWebKeySignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SigningService {

    private final KeyVaultClient keyvaultClient;
    private final String keyIdentifier;
    private final JsonWebKeySignatureAlgorithm keySignatureAlgorithm = JsonWebKeySignatureAlgorithm.RSNULL;

    public SigningService(KeyVaultClient keyvaultClient, @Value("${keyIdentifier}") String keyIdentifier) {
        this.keyvaultClient = keyvaultClient;
        this.keyIdentifier = keyIdentifier;
    }

    public byte[] signMessage(String message) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(message.getBytes("UTF-8"));
        KeyOperationResult keyOperationResult = keyvaultClient.sign(keyIdentifier, keySignatureAlgorithm, messageDigest.digest());
        return keyOperationResult.result();
    }

}