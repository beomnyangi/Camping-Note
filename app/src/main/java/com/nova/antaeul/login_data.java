package com.nova.antaeul;

public class login_data {
	private static final String TAG= "log";
	String id;
	String pw;
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	
	public login_data(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
}