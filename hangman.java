
import java.io.*;
import java.util.*;


class hangman {
	
	//Global array list. Loading all the words from the files to it and 
	//allowing the program to randomly pick-up one of the index numbers
	public static List<String> lines = new ArrayList<String>();
	
	/*GLOBAL VARIABLES*/
	
	public static int life = 6;		// Player's life
	public static int marker = 0; 	// Pontuation defined by the games difficulty
	public static int turn = 0;		// Counter used for score points to the player 
		
		
	public static String Guesses = "";		// Player's guess
	public static String playersName ="";	// Player's name

	//Method used to connect all the methods.
    public static void main(String[] args) {
        Scanner kb = new Scanner (System.in); // Collecting user input
        int counter = 0;	// Counter used in the menu 
        
		introduction(); 	// Start game screen
        menu(); 			// Game's menu
        
        
        System.out.print("\nInsert your name:");	//Collecting player's name
        playersName = kb.nextLine(); 				// Assigning User input
		
		//Instructions
		System.out.println(" _____________________________________________________________________");
		System.out.println("|                          Instructions                               |");
		System.out.println("|                                                                     |");
		System.out.println("|* Your player name will be used for the ranking system.              |");
		System.out.println("|* Each level of difficulty has a different punctuation for the score.|");
		System.out.println("|  * The points are given for each word successfully discovered!      |");
		System.out.println("|* Try to guess the correct letter, avoiding losing all your lives!   |");
		System.out.println("|* You have a total of 6 lives.                                       |");
		System.out.println("|* Enjoy and Good Luck!                                               |");
		System.out.println("|_____________________________________________________________________|");
		
		System.out.println("\n                     Press ENTER to continue."); 
        
		
		kb.nextLine(); 	// User hitting any key will move on the program 
		        
        //Selecting level of difficulty
        System.out.println("Select level of difficulty"); 	// User message
        System.out.println("1) Easy -   1 point"); 			// Levels  easy, medium and hard.
        System.out.println("2) Medium - 3 points"); 		// Each level with a different punctuation
        System.out.println("3) Hard -   6 points");
        
        //Checking user input for the level of difficulty
        while (counter !=1){
            char menuOption = kb.next().charAt(0); // Restricting user input to char 1,2 or 3
            if(menuOption == '1'){
                marker = 1;	//Assigning the base marker
				easy();		// Moving to desired level
                counter++;	//Ending looping
            }else if(menuOption == '2'){
				marker = 3;	//Assigning the base marker
                medium();	//Moving to desired level
                counter++;	//Ending looping
            }else if (menuOption == '3'){
				marker = 6;	//Assigning the base marker
                hard();		//Moving to desired level
                counter++;	//Ending looping
            }else{
                System.out.println("Please provide one of the options above."); // Error message for different chars inputted
            }
        }
		
		
		game(); // Game method

    }
    
	//Introduction Screen
    public static void introduction (){
	
	
		// Start Game Screen

		System.out.println(" 	    	  	     |\\|");
		System.out.println(" 	    	  	     |\\|");
		System.out.println(" 	   	  	     |\\|");
		System.out.println(" 	   	  	     |_|");
		System.out.println(" 	   	  	    (___)");
		System.out.println(" 	   	  	    (___)");
		System.out.println(" 	   	  	    (___)");
		System.out.println(" 	   	  	    (___)");
		System.out.println(" _   _   	   	  ,(/   \\),");
		System.out.println("| | | |      	   	 ('/     \\')");
		System.out.println("| |_| |  __ _  _ __  	('/       \\')   _ __ ___    __ _  _ __");
		System.out.println("|  _  | / _` || '_ \\  	|/|       |/|  | '_ ` _ \\  / _` || '_ \\");
		System.out.println("| | | || (_| || | | | 	|/|       |/|  | | | | | || (_| || | | |");
		System.out.println("\\_| |_/ \\__,_||_| |_| 	(-\\       /-)  |_| |_| |_| \\__,_||_| |_|");
		System.out.println(" 	   	  	 \\-\\     /-/");
		System.out.println(" 	   	  	  \\-\\___/-/");
		System.out.println(" 	   	  	   '-----'");
		System.out.println(" 	   	  	      |/|");
		System.out.println("                           ___/-|");
		System.out.println(" 	   	          '-----'");
	
	      


		// Collecting player's input
        Scanner kb = new Scanner(System.in); 
         
		//Printing welcome message
        System.out.println("\n                   Press ENTER to continue."); 
        
		// User hitting any key will move on the code
		kb.nextLine(); 
    }
    
	//Main menu
    public static void menu (){
        
        Scanner kb = new Scanner (System.in); 	//Collecting player input
              
        System.out.println("1) New Game"); 		// Start game
        System.out.println("2) Credits"); 		// My details
        System.out.println("3) Ranking"); 		// My details
		System.out.println("4) Quit"); 			// Exit the game
       
		char menuOption = kb.next().charAt(0); 	// Collecting user input
        if (menuOption == '1'){					// Restricting user input to char 1,2 or 3
           return;								// Returning to the main method
        }else if(menuOption == '2'){
            credits(); 							// Accessing My Deatails
        }else if(menuOption =='3'){
			ranking();							// Accessing Ranking Menu
		} 		
		else if (menuOption == '4'){
            System.exit(0); 					// Exiting the game
        }
    }
    
	// Game method used for all the code for the ingame
	public static void game(){
		
		/* Variables used*/
		
		Random wordRandom = new Random();	// Creating random variable
		String randomString = lines.get(wordRandom.nextInt(lines.size()));	// Using Random variable to get a random integer to be used in my ArrayList

		int counter = 0;	//Counter used for different loopings 
		int score = 0;		//Variable used to compute players correct answers
		int rankingScore = marker * turn;	// User's ranking score
		
		char userOption = 0; 				// Collecting players option for Winning/Game Over condition
		char userGuess;						// Player guess 
		
		Scanner kb = new Scanner (System.in);// Holding users input option 
		
		boolean correct = false;			// Boolean used to check user's answer. Correct or not
		
		Date date= new Date();				// Getting the local date
		
		PrintWriter outFile = null;			// Setting OutFile 
		
		try {
			outFile = new PrintWriter(new FileWriter("ranking.txt",true)); // Creating file "ranking.txt". Allowing it to appending
		}
	
		catch(IOException e){	//Setting error msg for file not found
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
				
		// 2d Array organized in: two rows and 'x' columns for each character 
		// First Row: dashes 
		// Second Row: characters from the random string
		char [][] dashWord = new char [2][randomString.length()];
		
		for (counter = 0; counter < randomString.length(); counter++){
			
			dashWord[0][counter] = '-';
			dashWord[1][counter]=randomString.charAt(counter);
		}
		
			
		while(life > 0){	//Looping to hold the player in the game, trying to guess the correct letter.
		
		//Top br, showing user's name and score 
		System.out.println("| Player:" + playersName + "             Score:" + rankingScore+" |");
		
			
		hangman();	//Calling hangman drawing
				
			System.out.println("      \nGuess the word:");	// Printing message
			
			System.out.print(dashWord[0]);		// Printing Random word dashed - I couldnt print it in the same line above 
			userGuess = kb.next().charAt(0);	// Collecint player's input
			
			while(counter != 1){				// Player's looping, checking characters 
				
				if(userGuess < 97 || userGuess > 122){ // Allowing only lower case characters
					
					System.out.println("Please provide a lower case letter!\n");	// Printing error message
					
					userGuess = kb.next().charAt(0);	// Collecting new input from player
				
				}else {
					
					counter++;	// Finishing looping
				}				
				
			}
			
			Guesses = userGuess + " " + Guesses ; //Assigning player guesses to be displayed during the game
			
			//Looping for checking user input and character in the string
			for (counter = 0; counter < randomString.length(); counter++){
				
				if(userGuess == dashWord[0][counter]){					// Checking player guess if it's a repeated character
					System.out.println("\n\nRepeated character!");
					correct = true;										// Avoiding player to lose lives
				}else if (userGuess == dashWord[1][counter]){			// Checking player guess if its correct
					dashWord[0][counter] = userGuess;					// Updating dashed array with players correct answer
					correct = true;										// Scoring the player
					score++;
				}
			}
			
			if (correct == true){			// Checking answers
				correct = false;			//  If correct, reseting the bolean loggic
			}else if (correct ==false){		// If wrong, player will lose one life
				life--;
			}
						
			//Checking WINNNING Condition
			if (score == randomString.length()){
				turn++;		//Increasing the turn will comput score for the user
				
				System.out.println("Congratulations!");				// Printing message
				System.out.println("Would like to play again?");
				System.out.println("1) Yes    2) No");
				userOption = kb.next().charAt(0);					// Collecting user next character
				if (userOption == '1'){
					life = 6; 		// Setting up variables for a new game
					Guesses = ""; 	// Setting up variables for a new game
					game();			// Calling game method
				}else if(userOption == '2'){
					rankingScore = marker * turn;	//Storing player's score of the game
			
					/*Saving user's score on ranking.txt*/
					outFile.println(" " + rankingScore + ".............." + playersName +"....."+ date );
					outFile.close();		// Closing and saving file
				
					menu();		// Calling menu method
				}else {
					System.out.println("Please provide one of the options above."); // Error message
				}
				
			}
			
			// Checking GAMEOVER Condition
			if(life == 0){
				
				// Top menu showing players name and score
				System.out.println("| Player:" + playersName + "             Score:" + rankingScore+" |");
				
				hangman();	// Calling hangman draw
				
				/*Saving user's score on ranking.txt*/
				outFile.println(" " + rankingScore + ".............." + playersName +"....."+ date );
				outFile.close(); 		// Closing and saving file
				
				System.out.println("\n\nGAME OVER!");				// Printing message
				System.out.println("Would like to play again?");
				System.out.println("1) Yes    2) No");
				
				while(userOption!= '1' || userOption!= '2'){
					userOption = kb.next().charAt(0);		// Collecting players input
					if (userOption == '1'){
						turn = 0;		// Starting a new game
						life = 6; 		// Starting a new game
						Guesses = ""; 	// Starting a new game
					}else if(userOption == '2'){
						menu();			// Calling menu method
					}else {
						System.out.println("Please provide one of the options above."); // Error message
						
					}
				}
			}
		}
	}
	
	// Ranking method
	public static void ranking(){
		
		// Creating ArrayList 
		List<String> rankingList = new ArrayList<String>();
		
		Scanner inFile = null;
		
		try{
			inFile= new Scanner(new File("ranking.txt")); 	// Reading rile "ranking.txt"
		}
			
        catch(Exception e){									// Error message for file not found
            System.out.println("Could not find the file");
        }
		
		String rankPosition ="";				// String used to assign each line in the file
		
		while(inFile.hasNextLine()){			// Looping checking the lines in the file
			
			rankPosition = inFile.nextLine();	// Assigning lines to the String  
			rankingList.add(rankPosition);		// Adding string to the ArrayList
		}

		//Printing top screen showing ranking details
		System.out.println("\n|******************** Ranking List ***********************|\n");
		System.out.println("  SCORE                 NAME                     DATE       \n");
		
		
		for (int i = 0; i < rankingList.size(); i++){	// Looping to check all the possible lines in the list
			
			System.out.println(rankingList.get(i));		// Printing lines from the ArrayList
			
		}
				
		
		
		
		/*
		
		
		
		 **** SOME OF MY ATTEMPTS TRYING TO SORT THE RANKING LIST BY DESCENDING ORDER ****
		 *** Unfortunately took me so long so I decided to move on with my other assignments 
		*** Eoin could you please email me later with some solution for it, please? Thanks! ***
			
				Collections.sort(rankingList);
				
				for (int j=0;j == rankingList.size();j++){
				//char test = (rankingList.get(j).charAt(0));
				test = rankingList.get(0);
				System.out.println(rankingList().charAt(0));
				}
				
				
				int counter = 0;
			
			
				for (counter = 0; counter < rankingList.size(); counter++){
					
					System.out.println(rankingList.get(counter));
					
					
		*/
		
		
		
	menu();		// Calling menu method
	
	}
	
	// Easy file
	public static void easy(){
        
		Scanner inFile = null;	// Assigning infile to receive document
		
		try{
			inFile= new Scanner(new File("easy.txt"));	// Readind file "easy.txt"
		}
			
        catch(Exception e){
            System.out.println("Could not find the file");	// Error message for file not found
        }
		
		String line = "";		// Creating empty string
        
		while( inFile.hasNextLine()){ 	// Looping to check each line in the file 
			
			line = inFile.nextLine();	// Assigning each line to the string "line"
			lines.add(line);			// Adding each string to the ArrayList
			}
        
	}
    
	public static void medium(){
	
	Scanner inFile = null;		// Assigning infile to receive document
		
		try{
			inFile= new Scanner(new File("medium.txt"));	// Readind file "medium.txt"
		}
			
        catch(Exception e){
            System.out.println("Could not find the file");	// Error message for file not found
        }
		
		String line = "";		// Creating empty string
        
		while( inFile.hasNextLine()){		// Looping to check each line in the file 
				
			line = inFile.nextLine();		// Assigning each line to the string "line"
			lines.add(line);				// Adding each string to the ArrayList
		}
	
	}
	
	public static void hard(){
	
	Scanner inFile = null;		// Assigning infile to receive document
		
		try{
			inFile= new Scanner(new File("hard.txt"));		// Readind file "hard.txt"
		}
			
        catch(Exception e){
            System.out.println("Could not find the file");	// Error message for file not found
        }
		
		String line = "";		// Creating empty string
        
		while( inFile.hasNextLine()){		// Looping to check each line in the file 
			
			line = inFile.nextLine();		// Assigning each line to the string "line"
			lines.add(line);				// Adding each string to the ArrayList
			}
	}

		// My Credits
    public static void credits(){
     
		//A thanks message for the user
		System.out.println("\n\n      Thank you for playing the 'Hangman' game. I hope that you have enjoyed!");
		System.out.println("\n\n      My name is Evandro and I'm a young program developer, coursing Griffith College.\n      I wish to become in the future a game designer and have my own game studio (Ramisar Studios) so I hope that you \n      find my code clear and easy to understand. \n      Sorry by any mistake but I'm still practicing, thanks!");
		//My future studio
		System.out.println("        ____                 _                  ____  _             _ _");           
		System.out.println("       |  _ \\ __ _ _ __ ___ (_)___  __ _ _ __  / ___|| |_ _   _  __| (_) ___  ___ ");
		 System.out.println("       | |_) / _` | '_ ` _ \\| / __|/ _` | '__| \\___ \\| __| | | |/ _` | |/ _ \\/ __|");
		System.out.println("       |  _ < (_| | | | | | | \\__ \\ (_| | |     ___) | |_| |_| | (_| | | (_) \\__ \\");
		System.out.println("       |_| \\_\\__,_|_| |_| |_|_|___/\\__,_|_|    |____/ \\__|\\__,_|\\__,_|_|\\___/|___/");

		//Waiting key
		System.out.println("\n\n      Press any KEY to Return.");

		//User input
		Scanner kb = new Scanner (System.in);
		//Clear scanner
		kb.nextLine();
		//Back to menu method
		menu();
    }
	
	// Hangman drawing
	public static void hangman(){
		
		// Counter
		int counter = 0; 
		
		// Looping for printing hangman drawing 
		for (counter = 0; counter < 1 ; counter++){ 
		
				System.out.println("\n   ____");
				System.out.println("  |    |");
		
			// Checking player`s life
			if(life == 6){ 
				System.out.println("  |    ");
				System.out.println("  |    ");
				System.out.println("  |    ");
				System.out.println("  |    ");
			
			// Checking player`s life
			}if(life ==5){ 
				System.out.println("  |    o");
				System.out.println("  |    ");
				System.out.println("  |    ");
				System.out.println("  |    ");
			
			// Checking player`s life
			}if(life == 4){ 
				System.out.println("  |    o");
				System.out.println("  |   /");
				System.out.println("  |    ");
				System.out.println("  |    ");
			
			// Checking player`s life
			}if (life == 3){ 
				System.out.println("  |    o");
				System.out.println("  |   /|");
				System.out.println("  |    |");
				System.out.println("  |    ");
			
			// Checking player`s life
			}if(life ==2){ 
				System.out.println("  |    o");
				System.out.println("  |   /|\\");
				System.out.println("  |    |");
				System.out.println("  |    ");
		
			// Checking player`s life
			}if(life ==1){ 
				System.out.println("  |    o");
				System.out.println("  |   /|\\ ");
				System.out.println("  |    |");
				System.out.println("  |   /");
			
			// Checking player`s life
			}if(life == 0){ 
				System.out.println("  |    o");
				System.out.println("  |   /|\\ ");
				System.out.println("  |    |");
				System.out.println("  |   / \\");
			}
		
				System.out.println(" _|_    ");
				System.out.println("|   |______");
				System.out.println("|          |"); //Prnting message and player's guesses
				System.out.println("|__________|" +"   Guesses:"+ Guesses);
		}
		
		
	}
		
	
	
	
	
    
}



