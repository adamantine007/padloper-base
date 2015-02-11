package com.henko.hasher.impl;

import com.henko.hasher.Hasher;

import static com.henko.dao.impl.PersonDaoImpl.*;

public class HasherImpl implements Hasher{

    @Override
    public int hash(Integer id) {
        return id.hashCode() % BLOCK_COUNT;
    }
}
