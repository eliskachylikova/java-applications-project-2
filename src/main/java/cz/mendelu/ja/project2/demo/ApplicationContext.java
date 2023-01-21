package cz.mendelu.ja.project2.demo;

import cz.mendelu.ja.project2.annotations.Autowired;
import cz.mendelu.ja.project2.annotations.Component;
import cz.mendelu.ja.project2.annotations.ComponentScan;
import cz.mendelu.ja.project2.annotations.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContext {

    Map<Class<?>, Object> objectRegistryMap = new HashMap<>();
    public ApplicationContext(Class<?> c) {
        initializeContext(c);
    }

    public <T> T getInstance(Class<T> c) throws Exception {
        T object = (T) objectRegistryMap.get(c);

        Field[] declaredFields = c.getDeclaredFields();
        injectAnnotatedFields(object, declaredFields);

        return object;
    }

    /**
     * Instantiate and inject fields in the class that are annotated with Autowired annotation.
     */
    private <T> void injectAnnotatedFields(T object, Field[] declaredFields) throws Exception {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObject = objectRegistryMap.get(type);

                field.set(object, innerObject);
                injectAnnotatedFields(innerObject, type.getDeclaredFields());
            }
        }
    }


    private void initializeContext(Class<?> c) {
        if(!c.isAnnotationPresent(Configuration.class)) // given class does not have @Configuration annotation
            throw new RuntimeException("Provide a valid configuration file.");
        else {
            ComponentScan componentScan = c.getAnnotation(ComponentScan.class);
            String packageValue = componentScan.value();
            Set<Class<?>> classes  = findClasses(packageValue);

            for (Class<?> loadingClass : classes) {
                try {
                    if (loadingClass.isAnnotationPresent(Component.class)) {
                        Constructor<?> constructor = loadingClass.getDeclaredConstructor();
                        Object newInstance = constructor.newInstance();
                        objectRegistryMap.put(loadingClass, newInstance);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private Set<Class<?>> findClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
