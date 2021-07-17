package io.christdoes.pojo.user.posts;

import java.util.List;

public class Response{
	private List<PostItem> response;

	public void setResponse(List<PostItem> response){
		this.response = response;
	}

	public List<PostItem> getResponse(){
		return response;
	}
}