import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.Random;

/**
 * testing methods in queen class and its necessary methods. 
 * @author hyojinkwak
 *
 */
public class QueenTest {
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
      Queen piece1 = new Queen(row, col, Color.BLACK);
      assertEquals("row: " + row + " column: " + col + " color: BLACK", piece1.toString());
    }

    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Queen piece1 = new Queen(row, col, Color.WHITE);
      assertEquals("row: " + row + " column: " + col + " color: WHITE", piece1.toString());
    }
    
    Queen piece1 = new Queen(1, 1, Color.WHITE);
    assertEquals("row: 1 column: 1 color: WHITE", piece1.toString());
  }
  
  
  /**
   * testing when row and col are out of bound. should throw error.
   */
  @Test
  public void invalidConstructor() {
    try {
      new Queen(-1, 100, Color.BLACK);
      new Queen(0, 8, Color.WHITE);
      fail("exception should have thrown");
    }
    catch (IllegalArgumentException e) {
      i = 0;
      assertTrue(i <= 0);
    }
    try {
      new Queen(7, 7, Color.BLACK);
      new Queen(0, 0, Color.WHITE);
    }
    catch (IllegalArgumentException e) {
      fail("exception should not be thrown");
    }
    
    // lower bound
    for (i = 0; i < 500; i++) {
      int row = random.nextInt() * -10;
      int col = random.nextInt() * -10;
      try {
        new Queen(row, col, Color.BLACK);
        new Queen(row, col, Color.WHITE);
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
        new Queen(row, col, Color.BLACK);
        new Queen(row, col, Color.WHITE);
        fail("exception should have thrown");
      } catch (IllegalArgumentException e) {
        assertTrue(i >= 0);
      }
    }
  }
  
  /**
   * queen can move horizontal, vertical, diagonal.
   */
  @Test
  public void testCanMove() {
    Queen q1 = new Queen(1, 1, Color.WHITE);
    Queen q2 = new Queen(7, 2, Color.BLACK);
    
    assertEquals(true, q1.canMove(1, 2)); // horizontal
    assertEquals(true, q1.canMove(1, 7));
    assertEquals(true, q2.canMove(7, 7));
   
    assertEquals(true, q1.canMove(2, 1)); // vertical
    assertEquals(true, q1.canMove(7, 1));
    assertEquals(true, q2.canMove(4, 2));
    
    assertEquals(true, q1.canMove(2, 2)); // diagonal
    assertEquals(true, q1.canMove(3, 3));
    assertEquals(true, q2.canMove(5, 0));
    
    assertEquals(false, q2.canMove(9, 8));
    assertEquals(false, q1.canMove(3, 7));
  }
  
  /**
   * queen can kill opponents(diff color) and if it can move to that position.
   */
  @Test
  public void testCanKill() {
    Queen q1 = new Queen(1, 1, Color.WHITE);
    Queen q2 = new Queen(7, 2, Color.BLACK);
    
    assertEquals(false, q1.canKill(q2));
    assertEquals(false, q2.canKill(q1));
    
    Queen q3 = new Queen(2, 2, Color.BLACK); // diagonal
    assertEquals(true, q1.canKill(q3));
    assertEquals(true, q3.canKill(q1));
    
    Queen q4 = new Queen(1, 2, Color.BLACK); // horizontal
    assertEquals(true, q4.canKill(q1));
    assertEquals(true, q1.canKill(q4));
    
    Queen q5 = new Queen(5, 1, Color.BLACK); // vertical
    assertEquals(true, q5.canKill(q1));
    assertEquals(true, q1.canKill(q5));
  }
  
  /**
   * getting column values.
   */
  @Test
  public void testGetCol() {
    Queen q1 = new Queen(1, 1, Color.WHITE);
    Queen q2 = new Queen(7, 2, Color.BLACK);
    assertEquals(1, q1.getColumn());
    assertEquals(2, q2.getColumn());
  }
  
  /**
   * getting row values.
   */
  @Test
  public void testGetRow() {
    Queen q = new Queen(2, 2, Color.BLACK);
    assertEquals(2, q.getRow());
    Queen q2 = new Queen(7, 2, Color.BLACK);
    assertEquals(7, q2.getRow());
  }
  
  /**
   * getting color of queen piece.
   */
  @Test
  public void testGetColor() {
    Queen q = new Queen(2, 2, Color.BLACK);
    assertEquals("BLACK", q.getColor().toString());
    Queen q2 = new Queen(5, 1, Color.WHITE);
    assertEquals("WHITE", q2.getColor().toString());
  }
  
  /**
   * string representation of queen should match with values we put it when creating objects.
   */
  @Test
  public void testToString() {
    Queen q1 = new Queen(5, 1, Color.WHITE);
    assertEquals("row: 5 column: 1 color: WHITE", q1.toString());
    
    Queen q2 = new Queen(0, 0, Color.BLACK);
    assertEquals("row: 0 column: 0 color: BLACK", q2.toString());
  }

}
