package blockchain;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {
	public static byte[] encrypt(byte[] keyBytes ,byte[] message){
//		byte[] keyBytes = Base64.getDecoder().decode(key); //bytes of key
		Cipher cipher_RSA;
		try {
			cipher_RSA = Cipher.getInstance("RSA");
			X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pk = keyFactory.generatePublic(spec);
			cipher_RSA.init(Cipher.ENCRYPT_MODE, pk); 
			return cipher_RSA.doFinal(message);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
				BadPaddingException | InvalidKeyException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static String stripKey(String key){
		key = key.replace("-----BEGIN RSA PUBLIC KEY-----", "");
		key = key.replace("-----END RSA PUBLIC KEY-----", "");
		key = key.replaceAll("\r\n", "");
		return key;
	}

}
