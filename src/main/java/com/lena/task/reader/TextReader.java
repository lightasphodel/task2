package com.lena.task.reader;

import com.lena.task.exception.*;

public interface TextReader {
    String readText() throws TextException;
}
