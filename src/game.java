/**
 * Created by Chris on 16-Jan-17.
 */

import java.util.*;

public class Game {

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
            orientation = "reverse";

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

    private static String[][] placeShip(String[][] board, Ship S){

        Random rand = new Random();

        //pick random position to place Ship
        int startingPointX = rand.nextInt(9);
        int startingPointY = rand.nextInt(9);

        HashSet<String> coord = new HashSet<>();

        String orientation = setOrientation(startingPointX, startingPointY, S.getLength());

        Boolean valid = checkBoard(board, startingPointX, startingPointY, orientation, S.getLength());

        if(valid) {

            switch(orientation){
                case "vertical":
                    for (int i = 0; i < S.getLength(); i++) {
                        board[startingPointX][startingPointY + i] = "X";
                        coord.add(Integer.toString(startingPointX) + Integer.toString(startingPointY + i));
                    }
                    break;

                case "horizontal":
                    for (int i = 0; i < S.getLength(); i++) {
                        board[startingPointX + i][startingPointY] = "X";
                        coord.add(Integer.toString(startingPointX + i) + Integer.toString(startingPointY));
                    }
                    break;

                case "reverse":
                    for (int i = 0; i < S.getLength(); i++) {
                        board[startingPointX][startingPointY - i] = "X";
                        coord.add(Integer.toString(startingPointX) + Integer.toString(startingPointY - i));
                    }
                    break;

                default:
                    for (int i = 0; i < S.getLength(); i++) {
                        board[startingPointX + i][startingPointY] = "X";
                        coord.add(Integer.toString(startingPointX + i) + Integer.toString(startingPointY));
                    }
                    break;

            }

            //System.out.println("coord" + coord);

            S.setCoord(coord);

        } else {
            //retry
            //System.out.println("I had to retry.");
            placeShip(board, S);
        }



        return board;
    }

    private static Boolean checkBoard(String[][] map, int startingPointX, int startingPointY, String orientation, int length){

        switch (orientation){
            case "vertical":
                for(int i = 0; i<length; i++){
                    if(map[startingPointX][startingPointY + i].equals("X")){
                        return false;
                    }
                }
                break;

            case "horizontal":
                for(int i = 0; i<length; i++){
                    if(map[startingPointX+i][startingPointY].equals("X")){
                        return false;
                    }
                }
                break;

            case "reverse":
                for(int i = 0; i<length; i++){
                    if(map[startingPointX][startingPointY-i].equals("X")){
                        return false;
                    }
                }
                break;

            default:
                for(int i = 0; i<length; i++){
                if(map[startingPointX+i][startingPointY].equals("X")){
                    return false;
                }
            }
        }

        return true;
    }

    private static String[][] setUpBoard(String[][] board, Ship[] setOfBattleships, Ship[] setOfDestroyers){

        for(Ship S : setOfBattleships)
            placeShip(board, S);

        for(Ship D : setOfDestroyers)
            placeShip(board, D);

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
            if(in<0 || in>3){
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
        try {
            int shot = Integer.parseInt(quantity);

            if(shot>0 && shot<11)
            {
                return true;
            } else {
                System.out.println("Invalid Y Coordinate, Please Try Again!\n");
                return false;
            }

        } catch(NumberFormatException e){
            System.out.println("Invalid Y Coordinate, Please Try Again!\n");
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

    private static String getInput(int[] shot){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a Coordinate: ");

        int numb = 0;
        String choice = input.next();
        String column = choice.substring(0,1);
        Boolean xValid = true;

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
            default: System.out.println("Invalid X Coordinate, Please Try Again!");
                xValid = false;
                break;
        }


        if(xValid && shotValid(choice.substring(1,choice.length()))){
            shot[0] = numb;
            shot[1] = Integer.parseInt(choice.substring(1, choice.length()));
            shot[1]--;

            return (Integer.toString(shot[1]) + Integer.toString(shot[0]));

        }

        return "error";
    }

    private static Boolean checkSunk(HashSet fired, Ship[] battleships, Ship[] destroyers){

        HashSet<String> shipPosition;
        int battleshipsLeft = 0;
        int destroyersLeft = 0;

        for(int i = 0; i<battleships.length; i++){

            shipPosition = battleships[i].getCoord();

            if(fired.size() >= shipPosition.size() && fired.containsAll(shipPosition)){
                battleships[i].setIsAlive(false);
            } else {

            }
            if(!battleships[i].getIsAlive()){
                battleshipsLeft++;
            }
        }

        for(int i = 0; i<destroyers.length; i++){

            shipPosition = destroyers[i].getCoord();

            if(fired.size() >= shipPosition.size() && fired.containsAll(shipPosition)){
                destroyers[i].setIsAlive(false);
            } else {

            }

            if(!destroyers[i].getIsAlive()){
                destroyersLeft++;
            }
        }

        System.out.println("");
        System.out.println("SUNK LIST");
        System.out.println("Battleships sunk: " + battleshipsLeft + "/" + battleships.length);
        System.out.println("Destroyers sunk: " + destroyersLeft + "/" + destroyers.length);
        System.out.println("");

        if(battleshipsLeft == battleships.length && destroyersLeft == destroyers.length){
            return true;
        }

        return false;
    }

    private static void beginGame(String[][] board, String[][] answers, Ship[] battleships, Ship[] destroyers){

        Scanner input = new Scanner(System.in);
        int[] shot = new int[2];
        Boolean done = false;
        HashSet<String> fired = new HashSet<>();

        while(!done){
            showBoard(board);
            displayMenu();

            int choice = getMenuInput(input);
            if(choice == 1){
                String in = getInput(shot);
                if(!in.equals("error")){
                    fired.add(in);

                    if(fireShot(shot,answers)){
                        board[shot[1]][shot[0]]="H";

                    }else {
                        board[shot[1]][shot[0]] = "M";
                    }

                    done = checkSunk(fired, battleships, destroyers);
                } else {

                }


            }else if(choice == 2){
                showBoard(answers);

            }else if (choice == 3){
                done = true;
                System.out.println("Thanks For Playing");
            }
        }

        System.out.println("You Win!");

    }

    public static void main(String[] arg){

        int rows = 10;
        int columns = 10;
        int noBattleships = 1;
        int noDestroyers = 2;
        int battleshipLength = 5;
        int destroyerLength = 4;

        String[][] board = new String[rows][columns];
        String[][] playingBoard = new String[rows][columns];
        String[][] answers;

        Ship[] setOfBattleships = new Ship[noBattleships];
        Ship[] setOfDestroyers = new Ship[noDestroyers];

        for(int i = 0; i<noBattleships;i++){

            Ship B = new Ship();
            B.setLength(battleshipLength);
            setOfBattleships[i] = B;
        }

        for(int i=0; i<noDestroyers;i++){

            Ship D = new Ship();
            D.setLength(destroyerLength);
            setOfDestroyers[i] = D;
        }

        board = createBoard(board);

        answers = setUpBoard(board, setOfBattleships, setOfDestroyers);

        playingBoard = createBoard(playingBoard);

        beginGame(playingBoard, answers, setOfBattleships, setOfDestroyers);

    }
}
