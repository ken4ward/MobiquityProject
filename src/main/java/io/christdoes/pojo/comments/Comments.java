package io.christdoes.pojo.comments;

import java.util.List;

public class Comments{
	private List<CommentsItem> comments;

	public void setComments(List<CommentsItem> comments){
		this.comments = comments;
	}

	public List<CommentsItem> getComments(){
		return comments;
	}

	@Override
 	public String toString(){
		return 
			"Comments{" + 
			"comments = '" + comments + '\'' + 
			"}";
		}
}