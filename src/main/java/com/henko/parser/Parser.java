package com.henko.parser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

public interface Parser {

    Object getObject(File file, Type type) throws IOException;

    void saveObject(File file, Object o);

}
