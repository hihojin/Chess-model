import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.Random;

/**
 * testing methods for knight chess piece.
 * 
 * @author hyojinkwak
 *
 */
public class KnightTest {
  Random random = new Random();
  int i;

  /**
   * testing if constructor of knight class correctly sets its value to provided
   * ones. testing constructor when row and columns are valid
   */
  @Test
  public void testConstructor() {
    Knight piece = new Knight(1, 0, Color.BLACK);

    assertEquals("row: 1 column: 0 color: BLACK", piece.toString());

    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Knight piece1 = new Knight(row, col, Color.BLACK);
      assertEquals("row: " + row + " column: " + col + " color: BLACK",
          piece1.toString());
    }
    
    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Knight piece1 = new Knight(row, col, Color.WHITE);
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
      new Knight(-1, 100, Color.BLACK);
      new Knight(8, 8, Color.WHITE);
      fail("exception should have thrown");
    }
    catch (IllegalArgumentException e) {
      assertTrue(i >= 0);
    }
    try {
      new Knight(7, 7, Color.BLACK);
      new Knight(0, 0, Color.WHITE);
    }
    catch (IllegalArgumentException e) {
      fail("exception should not be thrown");
    }
    
    // lower bound
    for (i = 0; i < 500; i++) {
      int row = random.nextInt() * -10;
      int col = random.nextInt() * -10;
      try {
        new Knight(row, col, Color.BLACK);
        new Knight(row, col, Color.WHITE);
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
        new Knight(row, col, Color.BLACK);
        new Knight(row, col, Color.WHITE);
        fail("exception should have thrown");
      }
      catch (IllegalArgumentException e) {
        assertTrue(i >= 0);
      }
    }
    
  }
  
  /**
   * knight can move 2 horizontal and 1 vertical or vice versa.
   */
  @Test
  public void testCanMove() {
    Knight p1 = new Knight(7, 7, Color.BLACK);
    assertEquals(false, p1.canMove(7, 7)); // a knight cannot move from origin to origin.
    
    assertEquals(true, p1.canMove(5, 6)); 
    assertEquals(true, p1.canMove(6, 5));
    
    assertEquals(false, p1.canMove(6, 6)); // diagonal is not allowed for knight
    assertEquals(false, p1.canMove(8, 9)); // out of bound --> false
    assertEquals(false, p1.canMove(0, 7));
    
    Knight p2 = new Knight(0, 0, Color.WHITE);
    assertEquals(true, p2.canMove(2, 1));
    assertEquals(true, p2.canMove(1, 2));
    assertEquals(false, p1.canMove(-1, -1));
  }
  
  /**
   * get column should return this piece's column.
   */
  @Test
  public void testGetCol() {
    Knight p1 = new Knight(6, 6, Color.BLACK);
    assertEquals(6, p1.getColumn());
    
    Knight p2 = new Knight(0, 1, Color.WHITE);
    assertEquals(1, p2.getColumn());
    
    Knight p3 = new Knight(0, 6, Color.WHITE);
    assertEquals(6, p3.getColumn());
    
    Knight p4 = new Knight(6, 1, Color.BLACK);
    assertEquals(1, p4.getColumn());
  }
  
  /**
   * get row should return this piece's column.
   */
  @Test
  public void testGetRow() {
    Knight p1 = new Knight(5, 6, Color.BLACK);
    assertEquals(5, p1.getRow());
    
    Knight p2 = new Knight(3, 1, Color.WHITE);
    assertEquals(3, p2.getRow());
    
    Knight p3 = new Knight(7, 6, Color.WHITE);
    assertEquals(7, p3.getRow());
    
    Knight p4 = new Knight(1, 1, Color.BLACK);
    assertEquals(1, p4.getRow());
  }
  
  /**
   * get color should return this piece's column.
   */
  @Test
  public void testGetColor() {
    Knight p1 = new Knight(5, 6, Color.BLACK);
    assertEquals("BLACK", p1.getColor().toString());
    
    Knight p2 = new Knight(3, 1, Color.WHITE);
    assertEquals("WHITE", p2.getColor().toString());
    
    Knight p3 = new Knight(7, 6, Color.WHITE);
    assertEquals("WHITE", p3.getColor().toString());
    
    Knight p4 = new Knight(1, 1, Color.BLACK);
    assertEquals("BLACK", p4.getColor().toString());
  }

  /**
   * see if two knights on board are diagonal.
   */
  @Test
  public void testCanKill() {
    Knight p1 = new Knight(7, 6, Color.BLACK);
    Knight p2 = new Knight(6, 4, Color.WHITE);
    assertEquals(true, p1.canKill(p2));
    assertEquals(true, p2.canKill(p1));
    
    try {
      Knight p2_1 = new Knight(8, 8, Color.WHITE);
      assertEquals(false, p1.canKill(p2_1));
      fail("exception should have thrown");
    } catch (IllegalArgumentException e) {
      assertTrue(p1.equals(p1));
    }
    
    Knight p3 = new Knight(0, 1, Color.BLACK);
    Knight p4 = new Knight(1, 3, Color.WHITE);
    assertEquals(true, p3.canKill(p4));
    assertEquals(true, p4.canKill(p3));
    
    Knight p5 = new Knight(2, 2, Color.BLACK);
    Knight p6 = new Knight(4, 1, Color.WHITE);
    assertEquals(true, p5.canKill(p6));
    assertEquals(true, p6.canKill(p5));
    
    Knight p6_1 = new Knight(3, 4, Color.WHITE);
    assertEquals(true, p5.canKill(p6_1));
    assertEquals(true, p6_1.canKill(p5));
    
    Knight p7 = new Knight(6, 0, Color.BLACK);
    Knight p8 = new Knight(4, 1, Color.WHITE);
    assertEquals(true, p7.canKill(p8));
    assertEquals(true, p8.canKill(p7));
    
    Knight p9 = new Knight(6, 0, Color.BLACK);
    Knight p10 = new Knight(1, 1, Color.WHITE);
    assertEquals(false, p10.canKill(p9));
    assertEquals(false, p9.canKill(p10));
  }
  
  //to string
  /**
   * to string represents string representation of knight. shows row, col, and color.
   */
  @Test
  public void testToString() {
    Knight p1 = new Knight(7, 6, Color.BLACK);
    Knight p2 = new Knight(6, 4, Color.WHITE);
    assertEquals("row: 7 column: 6 color: BLACK", p1.toString());
    assertEquals("row: 6 column: 4 color: WHITE", p2.toString());
  }

}
