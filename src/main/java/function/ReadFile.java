package function;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Constantes.Constante.*;

public class ReadFile {

    private int counter = 0;

    public void readFileFromPath(String pathToFile) {
        try {
            File myObj = new File(pathToFile);
            Scanner myReader = new Scanner(myObj);
            Pattern regex2 = Pattern.compile(regexSpecials2);
            boolean fileBoolean = true;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                System.out.println(data);

                //Finds if is a Method or function line
                if (data.contains("public") || data.contains("private")){

                    //Check is if not a class type
                    if (!data.contains("class")){

                        //Check regex special characters
                        Matcher matcher = regex2.matcher(data);
                        if (matcher.find()){

                            //Shows the file name only once
                            if (fileBoolean){
                                System.out.println(pathToFile);
                                fileBoolean = false;
                            }
                            System.out.println(data);
                            counter++;
                        }
                    }
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. FileNotFoundException");
            e.printStackTrace();
        }
    }

    public int getCounter(){
        return counter;
    }

}
