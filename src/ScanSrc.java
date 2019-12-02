import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class ScanSrc {

    public String getSrcPath() {
        return srcPath;
    }

    private String srcPath;

    private String[] textClasses;

    public ScanSrc(String srcPath) {
        this.srcPath = srcPath;
    }

    public String[] getTextClasses() throws IOException {
        Path path = Paths.get(srcPath);
        int len = path.toFile().list().length;
        textClasses = new String[len];
        int i = 0;
        for (String fileName: path.toFile().list()) {
            List<String> text = Files.readAllLines(Paths.get(path.toAbsolutePath() + "\\" + fileName));
            textClasses[i] = arrayListToString(text);
            i++;
        }
        return textClasses;
    }

    public String[] getNames() {
        return Paths.get(srcPath).toFile().list();
    }

    private String arrayListToString(List<String> list) {
        String result = "";
        for (String s: list) {
            result += s + "\n";
        }
        return result;
    }

}
