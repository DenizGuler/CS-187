import java.util.Scanner;

/* This application manages a library of movies for a user. Each movie is represented by an instance of 
 * the Movie class. The movies in the user's library are stored in an array. The array is managed by an 
 * instance of the MovieList class.
 * This class, LibraryMain, manages interactions with a user and the MovieList class. A menu of choices is
 * presented to a user. The user may add a movie, find a movie by matching on title or genre, and view all 
 * movies in the library.
*/

import java.io.*;

public class LibraryMain {
       
    public static void main(String[] args) throws IOException{
         System.out.println("My Movie Library");
         Scanner scan = new Scanner(System.in);
         MovieList movieList = new MovieList(5);        
         boolean keepGoing = true;
         String userStr = "";
         int position;
         while(keepGoing) {
              System.out.println("Main Menu:");
              System.out.println("Enter A to add a new Movie."); 
              System.out.println("Enter F to find a Movie."); 
              System.out.println("Enter P to view all Movies.");
              System.out.println("Enter X to quit.");
              System.out.println("");
              userStr = scan.nextLine();
              
              if (userStr.equalsIgnoreCase("A")){ 
                 Movie movie = null;                
                 System.out.println("Enter the title: ");
                 String title = scan.nextLine();
                 System.out.println("Enter the director: ");
                 String author = scan.nextLine();
                 System.out.println("Enter the genre: ");
                 String genre = scan.nextLine();
                 System.out.println("Enter the playing time (minutes): ");
                 String playTime = scan.nextLine();
                 System.out.println("Enter the lead actor: ");
                 String actor = scan.nextLine();
                 System.out.println("Enter the release year YYYY: ");
                 String year = scan.nextLine();
                 movie = new Movie(title, author, genre, Integer.parseInt(playTime),
                                              actor, year);
                 movieList.addMovie(movie);
              }
              else if (userStr.equalsIgnoreCase("F")){
                 System.out.println("Enter T to search by title, G to search by genre:");
                 userStr = scan.nextLine();
                 if(userStr.equalsIgnoreCase("T")){
                    System.out.println("Enter the title:");
                    String title = scan.nextLine();
                    Movie[] items = movieList.findMoviesByTitle(title);
                    if(items.length>0){
                      String listStr = getMovieListAsString(items);
                      System.out.println(listStr);
                    }
                    else
                     System.out.println("Could not find "+title+" in the list.");
                 }
                 else if(userStr.equalsIgnoreCase("G")){
                    System.out.println("Enter the genre:");
                    String genre = scan.nextLine();
                    Movie[] items = movieList.findMoviesByGenre(genre);
                    if(items.length>0){
                      String listStr = getMovieListAsString(items);
                      System.out.println(listStr);
                    }
                    else
                     System.out.println("Could not find "+genre+" in the list.");
                 }
                 else
                    System.out.println("Unrecognized search type.");
              }
              else if (userStr.equalsIgnoreCase("P")){
                 System.out.println("Your movies: ");
                 Movie[] items = movieList.getMovieListAsArray();
                 String listStr = getMovieListAsString(items);
                 System.out.println(listStr);
              }
              else if(userStr.equalsIgnoreCase("X"))
                 keepGoing = false;
              else
                 System.out.println("Unrecognized input."); 
              System.out.printf("%n");               
         }
         System.out.println("Bye for now.");
         scan.close();
    }
    
   /* This method returns a String which consists of the String
    * representations of all Movies in the array passed in.
    * Assume the movieList does not contain NULL values.
    * Assume the movieList is not null, and length >=0.
   */
   public static String getMovieListAsString(Movie[] movieList){
     StringBuilder sb = new StringBuilder();
     if(movieList.length>0){
       for(int i=0; i<movieList.length;i++) {
         sb.append(i+1).append(" ").append(movieList[i].toString());
         sb.append(System.lineSeparator());
       }
     }
     else
       sb.append("no items");
     return sb.toString();
   }
}