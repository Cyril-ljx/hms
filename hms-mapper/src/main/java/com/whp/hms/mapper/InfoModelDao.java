package com.whp.hms.mapper;

import com.whp.hms.core.entity.InfoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 酒店信息模板(InfoModel)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface InfoModelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    InfoModel queryById(Integer id);


    InfoModel isUseList();

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<InfoModel> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param infoModel 实例对象
     * @return 对象列表
     */
    List<InfoModel> queryAll(InfoModel infoModel);

    /**
     * 新增数据
     *
     * @param infoModel 实例对象
     * @return 影响行数
     */
    int insert(InfoModel infoModel);

    /**
     * 修改数据
     *
     * @param infoModel 实例对象
     * @return 影响行数
     */
    int update(InfoModel infoModel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("select count(*) from info_model")
    Integer queryCount();

    /**
     * 通过主键批量删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int batchDelete(List<Integer> ids);

    /*
     *通过主键进行首显
     *
     */
    @Update("update info_model set is_use=case when id=#{id} then 1 else 0 end")
    boolean showById(Integer id);
}