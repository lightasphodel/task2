package com.lena.task.composite;

public abstract class TextComponent {
    protected TextComponentType componentType;

    public TextComponentType getComponentType() {
        return this.componentType;
    }

    public void setComponentType(TextComponentType componentType) {
        this.componentType = componentType;
    }

    public abstract int countLetters();

    public abstract int countSymbolsWithoutSpaces();

    public abstract String buildText();
}
