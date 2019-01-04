package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 根据标签id查询最新问题列表
     * hql语句，半年了，第一次用到
     */

    @Query("select p from Problem p where p.id in (select  problemid from Pl  where   labelid=?1 ) order by p.replytime desc ")
    public Page<Problem> findNewListByLabelId(String lableId, Pageable pageable);


    /**
     * 热门回答--分页
     * 根据回复数从高到低排序
     */
    @Query("select p from Problem p where p.id in (select  problemid from Pl  where   labelid=?1 ) order by p.reply desc ")
    public Page<Problem> findHotListByLabelId(String labelid,Pageable pageable);


    /**
     * 等待回答列表
     * 回复数是0
     */
    @Query("select p from Problem p where p.id in (select  problemid from Pl  where   labelid=?1 )  and p.reply=0     order by p.reply desc ")
    public Page<Problem> findWaitListByLabelId(String labelid,Pageable pageable);
}
