package com.henko.dao.impl;

import com.google.gson.reflect.TypeToken;
import com.henko.dao.PersonDao;
import com.henko.dao.exception.NoSuchEntityException;
import com.henko.entity.Person;
import com.henko.hasher.Hasher;
import com.henko.hasher.impl.HasherImpl;
import com.henko.parser.Parser;
import com.henko.parser.impl.GsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PersonDaoImpl implements PersonDao {
    private static final String PATH_DATA = "data.json";
    public static final int BLOCK_COUNT = 3;

    private List<Map<Integer, Person>> memory;

    private static final Hasher hasher = new HasherImpl();
    private static final Parser parser = new GsonParser();

    private static PersonDaoImpl instance;

    private PersonDaoImpl() {
        Type type = new TypeToken<List<Map<Integer, Person>>>(){}.getType();

        try {
            memory = (List<Map<Integer, Person>>) parser.getObject(new File(PATH_DATA), type);
        } catch (FileNotFoundException e) {
            initMemory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addById(Person person) {
        int blockNumber = hasher.hash(person.getId());
        memory.get(blockNumber).put(person.getId(), person);
    }

    @Override
    public Person getById(int id) throws NoSuchEntityException {
        int blockNumber = hasher.hash(id);
        Person person = memory.get(blockNumber).get(id);

        if (person == null){
            throw new NoSuchEntityException("No such person in DB.");
        }

        return  person;
    }

    @Override
    public Person removeById(int id) throws NoSuchEntityException {
        int blockNumber = hasher.hash(id);
        Person person = memory.get(blockNumber).remove(id);

        if (person == null){
            throw new NoSuchEntityException("No such person in DB.");
        }

        return person;
    }

    @Override
    public void commitData() {
        parser.saveObject(new File(PATH_DATA), memory);
    }

    private void initMemory(){
        memory = new ArrayList<>();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            memory.add(new ConcurrentHashMap<Integer, Person>());
        }
    }

    public static PersonDaoImpl getInstance() {
        if (instance == null){
            synchronized (PersonDaoImpl.class) {
                if (instance == null)
                    instance = new PersonDaoImpl();
            }
        }
        return instance;
    }
}
