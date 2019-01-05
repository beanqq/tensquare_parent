package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 吐槽微服务的controller层
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;


    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 吐槽点赞
     * 点赞成功，做个标识，放入缓存中，避免重复点赞，缓存的key就用用户id来表示把
     * @param spitId
     * @return
     */
    @PutMapping("/thumbup/{spitId}")
 public Result thumbup(@PathVariable  String spitId){

        //先把用户id写死
        String userId="123";
        if(null==redisTemplate.opsForValue().get("thumbup_" + userId)){
            //缓存中没有
            spitService.thumbup(spitId);//点赞操作
            //保存到缓存中
            redisTemplate.opsForValue().set("thumbup_"+userId,1);
            return  new Result(true,StatusCode.OK,"点赞成功");
        };



        return  new Result(false,StatusCode.ERROR,"不能重复点赞");
 }

    //根据上级Id查询吐槽
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable String parentid,@PathVariable int page,
                                 @PathVariable int size){
        Page<Spit> spitPage = spitService.findByParentid(parentid, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(spitPage.getTotalElements(),spitPage.getContent()));
    }


 //全部列表
    @GetMapping
    public Result findAll(){
        List<Spit> list = spitService.findAll();
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

  //根据id查询
  @GetMapping("/{id}")
  public Result findById(@PathVariable  String id){
      return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
  }


  //增加吐槽
    @PostMapping
    public Result save(@RequestBody   Spit spit){
         spitService.save(spit);
         return  new Result(true,StatusCode.OK,"保存成功");
    }
//修改吐槽
    @PutMapping("/{id}")
    public Result update(@RequestBody Spit spit, @PathVariable String id){
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }


  //删除吐槽
  @DeleteMapping("/{id}")
  public Result deleteById(@PathVariable String  id){
        spitService.delete(id);
      return new Result(true,StatusCode.OK,"修改成功");
  }


}
