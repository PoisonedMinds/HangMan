package hangman;

import javax.swing.*;

/**
 * @title HangMan
 * @author Steven Biro
 * @teacher Mr. J. Carron
 * @date 1-Mar-2015 7:31:23 PM
 * @purpose The purpose of this program is to play hangman
 */
public class HangMan {

    public static void main(String[] args) {
        String sports[] = {"SPORTS", "SOCCER", "BASKETBALL", "SQUASH", "BASEBALL","FOOTBALL","TENNIS","HOCKEY"};
        String movies[] = {"MOVIES", "INTERSTELLAR", "HARRY POTTER", "THE GODFATHER", "THE INTERVIEW","AVATAR","TERMINATOR"};
        String tvshows[] = {"TV SHOWS", "DEXTER", "BREAKING BAD", "PRISION BREAK", "LOST", "SURVIVOR","FAMILY GUY","THE SIMPSONS"};
        //2d array of all words and categories
        String hangmanWords[][] = {sports, movies, tvshows};
        //array for the human graphics
        String graphic[] = {" O \n/ | \\\n |\n/  \\", " O \n/ | \\\n | \n/", " O \n/ | \\\n  |", " O\n/ | \\", " O\n/ |", " O\n/", " O", ""};
        int numGuesses, numWords;
        String category, word, blankWord, lettersUsed;

        int randomNumber1, randomNumber2; //random category and word
        randomNumber1 = (int) (Math.random() * hangmanWords.length);
        //random category
        randomNumber2 = (int) (Math.random() * (hangmanWords[randomNumber1].length - 1) + 1);//random word
        category = hangmanWords[randomNumber1][0];
        word = hangmanWords[randomNumber1][randomNumber2];

        //build the word with dashes
        blankWord = "";
        numWords = 1;

        for (int pos = 0; pos < word.length(); pos++) {
            //convert the char at position pos to an int
            //if it is between 'A' and 'Z', add a dash
            if (((int) word.charAt(pos) >= 65) && ((int) word.charAt(pos) <= 90)) {
                blankWord += "-";
            } else {
                blankWord += word.charAt(pos);
                if (word.charAt(pos) == ' ') {
                    numWords++;
                }
            }
        }
        lettersUsed = "";

        for (numGuesses = 7; numGuesses > 0; numGuesses--) {
            System.out.println("Category: " + category + "\nNumber of Words: " + numWords + "\n" + blankWord);
            System.out.println(graphic[numGuesses]);
            //loop for guesses
            String myGuess = JOptionPane.showInputDialog("Number of Guesses remaining: " + numGuesses + "\nGuess a letter: ");
            myGuess = myGuess.toUpperCase();

            boolean found = false;

            //check if letter was already used
            if (lettersUsed.indexOf(myGuess) != -1) {
                JOptionPane.showMessageDialog(null, "Theletter " + myGuess + " was already guess.\n" + "Lose one guess");
            } else {
                lettersUsed += myGuess;
                //determine if the guessed letter is in the word
                for (int pos = 0; pos < word.length(); pos++) {
                    if (("" + word.charAt(pos)).equals(myGuess)) {
                        //replace hyphen witht the letter
                        blankWord = blankWord.substring(0, pos) + myGuess + blankWord.substring(pos + 1, blankWord.length());
                        found = true;
                    }
                }
            }
            //if the letter is found, add one to the guess
            if (found) {
                numGuesses++;
            }
            if (blankWord.indexOf("-") == -1) {
                //no dashes left, you guessed it!
                System.out.println("Category: " + category + "\nNumber of Words: " + numWords + "\n" + blankWord);
                System.out.println("Congratulations! You guessed correctly!");
                System.exit(1);
            }
        }
        System.out.println("Sorry you have no guesses left.");
        System.out.println(graphic[numGuesses]);
        System.out.println("The correct word was: " + word);
    }

}
