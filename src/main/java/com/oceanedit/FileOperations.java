package com.oceanedit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations {
    public static String ReadFile(File file) throws IOException {
        FileReader reader = new FileReader(file);
        StringBuilder builder = new StringBuilder();
        int data;
        while ((data = reader.read()) != -1) {
            builder.append((char) data);
        }

        return builder.toString();
    }
}
