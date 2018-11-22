package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口  招聘信息表
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{


    //推荐职位列表  top6,就是取前6个信息
  public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);

    //最新职位列表  not  意思是不等于
public  List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);
}
