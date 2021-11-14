/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import java.util.ArrayList;

public abstract class EduSysDAO <E,K>{
    abstract public void insert (E entity);
    abstract public void update (E entity);
    abstract public void delete (K key);
    abstract public ArrayList<E> selectALL();
    abstract public E selectById (K key);
    abstract protected ArrayList<E> selectBySql(String sql,Object...args);
    
}
