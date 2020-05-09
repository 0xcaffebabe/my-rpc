package wang.ismy.rpc.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MY
 * @date 2020/5/9 9:17
 */
public class ReflectionUtils {
    public static <T> T newInstance(Class<T> klass){
        try {
            return klass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个类所有的public方法
     * @param klass
     * @return
     */
    public static Method[] getAllPublicMethod(Class<?> klass){
        Method[] methods = klass.getDeclaredMethods();
        List<Method> list = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())){
                list.add(method);
            }
        }
        return list.toArray(new Method[0]);
    }

    public static Object invoke(Object obj,Method method,Object...args){
        try {
            return method.invoke(obj,args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

}
