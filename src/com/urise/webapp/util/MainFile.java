package com.urise.webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\user\\Desktop\\basejava\\basejava\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File baseDirectory = new File("C:\\Users\\user\\Desktop\\basejava\\basejava");
        recurs(baseDirectory);
    }

    private static void recurs(File baseDirectory) {
        if (baseDirectory.isDirectory()) {
            File[] list = baseDirectory.listFiles();
            if (list != null) {
                for (File name : list) {
                    if (name.isFile()) {
                        System.out.println(name.getName() + " - файл");
                    } else {
                        recurs(name);
                    }
                }
            }
        } else System.out.println(baseDirectory.getName() + " не является папкой");
    }
}
