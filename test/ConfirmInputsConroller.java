import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.util.Objects;

/**
 * Mock class for MarbleSolitaireController. This class is for testing if the controller takes in
 * the correct inputs.
 */
public class ConfirmInputsConroller implements MarbleSolitaireModel {

  private final StringBuilder log;

  /**
   * This constructor only takes in the log, which should reflect the inputs given by the user.
   *
   * @param log a StringBuilder that collects a record of what the user has input
   */
  public ConfirmInputsConroller(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(
        String.format("fromRow = %d, fromCol = %d, toRow = %d, toCol = %d", fromRow, fromCol,
            toRow, toCol));
  }

  // all the methods below will not be needed to test inputs
  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
