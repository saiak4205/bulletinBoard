package com.example.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

@Controller
@RequestMapping("/note")
public class ArticleController {
	
	@Autowired
	private ArticleService artService;
	
	@Autowired
	private CommentService comService;
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping("")
	public String index() {
		List<Article>articleList = artService.findAll();
		articleList.forEach(a->a.setCommentList(comService.findByArticleId(a.getId())));
		application.setAttribute("articleList", articleList);
		return "board.html";
	}
	
	@RequestMapping("/write")
	public String insertArticle(
			@Validated ArticleForm form
			,BindingResult result
			,Model model) {
		if(result.hasErrors()) {
			return "board.html";
		}
		Article art = new Article();
		BeanUtils.copyProperties(form, art);
		artService.insert(art);
		return "redirect:";
	}
	
	@RequestMapping("/write2")
	public String insertComment(
			@Validated CommentForm form
			,BindingResult result
			,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("ErrorArticleId",form.getArticleId());
			return "board.html";
		}
		Comment com = new Comment();
		BeanUtils.copyProperties(form, com);
		com.setArticleId(Integer.parseInt(form.getArticleId()));
		comService.insert(com);
		return "redirect:";
	}
	
	@RequestMapping("/delete")
	public String deleteArticle(@ModelAttribute("id") Integer id) {
		artService.deleteById(id);
		return "redirect:";
	}

}
