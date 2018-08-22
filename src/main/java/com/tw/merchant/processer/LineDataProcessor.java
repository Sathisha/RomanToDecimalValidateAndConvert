package com.tw.merchant.processer;

import com.tw.merchant.exceptions.IncorrectInputRecord;
import com.tw.merchant.roman.IntergalacticToRomanConverter;
import com.tw.merchant.roman.RomanNumberFormat;

public class LineDataProcessor {

    public static IntergalacticToRomanConverter processDenomination(String line, IntergalacticToRomanConverter intergalacticToRomanConverterList) throws IncorrectInputRecord {
        String[] lineWords = line.split(" ");

        // This is Denominator definition
        //System.out.println("Found Roman denomination: " + lineWords[2]);

        RomanNumberFormat romanNumberFormat = new RomanNumberFormat();

        //Add to Roman Converter
        intergalacticToRomanConverterList.getIntergalacticUnits().add(lineWords[0]);
        intergalacticToRomanConverterList.getRomanDenomination()
                .add(romanNumberFormat.getRomanDenominations()
                        .get(romanNumberFormat.getRomanDenominations().indexOf(lineWords[2])));

        if (lineWords.length > 3) {
            throw new IncorrectInputRecord("Incorrect data. Ignoring the line.");
        }
        return intergalacticToRomanConverterList;
    }

    public static IntergalacticToRomanConverter processHowMuchIsQuestion(String line, IntergalacticToRomanConverter intergalacticToRomanConverterList) throws IncorrectInputRecord {
        IntergalacticToRomanConverter converter = new IntergalacticToRomanConverter();
        String[] lineWords = line.split(" ");

        //System.out.println("How much is - block, lineWords:" + lineWords.length);
        //This may be a "How much is" question
        int i = 3;
        for (; i < lineWords.length; i++) {
            String word = lineWords[i];
            //System.out.println("How much is - block: word:" + word);
            //expect either denomination or commodity
            if (intergalacticToRomanConverterList.getIntergalacticUnits().contains(word)) {
                //System.out.println("How much is - block; Denomination found");
                //we already know this denomination
                converter.getIntergalacticUnits().add(word);
                converter.getRomanDenomination()
                        .add(intergalacticToRomanConverterList.getRomanDenomination()
                                .get(intergalacticToRomanConverterList
                                        .getIntergalacticUnits().indexOf(word)));
            } else {
                //System.out.println("How much is - block; Denomination NOT found");
                break;
            }
        }


        if("?".equals(lineWords[i]) && lineWords.length == i+1){
            //Success parsing
        }else{
            throw new IncorrectInputRecord("Incorrect data. Ignoring the line.");
        }


        return converter;
    }

    public static IntergalacticToRomanConverter processHowManyCreditsQuestion(String line, IntergalacticToRomanConverter intergalacticToRomanConverterList) throws IncorrectInputRecord {
        IntergalacticToRomanConverter converter = new IntergalacticToRomanConverter();
        String[] lineWords = line.split(" ");

        //System.out.println("How many credits is- block, lineWords:" + lineWords.length);

        int i = 4;
        for (; i < lineWords.length; i++) {
            String word = lineWords[i];
            //expect either denomination or commodity
            if (intergalacticToRomanConverterList.getIntergalacticUnits().contains(word)) {
                //System.out.println("How many credits is- block; Denomination found; word:" + word);
                //we already know this denomination
                converter.getIntergalacticUnits().add(word);
                converter.getRomanDenomination()
                        .add(intergalacticToRomanConverterList.getRomanDenomination()
                                .get(intergalacticToRomanConverterList
                                        .getIntergalacticUnits().indexOf(word)));
            } else {
                converter.setCommodityName(word);
                break;
            }
        }
        //System.out.println(i);
        //System.out.println(lineWords.length);
        if ("?".equals(lineWords[i + 1]) && lineWords.length == i + 2) {
            //System.out.println("How many credits is- block- block: ? complete");
        } else {
            //System.out.println("Error: extra text found after question...");
            throw new IncorrectInputRecord("Incorrect data. Ignoring the line.");
        }

        return converter;
    }

    public static IntergalacticToRomanConverter processCommodityCost(String line, IntergalacticToRomanConverter intergalacticToRomanConverterList) throws IncorrectInputRecord {
        IntergalacticToRomanConverter converter = new IntergalacticToRomanConverter();
        String[] lineWords = line.split(" ");

        //System.out.println("processCommodityCost:" + lineWords.length);

        int i = 0;
        for (; i < lineWords.length; i++) {
            if (intergalacticToRomanConverterList.getIntergalacticUnits().contains(lineWords[i])) {
                //System.out.println("Found the denomination: " + lineWords[i]);
                converter.getIntergalacticUnits().add(lineWords[i]);
                converter.getRomanDenomination()
                        .add(intergalacticToRomanConverterList.getRomanDenomination()
                                .get(intergalacticToRomanConverterList
                                        .getIntergalacticUnits().indexOf(lineWords[i])));
            } else {
                //System.out.println("Found the commodity: " + lineWords[i]);
                converter.setCommodityName(lineWords[i]);
                break;

            }
        }
        i++;
        if ("is".equals(lineWords[i])) {
            i++;
            //System.out.println("i:" + i);
            //System.out.println("word[i]:" + lineWords[i]);

            try {
                int commodityCost = Integer.parseInt(lineWords[i]);
                converter.setCommodityCost(commodityCost);
            } catch (NumberFormatException nfe) {
                //System.out.println("NUmber format error");
            }

            i++;

            if ("Credits".toUpperCase().equals(lineWords[i].toUpperCase()) && lineWords.length == i + 1) {
                //System.out.println("Processed commodity cost");

            } else {
                //System.out.println("Processed commodity cost - failed!. ignore this row and proceed...");
                throw new IncorrectInputRecord("Incorrect data. Ignoring the line.");
            }
        }


        return converter;
    }
}
