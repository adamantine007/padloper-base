package com.henko.parser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * An interface that gives contract for users to
 * convert Json files to Java objects and backward.
 *
 * @author Ruslan Kurchenko
 */

public interface Parser {

    String toJson(Object obj);

    Object getObject(File file, Type type) throws IOException;

    void saveObject(File file, Object o);

}
