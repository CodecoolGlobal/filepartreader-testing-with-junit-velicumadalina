import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {

    private FilePartReader filePartReader;
    private static String filePath;


    @BeforeAll
    static void initFile() {
        filePath = "/home/madii/Desktop/FilepartReader/test.txt";
    }

    @BeforeEach
    void init() {
        filePartReader = new FilePartReader();
    }

    @Test
    void testConstructorAssignedValues() {
        assertAll("parameters",
                () -> assertNotNull(filePartReader.getFilePath()),
                () -> assertNotNull(filePartReader.getFromLine()),
                () -> assertNotNull(filePartReader.getToLine()));
    }

    @Test
    void testToLineSmallerThanFromLine() {
        Integer toLine = 1;
        Integer fromLine = 2;

        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup(filePath, fromLine, toLine));
    }

    @Test
    void testReadReturnsValue() throws IOException {
        Integer fromLine = 1;
        Integer toLine = 2;
        filePartReader.setup(filePath, fromLine, toLine);
        assertNotNull(filePartReader.read());
    }


    @Test
    void testReadLinesReturnsValue() throws IOException {
        Integer fromLine = 1;
        Integer toLine = 2;
        filePartReader.setup(filePath, fromLine, toLine);
        assertNotNull(filePartReader.readLines());
    }

    @Test
    void testReadReturnsCorrectValue() throws IOException {
        Integer fromLine = 1;
        Integer toLine = 2;
        String fileContent = "prima linie\n" +
                "a doua\n" +
                "a3a\n" +
                "aaaa\n";
        filePartReader.setup(filePath, fromLine, toLine);
        assertEquals(fileContent, filePartReader.read());
    }


    @Test
    void testReadOnlyFirstLine() throws IOException {
        Integer fromLine = 1;
        Integer toLine = 1;
        String firstLine = "prima linie\n";
        filePartReader.setup(filePath, fromLine, toLine);
        assertEquals(firstLine, filePartReader.readLines());
    }

    @Test
    void testReadOnlyLastLine() throws IOException {
        Integer fromLine = 4;
        Integer toLine = 4;
        String lastLine = "aaaa\n";
        filePartReader.setup(filePath, fromLine, toLine);
        assertEquals(lastLine, filePartReader.readLines());
    }

    @Test
    void testReadToLineSmallerThanFromLine() throws IOException {
        Integer fromLine = 2;
        Integer toLine = 1;
        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup(filePath, fromLine, toLine));
    }


    @Test
    void testReadNonExistentToLine() throws IOException {
        Integer fromLine = 1;
        Integer toLine = 0;
        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup(filePath, fromLine, toLine));
    }


    @Test
    void testReadFromNonExistentFile() throws IOException {
        String notExistentFilePath = "nothing/here.txt";

        filePartReader.setup(notExistentFilePath, 1, 100);

        assertThrows(IOException.class, () -> filePartReader.read());
    }


}