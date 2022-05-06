package com.example.tummyrumblers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class tummy_Rumblers {
    static boolean loggedIn = true;
    static String banner = "********************************************************";
    static String indent = "                                                  ";
    static String[] nope = new String[0];

    public static void main(String[] args) {
        if (!loggedIn) {
            userLogIn.main(new String[0]);
        }
        bootRumbling();
        listOptions();
        menu.loadMenu(customerChoices.selectRestaurant());

    }

    public static void bootRumbling() {

        try {
            Businesses.main(nope);
        } catch (FileNotFoundException ff) {
        }
        if (!menu.CUSTOMER_BACK_BUTTON) {
            System.out.println("********************************************************");
            System.out.println("**  Please wait while we load menu's and restaurants  **");
            System.out.println("********************************************************");


            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void listOptions() {
        System.out.println(" ");
        System.out.println("The following available for delivery and their food type");
        System.out.println(" ");
        //Setting equal to size but equal to size, loop remains the same.
        for (int i = 1; i <= Businesses.nameBus.size(); i++) {
            String busName = Businesses.nameBus.get(i);
            String busType = Businesses.typeOfBus.get(i);

            //indenting busType to end of window
            busType = indent.substring(0, indent.length() - busType.length() - busName.length()) + busType;

            System.out.println("{" + i + "}   " + busName + busType);
        }
    }
}
//    public static void listMenu(){
//        for(int i = 1; i < menu.menuItems.size(); i++){
//            String menuItem = menu.menuItems.get(i);
//            int price = menu.menuCosts.get(i);
//
////            String[] countSpace = price.toString().split("\\.");
////            int cS = countSpace[0].length() + countSpace[1].length();
//
//            String newPrice = indent.substring(0,indent.length() - price.toString().length() - menuItem.length()) + price;
//            System.out.println("{" + i + "}  " + menuItem + newPrice + "$" );
//        }
//    }
//}
