package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentForm {
	
	@NotBlank(message="コメント名は必須です")
	@Size(min=1,max=50,message="名前は50文字以内にしてください")
	private String name;
	
	@NotBlank(message="コメント内容は必須です")
	private String content;
	
	private String articleId;
	
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
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}
	
	
}
