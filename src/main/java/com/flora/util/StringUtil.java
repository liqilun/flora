package com.flora.util;

import java.io.UnsupportedEncodingException;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 * String Utility Class This is used to encode passwords programmatically
 * 
 */
public class StringUtil {
	public static String md5(byte[] input) {
		return encryptPassword(input, "md5");
	}
	public static String md5(String text) {
		return md5(text, "utf-8");
	}

	public static String md5(String text, String encoding) {
		return encryptPassword(text, encoding, "md5");
	}

	public static String md5(String text, int length) {
		String result = md5(text);
		if (result.length() > length)
			result = result.substring(0, length);
		return result;
	}

	public static String sha(String text, String encoding) {
		return encryptPassword(text, encoding, "sha");
	}

	private static String encryptPassword(String password, String encoding, String algorithm) {
		try {
			byte[] unencodedPassword = password.getBytes(encoding);
			return encryptPassword(unencodedPassword, algorithm);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	private static String encryptPassword(byte[] unencodedPassword, String algorithm) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			return null;
		}
		md.reset();
		md.update(unencodedPassword);
		byte[] encodedPassword = md.digest();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static String getUID() {
		String UID = new UID().toString().replace(':', '_').replace('-', '_');
		return "s" + UID;
	}

	/**
	 * 
	 * @param input
	 * @param size
	 * @return
	 */
	public static String enabbr(String input, int size) {
		if (StringUtils.isBlank(input))
			return null;
		int bytelength = getByteLength(input);
		if (bytelength < size) return input;
		StringBuilder sb = new StringBuilder(size);
		int count = 0;
		for(int i=0;i<input.length();i++){
			char c = input.charAt(i);
			sb.append(c);
			if(c > 128) count += 2;
			else count ++;
			if(count >= size) break;
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param input
	 * @param size
	 * @return
	 */
	public static String enabbrstr(String input, int size, String addStr) {
		if (StringUtils.isBlank(input))
			return null;
		int bytelength = getByteLength(input);
		if (bytelength < size) return input;
		String ss = enabbr(input, size);
		return ss + addStr;
	}

	public static int getByteLength(String input) {
		if (input == null)
			return 0;
		int length = input.length();
		int byteLength = length;
		for (int i = 0; i < length; i++)
			if (input.charAt(i) > 128)
				byteLength++;
		return byteLength;
	}

	public static String toUnicode(String original) {
		if (original == null)
			return null;
		String result = "";
		for (int i = 0, length = original.length(); i < length; i++) {
			if (original.charAt(i) > 0 && original.charAt(i) < 256)
				result += original.charAt(i);
			else
				result += "\\u" + Integer.toHexString(original.charAt(i)).toUpperCase();
		}
		return result;
	}

	public static final String upper = "ABCDEFGHJKLMNPQRSTUVWXYZ";
	public static final String lower = "abcdefghijkmnpqrstuvwxyz";
	public static final String digital = "23456789"; // 0,1

	/**
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		return getRandomString(length, true, true, true);
	}

	public static String getUpperRandomString(int length) {
		return getRandomString(length, true, false, false);
	}

	public static String getLowerRandomString(int length) {
		return getRandomString(length, false, true, false);
	}

	public static String getDigitalRandomString(int length) {
		return getRandomString(length, false, false, true);
	}

	public static String getRandomString(int length, boolean includeUpper, boolean includeLower, boolean includeDigital) {
		if (length > 100)
			length = 100;
		String s = "";
		if (includeUpper)
			s += upper;
		if (includeLower)
			s += lower;
		if (includeDigital)
			s += digital;
		if (length > 100){
			throw new IllegalArgumentException("");
		}
		return getRandomString(s, length);
		
	}
	/**
	 * @param length
	 * @return
	 */
	public static String getRandomString(String charArray, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(charArray.charAt(RandomUtils.nextInt(charArray.length())));
		}
		return sb.toString();
	}

	public static String getSearchKey(String qryStr) {
		if (StringUtils.isBlank(qryStr))
			return "";
		String result = substitute(qryStr, "[^ \\w$\\u4e00-\\u9fa5]+", "", true);
		result = result.replaceAll("AND", "and").replaceAll("OR", "or").replaceAll("NOT", "not");
		return result.trim();
	}

	public static String md5WithKey(String str) {
		return md5(str + "7fuTP5", "utf-8");
	}

	public static String md5WithKey(String str, int length) {
		String result = md5WithKey(str);
		if (result.length() > length)
			return result.substring(0, length);
		return result;
	}

	/**
	 * 
	 * @param str
	 * @param minstr
	 * @param maxstr
	 * @return
	 */
	public static boolean between(String str, String minstr, String maxstr) {
		return str.compareTo(minstr) >= 0 && str.compareTo(maxstr) <= 0;
	}

	/**
	 * author: bob date: 20100729
	 */
	public static String parse2HTML(String txt) {
		String reg = "(http://(([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?))";
		if (StringUtils.isBlank(txt))
			return "";
		return txt.replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
				.replaceAll(reg, "<a href='$1' target='_blank'>$2</a>");
	}

	/**
	 * Extract the filename from the given path, e.g. "mypath/myfile.txt" ->
	 * "myfile.txt".
	 * 
	 * @param path
	 *            the file path (may be <code>null</code>)
	 * @return the extracted filename, or <code>null</code> if none
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf('/');
		if (separatorIndex == -1)
			separatorIndex = path.lastIndexOf('\\');
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}

	/**
	 * Extract the filename from the given path, e.g. "mypath/myfile.txt" ->
	 * "mypath".
	 * 
	 * @param path
	 *            the file path (may be <code>null</code>)
	 * @return the extracted filename, or <code>null</code> if none
	 */
	public static String getFilepath(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf('/');
		if (separatorIndex == -1)
			separatorIndex = path.lastIndexOf('\\');
		return (separatorIndex != -1 ? path.substring(0, separatorIndex) : "");
	}

	/**
	 * Extract the filename extension from the given path, e.g.
	 * "mypath/myfile.txt" -> "txt".
	 * 
	 * @param path
	 *            the file path (may be <code>null</code>)
	 * @return the extracted filename extension, or <code>null</code> if none
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf('.');
		return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
	}

	public static String getSplitString(String str, String split, int length) {
		int splen = split.length();
		StringBuilder sb = new StringBuilder(str);
		int start = length;
		while (start < sb.length() - 1) {
			sb.insert(start, split);
			start += length + splen;
		}
		return sb.toString();
	}

	public static String insertStr(String src, String insert) {
		if (StringUtils.isBlank(src))
			return "";
		if (StringUtils.isBlank(insert))
			return src;
		String result = "";
		for (char c : src.toCharArray()) {
			result += c + insert;
		}
		result = result.substring(0, result.length() - insert.length());
		return result;
	}

	
	public static Set getRandomNumber(int max, int count) {
		Set v = new TreeSet();
		Random r = new Random();
		boolean b = true;
		while (b) {
			v.add(r.nextInt(max));
			if (v.size() == count) {
				b = false;
			}
		}
		return v;
	}

	public static List<String> findByRegex(String src, String reg, boolean unique) {
		List<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(src);
		while (matcher.find()) {
			String s = matcher.group();
			if (!unique || !result.contains(s))
				result.add(s);
		}
		return result;
	}

	public static String findFirstByRegex(String src, String reg) {
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(src);
		if (matcher.find())
			return matcher.group();
		return "";
	}

	public static String substitute(String src, String reg, String replacement, boolean ignoreCase) {
		Pattern pattern = null;
		if (ignoreCase) {
			pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		} else {
			pattern = Pattern.compile(reg);
		}
		Matcher matcher = pattern.matcher(src);
		String result = matcher.replaceAll(replacement);
		return result;
	}

	public static boolean regMatch(String src, String reg, boolean ignoreCase) {
		Pattern pattern = null;
		if (ignoreCase) {
			pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		} else {
			pattern = Pattern.compile(reg);
		}
		Matcher matcher = pattern.matcher(src);
		return matcher.find();
	}

	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	public static String getString(byte[] bytes, String charset) {
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
