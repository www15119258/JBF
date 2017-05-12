package com.cangzhitao.jbf.security.mapper;

import com.cangzhitao.jbf.datasource.annotation.DataSourceSelect;
import com.cangzhitao.jbf.datasource.annotation.MyBatisMapper;
import com.cangzhitao.jbf.datasource.mapper.BaseMapper;
import com.cangzhitao.jbf.security.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 2017/5/6.
 */
@MyBatisMapper
@DataSourceSelect(value = "default")
public interface RoleMapper extends BaseMapper {


    public List<User> findAll2(Map<String, Object> map);

}
