package com.henko.dao;

import com.henko.dao.exception.NoSuchEntityException;
import com.henko.entity.Person;

public interface PersonDao {

    public Person removeById(int id) throws NoSuchEntityException;

    public Person getById(int id) throws NoSuchEntityException;

    public void addById(Person person);

    public void commitData();
}
