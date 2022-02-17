package com.example.domain;

public class ArticleJoinComment {
	
	private Integer id;
	private String name;
	private String content;
	private Integer comId;
	private String comName;
	private String comContent;
	private Integer comArticleId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public Integer getComArticleId() {
		return comArticleId;
	}
	public void setComArticleId(Integer comArticleId) {
		this.comArticleId = comArticleId;
	}
	@Override
	public String toString() {
		return "ArticleJoinComment [id=" + id + ", name=" + name + ", content=" + content + ", comId=" + comId
				+ ", comName=" + comName + ", comContent=" + comContent + ", comArticleId=" + comArticleId + "]";
	}
	
	
	
}
