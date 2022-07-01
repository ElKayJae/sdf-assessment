package sdf.assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvFile {
    public InputStreamReader is;
    public BufferedReader br;
    public String csvFilePathString;
    public ArrayList<String[]> fileOutput = new ArrayList<>();
    public String[] header;

    public CsvFile(String csvFilePathString){
        this.csvFilePathString = csvFilePathString;

    }
    
    public ArrayList<String[]> readCSV(){
        try{
            File file = new File(this.csvFilePathString);
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()){
                String line =  scanner.nextLine();
                String[] splitLine = line.split(",");
                this.fileOutput.add(splitLine);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return fileOutput;
    }

    public int getVariableIndex(String variable){
        this.header = fileOutput.get(0);
        int index=0;
            for (int i = 0; i < header.length; i++) {
                if (variable.contains(header[i])){
                    // System.out.println("header: " +header[i]);
                    // System.out.println(i);
                    index = i;
                    }
                }
             return index;
            }
                     
                   
    

}
