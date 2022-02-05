package com.urise.webapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.urise.webapp.model.Section;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;

public class JsonParser {
    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }

    public static <T> T read(String value, Class<T> sectionClass) {
        return GSON.fromJson(value, sectionClass);
    }

    public static <T> String write(T value, Class<T> sectionClass) {
        return GSON.toJson(value, sectionClass);
    }

    public static <T> String write(T object) {
        return GSON.toJson(object);
    }
}
