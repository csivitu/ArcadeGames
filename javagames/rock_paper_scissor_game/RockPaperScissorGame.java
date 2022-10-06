import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorGame {
    private static Scanner input = new Scanner(System.in);
    private static String userChoice, inputUser, inputCom;
    private static String[] RockPaperScissor = {"Rock", "Paper", "Scissor"};
    private static Random random = new Random();
    private static boolean win = false;
    private static void beginPrint(){
        System.out.println("________________________");
        System.out.println("Rock Paper Scissor Game");
        System.out.println("________________________");
    }
    private static void printWin(String whoWin){
        System.out.println("________________________");
        System.out.println("\t" + whoWin + " Win");
        System.out.println("________________________");
    }

    private static void printDraw(){
        System.out.println("________________________");
        System.out.println("\tDraw");
        System.out.println("________________________");
    }

    private static void printUserAndComInput(){
        System.out.println("\n________________________");
        System.out.println("User Input : " + inputUser);
        System.out.println("Computer Input : " + inputCom);
    }

    private static void gameLogic(){
        System.out.print("Input User : ");
        inputUser = input.nextLine();
        int index = random.nextInt(RockPaperScissor.length);
        inputCom = RockPaperScissor[index];
        if(inputUser.equalsIgnoreCase("rock") && inputCom.equalsIgnoreCase("rock")){
            printUserAndComInput();
            printDraw();
            mainLogic();
        }else if(inputUser.equalsIgnoreCase("paper") && inputCom.equalsIgnoreCase("rock")){
            printUserAndComInput();
            printWin("User");
            mainLogic();
        }else if(inputUser.equalsIgnoreCase("scissor") && inputCom.equalsIgnoreCase("rock")){
            printUserAndComInput();
            printWin("Computer");
            mainLogic();
        }else if(inputUser.equalsIgnoreCase("rock") && inputCom.equalsIgnoreCase("paper")){
            printUserAndComInput();
            printWin("Computer");
            mainLogic();
        }else if(inputUser.equalsIgnoreCase("paper") && inputCom.equalsIgnoreCase("paper")){
            printUserAndComInput();
            printDraw();
            mainLogic();
        }else if(inputUser.equalsIgnoreCase("scissor") && inputCom.equalsIgnoreCase("paper")){
            printUserAndComInput();
            printWin("User");
            mainLogic();
        }else if(inputUser.equalsIgnoreCase("rock") && inputCom.equalsIgnoreCase("scissor")){
            printUserAndComInput();
            printWin("User");
            mainLogic();
        }else if(inputUser.equalsIgnoreCase("paper") && inputCom.equalsIgnoreCase("scissor")){
            printUserAndComInput();
            printWin("Computer");
            mainLogic();
        }else if(inputUser.equalsIgnoreCase("scissor") && inputCom.equalsIgnoreCase("scissor")){
            printUserAndComInput();
            printDraw();
            mainLogic();
        }else{
            System.out.println("Invalid Input");
            gameLogic();
        }
    }
    private static void mainLogic(){
        System.out.println("Want to Play?");
        userChoice = input.nextLine();
        if(userChoice.equalsIgnoreCase("y") || userChoice.equalsIgnoreCase("yes")){
            gameLogic();
        }else if(userChoice.equalsIgnoreCase("n") || userChoice.equalsIgnoreCase("no")){
            System.out.println("Okay");
        }else{
            System.out.println("Invalid Input");
            mainLogic();
        }
    }
    public static void main(String[] args) {
        beginPrint();
        mainLogic();
    }
}
