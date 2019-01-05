package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitRepository;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitRepository spitRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;


    //吐槽点赞
    public void thumbup(String spitId){
        Query query=new Query();
         query.addCriteria(Criteria.where("_id").is(spitId));

        Update update=new Update();
        update.inc("thumbup",1);//自增1操作
        mongoTemplate.updateFirst(query,update,"spit");
    }


    //根据上级Id查询吐槽数据
    public Page<Spit> findByParentid(String parentid,int page,int size){
        Pageable pageable=PageRequest.of(page-1,size);
       return  spitRepository.findByParentid(parentid,pageable);
    }



    //查询所有
    public List<Spit> findAll(){
        return  spitRepository.findAll();
    }

    //根据id查询
    public Spit findById(String id){
        return spitRepository.findById(id).get();
    }

    /**
     * 发布吐槽
     * 初始值，都设置为0
     * 要考虑：有父节点的情况，父节点的吐槽回复数量要+1
     * @param spit
     */
    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态

       if(null!=spit.getParentid()&&!"".equals(spit.getParentid())){
           Query query=new Query();
           query.addCriteria(Criteria.where("_id").is(spit.getParentid()));

           Update update=new Update();
           update.inc("comment",1);//自增1操作
           mongoTemplate.updateFirst(query,update,"spit");
       }
        spitRepository.save(spit);
    }

    //修改
    public void update(Spit spit){
        spitRepository.save(spit);
    }

    //删除
    public void delete(String id){
        spitRepository.deleteById(id);
    }
}
