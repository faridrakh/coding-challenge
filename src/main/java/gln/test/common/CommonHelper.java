package gln.test.common;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class CommonHelper<T> {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public static String dateToStr(Date dt){
        if(null != dt){
            return df.format(dt);
        }
        return null;
    }

    public T objectMapper(Object objectClass, Map<Object, Object> fields){
        Class cls = objectClass.getClass();
        fields.forEach((k,v)-> {
            Field field = null;
            try {
                field = cls.getDeclaredField(String.valueOf(k));
                String fieldName = field.getName();
                String methodName = "set" + Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
                Method method = cls.getDeclaredMethod(methodName,field.getType());
                method.invoke(objectClass,v);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

                }
        );
        return (T) objectClass;
    }

}
