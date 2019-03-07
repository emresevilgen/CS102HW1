import java.util.Scanner;
/**
 * @author Munib Emre Sevilgen
 * @version 2.00, 11.02.2018
 */


/**
 * Hangman 
 * @author Yigit Gorgulu, Ezgi Yavuz, Artun Cura, Irmak Demir, Yusuf Avci, Goksu Turan
 * @version 1.0, 02.02.2018
 */ 
public class HangmanMain
{
   public static void main( String[] args)
   {
      Scanner scan = new Scanner( System.in);
      
      // constants
      final int INVALID_INPUT = -1;
      final int USED_LETTER = -2;
      final int GAME_OVER = -3;
      
      // variables
      Hangman hangman;
      String triedLetter;
      int tryThisResult;
      
      // program code
      hangman = new Hangman();
      
      // print all letters
      System.out.println( hangman.getAllLetters()); 
      
      triedLetter = "";
      tryThisResult = 0;
      do
      {
         // interface update
         System.out.println( "Secret Word: " + hangman.getKnownSoFar());
         System.out.println( "Used Letters: " + hangman.getUsedLetters());
         System.out.println( "Remaining tries: " + (hangman.getMaxAllowedIncorrectTries() - hangman.getNumOfIncorrectTries()));
         
         // takes and tries a given letter
         System.out.print( "Please enter a letter: ");
         triedLetter = scan.next(); 
         tryThisResult = hangman.tryThis( triedLetter.charAt(0)); // stores error code
         
         if ( tryThisResult == INVALID_INPUT)
         {
            System.out.println( "Invalid letter.");
         }
         else if ( tryThisResult == USED_LETTER)
         {
            System.out.println( "Letter is already used.");
         }
         else if ( tryThisResult == GAME_OVER)
         {
            // we would need to print the secret word but there is no method to get the secret word
            System.out.println( "Game over!");
         }
      } while ( !hangman.isGameOver()); // There  should be hangman.isGameOver
      
      if ( hangman.hasLost())
      {
         System.out.println( "SORRY YOU LOST! :(");
      }
      else
      {
         System.out.println( "CONGRATS! YOU WON!");
      }
   }
}
