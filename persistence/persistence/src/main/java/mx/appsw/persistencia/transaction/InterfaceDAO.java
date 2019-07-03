/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.appsw.persistencia.transaction;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author kitto
 */
public interface InterfaceDAO<T> {

    public void save(T t);

    public void delete(T t);

    public T find(Integer id);

    public List<T> findAll();

    public void saveOrUpdate(T instance) throws Exception;

    public Serializable saveWR(T t);

    public List<T> executeQuery(String query);

    public List<Object[]> executeQueryObjects(String query);

    public T executeQueryUnique(String query);

    public void executeUpdate(String query);

    public List<Object[]> executeNoEntity(String query);

    public T executeQueryNoEntityUnique(String query);

    public List<T> executeQueryNoEntity(String query);

    public T findByOneParameterUnique(String value, String identificador);

    public List<T> findByOneParameter(String value, String identificador);

    public List<T> findByOneNotParameter(String value, String identificador);

    public List<T> findByOneParameterOrder(String value, String identificador, Boolean asc);

    public List<T> executeProcedure(String procedure, String[] names, String... param);

    public T executeProcedureOne(String procedure, String[] names, String... param);

    public int executeProcedureInt(String procedure, String[] names, String... param);

    public int executeCountQuery(String query);

    public int executeCountQueryDouble(String query);

    public int executeCountQueryFloat(String query);

    public T executeTransformationUniqueQuery(String query, String... param);

    public List<T> executeTransformationQuery(String query, Class<T> entityClass, String... param);
}
