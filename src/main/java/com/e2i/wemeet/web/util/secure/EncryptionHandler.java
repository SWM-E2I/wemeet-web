package com.e2i.wemeet.web.util.secure;

import com.e2i.wemeet.web.exception.internal.EncryptException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

@ConfigurationProperties(prefix = "security.encrypt")
@Primary
public class EncryptionHandler implements Cryptography {

    private final String alg;
    private final String key;
    private final SecretKeySpec secretKeySpec;
    private final IvParameterSpec ivParamSpec;

    public EncryptionHandler(String alg, String key) {
        this.alg = alg;
        this.key = key;
        this.secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        this.ivParamSpec = new IvParameterSpec(key.substring(0, 16).getBytes());
    }

    @Override
    public String encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new EncryptException();
        }
    }

    @Override
    public String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new EncryptException();
        }
    }
}
