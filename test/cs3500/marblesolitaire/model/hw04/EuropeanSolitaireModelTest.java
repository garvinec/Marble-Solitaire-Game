package cs3500.marblesolitaire.model.hw04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link EuropeanSolitaireModel}. Test if the model initializes correctly and that it
 * interacts with the methods properly.
 */
public class EuropeanSolitaireModelTest {

  private EuropeanSolitaireModel m1;
  private EuropeanSolitaireModel m2;
  private EuropeanSolitaireModel m3;
  private EuropeanSolitaireModel m4;

  @Before
  public void init() {
    this.m1 = new EuropeanSolitaireModel();
    this.m2 = new EuropeanSolitaireModel(2, 4);
    this.m3 = new EuropeanSolitaireModel(7);
    this.m4 = new EuropeanSolitaireModel(5, 2, 5);
  }

  // throws exception when the given empty slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlot() {
    new EuropeanSolitaireModel(-2, 3);
  }

  // throws exception when the given side length is negative
  @Test(expected = IllegalArgumentException.class)
  public void invalidNegativeSL() {
    new EuropeanSolitaireModel(-2);
  }

  // throws exception when the given side length is even
  @Test(expected = IllegalArgumentException.class)
  public void invalidEvenSL() {
    new EuropeanSolitaireModel(6);
  }

  // throws exception when the given empty slot and side length are invalid
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlotAndSL() {
    new EuropeanSolitaireModel(4, 0, 0);
  }

  // throws exception when only the given side length is invalid
  @Test(expected = IllegalArgumentException.class)
  public void invalidSLOnly() {
    new EuropeanSolitaireModel(4, 3, 3);
  }

  // throws exception when only the given empty slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlotOnly() {
    new EuropeanSolitaireModel(5, 0, 0);
  }

  @Test
  public void testConstructorInitialization() {
    // default constructor
    assertEquals(SlotState.Empty, m1.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, m1.getSlotAt(1, 1));
    assertEquals(SlotState.Invalid, m1.getSlotAt(5, 0));
    assertEquals(SlotState.Invalid, m1.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, m1.getSlotAt(1, 5));
    assertEquals(SlotState.Marble, m1.getSlotAt(2, 3));
    assertEquals(SlotState.Marble, m1.getSlotAt(0, 4));
    assertEquals(SlotState.Marble, m1.getSlotAt(6, 2));
    assertEquals(SlotState.Marble, m1.getSlotAt(3, 5));

    // constructor that takes in empty slot row and column
    assertEquals(SlotState.Empty, m2.getSlotAt(2, 4));
    assertEquals(SlotState.Invalid, m2.getSlotAt(5, 0));
    assertEquals(SlotState.Invalid, m2.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, m2.getSlotAt(1, 5));
    assertEquals(SlotState.Marble, m2.getSlotAt(2, 3));
    assertEquals(SlotState.Marble, m2.getSlotAt(0, 4));
    assertEquals(SlotState.Marble, m2.getSlotAt(6, 2));
    assertEquals(SlotState.Marble, m2.getSlotAt(3, 5));

    // constructor that takes in side length
    assertEquals(SlotState.Empty, m3.getSlotAt(9, 9));
    assertEquals(SlotState.Invalid, m3.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, m3.getSlotAt(5, 5));
    assertEquals(SlotState.Marble, m3.getSlotAt(13, 5));
    assertEquals(SlotState.Invalid, m3.getSlotAt(16, 17));
    assertEquals(SlotState.Marble, m3.getSlotAt(4, 15));

    // constructor that takes in side length and empty slot position
    assertEquals(SlotState.Empty, m4.getSlotAt(2, 5));
    assertEquals(SlotState.Marble, m4.getSlotAt(2, 2));
    assertEquals(SlotState.Invalid, m4.getSlotAt(1, 10));
    assertEquals(SlotState.Marble, m4.getSlotAt(9, 9));
    assertEquals(SlotState.Marble, m4.getSlotAt(9, 3));
  }

  @Test
  public void testMove() {

    // testing with the first constructor

    // move from top to bottom
    m1.move(1, 3, 3, 3);

    assertEquals(SlotState.Empty, m1.getSlotAt(1, 3));
    assertEquals(SlotState.Empty, m1.getSlotAt(2, 3));
    assertEquals(SlotState.Marble, m1.getSlotAt(3, 3));

    // move from bottom to top
    m1.move(4, 3, 2, 3);

    assertEquals(SlotState.Empty, m1.getSlotAt(4, 3));
    assertEquals(SlotState.Empty, m1.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, m1.getSlotAt(1, 3));
    assertEquals(SlotState.Marble, m1.getSlotAt(2, 3));

    // move from right to left
    m1.move(3, 5, 3, 3);

    assertEquals(SlotState.Empty, m1.getSlotAt(3, 5));
    assertEquals(SlotState.Empty, m1.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, m1.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, m1.getSlotAt(1, 3));
    assertEquals(SlotState.Empty, m1.getSlotAt(4, 3));

    // move from left to right
    m1.move(4, 1, 4, 3);

    assertEquals(SlotState.Marble, m1.getSlotAt(4, 3));
    assertEquals(SlotState.Empty, m1.getSlotAt(1, 3));
    assertEquals(SlotState.Empty, m1.getSlotAt(4, 2));
    assertEquals(SlotState.Empty, m1.getSlotAt(4, 1));

    // test move exceptions
    try {
      m1.move(3, 4, 3, 5);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move to its neighboring slots
    }
    try {
      m1.move(4, 4, 3, 3);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move diagonally
    }
    try {
      m1.move(3, 3, 3, 5);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move if the slot in between the destination and the current
      // position is empty
    }
    try {
      m1.move(4, 3, 3, 4);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move diagonally
    }

    // testing with the second constructor with the empty slot set at (2, 4)
    assertEquals(SlotState.Empty, m2.getSlotAt(2, 4));
    assertEquals(SlotState.Marble, m2.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, m2.getSlotAt(4, 4));

    m2.move(4, 4, 2, 4);

    assertEquals(SlotState.Marble, m2.getSlotAt(2, 4));
    assertEquals(SlotState.Empty, m2.getSlotAt(3, 4));
    assertEquals(SlotState.Empty, m2.getSlotAt(4, 4));

    // test move exceptions
    try {
      m2.move(3, 4, 3, 2);
    } catch (IllegalArgumentException e) {
      // Cannot move to a slot with marble
    }
    try {
      m2.move(2, 4, 4, 4);
    } catch (IllegalArgumentException e) {
      // Cannot move an empty slot
    }
  }

  @Test
  public void testIsGameOver() {

    // noMoves is when player has no more valid moves
    EuropeanSolitaireModel noMoves = new EuropeanSolitaireModel(1);

    assertTrue(noMoves.isGameOver());
    assertFalse(m1.isGameOver());
    assertFalse(m2.isGameOver());
    assertFalse(m3.isGameOver());
    assertFalse(m4.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, m1.getBoardSize());
    assertEquals(7, m2.getBoardSize());
    assertEquals(19, m3.getBoardSize());
    assertEquals(13, m4.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Empty, m1.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, m1.getSlotAt(1, 1));
    assertEquals(SlotState.Invalid, m1.getSlotAt(5, 0));

    assertEquals(SlotState.Empty, m2.getSlotAt(2, 4));
    assertEquals(SlotState.Invalid, m2.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, m2.getSlotAt(1, 5));

    assertEquals(SlotState.Empty, m3.getSlotAt(9, 9));
    assertEquals(SlotState.Marble, m3.getSlotAt(13, 5));
    assertEquals(SlotState.Invalid, m3.getSlotAt(16, 17));

    assertEquals(SlotState.Empty, m4.getSlotAt(2, 5));
    assertEquals(SlotState.Marble, m4.getSlotAt(2, 2));
    assertEquals(SlotState.Invalid, m4.getSlotAt(1, 10));

    // test exceptions when the given slot is beyond the board dimension
    try {
      m1.getSlotAt(0, 8);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      m1.getSlotAt(-1, 4);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      m1.getSlotAt(5, -2);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      m1.getSlotAt(7, 3);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
  }

  @Test
  public void testGetScore() {
    assertEquals(36, m1.getScore());
    m1.move(1, 3, 3, 3);
    assertEquals(35, m1.getScore());
    m1.move(2, 5, 2, 3);
    assertEquals(34, m1.getScore());

    assertEquals(36, m2.getScore());
    assertEquals(276, m3.getScore());
    assertEquals(128, m4.getScore());
  }
}