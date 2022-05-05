package com.example.tummyrumblers;
import java.util.*;

public class EraserThread implements Runnable {
    private volatile boolean stop;
    private char echochar = '*';
        //Prompt to be displayed to the user;
        public EraserThread(String prompt) {
            System.out.print(prompt);
        }

        // runs while stop = true; continues until stopMasking is declared, replaces characters with *.
        public void run() {
            int priority = Thread.currentThread().getPriority();
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            try{
            stop = true;
            while (stop) {
                System.out.print("\010" + echochar);
                try {
                    Thread.currentThread().sleep(1);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    ie.printStackTrace();
                    return;
                }
            }
        }finally {
                Thread.currentThread().setPriority(priority);
            }
        }
        //informs the run method user is done inputting and okay to stop masking.
        public void stopMasking() {
            this.stop = false;
        }
    }

