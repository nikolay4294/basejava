package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true); //отмена проверка доступа
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new uuid");

        Method[] toString = r.getClass().getMethods();
        System.out.println(Arrays.toString(toString));
    }
}
