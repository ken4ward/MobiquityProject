package io.christdoes.pojo.posts;

public class PostsItem{
	private int id;
	private String title;
	private String body;
	private int userId;

	public PostsItem(){}

	public PostsItem(int id, String title, String body, int userId) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.userId = userId;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"PostsItem{" + 
			"id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",body = '" + body + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
