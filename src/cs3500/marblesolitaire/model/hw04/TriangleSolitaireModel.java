package cs3500.marblesolitaire.model.hw04;

/**
 * The model of the Triangle Marble Solitaire game.
 */
public class TriangleSolitaireModel extends WorldSolitaireModel {

  private final int dimension;

  /**
   * This constructor takes in three parameters, dimension of the board, the row and column of the
   * empty slot.
   *
   * @param sRow      row of the empty slot
   * @param sCol      column of the empty slot
   * @param dimension number of rows on the board
   * @throws IllegalArgumentException if the given dimension is non-positive or the given position
   *                                  for empty slot is invalid
   */
  public TriangleSolitaireModel(int dimension, int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol, dimension);
    if (dimension < 0) {
      throw new IllegalArgumentException("Dimension must be positive");
    }
    this.dimension = dimension;

    if (sRow < 0 || sCol < 0 || sRow > dimension || sCol > dimension || sCol > sRow) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.sRow = sRow;
    this.sCol = sCol;

    this.board = new SlotState[dimension][dimension];
    this.generateBoard();
  }

  /**
   * This constructor takes in no parameters, it will initialize the board with default settings:
   * empty slot at (0,0) and has 5 rows.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * This constructor takes in one parameter, the dimension.
   *
   * @param dimension number of rows on the board
   * @throws IllegalArgumentException if the given dimension is non-positive
   */
  public TriangleSolitaireModel(int dimension) throws IllegalArgumentException {
    this(dimension, 0, 0);
  }

  /**
   * This constructor takes in two parameters, the row and column of the empty slot.
   *
   * @param sRow row of the empty slot
   * @param sCol column of the empty slot
   * @throws IllegalArgumentException if the given position is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(5, sRow, sCol);
  }

  // generate the board based on the SlotState of each slot
  private void generateBoard() {
    for (int i = 0; i < this.dimension; i++) {
      for (int j = 0; j < this.dimension; j++) {
        if (j > i) {
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

  // return true if a marble can move from (fromRow, fromCol) to (toRow, toCol)
  // otherwise false
  @Override
  protected boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    return inBoard(fromRow, fromCol, toRow, toCol)
           && this.board[fromRow][fromCol] == SlotState.Marble
           && this.board[toRow][toCol] == SlotState.Empty
           && ((Math.abs(fromRow - toRow) == 2 || Math.abs(fromCol - toCol) == 2))
           && ((Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol) == 2) || (
        Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol) == 4))
           && (this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2]) == SlotState.Marble;
  }

  @Override
  protected boolean isGameOverHelper(int fromRow, int fromCol) {
    return this.canMove(fromRow, fromCol, fromRow + 2, fromCol) || this.canMove(fromRow, fromCol,
        fromRow, fromCol + 2) || this.canMove(fromRow, fromCol, fromRow, fromCol - 2)
           || this.canMove(fromRow, fromCol, fromRow - 2, fromCol) || this.canMove(fromRow, fromCol,
        fromRow - 2, fromCol - 2) || this.canMove(fromRow, fromCol, fromRow + 2, fromCol + 2);
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.dimension; i++) {
      for (int j = 0; j < this.dimension; j++) {
        if (isGameOverHelper(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return this.dimension;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > this.dimension - 1 || col < 0
        || col > this.dimension - 1) {
      throw new IllegalArgumentException("The given position is beyond the board dimension");
    }
    return this.board[row][col];
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.dimension; i++) {
      for (int j = 0; j < this.dimension; j++) {
        if (this.board[i][j] == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }
}
