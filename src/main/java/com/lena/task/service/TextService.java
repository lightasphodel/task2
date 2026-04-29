package com.lena.task.service;

import java.util.OptionalInt;

import com.lena.task.composite.TextComposite;

public interface TextService {
    OptionalInt findMaxAmountSentencesWithSimilarWords(TextComposite textComposite);

    void displaySentencesSortedByLexemesAmount(TextComposite textComposite);

    TextComposite swapFirstAndLastLexemes(TextComposite textComposite);
}
