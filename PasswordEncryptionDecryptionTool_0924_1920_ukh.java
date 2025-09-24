// 代码生成时间: 2025-09-24 19:20:40
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class PasswordEncryptionDecryptionTool {

    private static final String ALGORITHM = "AES";

    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encrypt(@RequestBody Map<String, String> request) {
        try {
            String plainText = request.get("text");
            SecretKey key = generateKey();
            String encryptedText = encrypt(plainText, key);
            request.put("key", Base64.getEncoder().encodeToString(key.getEncoded()));
            request.put("encryptedText", encryptedText);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse("Encryption failed", e.getMessage()));
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody Map<String, String> request) {
        try {
            String encryptedText = request.get("encryptedText");
            String encodedKey = request.get("key");
            SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(encodedKey), ALGORITHM);
            String decryptedText = decrypt(encryptedText, key);
            return ResponseEntity.ok(createSuccessResponse("Decrypted text", decryptedText));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse("Decryption failed", e.getMessage()));
        }
    }

    private SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128, SecureRandom.getInstanceStrong());
        return keyGenerator.generateKey();
    }

    private String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private Map<String, String> createSuccessResponse(String key, String value) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put(key, value);
        return response;
    }

    private Map<String, String> createErrorResponse(String key, String value) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put(key, value);
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", e.getMessage());
        return ResponseEntity.status(500).body(response);
    }

    public static void main(String[] args) {
        SpringApplication.run(PasswordEncryptionDecryptionTool.class, args);
    }
}
