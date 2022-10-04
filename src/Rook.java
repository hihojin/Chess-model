/**
 * rook class; a chess piece type class.
 * @author hyojinkwak
 *
 */
public class Rook extends AbstractChessPiece {

  /**
   * constructor creates rook piece and calls the super constructor.
   * @param row row of rook
   * @param column col of rook
   * @param pieceColor color of rook
   * @throws IllegalArgumentException when row, col are out of bounds.
   */
  public Rook(int row, int column, Color pieceColor) throws IllegalArgumentException {
    super(row, column, pieceColor);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (AbstractChessPiece.boundCheck(row, col)) {
      return false;
    }
    
    return this.horizontal(row, col) || this.vertical(row, col);
  }

}
