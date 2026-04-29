package com.lena.task.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite extends TextComponent{
    private static final String SPACE = " ";
    private static final String TAB = SPACE.repeat(4);
    private static final String LINE_BREAK = "\n";
    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType componentType) {
        this.componentType = componentType;
    }

    public boolean add(TextComponent component) {
        return components.add(component);
    }

    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    public List<TextComponent> getComponents() {
        return new ArrayList<>(components);
    }

    @Override
    public int countLetters() {
        int count = 0;
        for (TextComponent component : components) {
            count += component.countLetters();
        }
        return count;
    }

    @Override
    public int countSymbolsWithoutSpaces() {
        int count = 0;
        for (TextComponent component : components) {
            count += component.countSymbolsWithoutSpaces();
        }
        return count;
    }

    @Override
    public String buildText() {
        StringBuilder text = new StringBuilder();
        TextComponent previousComponent = null;

        for (TextComponent component : components) {
            if (component.getComponentType() == TextComponentType.PARAGRAPH) {
                if (previousComponent != null) {
                    text.append(LINE_BREAK);
                }
                text.append(TAB);
            } else if (component.getComponentType() == TextComponentType.SENTENCE) {
                if ((previousComponent != null) && (previousComponent.getComponentType() != TextComponentType.PARAGRAPH)) {
                    text.append(SPACE);
                }
            } else if (component.getComponentType() == TextComponentType.LEXEME) {
                if (previousComponent != null && ((previousComponent.getComponentType() == TextComponentType.LEXEME) ||
                (previousComponent.getComponentType() == TextComponentType.WORD))) {
                    text.append(SPACE);
                }
            }

            text.append(component.buildText());
            previousComponent = component;
        }
        return text.toString();
    }
}
