package com.vmware.lars.signing.signingdemo;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SigningController {

    private final SigningService signingService;

    public SigningController(SigningService signingService) {
        this.signingService = signingService;
    }

    @GetMapping("/sign")
    public String signMessage(@RequestParam("message") String message)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return new String(signingService.signMessage(message), Charset.forName("UTF-8"));
    }

    @GetMapping("/validate")
    public Boolean validateMessage(@RequestParam("message") String message, @RequestParam("signature") String signature)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return signingService.isValidSignature(message, signature.getBytes(Charset.forName("UTF-8")));
    }

}