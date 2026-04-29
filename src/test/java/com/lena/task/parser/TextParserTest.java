package com.lena.task.parser;

import java.util.List;

import com.lena.task.composite.TextComponent;
import com.lena.task.composite.TextComponentType;
import com.lena.task.composite.TextComposite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextParserTest {
    @Test
    void wordParserTest() {
        LetterParser letterParser = new LetterParser();
        WordParser wordParser = new WordParser(letterParser);
        TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
        String word = "World";
        wordParser.parse(word, wordComposite);
        List<TextComponent> wordComponents = wordComposite.getComponents();

        assertEquals(5, wordComponents.size());

        assertEquals(TextComponentType.LETTER, wordComponents.get(0).getComponentType());
        assertEquals("W", wordComponents.get(0).buildText());

        assertEquals(TextComponentType.LETTER, wordComponents.get(1).getComponentType());
        assertEquals("o", wordComponents.get(1).buildText());

        assertEquals(TextComponentType.LETTER, wordComponents.get(2).getComponentType());
        assertEquals("r", wordComponents.get(2).buildText());

        assertEquals(TextComponentType.LETTER, wordComponents.get(3).getComponentType());
        assertEquals("l", wordComponents.get(3).buildText());

        assertEquals(TextComponentType.LETTER, wordComponents.get(4).getComponentType());
        assertEquals("d", wordComponents.get(4).buildText());
    }

    @Test
    void lexemeParserTest() {
        SymbolParser symbolParser = new SymbolParser();
        LetterParser letterParser = new LetterParser();
        WordParser wordParser = new WordParser(letterParser);
        LexemeParser lexemeParser = new LexemeParser(wordParser, symbolParser);
        TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
        String lexeme = "('Hello-world').";
        lexemeParser.parse(lexeme, lexemeComposite);
        List<TextComponent> lexemeComponents = lexemeComposite.getComponents();

        assertEquals(8, lexemeComponents.size());

        assertEquals(TextComponentType.SYMBOL, lexemeComponents.get(0).getComponentType());
        assertEquals("(", lexemeComponents.get(0).buildText());

        assertEquals(TextComponentType.SYMBOL, lexemeComponents.get(1).getComponentType());
        assertEquals("'", lexemeComponents.get(1).buildText());

        assertEquals(TextComponentType.WORD, lexemeComponents.get(2).getComponentType());
        assertEquals("Hello", lexemeComponents.get(2).buildText());

        assertEquals(TextComponentType.SYMBOL, lexemeComponents.get(3).getComponentType());
        assertEquals("-", lexemeComponents.get(3).buildText());

        assertEquals(TextComponentType.WORD, lexemeComponents.get(4).getComponentType());
        assertEquals("world", lexemeComponents.get(4).buildText());

        assertEquals(TextComponentType.SYMBOL, lexemeComponents.get(5).getComponentType());
        assertEquals("'", lexemeComponents.get(5).buildText());

        assertEquals(TextComponentType.SYMBOL, lexemeComponents.get(6).getComponentType());
        assertEquals(")", lexemeComponents.get(6).buildText());

        assertEquals(TextComponentType.SYMBOL, lexemeComponents.get(7).getComponentType());
        assertEquals(".", lexemeComponents.get(7).buildText());
    }

    @Test
    void sentenceParserTest() {
        SymbolParser symbolParser = new SymbolParser();
        LetterParser letterParser = new LetterParser();
        WordParser wordParser = new WordParser(letterParser);
        LexemeParser lexemeParser = new LexemeParser(wordParser, symbolParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
        String sentence = "'Hello' world (abc).";
        sentenceParser.parse(sentence, sentenceComposite);
        List<TextComponent> sentenceComponents = sentenceComposite.getComponents();

        assertEquals(3, sentenceComponents.size());

        assertEquals(TextComponentType.LEXEME, sentenceComponents.get(0).getComponentType());
        assertEquals("'Hello'", sentenceComponents.get(0).buildText());

        assertEquals(TextComponentType.LEXEME, sentenceComponents.get(1).getComponentType());
        assertEquals("world", sentenceComponents.get(1).buildText());

        assertEquals(TextComponentType.LEXEME, sentenceComponents.get(2).getComponentType());
        assertEquals("(abc).", sentenceComponents.get(2).buildText());
    }

    @Test
    void paragraphParserTest() {
        SymbolParser symbolParser = new SymbolParser();
        LetterParser letterParser = new LetterParser();
        WordParser wordParser = new WordParser(letterParser);
        LexemeParser lexemeParser = new LexemeParser(wordParser, symbolParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
        String paragraph = "'Hello' world (abc). Java Innowise! 1234? test test";
        paragraphParser.parse(paragraph, paragraphComposite);
        List<TextComponent> paragraphComponents = paragraphComposite.getComponents();

        assertEquals(4, paragraphComponents.size());

        assertEquals(TextComponentType.SENTENCE, paragraphComponents.get(0).getComponentType());
        assertEquals("'Hello' world (abc).", paragraphComponents.get(0).buildText());

        assertEquals(TextComponentType.SENTENCE, paragraphComponents.get(1).getComponentType());
        assertEquals("Java Innowise!", paragraphComponents.get(1).buildText());

        assertEquals(TextComponentType.SENTENCE, paragraphComponents.get(2).getComponentType());
        assertEquals("1234?", paragraphComponents.get(2).buildText());

        assertEquals(TextComponentType.SENTENCE, paragraphComponents.get(3).getComponentType());
        assertEquals("test test", paragraphComponents.get(3).buildText());
    }
}
