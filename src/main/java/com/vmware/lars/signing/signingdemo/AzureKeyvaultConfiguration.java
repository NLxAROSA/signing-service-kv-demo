package com.vmware.lars.signing.signingdemo;


import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.spring.AzureKeyVaultCredential;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureKeyvaultConfiguration {

    private final String keyVaultClientId;
    private final String keyVaultClientSecret;

    public AzureKeyvaultConfiguration(@Value("${clientId}") String keyVaultClientId,
            @Value("${clientSecret}") String keyVaultClientSecret) {
                
        this.keyVaultClientId = keyVaultClientId;
        this.keyVaultClientSecret = keyVaultClientSecret;
    }

    @Bean
    public KeyVaultClient buildClient() {
        AzureKeyVaultCredential credential = new AzureKeyVaultCredential(keyVaultClientId, keyVaultClientSecret);
        return new KeyVaultClient(credential);
    }

}