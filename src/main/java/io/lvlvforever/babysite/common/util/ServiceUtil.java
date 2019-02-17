package io.lvlvforever.babysite.common.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lvlvforever on 2019/2/16.
 */
public class ServiceUtil {
    /**
     * 获得source对象中的所有空值对象
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);

    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("\\-","");
    }
}
