package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 * 基础标签web层
 * 北京合众伟奇科技有限公司
 */
@RestController
@RequestMapping("/label")
@CrossOrigin  //解决跨域问题
public class LabelController {

        @Autowired
    private LabelService labelService;


    /**
     * 查询全部
     */
    @GetMapping()
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }


    /**
     * 根据id查询
     */

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }

    /**
     * 增加
     *
     */

    @PostMapping
    public Result add(@RequestBody  Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }
    /**
     * 修改
     */

    @PutMapping("/{id}")
public Result update(    @RequestBody Label label,@PathVariable String id){
              label.setId(id);
        labelService.update(label);  //自动更新或者保存
        return new Result(true,StatusCode.OK,"修改成功");
    }
    /**
     * 删除
     */
    @DeleteMapping("/{id}")
 public Result deleteById(@PathVariable String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
 }


    /**
     * 多条件搜索
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> search = labelService.findSearch(label);
        return  new Result(true,StatusCode.OK,"查询成功",search);
    }


    /**
     * 多条件分页
     */

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        Page<Label> pageData = labelService.pageSearch(label, page, size);

     return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
