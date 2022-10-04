/**
 * a class for queen; a chess piece type.
 * @author hyojinkwak
 *
 */
public class Queen extends AbstractChessPiece {

  /**
   * creates queen piece object, calls super constructor, checks row, col validation.
   * @param row row of this queen
   * @param column col of this queen
   * @param pieceColor color of queen piece
   * @throws IllegalArgumentException throws exception when row, col are out of bound.
   */
  public Queen(int row, int column, Color pieceColor) throws IllegalArgumentException {
    super(row, column, pieceColor);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (AbstractChessPiece.boundCheck(row, col)) {
      return false;
    }
    return this.diagonal(row, col) || this.horizontal(row, col) || this.vertical(row, col) ;
  }

}
