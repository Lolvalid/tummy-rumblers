package com.example.tummyrumblers;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class tummy_Rumblers {
    //default for loggedIn is False, set to true if working on app for debugging
    private static boolean loggedIn = true;
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
        if (!menu.isBack()) {
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
        for (int i = 1; i <= Businesses.businessesMapDB.size()+1; i++) {
            if (i <= Businesses.businessesMapDB.size()) {
                String busName = Businesses.businessesMapDB.get(i).getNameBus();
                String busType = Businesses.businessesMapDB.get(i).getTypeOfBus();

                //indenting busType to end of window
                busType = indent.substring(0, indent.length() - busType.length() - busName.length()) + busType;

                System.out.println("{" + i + "}   " + busName + busType);
            } else {
                System.out.println();
                System.out.println("     You may also press {" + i + "} to complete your order");
            }
        }
    }
}
