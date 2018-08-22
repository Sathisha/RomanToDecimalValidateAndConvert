package com.tw.merchant.processer;

import com.tw.merchant.exceptions.IncorrectInputRecord;
import com.tw.merchant.roman.IntergalacticToRomanConverter;
import com.tw.merchant.roman.RomanNumberFormat;

import java.util.*;

/**
 * @author Sathisha
 */
public class InputDataProcessor {
    public List processInputLines(List<String> inputLines) {
        List outputLines = new ArrayList<String>();
        //1. loop through the list

        RomanNumberFormat romanNumberFormat = new RomanNumberFormat();
        IntergalacticToRomanConverter intergalacticToRomanConverterList = new IntergalacticToRomanConverter();
        Map shoppingCart = new HashMap<String, IntergalacticToRomanConverter>();

        for (String line : inputLines) {
            String[] lineWords = line.split(" ");
            IntergalacticToRomanConverter converter;


            System.out.println("\n \n \n *******Starting processing of line*************:" + line);
            if (lineWords.length >= 3 && "is".equals(lineWords[1])
                    && romanNumberFormat.getRomanDenominations().contains(lineWords[2])) {

                try {
                    LineDataProcessor.processDenomination(line, intergalacticToRomanConverterList);
                } catch (IncorrectInputRecord incorrectInputRecord) {
                    incorrectInputRecord.printStackTrace();
                    outputLines.add("I have no idea what you are talking about");
                } catch (ArrayIndexOutOfBoundsException aiob) {
                    aiob.printStackTrace();
                    outputLines.add("I have no idea what you are talking about");
                }

            } else if (lineWords.length >= 5 && line.toUpperCase().startsWith("How much is".toUpperCase())
            ) {

                try {
                    converter = LineDataProcessor.processHowMuchIsQuestion(line, intergalacticToRomanConverterList);
                    converter.getDecimalValue();
                    converter.getCommodityCostperUnit();

                    System.out.println("How much is:" + converter);

                    outputLines.add(converter.printIntergalacticDenomination() + " is " + converter.getDecimalValue());
                } catch (IncorrectInputRecord incorrectInputRecord) {
                    incorrectInputRecord.printStackTrace();
                    outputLines.add("I have no idea what you are talking about");
                } catch (ArrayIndexOutOfBoundsException aiob) {
                    aiob.printStackTrace();
                    outputLines.add("I have no idea what you are talking about");
                }

            } else if (lineWords.length >= 5 && line.toUpperCase().startsWith("How many credits is".toUpperCase())) {

                try {
                    converter = LineDataProcessor.processHowManyCreditsQuestion(line, intergalacticToRomanConverterList);
                    System.out.println("How many credits is:" + converter);
                    converter.getDecimalValue();
                    converter.getCommodityCostperUnit();

                    IntergalacticToRomanConverter commodity = (IntergalacticToRomanConverter) shoppingCart.get(converter.getCommodityName());
                    if(commodity == null){
                        outputLines.add("No Commodity/denomination definition found:" + converter.getCommodityName());
                    }else{
                        commodity.getDecimalValue();
                        commodity.getCommodityCostperUnit();
                        outputLines.add(converter.printIntergalacticDenomination() + converter.getCommodityName() + " is " + converter.getDecimalValue() * commodity.getCommodityCostperUnit() + " Credits");

                    }
                } catch (IncorrectInputRecord incorrectInputRecord) {
                    incorrectInputRecord.printStackTrace();
                    outputLines.add("I have no idea what you are talking about");
                } catch (ArrayIndexOutOfBoundsException aiob) {
                    aiob.printStackTrace();
                    outputLines.add("I have no idea what you are talking about");
                }

            } else if (lineWords.length >= 4 && (line.toUpperCase().endsWith("Credits".toUpperCase())
                    || line.toUpperCase().endsWith("Credit".toUpperCase()))) {

                try {
                    converter = LineDataProcessor.processCommodityCost(line, intergalacticToRomanConverterList);
                    shoppingCart.put(converter.getCommodityName(), converter);
                } catch (IncorrectInputRecord incorrectInputRecord) {
                    incorrectInputRecord.printStackTrace();
                    outputLines.add("I have no idea what you are talking about");
                } catch (ArrayIndexOutOfBoundsException aiob) {
                    aiob.printStackTrace();
                    outputLines.add("I have no idea what you are talking about");
                }


            } else {
                outputLines.add("I have no idea what you are talking about");
            }

            System.out.println("shoppingCart:" + shoppingCart);
            System.out.println("intergalacticToRomanConverterList:" + intergalacticToRomanConverterList);
            System.out.println("*******Starting processing of line************* complete *****   ");
        }


        return outputLines;
    }
}
