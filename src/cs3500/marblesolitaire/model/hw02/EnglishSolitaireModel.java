package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.WorldSolitaireModel;

/**
 * The model of the English Marble Solitaire game.
 */
public class EnglishSolitaireModel extends WorldSolitaireModel {

  private final int armThickness;

  /**
   * Constructor takes in three parameters, arm thickness, sRow, and sCol. It initializes the game
   * board with the given arm thickness, and the empty slot at (sRow, sCol).
   *
   * @param armThickness how many marbles there will be to the left, right, top, and bottom of the
   *                     center slot
   * @param sRow         the x-coordinate of the empty slot
   * @param sCol         the y-coordinate of the empty slot
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   * @throws IllegalArgumentException when the specified position of empty slot is invalid
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    super(sRow, sCol, armThickness);
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness has to be a positive odd number");
    }
    this.armThickness = armThickness;

    if (((sRow > (this.armThickness * 2) - 2 || sRow < armThickness - 1) && (sCol < armThickness - 1
                                                                             || sCol >
                                                                                (this.armThickness
                                                                                 * 2) - 2))
        || sRow < 0 || sCol < 0 || sRow > (this.armThickness * 3) - 3
        || sCol > (this.armThickness * 3) - 3) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    this.sRow = sRow;
    this.sCol = sCol;

    this.board = new SlotState[(armThickness * 3) - 2][(armThickness * 3) - 2];
    this.generateBoard();
  }

  /**
   * Default constructor takes no parameters, initializes the game board with default settings (arm
   * thickness 3, and empty slot at the center).
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Constructor takes two parameters, sRow and sCol. This should initialize the game board so that
   * the arm thickness is 3 and the empty slot is at the given position.
   *
   * @param sRow the x-coordinate of the empty slot
   * @param sCol the y-coordinate of the empty slot
   * @throws IllegalArgumentException when the specified position is invalid.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Constructor takes the arm thickness as its only parameter. It initializes a game board with the
   * empty slot at the center.
   *
   * @param armThickness how many marbles there will be to the left, right, top, and bottom of the
   *                     center slot
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, ((armThickness * 3) - 2 - 1) / 2,
        ((armThickness * 3) - 2 - 1) / 2);
  }

  // generate the board based on the SlotState of each slot
  private void generateBoard() {
    // generates the invalid slots on the top half of the board
    for (int i = 0; i < this.armThickness - 1; i++) {
      for (int j = 0; j < this.armThickness - 1; j++) {
        this.board[i][j] = SlotState.Invalid;
      }
      for (int j = (this.armThickness * 2) - 1; j < (this.armThickness * 3) - 2; j++) {

        this.board[i][j] = SlotState.Invalid;
      }
    }

    // generates the left and right arms of the board
    for (int i = this.armThickness - 1; i < (this.armThickness * 2) - 1; i++) {
      for (int j = 0; j < this.armThickness - 1; j++) {
        this.board[i][j] = SlotState.Marble;
      }
      for (int j = (this.armThickness * 2) - 1; j < (this.armThickness * 3) - 2; j++) {
        this.board[i][j] = SlotState.Marble;
      }
    }

    // generates the invalid slots on the bottom half of the board
    for (int i = (this.armThickness * 2) - 1; i < (this.armThickness * 3) - 2; i++) {
      for (int j = 0; j < this.armThickness - 1; j++) {
        this.board[i][j] = SlotState.Invalid;
      }
      for (int j = (this.armThickness * 2) - 1; j < (this.armThickness * 3) - 2; j++) {
        this.board[i][j] = SlotState.Invalid;
      }
    }

    // generates the middle columns of the board
    for (int i = 0; i < (this.armThickness * 3) - 2; i++) {
      for (int j = this.armThickness - 1; j < (this.armThickness * 2) - 1; j++) {
        this.board[i][j] = SlotState.Marble;
      }
    }

    this.board[sRow][sCol] = SlotState.Empty;
  }
}
