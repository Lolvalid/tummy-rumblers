package com.example.tummyrumblers;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;

import static java.io.FileDescriptor.in;

public class userLogIn {
    static String banner = "********************************************************";
    static Scanner userNameInput = new Scanner(System.in);
    static int wrongLogins = 0;
    static int wrongPasswords = 0;


    public static void main(String[] args) {
        System.out.println(banner);
        System.out.println("Welcome to Ultra Eats where We get there ULTRA Fast");
        System.out.println(banner);
        System.out.println("");
        System.out.print("Please enter your username: ");
        logIn(userNameInput.nextLine());
    }

    public static void logIn(String username) {
        boolean foundUser = false;
        boolean loggedIn = false;
        try {
            //importing password file, yes its in plain text right now idk.
            File importDB = new File("./src/main/minidb/PasswordDB/PlainTextPasswordslol.txt");
            //loops file to check if next line has the username in the string, as long as file still has strings to read.
            Scanner readDB = new Scanner(importDB);
            do {
                HashMap<String, String> userInfo = new HashMap<>();
                String tempHold = readDB.nextLine();
                if (tempHold.contains(username)) {
                    String[] tempHoldArr = new String[readDB.nextLine().length()];
                    int userName = 0;
                    int userPass = 1;
                    tempHoldArr = (tempHold.split(","));
                    //checkins user input for password vs. data stored in string array.

                    do {
                        //Activating readPass method to take password as char array.
                        char[] arrayPass = readPassword();
                        //converting char array to string using SB.
                        StringBuilder sb1 = new StringBuilder();
                        for (int i = 0;i < arrayPass.length;i++){
                            sb1.append(arrayPass[i]);
                        }
                        //if password is same as in db, turns on loggedIN and allows user to continue through MAIN in tummy.
                        if (sb1.toString().equals(tempHoldArr[userPass])) {
                            loggedIn = true;
                            System.out.println(banner);
                            System.out.println("**     Log in successful, booting application         **");
                            System.out.println(banner);
                            break;
                        }
                        // loops through password prompt, iterates # of times wrong,
                        System.out.println(banner);
                        System.out.println("Incorrect password, please try again");
                        System.out.println(banner);
                        wrongPasswords++;
                    //if too many times wrong will close program and "lock" account.
                    } while (wrongPasswords < 3);

                    if (wrongPasswords > 3 && !loggedIn) {
                        System.out.println(banner);
                        System.out.println("Too many incorrect passwords, Account has been locked");
                        System.out.println("Please contact customer service for assistance");
                        System.out.println(banner);
                        System.exit(0);
                    }
                    foundUser = true;
                }
                tempHold = null;
                //As part of loop for user/pass
                // if file reaches end without username prompts error, and trys again.
                //after too many locks account and closes.
            } while (readDB.hasNextLine() && !foundUser);

            if (!foundUser && wrongLogins < 3) {
                System.out.println("Username not found, Please try again.");
                wrongLogins++;
                System.out.print("Please enter your username: ");
                logIn(userNameInput.nextLine());

            } else if (!foundUser && wrongLogins > 3) {
                System.out.println(banner);
                System.out.println("Username not found, Account has been locked");
                System.out.println("Please contact customer service for assistance");
                System.out.println(banner);
                System.exit(0);
            }

        } catch (FileNotFoundException EE) {
            //should never see this....
            System.out.println("Jinkies, something went wrong");
        }
    }


    //Prompts user to input password, to be stored and compared in DB
    public static char [] readPassword() {
        //creating new Password field, and setting options for dialog box.
        final JPasswordField jpf = new JPasswordField();
        JOptionPane jop = new JOptionPane(jpf,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = jop.createDialog("Enter Password: ");
        dialog.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        //attempts to focus window to front , haven't had luck with it in intellij
                        jpf.requestFocusInWindow();
                    }
                });
            }
        });

        //finally open dialog window and request password, then pass back up via return to compare.
        dialog.setVisible(true);
        int result = (Integer)jop.getValue();
        dialog.dispose();
        char[] password = null;
        if(result == JOptionPane.OK_OPTION){
            password = jpf.getPassword();
        }return password;
    }
}
