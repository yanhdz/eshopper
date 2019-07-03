/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.appsw.persistencia.transaction;

/**
 *
 * @author kitto
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.List;
import mx.appsw.persistencia.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;

/**
 * Generic abstract class avoid to extends another DAO for make generic methods
 * with the other DAO
 *
 * @param <PK>
 * @param <T>
 */
public abstract class AbstractDAO<PK extends Serializable, T> implements InterfaceDAO<T> {

    private Class<T> entityClass;

    public AbstractDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public void saveOrUpdate(T obj) throws Exception{
        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            HibernateUtil.getCurrentSession().saveOrUpdate(obj);

        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
                HibernateUtil.closeSession();
        }
    }

    @Override
    public void save(T obj) {
        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            HibernateUtil.getCurrentSession().save(obj);

        } catch (HibernateException e) {
            System.out.println("Error" + entityClass.getName() + ": " + e);
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public void delete(T obj) {
        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            HibernateUtil.getCurrentSession().delete(obj);

        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.commitTransaction();
            HibernateUtil.closeSession();
        }
    }

    @Override
    public T find(Integer id) {
        Object obj = null;
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(entityClass);
        String identificador = catMeta.getIdentifierPropertyName();

        String nombre = entityClass.getSimpleName();
        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            obj = HibernateUtil.getCurrentSession().createQuery("from " + nombre
                    + " as " + nombre.toLowerCase() + " where " + nombre.toLowerCase() + "." + identificador + " = :id").setString("id", String.valueOf(id)).uniqueResult();
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {

            HibernateUtil.closeSession();
        }
        return (T) obj;

    }

    public T saveWT(T t) {

        Serializable ser;
        T t1 = null;
        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            ser = HibernateUtil.getCurrentSession().save(t);
            t1 = find((Integer) ser);
            System.out.println("AGREGADO");
            HibernateUtil.commitTransaction();
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return t1;
    }

    public Serializable saveWR(T t) {
        Serializable ser = null;
        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            ser = HibernateUtil.getCurrentSession().save(t);
            System.out.println("AGREGADO");
            HibernateUtil.commitTransaction();
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            //HibernateUtil.commitTransaction();
            HibernateUtil.closeSession();
        }

        return ser;
    }


    @Override
    public List<T> findAll() {
        List<T> objects = null;
        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            Query query = HibernateUtil.getCurrentSession().createQuery("from " + entityClass.getName());
            objects = query.list();

        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return objects;
    }

    @Override
    public List<T> executeQuery(String query) {
        List<T> result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = HibernateUtil.getCurrentSession().createSQLQuery(query).addEntity(entityClass.getCanonicalName()).list();
        } catch (HibernateException e) {
            System.out.println(e);
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public List<Object[]> executeQueryObjects(String query) {
        List<Object[]> result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = HibernateUtil.getCurrentSession().createSQLQuery(query).list();
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public void executeUpdate(String query) {
        // ClassMetadata catMeta = HibernateUtil.getCurrentSessionFactory().getClassMetadata(getTipo());
        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            HibernateUtil.getCurrentSession().createSQLQuery(query).executeUpdate();
        } catch (HibernateException x) {
            System.out.println("" + x);
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public T executeQueryUnique(String query) {
        T result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = (T) HibernateUtil.getCurrentSession().createSQLQuery(query).addEntity(entityClass.getCanonicalName()).uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.toString());
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public T executeQueryNoEntityUnique(String query) {
        T result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = (T) HibernateUtil.getCurrentSession().createSQLQuery(query).uniqueResult();
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public List<T> executeQueryNoEntity(String query) {
        List<T> result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = HibernateUtil.getCurrentSession().createSQLQuery(query).list();
        } catch (HibernateException x) {

            System.out.println("" + x);
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public List<Object[]> executeNoEntity(String query) {

        List<Object[]> result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = HibernateUtil.getCurrentSession().createSQLQuery(query).list();
        } catch (HibernateException x) {
            System.out.println("" + x);
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public T findByOneParameterUnique(String value, String identificador) {
        T result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            String nombre = entityClass.getSimpleName();
            result = (T) HibernateUtil.getCurrentSession().createQuery("from " + nombre
                    + " as " + nombre.toLowerCase() + " where " + nombre.toLowerCase() + "." + identificador + " = :id").setString("id", String.valueOf(value)).uniqueResult();

        } catch (HibernateException x) {
            System.out.println("" + x);
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public List<T> findByOneParameter(String value, String identificador) {
        List<T> result = null;
        String nombre = entityClass.getSimpleName();

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = HibernateUtil.getCurrentSession().createQuery("from " + nombre
                    + " as " + nombre.toLowerCase() + " where " + nombre.toLowerCase() + "." + identificador + " = :id").setString("id", String.valueOf(value)).list();
            System.err.println("Listo");
        } catch (HibernateException x) {
            System.out.println("" + x);
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public List<T> findByOneNotParameter(String value, String identificador) {
        List<T> result = null;
        String nombre = entityClass.getSimpleName();

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = HibernateUtil.getCurrentSession().createQuery("from " + nombre
                    + " as " + nombre.toLowerCase() + " where " + nombre.toLowerCase() + "." + identificador + " <> :id").setString("id", String.valueOf(value)).list();
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public List<T> findByOneParameterOrder(String value, String identificador, Boolean asc) {
        List<T> result = null;
        String nombre = entityClass.getSimpleName();

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            ClassMetadata catMeta = HibernateUtil.getCurrentSession().getSessionFactory().getClassMetadata(entityClass);
            if (asc) {
                result = HibernateUtil.getCurrentSession().createQuery("from " + nombre
                        + " as " + nombre.toLowerCase() + " where " + nombre.toLowerCase() + "." + identificador + " = :id order by " + catMeta.getIdentifierPropertyName() + " asc").setString("id", String.valueOf(value)).list();
            } else {
                result = HibernateUtil.getCurrentSession().createQuery("from " + nombre
                        + " as " + nombre.toLowerCase() + " where " + nombre.toLowerCase() + "." + identificador + " = :id order by " + catMeta.getIdentifierPropertyName() + " desc").setString("id", String.valueOf(value)).list();

            }
            System.err.println("Listo");
        } catch (HibernateException x) {
            System.out.println("" + x);
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    //Procedures
    @Override
    public List<T> executeProcedure(String procedure, String[] names, String... param) {
        List<T> result;
        HibernateUtil.getCurrentSession();
        HibernateUtil.beginTransaction();
        SQLQuery lQuery = HibernateUtil.getCurrentSession().createSQLQuery(procedure).addEntity(entityClass);
        for (int i = 0; i < param.length; i++) {
            lQuery.setParameter(names[i], param[i]);
        }
        result = lQuery.list();
        return result;
    }

    @Override
    public T executeProcedureOne(String procedure, String[] names, String... param) {
        T result;
        HibernateUtil.getCurrentSession();
        HibernateUtil.beginTransaction();
        SQLQuery lQuery = HibernateUtil.getCurrentSession().createSQLQuery(procedure).addEntity(entityClass);

        for (int i = 0; i < param.length; i++) {
            lQuery.setParameter(names[i], param[i]);
        }
        result = (T) lQuery.uniqueResult();
        return result;
    }

    @Override
    public int executeProcedureInt(String procedure, String[] names, String... param) {
        BigInteger result = null;
        HibernateUtil.getCurrentSession();
        HibernateUtil.beginTransaction();
        SQLQuery lQuery = HibernateUtil.getCurrentSession().createSQLQuery(procedure);
        for (int i = 0; i < param.length; i++) {
            lQuery.setParameter(names[i], param[i]);
        }
        result = (BigInteger) lQuery.uniqueResult();
        return result.intValue();
    }

    @Override
    public int executeCountQuery(String query) {
        BigInteger result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            result = (BigInteger) HibernateUtil.getCurrentSession().createSQLQuery(query).uniqueResult();
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result.intValue();
    }

    @Override
    public int executeCountQueryDouble(String query) {
        int result = 0;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            Double aux = (Double) HibernateUtil.getCurrentSession().createSQLQuery(query).uniqueResult();
            if (aux != null) {
                result = aux.intValue();
            }
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public int executeCountQueryFloat(String query) {
        int result = 0;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            Float aux = (Float) HibernateUtil.getCurrentSession().createSQLQuery(query).uniqueResult();
            if (aux != null) {
                result = aux.intValue();
            }
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public T executeTransformationUniqueQuery(String query, String... param) {
        T result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();
            SQLQuery lQuery = HibernateUtil.getCurrentSession().createSQLQuery(query);

            for (int i = 0; i < param.length; i++) {
                lQuery.addScalar(param[i]);
            }

            result = (T) lQuery.setResultTransformer(Transformers.aliasToBean(entityClass)).uniqueResult();

        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    @Override
    public List<T> executeTransformationQuery(String query, Class<T> entity, String... param) {
        List<T> result = null;

        try {
            HibernateUtil.getCurrentSession();
            HibernateUtil.beginTransaction();

            SQLQuery lQuery = HibernateUtil.getCurrentSession().createSQLQuery(query);

            for (int i = 0; i < param.length; i++) {
                lQuery.addScalar(param[i]);
            }

            result = lQuery.setResultTransformer(Transformers.aliasToBean(entity)).list();

        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

}
