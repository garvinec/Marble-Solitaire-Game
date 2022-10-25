package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class for EnglishSolitaireModel and EuropeanSolitaireModel.
 */
public abstract class WorldSolitaireModel implements MarbleSolitaireModel {

  protected int sRow;
  protected int sCol;
  protected int length;
  protected SlotState[][] board;

  /**
   * This constructor takes in three parameters, length (side length for EuropeanSolitaireModel and
   * arm thickness for EnglishSolitaireModel) and row and column of the empty slot.
   *
   * @param sRow   row of the empty slot
   * @param sCol   column of the empty slot
   * @param length number of side length/arm thickness
   * @throws IllegalArgumentException throws exception if the length is not a positive odd number.
   */
  public WorldSolitaireModel(int sRow, int sCol, int length) throws IllegalArgumentException {
    this.sRow = sRow;
    this.sCol = sCol;

    if (length < 0 || length % 2 == 0) {
      throw new IllegalArgumentException();
    }
    this.length = length;

    this.board = new SlotState[(this.length * 3) - 2][(this.length * 3) - 2];
  }

  // return true if (fromRow, fromCol) and (toRow, toCol) are both within the board dimension
  // otherwise false
  protected boolean inBoard(int fromRow, int fromCol, int toRow, int toCol) {
    return fromRow < (this.length * 3) - 2 && toRow < (this.length * 3) - 2 && fromCol <
                                                                               (this.length * 3) - 2
           && toCol < (this.length * 3) - 2 && fromRow >= 0 && toRow >= 0
           && fromCol >= 0 && toCol >= 0;
  }

  // return true if a marble can move from (fromRow, fromCol) to (toRow, toCol)
  // otherwise false
  protected boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    return inBoard(
        fromRow, fromCol, toRow, toCol) && this.board[fromRow][fromCol] == SlotState.Marble
           && this.board[toRow][toCol] == SlotState.Empty && (toRow == fromRow || toCol == fromCol)
           && ((Math.abs(fromRow - toRow) == 2 || Math.abs(fromCol - toCol) == 2))
           && (this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2]) == SlotState.Marble;
  }

  /**
   * Moves the marble at (fromRow, fromCol) to (toRow, toCol).
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the following occurs: either the from position or the to
   *                                  position is beyond the board dimension, the from position is
   *                                  not a marble, the to position is not empty, the position
   *                                  between the from position and the to position is not a marble,
   *                                  the from the to position is not exactly 2 positions apart
   *                                  (either in the same row or column)
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (canMove(fromRow, fromCol, toRow, toCol)) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
    } else {
      throw new IllegalArgumentException("Illegal move");
    }
  }

  protected boolean isGameOverHelper(int fromRow, int fromCol) {
    return this.canMove(fromRow, fromCol, fromRow + 2, fromCol) || this.canMove(fromRow, fromCol,
        fromRow, fromCol + 2) || this.canMove(fromRow, fromCol, fromRow, fromCol - 2)
           || this.canMove(fromRow, fromCol, fromRow - 2, fromCol);
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < (this.length * 3) - 2; i++) {
      for (int j = 0; j < (this.length * 3) - 2; j++) {
        if (isGameOverHelper(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return (this.length * 3) - 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > (this.length * 3) - 3 || col < 0
        || col > (this.length * 3) - 3) {
      throw new IllegalArgumentException("The given position is beyond the board dimension");
    }
    return this.board[row][col];
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < (this.length * 3) - 2; i++) {
      for (int j = 0; j < (this.length * 3) - 2; j++) {
        if (this.board[i][j] == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }
}
