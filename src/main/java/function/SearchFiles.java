package function;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Constantes.Constante.regexLettersNumbers;
import static Constantes.Constante.regexSpecials;

public class SearchFiles {

    private List<String> result;

    private void readFilesFromDirectory(String pathToFiles){
//        String pathToFiles = "C:/Users/Maximiliano Guerra/IdeaProjects/checkStructure";
        try (Stream<Path> walk = Files.walk(Paths.get(pathToFiles))) {
            // We want to find only regular files
//            List<String> result = walk.filter(Files::isRegularFile)
//                    .map(x -> x.toString()).collect(Collectors.toList());
            result = walk.filter(Files::isRegularFile).filter(p -> p.getFileName().toString().endsWith(".java"))
                      .map(x -> x.toString()).collect(Collectors.toList());

            String valorOK = "--VALORES OK--\n";
            String valorNotOK = "--ARCHIVOS CON ERROR DE CARACTER ESPECIAL--\n";
            Pattern regex = Pattern.compile(regexLettersNumbers);
            Pattern regex2 = Pattern.compile(regexSpecials);

            for (String value : result) {
                Matcher matcher = regex.matcher(value);
                Matcher matcher2 = regex2.matcher(value.replace(" ", ""));
                if(matcher.find() && !matcher2.find() && !value.toLowerCase().contains("ñ")){
//                    valorOK = valorOK + value + "\n";
                } else {
                    valorNotOK = valorNotOK + value + "\n";
                }
            }
            System.out.println(valorNotOK);
//            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getResult(String pathToFiles){
        readFilesFromDirectory(pathToFiles);
        return result;
    }


}
