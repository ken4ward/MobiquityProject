package io.christdoes.pojo.comments;

public class CommentsItem{
	private String name;
	private int postId;
	private int id;
	private String body;
	private String email;

	public CommentsItem(){}

	public CommentsItem(String name, int postId, int id, String body, String email) {
		this.name = name;
		this.postId = postId;
		this.id = id;
		this.body = body;
		this.email = email;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPostId(int postId){
		this.postId = postId;
	}

	public int getPostId(){
		return postId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"CommentsItem{" + 
			"name = '" + name + '\'' + 
			",postId = '" + postId + '\'' + 
			",id = '" + id + '\'' + 
			",body = '" + body + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
