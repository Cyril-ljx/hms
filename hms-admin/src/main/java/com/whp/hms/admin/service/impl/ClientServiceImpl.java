package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.ClientService;
import com.whp.hms.core.entity.Client;
import com.whp.hms.mapper.ClientDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户：订房的用户(Client)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {
    @Resource
    private ClientDao clientDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Client queryById(Integer id) {
        return this.clientDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Client> queryAllByLimit(int offset, int limit) {
        return this.clientDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param client 实例对象
     * @return 实例对象
     */
    @Override
    public Client insert(Client client) {
        this.clientDao.insert(client);
        return client;
    }

    /**
     * 修改数据
     *
     * @param client 实例对象
     * @return 实例对象
     */
    @Override
    public Client update(Client client) {
        this.clientDao.update(client);
        return this.queryById(client.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.clientDao.deleteById(id) > 0;
    }

    @Override
    public Client queryByUserName(String username) {
        return this.clientDao.queryByUserName(username);
    }

    @Override
    public List<Client> list(Integer offset, Integer limit, String nickname, Integer state) {
        return this.clientDao.list(offset,limit,nickname,state);

    }

    @Override
    public Integer queryCount(String nickname, Integer state) {
        return this.clientDao.queryCount(nickname,state);    }
}