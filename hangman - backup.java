
import java.io.*;
import java.util.*;

class hangman {
	
	//Global array list. Loading all the words from the files to it and 
	//allowing the program to randomly pick-up one of the index numbers
	public static List<String> lines = new ArrayList<String>();
	public static int life = 6;
	public static String Guesses = "";

	//Method used to connect all the methods.
    public static void main(String[] args) {
        Scanner kb = new Scanner (System.in); // Collecting user input
        int counter = 0;
        
		introduction(); // Start game screen
        menu(); // Game's menu
        
        //Collecting player's name
        System.out.print("\nPlayer's name:");
        String playersName = kb.nextLine(); // Assigning User input
      
        
        //Selecting level of difficulty
        System.out.println("Select level of difficulty"); // User message
        System.out.println("1) Easy"); // Levels  easy, medium and hard.
        System.out.println("2) Medium"); 
        System.out.println("3) Hard");
        
        //Checking user input
        while (counter !=1){
            char menuOption = kb.next().charAt(0); // Restricting user input to char 1,2 or 3
            if(menuOption == '1'){
                easy();		// Moving to desired level
                counter++;	//Ending looping
            }else if(menuOption == '2'){
                medium();	//Moving to desired level
                counter++;	//Ending looping
            }else if (menuOption == '3'){
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
        Scanner kb = new Scanner(System.in); // Collecting user input
          
        System.out.println("Press ENTER to continue."); //Welcome message
        kb.nextLine(); // User hitting any key will move on the code
    }
    
	//Main menu
    public static void menu (){
        
        Scanner kb = new Scanner (System.in); //Collecting user input
              
        System.out.println("1) New Game"); // Start game
        System.out.println("2) Credits"); // My details
        System.out.println("3) Quit"); // Exit the game
       
		char menuOption = kb.next().charAt(0); // Collecting user input
        if (menuOption == '1'){	//Restricting user input to 1,2 or 3
           return;	// Return to the main method
        }else if(menuOption == '2'){
            credits(); // Access My Deatails
        }else if (menuOption == '3'){
            System.exit(0); // Exiting the game
        }
    }
    
	// My Credits
    public static void credits(){
        Scanner kb = new Scanner (System.in); // Collecting user input
        //A thanks message for the user
		System.out.println("\n\n      Thank you for playing the 'ZombieDice' game. I hope that you have enjoyed!");
		System.out.println("\n\n      My name is Evandro and I'm a young program developer, coursing Griffith College.\n      I wish to become in the future a game designer and have my own game studio (Ramisar Studios) so I hope that you \n      find my code clear and easy to understand. \n      Sorry by any mistake but I'm still practing, thanks!");
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
   
   
	public static void game(){
		
		Random wordRandom = new Random();
		String randomString = lines.get(wordRandom.nextInt(lines.size()));	
		int counter = 0;
		int score = 0;
		char userOption = 0;
		Scanner kb = new Scanner (System.in);
		char userGuess;
		boolean correct = false;
		
		
		
		// 2d Array organized in: two rows and 'x' columns for each character 
		// First Row: dashes 
		// Second Row: characters from the random string
		char [][] dashWord = new char [2][randomString.length()];
		
		for (counter = 0; counter < randomString.length(); counter++){
			
			dashWord[0][counter] = '-';
			dashWord[1][counter]=randomString.charAt(counter);
			
		}
		
		

		
		while(life > 0){
		
		//Calling hangman drawing
		hangman();
				
			//Printing message
			System.out.println("\nGuess the word:");
			System.out.println(dashWord[1]); // TEST ////
			
			//Printing Random word dashed
			System.out.println(dashWord[0]);
			
			//Collecint player's input
			userGuess = kb.next().charAt(0);
			
			//Player's looping, checking characters 
			while(counter != 1){
				
				// Allowing only lower case characters
				if(userGuess < 97 || userGuess > 122){ 
					
					//Printing message
					System.out.println("Please provide a lower case letter!\n");
					
					//Collecting new input from player
					userGuess = kb.next().charAt(0);
				
				}else {
					//Finishing looping
					counter++;
				}				
				
			}
			
			
			
			
			
			
			
			Guesses = userGuess + " " + Guesses ;
			//Looping for checking user input and character in the string
			for (counter = 0; counter < randomString.length(); counter++){
				
				if(userGuess == dashWord[0][counter]){
					System.out.println("\n\nRepeated character!");
					correct = true;
				}else if (userGuess == dashWord[1][counter]){
					dashWord[0][counter] = userGuess;
					correct = true;
					score++;
				}
			}
			
			if (correct == true){
				correct = false;
			}else if (correct ==false){
				life--;
			}
			
			
		
						
			//Checking WIN Condition
			if (score == randomString.length()){
				System.out.println("Congratulations!");
				System.out.println("Would like to play again?");
				System.out.println("1) Yes    2)No");
				userOption = kb.next().charAt(0);
				if (userOption == '1'){
					life = 6; //Setting up variables for a new game
					Guesses = ""; // Setting up variables for a new game
					game();
				}else if(userOption == '2'){
					System.exit(0);
				}else {
					System.out.println("Please provide one of the options above.");
				}
				
			}
			
			// Checking GAMEOVER Condition
			if(life == 0){
				hangman();
				System.out.println("\n\nGAME OVER!");
				System.out.println("Would like to play again?");
				System.out.println("1) Yes    2)No");
				
				while(userOption!= '1' || userOption!= '2'){
					userOption = kb.next().charAt(0);
					if (userOption == '1'){
						life = 6; //Setting up variables for a new game
						Guesses = ""; // Setting up variables for a new game
						game();
					}else if(userOption == '2'){
						System.exit(0);
					}else {
						System.out.println("Please provide one of the options above."); // !!!! Need to correct
						
					}
				}
			}
		}
	}
	
	
	
	public static void easy(){
        
		Scanner inFile = null;
		
		try{
			inFile= new Scanner(new File("easy.txt"));
		}
			
        catch(Exception e){
            System.out.println("Could not find the file");
        }
		
		String line = "";
        
		while( inFile.hasNextLine()){
			
			line = inFile.nextLine();
			lines.add(line);
			}
        
	}
    
	public static void medium(){
	
	Scanner inFile = null;
		
		try{
			inFile= new Scanner(new File("medium.txt"));
		}
			
        catch(Exception e){
            System.out.println("Could not find the file");
        }
		
		String line = "";
        
		while( inFile.hasNextLine()){
			
			line = inFile.nextLine();
			lines.add(line);
		}
	
	}
	
	public static void hard(){
	
	Scanner inFile = null;
		
		try{
			inFile= new Scanner(new File("easy.txt"));
		}
			
        catch(Exception e){
            System.out.println("Could not find the file");
        }
		
		String line = "";
        
		while( inFile.hasNextLine()){
			
			line = inFile.nextLine();
			lines.add(line);
			}
	
	
	}

	// Hangman drawing
	public static void hangman(){
		
		// Counter
		int counter = 0; 
		
		// Looping for printing hangman drawing 
		for (counter = 0; counter < 1 ; counter++){ 
		
				System.out.println("\n\n   ____");
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



/*
     |/|
     |/|
     |/|
     |/|
     |/|
     |/|
     |/| /¯)
     |/|/\/
     |/|\/
    (¯¯¯)
    (¯¯¯)
    (¯¯¯)
    (¯¯¯)
    (¯¯¯)
    /¯¯/\
   / ,^./\
  / /   \/\
 / /     \/\
( (       )/)
| |       |/|
| |       |/|
| |       |/|
( (       )/)
 \ \     / /
  \ `---' /
   `-----'    
   */
