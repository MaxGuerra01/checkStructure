import function.ReadFile;
import function.SearchFiles;
import org.junit.jupiter.api.Test;

import java.util.List;

public class testFunction {

    @Test
    public void test(){
        String pathToFiles = "C:/Users/Maximiliano Guerra/IdeaProjects/checkStructure";
        SearchFiles searchFiles = new SearchFiles();
        List<String> result = searchFiles.getResult(pathToFiles);
        ReadFile readFile = new ReadFile();

        System.out.println("---METODOS CON ERROR DE CARACTER ESPECIAL---");

        for (String value: result) {
            readFile.readFileFromPath(value);
        }

        System.out.println("("+readFile.getCounter()+") Errores encontrados");
    }
}
