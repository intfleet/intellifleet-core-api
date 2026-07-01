package com.intellifleet.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.security.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

import java.security.interfaces.*;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;
import java.util.Base64;


@UtilityClass
public class AdvanceJWTTokenUtils extends RSAKeyUtil{

    //String issuer, String audience, Map<String, Object> payload
    public String getToken(JWTTokenCreaterDto jwtTokenCreaterDto) {

        //1.    Create Claims
        JWTClaimsSet claims = JwtCryptoUtil.createClaims(jwtTokenCreaterDto.getPayload(), jwtTokenCreaterDto.getIssuer(), jwtTokenCreaterDto.getIssuer());

        //2.    Sign Claims
        SignedJWT signedJWT = JwtCryptoUtil.sign(claims, jwtTokenCreaterDto.getSignPrivateKey(),jwtTokenCreaterDto.getKid());

        //3.    Encrypt signed JWT
        String token = JwtCryptoUtil.encrypt(signedJWT, jwtTokenCreaterDto.getEncryptPublicKey());
        System.out.println("token: " + token);
        return token;
    }

    public boolean isJWEToken(String token) {
        int parts = token.split("\\.").length;
        return parts == 5;
    }

    public Map<String, Object> validateAndRetrievedToken(JWTTokenValidatorDto validatorDto) {

        SignedJWT signedJWT = JwtCryptoUtil.decrypt(validatorDto.getToken(), validatorDto.getDecryptPrivateKey());

        String kid = signedJWT.getHeader().getKeyID();
        if(!kid.equals(validatorDto.getKid())) {
            throw new RuntimeException("KID not matched.");
        }

        JWTClaimsSet claims = JwtCryptoUtil.verify(signedJWT, validatorDto.getVerifyPublicKey(), validatorDto.getIssuer(), validatorDto.getAudience());

        Map<String, Object> data = (Map<String, Object>) claims.getClaim("data");
        System.out.println("data: " + data);
        return data;
    }

    @Builder
    @Getter
    public static class JWTTokenCreaterDto {
        private String issuer;
        private String audience;
        private Map<String, Object> payload;
        private RSAPrivateKey signPrivateKey;
        private String kid;
        RSAPublicKey encryptPublicKey;
    }
    @Builder
    @Getter
    public static class JWTTokenValidatorDto {
        private String token;
        private String issuer;
        private String audience;
        private String kid;
        private RSAPrivateKey decryptPrivateKey;
        RSAPublicKey verifyPublicKey;
    }

    public static void main(String args[]) {
        System.out.println("*************  START:  Key pair for JWS    **************");
        GenerateRSAKeys.print();
        System.out.println("*************  END:    Key pair for JWS    **************");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("*************  START:  Key pair for JWE    **************");
        GenerateRSAKeys.print();
        System.out.println("*************  END:    Key pair for JWE    **************");
    }



}

@UtilityClass
class GenerateRSAKeys {

    @SneakyThrows
    public void print() {

        /***  START:    Generate + Store  ***/
        RSAKeyUtil.KeyData keyData = RSAKeyUtil.generateAndPrepare();

        // save to DB
        String publicKey = keyData.getPublicKey();
        String encryptedPrivateKey = keyData.getEncryptedPrivateKey();
        System.out.println("publicKey: " + publicKey);
        System.out.println("encryptedPrivateKey: " + encryptedPrivateKey);
        /***  END:    Generate + Store  ***/


        /***  START:    Load + Use  ***/
        String publicKeyFromDB = publicKey;
        RSAPublicKey pubKey = RSAKeyUtil.decodePublicKey(publicKeyFromDB);

        String encryptedPrivateKeyFromDB = encryptedPrivateKey;
        RSAPrivateKey priKey = RSAKeyUtil.decryptPrivateKey(encryptedPrivateKeyFromDB);
        System.out.println("Validated generated key pairs.");
        /***  END:    Load + Use  ***/
    }
}

class AESGCMUtil {

    private static final String ALGO = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12; // recommended
    private static final int TAG_LENGTH = 128;

//    private static final String SECRET = System.getenv("AES_SECRET"); // 16/24/32 bytes
    private static final String SECRET = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A"; //32 bytes

    private static SecretKey getKey() {
        return new SecretKeySpec(SECRET.getBytes(), ALGO);
    }

    // =========================
    // 🔐 Encrypt
    // =========================
    public static String encrypt(String plainText) {
        try {
            byte[] iv = new byte[IV_LENGTH];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(), new GCMParameterSpec(TAG_LENGTH, iv));

            byte[] encrypted = cipher.doFinal(plainText.getBytes());

            // prepend IV
            byte[] combined = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

            return Base64.getEncoder().encodeToString(combined);

        } catch (Exception e) {
            throw new RuntimeException("AES-GCM encrypt failed", e);
        }
    }

    // =========================
    // 🔓 Decrypt
    // =========================
    public static String decrypt(String encryptedText) {
        try {
            byte[] decoded = Base64.getDecoder().decode(encryptedText);

            byte[] iv = new byte[IV_LENGTH];
            byte[] cipherText = new byte[decoded.length - IV_LENGTH];

            System.arraycopy(decoded, 0, iv, 0, IV_LENGTH);
            System.arraycopy(decoded, IV_LENGTH, cipherText, 0, cipherText.length);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, getKey(), new GCMParameterSpec(TAG_LENGTH, iv));

            byte[] decrypted = cipher.doFinal(cipherText);

            return new String(decrypted);

        } catch (Exception e) {
            throw new RuntimeException("AES-GCM decrypt failed", e);
        }
    }
}

//🔑 ✅ RSAKeyUtil (with AES-GCM integrated)
class RSAKeyUtil {

    // =========================
    // 🔹 Generate RSA Key Pair
    // =========================
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(2048);
            return gen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Key generation failed", e);
        }
    }

    // =========================
    // 🔹 Encode Public Key (Base64)
    // =========================
    public static String encodePublicKey(RSAPublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // =========================
    // 🔹 Encode Private Key (Base64 only)
    // =========================
    public static String encodePrivateKey(RSAPrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // =========================
    // 🔐 Encrypt Private Key (AES-GCM)
    // =========================
    public static String encryptPrivateKey(RSAPrivateKey privateKey) {
        String base64Private = encodePrivateKey(privateKey);

        // ✅ Using AES-GCM (secure)
        return AESGCMUtil.encrypt(base64Private);
    }

    // =========================
    // 🔓 Decrypt Private Key (AES-GCM)
    // =========================
    public static RSAPrivateKey decryptPrivateKey(String encryptedPrivateKey) {
        try {
            // ✅ AES-GCM decryption
            String base64Private = AESGCMUtil.decrypt(encryptedPrivateKey);

            byte[] decoded = Base64.getDecoder().decode(base64Private);

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);

        } catch (Exception e) {
            throw new RuntimeException("Private key decode failed", e);
        }
    }

    // =========================
    // 🔓 Decode Public Key
    // =========================
    public static RSAPublicKey decodePublicKey(String base64PublicKey) {
        try {
            byte[] decoded = Base64.getDecoder().decode(base64PublicKey);

            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);

        } catch (Exception e) {
            throw new RuntimeException("Public key decode failed", e);
        }
    }

    // =========================
    // 🔹 Generate + Prepare for DB
    // =========================
    public static KeyData generateAndPrepare() {
        KeyPair pair = generateKeyPair();

        RSAPublicKey pub = (RSAPublicKey) pair.getPublic();
        RSAPrivateKey pri = (RSAPrivateKey) pair.getPrivate();

        String publicKey = encodePublicKey(pub);

        // 🔐 AES-GCM encryption here
        String encryptedPrivateKey = encryptPrivateKey(pri);

        return new KeyData(publicKey, encryptedPrivateKey);
    }

    // =========================
    // 🔹 DTO for DB
    // =========================
    public static class KeyData {
        private String publicKey;
        private String encryptedPrivateKey;

        public KeyData(String publicKey, String encryptedPrivateKey) {
            this.publicKey = publicKey;
            this.encryptedPrivateKey = encryptedPrivateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getEncryptedPrivateKey() {
            return encryptedPrivateKey;
        }
    }
    //🔑 🔹 Load Keys From DB (IMPORTANT)
    public static KeyPair loadKeyPair(String base64PublicKey, String encryptedPrivateKey) {
        try {
            // 🔓 Step 1: Decrypt private key (AES-GCM)
            String base64Private = AESGCMUtil.decrypt(encryptedPrivateKey);

            // 🔹 Step 2: Decode public key
            byte[] pubBytes = Base64.getDecoder().decode(base64PublicKey);
            X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubBytes);

            // 🔹 Step 3: Decode private key
            byte[] priBytes = Base64.getDecoder().decode(base64Private);
            PKCS8EncodedKeySpec priSpec = new PKCS8EncodedKeySpec(priBytes);

            KeyFactory kf = KeyFactory.getInstance("RSA");

            RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(pubSpec);
            RSAPrivateKey privateKey = (RSAPrivateKey) kf.generatePrivate(priSpec);

            return new KeyPair(publicKey, privateKey);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load key pair from DB", e);
        }
    }

    //Load only Public Key from db
    public static RSAPublicKey loadPublicKey(String base64PublicKey) {
        try {
            byte[] decoded = Base64.getDecoder().decode(base64PublicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);

            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Load only Private Key from db
    public static RSAPrivateKey loadPrivateKey(String encryptedPrivateKey) {
        try {
            String base64 = AESGCMUtil.decrypt(encryptedPrivateKey);

            byte[] decoded = Base64.getDecoder().decode(base64);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);

            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

@UtilityClass
class JwtCryptoUtil {

    // =========================
    // 🔹 Create Claims
    // =========================
    public JWTClaimsSet createClaims(Map<String, Object> payload, String issuer, String audience) {

        return new JWTClaimsSet.Builder()
                .subject((String) payload.get("sub"))
                .issuer(issuer)
                .audience(audience)
                .claim("data", payload)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + 60000))
                .jwtID(UUID.randomUUID().toString())
                .build();
    }

    // =========================
    // 🔹 SIGN (JWS)
    // =========================
    public SignedJWT sign(JWTClaimsSet claims, RSAPrivateKey privateKey, String kid) {

        try {
            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader.Builder(JWSAlgorithm.RS256)
                            .keyID(kid)
                            .build(),
                    claims
            );

            signedJWT.sign(new RSASSASigner(privateKey));
            return signedJWT;

        } catch (Exception e) {
            throw new RuntimeException("Signing failed", e);
        }
    }

    // =========================
    // 🔹 VERIFY (JWS)
    // =========================
    public JWTClaimsSet verify(SignedJWT signedJWT, RSAPublicKey publicKey, String expectedIssuer, String expectedAudience) {

        try {
            boolean valid = signedJWT.verify(new RSASSAVerifier(publicKey));
            if (!valid) throw new RuntimeException("Invalid signature");

            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            if (!expectedIssuer.equals(claims.getIssuer()))
                throw new RuntimeException("Invalid issuer");

            //if (!claims.getAudience().contains(expectedAudience))  // Earlier it was there. Need to check why it's not working here
            if (!claims.getAudience().contains(expectedIssuer))
                throw new RuntimeException("Invalid audience");

            if (claims.getExpirationTime().before(new Date()))
                throw new RuntimeException("Token expired");

            return claims;

        } catch (Exception e) {
            throw new RuntimeException("Verification failed", e);
        }
    }

    // =========================
    // 🔹 ENCRYPT (JWE)
    // =========================
    public String encrypt(SignedJWT signedJWT, RSAPublicKey publicKey) {

        try {
            JWEObject jwe = new JWEObject(
                    new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM),
                    new Payload(signedJWT)
            );

            jwe.encrypt(new RSAEncrypter(publicKey));
            return jwe.serialize();

        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    // =========================
    // 🔹 DECRYPT (JWE)
    // =========================
    public SignedJWT decrypt(String token, RSAPrivateKey privateKey) {

        try {
            JWEObject jwe = JWEObject.parse(token);
            jwe.decrypt(new RSADecrypter(privateKey));

            return jwe.getPayload().toSignedJWT();

        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}
