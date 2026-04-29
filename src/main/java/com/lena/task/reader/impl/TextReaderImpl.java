package com.lena.task.reader.impl;

import com.lena.task.reader.TextReader;
import com.lena.task.exception.TextException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TextReaderImpl implements TextReader{
    private static final String FILE_PATH = "data/text.txt";

    @Override
    public String readText() throws TextException {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) {
            throw new TextException("File does not exists");
        }
        try {
            List<String> strings = Files.readAllLines(path);
            String result = String.join("\n",strings);
            return result;
        } catch (IOException exception) {
            throw new TextException("Failed to read file");
        }
    }
}
