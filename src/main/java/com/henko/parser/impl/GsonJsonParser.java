package com.henko.parser.impl;

import com.google.gson.Gson;
import com.henko.parser.Parser;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Scanner;

public class GsonJsonParser implements Parser {
    private static final String PATH_DATA = "./data/data.json";

    @Override
    public Object getObject(File file, Type type) throws IOException {
        Object object = null;
        String jsonString = readFile(file);
        Gson gson = new Gson();
        object = gson.fromJson(jsonString, type);

        return object;
    }

    @Override
    public void saveObject(File file, Object o) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(o);
        try {
            FileWriter writer = new FileWriter(file);
            System.err.println("JSON_STRING: " + jsonString);
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFileAsString(File file) throws IOException {
        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    private String readFile(File file) throws IOException {
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine());
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
}
