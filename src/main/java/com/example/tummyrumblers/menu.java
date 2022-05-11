package com.example.tummyrumblers;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Scanner;

public class menu {
    static String banner = "********************************************************";
    private static int name = 0;
    private String menuItem;
    private Integer menuCost;
//    public static HashMap<Integer, String> menuItems = new HashMap<>();
//    public static HashMap<Integer, Integer> menuCosts = new HashMap<>();
//    public static HashMap<String, Integer > cartItems = new HashMap<>();
//    public static HashMap<String, Integer> cartCosts = new HashMap<>();

    public static HashMap<Integer, menu> menuHash = new HashMap<>();
    public static boolean CUSTOMER_BACK_BUTTON = false;

    public menu(String menuItem, Integer menuCost) {
        this.menuItem = menuItem;
        this.menuCost = menuCost;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public Integer getMenuCost() {
        return menuCost;
    }

    public static void main(String[] args) {
        //loadMenuOptions gets what menu categories area available in the restaurants folders and sets into Array.
        //menuCats grabs the array and displays to user just cat. names and prompts to select, offering a back option.
        String [] restMenuOpt = loadMenuOptions();

        assert restMenuOpt != null;
        int catSelected = menuCats(restMenuOpt);

        if (CUSTOMER_BACK_BUTTON) {
            tummy_Rumblers.main(new String [0]);
        }else {
            loadCatItems(restMenuOpt[catSelected]);
            printSubMenu();
        }
        }



    public static void loadMenu(int resChoice) {
        //generic class for banners/texts for the first issue of menus
        name = resChoice;
        System.out.println(banner);
        System.out.println("                 " + Businesses.businessesMapDB.get(resChoice).nameBus);
        System.out.println("                  " + Businesses.businessesMapDB.get(resChoice).phoneBus);
        System.out.println("         " + Businesses.businessesMapDB.get(resChoice).addyBus);
        System.out.println(banner);
        System.out.println();
        main(new String[0]);

    }

    public static String[] loadMenuOptions() {
        try {
            //creating new file, located in whatever business db in the menu DB
            File menuDB = new File("C:\\Users\\erwin\\Desktop\\Java Dev\\Personal Projects\\tummy-rumblers\\src\\main\\minidb\\MenuDB\\" + Businesses.businessesMapDB.get(name).getNameBus());
            //setting filter to only pick up the txt folders encase other stuff is in there.
            FilenameFilter menuFilter = new FilenameFilter() {
                @Override
                public boolean accept(File menuDB, String name) {
                    return name.endsWith(".txt");
                }
            };
            //creating file array to fill with list of options available at restaurant
            File[] menuOptions = menuDB.listFiles(menuFilter);
            assert menuOptions != null;
            String[] namedOptions = new String [menuOptions.length+1];
            //using a loop to get the names of each file and send back to array.
            for (int i = 0; i < menuOptions.length; i++) {
                namedOptions[i+1] = menuOptions[i].getName();
            }
            return namedOptions;
        }catch (Exception e) {} return null;

    }

    public static void loadCatItems(String catName) {
        try {
            File menuDB = new File("C:\\Users\\erwin\\Desktop\\Java Dev\\Personal Projects\\tummy-rumblers\\src\\main\\minidb\\MenuDB\\" + Businesses.businessesMapDB.get(name).getNameBus() + "\\"+ catName);
            Scanner readDB = new Scanner(menuDB);
            final int MONEY_CONV = 100;
            int itemOrder = 1;
            while (readDB.hasNextLine()) {
                int menuItem = 0;
                int menuPrice = 1;
                String[] readingText = new String[(int) menuDB.length()];
                //Splits line into 0/1/2/3 elements
                readingText = readDB.nextLine().split(",");
                double inputCost = Double.valueOf(readingText[menuPrice]) * MONEY_CONV;
                menuHash.put(itemOrder, new menu (readingText[menuItem],(int) inputCost));
                itemOrder++;
            }

        } catch (FileNotFoundException ff) {}
    }
    public static int menuCats(String[] menuOptions){
        Scanner selection = new Scanner(System.in);
        int menuChoice=0;

                for(int i = 1; i <= menuOptions.length; i++) {
                    if (i == menuOptions.length) {
                        System.out.println("{" + i + "}" + "  " + "Return to Restaurant Selection.");
                        break;
                    }
                    System.out.println("{" + i + "}" + "  " + menuOptions[i].replace(".txt", ""));
                }
        System.out.println();
        System.out.print("Which section of the menu would you like to see: ");
        do {
            menuChoice = selection.nextInt();
            CUSTOMER_BACK_BUTTON = false;
            if (menuChoice > menuOptions.length || menuChoice <= 0) {
                System.out.println("Invalid selection, please try again.");
            }
        }while (menuChoice > menuOptions.length || menuChoice <= 0);
        //if they selected to return to menu, boot backwards into menu
        if (menuChoice == menuOptions.length) {
            CUSTOMER_BACK_BUTTON = true;
        }
        return menuChoice;
    }

    public static void printSubMenu(){

     for( int i =1; i <= menuHash.size(); i++){
        //separated and named vars for readability & indenting
        String getItems = menuHash.get(i).getMenuCost().toString();
        //getting dollars and cents and separating to print.
        String dollars = getItems.substring(0, getItems.length()-2);
        String cents = getItems.substring(getItems.length()-2);
        String totalCost = dollars + "." + cents;

        String item = menuHash.get(i).getMenuItem();
        totalCost = tummy_Rumblers.indent.substring(0, tummy_Rumblers.indent.length() - totalCost.length() - item.length()) + totalCost;
        System.out.println(item + totalCost);
    }
        Scanner selection = new Scanner(System.in);
        System.out.println();
        System.out.println("Please select items to add to your cart: ");
        do {
            int menuChoice = selection.nextInt();
            if (menuChoice > menuHash.size() || menuChoice <= 0 ) {
                System.out.println("Invalid selection, please try again.");

            }

//            String busName = me.get(menuChoice);
//            int tempCart = cartItems.get(name);
//            int amount;
//            if (cartItems.get(busName) == null){
//                amount = 0;
//            } else { amount = cartItems.get(busName);}
//            cartItems.put(busName,amount + 1);
//            cartCosts.put(busName,menuCosts.get(menuChoice));
//            System.out.println(busName + " added to cart, you have " + tempCart + " in cart.");
        } while (!CUSTOMER_BACK_BUTTON);
}
}

