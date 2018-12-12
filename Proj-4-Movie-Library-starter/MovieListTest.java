import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;

@SuppressWarnings("unchecked")
public class MovieListTest {

   private MovieList movieList;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
       movieList = new MovieList(6);
   }
   
         /** Test for required name of instance variable declarations. **/
   @Test 
     public void initialLengthNameTest1() throws Exception{
     boolean wasDeclared = false;
     Field[] fields = MovieList.class.getDeclaredFields();
       for(int i=0;i<fields.length;i++){
         if(fields[i].getName().equals("initialLength")){
            wasDeclared = true;
         }
        }
      assertEquals("Test 1: The instance variable initialLength has not been declared.", true, wasDeclared);
   }
   
   @Test 
   public void movieListNameTest2() throws Exception{
     boolean wasDeclared = false;
      Field[] fields = MovieList.class.getDeclaredFields();
      for(int i=0;i<fields.length;i++){
         if(fields[i].getName().equals("movieList")){
            wasDeclared = true;
         }
      }
      assertEquals("Test 2: The instance variable movieList has not been declared.", true, wasDeclared);
   }
   
   @Test 
   public void numMoviesNameTest3() throws Exception{
     boolean wasDeclared = false;
        Field[] fields = MovieList.class.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
         if(fields[i].getName().equals("numMovies")){
            wasDeclared = true;
         }
        }
      assertEquals("Test 3: The instance variable numMovies has not been declared.", true, wasDeclared);
   }
   
   /** Test for required instance variable type declarations. **/
   
   @Test 
   public void initialLengthTypeTest4() throws Exception{
      String varType = "";
         Field[] fields = MovieList.class.getDeclaredFields(); 
         for(int i=0;i<fields.length;i++){
            if(fields[i].getName().equals("initialLength")){
               varType = fields[i].getType().getName();
            }
         }
      assertEquals("Test 4: The instance variable initialLength has not been declared as an int.", "int", varType);
   }   
   
   @Test 
   public void movieListTypeTest5() throws Exception{
      String varType = "";
        Field[] fields = MovieList.class.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
           if(fields[i].getName().equals("movieList")){
              varType = fields[i].getType().getName();
           }
        }
      assertEquals("Test 5: The instance variable movieList has not been declared as an array of type Movie.", "[LMovie;", varType);
   }
   
   @Test 
   public void numMoviesTypeTest6() throws Exception{
      String varType = "";
      Field[] fields = MovieList.class.getDeclaredFields();
       for(int i=0;i<fields.length;i++){
         if(fields[i].getName().equals("numMovies")){
            varType = fields[i].getType().getName();
         }
       }
      assertEquals("Test 6: The instance variable numMovies has not been declared as an int.", "int", varType);
   }
   
   
   /** Test that the movieList has been initialized to the length passed in to constructor. **/
   @Test 
   public void initMovieListTest7()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      MovieList theList = new MovieList(50);
      int actualLen = 0;
      Field field = theList.getClass().getDeclaredField("movieList");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(theList);
      Movie[] testList = (Movie[])targetObj;
      actualLen = testList.length; 
      assertEquals("Test 7: The Movie array is initialized to the length passed to constructor.", 50, actualLen);
   }
   
   /** Test getMovieListAsArray. **/
   @Test 
   public void initListTest8() {
      Movie[] items = movieList.getMovieListAsArray(); 
      assertNotEquals("Test 8: The array returned by getMovieListAsArray is null- should be an array of length 0.", null, items);
   }
   
   /** Test the addMovie method and the getMovieListAsArray. **/
   @Test 
   public void addOneMovieTest9()throws Exception {
      String result = "";
      Movie item = new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018");   
         movieList.addMovie(item);
         Movie[] movies = movieList.getMovieListAsArray();
         result = movies[0].toString(); 
      assertEquals("Test 9: The Movie was not added.", item.toString(), result);
   }
   
   /** Test the addMovie method and the getMovieListAsArray-length of array. **/
   @Test 
   public void addOneMovieLenOneTest10()throws Exception {
      int len = 0;
         movieList.addMovie(new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018"));
         Movie[] movies = movieList.getMovieListAsArray(); 
         len = movies.length;
      assertEquals("Test 10: Call to addMovie- the list should contain one item.", 1, len);
   }
   
   /** Test the getNumberOfMovies method on an empty list. **/
   @Test 
   public void initListSizeTest11() {
      int len = movieList.getNumMovies(); 
      assertEquals("Test 11: The getNumberOfMovies method should return 0 on an empty list.", 0, len);
   } 
   
   @Test 
   public void initListIsEmptyTest12() {
      boolean empty = movieList.isEmpty(); 
      assertEquals("Test 12: The isEmpty method should return true on an empty list.", true, empty);
   }

   @Test 
   public void oneMovieListSizeTest13() {
      movieList.addMovie(new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018"));
      int len = movieList.getNumMovies(); 
      assertEquals("Test 13: The getNumberOfMovies method should return 1.", 1, len);
   }  

   @Test 
   public void listOneMovieIsEmptyTest14() {
      movieList.addMovie(new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018"));
      boolean empty = movieList.isEmpty(); 
      assertEquals("Test 14: The isEmpty method should return false on a list with one item.", false, empty);
   }
      
   @Test 
   public void findMoviesByTitleZeroMatchesTest15()throws Exception {
      int len = 0;
         addSixMixedMovies(movieList);
         Movie[] movies = movieList.findMoviesByTitle("No Title Matches This");
         len = movies.length; 
      assertEquals("Test 15: Test the findMoviesByTitle method with zero matches.", 0, len);
   }

/** . **/
   @Test 
   public void findMoviesByTitleTwoMatchesTest16()throws Exception {
      int len = 0;
      addSixMixedMovies(movieList);
      Movie[] movies = movieList.findMoviesByTitle("Repo Man"); 
      len = movies.length;   
      assertEquals("Test 16: Test the findMoviesByTitle method with two matches.", 1, len);
   }
   
   @Test 
   public void findMoviesByGenreZeroMatchesTest17() throws Exception{
      int len = 0;
      addSixMixedMovies(movieList);
      Movie[] movies = movieList.findMoviesByGenre("No Genre Matches This"); 
      len = movies.length;
      assertEquals("Test 17: Test the findMoviesByGenre method with zero matches.", 0, len);
   }
 
   @Test 
   public void findMoviesByGenreTwoMatchesTest18()throws Exception {
      int len = 0;
      addSixMixedMovies(movieList);
      Movie[] movies = movieList.findMoviesByGenre("sci fi");
      len = movies.length;
      assertEquals("Test 18: Test the findMoviesByGenre method with two matches.", 2, len);
   }  

   @Test 
   public void expandListTest19()throws Exception {
      int result = 0;
         Class classToCall = Class.forName("MovieList");     
	      Method methodToExecute = classToCall.getDeclaredMethod("expandList", new Class[]{Movie[].class});
         methodToExecute.setAccessible(true);
         Movie[] args = new Movie[5];
         Object expandedListObj = methodToExecute.invoke(movieList, new Object[]{args});
	      Movie[] expandedList = (Movie[])expandedListObj; 
         result = expandedList.length;
      assertEquals("Test 19: The expandList method did not create an array of double the length of the array passed in.", 10, result);
   }

   /*  Test private method isFull on default list. */
   @Test 
   public void isFullTest20()throws Exception {
      boolean result = true;
      Class classToCall = Class.forName("MovieList");     
	   Method methodToExecute = classToCall.getDeclaredMethod("isFull");
      methodToExecute.setAccessible(true);
      Boolean boolObj = (Boolean)methodToExecute.invoke(movieList); 
      result = boolObj.booleanValue();
      assertEquals("Test 20: The isFull method should return false on an array with NULL values.", false, result);
   }
   
/** Test the getMovieListAsArray method after expansion. **/  
   @Test 
   public void getMovieListAsArrayAfterExpansionTest21()throws Exception {
      int len = 0;
         movieList = new MovieList(2);
         addSixMixedMovies(movieList);
         Movie[] movies = movieList.getMovieListAsArray(); 
         len = movies.length;
      assertEquals("Test 21: The isEmpty method should return false on a list with one item.", 6, len);
   }        
   
   // populate the array with 6 movies.
   private void addSixMixedMovies(MovieList list){
       list.addMovie(new Movie("2001; A Space Odyssey","Kubrick","sci fi",142,"Keir Dullea","1968"));
       list.addMovie(new Movie("Black Panther", "Coogler", "fantasy", 134, "Chadwick Boseman", "2018"));
       list.addMovie(new Movie("My Neighbor Totoro", "Miyazaki", "fantasy", 87, "Animation", "1993"));
       list.addMovie(new Movie("Repo Man", "Cox", "sci fi", 92, "Emilio Estevez", "1984"));
       list.addMovie(new Movie("The Seven Samurai", "Kurosawa", "drama", 207, "Toshiro Mifune", "1954"));
       list.addMovie(new Movie("Moneyball", "Miller", "drama", 133, "Brad Pitt", "2011"));
   }
}
