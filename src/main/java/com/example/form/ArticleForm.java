package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ArticleForm {
	
	@NotBlank(message="投稿者名は必須です")
	@Size(min=1,max=50,message="名前は50文字以内にしてください")
	private String name;
	
	@NotBlank(message="投稿内容は必須です")
	private String content;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}

}
