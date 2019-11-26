import java.io.IOException;
import java.util.Arrays;

class Controller {

    private ScanSrc src;
    private AnalizerSrc analizer;
    private Result result;

    public static void main(String[] args) throws IOException {
        String pathIn = args[0];
        String pathOut = args[1];
        Controller controller = new Controller();
        controller.start(pathIn, pathOut);
//        String pathIn = "D:\\projects\\classesToDiagram\\src";
//        String pathOut = "D:\\projects\\classesToDiagram\\diagram.txt";
    }

    private void start(String pathIn, String pathOut) throws IOException {
        src = new ScanSrc(pathIn);
        analizer = new AnalizerSrc(src.getTextClasses(), src.getNames());
        result = new Result(analizer.getResult());
        result.toConsole();
        result.toFile(pathOut);
    }

}
