package com.example.tummyrumblers;

import java.util.Scanner;

public class customerChoices {
    static Scanner choice = new Scanner(System.in);
    static int restaurantSelected = 0;
    static int count = 0;

    public static void main(String[] args) {

    }

    public static int selectRestaurant() {
        //prompt to select, loop until valid answer between restaurants available.
        if (count == 0) {
            System.out.print("Please select which restaurant you would like: ");
        }
        try {
            restaurantSelected = Integer.valueOf(choice.nextLine());
            if (!(restaurantSelected > 0 && restaurantSelected <= Businesses.nameBus.size())){
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
}
