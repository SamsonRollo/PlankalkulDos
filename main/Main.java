package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import lib.PlankalkulDos;

public class Main{
    public Main(String path){
        String fileContent = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String curLine;
            while((curLine = br.readLine())!=null){
                fileContent+= curLine;
            }
            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PlankalkulDos pd = new PlankalkulDos(fileContent);
        pd.compile();
    }

    public static void main(String[] args){
        new Main("sample/example.plkd");
    }
}