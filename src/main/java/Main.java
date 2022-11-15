import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\Alexander\\Desktop\\Документы";
        File file = new File(folderPath);
        System.out.println(getFileSize(file));
        System.out.println(file.length());
    }

    public static int getFileSize(File file) {
        if (file.isFile()) {
            return (int) file.length() / 8 / 1024;
        }

        int sum = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            sum = Arrays.stream(files).mapToInt(Main::getFileSize).sum();
        }
        return sum;
    }
}
