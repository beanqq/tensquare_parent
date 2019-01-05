package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitRepository extends MongoRepository<Spit,String> {



    //根据上级 id  parentId查询吐槽数据（分页）
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
