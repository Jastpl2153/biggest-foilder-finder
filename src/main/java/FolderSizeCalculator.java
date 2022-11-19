import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {
    private File file;

    public FolderSizeCalculator(File file) {
        this.file = file;
    }

    @Override
    protected Long compute() {
        if (file.isFile()) {
            return file.length();
        }

        List<FolderSizeCalculator> subTask = new LinkedList<>();
        long sum = 0;
        File[] files = file.listFiles();
        for (File file1 : Objects.requireNonNull(files)) {
            FolderSizeCalculator task = new FolderSizeCalculator(file1);
            task.fork();
            subTask.add(task);
        }

        for (FolderSizeCalculator task: subTask) {
            sum += task.join();
        }
        return sum;
    }
}
