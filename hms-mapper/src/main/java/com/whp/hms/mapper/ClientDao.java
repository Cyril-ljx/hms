package com.whp.hms.mapper;

import com.whp.hms.core.entity.Client;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 客户：订房的用户(Client)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface ClientDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Client queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Client> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param client 实例对象
     * @return 对象列表
     */
    List<Client> queryAll(Client client);

    /**
     * 新增数据
     *
     * @param client 实例对象
     * @return 影响行数
     */
    int insert(Client client);

    /**
     * 修改数据
     *
     * @param client 实例对象
     * @return 影响行数
     */
    int update(Client client);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Integer queryCount(String nickname, Integer state);

    List<Client> list(@Param("offset") Integer offset, @Param("limit") Integer limit,
                      @Param("nickname") String nickname, @Param("state") Integer state);

    @Select("select * from client where username = #{username} limit 1")
    Client queryByUserName(@Param("username") String username);

}