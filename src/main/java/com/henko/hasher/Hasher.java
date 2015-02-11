package com.henko.hasher;

/**
 * An interface that gives contract for users to return hash of integer object.
 *
 * @author Ruslan Kurchenko
 */

public interface Hasher {
    public int hash(Integer o);
}
