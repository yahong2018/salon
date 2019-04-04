package com.hy.salon.basic.common;




import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class ArraysUtil {

    public static  <T extends Collection> T requireNonEmpty(T array){
        return requireNonEmpty(array, null);
    }


    public static  <T extends Collection> T requireNonEmpty(T array, String msg){
        if (array.size() == 0) {
            throw new ServiceException(msg == null ? "array can't be null" : msg);
        }
        return array;
    }


    public static  <T extends Map> T requireNonEmpty(T map) {
        requireNonEmpty(map.keySet());
        return map;
    }

    public static  <T extends Map> T requireNonEmpty(T map, String msg){
        requireNonEmpty(map.keySet(), msg);
        return map;
    }


    public static byte[] channel(InputStream input) throws IOException {
        int i;
        byte[] elements = new byte[100];
        int idx = 0;
        while ((i= input.read()) != -1) {
            if (elements.length <= idx) {
                elements = grow(elements, idx);
            }
            elements[idx++] = (byte)i;
        }
        return Arrays.copyOf(elements, idx);
    }


    private static byte[] grow(byte[] array, int minLen){
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity -  minLen< 0)
            newCapacity = minLen;
        if (newCapacity - Integer.MAX_VALUE > -8) {
            if (minLen < 0)
                throw new OutOfMemoryError();
            newCapacity =  (minLen > Integer.MAX_VALUE - 8) ? Integer.MAX_VALUE : Integer.MAX_VALUE - 8;
        }
        return Arrays.copyOf(array, newCapacity);
    }


}
