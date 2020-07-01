import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {

    FilePartReader fileReader;
    FileWordAnalyzer wordAnalyzer;


    @BeforeEach
    void setUp() {
        fileReader = new FilePartReader();
        String filePath = "/home/madii/Desktop/FilepartReader/test.txt";
        Integer fromLine = 1;
        Integer toLine = 4;
        fileReader.setup(filePath, fromLine, toLine);
        wordAnalyzer = new FileWordAnalyzer(fileReader);
    }


    @Test
    void testOrderWordsReturnsValues() throws IOException {
        assertNotNull(wordAnalyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testOrderWordsReturnsCorrectValues() throws IOException {
        List<String> orderedWordsList = Arrays.asList("a", "a3a", "aaaa", "doua", "linie", "prima");
        assertEquals(orderedWordsList, wordAnalyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testWordsContainingSubstringReturnsValues() throws IOException {
        assertNotNull(wordAnalyzer.getWordsContainingSubstring("a"));
    }

    @Test
    void testWordsContainingSubstringReturnsCorrectValues() throws IOException {
        List<String> wordsWithSubstring = Arrays.asList("prima", "a", "doua", "a3a", "aaaa");
        assertEquals(wordsWithSubstring, wordAnalyzer.getWordsContainingSubstring("a"));
    }

    @Test
    void testWordsContainingSubstringNonExistingSubstring() throws IOException {
        List<String> wordsWithSubstring = new ArrayList<>();
        assertEquals(wordsWithSubstring, wordAnalyzer.getWordsContainingSubstring("cuvant"));
    }

    @Test
    void testPalindromeReturnsValue() throws IOException {
        assertNotNull(wordAnalyzer.getStringsWhichPalindromes());
    }

    @Test
    void testPalindromeReturnsCorrectValues() throws IOException {
        List<String> palindromes = Arrays.asList("a", "a3a", "aaaa");
        assertEquals(palindromes, wordAnalyzer.getStringsWhichPalindromes());
    }


}