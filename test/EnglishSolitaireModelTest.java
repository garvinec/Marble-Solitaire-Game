import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link EnglishSolitaireModel}.
 * Test if the model initializes correctly and that it interacts with the methods properly.
 */
public class EnglishSolitaireModelTest {

  private EnglishSolitaireModel e1;
  private EnglishSolitaireModel e2;
  private EnglishSolitaireModel e3;
  private EnglishSolitaireModel e4;

  @Before
  public void init() {
    this.e1 = new EnglishSolitaireModel();
    this.e2 = new EnglishSolitaireModel(2, 4);
    this.e3 = new EnglishSolitaireModel(7);
    this.e4 = new EnglishSolitaireModel(5, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlot() {
    new EnglishSolitaireModel(-2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidNegativeAT() {
    new EnglishSolitaireModel(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEvenAT() {
    new EnglishSolitaireModel(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlotAndAT() {
    new EnglishSolitaireModel(4, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidATOnly() {
    new EnglishSolitaireModel(4, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlotOnly() {
    new EnglishSolitaireModel(5, 0, 12);
  }

  @Test
  public void testMove() {

    // testing with the first constructor
    e1.move(1, 3, 3, 3);

    assertEquals(SlotState.Empty, e1.getSlotAt(1, 3));
    assertEquals(SlotState.Empty, e1.getSlotAt(2, 3));
    assertEquals(SlotState.Marble, e1.getSlotAt(3, 3));

    e1.move(4, 3, 2, 3);

    assertEquals(SlotState.Empty, e1.getSlotAt(4, 3));
    assertEquals(SlotState.Empty, e1.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, e1.getSlotAt(1, 3));
    assertEquals(SlotState.Marble, e1.getSlotAt(2, 3));

    e1.move(3, 5, 3, 3);

    assertEquals(SlotState.Empty, e1.getSlotAt(3, 5));
    assertEquals(SlotState.Empty, e1.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, e1.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, e1.getSlotAt(1, 3));
    assertEquals(SlotState.Empty, e1.getSlotAt(4, 3));

    e1.move(4, 1, 4, 3);

    assertEquals(SlotState.Marble, e1.getSlotAt(4, 3));
    assertEquals(SlotState.Empty, e1.getSlotAt(1, 3));
    assertEquals(SlotState.Empty, e1.getSlotAt(4, 2));
    assertEquals(SlotState.Empty, e1.getSlotAt(4, 1));

    // test move exceptions
    try {
      e1.move(3, 4, 3, 5);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move to its neighboring slots
    }
    try {
      e1.move(3, 3, 3, 5);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move if the slot in between the destination and the current
      // position is empty
    }
    try {
      e1.move(4, 3, 3, 4);
    } catch (IllegalArgumentException e) {
      // marble should not be able to move diagonally
    }

    // testing with the second constructor with the empty slot set at (2, 4)
    assertEquals(SlotState.Empty, e2.getSlotAt(2, 4));
    assertEquals(SlotState.Marble, e2.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, e2.getSlotAt(4, 4));

    e2.move(4, 4, 2, 4);

    assertEquals(SlotState.Marble, e2.getSlotAt(2, 4));
    assertEquals(SlotState.Empty, e2.getSlotAt(3, 4));
    assertEquals(SlotState.Empty, e2.getSlotAt(4, 4));

    // test move exceptions
    try {
      e2.move(3, 4, 3, 2);
    } catch (IllegalArgumentException e) {
      // Cannot move to a slot with marble
    }
    try {
      e2.move(2, 4, 4, 4);
    } catch (IllegalArgumentException e) {
      // Cannot move an empty slot
    }
  }

  @Test
  public void testIsGameOver() {

    // noMoves is when player has no more valid moves
    EnglishSolitaireModel noMoves = new EnglishSolitaireModel();

    assertFalse(noMoves.isGameOver());

    noMoves.move(1, 3, 3, 3);
    noMoves.move(4, 3, 2, 3);
    noMoves.move(6, 3, 4, 3);
    noMoves.move(3, 1, 3, 3);
    noMoves.move(3, 4, 3, 2);
    noMoves.move(3, 6, 3, 4);

    // won is when the player has won the game, hence game is over
    EnglishSolitaireModel won = new EnglishSolitaireModel();

    assertFalse(won.isGameOver());

    won.move(3, 5, 3, 3);
    won.move(5, 4, 3, 4);
    won.move(4, 6, 4, 4);
    won.move(2, 6, 4, 6);
    won.move(4, 3, 4, 5);
    won.move(4, 6, 4, 4);
    won.move(4, 1, 4, 3);
    won.move(6, 2, 4, 2);
    won.move(6, 4, 6, 2);
    won.move(3, 2, 5, 2);
    won.move(6, 2, 4, 2);
    won.move(1, 2, 3, 2);
    won.move(2, 0, 2, 2);
    won.move(4, 0, 2, 0);
    won.move(2, 3, 2, 1);
    won.move(2, 0, 2, 2);
    won.move(2, 5, 2, 3);
    won.move(0, 4, 2, 4);
    won.move(0, 2, 0, 4);
    won.move(3, 4, 1, 4);
    won.move(0, 4, 2, 4);
    won.move(3, 2, 1, 2);
    won.move(1, 2, 1, 4);
    won.move(1, 4, 3, 4);
    won.move(3, 4, 5, 4);
    won.move(5, 4, 5, 2);
    won.move(5, 2, 3, 2);
    won.move(3, 3, 1, 3);
    won.move(3, 1, 3, 3);
    won.move(4, 3, 2, 3);
    won.move(1, 3, 3, 3);

    assertFalse(e1.isGameOver());
    assertFalse(e2.isGameOver());
    assertFalse(e3.isGameOver());
    assertFalse(e4.isGameOver());

    assertTrue(noMoves.isGameOver());
    assertTrue(won.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, e1.getBoardSize());
    assertEquals(7, e2.getBoardSize());
    assertEquals(19, e3.getBoardSize());
    assertEquals(13, e4.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Empty, e1.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, e1.getSlotAt(1, 1));
    assertEquals(SlotState.Invalid, e1.getSlotAt(5, 0));
    assertEquals(SlotState.Invalid, e1.getSlotAt(6, 6));
    assertEquals(SlotState.Invalid, e1.getSlotAt(1, 5));
    assertEquals(SlotState.Marble, e1.getSlotAt(2, 3));
    assertEquals(SlotState.Marble, e1.getSlotAt(0, 4));
    assertEquals(SlotState.Marble, e1.getSlotAt(6, 2));
    assertEquals(SlotState.Marble, e1.getSlotAt(3, 5));

    assertEquals(SlotState.Empty, e2.getSlotAt(2, 4));

    assertEquals(SlotState.Empty, e3.getSlotAt(9, 9));
    assertEquals(SlotState.Invalid, e3.getSlotAt(1, 1));
    assertEquals(SlotState.Invalid, e3.getSlotAt(5, 5));
    assertEquals(SlotState.Invalid, e3.getSlotAt(13, 5));
    assertEquals(SlotState.Invalid, e3.getSlotAt(16, 17));
    assertEquals(SlotState.Invalid, e3.getSlotAt(4, 15));

    assertEquals(SlotState.Empty, e4.getSlotAt(2, 5));
    assertEquals(SlotState.Invalid, e4.getSlotAt(2, 2));
    assertEquals(SlotState.Invalid, e4.getSlotAt(1, 10));
    assertEquals(SlotState.Invalid, e4.getSlotAt(9, 9));
    assertEquals(SlotState.Invalid, e4.getSlotAt(9, 3));

    // test exceptions when the given slot is beyond the board dimension
    try {
      e1.getSlotAt(0, 8);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      e1.getSlotAt(-1, 4);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      e1.getSlotAt(5, -2);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
    try {
      e1.getSlotAt(7, 3);
    } catch (IllegalArgumentException e) {
      // Cannot get SlotState of a slot that is beyond the board dimension
    }
  }

  @Test
  public void testGetScore() {
    assertEquals(32, e1.getScore());
    e1.move(1, 3, 3, 3);
    assertEquals(31, e1.getScore());
    e1.move(2, 5, 2, 3);
    assertEquals(30, e1.getScore());

    assertEquals(32, e2.getScore());
    assertEquals(216, e3.getScore());
    assertEquals(104, e4.getScore());
  }
}