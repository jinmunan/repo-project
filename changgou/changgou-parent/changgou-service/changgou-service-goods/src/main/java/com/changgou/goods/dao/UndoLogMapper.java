package com.changgou.goods.dao;
import com.changgou.goods.pojo.UndoLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:jinmunan
 * @Description:UndoLog的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface UndoLogMapper extends Mapper<UndoLog> {
}
