package com.dhl.serv.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.SystemUtils;

public class ImagesUtil {

	public static String encodeImage(byte[] imageByteArray){
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}


	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}

}
