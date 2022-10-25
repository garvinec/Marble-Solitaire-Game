package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.io.IOException;

/**
 * The view of the game TriangleMarbleSolitaire.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState model;
  private final Appendable out;

  /**
   * The constructor takes in a parameter, the model.
   *
   * @param model the model from the class TriangleSolitaireModel
   * @throws IllegalArgumentException if the given model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
      throws IllegalArgumentException {
    this(model, System.out);
  }

  /**
   * The constructor takes in the model, and an appendable.
   *
   * @param model the model from the class TriangleSolitaireModel
   * @param out   an appendable from the appendable class
   * @throws IllegalArgumentException if the given model or the appendable is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable out)
      throws IllegalArgumentException {
    if (model == null || out == null) {
      throw new IllegalArgumentException("model and appendable cannot be empty");
    }
    this.model = model;
    this.out = out;
  }

  // check what it should print for each corresponding SlotState
  private String which(SlotState s) {
    switch (s) {
      case Marble:
        return "O";
      case Empty:
        return "_";
      case Invalid:
        return "";
      default:
        throw new IllegalArgumentException("SlotState should not be null");
    }
  }

  /**
   * Renders the model in string form, with "O" representing the marble, "_" representing the empty
   * slots, and " " representing the invalid slots.
   *
   * @return the model in string form
   */
  public String toString() {

    StringBuilder board = new StringBuilder();

    for (int i = 0; i < this.model.getBoardSize(); i++) {
      for (int j = 0; j < this.model.getBoardSize(); j++) {
        // top tip of the triangle board
        if (j == 0 && i == 0) {
          int repeat = 0;
          while (repeat < this.model.getBoardSize() - 1 - i) {
            board.append(" ");
            repeat++;
          }
          board.append(which(model.getSlotAt(i, j)));
        }

        // leftmost slots of the triangle board
        if (j == 0 && i != 0) {
          int repeat = 0;
          while (repeat < this.model.getBoardSize() - 1 - i) {
            board.append(" ");
            repeat++;
          }
          board.append(which(model.getSlotAt(i, j)));
          board.append(" ");
        }

        // rightmost slots of the board
        if (j == i && j != 0) {
          board.append(which(model.getSlotAt(i, j)));
        }

        // rest of the triangle board
        if (i > j && j != 0) {
          board.append(which(model.getSlotAt(i, j)));
          board.append(" ");
        }
      }
      // prevent adding a new line after the last row is rendered
      if (i != model.getBoardSize() - 1) {
        board.insert(board.length(), "\n");
      }
    }
    return board.toString();
  }

  @Override
  public void renderBoard() throws IOException {
    this.out.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}