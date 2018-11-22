package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private LabelDao labelDao;


    /**
     * 查询全部标签
     */

    public List<Label> findAll(){
        return labelDao.findAll();
    }
    /**
     * 根据id查询标签
     */

    public Label findById(String id){
         return labelDao.findById(id).get();
    }

    /**
     * 增加标签
     */

     public void add(Label label){

         long id = idWorker.nextId();
         label.setId(id+"");
         labelDao.save(label);
     }

    /**
     * 修改标签
     */

    public void  update(Label label){
             labelDao.save(label);
    }

    /**
     * 删除标签
     */

    public void deleteById(String id){
        labelDao.deleteById(id);
    }


    /**
     * 条件查询specification体系
     */

    public List<Label> findSearch(Label label){

        return  labelDao.findAll(new Specification<Label>() {

            /**
             *
             * @param root  根对象      where label.labenName like ....
             * @param query   查询对象
             * @param cb  构建查询条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {


                //定义predicate查询条件集合，保存多个查询条件
                List<Predicate> predicateList =new ArrayList<>();
                if(null!=label.getLabelname()&&!"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }

                if(null!=label.getState()&&!"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    predicateList.add(predicate);
                }


                //cb.and(数组，需要固定长度)

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        });

    }


    /**
     * 分页，条件查询  pageable
     */

    public Page<Label> pageSearch(Label label, int page, int size){


        Pageable pageable=PageRequest.of(page-1,size);

        Specification<Label> specification = new Specification<Label>() {

            /**
             *
             * @param root  根对象      where label.labenName like ....
             * @param query   查询对象
             * @param cb  构建查询条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {


                //定义predicate查询条件集合，保存多个查询条件
                List<Predicate> predicateList = new ArrayList<>();
                if (null != label.getLabelname() && !"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }

                if (null != label.getState() && !"".equals(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    predicateList.add(predicate);
                }


                //cb.and(数组，需要固定长度)

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };


      return  labelDao.findAll(specification,pageable);


    }

}
