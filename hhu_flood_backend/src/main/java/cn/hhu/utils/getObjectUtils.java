package cn.hhu.utils;

import cn.hhu.Bean.CA_PD;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author tlj
 */
public class getObjectUtils {
   public static <T>  T getObjectFromRequest(HttpServletRequest request, Class<T> clazz) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
       T t = clazz.newInstance();
       Field[] fields = clazz.getDeclaredFields();
       for(Field field :fields){

           String parameter = request.getParameter(field.getName());
           if(parameter != null){
               Constructor constructor = field.getType().getConstructor(String.class);
               Method method = clazz.getDeclaredMethod("set"+field.getName(),field.getType());
               method.invoke(t,constructor.newInstance(parameter));
           }

           //System.out.println(field.getName()+":"+request.getParameter(field.getName()));
       }

       return t;
   }



    @Test
   public void mytest() throws InstantiationException, IllegalAccessException {
       Class clazz =CA_PD.class;
       Object o = clazz.newInstance();
       Method[] method = clazz.getDeclaredMethods();
       System.out.println(Arrays.toString(method));
   }
}
