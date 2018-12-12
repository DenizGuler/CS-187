
/* This class encapsulates a list of movies in a user's collection.
 * The list is implemented as an array of type Movie. 
 * Each movie is represented by an instance of the Movie class.
*/
public class MovieList {
   //Class member variable declarations:
        int numMovies;
        Movie[] movieList;


   /* Constructor that initializes the member variables. The array is 
   *  created using the initial length passed in to the constructor.
   *  The initialLength is assigned to the initial length passed in to the constructor.
   *  The numMovies is initialized to 0.
   *  Any other member variables are initialized as well.
   */    
   public MovieList(int initialLen){
       numMovies = 0;
       movieList = new Movie[initialLen];
   }
   
   /* Add the newMovie passed in to the next available cell in the movieList.
    * Available (empty) cells have the value NULL. The numMovies variable may be used
    * to keep track of the index of the next available cell.
    * For example, if the list contained: item1, item2, NULL, NULL,
    * the next available cell is at index 2.
   */
   public void addMovie(Movie newMovie){
        if(this.isFull()){
          expandList(movieList);
        }
        System.out.println(numMovies);
         System.out.println(movieList.length);
        movieList[numMovies] = newMovie;
        numMovies++;
   }

  /* This method returns an array that contains only Movie objects whose 
   * title matches the targetTitle passed in.
   * The array returned does not contain any NULL values.
   * This method returns an array of length 0 if there are no matches.
   * This method may call the getOnlyItems method.
   */   
   public Movie[] findMoviesByTitle(String targetTitle){
       int count = 0;
       int indexCount = 0;
       Movie[] tempMovieList = getOnlyItems(movieList, movieList.length);
       for(int i = 0; i < tempMovieList.length-1; i++){
        if(tempMovieList[i].getTitle().equals(targetTitle)){
          count++;
        }
       }
       Movie[] titleArray = new Movie[count];
       for(int i = 0; i < movieList.length-1; i++){
        if(tempMovieList[i].getTitle().equals(targetTitle)){
          titleArray[indexCount] = tempMovieList[i];
          indexCount++;
        }
       }
       return titleArray;
   }
   
  /* This method returns an array that contains only Movie objects whose 
   * genre matches the targetGenre passed in.
   * The array returned does not contain any NULL values.
   * This method returns an array of length 0 if there are no matches.
   * This method may call the getOnlyItems method.
   */   
   public Movie[] findMoviesByGenre(String targetGenre){
       int count = 0;
       int indexCount = 0;
       Movie[] tempMovieList = getOnlyItems(movieList, movieList.length);
       for(int i = 0; i < tempMovieList.length-1; i++){
        if(tempMovieList[i].getGenre().equals(targetGenre)){
          count++;
        }
       }
       Movie[] genreArray = new Movie[count];
       for(int i = 0; i < tempMovieList.length-1; i++){
        if(tempMovieList[i].getGenre().equals(targetGenre)){
          genreArray[indexCount] = tempMovieList[i];
          indexCount++;
        }
       }
       return genreArray;   
   }
   
  /* This method returns an array of all of the Movie objects that are 
   * stored in the movieList. The array returned does not contain any NULL 
   * values. This method returns an array of length 0 if the movieList is empty.
   * This method may call the getOnlyItems method
   */
   public Movie[] getMovieListAsArray(){
      if(movieList.length == 0){
        Movie[] newArray = new Movie[0];
        return newArray;
      }
      return getOnlyItems(movieList, movieList.length);
   }
    
    /* Returns the number of Movies stored in the movieList. 
    */
    public int getNumMovies(){
        return numMovies;
    }
    
   /* Returns true if the movieList contains no Movies, false otherwise.
    */
   public boolean isEmpty(){
      if(numMovies == 0){
        return true;
      }else{
        return false;
      }
   }
  
    /****** Private, "helper" method section ******/
   
   /* Creates a new array that is double the size of the array passed in, copies the data 
    * from that array to the new array, and returns the new array. 
    * Note that the new array will contain the Movies from the previous array followed by NULL values.
    */
    private Movie[] expandList(Movie[] inputList){
      Movie[] newArray = new Movie[inputList.length * 2];
      for(int i = 0; i < movieList.length-1; i++){
        newArray[i] = movieList[i];
      }
      movieList = newArray;
      return movieList;
    }
    
   /* A full Movie list is an array where all cells contain a Movie. That
    * means there is no cell that contains NULL.
    * This method returns true if all cells in the array contain a Movie
    * object, false otherwise. 
    */
    private boolean isFull(){
      if(numMovies == movieList.length - 1){
        return true;
      }else{
        return false;
      }
    }
    
    /*
    * This method takes an array of Movies as an input as well as
    * the number of Movies on that array. The inputArray may contain 
    * some NULL values.
    * This method returns an array that contains only the Movies in 
    * the input array and no NULL values.
    * It returns an array of length 0 if there are no Movies in the input array.
    */
    private Movie[] getOnlyItems(Movie[] inputArray, int size){
      if(this.isFull()){
        return inputArray;
      }else if(this.isEmpty()){
        Movie[] outputArray = new Movie[0];
        return outputArray;
      }else{
        int currIndex = 0;
        Movie[] returnArray = new Movie[size];
        for(int i = 0; i < inputArray.length; i++){
          if(inputArray[i] != null){
            returnArray[currIndex] = inputArray[i];
            currIndex++;
          }
        }
        return returnArray;
      }
    }
}