/**
 * this class represents pawn chess piece.
 * 
 * @author hyojinkwak
 *
 */
public class Pawn extends AbstractChessPiece {

  /**
   * this constructor creates a pawn object.
   * 
   * @param row        row of this pawn
   * @param column     col of this pawn
   * @param pieceColor color of this pawn
   * @throws IllegalArgumentException exception is thrown in super class when row,
   *                                  col are out of bound.
   */
  public Pawn(int row, int column, Color pieceColor) throws IllegalArgumentException {
    super(row, column, pieceColor);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (AbstractChessPiece.boundCheck(row, col)) {
      return false;
    }
    // pawn can move only forward (not backwards towards where its color started)
    // vertically.
    // not using abs value here as that will allow movement back and forth.
    if (this.getColor() == Color.BLACK && this.row - row == 1 && this.column == col) {
      return true;
    }

    return (this.getColor() == Color.WHITE && row - this.row == 1 && this.column == col);

  }

  /**
   * pawn is unique in its killing ability of another piece. It cannot kill an
   * opponent piece by its regular canMove() method. Pawn can kill by moving one
   * place forward diagonally. Therefore, redefining pawn's canKill method in its
   * own class.
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    if (AbstractChessPiece.boundCheck(piece.getRow(), piece.getColumn())) {
      return false;
    }
    if (this.getColor() == piece.getColor()) {
      return false;
    }
    if (this.getColor() == Color.WHITE && piece.getRow() - this.row == 1
        && Math.abs(piece.getColumn() - this.column) == 1) {
      return true;
    }
    return (this.getColor() == Color.BLACK && this.row - piece.getRow() == 1
        && Math.abs(this.column - piece.getColumn()) == 1);
  }
}
