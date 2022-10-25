package cs3500.marblesolitaire.model.hw04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link TriangleSolitaireModel}. Test if the model initializes correctly and that it
 * interacts with the methods properly.
 */
public class TriangleSolitaireModelTest {

  private TriangleSolitaireModel t1;
  private TriangleSolitaireModel t2;
  private TriangleSolitaireModel t3;
  private TriangleSolitaireModel t4;

  @Before
  public void init() {
    this.t1 = new TriangleSolitaireModel();
    this.t2 = new TriangleSolitaireModel(2, 2);
    this.t3 = new TriangleSolitaireModel(7);
    this.t4 = new TriangleSolitaireModel(5, 4, 2);
  }

  // throws exception when it was given an invalid empty slot
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlot() {
    new TriangleSolitaireModel(-2, 3);
  }

  // throws exception when it was given a negative dimension
  @Test(expected = IllegalArgumentException.class)
  public void invalidNegativeDimension() {
    new TriangleSolitaireModel(-2);
  }

  // throws exception when the given empty slot and dimension are invalid
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlotAndDimension() {
    new TriangleSolitaireModel(-1, 0, 0);
  }

  // throws exception when only the given dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void invalidDimensionOnly() {
    new TriangleSolitaireModel(-2, 0, 0);
  }

  // throws exception when only the given empty slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlotOnly() {
    new TriangleSolitaireModel(6, 6, 5);
  }

  @Test
  public void testConstructorInitialization() {
    // default constructor
    assertEquals(SlotState.Empty, t1.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, t1.getSlotAt(1, 1));
    assertEquals(SlotState.Invalid, t1.getSlotAt(2, 3));

    // constructor that takes in empty slot row and column
    assertEquals(SlotState.Empty, t2.getSlotAt(2, 2));
    assertEquals(SlotState.Invalid, t2.getSlotAt(0, 1));
    assertEquals(SlotState.Marble, t2.getSlotAt(0, 0));

    // constructor that takes in side length
    assertEquals(SlotState.Empty, t3.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, t3.getSlotAt(5, 6));
    assertEquals(SlotState.Marble, t3.getSlotAt(4, 3));

    // constructor that takes in side length and empty slot position
    assertEquals(SlotState.Empty, t4.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, t4.getSlotAt(2, 2));
    assertEquals(SlotState.Invalid, t4.getSlotAt(1, 3));
  }

  @Test
  public void testMove() {

    // testing with the first constructor
    t1.move(2, 0, 0, 0);

    assertEquals(SlotState.Empty, t1.getSlotAt(1, 0));
    assertEquals(SlotState.Empty, t1.getSlotAt(2, 0));
    assertEquals(SlotState.Marble, t1.getSlotAt(0, 0));

    t1.move(4, 2, 2, 0);

    assertEquals(SlotState.Empty, t1.getSlotAt(1, 0));
    assertEquals(SlotState.Empty, t1.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, t1.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, t1.getSlotAt(2, 0));

    // test move exceptions
    try {
      t1.move(3, 4, 3, 5);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move to its neighboring slots
    }
    try {
      t1.move(2, 1, 0, 0);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move diagonally
    }
  }

  @Test
  public void testIsGameOver() {

    // noMoves is when player has no more valid moves
    TriangleSolitaireModel noMoves = new TriangleSolitaireModel(1);

    assertTrue(noMoves.isGameOver());

    assertFalse(t1.isGameOver());
    assertFalse(t2.isGameOver());
    assertFalse(t3.isGameOver());
    assertFalse(t4.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, t1.getBoardSize());
    assertEquals(5, t2.getBoardSize());
    assertEquals(7, t3.getBoardSize());
    assertEquals(5, t4.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    // default constructor
    assertEquals(SlotState.Empty, t1.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, t1.getSlotAt(1, 1));
    assertEquals(SlotState.Invalid, t1.getSlotAt(2, 3));

    // constructor that takes in empty slot row and column
    assertEquals(SlotState.Empty, t2.getSlotAt(2, 2));
    assertEquals(SlotState.Invalid, t2.getSlotAt(0, 1));
    assertEquals(SlotState.Marble, t2.getSlotAt(0, 0));

    // constructor that takes in side length
    assertEquals(SlotState.Empty, t3.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, t3.getSlotAt(5, 6));
    assertEquals(SlotState.Marble, t3.getSlotAt(4, 3));

    // constructor that takes in side length and empty slot position
    assertEquals(SlotState.Empty, t4.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, t4.getSlotAt(2, 2));
    assertEquals(SlotState.Invalid, t4.getSlotAt(1, 3));

    // test exceptions when the given slot is beyond the board dimension
    try {
      t1.getSlotAt(0, 8);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      t1.getSlotAt(-1, 4);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      t1.getSlotAt(5, -2);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      t1.getSlotAt(7, 3);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
  }

  @Test
  public void testGetScore() {
    assertEquals(14, t1.getScore());
    t1.move(2, 2, 0, 0);
    assertEquals(13, t1.getScore());
    t1.move(2, 0, 2, 2);
    assertEquals(12, t1.getScore());

    assertEquals(14, t2.getScore());
    assertEquals(27, t3.getScore());
    assertEquals(14, t4.getScore());
  }
}