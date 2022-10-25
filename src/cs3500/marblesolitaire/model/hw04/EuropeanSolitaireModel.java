package cs3500.marblesolitaire.model.hw04;

/**
 * The model of the European Marble Solitaire game.
 */
public class EuropeanSolitaireModel extends WorldSolitaireModel {

  private int sides;

  /**
   * This constructor takes in three parameters, the side length, the row and the column of the
   * empty slot.
   *
   * @param sides side length of the board
   * @param sRow  the row of the empty slot
   * @param sCol  the column of the empty slot
   * @throws IllegalArgumentException if the given side length is not a positive odd number or the
   *                                  given empty slot is invalid
   */
  public EuropeanSolitaireModel(int sides, int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol, sides);
    if (sides < 0 || sides % 2 == 0) {
      throw new IllegalArgumentException("Side length has to be a positive odd number");
    }
    this.sides = sides;

    if (sCol > sRow + (this.sides - 1) * 2 || sCol < this.sides - 1 - sRow
        || sCol < sRow - ((this.sides - 1) * 2) || sCol > ((this.sides - 1) / 2) * 10 - sRow
        || sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.sRow = sRow;
    this.sCol = sCol;

    this.generateBoard();
  }

  /**
   * This default constructor does not take in any parameters. It initializes the board with default
   * settings - empty slot at the center of the board and the side length of the board is 3.
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * This constructor takes in a parameter, the side length. It will then create a board with the
   * given side length.
   *
   * @param sides the side length of the board
   * @throws IllegalArgumentException if the given side length is negative and/or even
   */
  public EuropeanSolitaireModel(int sides) throws IllegalArgumentException {
    this(sides, (((sides * 3) - 2) - 1) / 2, (((sides * 3) - 2) - 1) / 2);
  }

  /**
   * This constructor takes in two parameters, the row and column of the empty slot.
   *
   * @param sRow the row the empty slot is in
   * @param sCol the column the empty slot is in
   * @throws IllegalArgumentException if the given coordinates for the empty slot is outside the
   *                                  board or of SlotState.Invalid
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  // generate the board based on the SlotState of each slot
  private void generateBoard() {
    for (int i = 0; i < (sides * 3) - 2; i++) {
      for (int j = 0; j < (sides * 3) - 2; j++) {
        if (j > i + (this.sides - 1) * 2 || j < this.sides - 1 - i
            || j < i - ((this.sides - 1) * 2) || j > ((this.sides - 1) / 2) * 10 - i) {
          this.board[i][j] = SlotState.Invalid;
        } else {
          if (i == this.sRow && j == this.sCol) {
            this.board[i][j] = SlotState.Empty;
          } else {
            this.board[i][j] = SlotState.Marble;
          }
        }
      }
    }
  }
}
