package com.cangzhitao.jbf;

import com.cangzhitao.jbf.datasource.util.DomainUtil;
import com.cangzhitao.jbf.security.domain.Role;
import com.cangzhitao.jbf.security.domain.User;
import com.cangzhitao.jbf.security.mapper.RoleMapper;
import com.cangzhitao.jbf.security.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 2017/5/6.
 */
@Controller
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @RequestMapping("/test")
    @ResponseBody
    String home() {
//        User user = new User();
//        user.setUsername("test2");
//        user.setPassword("123456");
//        userMapper.insertSelective(user);
//        System.out.println(user.getId());

//        User entity = new User();
//        entity.setUsername("admin");
//        PageHelper.startPage(1,2);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("tableName", "jbf_user");
//        List<User> userList = userMapper.findAll2(DomainUtil.getMateData(User.class));
//        System.out.println(userList);

//        userList = userMapper.findAll();
//        System.out.println(userList);
//        PageInfo<User> pageInfo = new PageInfo<User>(userList);
//        System.out.println(userList);
//        System.out.println(pageInfo);
//        userList = userMapper.find(entity);
//        System.out.println(userList);
//
//        userMapper.find2("a1", "b1");

//        userList = userMapper.find(DomainUtil.getMateData(User.class), entity);
//        roleMapper.findAll2(DomainUtil.getMateData(Role.class));

//        List<User> userList = new ArrayList<User>();
//        User entity = userMapper.findById(22l);
//        entity.setUsername("t111");
//        userList.add(entity);
//
//        entity = userMapper.findById(23l);
//        entity.setUsername("t222");
//        userList.add(entity);
//
//        userMapper.updateBatch(userList);

        List<Long> idList = new ArrayList<Long>();
        idList.add(22l);
        idList.add(23l);
        userMapper.deleteByIdBatch(idList);
        return "test!";


    }

}
