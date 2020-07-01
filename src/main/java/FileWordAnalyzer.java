import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWordAnalyzer {

    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List<String> getWords() throws IOException {
        return Arrays.stream(FilePartReader.readLines().split("\\s+"))
                .map(word-> word.replaceAll("^\\W+$|^\\W|\\W$", ""))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getWordsOrderedAlphabetically() throws IOException {
        FilePartReader.readLines();
        List<String> words = getWords();
        return words.stream().sorted().collect(Collectors.toList());
    }

    public List<String> getWordsContainingSubstring(String subString ) throws IOException {
        FilePartReader.readLines();
        return getWords().stream()
                .filter(word -> word.contains(subString))
                .collect(Collectors.toList());
    }

    public List<String> getStringsWhichPalindromes() throws IOException {
        FilePartReader.readLines();
        return getWords().stream()
                .filter(FileWordAnalyzer::isPalindrome)
                .collect(Collectors.toList());

    }

    public static boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

}
