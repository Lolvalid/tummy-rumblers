package com.example.tummyrumblers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class menu {
    public static int menu = 0;
    public static HashMap<Integer,String> menuItems = new HashMap<>();
    public static HashMap<Integer,Double> menuCosts = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        File importDB = new File("./src/main/minidb/MenuDB/" + Businesses.nameBus.get(menu) + ".txt");
        Scanner readDB = new Scanner(importDB);
        int itemOrder =1;
        while (readDB.hasNextLine()) {
            int menuItem = 0;
            int menuPrice = 1;
            String[] readingText = new String[(int) importDB.length()];

            //Splits line into 0/1/2/3 elements
            readingText = readDB.nextLine().split(",");
            menuItems.put(itemOrder,readingText[menuItem]);
            menuCosts.put(itemOrder,Double.valueOf(readingText[menuPrice]));
            itemOrder++;
        }
    }

        public static void loadMenu( int resChoice){

            menu = resChoice;
            try{
                main(new String[0]);
            } catch (FileNotFoundException EE){

            }
        }
    }
