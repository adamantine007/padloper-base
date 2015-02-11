package com.henko.hasher.impl;

import com.henko.dao.impl.PersonDaoImpl;
import com.henko.hasher.Hasher;

import static com.henko.dao.impl.PersonDaoImpl.*;

/**
 * Hasher implementation that uses static import from PersonDaoImpl that allows
 * return right hash of Integer objects using BLOCK_COUNT static variable.
 *
 * @see PersonDaoImpl
 * @see Hasher
 *
 * @author Ruslan Kurchenko
 */

public class HasherImpl implements Hasher{

    @Override
    public int hash(Integer id) {
        return id.hashCode() % BLOCK_COUNT;
    }
}
