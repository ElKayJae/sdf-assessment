package sdf.assessment;

import java.util.ArrayList;

public class App 
{
    public static String csvFilePathString;
    public static String txtFilePathString;

    // mvn compile exec:java -Dexec.mainClass="sdf.assessment.App" -Dexec.args=""
    // java -jar target/task1-1.0-SNAPSHOT.jar
    public static void main( String[] args ){
        
        for (int i = 0; i < args.length; i++) {
            csvFilePathString = args[0];
            txtFilePathString = args[1];
        }

        String filename = csvFilePathString.replace(".csv", "");

        System.out.println( "Hello World!1" );
        CsvFile csvFile = new CsvFile(csvFilePathString);
        csvFile.readCSV();
        TemplateFile templateFile = new TemplateFile(txtFilePathString,csvFilePathString);
        ArrayList<String> variableList = templateFile.getVariableName();
        templateFile.printVariables();
        ArrayList<String> templateArray = templateFile.templateFileOutput;
        ArrayList<String[]> csvArray = csvFile.fileOutput;

        for (int rows = 1; rows< csvArray.size(); rows++){
            
            // System.out.println("new line_______________________________");
            ArrayList<String> tempArray = new ArrayList<>();
            for (String line: templateArray){
                for (int j = 0; j < variableList.size(); j++) {
                    if (line.contains(variableList.get(j))){
                        line = line.replace("__"+variableList.get(j)+"__", csvArray.get(rows)[csvFile.getVariableIndex(variableList.get(j))]);
                    } 
                }

                tempArray.add(line);
            }
            tempArray.forEach( newline -> System.out.println(newline));
            templateFile.writefile(tempArray, rows, filename);
        }
    }
}
