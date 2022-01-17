package dev.brokenstudio.polarlobby.cosmetics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class CosmeticHandler {

    public class Loader {

        public void loadCosmetics(ArrayList<AbstractCosmetic> cosmetics){
            Set<Class<?>> cosmeticClasses = findAllClassesUsingClassLoader("dev.brokenstudio.polarlobby.cosmetics.implementation");
            cosmeticClasses.forEach(cr -> {
                try {
                    cosmetics.add((AbstractCosmetic) cr.getDeclaredConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });
        }

        private Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
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
                return Class.forName(packageName + "."
                        + className.substring(0, className.lastIndexOf('.')));
            } catch (ClassNotFoundException e) {
                // handle the exception
            }
            return null;
        }

    }

    private Loader loader;

    public CosmeticHandler(){
        this.loader = new Loader();
    }

}
