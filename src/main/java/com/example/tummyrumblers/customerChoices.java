package com.example.tummyrumblers;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class customerChoices {
    static Scanner choice = new Scanner(System.in);
    static int restaurantSelected = 0;
    static int count = 0;

    public static void main(String[] args) {

    }

    public static int selectRestaurant() {
        //prompt to select, loop until valid answer between restaurants available.
        if (count == 0) {
            System.out.println();
            System.out.print("Please select which restaurant you would like: ");
        }
        try {
            restaurantSelected = Integer.valueOf(choice.nextLine());
            if (!(restaurantSelected > 0 && restaurantSelected <= Businesses.businessesMapDB.size())){
                System.out.println("Please enter a valid Restaurant");
                selectRestaurant();
                count++;
            }
            if (count > 4){
                System.out.println("Unable to continue, too many invalid responses received");
                System.out.println("Closing application, Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid response.");
            System.out.println("Closed due to Error.");
        }return restaurantSelected;
    }

    public static void returnToRestaurant(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println();
        System.out.println(menu.banner);
        System.out.println("          Returning to Restaurant Menus");
        System.out.println(menu.banner);
        tummy_Rumblers.listOptions();
        menu.loadMenu(customerChoices.selectRestaurant());
    }
}
