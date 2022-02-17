package com.example.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.ArticleJoinComment;
import com.example.domain.Comment;

@Repository
public class ArticleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	//private static final RowMapper<Article>ARTI_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);
	//private static final RowMapper<Comment>COM_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);
	private static final RowMapper<ArticleJoinComment>ARTICOM_ROW_MAPPER = new BeanPropertyRowMapper<>(ArticleJoinComment.class);
	
	public List<Article> findAll(){
		
		/*
		String sql = "select * from articles;";
		List<Article>articleList = template.query(sql,ARTI_ROW_MAPPER);
		return articleList;
		*/
		
		
		
		//中級課題
		String sql = "select a.id,a.name,a.content,c.id com_id,c.name com_name,c.content com_content,c.article_id com_article_id from articles a left join comments c ON a.id = c.article_id;";
		List<ArticleJoinComment>articleJoinList = template.query(sql,ARTICOM_ROW_MAPPER);
		List<Article>articleList = new ArrayList<Article>();
		List<Comment>commentList = new LinkedList<>();
		int i=0;
		for(ArticleJoinComment a:articleJoinList) {
			if(i!=a.getId() || i==0) {
			Article article = new Article();
			article.setId(a.getId());
			article.setName(a.getName());
			article.setContent(a.getContent());
			articleList.add(article);
			}
			Comment comment = new Comment();
			comment.setId(a.getComId());
			comment.setName(a.getComName());
			comment.setContent(a.getComContent());
			comment.setArticleId(a.getComArticleId());
			commentList.add(comment);
			i = a.getId();
		}
		System.out.println(articleList);
		for(Article art:articleList) {
			List<Comment>list = new ArrayList<>();
			for(Comment c:commentList) {
				if(c.getArticleId() == art.getId()) {
					list.add(c);
				}
			}
			art.setCommentList(list);
		}
		articleList.sort((a,b)-> a.getId()- b.getId());
		Collections.reverse(articleList);
		return articleList;
		
	}
	
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String insertSql = "INSERT INTO articles(name,content) VALUES (:name, :content);";
        template.update(insertSql, param);
	}
	
	public void deleteById(int id) {
		String deleteSql = "DELETE from comments where article_id = :articleId; DELETE from articles where id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId",id).addValue("id",id);
		template.update(deleteSql, param);
		
	}
	

}
