package com.whp.hms.mapper;

import com.whp.hms.core.entity.TempService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 临时服务：用户通过 前台预定系统 发送的服务请求(TempService)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:34
 */
public interface TempServiceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TempService queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TempService> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tempService 实例对象
     * @return 对象列表
     */
    List<TempService> queryAll(TempService tempService);

    /**
     * 新增数据
     *
     * @param tempService 实例对象
     * @return 影响行数
     */
    int insert(TempService tempService);

    /**
     * 修改数据
     *
     * @param tempService 实例对象
     * @return 影响行数
     */
    int update(TempService tempService);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<TempService> list(@Param("offset") Integer offset, @Param("limit") Integer limit,
                           @Param("state") Integer state);

    Integer queryCount(@Param("state") Integer state);
}