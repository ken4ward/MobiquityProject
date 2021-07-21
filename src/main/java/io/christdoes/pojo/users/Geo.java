package io.christdoes.pojo.users;

public class Geo{
	private String lng;
	private String lat;

	public Geo(){}

	public Geo(String lng, String lat) {
		this.lng = lng;
		this.lat = lat;
	}

	public void setLng(String lng){
		this.lng = lng;
	}

	public String getLng(){
		return lng;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"Geo{" + 
			"lng = '" + lng + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}
