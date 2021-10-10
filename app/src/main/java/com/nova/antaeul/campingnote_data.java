package com.nova.antaeul;

public class campingnote_data {
	private static final String TAG= "log";

	String name;
	String imageexplain;
	String imageuri;
	String review;
	String address;
	double x;
	double y;
	String number;
	String writedate;

	public String getName() {
		return name;
	}
	public String getImageexplain() {
		return imageexplain;
	}
	public String getImageuri() {
		return imageuri;
	}
	public String getReview() {
		return review;
	}
	public String getAddress() {
		return address;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public String getNumber() {
		return number;
	}
	public String getWritedate() {
		return writedate;
	}

	public campingnote_data(String name, String imageexplain, String imageuri, String review, String address, double x, double y, String number, String writedate){
		this.name = name;
		this.imageexplain = imageexplain;
		this.imageuri = imageuri;
		this.address = address;
		this.x = x;
		this.y = y;
		this.review = review;
		this.number = number;
		this.writedate = writedate;
	}
}