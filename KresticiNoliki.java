//импорт из стандартной библиотеки, так же можно использовать java.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class KresticiNoliki {
// Два листа для сохранения позиций игрока и компъютера.
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		//массив чаров - игровое поле
		char[][] gameBoard = { 	{ ' ', '|', ' ', '|', ' ' }, 
								{ '-', '+', '-', '+', '-' },
								{ ' ', '|', ' ', '|', ' ' },
								{ '-', '+', '-', '+', '-' },
								{ ' ', '|', ' ', '|', ' ' } };

		printGameBoard(gameBoard);

		//запускается игровой цикл
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Vvedi chislo ot 1 do 9/enter a number from 1 to 9");
			int playerPos = scan.nextInt();
			//проверяем не занята ли клетка
			while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Kletka zanyata, vybery druguyu!/cell is busy choose another :)");
				playerPos = scan.nextInt();
			}
			placePiece(gameBoard, playerPos, "player");
			//проверяем есть ли победитель
			String result = checkWinner();
			//останавливаем игру если победитель найден
			if (result.length() > 0) {
				System.out.println(result);
				printGameBoard(gameBoard);
				break;
			}
			//ход компъютера
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			//проверяем что бы цпу не выбрал такую же клетку что и пользователь
			//если цпу выбрал ту же клетку запускаем цикл выбора свободной клетки
			while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {

				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPos, "cpu");
			//выводим в консоль игровое поле после хода игроков
			printGameBoard(gameBoard);
			//проверяем есть ли победитель
			result = checkWinner();
			//останавливаем игру если победитель найден
			if (result.length() > 0) {
				System.out.println(result);
				printGameBoard(gameBoard);
				break;
			}

		}

	}

	//метод выводит в консоль игровое поле 
	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	//метод вставляет вместо пробелов крестики и нолики, добавляет использованную ячейку в лист сохранения позиций из 9 и 10 строки
	public static void placePiece(char[][] gameBoard, int pos, String user) {

		char symbol = ' ';

		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if (user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		//меняем в игровом поле пробелы на Х или О
		switch (pos) {
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
	
	//метод проверки победителя
	public static String checkWinner() {
		//победные позиции
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(3, 5, 7);
		//лист с победными позициями
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		//проверяем содератся ли наши массивы в победных позициях 
		for (List l : winning) {
			if (playerPositions.containsAll(l)) {
				return "Ty pobedil!/You win!";
			} else if (cpuPositions.containsAll(l)) {
				return "Ty proigral!/You lose!";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "Nich'a / Draw in the game";
			}

		}

		return "";

	}
}
