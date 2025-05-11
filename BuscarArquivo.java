import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class BuscarArquivo {

    public static void main(String[] args) {
        // Caminho inicial (diretório raiz do C:\)
        Path caminhoInicial = Paths.get("C:\\");
        
        // Nome do arquivo que estamos procurando
        String nomeArquivoProcurado = "oi.pdf";
        
        // Chamada ao método que realiza a busca
        buscarArquivo(caminhoInicial, nomeArquivoProcurado);
    }
    
    public static void buscarArquivo(Path caminho, String nomeArquivoProcurado) {
        try {
            // Usando Files.walkFileTree para percorrer todos os arquivos e diretórios
            Files.walkFileTree(caminho, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path arquivo, BasicFileAttributes atributos) throws IOException {
                    // Verifica se o nome do arquivo é igual ao procurado
                    if (arquivo.getFileName().toString().equalsIgnoreCase(nomeArquivoProcurado)) {
                        // Exibe o caminho absoluto do arquivo encontrado
                        System.out.println("Arquivo encontrado: " + arquivo.toAbsolutePath());
                        return FileVisitResult.TERMINATE; // Para a busca quando encontrar o arquivo
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path caminho, IOException erro) throws IOException {
                    // Caso haja algum erro ao tentar acessar o arquivo ou diretório, ignoramos e continuamos
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
