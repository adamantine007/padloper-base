package com.henko.parser.impl;

import com.google.gson.Gson;
import com.henko.parser.Parser;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Scanner;

/**
 * An implementation of Parser which uses Gson
 * library for convert JSON strings to Java objects and backward.
 * Also GsonParser can save data to JSON file.
 *
 * @see Gson
 * @author Ruslan Kurchenko
 */

public class GsonParser implements Parser {
    private Gson gson;

    /**
     * Construct object and initialize Gson reference
     */
    public GsonParser(){
        this.gson = new Gson();
    }

    /**
     * Convert Java object to JSON string and returns it
     *
     * @param obj Java object that will convert to JSON string
     * @return JSON string of Java object
     */
    public String toJson(Object obj){
        return gson.toJson(obj);
    }

    /**
     * Convert JSON sting from file using Type of Java object
     *
     * @param file from that reads JSON string
     * @param type of object
     * @return Java object that was created from JSON string
     * @throws IOException
     */
    @Override
    public Object getObject(File file, Type type) throws IOException {
        String jsonString = readFile(file);

        return gson.fromJson(jsonString, type);
    }

    /**
     * Save object to JSON file using FileWriter
     *
     * @param file in which will save JSON string
     * @param o object that will save
     */
    @Override
    public void saveObject(File file, Object o) {
        String jsonString = gson.toJson(o);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads string from file and returns it.
     *
     * @param file from which will read string
     * @return string
     * @throws IOException
     */
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
