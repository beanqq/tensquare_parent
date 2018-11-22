package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 企业信息数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{

    //查询热门企业  ，根据jpa的关键字命名查询
    public List<Enterprise> findByIshot(String ishot);
}
