/**
 * This class encapsulates the data required to represent a movie in a collection
 * of Movies. The attributes of a movie are title, director, genre, 
 * playing time, lead actor, and year of release in the form YYYY (i.e. 2018).
 **/
 public class Movie {

  private String title;
  private String director;
  private String genre;
  private int playTime;// in minutes
  private String leadActor;
  private String releaseYear; //in the form YYYY
  
  /* Constructor
  */
  public Movie(String title, String director, String genre, 
                   int playTime, String leadActor, String releaseYear){
     this.title = title;
     this.director = director; 
     this.genre = genre;
     this.playTime = playTime;
     this.leadActor = leadActor;
     this.releaseYear = releaseYear;
  }
  
  // get method for the title
  public String getTitle(){
     return title;
  }
  // get method for the author
  public String getDirector(){
     return director;
  }
  // get method for the genre
  public String getGenre(){
     return genre;
  }
  // get method for the playing time
  public int getPlayTime(){
     return playTime;
  }
  // get method for the lead actor
  public String getLeadActor(){
     return leadActor;
  }
  // get method for the release year
  public String getReleaseYear(){
     return releaseYear;
  }
  /* The return should have the format:
   *   Movie: [title], [director], [genre], [playTime], [leadActor], [releaseYear]
   * For example:
   *   "Movie: Black Panther, Coogler, fantasy, 134, Chadwick Boseman, 2018"
   */
  public String toString(){
     return "Movie: "+title+", "+director+", "+genre+", "+playTime+", "+leadActor+", "+releaseYear;
  }
}