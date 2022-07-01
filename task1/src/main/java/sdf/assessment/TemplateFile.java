package sdf.assessment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class TemplateFile {
    
    public InputStreamReader is;
    public BufferedReader br;
    public String txtFilePathString;
    public String csvFilePathString;
    public ArrayList<String> templateFileOutput = new ArrayList<>();
    public ArrayList<String> editedFileOutput = new ArrayList<>();
    public ArrayList<String[]> csvFileOutput = new ArrayList<>();
    public ArrayList<String> variableList = new ArrayList<>();
    public CsvFile csvFile;


    public TemplateFile(String txtFilePathString, String csvFilePathString){
        this.txtFilePathString = txtFilePathString;
        this.csvFile = new CsvFile(csvFilePathString);
        this.csvFileOutput = csvFile.readCSV();
        this.templateFileOutput = readTxt();
        // for(String line:templateFileOutput)
        // System.out.println(line);
    }
    
    public ArrayList<String> readTxt(){
        try{
            File file = new File(this.txtFilePathString);
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()){
                String line =  scanner.nextLine();
                templateFileOutput.add(line);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return templateFileOutput;
    }

    public ArrayList<String> getVariableName(){
        //for each line
        try{
            File file = new File(this.txtFilePathString);
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNext()){
                String line =  scanner.nextLine();
                if (line.contains("__")){
                int variableStart=line.indexOf("__")+2;
                int variableEnd=line.lastIndexOf("__");
                String variable = line.substring(variableStart,variableEnd);
                variableList.add(variable);
                }
            }
            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return variableList;
    }

    public void printVariables(){
        System.out.println("Variable List: ");
        variableList.forEach(variable -> System.out.println(variable));
    }

    // public void replaceVariables(){
    //     System.out.println("test");
    //     System.out.println(csvFile.fileOutput.size());

    //     //for each row of csv file
    //     for (int index = 1; index < csvFile.fileOutput.size()-1; index++) {
    //         System.out.println("test");
    //         //for each line of the template
    //         for (String line:this.templateFileOutput){
    //             System.out.println(line);
    //             //for each variable
    //             for(String variable:variableList){
    //                 if (line.contains(variable)){
    //                     System.out.println(line);
    //                     System.out.println(variable);
    //                     line = line.replace(variable, csvFile.getFiller(csvFile.getVariableIndex(variable), index));
    //                     System.out.println(line);
    //                 }
    //             }

    //         }
    //     }
    // }

    

    public void writefile(ArrayList<String> tempArray, int number){

        //define new file object same as above step in load function
        String saveLocation = "file"+ number + ".txt";
        File file = new File(saveLocation);
    
        try {
            PrintWriter printWriter = new PrintWriter(file);
            BufferedWriter bw = new BufferedWriter(printWriter);


            for (String line:tempArray){
                bw.write(line+"\n");
                System.out.println(line);
            }

            bw.flush();
            bw.close();
            printWriter.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    
}
