package com.unipi.talepis;

import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static int pG = 0; //played games
    private static int wG = 0; //won games
    private static int draws = 0; //draws

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("RANDOM CARD");
        while (true)//its always true but you can use a break
        {
            sG();
            pG = pG + 1;
            System.out.println("Do you want to play another game;\"YES/NO\"");
            String answer = sc.nextLine().toUpperCase(); //convert a string to uppercase letters
            if (answer.equals("YES"))  {
                continue;
            }
            else{
                break;
            }
        }
        sc.close();
        eG();
    }


    private static void sG() {
        ArrayList<Integer> cards = new ArrayList<>();
        ArrayList<Integer> user = new ArrayList<>();
        ArrayList<Integer> dealer = new ArrayList<>();
        for (int i = 0; i < 4; i++) { //χρωματα τραπουλας
            for (int j = 1; j < 14; j++) { // χαρτια τραπουλας για καθε χρωμα
                cards.add(j);
            }
        }
        Collections.shuffle(cards);
        List<Integer> UserCards = new ArrayList();
        List<Integer> DealerCards = new ArrayList();
        for (int i = 1; i < 3; i++) { //δυο φορες για το μοιρασμα
            com.unipi.talepis.Main random = new com.unipi.talepis.Main();
            int us = random.getRandomElement(cards);
            if (us==11 || us == 12 || us==13){
                us = 10;
            }
            if (us==1){
                System.out.println("Your card is ace.Do you want to use it as 1 or as 11?");
                Scanner answer1 = new Scanner(System.in);
                int answer = answer1.nextInt();
                Boolean flagan = true ;
                while (flagan.equals(true)){
                    if (answer==1){
                        us = 1;
                        flagan = false;
                    }
                    else if (answer==11){
                        us = 11;
                        flagan = false;
                    }
                    else{
                        System.out.println("Try again with 1 or 11");
                        answer = answer1.nextInt();
                    }
                }
            }
            UserCards.add(us);
            System.out.println("User's card "+us);
            if (i==2) {
                Integer sum = UserCards.stream().mapToInt(Integer::intValue).sum();
                System.out.println(sum);
            }
            com.unipi.talepis.Main random1 = new com.unipi.talepis.Main();
            int deal = random1.getRandomElement(cards);
            DealerCards.add(deal);
            if (i==2) {
                Integer sum1 = DealerCards.stream().mapToInt(Integer::intValue).sum();
            }
            cards.remove(cards.size() - 1);
            cards.remove(cards.size() - 1);
        }
        System.out.println("Do you want another card;\"YES/NO\"");
        Boolean flag = true;
        while (flag.equals(true)){
            Scanner sc = new Scanner(System.in);
            String an = sc.nextLine().toUpperCase(); //convert a string to uppercase letters
            while (an.equals("YES"))  {
                System.out.println("Answer is YES");
                com.unipi.talepis.Main random = new com.unipi.talepis.Main();
                int us = random.getRandomElement(cards);
                System.out.println("Your new card is "+us);
                if (us==1){
                    System.out.println("Your card is ace.Do you want to use it as 1 or as 11?");
                    Scanner answer1 = new Scanner(System.in);
                    int answer = answer1.nextInt();
                    Boolean flagan = true ;
                    while (flagan.equals(true)){
                        if (answer==1){
                            us = 1;
                            flagan = false;
                        }
                        else if (answer==11){
                            us = 11;
                            flagan = false;
                        }
                        else{
                            System.out.println("Try again with 1 or 11");
                            answer = answer1.nextInt();
                        }
                    }
                }
                UserCards.add(us);
                Integer sum = UserCards.stream().mapToInt(Integer::intValue).sum();
                System.out.println(sum);
                cards.remove(cards.size() - 1);
                System.out.println("Do you want another card;\"YES/NO\"");
                sc = new Scanner(System.in);
                an = sc.nextLine().toUpperCase();
            }
            if (an.equals("NO")){
                System.out.println("Answer is NO");
                flag = false;
            }
            else{
                System.out.println("Try again with \"YES/NO\"");
            }
        }

        Integer sum1 = DealerCards.stream().mapToInt(Integer::intValue).sum();
        while (sum1<18){
            com.unipi.talepis.Main random1 = new com.unipi.talepis.Main();
            int deal = random1.getRandomElement(cards);
            DealerCards.add(deal);
            sum1 = sum1 + deal;
            cards.remove(cards.size() - 1);
        }
        compareCards(UserCards,DealerCards);
    }

    //συναρτηση για να συγκρινουμε τις καρτες
    private static void compareCards(List<Integer> UserCards, List<Integer> DealerCards){
        Integer uc = UserCards.stream().mapToInt(Integer::intValue).sum();
        System.out.println("User's sum is "+uc);
        Integer dc = DealerCards.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Dealer's sum is "+dc);
        if (uc>21 && dc<=21){
            System.out.println("You lost the game.Your points are more than 21");
        }
        else if(dc>21 && uc<=21){
            System.out.println("Dealer lost the game.His points are more than 21");
            wG=wG+1;
        }
        else if (dc<=21 && uc<=21 && dc>uc){
            System.out.println("You lost the game.Dealer's points are more than yours");
        }
        else if(dc<= 21 && uc<=21 && dc<uc){
            System.out.println("You won the game.Your points are more than the dealer's points");
            wG = wG +1;
        }
        else if(dc<=21 & uc<=21 && dc==uc){
            System.out.println("Draw");
            draws = draws +1 ;
        }
        else{
            System.out.println("You both have more than 21 points.Draw");
            draws=draws+1;
        }
    }

    //synarthsh gia na pairnoume to tyxaio
    public int getRandomElement(List<Integer> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    private static void eG() {
        System.out.println("You have played " + pG + " games");
        System.out.println("You have won " + wG + " games");
        System.out.println("You had " + draws + " draws");
    }
}
//Rules:
//The object for the player is to draw cards totaling closer to 21,without going over (busted), than the
//dealer's cards.The cards 2 through 10 are worth their face value.Kings,queens and jacks are each worth 10
//and aces may be used as either 1 or 11