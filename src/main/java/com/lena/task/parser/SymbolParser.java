package com.lena.task.parser;

import com.lena.task.composite.TextComponentType;
import com.lena.task.composite.TextComposite;
import com.lena.task.composite.TextLeaf;

public class SymbolParser extends AbstractParser{
    @Override
    public void parse(String symbols, TextComposite parentComposite) {
        for (char c : symbols.toCharArray()) {
            TextLeaf symbolLeaf = new TextLeaf(c, TextComponentType.SYMBOL);
            parentComposite.add(symbolLeaf);
        }
    }
}
