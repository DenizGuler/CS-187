import java.util.Random;

/* The game is played in a series of rounds. The game board is a 2D array with
 * a set number of rows and columns. The array contains Objects of type GamePiece.
 * There are four types of GamePieces: Player- the user, Troll- the villain, Treasure- the 
 * goal, and EmptyPiece- represents cells that are not one of the previous types.
 * The player's goal is to reach the treasure without encountering the Troll.
 * The Troll's goal is to intercept the player before the player reaches the treasure.
 * The player starts in the upper left-hand corner- position 0,0.
 * The treasure is located at the lower right-hand corner- position 7, 9.
 * The Troll starts at a random position in the board that is not
 * the player's starting position or the treasure's position.
 * The player makes a move one step in one of four directions: up, down, left, right.
 * If the player is on a border and the requested move would take the player off the 
 * board, the move is ignored- i.e. the player does not move.
 * The Troll knows the player's position and after the player moves, the Troll will
 * move towards the player. If the Troll's next move reaches the player, the Troll
 * wins. If the player reaches the treasure and the Troll also reaches the treasure 
 * in the same round, the player wins.
 * The player starts with 160 life points. Each move the player makes costs 10 life points. 
 * This deduction occurs for all player moves- even if the player attempts to move off the board.
 * If a player's life level drops to 0 or less, the player is not alive an cannot move.
*/

public class TrollGame {
   //constants for this version of the game
   private static final int INIT_PLAYER_POINTS = 160;
   private static final int PLAYER_POINTS_DEC = -10;
   private static final int TREASURE_POINTS = 500;
   private static final int ROWS = 8;
   private static final int COLS = 10;
   // random number generator
   private Random rand;
   // the game board, a 2D array of GamePieces
   private GamePiece[][] gameBoard;
   // variables to keep track of the locations of player, troll
   private int curPlayerRow, curPlayerCol;
   private int curTrollRow, curTrollCol;
   // the player's status
   private boolean playerWins;
   private boolean playerLoses;
   private int treasurePoints;
   
   /* Constructor that uses an unseeded instance of the random number generator.
    * Calls initBoard.
    */
   public TrollGame () {
      rand = new Random();
      initBoard(ROWS, COLS, rand);
   }

   /* Constructor that uses a seeded instance of the random number generator.
    * Calls initBoard.
    */   
   public TrollGame (int seed){
      rand = new Random(seed);
      initBoard(ROWS, COLS, rand);
   }
   
   /*
    * Manages a player move. This is a multi-step process that involves:
    * 1- if the player is alive:
    *    - determine the direction of the user's input.
    *    - check that the user move is valid. If not, player does not change position,
    *      if valid, adjust player position accordingly.
    *    - adjust player life level- regardless of validity of move.
    * 2- calculate troll's move given the player's new position.
    * 3- check if troll has same position as player.if so, player loses. Adjust the GamePieces
    *    so that the board is displayed properly, i.e. the player is gone and is replaced by the troll.
    * 4- check if the player reached the treasure. If so, the player wins. Adjust the GamePieces
    *    so that the board is displayed properly, i.e. the player now appears in the treasure position. 
    *    Also, update the troll's last position.
    * 5- If neither 3 nor 4 above apply, then update the player and trollpositions.
    *    Adjust the GamePieces so that the board is displayed properly, i.e. the player and troll appear 
    *    in their new positions.
    */
   public void movePlayer(String direction) {
      if(playerAlive(curPlayerRow, curPlayerCol)){
        if(direction.equals("u")&&(curPlayerRow-1)>=0){
          overwritePosition(curPlayerRow, curPlayerCol, (curPlayerRow-1), curPlayerCol);
          curPlayerRow -= 1;
        }else if(direction.equals("d")&&(curPlayerRow+1)<=7){
          overwritePosition(curPlayerRow, curPlayerCol, (curPlayerRow+1), curPlayerCol);
          curPlayerRow += 1;
        }else if(direction.equals("l")&&(curPlayerCol-1)>=0){
          overwritePosition(curPlayerRow, curPlayerCol, curPlayerRow, (curPlayerCol-1));
          curPlayerCol -= 1;
        }else if(direction.equals("r")&&(curPlayerCol+1)<=9){
          overwritePosition(curPlayerRow, curPlayerCol, curPlayerRow, (curPlayerCol+1));
          curPlayerCol += 1;
        }
      }
        if(playerFoundTreasure(curPlayerRow, curPlayerCol)){
          playerWins();
        }
        adjustPlayerLifeLevel(curPlayerRow, curPlayerCol);
        int[] array = calcNewTrollCoordinates(curPlayerRow, curPlayerCol, curTrollRow, curTrollCol);
        if(curPlayerRow == curTrollRow && curPlayerCol == curTrollCol){
          gameBoard[curTrollRow][curTrollCol] = new TrollPiece();
        }else{
          int newTrollRow = array[0];
          int newTrollCol = array[1];
          overwritePosition(curTrollRow, curTrollCol, newTrollRow, newTrollCol);
          curTrollRow = newTrollRow;
          curTrollCol = newTrollCol;
        }
   }
   
   /* Returns true if the player wins, false otherwise. */
   public boolean playerWins(){
      return playerFoundTreasure(curPlayerRow, curPlayerCol);
   }
   
   /* Returns true if the player loses, false otherwise. */
   public boolean playerLoses(){
      return (curPlayerRow == curTrollRow && curPlayerCol == curTrollCol);
   }
   
   /* Returns the number of treasure points. */
   public int getTreasurePoints(){
      return treasurePoints;
   }
   
   /* Resets the game variables and game board (call initBoard).
    * Does NOT change the random number generator instance.
   */
   public void resetGame(){
      initBoard(ROWS, COLS, rand);
   }
   
   /* Returns a String version of the game. */
   public String getGameStr() {
      StringBuilder outStr = new StringBuilder();
      for(int i=0;i<ROWS;i++) {
         for(int j=0;j<COLS;j++){ 
            outStr.append("|").append(gameBoard[i][j].show());
          }
         outStr.append("|").append(System.getProperty("line.separator"));
      }
      return outStr.toString();
   }
   
   /** private methods below **/
      
   /* Creates the game board array with rows and cols. The player starts in the upper left-hand corner. 
    * The treasure is at the lower right-hand corner. The rest of the cells are empty pieces.
    * The player starts with INIT_PLAYER_POINTS life points. The treasure is initialized to
    * TREASURE_POINTS. The Troll position is determined at random by calling the getRandTrollRow
    * and getRandTrollCol methods. This method returns the initialized array.
   */
   private GamePiece[][] initBoard(int rows, int cols, Random rand) {
      gameBoard = new GamePiece[rows][cols];
      curPlayerRow = 0;
      curPlayerCol = 0;
      for(int i = 0; i < rows; i++){
        for(int j = 0; j < cols; j++){
          gameBoard[i][j] = new EmptyPiece();
        }
      }
      gameBoard[0][0] = new PlayerPiece(INIT_PLAYER_POINTS);
      curTrollRow = getRandTrollRow(rand, rows);
      curTrollCol = getRandTrollCol(rand, cols);
      while(curTrollRow == 7 && curTrollCol == 9){
        curTrollRow = getRandTrollRow(rand, rows);
        curTrollCol = getRandTrollCol(rand, cols);
      }
      gameBoard[curTrollRow][curTrollCol] = new TrollPiece();
      gameBoard[7][9] = new TreasurePiece(TREASURE_POINTS);
      return gameBoard;
   }
   
   /* Returns true if the player is alive, false otherwise.*/
   private boolean playerAlive(int curPlayerRow, int curPlayerCol){
      return (gameBoard[curPlayerRow][curPlayerCol].isAlive());
   }
   
   /* Adjusts the player's life level by the amount PLAYER_POINTS_DEC. */
   private void adjustPlayerLifeLevel(int curPlayerRow, int curPlayerCol){
      gameBoard[curPlayerRow][curPlayerCol].updateLifePoints(PLAYER_POINTS_DEC);
   }
   
   /* Returns true if the player row and column passed in equals
      the treasure row and column. */
   private boolean playerFoundTreasure(int playerRow, int playerCol){
      return (playerRow == 7 && playerCol == 9);
   }
   
   /* Returns a random number in [1,numRows-1] */
   private int getRandTrollRow(Random rand, int numRows){
      return rand.nextInt(numRows-1)+1;
   }
   
   /* Returns a random number in [1,numCols-1] */
   private int getRandTrollCol(Random rand, int numCols){
      return rand.nextInt(numCols-1)+1;
   }
   
   /* 
    * This method calculates the direction the troll will move to get as close to the player as possible.
    * The player and current troll positions are passed in. The method chooses a move in one direction
    * that will bring it closer to the player. The method returns an int array [row, col], where row is the 
    * index of the verical position and col is the index of the horizontal position in the game board.
    * The strategy of calculating the new troll position is to minimize the distance between the troll and player.
    * This can be done by using the Manhattan, or city block, distance between the two pieces. 
    * Determine the horizontal distance and then the vertical distance. The troll will want to move to 
    * decrease the greater of the two distances. Note that the direction is also important. The sign of
    * the difference can indicate the direction to move in. Asume the player is never out of bounds.
    * Note: the player should loose if they move onto the troll's position. The troll should detect this
    * situation and not make a move.
   */ 
   private int[] calcNewTrollCoordinates(int playerRow, int playerCol, int trollRow, int trollCol){
      int diffRow = playerRow - trollRow;
      int diffCol = playerCol - trollCol;
      int[] newArray = new int[2];

      if(diffRow == 0 && diffCol == 0){
      }
      else if(Math.abs(diffRow) >= Math.abs(diffCol) && diffRow > 0){
        trollRow += 1;
      }
      else if(Math.abs(diffRow) >= Math.abs(diffCol) && diffRow < 0){
        trollRow -= 1;
      }
      else if(Math.abs(diffRow) < Math.abs(diffCol) && diffCol > 0){
        trollCol += 1;
      }
      else if(Math.abs(diffRow) < Math.abs(diffCol) && diffCol < 0){
        trollCol -= 1;
      }
      newArray[0] = trollRow;
      newArray[1] = trollCol;
      return newArray;
   }
   
 // The following three methods may be helpful when adjusting the GamePieces in the movePlayer method. */
   
   /* Overwrite the GamePiece at the coordinates passed in with an empty GamePiece. */
   private void overwritePositionWithEmpty(int row, int col){
      gameBoard[row][col] = new EmptyPiece();
   }
   
   /* Overwrite the GamePiece at the new coordinates with the GamePiece at the 
      old coordinates. Place a new EmptyPiece at the old coordinates. */
   private void overwritePosition(int oldRow, int oldCol, int newRow, int newCol){
        gameBoard[newRow][newCol] = gameBoard[oldRow][oldCol];
        gameBoard[oldRow][oldCol] = new EmptyPiece();
   }
      
   /* Swap the position of the GamePiece at the current position with the 
    * GamePiece at the new position. */
   private void swapPosition(int curRow, int curCol, int newRow, int newCol){
      gameBoard[newRow][newCol] = gameBoard[curRow][curCol];
      gameBoard[curRow][curCol] = new EmptyPiece();
   }

}