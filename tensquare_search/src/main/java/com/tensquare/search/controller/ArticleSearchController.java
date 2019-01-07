package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService  articleSearchService;


    //保存
    @PostMapping
    public Result save(@RequestBody Article article){
        articleSearchService.save(article);
        return new Result(true,StatusCode.OK,"保存成功");
    }


    //搜索功能
    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result searchArticle(@PathVariable String keywords,
                                @PathVariable int page,
                                @PathVariable int size){

        Page<Article> articlePage = articleSearchService.searchArticle(keywords, page, size);
        PageResult<Article> pageResult=new PageResult<>(articlePage.getTotalElements(),articlePage.getContent());

        return new Result(true,StatusCode.OK,"查询成功",pageResult);

    }
}
