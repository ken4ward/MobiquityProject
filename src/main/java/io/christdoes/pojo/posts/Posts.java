package io.christdoes.pojo.posts;

import java.util.List;

public class Posts{
	private List<PostsItem> posts;

	public void setPosts(List<PostsItem> posts){
		this.posts = posts;
	}

	public List<PostsItem> getPosts(){
		return posts;
	}

	@Override
 	public String toString(){
		return 
			"Posts{" + 
			"posts = '" + posts + '\'' + 
			"}";
		}
}