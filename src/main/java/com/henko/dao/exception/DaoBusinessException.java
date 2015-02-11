package com.henko.dao.exception;

/**
 * Exception that was created for trows when server have some problems with database.
 *
 * @see DaoException
 * @author Ruslan Kurchenko
 */

public class DaoBusinessException extends DaoException{

    public DaoBusinessException(String message) {
        super(message);
    }

    public DaoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
