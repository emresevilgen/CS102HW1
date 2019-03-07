/**
 * @author Munib Emre Sevilgen
 * @version 2.00, 11.02.2018
 */

import java.util.*;
import java.lang.StringBuffer;
import java.util.ArrayList;
import java.io.File;
/**
 * Hangman Game
 * @author Munib Emre Sevilgen, Utku Kalkanli, Ömer Özbekler, Abdullah Yilmaz 
 * @version 1.00 04.02.2018
 */ 
public class Hangman 
{
   // properties
   private StringBuffer secretWord;
   private StringBuffer allLetters;
   private StringBuffer usedLetters;
   private int numberOfIncorrectTries;
   private int maxAllowedIncorrectTries;
   private StringBuffer knownSoFar;
   
   // constructors
   /**
    * Hangman class (constructor only)
    * @author Abdul Moeed, Elif Demir, Gonca YÄ±lmaz, Hasan Raza, Ä°brahim Burak Yorulmaz, Tanay Toksoy
    * @version 2018.02.03
    */
   public Hangman() {
      String stars; // for use in creating knownSoFar
      
      allLetters = new StringBuffer( "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
      maxAllowedIncorrectTries = 6;
      numberOfIncorrectTries = 0;
      usedLetters = new StringBuffer( ""); // not camelCase because it is given this way in the assignment
      secretWord = new StringBuffer( chooseSecretWord()); // chooseSecretWord() return type is not specified, assume it returns a String rather than a StringBuffer
      stars = "";
      for ( int i = 0; i < secretWord.length(); i++ ) {
         stars = stars + "*";
      }
      knownSoFar = new StringBuffer( stars);
   }
   
   // methods
   /**
    * Gets all letters
    * @return allLetters
    */
   public String getAllLetters() {
      return allLetters.toString();
   }
   
   /**
    * Gets used letters
    * @return usedLetters
    */
   public String getUsedLetters() {
      return usedLetters.toString();
   }
   
   /**
    * Gets number of incorrect tries
    * @return numberOfIncorrectTries
    */ 
   
   public int getNumOfIncorrectTries() {
      return numberOfIncorrectTries;
   }
   /**
    * Gets number of maximum allowed tries
    * @return getMaxAllowedIncorrectTries
    */  
   public int getMaxAllowedIncorrectTries() {
      return maxAllowedIncorrectTries;
   }
   
   /**
    * Gets the letters known so far
    * @return knownSoFar
    */ 
   public String getKnownSoFar() {
      return knownSoFar.toString();
   }
   
   /**
    * This method checks if the letter param is in the secret word or no.
    * Created by GROUP A - Aysegul, Endri, Rana, Utku, Zeyad on 02.02.2018.
    * @param letter ,the char to be checked in the secretWord.
    * @return -1 : the letter is not from the alphabet, -2 : the letter was already used, -3 : user ued all his chances, won or lost, count : number of changes made to the knownSoFar.
    */
   public int tryThis(char letter) {
      
      int count;
      boolean isCorrect;
      
      letter = Character.toLowerCase(letter);
      // Check if it is valid char.
      if ( ( letter < 'a' || letter > 'z')) {
         return -1;
      }
      
      else if ( usedLetters.indexOf( Character.toString(letter)) != -1) {
         return -2;
      }
      
      else {
         count = 0;
         usedLetters.append( Character.toString(letter));
         isCorrect = false;
         for ( int i = 0; i < secretWord.length(); i++) {
            if ( secretWord.charAt(i) == letter) {
               knownSoFar.deleteCharAt(i);
               knownSoFar.insert(i, letter);
               isCorrect = true;
               count++;
            }
         }
         
         if (!isCorrect) {
            numberOfIncorrectTries++;
         }
         
         if (isGameOver() || hasLost()){
            return -3;
         } else {
            return count;
         }
      }
   }
   
   /**
    * Checks game is over or not.
    * @return true if game is over, false if game is not over
    */
   public boolean isGameOver() {
      if ( hasLost() || secretWord.toString().equals(knownSoFar.toString()))
         return true;
      return false;
      
   }
   /**
    * Checks the user has lost or not.
    * @return true if the user has lost, false if the user has not lost
    */
   public boolean hasLost() {
      if ( numberOfIncorrectTries >= maxAllowedIncorrectTries)
         return true;
      return false;
   }
   
   /*
    * Akin Parkan
    * Alptekin Onder
    * Batuhan Tosyali
    * Elif Kasapoglu
    * Enes Merdane
    * Muhammed Berk Kose
    */
   /*
    * chooseSecretWords_FromText 
    * ---> Opens the text file
    * ---> Adds the words in the text file to the arraylist named words.
    * ---> Uses Math.Random() method to pick a random word from the arraylist 
    * ---> returns the chosen word.
    */
   /**
    * Chooses the secret word from Words.txt file
    * @return the secret word
    */
   public String chooseSecretWord(){
      Scanner fileIn;
      ArrayList<String> words = new ArrayList<String>();
      try
      {
         fileIn = new Scanner( new File( "Words.txt" )); // Make sure that the text is in the same folder as class/classpath or insert a direct path example: C:\\Users\\Admin\\Desktop\\Words.txt
         while( fileIn.hasNext() )
         {
            words.add( fileIn.next() ); // Adds the words in the text file to the arraylist
         }
         fileIn.close();
      }
      catch( Exception e ) // If the file is not found. An error message is displayed Informing that the chosen word is from the fixed array list.
      {
         System.out.println( "Could not find the file." );
         System.out.println( "Choosing a default word, from the fixed array list." );
         return chooseSecretWord();
      }
      return words.get( ( int )( Math.random()*words.size() ) ); // Returns a random words from the arraylist.
      
   }

   /**
    * Returns the string of properties
    * @return string of properties
    */
   public String toString() {
      return "Secret Word: " + secretWord.toString() + "\nAll letters: " + 
         allLetters.toString() + "\nUsed Letters: " + usedLetters.toString() + 
         "\nNumber of incorrect tries: " + numberOfIncorrectTries + 
         "\nNumber of maximum allowed tries: " + maxAllowedIncorrectTries + 
         "The letters known so far: "+ knownSoFar.toString();   
   }
   /**
    * Checks two Hangman whether they are equal or not
    * @param other Hangman object
    * @return true if objects are equal or false if they are not equal
    */
   public boolean equals( Hangman other) {
      if ( !secretWord.toString().equals( other.secretWord.toString()))
         return false;
      else if ( !allLetters.toString().equals( other.allLetters.toString()))
         return false;
      else if ( !usedLetters.toString().equals( other.usedLetters.toString()))
         return false;
      else if ( numberOfIncorrectTries != other.numberOfIncorrectTries)
         return false;
      else if ( maxAllowedIncorrectTries != other.maxAllowedIncorrectTries)
         return false;
      else if ( !knownSoFar.toString().equals( other.knownSoFar.toString()))
         return false;
      else
         return true;
   }     
}