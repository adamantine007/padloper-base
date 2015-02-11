package com.henko.dao.exception;

/**
 * Class that is the root of DAO exceptions.
 *
 * @author Ruslan Kurchenko
 */

public class DaoException extends Exception{
    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Throwable cause){
        super(message, cause);
    }
}
