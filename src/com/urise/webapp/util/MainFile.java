package com.urise.webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "./.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("./src/com/urise/webapp");
        //File dir = new File("C:\\Users\\user\\Desktop\\basejava1\\src");
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
        doRecurs(dir, 0);
    }

    static String repeat(int n, String value) {
        return new String(new char[n]).replace("\0", value);
    }

    static void doRecurs(File dir, int level) {
        final String indent = repeat(level, "    ");

        if (dir.isDirectory()) {
            File[] list = dir.listFiles();
            if (list == null)
                return;

            for (File name : list) {
                if (name.isFile()) {
                    System.out.println(indent + "File - " + name.getName());
                } else {
                    System.out.println(indent + "Directory - " + name.getName());
                    doRecurs(name, level + 1);
                }
            }
        } else {
            System.out.println(dir.getName() + " не является папкой");
        }
    }
}