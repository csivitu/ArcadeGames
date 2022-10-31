import java.util.*;
import java.util.stream.Collectors;

public class Hangman {

	//50 word list
	private final static List<String> words = List.of(
			"GUM", "SIN", "FOR", "CRY", "LUG", "BYE", "FLY",
			"UGLY", "EACH", "FROM", "WORK", "TALK", "WITH", "SELF",
			"PIZZA", "THING", "FEIGN", "FIEND", "ELBOW", "FAULT", "DIRTY",
			"BUDGET", "SPIRIT", "QUAINT", "MAIDEN", "ESCORT", "PICKAX",
			"EXAMPLE", "TENSION", "QUININE", "KIDNEY", "REPLICA", "SLEEPER",
			"TRIANGLE", "KANGAROO", "MAHOGANY", "SERGEANT", "SEQUENCE",
			"MOUSTACHE", "DANGEROUS", "SCIENTIST", "DIFFERENT", "QUIESCENT",
			"MAGISTRATE", "ERRONEOUSLY", "LOUDSPEAKER", "PHYTOTOXIC",
			"MATRIMONIAL", "PARASYMPATHOMIMETIC", "THIGMOTROPISM");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		printIntro();
		System.out.println("Enter your preferred level:\n1 (3-4 letter words)\n2 (5-6 letter words)\n3 (7-8 letter words)\n4 (9-11 letter words)\n5 (random)");
		int level = scan.nextInt();
		List<String> wordz = new ArrayList<String>();
		if (level == 1){
			for(int i=0; i<=13; i++){
				wordz.add(words.get(i));
			}
		}
		else if (level == 2){
			for(int i=14; i<=26; i++){
				wordz.add(words.get(i));
			}
		}
		else if (level == 3){
			for(int i=27; i<=37; i++){
				wordz.add(words.get(i));
			}
		}
		else if (level == 4){
			for(int i=38; i<=49; i++){
				wordz.add(words.get(i));
			}
		}
		else{
			wordz = words;
		}

		int[] usedWords = new int[50];
		int roundNumber = 1;
		int totalWords = wordz.size();
		boolean continueGame = false;

		do {
			if (roundNumber > totalWords) {
				System.out.println("\nYOU DID ALL THE WORDS!!");
				break;
			}

			int randomWordIndex;
			do {
				randomWordIndex = ((int) (totalWords * Math.random())) + 1;
			} while (usedWords[randomWordIndex] == 1);
			usedWords[randomWordIndex] = 1;

			boolean youWon = playRound(scan, wordz.get(randomWordIndex - 1));
			if (!youWon) {
				System.out.print("\nYOU MISSED THAT ONE.  DO YOU WANT ANOTHER WORD? ");
			} else {
				System.out.print("\nWANT ANOTHER WORD? ");
			}
			final String anotherWordChoice = scan.next();

			if (anotherWordChoice.toUpperCase().equals("YES") || anotherWordChoice.toUpperCase().equals("Y")) {
				continueGame = true;
			}
			roundNumber++;
		} while (continueGame);

		System.out.println("\nIT'S BEEN FUN!  BYE FOR NOW.");
	}

	private static boolean playRound(Scanner scan, String word) {
		char[] letters;
		char[] discoveredLetters;
		int misses = 0;
                boolean numeric=false;
		Set<Character> lettersUsed = new LinkedHashSet<>();//LinkedHashSet maintains the order of characters inserted

		String[][] hangmanPicture = new String[12][12];
		//initialize the hangman picture
		for (int i = 0; i < hangmanPicture.length; i++) {
			for (int j = 0; j < hangmanPicture[i].length; j++) {
				hangmanPicture[i][j] = " ";
			}
		}
		for (int i = 0; i < hangmanPicture.length; i++) {
			hangmanPicture[i][0] = "X";
		}
		for (int i = 0; i < 7; i++) {
			hangmanPicture[0][i] = "X";
		}
		hangmanPicture[1][6] = "X";

		int totalWordGuesses = 0; //guesses

		int len = word.length();
		letters = word.toCharArray();

		discoveredLetters = new char[len];
		Arrays.fill(discoveredLetters, '-');

		boolean validNextGuess = false;
		char guessLetter = ' ';

		while (misses < 10) {
			while (!validNextGuess) {
				printLettersUsed(lettersUsed);
				printDiscoveredLetters(discoveredLetters); 
                             
                             
				System.out.print("WHAT IS YOUR GUESS? ");
				var tmpRead = scan.next();
				 numeric = tmpRead.matches("-?\\d+(\\.\\d+)?");
				 if(numeric)
				 {
				     System.out.println("Please enter a word");
				 }
				guessLetter = Character.toUpperCase(tmpRead.charAt(0));
				if (lettersUsed.contains(guessLetter)) {
					System.out.println("YOU GUESSED THAT LETTER BEFORE!");
				} else if(guessLetter < 'A' || guessLetter > 'Z'){
					System.out.println("ENTER AN ALPHABET");
				} else {
					lettersUsed.add(guessLetter);
					totalWordGuesses++;
					validNextGuess = true;
				}
			}

			if (word.indexOf(guessLetter) >= 0) {
				//replace all occurrences in D$ with G$
				for (int i = 0; i < letters.length; i++) {
					if (letters[i] == guessLetter) {
						discoveredLetters[i] = guessLetter;
					}
				}
				//check if the word is fully discovered
				boolean isWordDiscovered = true;
				for (char discoveredLetter : discoveredLetters) {
					if (discoveredLetter == '-') {
						isWordDiscovered = false;
						break;
					}
				}
				if (isWordDiscovered) {
					System.out.println("YOU FOUND THE WORD!");
					return true;
				}

				printDiscoveredLetters(discoveredLetters);
				System.out.print("WHAT IS YOUR GUESS FOR THE WORD? ");
				final String wordGuess = scan.next();
				if (wordGuess.toUpperCase().equals(word)) {
					System.out.printf("RIGHT!!  IT TOOK YOU %s GUESSES!", totalWordGuesses);
					return true;
				} else {
					System.out.println("WRONG.  TRY ANOTHER LETTER.");
				}
			} else {
				misses = misses + 1;
				System.out.println("\n\nSORRY, THAT LETTER ISN'T IN THE WORD.");
				drawHangman(misses, hangmanPicture);
			}
			validNextGuess = false;
		}

		System.out.printf("SORRY, YOU LOSE.  THE WORD WAS %s", word);
		return false;
	}

	private static void drawHangman(int m, String[][] hangmanPicture) {
		switch (m) {
			case 1:
				System.out.println("FIRST, WE DRAW A HEAD");
				hangmanPicture[2][5] = "-";
				hangmanPicture[2][6] = "-";
				hangmanPicture[2][7] = "-";
				hangmanPicture[3][4] = "(";
				hangmanPicture[3][5] = ".";
				hangmanPicture[3][7] = ".";
				hangmanPicture[3][8] = ")";
				hangmanPicture[4][5] = "-";
				hangmanPicture[4][6] = "-";
				hangmanPicture[4][7] = "-";
				break;
			case 2:
				System.out.println("NOW WE DRAW A BODY.");
				for (var i = 5; i <= 8; i++) {
					hangmanPicture[i][6] = "X";
				}
				break;
			case 3:
				System.out.println("NEXT WE DRAW AN ARM.");
				for (int i = 3; i <= 6; i++) {
					hangmanPicture[i][i - 1] = "\\";
				}
				break;
			case 4:
				System.out.println("THIS TIME IT'S THE OTHER ARM.");
				hangmanPicture[3][10] = "/";
				hangmanPicture[4][9] = "/";
				hangmanPicture[5][8] = "/";
				hangmanPicture[6][7] = "/";
				break;
			case 5:
				System.out.println("NOW, LET'S DRAW THE RIGHT LEG.");
				hangmanPicture[9][5] = "/";
				hangmanPicture[10][4] = "/";
				break;
			case 6:
				System.out.println("THIS TIME WE DRAW THE LEFT LEG.");
				hangmanPicture[9][7] = "\\";
				hangmanPicture[10][8] = "\\";
				break;
			case 7:
				System.out.println("NOW WE PUT UP A HAND.");
				hangmanPicture[2][10] = "\\";
				break;
			case 8:
				System.out.println("NEXT THE OTHER HAND.");
				hangmanPicture[2][2] = "/";
				break;
			case 9:
				System.out.println("NOW WE DRAW ONE FOOT");
				hangmanPicture[11][9] = "\\";
				hangmanPicture[11][10] = "-";
				break;
			case 10:
				System.out.println("HERE'S THE OTHER FOOT -- YOU'RE HUNG!!");
				hangmanPicture[11][2] = "-";
				hangmanPicture[11][3] = "/";
				break;
		}
		for (int i = 0; i <= 11; i++) {
			for (int j = 0; j <= 11; j++) {
				System.out.print(hangmanPicture[i][j]);
			}
			System.out.print("\n");
		}

	}

	private static void printDiscoveredLetters(char[] D$) {
		System.out.println(new String(D$));
		System.out.println("\n");
	}

	private static void printLettersUsed(Set<Character> lettersUsed) {
		System.out.println("\nHERE ARE THE LETTERS YOU USED:");
		System.out.println(lettersUsed.stream()
				.map(Object::toString).collect(Collectors.joining(",")));
		System.out.println("\n");
	}

	private static void printIntro() {
		System.out.println("                                HANGMAN");
		System.out.println("              COMPUTER SOCIETY INDIA - VIT STUDENT CHAPTER");
		System.out.println("\n\n\n");
	}

}
