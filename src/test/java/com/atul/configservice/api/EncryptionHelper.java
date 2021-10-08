package com.atul.configservice.api;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;


@Slf4j
public class EncryptionHelper {

  //TODO testcase to make sure key length is multiple of 8
  private static final String ENCRYPTION_KEY = "f$8!Lo&@(as^-*c2";
  private static final String ENCRYPTION_ALGO = "AES/ECB/PKCS5Padding";
  private static final SecretKeySpec key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");


  public static String encrypt(String text) {
    try {
      Cipher aes = Cipher.getInstance(ENCRYPTION_ALGO);
      aes.init(Cipher.ENCRYPT_MODE, key);
      byte[] encrypted = aes.doFinal(text.getBytes());
      return new String(Base64.getEncoder().encode(encrypted));
    } catch (Exception e) {
      log.error("Encrypting:" + text, e);
    }
    return null;
  }

  public static String decrypt(String encryptedText) {
    if (encryptedText == null || encryptedText.length() == 0) {
      return null;
    }
    try {
      Cipher aes = Cipher.getInstance(ENCRYPTION_ALGO);
      aes.init(Cipher.DECRYPT_MODE, key);
      return new String(aes.doFinal(Base64.getDecoder().decode(encryptedText)),"UTF-8");
    } catch (Exception e) {
      log.error("Error while decrypting. Text to decrypt : {}. Exception : {} {}",
                encryptedText, e.getMessage(), e.getStackTrace());
    }
    return null;
  }

  public static void main(String[] args) {
    String res = EncryptionHelper.encrypt("deviceId=mydeviceid|timestamp=1568807750");
    System.out.println(res);


    res = EncryptionHelper.decrypt("5UJEcbyFrG6/y6Az4W5ngPSI/MoTDxOp3/lvwc+J3D8IOSiCfYdXycxnVQl409jj");
    System.out.println(res);
  }


}
