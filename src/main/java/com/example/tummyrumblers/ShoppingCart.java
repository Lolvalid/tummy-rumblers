package com.example.tummyrumblers;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private static Map<menu, Integer> shoppingCart = new HashMap<>();
    private static int totalCartPrice;

    public  ShoppingCart(Map<menu, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getTotalCartPrice() {
        return totalCartPrice;
    }

    public static void addToCart(menu item){
        int currentCart;
        if (shoppingCart.get(item) == null){
            currentCart = 0;
        } else {
            currentCart = shoppingCart.get(item);
        }

        shoppingCart.put(item, ++currentCart);
    }

    public static Map<menu, Integer> getShoppingCart() {
        return shoppingCart;
    }


    public static void totalOfCart(){
        totalCartPrice=0;
        for (menu item: shoppingCart.keySet()){
             totalCartPrice += shoppingCart.get(item) * item.getMenuCost();
        }
            String priceAsString =Integer.toString(totalCartPrice);
            StringBuilder convertedIntPrice = new StringBuilder(priceAsString);

            convertedIntPrice.insert(priceAsString.length()-2,".");
            System.out.println("     Your current cart's subtotal is : $" + convertedIntPrice);
        }
    }

