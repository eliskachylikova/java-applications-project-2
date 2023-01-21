package cz.mendelu.ja.project2;

import java.util.Arrays;

public class IOC {

    public static <A> A create(Class<A> appClass) {
        try {
            var instance = appClass.newInstance();
            var fields = appClass.getDeclaredFields();
            for (var field: fields) {
                System.out.println(field.getName());
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
