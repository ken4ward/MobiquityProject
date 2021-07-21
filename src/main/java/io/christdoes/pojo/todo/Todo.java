package io.christdoes.pojo.todo;

import java.util.List;

public class Todo{
	private List<TodoItem> todo;

	public void setTodo(List<TodoItem> todo){
		this.todo = todo;
	}

	public List<TodoItem> getTodo(){
		return todo;
	}

	@Override
 	public String toString(){
		return 
			"Todo{" + 
			"todo = '" + todo + '\'' + 
			"}";
		}
}