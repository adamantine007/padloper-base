package com.henko.dao;

import com.henko.dao.exception.NoSuchEntityException;
import com.henko.entity.Person;

/**
 * An interface for Person DAO layer
 *
 * @see Person
 * @see NoSuchEntityException
 *
 * @author Ruslan Kurchenko
 */

public interface PersonDao {

    public Person removeById(int id) throws NoSuchEntityException;

    public Person getById(int id) throws NoSuchEntityException;

    public void addById(Person person);

    public void commitData();
}
