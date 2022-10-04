import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.Random;

/**
 * testing bishop class for its neccessary method.
 * 
 * @author hyojinkwak
 *
 */
public class BishopTest {
  int i;
  Random random = new Random();

  /**
   * testing constructor bishop with valid inputs.
   */
  @Test
  public void testConstructor() {
    Bishop piece = new Bishop(0, 0, Color.BLACK);

    assertEquals("row: 0 column: 0 color: BLACK", piece.toString());

    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Bishop piece1 = new Bishop(row, col, Color.BLACK);
      assertEquals("row: " + row + " column: " + col + " color: BLACK", piece1.toString());
    }

    for (i = 0; i < 500; i++) {
      int row = random.nextInt(8);
      int col = random.nextInt(8);
      Bishop piece1 = new Bishop(row, col, Color.WHITE);
      assertEquals("row: " + row + " column: " + col + " color: WHITE", piece1.toString());
    }
  }

  /**
   * testing when row and col are out of bound. should throw error.
   */
  @Test
  public void invalidConstructor() {
    
    try {
      new Bishop(-1, 100, Color.BLACK);
      new Bishop(0, 8, Color.WHITE);
      fail("exception should have thrown");
    } catch (IllegalArgumentException e) {
      i = 0;
      assertTrue(i <= 0);
    }
    try {
      new Bishop(7, 7, Color.BLACK);
      new Bishop(0, 0, Color.WHITE);
    } catch (IllegalArgumentException e) {
      fail("exception should not be thrown");
    }

    // lower bound
    for (i = 0; i < 500; i++) {
      int row = random.nextInt() * -10;
      int col = random.nextInt() * -10;
      try {
        new Bishop(row, col, Color.BLACK);
        new Bishop(row, col, Color.WHITE);
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
        new Bishop(row, col, Color.BLACK);
        new Bishop(row, col, Color.WHITE);
        fail("exception should have thrown");
      } catch (IllegalArgumentException e) {
        assertTrue(i >= 0);
      }
    }

  }

  /**
   * bishop should be able to move diagonally.
   */
  @Test
  public void testCanMove() {
    Bishop b1 = new Bishop(0, 0, Color.WHITE);
    Bishop b2 = new Bishop(7, 7, Color.BLACK);

    for (i = 1; i <= 7; i++) {
      assertEquals(true, b1.canMove(i, i));
    }
    for (i = 7; i <= 0; i--) {
      assertEquals(true, b2.canMove(i, i));
    }

    Bishop b3 = new Bishop(3, 5, Color.WHITE);
    assertEquals(false, b3.canMove(4, 5));
        
    assertEquals(false, b2.canMove(8, 8));

  }

  /**
   * bishop should be able to kill opponents (diff color) if it can move to that
   * position.
   */
  @Test
  public void testCanKil() {
    Bishop b1 = new Bishop(0, 0, Color.WHITE);
    Bishop b2 = new Bishop(7, 7, Color.BLACK);

    assertEquals(true, b1.canKill(b2));
    assertEquals(true, b2.canKill(b1));

    Bishop b3 = new Bishop(3, 5, Color.WHITE);

    assertEquals(false, b1.canKill(b3));

    Bishop b4 = new Bishop(4, 4, Color.BLACK);
    Bishop b5 = new Bishop(4, 4, Color.WHITE);

    assertEquals(true, b3.canKill(b4));
    assertEquals(true, b4.canKill(b3));

    // cannot kill same color.
    assertEquals(false, b3.canKill(b5));
    assertEquals(false, b5.canKill(b3));
  }

  /**
   * get column should return its col value.
   */
  @Test
  public void testGetCol() {
    Bishop b1 = new Bishop(0, 0, Color.WHITE);
    assertEquals(0, b1.getColumn());
    Bishop b2 = new Bishop(7, 7, Color.BLACK);
    assertEquals(7, b2.getColumn());
    Bishop b3 = new Bishop(3, 5, Color.WHITE);
    assertEquals(5, b3.getColumn());
    Bishop b4 = new Bishop(4, 4, Color.BLACK);
    assertEquals(4, b4.getColumn());
  }

  /**
   * object's row should be correct.
   */
  @Test
  public void testGetRow() {
    Bishop b1 = new Bishop(0, 0, Color.WHITE);
    assertEquals(0, b1.getRow());
    Bishop b2 = new Bishop(7, 7, Color.BLACK);
    assertEquals(7, b2.getRow());
    Bishop b3 = new Bishop(3, 5, Color.WHITE);
    assertEquals(3, b3.getRow());
    Bishop b4 = new Bishop(4, 4, Color.BLACK);
    assertEquals(4, b4.getRow());
  }

  /**
   * color of bishop sould be correct.
   */
  @Test
  public void testgetColor() {
    Bishop b1 = new Bishop(0, 0, Color.WHITE);
    assertEquals("WHITE", b1.getColor().toString());
    Bishop b2 = new Bishop(7, 7, Color.BLACK);
    assertEquals("BLACK", b2.getColor().toString());
    Bishop b3 = new Bishop(3, 5, Color.WHITE);
    assertEquals("WHITE", b3.getColor().toString());
    Bishop b4 = new Bishop(4, 4, Color.BLACK);
    assertEquals("BLACK", b4.getColor().toString());
  }

  /**
   * bishop's row, col, color should match.
   */
  @Test
  public void testToString() {
    Bishop p1 = new Bishop(6, 6, Color.BLACK);
    assertEquals("row: 6 column: 6 color: BLACK", p1.toString());
  }

}
