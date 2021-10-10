package com.nova.antaeul;

public class searchaddress_data {
	private static final String TAG= "log";

	String place_name;
	String address_name;
	String road_address_name;
	double x;
	double y;

	public String getPlace_name() {
		return place_name;
	}
	public String getAddress_name() {
		return address_name;
	}
	public String getRoad_address_name() {
		return road_address_name;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	public searchaddress_data(String place_name, String address_name, String road_address_name, double x, double y){
		this.place_name = place_name;
		this.address_name = address_name;
		this.road_address_name = road_address_name;
		this.x = x;
		this.y = y;
	}
}