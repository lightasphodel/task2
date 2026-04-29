package com.lena.task.parser;

import com.lena.task.composite.TextComponentType;
import com.lena.task.composite.TextComposite;

public class SentenceParser extends AbstractParser{
    private static final String LEXEME_DELIMITER = "\\s+";

    public SentenceParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(String sentence, TextComposite parentComposite) {
        String[] lexemes = sentence.split(LEXEME_DELIMITER);
        for (String lexeme : lexemes) {
            TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
            parentComposite.add(lexemeComposite);
            nextParser.parse(lexeme, lexemeComposite);
        }
    }
}
