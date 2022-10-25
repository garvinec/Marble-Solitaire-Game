package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.io.IOException;

/**
 * The view of the game EnglishMarbleSolitaire and EuropeanMarbleSolitaire.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState model;
  private final Appendable out;

  /**
   * The constructor takes in a parameter, the model.
   *
   * @param model the model from the class EnglishSolitaireModel
   * @throws IllegalArgumentException if the given model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    this(model, System.out);
  }

  /**
   * The constructor takes in the model, and an appendable.
   *
   * @param model the model from the class EnglishSolitaireModel
   * @param out   an appendable from the appendable class
   * @throws IllegalArgumentException if the given model or the appendable is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable out)
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
        return " ";
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

    for (int i = 0; i < model.getBoardSize(); i++) {
      for (int j = 0; j < model.getBoardSize(); j++) {

        // rendering the left half of the board
        if (j < model.getBoardSize() - ((model.getBoardSize() + 2) / 3)) {

          board.append(which(model.getSlotAt(i, j)));
          board.append(" ");
        }

        // rendering the top and bottom third of the column to the right of center column
        if (j == model.getBoardSize() - ((model.getBoardSize() + 2) / 3) && (
            i < ((model.getBoardSize() + 2) / 3) - 1 || i > model.getBoardSize() - (
                (model.getBoardSize() + 2) / 3)) && (model.getSlotAt(i, j + 1)
                                                     == SlotState.Invalid)) {

          board.append(which(model.getSlotAt(i, j)));
        }

        // rendering the top and bottom third of the column to the right of center column
        if (j == model.getBoardSize() - ((model.getBoardSize() + 2) / 3) && (
            i < ((model.getBoardSize() + 2) / 3) - 1 || i > model.getBoardSize() - (
                (model.getBoardSize() + 2) / 3)) && (model.getSlotAt(i, j + 1)
                                                     == SlotState.Marble)) {

          board.append(which(model.getSlotAt(i, j)));
          board.append(" ");
        }

        // rendering the middle third of the column to the right of center column
        if (j == model.getBoardSize() - ((model.getBoardSize() + 2) / 3) && (
            i >= ((model.getBoardSize() + 2) / 3) - 1 && i <= model.getBoardSize() - (
                (model.getBoardSize() + 2) / 3))) {

          board.append(which(model.getSlotAt(i, j)));
          board.append(" ");
        }

        // rendering the right arm except for the rightmost column
        if (j > model.getBoardSize() - ((model.getBoardSize() + 2) / 3)
            && j < model.getBoardSize() - 1 && i >= ((model.getBoardSize() + 2) / 3) - 1
            && i < model.getBoardSize() - (((model.getBoardSize() + 2) / 3) - 1) && (
                (model.getSlotAt(i, j) == SlotState.Marble) || (model.getSlotAt(i, j)
                                                                == SlotState.Empty))) {

          board.append(which(model.getSlotAt(i, j)));
          board.append(" ");
        }

        // rendering the right arm except for the rightmost column
        if (j > model.getBoardSize() - ((model.getBoardSize() + 2) / 3)
            && j < model.getBoardSize() - 1 && ((i < ((model.getBoardSize() + 2) / 3) - 1
                                                 && j < i + (((model.getBoardSize() + 2) / 3) - 1)
                                                            * 2)
                                                || (i >= model.getBoardSize() - (
            ((model.getBoardSize() + 2) / 3) - 1)
                                                    && j <
                                                       ((((model.getBoardSize() + 2) / 3)) / 2) * 10
                                                       - i)) && (
                (model.getSlotAt(i, j) == SlotState.Marble)
                || (model.getSlotAt(i, j) == SlotState.Empty))) {

          board.append(which(model.getSlotAt(i, j)));
          board.append(" ");
        }

        // rendering the right arm except for the rightmost column
        if (j > model.getBoardSize() - ((model.getBoardSize() + 2) / 3)
            && j < model.getBoardSize() - 1 && ((i < ((model.getBoardSize() + 2) / 3) - 1
                                                 && j == i + (((model.getBoardSize() + 2) / 3) - 1)
                                                             * 2)
                                                || (i >= model.getBoardSize() - (
            ((model.getBoardSize() + 2) / 3) - 1)
                                                    && j ==
                                                       ((((model.getBoardSize() + 2) / 3)) / 2) * 10
                                                       - i)) && (
                (model.getSlotAt(i, j) == SlotState.Marble)
                || (model.getSlotAt(i, j) == SlotState.Empty))) {

          board.append(which(model.getSlotAt(i, j)));
        }

        // rendering the rightmost column of the board
        if (j == model.getBoardSize() - 1 && (
            (model.getSlotAt(i, j) == SlotState.Marble)
            || (model.getSlotAt(i, j) == SlotState.Empty))) {
          board.append(which(model.getSlotAt(i, j)));
        }
      }
      // prevent adding a new line after the last row is rendered
      if (i != model.getBoardSize() - 1) {
        board.insert(board.length(), "\n");
      }
    }
    return board.toString();
  }

  // renders the board in any part of the program, not just the view class, unlike to string
  @Override
  public void renderBoard() throws IOException {
    this.out.append(this.toString());
  }

  // renders the given message to the view
  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}