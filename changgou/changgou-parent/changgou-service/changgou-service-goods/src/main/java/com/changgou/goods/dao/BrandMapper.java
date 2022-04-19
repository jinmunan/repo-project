package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:jinmunan
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 根据品牌id查询品牌集合信息
     * @param categoryId
     * @return
     */
    @Select("select tb.* from tb_brand tb join tb_category_brand tcb on tb.id = tcb.brand_id and tcb.category_id = #{pid}")
    List<Brand> findByCategory(Integer categoryId);
}
