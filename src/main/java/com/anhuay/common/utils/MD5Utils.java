package com.anhuay.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
	private static final String SALT = "1qazxsw2";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}
	public static void main(String[] args) {
		/*secadm	安全管理员
auditdam	审计管理员
root	系统管理员
xingjunhao 最高权限*/
		System.out.println(MD5Utils.encrypt("secadm", "123456"));
		System.out.println(MD5Utils.encrypt("auditdam", "123456"));
		System.out.println(MD5Utils.encrypt("root", "123456"));
		System.out.println(MD5Utils.encrypt("xingjunhao", "123456"));
	}

}
