package utils;




import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

public class BeanUtils {

    public static Object copyBean(Object target, Object source) throws Exception {
        String[] parsePatterns = {"yyyy-MM-dd HH:mm:ss"};
        Map<String, Object> test1Map = convertToMap(source);
        Set<String> keySet = test1Map.keySet();
        Field[] test1Files = target.getClass().getDeclaredFields();

        for (String fiedName : keySet) {
            for (Field filed1 : test1Files) {
                if (fiedName.equalsIgnoreCase(filed1.getName())) {
                    filed1.setAccessible(true);
//                    if (test1Map.get(fiedName) instanceof Date) {
//                        filed1.set(target, DateUtils.((Date) test1Map.get(fiedName), parsePatterns));
//                    } else {
//                        filed1.set(target, test1Map.get(fiedName));
//                    }
                    filed1.set(target, test1Map.get(fiedName));
                }
            }
        }
        return target;
    }

    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        List<Method> methods = getAllMethods(obj);
//        obj.getClass().getDeclaredFields();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                String propertyName = methodName.substring(3);
                try {
                    map.put(propertyName, method.invoke(obj));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    private static List<Method> getAllMethods(Object obj) {
        List<Method> methods = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        while (!clazz.getName().equalsIgnoreCase("java.lang.Object")) {
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
            clazz = clazz.getSuperclass();
        }
        return methods;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("xx");
        user.setBirthday(null);
        user.setMoney(new BigDecimal(100));

        try {
            User u = (User) BeanUtils.copyBean(new User(), user);
            System.out.println(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
