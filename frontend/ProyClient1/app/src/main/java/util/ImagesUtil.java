package util;

import org.apache.commons.codec.binary.Base64;

public class ImagesUtil {

	public static String encodeImage(byte[] imageByteArray){
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}


	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}

}
