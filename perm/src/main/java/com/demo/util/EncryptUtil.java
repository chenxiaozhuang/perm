package com.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 数据加密工具类型，主要提供了MD5和SHA1加密工具方法
 *
 * @author Lu Jianliang
 *
 */
public class EncryptUtil {
	public static final String KEY_SHA = "SHA";

	public static final String KEY_MD5 = "MD5";

	public static final String KEY_SHA1 = "SHA-1";

	private static final String KEY_SHA256 = "SHA-256";
	
	private static final String KEY_SHA512 = "SHA-512";

	/**
	 * BASE64解码
	 *
	 * @param key
	 *            -需要解码的密码字符串
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64编码
	 *
	 * @param key
	 *            -需要编码的字节数组
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * MD5加密
	 *
	 * @param data
	 *            - 需要加密的字节数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA加密
	 *
	 * @param data
	 *            -需要加密的字节数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(String shaType,byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(shaType);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * SHA1加密
	 *
	 * @param data
	 *            -需要加密的字节数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA1(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA1, data);

	}
	
	/**
	 * SHA256加密
	 *
	 * @param data
	 *            -需要加密的字节数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA256(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA256, data);

	}
	
	/**
	 * SHA512加密
	 *
	 * @param data
	 *            -需要加密的字节数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA512(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA512, data);

	}

	/**
	 * MD5加密
	 * 
	 * @param content
	 *            -待加密明文字符串
	 * @param charset
	 *            -待加密明文字符串采用的字符编码集
	 * @return -返回MD5加密后的字符串
	 */
	public static String encryptMD5(String content, String charset) {
		try {
			byte[] b = encryptMD5(content.getBytes(charset));
			StringBuffer buffer = new StringBuffer();

			for (int i = 0; i < b.length; i++) {
				String shaHex = Integer.toHexString(b[i] & 0xFF);
				if (shaHex.length() < 2) {
					buffer.append(0);
				}
				buffer.append(shaHex);
			}
			return buffer.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * MD5加密算法，该方法采用UTF-8编码集将明文进行转换，如果明文字符串为其他字符编码，<br>
	 * 请采用<code>encryptMD5(String content,String charset)</code>方法
	 * 
	 * @param content
	 *            -待加密明文字符串
	 * @return -返回加密后密文字符串
	 */
	public static String encryptMD5(String content) {
		return encryptMD5(content, "UTF-8");
	}

	/**
	 * SHA加密算法
	 * 
	 * @param content -待加密明文
	 * @param charset -明文所采用字符编码集
	 * @return
	 */
	public static String encryptSHA(String shaType,String content, String charset) {
		try {
			byte[] b = encryptSHA(shaType,content.getBytes(charset));
			StringBuffer buffer = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < b.length; i++) {
				String shaHex = Integer.toHexString(b[i] & 0xFF);
				if (shaHex.length() < 2) {
					buffer.append(0);
				}
				buffer.append(shaHex);
			}
			return buffer.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * SHA加密算法
	 * @param content -待加密明文字符串
	 * @return
	 */
	public static String encryptSHA(String shaType,String content){
		return encryptSHA(shaType,content,"UTF-8");
	}
	
	/**
	 * SHA1加密算法
	 * @param content -待加密明文字符串
	 * @param charset -明文所采用字符编码集
	 * @return
	 */
	public static String encryptSHA1(String content,String charset){
		
		return encryptSHA(KEY_SHA1, content,charset);
	}
	
	
	/**
	 * SHA1加密算法
	 * @param content -待加密明文字符串
	 * @return
	 */
	public static String encryptSHA1(String content){
		return encryptSHA1(content,"UTF-8");
	}
	
	/**
	 * SHA256加密算法
	 * @param content -待加密明文字符串
	 * @param charset -明文所采用字符编码集
	 * @return
	 */
	public static String encryptSHA256(String content,String charset){
		
		return encryptSHA(KEY_SHA256, content,charset);
	}
	
	
	/**
	 * SHA256加密算法
	 * @param content -待加密明文字符串
	 * @return
	 */
	public static String encryptSHA256(String content){
		return encryptSHA256(content,"UTF-8");
	}
	
	/**
	 * SHA512加密算法
	 * @param content -待加密明文字符串
	 * @param charset -明文所采用字符编码集
	 * @return
	 */
	public static String encryptSHA512(String content,String charset){
		
		return encryptSHA(KEY_SHA512, content,charset);
	}
	
	
	/**
	 * SHA512加密算法
	 * @param content -待加密明文字符串
	 * @return
	 */
	public static String encryptSHA512(String content){
		return encryptSHA512(content,"UTF-8");
	}

}