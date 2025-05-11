import java.io.File;
import java.util.*;

public class LargestFilesFinder {
    public static void main(String[] args) {
        String directoryPath = args.length > 0 ? args[0] : "/"; // Diretório raiz por padrão
        int fileLimit = args.length > 1 ? Integer.parseInt(args[1]) : 10; // Quantidade de arquivos a exibir
        
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("O diretório especificado não existe ou não é um diretório válido.");
            return;
        }
        
        List<File> largestFiles = findLargestFiles(directory, fileLimit);
        
        System.out.println("Maiores arquivos em " + directoryPath + ":");
        for (File file : largestFiles) {
            System.out.println(file.getAbsolutePath() + " - " + file.length() / 1024 + " KB");
        }
    }
    
    public static List<File> findLargestFiles(File root, int limit) {
        PriorityQueue<File> largestFiles = new PriorityQueue<>(Comparator.comparingLong(File::length));
        
        Queue<File> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            File current = queue.poll();
            File[] files = current.listFiles();
            
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        queue.add(file);
                    } else {
                        largestFiles.offer(file);
                        if (largestFiles.size() > limit) {
                            largestFiles.poll(); // Remove o menor arquivo da lista
                        }
                    }
                }
            }
        }
        
        List<File> result = new ArrayList<>(largestFiles);
        result.sort((a, b) -> Long.compare(b.length(), a.length())); // Ordena em ordem decrescente
        return result;
    }
}
