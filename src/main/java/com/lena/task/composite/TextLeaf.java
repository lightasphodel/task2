package com.lena.task.composite;

public class TextLeaf extends TextComponent{
    private Character symbol;

    public TextLeaf(Character symbol, TextComponentType componentType) {
        this.symbol = symbol;
        this.componentType = componentType;
    }

    @Override
    public int countLetters() {
        return componentType == TextComponentType.LETTER ? 1 : 0;
    }

    @Override
    public int countSymbolsWithoutSpaces() {
        return 1;
    }

    @Override
    public String buildText() {
        return symbol.toString();
    }
}
