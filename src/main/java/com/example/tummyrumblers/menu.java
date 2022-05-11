package com.example.tummyrumblers;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class menu {
    static String banner = "********************************************************";
    private static int name = 0;
    private String menuItem;
    private Integer menuCost;
    private static String[] previousMenu;
    public static HashMap<Integer, menu> menuHash = new HashMap<>();
    private static boolean hasCustomerHitBack = false;
    public static boolean isOrderFinished = false;

    public menu(String menuItem, Integer menuCost) {
        this.menuItem = menuItem;
        this.menuCost = menuCost;
    }
    private menu() {
    }

    public String getMenuItem() {
        return menuItem;
    }
    public static boolean isBack() {
        return hasCustomerHitBack;
    }

    public Integer getMenuCost() {
        return menuCost;
    }

    public static void main(String[] args) {
        //restMenuUpts gets what menu categories area available in the restaurants folders and sets into Array.
        //menuCats grabs the array and displays to user just cat. names and prompts to select, offering a back option.
        String[] restMenuOpt = loadMenuOptions();
        assert restMenuOpt != null;
        int catSelected = menuCats(restMenuOpt);
        do {
            if (hasCustomerHitBack) {
                tummy_Rumblers.main(new String[0]);
            } else {
                loadCatItems(restMenuOpt[catSelected]);
                printSubMenu();
                chooseItem();
            }
        } while (!isOrderFinished);
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
        previousMenu = menuOptions;
        Scanner selection = new Scanner(System.in);
        int menuChoice=0;

                for(int i = 1; i <= menuOptions.length + 1; i++) {
                    if (i == menuOptions.length) {
                        System.out.println("{" + i + "}  Return to Restaurant Selection.");
                        continue;
                    } else if ( i == menuOptions.length+1){
                        System.out.println();
                        System.out.println("     You may also press {" + i + "} to complete your order");
                        break;
                    }
                    System.out.println("{" + i + "}" + "  " + menuOptions[i].replace(".txt", ""));
                }
        System.out.println();
        System.out.print("Which section of the menu would you like to see: ");
        do {
            menuChoice = selection.nextInt();
            hasCustomerHitBack = false;
            if (menuChoice > menuOptions.length+1 || menuChoice <= 0) {
                System.out.println("Invalid selection, please try again.");
            }
        }while (menuChoice > menuOptions.length+1 || menuChoice <= 0);
        //if they selected to return to menu, boot backwards into menu
        if (menuChoice == menuOptions.length) {
            hasCustomerHitBack = true;
        }
        if (menuChoice == menuOptions.length+1) {
            isOrderFinished = true;
            System.out.println("Havent completed checkout yet lol");
            System.exit(0);
        }
        return menuChoice;
    }

    public static void printSubMenu() {

        for (int i = 1; i <= menuHash.size() + 1; i++) {
            if (i < menuHash.size()) {
                //separated and named vars for readability & indenting
                String getItems = menuHash.get(i).getMenuCost().toString();
                //getting dollars and cents and separating to print.
                String dollars = getItems.substring(0, getItems.length() - 2);
                String cents = getItems.substring(getItems.length() - 2);
                String totalCost = dollars + "." + cents;

                String item = menuHash.get(i).getMenuItem();
                totalCost = tummy_Rumblers.indent.substring(0, tummy_Rumblers.indent.length() - totalCost.length() - item.length()) + totalCost;
                System.out.println("{" + i + "} " + item + totalCost);
                continue;
            }

            if (i == menuHash.size()) {
                System.out.println("{" + i + "} Return to categories");
                continue;
            }
            if (i == menuHash.size() + 1) {
                System.out.println();
                System.out.println("     You may also press {" + i + "} to complete your order");
                continue;
            }
        }
    }

     public static void chooseItem() {
        Scanner selection = new Scanner(System.in);
        System.out.print("");
        System.out.print("     Please select an item to add to your cart: ");

        do {
            int menuChoice = selection.nextInt();
            if (menuChoice == menuHash.size()+1){
                isOrderFinished = true;
            }
            if (menuChoice == menuHash.size()){
                main(tummy_Rumblers.nope);
            }
            if (menuChoice > menuHash.size()+1 || menuChoice <= 0 ) {
                System.out.println("Invalid selection, please try again.");
                continue;
            }
            if(!isOrderFinished) {
                System.out.println();
                System.out.println("     ~~~~~ Added " + menuHash.get(menuChoice).getMenuItem() + " to cart. ~~~~~");
                ShoppingCart.addToCart(menuHash.get(menuChoice));
                ShoppingCart.totalOfCart();
                System.out.println();

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                break;
            }
        } while (!isOrderFinished);
        }
    }



