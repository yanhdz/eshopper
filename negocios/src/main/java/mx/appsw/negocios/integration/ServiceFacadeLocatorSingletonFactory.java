/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.appsw.negocios.integration;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kitto
 */
public class ServiceFacadeLocatorSingletonFactory {
    private static final ServiceFacadeLocatorSingletonFactory instance = new ServiceFacadeLocatorSingletonFactory();

    @SuppressWarnings("rawtypes")
    private Map<Class, Object> mapHolder = new HashMap<>();

    private ServiceFacadeLocatorSingletonFactory() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> classOf) {
        synchronized (instance) {
            if (!instance.mapHolder.containsKey(classOf)) {
                T obj = null;
                try {
                    obj = classOf.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                instance.mapHolder.put(classOf, obj);
            }
        }
        return (T) instance.mapHolder.get(classOf);
    }
  
}
