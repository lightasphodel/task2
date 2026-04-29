package com.lena.task.parser;

import com.lena.task.composite.TextComposite;

public class WordParser extends AbstractParser{
    public WordParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(String word, TextComposite parentComposite) {
        char[] letters = word.toCharArray();
        for (char letter : letters) {
            nextParser.parse(String.valueOf(letter), parentComposite);
        }
    }
}
