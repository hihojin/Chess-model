/**
 * ChessPiece type knight class.
 * 
 * @author hyojinkwak
 *
 */
public class Knight extends AbstractChessPiece {
  /**
   * creates knight piece object.
   * 
   * @param row        this row of current piece
   * @param column     this row of current piece
   * @param pieceColor piece color of knight
   * @throws IllegalArgumentException exception thrown in abstract super class.
   */
  public Knight(int row, int column, Color pieceColor) throws IllegalArgumentException {
    super(row, column, pieceColor);
  }

  @Override
  public boolean canMove(int row, int col) {
    // if it returned true -- means it is out of bound. so if it is out of bound return false.
    if (AbstractChessPiece.boundCheck(row, col)) {
      return false;
    }
    
    // knight can either move 2 horizontally and 1 vertically at once
    // ove move 1 horizontally and 2 vertically at once.
    // ex. (0, 0) -> (2, 1) or (7, 7)-> (5,6)
    return (Math.abs(this.row - row) == 2 && Math.abs(this.column - col) == 1
            || Math.abs(this.row - row) == 1 && Math.abs(this.column - col) == 2);

  }

}
