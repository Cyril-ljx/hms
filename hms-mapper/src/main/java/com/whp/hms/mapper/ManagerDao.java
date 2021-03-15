package com.whp.hms.mapper;

import com.whp.hms.core.entity.Client;
import com.whp.hms.core.entity.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员：可以登录后台管理的用户(Manager)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface ManagerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Manager queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Manager> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param manager 实例对象
     * @return 对象列表
     */
    List<Manager> queryAll(Manager manager);

    /**
     * 新增数据
     *
     * @param manager 实例对象
     * @return 影响行数
     */
    int insert(Manager manager);

    /**
     * 修改数据
     *
     * @param manager 实例对象
     * @return 影响行数
     */
    int update(Manager manager);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Manager queryByUserName(@Param("username") String username);

    List<Manager> queryAdminList(@Param("id") int uid, @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer queryAdminCount(@Param("id") int uid);
}