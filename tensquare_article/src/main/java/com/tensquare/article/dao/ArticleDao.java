package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 文字审核   把文章状态state换为 1
     * 0:未审核  1：已审核
     */
    @Modifying
    @Query("update Article  set state='1' where id=?1")
    public void examine(String id);


    /**
     * 文章点赞  ，点一次链接,文章点赞数+1
     */

    @Modifying
    @Query(" update  Article  set  thumbup=thumbup+1 where id=?1 ")
    public int thumbup(String id);
}
