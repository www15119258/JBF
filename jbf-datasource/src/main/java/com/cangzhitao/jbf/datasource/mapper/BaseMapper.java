package com.cangzhitao.jbf.datasource.mapper;

import java.util.List;

/**
 * Created by lihui on 2017/5/6.
 */
public interface BaseMapper<ID, T> {

    public List<T> findAll();

    public List<T> find(T example);

    public T findById(ID id);

    public void insert(T entity);

    public void insertSelective(T entity);

    public void insertBatch(List<T> entities);

    public void insertSelectiveBatch(List<T> entities);

    public T update(T entity);

    public T updateSelective(T entity);

    public List<T> updateBatch(List<T> entities);

    public List<T> updateSelectiveBatch(List<T> entities);

    public T delete(T entity);

    public ID deleteById(ID id);

    public List<ID> deleteByIdBatch(List<ID> ids);

}
