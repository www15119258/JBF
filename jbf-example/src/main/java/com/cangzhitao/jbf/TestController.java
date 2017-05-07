package com.cangzhitao.jbf;

import com.cangzhitao.jbf.core.util.SpringUtil;
import com.cangzhitao.jbf.security.domain.User;
import com.cangzhitao.jbf.security.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by lihui on 2017/5/6.
 */
@Controller
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/test")
    @ResponseBody
    String home() {
//        User user = new User();
//        user.setUsername("test2");
//        user.setPassword("123456");
//        userMapper.insertSelective(user);
//        System.out.println(user.getId());

        User entity = new User();
        entity.setUsername("admin");
        PageHelper.startPage(1,2);
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        System.out.println(userList);
        System.out.println(pageInfo);
        userList = userMapper.find(entity);
        System.out.println(userList);
        return "test!";
    }

}
