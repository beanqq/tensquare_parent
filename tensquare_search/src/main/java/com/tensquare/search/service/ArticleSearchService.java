package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao   articleSearchDao;


    /**
     * 搜索标题和内容
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    public Page<Article> searchArticle(String keywords,int page,int size){

        Pageable pageable=PageRequest.of(page-1,size);
        return  articleSearchDao.findByTitleOrContentLike(keywords,keywords,pageable);
    }

    //保存
    public void save(Article article) {


        articleSearchDao.save(article);

    }
}
