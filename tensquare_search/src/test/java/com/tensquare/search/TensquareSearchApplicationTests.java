package com.tensquare.search;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TensquareSearchApplicationTests {

    @Autowired
    private ElasticsearchTemplate et;

@Autowired
private ArticleSearchDao  dao;

    @Test
    public void contextLoads() {

       // et.createIndex(Article.class);
       // et.putMapping(Article.class);

        Pageable pageable=PageRequest.of(0,10);
        Page<Article> page = dao.findByTitleOrContentLike("java", "java", pageable);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());

    }

}

