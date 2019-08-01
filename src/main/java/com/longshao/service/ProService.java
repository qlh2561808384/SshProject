package com.longshao.service;

import com.longshao.dto.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProService {
    public void login(String name, String password, Result result, HttpSession session);

    public void addUser(String name, String password,Result result);
}
