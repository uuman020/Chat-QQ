package yyz.njupt.server;


public class Common {

	// REQ CONTENDS
	public static final String LOGIN_AUTH = "LOGIN_AUTH";
	public static final String GET_FRIENDS_INFO = "GET_FRIENDS_INFO";
	public static final String GET_PERSONAL_DATA = "GET_PERSONAL_DATA";
	public static final String GET_ONLINE_UIDS = "GET_ONLINE_UIDS";

	// RESP CONTENDS
	public static final String LOGIN_AUTH_RESP = "LOGIN_AUTH_RESP";
	public static final String GET_FRIENDS_INFO_RESP = "GET_FRIENDS_INFO_RESP";
	public static final String GET_PERSONAL_DATA_RESP = "GET_PERSONAL_DATA_RESP";
	public static final String GET_ONLINE_UIDS_RESP = "GET_ONLINE_UIDS_RESP";

	// Login Exceptions
	public static final String LOGIN_USERNAME_NOT_FOUND = "LOGIN_USERNAME_NOT_FOUND";
	public static final String LOGIN_PASSWD_ERROR = "LOGIN_PASSWD_ERROR";
	public static final String LOGIN_USER_LOCKED = "LOGIN_USER_LOCKED";
	public static final String LOGIN_UNKONW_EXCEPTION = "LOGIN_UNKONW_EXCEPTION";
	
	// register
	public static final String REG_VERIFICATION_REQ = "REG_VERIFICATION_REQ";
	public static final String REG_REQ = "REG_REQ";
	public static final String REG_VERIFICATION_RESP_SUC = "REG_VERIFICATION_RESP_SUC";
	public static final String REG_VERIFICATION_RESP_ERR = "REG_VERIFICATION_RESP_ERR";
	public static final String REG_RESP_SUC = "REG_RESP_SUC";
	public static final String REG_RESP_ERR = "REG_RESP_ERR";
}
