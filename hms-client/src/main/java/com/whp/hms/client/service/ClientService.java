package com.whp.hms.client.service;

import com.whp.hms.core.entity.Client;
import com.whp.hms.core.entity.Manager;
import com.whp.hms.core.vo.Response;

import java.util.List;

/**
 * 客户：订房的用户(Client)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface ClientService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Client queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Client> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param client 实例对象
     * @return 实例对象
     */
    Response insert(Client client);

    /**
     * 修改数据
     *
     * @param client 实例对象
     * @return 实例对象
     */
    Client update(Client client);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Client queryByUserName(String username);

}