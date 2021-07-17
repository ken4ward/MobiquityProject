package io.christdoes.pojo.user;

import java.util.List;

public class Response{
	private List<UserResponseItem> response;
	public void setResponse(List<UserResponseItem> response){
		this.response = response;
	}
	public List<UserResponseItem> getResponse(){
		return response;
	}
}