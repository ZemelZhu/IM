package com.im.zemel.controller;

import com.im.zemel.pojo.Article;
import com.im.zemel.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/Article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/newArticle")
    public Integer newArticle(@RequestBody Article article, HttpSession session) {
        return articleService.newArticle(article,session);
    }
    @RequestMapping("/deleteArticleById")
    public String deleteArticleById(@RequestBody Article article) {
        return articleService.deleteArticleById(article);
    }

    @RequestMapping("/getArticleById")
    public Article getArticleById(@RequestBody Map map) {
//        map是为解决vue.axios传参问题, 把json解析成map
        int id= Integer.parseInt((String) map.get("id"));
        Article articleById = articleService.getArticleById(id);
       return  articleById;
    }


}

