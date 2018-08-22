package com.tw.merchant;

import com.tw.merchant.processer.InputDataProcessor;
import com.tw.merchant.utility.FileProcessor;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StarterProg {

    public static void main(String [] args){

        String inputFile= "";
        if(args.length >= 1){
            inputFile = args[0];
        }else{
            System.out.println("USAGE: please pass the file name as parameter");
            System.exit(1);
        }

        //1. Read the file
        List inputLines = new ArrayList<String>();
        List outputLines = new ArrayList<String>();

        try {
            inputLines = (new FileProcessor()).readFile(inputFile);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("Number of lines in file: " + inputLines.size());
        Arrays.asList(inputLines).stream().forEach(System.out::println);


        //2. Read file contents and decipher
        outputLines = (new InputDataProcessor()).processInputLines(inputLines);

        System.out.println("output:" + outputLines.toString());

        //3. Prepare output

        //4. Write output file


        try {
            boolean status = (new FileProcessor()).writeOutputFile(outputLines);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
            //Nothing to do!
        }
    }
}
