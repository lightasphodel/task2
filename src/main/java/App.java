import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lena.task.composite.TextComponentType;
import com.lena.task.composite.TextComposite;
import com.lena.task.exception.TextException;
import com.lena.task.parser.LetterParser;
import com.lena.task.parser.LexemeParser;
import com.lena.task.parser.ParagraphParser;
import com.lena.task.parser.SentenceParser;
import com.lena.task.parser.SymbolParser;
import com.lena.task.parser.TextParser;
import com.lena.task.parser.WordParser;
import com.lena.task.reader.TextReader;
import com.lena.task.reader.impl.TextReaderImpl;
import com.lena.task.service.TextService;
import com.lena.task.service.impl.TextServiceImpl;

import java.util.OptionalInt;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args){
        TextReader textReader = new TextReaderImpl();
        TextService textService = new TextServiceImpl();

    try {
      String text = textReader.readText();
      logger.info("Original text:\n{}", text);

      LetterParser letterParser = new LetterParser();
      SymbolParser symbolParser = new SymbolParser();
      WordParser wordParser = new WordParser(letterParser);
      LexemeParser lexemeParser = new LexemeParser(wordParser, symbolParser);
      SentenceParser sentenceParser = new SentenceParser(lexemeParser);
      ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
      TextParser textParser = new TextParser(paragraphParser);

      TextComposite textComposite = new TextComposite(TextComponentType.TEXT);
      textParser.parse(text, textComposite);

      String parsedText = textComposite.buildText();
      logger.info("Parsed text:\n{}", parsedText);

      int lettersAmount = textComposite.countLetters();
      logger.info("Letters amount: {}", lettersAmount);

      int symbolsAmount = textComposite.countSymbolsWithoutSpaces();
      logger.info("Symbols amount: {}", symbolsAmount);

      logger.info("Find maximum number of sentences with the same word");
      OptionalInt maxSentences = textService.findMaxAmountSentencesWithSimilarWords(textComposite);
      if (maxSentences.isPresent()) {
        logger.info("Result: Maximum number of sentences containing the same word is: {}", maxSentences.getAsInt());
      } else {
        logger.warn("Result: No words found or text is empty");
      }

      logger.info("Display sentences sorted by number of lexemes (ascending)");
      textService.displaySentencesSortedByLexemesAmount(textComposite);

      logger.info("Swap first and last lexemes in each sentence");
      logger.info("Text before swapping:\n{}", textComposite.buildText());

      TextComposite modifiedText = textService.swapFirstAndLastLexemes(textComposite);

      logger.info("Text after swapping:\n{}", modifiedText.buildText());

    } catch (TextException e) {
      logger.error("Error: {}", e.getMessage());
    }
  }
}
