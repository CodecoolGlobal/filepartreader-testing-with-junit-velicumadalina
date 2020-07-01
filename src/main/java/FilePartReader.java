import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FilePartReader {
    private static String filePath;
    private static Integer fromLine = 0;
    private static Integer toLine = 0;

    public FilePartReader() {
        filePath = "/dummy/path/to/the/file.txt";
        fromLine = 1;
        toLine = 1;
    }

    public String getFilePath() {
        return filePath;
    }

    public Integer getFromLine() {
        return fromLine;
    }

    public Integer getToLine() {
        return toLine;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) throws IllegalArgumentException {

        if (toLine < fromLine || fromLine < 1) throw new IllegalArgumentException();

        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public static String read() throws IOException {
        StringBuilder builder = new StringBuilder();
        Stream<String> stream = Files.lines(Paths.get(filePath));
        stream.forEach(s -> builder.append(s).append("\n"));
        return builder.toString();
    }

    public static String readLines() throws IOException {
        List<String> lines = Arrays.asList(read().split("\n"));
        StringBuilder builder = new StringBuilder();
        for (int i = fromLine - 1; i <= toLine - 1; i++) {
            builder.append(lines.get(i)).append("\n");
        }
        return builder.toString();
    }


}
