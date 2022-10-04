import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.Random;

/**
 * testing rook's methods in rook's class. 
 * @author hyojinkwak
 *
 */
public class RookTest {
  int i;
  Random random = new Random();

  /**
   * testing constructor with valid inputs.
   */
  @Test
  public void testConstructor() {
    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Rook piece1 = new Rook(row, col, Color.BLACK);
      assertEquals("row: " + row + " column: " + col + " color: BLACK", piece1.toString());
    }

    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Rook piece1 = new Rook(row, col, Color.WHITE);
      assertEquals("row: " + row + " column: " + col + " color: WHITE", piece1.toString());
    }
    
    Rook piece1 = new Rook(0, 0, Color.WHITE);
    assertEquals("row: 0 column: 0 color: WHITE", piece1.toString());
    
    Rook piece2 = new Rook(0, 7, Color.BLACK);
    assertEquals("row: 0 column: 7 color: BLACK", piece2.toString());
    
    Rook piece3 = new Rook(7, 0, Color.BLACK);
    assertEquals("row: 7 column: 0 color: BLACK", piece3.toString());
    
    Rook piece4 = new Rook(7, 7, Color.BLACK);
    assertEquals("row: 7 column: 7 color: BLACK", piece4.toString());
  }
  
  /**
   * testing when row and col are out of bound. should throw error.
   */
  @Test
  public void invalidConstructor() {
    try {
      new Rook(-1, 100, Color.BLACK);
      new Rook(9, 8, Color.WHITE);
      fail("exception should have thrown");
    }
    catch (IllegalArgumentException e) {
      i = 0;
      assertTrue(i <= 0);
    }
    try {
      new Rook(7, 7, Color.BLACK);
      new Rook(0, 0, Color.WHITE);
    }
    catch (IllegalArgumentException e) {
      fail("exception should not be thrown");
    }
    
    // lower bound
    for (i = 0; i < 500; i++) {
      int row = random.nextInt() * -10;
      int col = random.nextInt() * -10;
      try {
        new Rook(row, col, Color.BLACK);
        new Rook(row, col, Color.WHITE);
        fail("exception should have thrown");
      } catch (IllegalArgumentException e) {
        assertTrue(i >= 0);
      }
    }

    // lower and upper bound
    for (i = 0; i < 500; i++) {
      int row = random.nextInt() * 100;
      int col = random.nextInt() * -10;
      try {
        new Rook(row, col, Color.BLACK);
        new Rook(row, col, Color.WHITE);
        fail("exception should have thrown");
      } catch (IllegalArgumentException e) {
        assertTrue(i >= 0);
      }
    }
  }
  
  /**
   * rook can move vertical and horizontal.
   */
  @Test
  public void testCanMove() {
    Rook q1 = new Rook(1, 1, Color.WHITE);
    Rook q2 = new Rook(7, 2, Color.BLACK);
    
    assertEquals(true, q1.canMove(1, 2)); // horizontal
    assertEquals(true, q1.canMove(1, 7));
    assertEquals(true, q2.canMove(7, 7));
   
    assertEquals(true, q1.canMove(2, 1)); // vertical
    assertEquals(true, q1.canMove(7, 1));
    assertEquals(true, q2.canMove(4, 2));
    
    assertEquals(false, q2.canMove(9, 8)); // out of bound should return false
    assertEquals(false, q1.canMove(3, 7));
  }
  
  /**
   * rook can kill opponents (diff color) by moving vertical and horizontal.
   */
  @Test
  public void testCanKill() {
    Rook q1 = new Rook(4, 4, Color.WHITE);
    Rook q2 = new Rook(4, 2, Color.BLACK);
    Rook q4 = new Rook(4, 7, Color.BLACK);
    
    assertEquals(true, q1.canKill(q2)); // horizontal
    assertEquals(true, q2.canKill(q1));
    assertEquals(true, q1.canKill(q4));
    assertEquals(true, q4.canKill(q1));
    
    Rook q3 = new Rook(1, 4, Color.BLACK);
   
    assertEquals(true, q1.canKill(q3)); // vertical
    assertEquals(true, q3.canKill(q1));
    assertEquals(false, q2.canKill(q3));
    assertEquals(false, q3.canKill(q2)); 
  }
  
  /**
   * getting column values.
   */
  @Test
  public void testGetCol() {
    Rook q1 = new Rook(1, 1, Color.WHITE);
    Rook q2 = new Rook(7, 2, Color.BLACK);
    assertEquals(1, q1.getColumn());
    assertEquals(2, q2.getColumn());
  }
  
  /**
   * getting row values.
   */
  @Test
  public void testGetRow() {
    Rook q = new Rook(2, 2, Color.BLACK);
    assertEquals(2, q.getRow());
    Rook q2 = new Rook(7, 2, Color.BLACK);
    assertEquals(7, q2.getRow());
  }
  
  /**
   * getting color of queen piece.
   */
  @Test
  public void testGetColor() {
    Rook q = new Rook(2, 2, Color.BLACK);
    assertEquals("BLACK", q.getColor().toString());
    Rook q2 = new Rook(5, 1, Color.WHITE);
    assertEquals("WHITE", q2.getColor().toString());
  }
  
  /**
   * string representation of queen should match with values we put it when creating objects.
   */
  @Test
  public void testToString() {
    Rook q1 = new Rook(5, 1, Color.WHITE);
    assertEquals("row: 5 column: 1 color: WHITE", q1.toString());
    
    Rook q2 = new Rook(0, 0, Color.BLACK);
    assertEquals("row: 0 column: 0 color: BLACK", q2.toString());
  }


}
