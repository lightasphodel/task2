package com.lena.task.parser;

import com.lena.task.composite.TextComponentType;
import com.lena.task.composite.TextComposite;

public class LexemeParser extends AbstractParser{
    private static final String WORD_DELIMITER = "(?<=\\w)(?=\\p{Punct})|(?<=\\p{Punct})(?=\\w)";
  private static final String WORD_REGEX = "[\\wа-яА-ЯёЁ]+";

    private final WordParser wordParser;
    private final SymbolParser symbolParser;

    public LexemeParser(WordParser wordParser, SymbolParser symbolParser) {
        this.wordParser = wordParser;
        this.symbolParser = symbolParser;
    }

    @Override
    public void parse(String lexeme, TextComposite parentComposite) {
        String[] parts = lexeme.split(WORD_DELIMITER);

        for (String part : parts) {
        if (part.isEmpty()) {
            continue;
        }
        if (part.matches(WORD_REGEX)) {
            TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
            parentComposite.add(wordComposite);
            wordParser.parse(part, wordComposite);
        } else {
            symbolParser.parse(part, parentComposite);
        }
        }
    }
}
