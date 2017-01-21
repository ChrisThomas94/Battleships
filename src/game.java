/**
 * Created by Chris on 16-Jan-17.
 */

import java.util.*;

public class game {

    private static String[][] createBoard(String[][] board){

        for(int r = 0; r<board.length; r++){
            for (int c = 0; c<board[0].length; c++){

                board[r][c] = "~";
            }
        }

        return board;
    }

    private static void showBoard(String[][] board){

        System.out.println("   A B C D E F G H I J");
        for(int r = 0; r<board.length;r++){
            if(r==9){
                System.out.print((r + 1) + "");
            }
            else{
                System.out.print((r + 1) + " ");
            }
            for(int c = 0; c<board[0].length;c++){
                System.out.print(" " + board[r][c]);

            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static String setOrientation(int x, int y, int length){

        String orientation = "";        //false = horizontal  true = vertical

        //if position is too close to edge then set orientation accordingly
        if(y>length && x>length) {
            //can be either but must be placed in reverse
            orientation = "error";

        } else if(x>length){
            //battleship must be vertical
            orientation = "vertical";

        }else if(y>length){
            //battleship must be horizontal
            orientation = "horizontal";

        }else{
            //battleship can be either
            orientation = "either";
        }

        return orientation;
    }

    private static String[][] placeBattleship(String[][] board, battleship b){

        //String[][] map = board;
        Random rand = new Random();


        //pick random position to place ship
        int startingPointX = rand.nextInt(9);
        int startingPointY = rand.nextInt(9);

        HashSet<String> set = new HashSet<String>();


        //int[] ship = new int[startingPointX, startingPointY];

        String orientation = setOrientation(startingPointX, startingPointY, b.getLength());

        Boolean valid = checkBoard(board, startingPointX, startingPointY, orientation, b.getLength());

        if(valid) {

            if (orientation.equals("vertical")) {
                for (int i = 0; i < b.getLength(); i++) {
                    board[startingPointX][startingPointY + i] = "X";
                    //b.setCoord([startingPointX][startingPointY + i]);
                }

            } else if (orientation.equals("horizontal")) {
                for (int i = 0; i < b.getLength(); i++) {
                    board[startingPointX + i][startingPointY] = "X";
                }

            } else if (orientation.equals("error")) {
                for (int i = 0; i < b.getLength(); i++) {
                    board[startingPointX][startingPointY - i] = "X";
                }

            } else {
                for (int i = 0; i < b.getLength(); i++) {
                    board[startingPointX + i][startingPointY] = "X";
                }
            }
        } else {
            //retry
            placeBattleship(board, b);
        }

        return board;
    }

    private static Boolean checkBoard(String[][] map, int startingPointX, int startingPointY, String orientation, int length){

        if(orientation.equals("vertical")){
            for(int i = 0; i<length; i++){
                if(map[startingPointX][startingPointY + i].equals("X")){
                    return false;
                }
            }

        } else if(orientation.equals("horizontal")) {
            for(int i = 0; i<length; i++){
                if(map[startingPointX+i][startingPointY].equals("X")){
                    return false;
                }
            }

        } else if(orientation.equals("error")){
            for(int i = 0; i<length; i++){
                if(map[startingPointX][startingPointY-i].equals("X")){
                    return false;
                }
            }

        } else {
            for(int i = 0; i<length; i++){
                if(map[startingPointX+i][startingPointY].equals("X")){
                    return false;
                }
            }
        }

        return true;
    }

    private static String[][] setUpBoard(String[][] board, battleship[] totalBattleships, destroyer[] totalDestroyers){

        for(int i = 0; i<totalBattleships.length;i++){

            placeBattleship(board, totalBattleships[i]);
        }

        for(int i=0; i<totalDestroyers.length;i++){

            //placeDestroyer(board, totalDestroyers[i]);
        }

        return board;
    }

    private static void displayMenu(){
        System.out.println("\nMenu:");
        System.out.println("1. Fire Shot");
        System.out.println("2. Show Solution");
        System.out.println("3. Quit");
    }

    private static int getMenuInput(Scanner input){
        int in = 0;
        if(input.hasNextInt()){
            in = input.nextInt();
            if(in>0 && in<4){
                in = in;
            }else{
                System.out.println("Invalid Entry, Please Try Again!!!\n");
            }
        }else{
            System.out.println("Invalid Entry, Please Try Again!!\n");
            input.next();
        }
        return in;
    }

    private static Boolean shotValid(String quantity)
    {
        int shot = Integer.parseInt(quantity);

        if(shot>0 && shot<11)
        {
            shot = shot;
            return true;
        } else {
            System.out.println("Invalid Entry, Please Try Again!!!!\n");
            return false;
        }
    }

    private static boolean fireShot(int[] shot, String[][] answers){

        boolean result = false;

        for(int shipHit=0 ; shipHit<answers.length; shipHit++)
        {
            if( answers[shot[1]][shot[0]].equals("X")){
                result = true;
            }else
            {
                result = false;
            }
        }
        return result;
    }

    private static void getInput(int[] shot){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a Coordinate: ");

        int numb = 0;
        String choice = input.next();
        String column = choice.substring(0,1);

        switch(column){
            case "A": numb = 0;
                break;
            case "B": numb = 1;
                break;
            case "C": numb = 2;
                break;
            case "D": numb = 3;
                break;
            case "E": numb = 4;
                break;
            case "F": numb = 5;
                break;
            case "G": numb = 6;
                break;
            case "H": numb = 7;
                break;
            case "I": numb = 8;
                break;
            case "J": numb = 9;
                break;
            default: System.out.println("Invalid Entry, Please Try Again!");
                break;
        }


        if(shotValid(choice.substring(1,choice.length()))){
            shot[0] = numb;
            shot[1] = Integer.parseInt(choice.substring(1, choice.length()));
            shot[1]--;
        }else{

        }

    }

    private static void beginGame(String[][] board, String[][] answers){

        Scanner input = new Scanner(System.in);
        int[] shot = new int[2];
        Boolean done = false;

        while(!done){
            showBoard(board);
            displayMenu();

            int choice = getMenuInput(input);
            if(choice == 1){
                getInput(shot);

                if(fireShot(shot,answers)){
                    board[shot[1]][shot[0]]="H";

                }else {
                    board[shot[1]][shot[0]] = "M";
                }
            }else if(choice == 2){
                showBoard(answers);

            }else if (choice == 3){
                done = true;
                System.out.println("Thanks For Playing");
            }
        }
    }

    public static void main(String[] arg){

        int rows = 10;
        int columns = 10;
        int noBattleships = 1;
        int noDestroyers = 2;

        String[][] board = new String[rows][columns];
        String[][] playingBoard = new String[rows][columns];
        String[][] answers;

        battleship[] totalBattleships = new battleship[noBattleships];
        destroyer[] totalDestroyers = new destroyer[noDestroyers];

        for(int i = 0; i<noBattleships;i++){

            battleship b = new battleship();
            totalBattleships[i] = b;
        }

        for(int i=0; i<noDestroyers;i++){

            destroyer d = new destroyer();
            totalDestroyers[i] = d;
        }

        board = createBoard(board);

        answers = setUpBoard(board, totalBattleships, totalDestroyers);

        playingBoard = createBoard(playingBoard);

        beginGame(playingBoard, answers);

    }
}
