package com.intellifleet.service.impl;

import com.intellifleet.bean.UserContext;
import com.intellifleet.service.AdvancedJWTService;
import com.intellifleet.utils.AdvanceJWTTokenUtils;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdvancedJWTServiceImpl implements AdvancedJWTService {

    private String ISSUER = "app1";
    private String AUDIENCE = "app2";
    private String KID = "kid-1";
    private String signEncPrivateKey = "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";
    private String signPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyS0aQocRVm850XwCU3nmI8urPzBun7epFXx+R914XNVoTDi7dHoNPsCvaMsyTWK5Y0qx7hyCH/ILjtaZmN2LRhHJ1Ny7x8+DjM19cquMFC/qSdKH1Wujda0in2yAp2RRexjKtZAkFQ8ywI3+wz64VeMMqXzqlKdMt6Pph0vj9I9ZhsLAPFigevQXEuNdhvXxYivT5zTQijJF/BD7kicYumD9Xbi3UTdf16IBqIJHyJ2FAQk8ztI8bxDpe2Z/k+s59JUwDte8H9Rry21h+ki4YgFIjmdtSUOypEoAejMi4c9UpR+Fn1Fy7rjsOefLmzXzx5iQqTOYPVvS92oYyBAqxQIDAQAB";
    private String encryptEncPrivateKey = "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";
    private String encryptPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxQRh6oCqZoqmiU0FORykc7LORD5JFsSAl2UDYCt2Ip/Am3uEMTjesicRpaBuHinmGW6xXih0NFPp3GVxuE6IrjMmgxfUdyf/b6c8f9dQTDHpuNNOwdkum1gi5fbPRp4HY2gwCmczKrycUNNty2jZICLZCiWIx3fzHogVYOMg8XS6CzdRbNzcShEIxObvMbWzPs8b/2QfRkUBXaiTmnRfqnXSNPnREoeB2OSpbF/VOJc3vBNI35DJOByZfIKr4lwBvDpGR7ZD3IWay8aRgsX+0TGkWCrKf1BhT+BycRxEZU3uIGWjWGxTHsGu8Qf6y+Y3BnyzokyZB5HDVL2saMR/awIDAQAB";

    @Override
    public String generateToken(UserContext userContext) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userContext.getUsername());
        claims.put("username", userContext.getUsername());
        claims.put("coreMatrix", userContext.getCoreMatrixBean());

        RSAPrivateKey signPriKey = AdvanceJWTTokenUtils.decryptPrivateKey(signEncPrivateKey);
        RSAPublicKey encPubKey = AdvanceJWTTokenUtils.decodePublicKey(encryptPublicKey);
        AdvanceJWTTokenUtils.JWTTokenCreaterDto jwtTokenCreaterDto = AdvanceJWTTokenUtils.JWTTokenCreaterDto.builder()
                .payload(claims)
                .issuer(ISSUER)
                .audience(AUDIENCE)
                .kid(KID)
                .signPrivateKey(signPriKey)     // its required to sign
                .encryptPublicKey(encPubKey)    // // its required to encrypt
                .build();

        String token = AdvanceJWTTokenUtils.getToken(jwtTokenCreaterDto);
        return token;
    }

    @Override
    public Map<String, Object> validateAndRetrievedToken(String token) {

        RSAPrivateKey encryptPriKey = AdvanceJWTTokenUtils.decryptPrivateKey(encryptEncPrivateKey);
        RSAPublicKey verifyPubKey = AdvanceJWTTokenUtils.decodePublicKey(signPublicKey);
        AdvanceJWTTokenUtils.JWTTokenValidatorDto jwtTokenValidatorDto = AdvanceJWTTokenUtils.JWTTokenValidatorDto.builder()
                .token(token)
                .issuer(ISSUER)
                .audience(AUDIENCE)
                .kid(KID)
                .decryptPrivateKey(encryptPriKey)   // its required to decrypt
                .verifyPublicKey(verifyPubKey)      // its Required to verify
                .build();

        Map<String, Object> stringObjectMap = AdvanceJWTTokenUtils.validateAndRetrievedToken(jwtTokenValidatorDto);
        return stringObjectMap;
    }
}
