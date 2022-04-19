package com.changgou.goods.dao;
import com.changgou.goods.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:jinmunan
 * @Description:Category的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface CategoryMapper extends Mapper<Category> {
//    /**
//     * 根据分类的父节点查询所有子节点集合
//     */
//    @Select("select tb.* from tb_brand tb join tb_category_brand tcb on tb.id = tcb.brand_id and tcb.category_id = #{pid}")
//    List<Category> findByParentId(Integer pid);
}
