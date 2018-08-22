package com.tw.merchant.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sathisha
 * This class helps with File handling. Read input file and write results to a file.
 */
public class FileProcessor {

    public List readFile(String inputFile) throws IOException {

        List inputLines = new ArrayList<String>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            //System.out.println("File not found!!!");

            throw new FileNotFoundException("File not found!!! please check the file name with correct path.");
        }

        String line = null;
        try {
            line = reader.readLine();

            while (line != null) {
                inputLines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            //e.printStackTrace();
            //System.out.println("Error while reading file contents");

            throw new IOException("Error while reading the file contents");
        }

        reader.close();

        return inputLines;
    }

    public boolean writeOutputFile(List<String> outputLines) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("OutputFile.txt");

        try {
            for (String line : outputLines) {

                fileOutputStream.write(line.getBytes());
                fileOutputStream.write(System.lineSeparator().getBytes());


            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new IOException("Error while writing to file");
        }
        fileOutputStream.close();
        return true;
    }
}
