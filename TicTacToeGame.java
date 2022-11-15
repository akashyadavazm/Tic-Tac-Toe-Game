package day18Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

	Scanner scan = new Scanner(System.in);
	static ArrayList<Integer> playerPosition = new ArrayList<>();
	static ArrayList<Integer> computerPos = new ArrayList<>();

	static String playerOption = "X";
	
	static char[][] gameBoard =  {{' ', '|', ' ', '|', ' '},
								  {'-', '+', '-', '+', '-'},
								  {' ', '|', ' ', '|', ' '},
								  {'-', '+', '-', '+', '-'},
								  {' ', '|', ' ', '|', ' '}};

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
        System.out.println("\n Welcome to Tic-Tac-Toe-Game\n");
        System.out.println(" This is your default game board 👇\n");
        printGameBoard(gameBoard);
        System.out.println();
        System.out.println(" Choose Your Player Option\n 1) Player-X \n 2) Player-O\n");
        int option = scan.nextInt();
        if(option == 1)
        	playerOption = "X";
        else
        	playerOption = "O";
        
        while(true) 
        {
        	if(playerOption.equalsIgnoreCase("X"))
                System.out.println(" Enter your X-placement as an integer from 1-9: \n");
        	else if(playerOption.equalsIgnoreCase("O"))
        		System.out.println(" Enter your O-placement as an integer from 1-9: \n");
        	
            int playerPos = scan.nextInt();
            while(computerPos.contains(playerPos) || computerPos.contains(computerPos)) {
                System.out.println(" Position taken! Enter another integer from 1-9: \n");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player", playerOption);
            String result = checkWinner();

            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(computerPos.contains(cpuPos) || computerPos.contains(cpuPos)) {
                System.out.println(" Position taken! Enter another integer 1-9: \n");
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu", playerOption);

            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) 
    {
        for(char[] row : gameBoard) 
        {
            for(char c : row) 
            {
                System.out.print(" "+c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user, String playerOption) 
    {

        char symbol = ' ';
        if(user.equals("player")) 
        {
        	if(playerOption.equalsIgnoreCase("X"))
                symbol  = 'X';
        	else if(playerOption.equalsIgnoreCase("O"))
        		symbol = 'O';
            computerPos.add(pos);
        }
        else 
        {
        	if(playerOption.equalsIgnoreCase("X"))
                symbol  = 'O';
        	else if(playerOption.equalsIgnoreCase("O"))
        		symbol = 'X';
            computerPos.add(pos);
        }

        switch(pos) 
        {
            case 1:
                gameBoard[0][0] = symbol;
                break;

            case 2:
                gameBoard[0][2] = symbol;
                break;

            case 3:
                gameBoard[0][4] = symbol;
                break;

            case 4:
                gameBoard[2][0] = symbol;
                break;

            case 5:
                gameBoard[2][2] = symbol;
                break;

            case 6:
                gameBoard[2][4] = symbol;
                break;

            case 7:
                gameBoard[4][0] = symbol;
                break;

            case 8:
                gameBoard[4][2] = symbol;
                break;

            case 9:
                gameBoard[4][4] = symbol;
                break;

            default:
                break;
        }
    }

    public static String checkWinner() 
    {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> middleRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);

        List<Integer> leftColumn = Arrays.asList(1, 4, 7);
        List<Integer> middleColumn = Arrays.asList(2, 5, 8);
        List<Integer> lastColumn = Arrays.asList(3, 6, 9);

        List<Integer> diagonalRight = Arrays.asList(1, 5, 9);
        List<Integer> diagonalLeft = Arrays.asList(3, 5, 7);

        List<List> winningPositions = new ArrayList<List>();
        winningPositions.add(topRow);
        winningPositions.add(middleRow);
        winningPositions.add(bottomRow);

        winningPositions.add(leftColumn);
        winningPositions.add(middleColumn);
        winningPositions.add(lastColumn);

        winningPositions.add(diagonalRight);
        winningPositions.add(diagonalLeft);

        for(List l : winningPositions) 
        {
            if(computerPos.containsAll(l))
            {
                printGameBoard(gameBoard);
                System.out.println();
                return " 😃 Congratulations Player-"+playerOption+", you won Tic-Tac-Toe !!!";
            }
            else if(computerPos.containsAll(l)) 
            {
                return " Sorry you lost 👎 Computer Wins the Game!";
            }
            else if(computerPos.size() + computerPos.size() == 9) 
            {
                return " Its a Tie Game !";
            }
        }
        return "";
    }

}