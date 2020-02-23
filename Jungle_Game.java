/*
 * @author Matthew O'Neill
 * moneil13
 * @date 12/5/2018
 * @version Java JDK 10.0.2
 * 
 * Description: This program is a jungle video game, where the user can add and remove animals of their
 * choice to a 2-D grid environment.  Then, the user can navigate around the map, looking for the 
 * animals they placed into the world. 
 * 
 * Pseudocode:
 * Flow of control:
 * 
 * Create static boolean array for exploredOrNot
 * Create static count variable
 * Main Method:
 * 		Display menu
 * 		Get game dimensions from user
 * 		Create world array, initialize it as all empty with "*"
 * 		Create animals in world array, initialize it all as empty with ""
 * 		Initialize boolean exploredOrNot array as all false
 * 		Create animalList array and initialize all as empty with ""
 * 		Print main menu
 * 		Get choice from user, make sure it is valid
 * 		If user picks 1, call insertAnimalIntoWord() method
 * 			Increment count by 1
 * 		If user picks 2, call removeAnimalsFromWorld() method
 * 			Decrement count by 1
 * 		If user picks 3: call explore() method
 * 
 * Method List:
 * 
 * makeWorld:
 * Takes in the world 2-D array and an x and y coordinate as arguments, and returns the
 * updated world array
 * 	Loop through each element in the 2-D array
 * 	Check the 2-D boolean array to see if it is explored or not and set each element to
 * 		either "O" for explored or "*" for unexplored
 * 	Set the user's position to "X", given the x and y coordinates
 * 	Return updated world array
 * 
 * printMainMenu:
 * Simply prints out the main menu with 3 options.
 * 
 * printMoveMenu:
 * Simply prints out the move options.
 * 
 * insertAnimalToWorld:
 * Takes the animalLocations array as an argument and returns the updated animalLocations array
 * 	Get coordinates from user
 * 	Check to make sure coordinates are in bounds
 * 	Check to make sure position is not occupied by an animal already
 * 	Have user enter new coordinates if either is true
 * 	Get animal Name from user
 * 	Add animal to animalLocations array
 * 	Return updated array
 * 
 * removeAnimalFromWorld:
 * Takes in the animalLocations array as an argument and returns the updated animalLocations array
 * 	Get coordinates from user
 * 	Check to make sure coordinates are in bounds
 * 	Check to make sure position is already occupied by an animal
 * 	Have user enter new coordinates if either is true
 * 	Remove animal from animalLocations array
 * 	Return updated array
 * 
 * isEmptyBlock:
 * Takes in the animalWorld array and an x and y coordinate, returns boolean either T or F
 * 	Checks if array location at x,y is occupied, returns false if it is, true if it is not
 * 
 * updateObservedAnimals:
 * Takes the observedAnimals 1-D array and an animal String as arguments, returns the updated
 * 		observedAnimals array
 * Inserts animal at index count
 * Returns updated array
 * 
 * move:
 * Takes the world array, an x and y coordinate, observedAnimals array and animalLocations array
 * 		as arguments and returns the updated world array
 * Sets boolean exploredArray to true at the location x,y
 * Call makeWorld function to update world
 * Check to make sure animalLocation is unoccupied
 * 	If it is, add animal at location to observedAnimal list at index count
 * 	Print observed animals by calling printObservedAnimals() method
 * 
 * explore:
 * Takes world array, animalLocations array, and observedAnimals array as arguments, is a void method
 * 	Set x and y to 0
 * 	Call makeWorld method
 * 	While user chooses not to exit, print world and display movement menu
 * 		Get movement choice from user
 * 		Use switch statement to divert logic to different movement options
 * 		If user picks movement direction:
 * 			Check to make sure movement is in bounds
 * 			Call makeWorld method with updated position and explored variables
 * 			Display updated world
 * 			Display if user finds an animal and list all found animals
 * 		If user picks to display found animals, display animal list
 * 		End movement if the choose to exit
 * 
 * printObservedAnimals
 * Takes in observedAnimals array as argument
 * 	Simply prints all animals in the observed animals array
 * 
 * isExplored:
 * Checks the boolean exploredOrNot array and returns a boolean T/F,
 * 		true if explored, false if unexplored
 * 
 */
import java.util.Scanner;
import java.util.Arrays;

public class Jungle_Game {
	//Create static boolean array and static count variable
	static boolean[][] exploredOrNotArray;
	static int count = 0;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//Display main menu
		System.out.println("Welcome to the jungle creator!");
		System.out.println("In this game, you will be inserting animals in certain places in the world");
		System.out.println("that you create! You will also be allowed to remove animals from certain");
		System.out.println("locations before you start exploring!");
		System.out.println("Once you start exploring you will navigate around the world to observe the");
		System.out.println("animals you have inserted.");
		System.out.println("The game will keep track of all the animals you have observed!");
		System.out.println("Enter the dimensions of your jungle!");
		
		//Get game dimensions from user
		System.out.println("Enter length:");
		int gameLength = input.nextInt();
		
		System.out.println("Enter width:");
		int gameWidth = input.nextInt();
		
		
		//Create and initialize values for world array
		String[][] worldArray = new String[gameLength][gameWidth];
		
		for(int i = 0; i < worldArray.length; i++) {
			for(int j = 0; j < worldArray[0].length; j++) {
				worldArray[i][j] = "*";
			}
			
		}
		//Create and assign initial values for animals in world array
		String[][] animalsInWorldArray = new String[gameLength][gameWidth];
		
		for(int i = 0; i < animalsInWorldArray.length; i++) {
			for(int j = 0; j < animalsInWorldArray[0].length; j++) {
				animalsInWorldArray[i][j] = "";
			}
		}
		
		//Create and initialize initial values for animalList array
		String[] animalList = new String[gameLength * gameWidth];
		
		for(int i = 0; i < animalList.length; i++) {
			animalList[i] = "";
		}
		
		//Initialize values for explored array
		exploredOrNotArray = new boolean[gameLength][gameWidth];
		
		for(int i = 0; i < exploredOrNotArray.length; i++) {
			for(int j = 0; j < exploredOrNotArray[0].length; j++) {
				exploredOrNotArray[i][j] = false;
			}
		}
		exploredOrNotArray[0][0] = true;
		int choice = 0;
		
		while(choice != 9) {
			//Print main menu
			printMainMenu();
			//Get choice from user
			choice = input.nextInt();
			
			//Control main menu with switch statement
			switch(choice) {
			case 1:
				//Call insertAnimal method to insert animal
				animalsInWorldArray = insertAnimalToWorld(animalsInWorldArray);
				//Increment count
				count++;
				break;
			case 2:
				//Call removeAnimals method
				animalsInWorldArray = removeAnimalFromWorld(animalsInWorldArray);
				//Decrement count
				count--;
				break;
			case 3:
				//Call exlore method
				explore(worldArray, animalsInWorldArray, animalList);
				break;
			default:
				//Make sure choice is valid
				System.out.println("Invalid choice, choose again.");
				break;
			}
			//FIXME
			System.out.println("The observed animal list is");
			printObservedAnimals(animalList);
		}
	}
	
	public static String[][] makeWorld(String[][] world, int posX, int posY){
		for(int i = 0; i < world.length; i++) {
			for(int j = 0; j < world[0].length; j++) {
				//Set element to explored by checking boolean array
				if(exploredOrNotArray[i][j] == true) {
					world[i][j] = "O";
				}
				//Else set to unexplored
				else {
					world[i][j] = "*";
				}
			}
		}
		//Set current position
		world[posX][posY] = "X";
		//Return updated array
		return world;
	}
	//Simply print main menu
	public static void printMainMenu() {
		System.out.println("1. Insert an animal into the world");
		System.out.println("2. Remove an animal from the world");
		System.out.println("3. Explore the world");
		
	}
	//Simply print movement menu
	public static void printMoveMenu() {
		System.out.println("W. Move Up");
		System.out.println("A. Move Left");
		System.out.println("S. Move Down");
		System.out.println("D. Move Right");
		System.out.println("I. Display observed animals");
		System.out.println("E. Exit");
		
	}
	
	public static String[][] insertAnimalToWorld(String[][] animalLocations) {
		Scanner input = new Scanner(System.in);
		//Get movement coordinates from user
		System.out.println("Enter the length coordinate for the animal:");
		int x = input.nextInt();
		System.out.println("Enter the width coordinate of the animal:");
		int y = input.nextInt();
		
		//Check to make sure movement is in bounds, get neew coordinates if it is out of bounds
		while((x > animalLocations.length - 1) || x < 0 || y < 0 || y > (animalLocations[0].length - 1)) {
			System.out.println("Sorry, location is not in game, please choose new coordinates:");
			System.out.println("Enter the x coordinate for the animal:");
			x = input.nextInt();
			System.out.println("Enter the y coordinate of the animal:");
			y = input.nextInt();
		}
		
		//Check to make sure animalLocation is empty, get new coordinates if it is occupied
		while(isEmptyBlock(animalLocations, x, y) == false) {
			System.out.println("Sorry, that space is already occupied by an animal, choose another position:");
			System.out.println("Enter the length coordinate for the animal:");
			x = input.nextInt();
			System.out.println("Enter the width coordinate of the animal:");
			y = input.nextInt();
		}
		//Get name of animal from user
		System.out.println("Enter the name of the animal:");
		String animal = input.next();
		//Add animal to array
		animalLocations[x][y] = animal;
		//Return updated array
		return animalLocations;
	}
	
	public static String[][] removeAnimalFromWorld(String[][] animalLocations) {
		Scanner input = new Scanner(System.in);
		//Get coordinates from user
		System.out.println("Enter the x coordinate for the animal:");
		int x = input.nextInt();
		System.out.println("Enter the y coordinate of the animal:");
		int y = input.nextInt();
		
		//Check to make sure coordinates are in bounds/ get new coordinates if out of bounds
		while((x > animalLocations.length - 1) || x < 0 || y < 0 || y > (animalLocations[0].length - 1)) {
			System.out.println("Sorry, location is not in game, please choose new coordinates:");
			System.out.println("Enter the x coordinate for the animal:");
			x = input.nextInt();
			System.out.println("Enter the y coordinate of the animal:");
			y = input.nextInt();
		}
		//Check to make sure animalLocation is empty, get new coordinates until it is
		while(isEmptyBlock(animalLocations, x, y) == true) {
			System.out.println("Sorry, that space does not have an animal. Choose another location:");
			System.out.println("Enter the x coordinate for the animal:");
			x = input.nextInt();
			System.out.println("Enter the y coordinate of the animal:");
			y = input.nextInt();
		}
		//Replace location with ""
		animalLocations[x][y] = "";
		//Return updated array
		return animalLocations;
	}
	
	//Check to make sure animal slot is empty
	public static boolean isEmptyBlock(String[][] animalWorld, int x, int y) {
		//return true if empty
		if(animalWorld[x][y] == "") {
			return true;
		}
		//return false if filled
		else {
			return false;
		}
	}
	//place animal in observedAnimal array at position count
	public static String[] updateObservedAnimals(String[] observedAnimals, String animal) {
		observedAnimals[count] = animal;
		//Return updated array
		return observedAnimals;
		
	}
	//Print the world array in a tabular form
	public static void printWorld(String[][] world) {
		for(int i = 0; i < world.length; i++) {
			System.out.println("\n");
			for(int j = 0; j < world[0].length; j++) {
				System.out.print(world[i][j] + " ");
			}
		}
		System.out.println("\n");
	}
	
	public static String[][] move(String[][] world, int x, int y, String[] observedAnimals, String[][] animalLocations) {
		//Set position
		exploredOrNotArray[x][y] = true;
		//call makeWorld function
		world = makeWorld(world, x, y);

		//Check to make sure animal slot is occupied
		if(animalLocations[x][y] != "") {
			//Place animal in observed animal list
			//FIXME
			observedAnimals[count] = animalLocations[x][y];
			//print observed animals
			printObservedAnimals(observedAnimals);
		}
		//return updated array
		return world;
	}
	

	public static void explore(String[][] world, String[][] animalLocations, String[] observedAnimals) {
		Scanner input = new Scanner(System.in);
		char moveChoice = 'A';
		int x = 0;
		int y = 0;
		
		world[x][y] = "X";
		//Make initial world
		world = makeWorld(world, x, y);
		
		//While user does not want to quit, keep movint
		while(moveChoice != 'E' && moveChoice != 'e') {
			//display world
			printWorld(world);
			//display menu
			printMoveMenu();
			//get choice grom user
			moveChoice = input.next().charAt(0);
			
			//Control choices
			switch(moveChoice) {
			case 'W':
			case 'w':
				//Check bounds
				if((x-1) < 0) {
					System.out.println("Sorry, that is out of bounds, choose move again.");
					break;
				}
				//Change position
				x--;
				world = move(world, x, y, observedAnimals, animalLocations);
				break;
			case 'A':
			case 'a':
				//Check bounds
				if((y - 1) < 0) {
					System.out.println("Sorry, that is out of bounds, choose move again.");
					break;
				}
				//Change position
				y--;
				world = move(world, x, y, observedAnimals, animalLocations);
				break;
			case 'S':
			case 's':
				//Check bounds
				if((x+1) > world.length - 1) {
					System.out.println("Sorry, that is out of bounds, choose move again.");
					break;
				}
				//Move position
				x++;
				world = move(world, x, y, observedAnimals, animalLocations);
				break;
			case 'D':
			case 'd':
				//Check bounds
				if((y + 1) > world[0].length - 1) {
					System.out.println("Sorry, that is out of bounds, choose move again.");
					break;
				}
				//Change position
				y++;
				world = move(world, x, y, observedAnimals, animalLocations);
				break;
			case 'I':
			case 'i':
				//Display observed animals
				printObservedAnimals(observedAnimals);
				break;
			case 'E':
			case 'e':
				//Exit
				break;
			default:
				//Validate input
				System.out.println("Sorry, that movement is not allowed, choose again.");
				break;
			}
			
			moveChoice = Character.toUpperCase(moveChoice);
			//If user moves, and if slot is occupied, display name of found animal
			if(moveChoice == 'W' || moveChoice == 'A' || moveChoice == 'S' || moveChoice == 'D') {
				if(animalLocations[x][y] != "") {
					System.out.println("You have encountered a/an " + animalLocations[x][y]);
				}
			}
		}
	}
	//Simply print all observed animals
	public static void printObservedAnimals(String[] observedAnimals) {
		System.out.println("Observed Animals:");
		//should be count, not length FIXME
		//observedAnimals.length
		for(int i = 0; i < count; i++) {
			System.out.println(observedAnimals[i]);
		}
	}
	
	//return true if element in exploredArray is true, false if unexplored
	public static boolean isExplored(int x, int y) {
		if(exploredOrNotArray[x][y] == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
