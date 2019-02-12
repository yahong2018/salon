/*
 * Copyright (C) 2010 SUNRico Inc.
 *  ------------------------------------------------------------------------------
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *       http://www.streets.cn
 *
 *  ----------------------------------------------------------------------------------
 */

package com.zhxh.core.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * BeanProvider implementation for accessing properties.
 * <p>
 * Can handle a.b.c.d -> getA().getB().getC().getD().
 * Access properties in this order: bean.getA(), bean.isA(), bean.a(), bean.a.
 * <p>
 * Can also deal with setter methods.
 *
 * @author <a href="mailto:joe@truemesh.com">Joe Walnes</a>
 * @version $Revision: 1.1.1.1 $
 */
public class BeanAccessor {

    private static final String GET = "get";
    private static final String IS = "is";

    public boolean set(Object object, String property, Object value) {
        if ((property == null) || (object == null)) {
            return false;
        }

        // Split out property on dots ( "person.name.first" -> "person","name","first" -> getPerson().getName().getFirst() )
        String[] props = property.split("\\.");

        if (props.length == 0) return false;

        // Holder for Object at current depth along chain.
        Object current = object;

        // Loop through properties in chain.
        for (int i = 0; i < props.length - 1; i++) {
            String propName = props[i];
            current = invokeProperty(current, propName);
            if (current == null) return false;
        }
        // Final property in chain, hence setter
        try {
            // Call setter
            //Class<?> cls = current.getClass();
            PropertyDescriptor pd = new PropertyDescriptor(props[props.length - 1], current.getClass());
            pd.getWriteMethod().invoke(current, new Object[]{value});
            return true;
        } catch (Exception e) {

            try {
                Field field = current.getClass().getField(property);
                field.setAccessible(true);
                field.set(current, value);
                return true;
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public Object get(Object object, String property) {
        if ((property == null) || (object == null)) {
            return null;
        }

        // Split out property on dots ( "person.name.first" -> "person","name","first" -> getPerson().getName().getFirst() )
        String[] props = property.split("\\.");

        if (props.length == 0) {
            return null;
        }

        // Holder for Object at current depth along chain.
        Object result = object;

        // Loop through properties in chain.
        for (String prop : props) {
            // Assign to holder the next property in the chain.
            result = invokeProperty(result, prop);
            if (result == null) return null;
        }
        // Return holder Object
        return result;
    }

    /**
     * Convert property name into getProperty name ( "something" -> "getSomething" )
     */
    private String generateMethodName(String prefix, String propertyName) {
        return prefix + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
    }

    /**
     * Invoke the method/field getter on the Object.
     * It tries (in order) obj.getProperty(), obj.isProperty(), obj.property(), obj.property.
     */
    private Object invokeProperty(Object obj, String property) {

        if ((property == null) || (property.length() == 0)) {
            return null; // just in case something silly happens.
        }
        Class<?> clzz = obj.getClass();
        Object[] objParams = {};
        Class<?>[] clsParams = {};
        String propName;
        try {
            propName = generateMethodName(GET, property);
            // First try object.getProperty()
            Method method = clzz.getMethod(propName, clsParams);
            return method.invoke(obj, objParams);
        } catch (Exception e1) {
            try {
                // First try object.isProperty()
                propName = generateMethodName(IS, property);
                Method method = clzz.getMethod(propName, clsParams);
                return method.invoke(obj, objParams);
            } catch (Exception e2) {
                try {
                    // Now try object.property()
                    Method method = clzz.getMethod(property, clsParams);
                    return method.invoke(obj, objParams);
                } catch (Exception e3) {
                    try {
                        // Now try object.property()
                        Field field = clzz.getField(property);
                        return field.get(obj);
                    } catch (Exception e4) {
                        // oh well
                        return null;
                    }
                }
            }
        }
    }
}
