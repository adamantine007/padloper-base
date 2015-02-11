package com.henko.dao.exception;

/**
 * Exception that was created for trows when database do not have requested entity.
 *
 * @see DaoBusinessException
 * @author Ruslan Kurchenko
 */

public class NoSuchEntityException extends DaoBusinessException{

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
