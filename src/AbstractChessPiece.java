/**
 * abstract class for chess pieces. This will have fields and methods that pieces share.
 * @author hyojinkwak
 */
public abstract class AbstractChessPiece implements ChessPiece {
  // all ChessPiece type share these fields.
  protected int row;
  protected int column;
  protected Color pieceColor;
  
  protected static final int ROWUPPERBOUND = 7;
  protected static final int COLUMUPPDERBOUND = 7;
  protected static final int ROWLOWERBOUND = 0;
  protected static final int COLUMLOWERBOUND = 0;
  
  
  /**
   * constructor of abstract chess piece class initializes the common fields with provided values.
   * @param row row of this piece.
   * @param column column of this piece.
   * @param pieceColor color of this chess piece.
   */
  public AbstractChessPiece(int row, int column, Color pieceColor) throws IllegalArgumentException {
    
    if (this.row > ROWUPPERBOUND || this.column >  COLUMUPPDERBOUND) {
      throw new IllegalArgumentException("row and column should not go over 7");
    }
    if (this.row < ROWLOWERBOUND || this.column < COLUMLOWERBOUND) {
      throw new IllegalArgumentException("row and column should not go below 0");
    }
    
    this.row = row;
    this.column = column;
    this.pieceColor = pieceColor;
  }
  
  @Override
  public int getColumn() {
    return this.column;
  }
  
  @Override
  public int getRow() {
    return this.row;
  }
  
  @Override
  public Color getColor() {
    return this.pieceColor;
  }
  
  /**
   * checking if pieces are on horizontal. (used for queen, bishop, rook as knight and pawn
   * have its unique move behavior and kill behavior for pawn as well.)
   * this method is going to be called only after when bound check validation is done. So,
   * I am not re-checking the bound here again.
   * @return return true if two pieces are on horizontal, else false.
   */
  protected boolean horizontal(int row, int col) {
    return (this.row == row && this.column != col);
  }
  
  /**
   * checking if pieces are on vertical. (used for queen, bishop, rook as knight and pawn
   * have its unique move behavior and kill behavior for pawn as well.)
   * this method is going to be called only after when bound check validation is done. So,
   * I am not re-checking the bound here again.
   * @return true if two pieces are on vertical else, return false.
   */
  protected boolean vertical(int row, int col) {
    return (this.row != row && this.column == col);
  }
  
  /**
   * checking if pieces are on diagonal. (used for queen, bishop, rook as knight and pawn
   * have its unique move behavior and kill behavior for pawn as well.)
   * this method is going to be called only after when bound check validation is done. So,
   * I am not re-checking the bound here again.
   * @return true if two pieces are on diagonal else, return false.
   */
  protected boolean diagonal(int row, int col) {
    return (Math.abs(this.row - row) == Math.abs(this.column - col));
  }
  
  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.pieceColor != piece.getColor() && this.canMove(piece.getRow(), piece.getColumn()));
  }
  
  @Override
  public String toString() {
    return "row: " + this.getRow() + " column: " + this.getColumn() + " color: " + this.getColor();
  }
  
  /**
   * check if prodived row and col cell is out of bound.
   * static method as this condition applies to all object of knights or Chesspiece type.
   * @param row destination row
   * @param col destination col
   * @return false if out of bound
   */
  protected static boolean boundCheck(int row, int col) {
    return (row > ROWUPPERBOUND || col > COLUMUPPDERBOUND || row < ROWLOWERBOUND
        || col < COLUMLOWERBOUND);
  }
  
  
}
