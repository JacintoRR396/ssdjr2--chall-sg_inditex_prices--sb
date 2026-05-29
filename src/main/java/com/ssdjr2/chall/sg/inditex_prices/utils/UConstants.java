package com.ssdjr2.chall.sg.inditex_prices.utils;

public class UConstants {

	private UConstants() {
		throw new IllegalStateException( "Utility class" );
	}

	// --- GENERIC ---
	public static final String NOT_APPLY = "N/A";

	// --- LOGS ---
	public static final String MSG_BASE_OK = "[OK] ";
	public static final String MSG_BASE_INFO = "[INFO] ";
	public static final String MSG_BASE_INFO_REQUEST = "[INFO-REQ] ";
	public static final String MSG_BASE_INFO_RESPONSE = "[INFO-RESP] ";
	public static final String MSG_BASE_DEBUG = "[DEBUG] ";
	public static final String MSG_BASE_ERROR = "[ERROR] ";

	// --- FORMAT DATE ---
	public static final String S_FORMAT_DATETIME_BACK = "yyyy-MM-dd HH:mm:ss";
	public static final String S_FORMAT_TIMESTAMP_BACK = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
}
