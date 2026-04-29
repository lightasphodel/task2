package com.lena.task.parser;

import com.lena.task.composite.TextComposite;

public abstract class AbstractParser {
    protected AbstractParser nextParser;

    public abstract void parse(String text, TextComposite parentComposite);
}
