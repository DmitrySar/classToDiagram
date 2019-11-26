import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Result {

    private String[] textClasses;

    public Result(String[] textClasses) {
        this.textClasses = textClasses;
    }

    public void toConsole() {
        for (String s: textClasses) {
            System.out.println(s);
        }
    }

    public void toFile(String path) {
        Path p = Paths.get(path);
        String allText = "";
        for (String s: textClasses) {
            allText += s;
        }
        try {
            Files.write(p, allText.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
