package com.zhxh.core.utils;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 基本Bean功能类
 */
public class BeanUtils {

    private static final BeanAccessor accessor;

    static {
        accessor = new BeanAccessor();
    }

    /**
     * Get list of property names of bean.
     *
     * @param beanClazz Object class to query for property names.
     * @return Array of property names, or null if an error occurred.
     */
    public static final String[] getPropertyNames(Class<?> beanClazz) {
        try {
            BeanInfo info = Introspector.getBeanInfo(beanClazz);
            PropertyDescriptor[] properties = info.getPropertyDescriptors();
            String[] result = new String[properties.length];

            for (int i = 0; i < properties.length; i++) {
                result[i] = properties[i].getName();
            }

            return result;
        } catch (IntrospectionException e) {
            return null;
        }
    }

    /**
     * Copy one bean's properties value to annother bean
     * @param bean
     * @param toBean
     */
    public static void copy(Object from, Object dest) {
        if (from == null || dest == null) {
            return;
        }
        Class<?> fromClass = from.getClass();
        Class<?> destClass = dest.getClass();

        Field[] fromFields = ClassUtils.getPublicFields(fromClass);
        Field[] destFields = ClassUtils.getPublicFields(destClass);
        Set<String> fns = new HashSet<String>();

        for (Field field : fromFields) {
            if (Arrays.stream(destFields).filter(x -> x.getName().equalsIgnoreCase(field.getName())).count() == 0) {
                continue;
            }

            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                field.set(dest, field.get(from));
                fns.add(field.getName());
            } catch (Exception e) {
                Logger.error("EntityObject.copy出现错误:" + e.getMessage());
            }
        }

        String[] props = BeanUtils.getPropertyNames(fromClass);
        for (String prop : props) {
            if (!fns.contains(prop)) {
                Object value = BeanUtils.getValue(from, prop);
                BeanUtils.setValue(dest, prop, value);
            }
        }
        fns.clear();
    }

//    public static void copy(Object bean, Object toBean) {
//
//        if (bean.getClass() != toBean.getClass()) {
//            return;
//        }
//
//        Field[] fields = ClassUtils.getPublicFields(bean.getClass());
//
//        Set<String> fns = new HashSet<String>();
//
//        for (Field field : fields) {
//            if (!field.isAccessible()) {
//                field.setAccessible(true);
//            }
//            try {
//                field.set(toBean, field.get(bean));
//                fns.add(field.getName());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        String[] props = getPropertyNames(bean.getClass());
//        for (String prop : props) {
//            if (!fns.contains(prop)) {
//                Object value = getValue(bean, prop);
//                setValue(toBean, prop, value);
//            }
//        }
//
//        fns.clear();
//    }

    /**
     * 对一些特别的参数进行的trick动作
     * @param v
     * @return
     */
    private static Object equal_trick_trim(Object v) {

        if (v == null) {
            return v;
        }

        if (v instanceof String) {
            if (v.equals("null") || ((String) v).trim().length() == 0) {
                return null;
            }
        }

        if (v instanceof Boolean || boolean.class == v.getClass()) {
            if (!((Boolean) v).booleanValue()) {
                return null;
            }
        }

        return v;
    }

    /**
     * 必须是同一类型的Object比较
     * @param bean
     * @param bean2
     * @return
     */
    public static boolean equals(Object bean, Object bean2) {

        if (bean.getClass() != bean2.getClass()) {
            return false;
        }

        Field[] fields = ClassUtils.getPublicFields(bean.getClass());

        Set<String> fns = new HashSet<String>();

        for (Field field : fields) {
            fns.add(field.getName());
            try {
                Object v1 = equal_trick_trim(field.get(bean));
                Object v2 = equal_trick_trim(field.get(bean2));
                if (v1 != null) {
                    if (!v1.equals(v2)) {
                        return false;
                    }
                } else {
                    if (v2 != null) {
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        String[] props = getPropertyNames(bean.getClass());

        for (String prop : props) {
            if (!fns.contains(prop)) {
                Object v1 = equal_trick_trim(getValue(bean, prop));
                Object v2 = equal_trick_trim(getValue(bean2, prop));
                if (v1 != null) {
                    if (!v1.equals(v2)) {
                        return false;
                    }
                } else {
                    if (v2 != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Get a single property of the bean.
     *
     * @param obj The object to be accessed.
     * @param property Name of property to get.
     * @return Value of property. If property was not found, null is returned.
     */
    public static Object getValue(Object obj, String property) {
        return accessor.get(obj, property);
    }

    /**
     * Set a single property of the bean.
     *
     * @param obj The object to be manipulated.
     * @param property Name of property to set.
     * @param value Value to set property to.
     * @return Boolean indicating success.
     */
    public static boolean setValue(Object obj, String property, Object value) {
        return accessor.set(obj, property, value);
    }

    /**
     * Get Map of property values from a bean.
     *
     * @param obj Object to query for properties.
     * @param properties If array is NOT null, only the properties matching names
     *        passed here shall be retrieved.
     * @return Map containing property-name (String) / property-value (Object) pairs.
     */
    public static Map<String, Object> getValues(Object obj, String[] properties) {

        Map<String, Object> result = new HashMap<String, Object>();

        String[] propertyNames = getPropertyNames(obj.getClass());

        for (int i = 0; i < propertyNames.length; i++) {
            String propertyName = propertyNames[i];
            Object propertyValue = getValue(obj, propertyName);

            if ((propertyName == null) || (propertyValue == null)) {
                continue;
            }

            if (allowed(propertyName, properties)) {
                result.put(propertyName, propertyValue);
            }
        }

        return result;
    }

    /**
     * Set multiple properties of a bean at once using a Map. Any unknown properties
     * shall be ignored.
     *
     * @param obj The object to be manipulated.
     * @param values Map containing property-name (String) / property-value (Object)
     *        pairs to set in the object.
     * @param properyNames If array is NOT null, only the properties matching names
     *        passed here shall be set.
     */
    public static void setValues(Object obj, Map<String, Object> values, String[] properyNames) {
        for (Map.Entry<String, Object> e : values.entrySet()) {
            if (allowed(e.getKey(), properyNames)) {
                setValue(obj, e.getKey(), e.getValue());
            }
        }
    }

    /**
     * Set multiple properties of a bean at once using properties of another bean.
     * The beans may be of different types and any properties not common to both types
     * shall be ignored.
     *
     * @param obj The object to be manipulated.
     * @param src The object containing the properties to be copied.
     * @param properties If array is NOT null, only the properties matching names
     *        passed here shall be set.
     */
    public static void setValues(Object obj, Object src, String[] properties) {
        setValues(obj, getValues(src, properties), properties);
    }

    private static boolean allowed(String property, String[] properties) {
        if (properties == null) {
            return true;
        }

        if (property == null) {
            return false;
        }

        synchronized (properties) {
            for (int i = 0; i < properties.length; i++) {
                if (property.equals(properties[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Map<String, Object> getValues(Object obj) {
        String[] props = getPropertyNames(obj.getClass());
        return getValues(obj, props);
    }
}
