/**
 * one of ChessPiece type - bishop piece class.
 * @author hyojinkwak
 *
 */
public class Bishop extends AbstractChessPiece {
  
  /**
   * creates bishop objects and calls super class constructor.
   * @param row row of this bishop piece
   * @param col col of this bishop piece
   * @param color color of this bishop piece
   * @throws IllegalArgumentException super constructor throws error when row and col are out
   *          of bound. 
   */
  public Bishop(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);    
  }

  @Override
  public boolean canMove(int row, int col) {
    if (AbstractChessPiece.boundCheck(row, col)) {
      return false;
    }
    return this.diagonal(row, col);
  }

}
