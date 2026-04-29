package com.lena.task.composite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lena.task.parser.*;

class TextCompositeTest {
    TextComposite textComposite = new TextComposite(TextComponentType.TEXT);

    @Test
    void countLettersAndSymbolsTest() {
        SymbolParser symbolParser = new SymbolParser();
        LetterParser letterParser = new LetterParser();
        WordParser wordParser = new WordParser(letterParser);
        LexemeParser lexemeParser = new LexemeParser(wordParser, symbolParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
        String paragraph = "Hi! I'm 19 years old. What's about you?\n    20?";
        paragraphParser.parse(paragraph, paragraphComposite);
        int countLetters = paragraphComposite.countLetters();
        int countSymbols = paragraphComposite.countSymbolsWithoutSpaces();

        assertEquals(29, countLetters);
        assertEquals(35, countSymbols);
    }

}
