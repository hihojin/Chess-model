/**
 * interface for chess pieces. Includes common operations.
 * @author hyojinkwak
 *
 */
public interface ChessPiece {
  /**
   * returns its current position on chess board (row).
   * @return return this row.
   */
  public int getRow();
  
  /**
   * returns its current position on chess board (column).
   * @return this column.
   */
  public int getColumn();
  
  /**
   * gets the enum color: black and white.
   * @return the enum color black or white.
   */
  public Color getColor();
  
  /**
   * determines if a piece can move to a given row, col.
   * @param row this row.
   * @param col this column.
   * @return boolean true or false.
   */
  public boolean canMove(int row, int col);
  
  /**
   * determines if a piece can kill a provided piece from where it currently is.
   * a piece can kill the opponent piece: other color and can move to that position.
   * @param piece a piece provided to kill.
   * @return true or false.
   */
  public boolean canKill(ChessPiece piece);
  

}
