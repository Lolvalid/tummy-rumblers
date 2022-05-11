package com.example.tummyrumblers;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Businesses {
    String nameBus;
    String phoneBus;
    String addyBus;
    String typeOfBus;
    static Map<Integer, Businesses> businessesMapDB = new HashMap<>();

    public Businesses(String nameBus, String phoneBus, String addyBus, String typeOfBus) {
        this.nameBus = nameBus;
        this.phoneBus = phoneBus;
        this.addyBus = addyBus;
        this.typeOfBus = typeOfBus;
    }

    private Businesses() {

    }
//   public static HashMap<Integer,String> nameBus = new HashMap<>();
//   public static HashMap<Integer,String> phoneBus = new HashMap<>();
//   public static HashMap<Integer,String> addyBus = new HashMap<>();
//   public static HashMap<Integer,String> typeOfBus = new HashMap<>();

    public String getNameBus() {
        return nameBus;
    }

    public String getPhoneBus() {
        return phoneBus;
    }

    public String getAddyBus() {
        return addyBus;
    }

    public String getTypeOfBus() {
        return typeOfBus;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //locations in file for each
        int businessNameLoc = 0;
        int businessPhoneLoc = 1;
        int businessAddLoc = 2;
        int businessTypeLoc = 3;

        //reading file from minidb folder
        File importDB = new File("./src/main/minidb/BusinessDB/BusinessDB.txt");
        //Preparing String Array for when scanner reads file.
        Scanner readDB = new Scanner(importDB);
        int restNum = 1;
        while (readDB.hasNextLine()) {
            String[] readingText = new String[(int) importDB.length()];

            //Splits line into 0/1/2/3 elements
            readingText = readDB.nextLine().split(",");
            //safety check to prevent empty strings
            if ( restNum > 2 && readingText[0].equals(""))
                break;
            businessesMapDB.put(restNum,new Businesses(readingText[businessNameLoc],readingText[businessPhoneLoc],readingText[businessAddLoc],readingText[businessTypeLoc]));
            restNum++;
        }
    }
}