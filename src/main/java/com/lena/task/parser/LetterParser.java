package com.lena.task.parser;

import com.lena.task.composite.TextComponentType;
import com.lena.task.composite.TextComposite;
import com.lena.task.composite.TextLeaf;

public class LetterParser extends AbstractParser{
     @Override
    public void parse(String letter, TextComposite parentComposite) {
        TextLeaf letterLeaf= new TextLeaf(letter.charAt(0), TextComponentType.LETTER);
        parentComposite.add(letterLeaf);
    }
}
