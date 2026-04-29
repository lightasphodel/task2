package com.lena.task.parser;

import com.lena.task.composite.TextComponentType;
import com.lena.task.composite.TextComposite;

public class TextParser extends AbstractParser{
    private static final String PARAGRAPH_DELIMITER = "\n";

    public TextParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(String text, TextComposite parentComposite) {
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);
        for (String paragraph : paragraphs) {
            paragraph = paragraph.strip();
            TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
            parentComposite.add(paragraphComposite);
            nextParser.parse(paragraph, paragraphComposite);
        }
    }
}
