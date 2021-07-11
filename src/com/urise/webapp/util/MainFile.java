package com.urise.webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

        doRecurs(dir);
    }

    private static void doRecurs(File dir) {
        if (dir.isDirectory()) {
            File[] list = dir.listFiles();
            if (list != null) {
                for (File name : list) {
                    if (name.isFile()) {
                        System.out.println("\tFile - " + name.getName());
                    } else {
                        System.out.println("Directory - " + name.getName());
                        doRecurs(name);
                    }
                }
            }
        } else System.out.println(dir.getName() + " не является папкой");
    }
}
