import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.Random;

/**
 * testing pawn class's method.
 * @author hyojinkwak
 *
 */
public class PawnTest {

  Random random = new Random();
  int i;

  /**
   * testing if constructor of pawn class correctly sets its value to provided
   * ones. testing constructor when row and columns are valid
   */
  @Test
  public void testConstructor() {
    Pawn piece = new Pawn(0, 0, Color.BLACK);

    assertEquals("row: 0 column: 0 color: BLACK", piece.toString());

    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Pawn piece1 = new Pawn(row, col, Color.BLACK);
      assertEquals("row: " + row + " column: " + col + " color: BLACK",
          piece1.toString());
    }
    
    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Pawn piece1 = new Pawn(row, col, Color.WHITE);
      assertEquals("row: " + row + " column: " + col + " color: WHITE",
          piece1.toString());
    }
  }
  
  /**
   * testing when row and col are out of bound. should throw error.
   */
  @Test
  public void invalidConstructor() {
    try {
      new Pawn(-1, 100, Color.BLACK);
      new Pawn(8, 8, Color.WHITE);
      fail("exception should have thrown");
    }
    catch (IllegalArgumentException e) {
      assertTrue(i >= 0);
    }
    try {
      new Pawn(7, 7, Color.BLACK);
      new Pawn(0, 0, Color.WHITE);
    }
    catch (IllegalArgumentException e) {
      fail("exception should not be thrown");
    }
    
    // lower bound
    for (i = 0; i < 500; i++) {
      int row = random.nextInt() * -10;
      int col = random.nextInt() * -10;
      try {
        new Pawn(row, col, Color.BLACK);
        new Pawn(row, col, Color.WHITE);
        fail("exception should have thrown");
      }
      catch (IllegalArgumentException e) {
        assertTrue(i >= 0);
      }
    }
    
    // lower and upper bound
    for (i = 0; i < 500; i++) {
      int row = random.nextInt() * 100;
      int col = random.nextInt() * -10;
      try {
        new Pawn(row, col, Color.BLACK);
        new Pawn(row, col, Color.WHITE);
        fail("exception should have thrown");
      }
      catch (IllegalArgumentException e) {
        assertTrue(i >= 0);
      }
    }
    
  }
  
  /**
   * pawn should move foward in the same column, not backward 
   * or elsewhere is allowed.
   * black pieces are traditionally are arranged in the top two rows on board.
   */
  @Test
  public void testCanMove() {
    
    Pawn p1 = new Pawn(6,7, Color.BLACK);
    // if out of bound, return false
    assertEquals(false, p1.canMove(8, 8));
    
    assertEquals(false, p1.canMove(6, 7)); // cannot move from origin to origin
    assertEquals(true, p1.canMove(5, 7)); // black can move down 1.
    assertEquals(false, p1.canMove(7, 7));
    assertEquals(false, p1.canMove(4, 7));
    assertEquals(false, p1.canMove(5, 5));
    
    Pawn p2 = new Pawn(2, 2, Color.WHITE);
    
    assertEquals(true, p2.canMove(3, 2)); // white can move up 1.
    assertEquals(false, p2.canMove(4, 2));
    assertEquals(false, p2.canMove(1, 2));
    assertEquals(false, p2.canMove(2, 3));
    assertEquals(false, p2.canMove(7, 7));
  }
  
  /**
   * pawn can kill opponent's piece by moving one place forward diagonally.
   * it can not move elsewhere.
   */
  @Test
  public void testCanKill() {
    Pawn p1 = new Pawn(6, 6, Color.BLACK);
    Pawn p2 = new Pawn(5, 5, Color.WHITE);
    
    assertEquals(false, p1.canKill(p1));
    
    // symmetric
    assertEquals(true, p1.canKill(p2));
    // white kills black, moving forward diagonally
    assertEquals(true, p2.canKill(p1));
    
    Pawn p3 = new Pawn(7, 7, Color.WHITE);
    Pawn p4 = new Pawn(6, 6, Color.BLACK);
    // black cannot move backward diagonally
    assertEquals(false, p4.canKill(p3));
    //white cannot move backward diagonally
    assertEquals(false, p3.canKill(p4));
    
    Pawn p5 = new Pawn(4, 5, Color.WHITE);
    // cannot kill the same team memeber (white)
    assertEquals(false, p5.canKill(p2));
    assertEquals(false, p2.canKill(p5));
    
    Pawn p6 = new Pawn(4, 4, Color.BLACK);
    assertEquals(false, p5.canKill(p6));
    
    Pawn p7 = new Pawn(1, 0, Color.BLACK);
    Pawn p8 = new Pawn(0, 1, Color.WHITE);
    assertEquals(true, p7.canKill(p8));
    
    Pawn p9 = new Pawn(0, 0, Color.WHITE);
    Pawn p10 = new Pawn(1, 1, Color.BLACK);
    assertEquals(true, p9.canKill(p10));
    
  }
  
  // get column
  /**
   * testing get column method of pawn.
   */
  @Test
  public void testGetColumn() {
    Pawn p1 = new Pawn(6, 6, Color.BLACK);
    assertEquals(6, p1.getColumn());
    
    Pawn p2 = new Pawn(3, 1, Color.WHITE);
    assertEquals(1, p2.getColumn());
    
    Pawn p3 = new Pawn(0, 3, Color.WHITE);
    assertEquals(3, p3.getColumn());
    
    Pawn p4 = new Pawn(6, 5, Color.BLACK);
    assertEquals(5, p4.getColumn());
  }
  
  // get row
  /**
   * testing get row method of pawn.
   */
  @Test
  public void testGetRow() {
    Pawn p1 = new Pawn(6, 6, Color.BLACK);
    assertEquals(6, p1.getRow());
    
    Pawn p2 = new Pawn(3, 1, Color.WHITE);
    assertEquals(3, p2.getRow());
    
    Pawn p3 = new Pawn(0, 3, Color.WHITE);
    assertEquals(0, p3.getRow());
    
    Pawn p4 = new Pawn(7, 5, Color.BLACK);
    assertEquals(7, p4.getRow());
  }
  
  // get color
  /**
   * testing get color method of pawn.
   */
  @Test
  public void testGetColor() {
    Pawn p1 = new Pawn(6, 6, Color.BLACK);
    assertEquals("BLACK", p1.getColor().toString());
    
    Pawn p2 = new Pawn(3, 1, Color.WHITE);
    assertEquals("WHITE", p2.getColor().toString());
    
    Pawn p3 = new Pawn(0, 3, Color.WHITE);
    assertEquals("WHITE", p3.getColor().toString());
    
    Pawn p4 = new Pawn(7, 5, Color.BLACK);
    assertEquals("BLACK", p4.getColor().toString());
  }
  
  //toString
  /**
   * testing to string method correctly represents pawn objects.
   */
  @Test
  public void testToString() {
    Pawn p1 = new Pawn(1, 1, Color.BLACK);    
    Pawn p2 = new Pawn(2, 2, Color.WHITE);
    assertEquals("row: 1 column: 1 color: BLACK", p1.toString());
    assertEquals("row: 2 column: 2 color: WHITE", p2.toString());
  }
  
  //boundCheck
  /**
   * test if given row, col is out of bound. true if it is out of bound,
   * false if it is in-bound.
   */
  @Test
  public void testBoundCheck() {
    assertEquals(false, AbstractChessPiece.boundCheck(0, 0));
    assertEquals(false, AbstractChessPiece.boundCheck(7, 7));
    assertEquals(false, AbstractChessPiece.boundCheck(0, 7));
    assertEquals(false, AbstractChessPiece.boundCheck(7, 0));
    assertEquals(true, AbstractChessPiece.boundCheck(8, 8));
    assertEquals(true, AbstractChessPiece.boundCheck(-1, 0));
    assertEquals(true, AbstractChessPiece.boundCheck(-10, -10));
  }

}
