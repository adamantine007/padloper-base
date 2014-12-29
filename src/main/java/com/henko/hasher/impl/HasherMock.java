package com.henko.hasher.impl;

import com.henko.hasher.Hasher;

public class HasherMock implements Hasher{

    private static final int BLOCKS_COUNT = 3;

    @Override
    public int hash(Integer id) {
        return id.hashCode() % BLOCKS_COUNT;
    }
}
