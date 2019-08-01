package com.longshao.service.impl;

import com.longshao.dao.BaseDao;
import com.longshao.dto.Result;
import com.longshao.model.Users;
import com.longshao.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("ProService")
public class ServiceImpl implements ProService {
    @Autowired
    private BaseDao baseDao;
    @Override
    public void  login(String name, String password, Result result, HttpSession session) {
        List list = new ArrayList();
        list.add(name);
        list.add(password);
        String sql = "select * from users where username = '" + name + "' and password = '" + password + "'";
        List<Map> list1 = baseDao.selectMapsBySQL(sql);
        if (0 < list1.size()) {
            session.setAttribute("username", name);
            session.setAttribute("password", password);
            result.setSuccess(true);
            result.setContent("登录成功");
        }
    }

    @Override
    public void addUser(String name, String password,Result result) {
        Users users = new Users();
        users.setUsername(name);
        users.setPassword(password);
        baseDao.save(users);
        result.setSuccess(true);
        result.setContent("注册成功");
    }
}
