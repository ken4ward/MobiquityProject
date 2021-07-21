package io.christdoes.pojo.users;

import java.util.List;

public class User{

	private List<UserItem> user;

	public void setUser(List<UserItem> user){
		this.user = user;
	}

	public List<UserItem> getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"user = '" + user + '\'' + 
			"}";
		}
}