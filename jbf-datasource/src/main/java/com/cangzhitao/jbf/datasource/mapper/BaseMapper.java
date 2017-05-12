package com.cangzhitao.jbf.datasource.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 2017/5/6.
 */
public interface BaseMapper<ID, T> {

    public List<T> findAll();

    public List<T> find(T example);

    public T findById(ID id);

    public void insert(T entity);

    public void insertBatch(List<T> entities);

    public void update(T entity);

    public void updateBatch(List<T> entities);

    public void delete(T entity);

    public void deleteById(ID id);

    public void deleteByIdBatch(List<ID> ids);

}
