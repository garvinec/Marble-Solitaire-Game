import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link MarbleSolitaireControllerImpl} Test if the controller interacts with the model
 * and the view properly.
 */
public class MarbleSolitaireControllerImplTest {

  private MarbleSolitaireTextView v1;

  private EnglishSolitaireModel m1;
  private EnglishSolitaireModel m2;

  // initial condition for tests
  @Before
  public void init() {
    this.m1 = new EnglishSolitaireModel();
    this.m2 = new EnglishSolitaireModel(5);

    this.v1 = new MarbleSolitaireTextView(this.m1);
  }

  // test invalid controller constructor, when the given model is null
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullModelController() {
    Readable in = new StringReader("Test Exception");
    new MarbleSolitaireControllerImpl(null, this.v1, in);
  }

  // test invalid controller constructor, when the given view is null
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullViewController() {
    Readable in = new StringReader("Test Exception");
    new MarbleSolitaireControllerImpl(this.m1, null, in);
  }

  // test invalid controller constructor, when the given readable is null
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullReadableController() {
    new MarbleSolitaireControllerImpl(this.m1, this.v1, null);
  }

  // test exception when the given Readable is not valid
  @Test(expected = IllegalStateException.class)
  public void testReadableException() {
    Readable in = new InvalidReadable();
    StringBuilder out = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view, in);
    controller.playGame();
  }

  // using the mock class, test inputs
  @Test
  public void testInputs() {
    Readable in = new StringReader("4 4 6 4\n q\n");
    StringBuilder out = new StringBuilder(); // does not matter what the output is
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mock = new ConfirmInputsConroller(log);
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(mock, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(mock, view, in);

    controller.playGame();
    assertEquals("fromRow = 3, fromCol = 3, toRow = 5, toCol = 3", log.toString());
  }

  // test IllegalStateException when the game is not over or the user does not quit
  @Test(expected = IllegalStateException.class)
  public void testEndlessGame() {
    StringBuilder out = new StringBuilder();
    Readable in = new StringReader("4 6 4 4\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view, in);

    controller.playGame();
  }

  // test IllegalStateException when user puts in no inputs
  @Test(expected = IllegalStateException.class)
  public void testEmptyInput() {
    StringBuilder out = new StringBuilder();
    Readable in = new StringReader("\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view, in);

    controller.playGame();
  }

  // test IllegalStateException when user puts in a negative number
  @Test
  public void testNegativeInput() {
    StringBuilder out = new StringBuilder();
    Readable in = new StringReader("-1 2 3 4\nq\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view, in);

    controller.playGame();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Input has to be a positive integer, please re-enter an input", outputs[8]);
  }

  // test IllegalStateException when user puts in a letter
  @Test
  public void testLetterInput() {
    StringBuilder out = new StringBuilder();
    Readable in = new StringReader("1 2 c 4\nq\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view, in);

    controller.playGame();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Input has to be a positive integer, please re-enter an input", outputs[8]);
  }

  // test IllegalStateException when user puts in a special character
  @Test
  public void testSpecialCharInput() {
    StringBuilder out = new StringBuilder();
    Readable in = new StringReader("1 ! 3 4\nq\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view, in);

    controller.playGame();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Input has to be a positive integer, please re-enter an input", outputs[8]);
  }

  // test IllegalStateException when user puts in a decimal
  @Test
  public void testDecimalInput() {
    StringBuilder out = new StringBuilder();
    Readable in = new StringReader("1 2 3 0.4\nq\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view, in);

    controller.playGame();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Input has to be a positive integer, please re-enter an input", outputs[8]);
  }

  // test user re-entering inputs when prompted to do so
  @Test
  public void testReenterInputs() {
    // when there's an invalid input and the user is prompted to re-enter a new one
    StringBuilder out1 = new StringBuilder();
    Readable in1 = new StringReader("4 6 t 4\n4\nq\n");
    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(this.m1, out1);
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(this.m1, view1,
        in1);

    controller1.playGame();

    String[] outputs1 = out1.toString().split(System.lineSeparator());

    assertEquals("Input has to be a positive integer, please re-enter an input",
        outputs1[8]);

    assertEquals("    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O O _ _ O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 31",
        outputs1[9] + "\n" + outputs1[10] + "\n" + outputs1[11] + "\n" + outputs1[12] + "\n"
        + outputs1[13] + "\n" + outputs1[14] + "\n" + outputs1[15] + "\n" + outputs1[16]);

    // when the user's inputs executes an invalid move, he re-enter inputs that will execute a
    // valid move
    StringBuilder out2 = new StringBuilder();
    Readable in2 = new StringReader("1 2 3 4\n4 6 4 4\nq\n");
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(this.m1, out2);
    MarbleSolitaireControllerImpl controller2 = new MarbleSolitaireControllerImpl(this.m1, view2,
        in2);

    controller2.playGame();

    String[] outputs2 = out2.toString().split(System.lineSeparator());

    assertEquals("Please enter a valid move",
        outputs2[8]);

    assertEquals("    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O O _ _ O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 31",
        outputs2[12] + "\n" + outputs2[13] + "\n" + outputs2[14] + "\n" + outputs2[15] + "\n"
        + outputs2[16] + "\n" + outputs2[17] + "\n" + outputs2[18] + "\n" + outputs2[19]);
  }

  // test the starting display
  @Test
  public void testStartingBoard() {
    // when the arm thickness of the baord is 3 (default board)
    StringBuilder out1 = new StringBuilder();
    Readable in1 = new StringReader("q\n");
    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(this.m1, out1);
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(this.m1, view1,
        in1);

    controller1.playGame();

    String[] outputs1 = out1.toString().split(System.lineSeparator());

    // displays the correct default starting board
    assertEquals("    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 32",
        outputs1[0] + "\n" + outputs1[1] + "\n" + outputs1[2] + "\n" + outputs1[3] + "\n" +
        outputs1[4] + "\n" + outputs1[5] + "\n" + outputs1[6] + "\n" + outputs1[7]);

    // when the arm thickness of the board is 5
    StringBuilder out2 = new StringBuilder();
    Readable in2 = new StringReader("q\n");
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(this.m2, out2);
    MarbleSolitaireControllerImpl controller2 = new MarbleSolitaireControllerImpl(this.m2, view2,
        in2);

    controller2.playGame();

    String[] outputs2 = out2.toString().split(System.lineSeparator());

    // displays the correct board with arm thickness 5
    assertEquals("        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O _ O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n"
                 + "Score: 104",
        outputs2[0] + "\n" + outputs2[1] + "\n" + outputs2[2] + "\n" + outputs2[3] + "\n" +
        outputs2[4] + "\n" + outputs2[5] + "\n" + outputs2[6] + "\n" + outputs2[7] + "\n"
        + outputs2[8] + "\n" + outputs2[9] + "\n" + outputs2[10] + "\n" + outputs2[11] + "\n"
        + outputs2[12] + "\n" + outputs2[13]);
  }

  // test playGame method with user inputs making valid moves and quitting mid-game
  @Test
  public void testValidMoves() throws IllegalStateException {
    StringBuilder out = new StringBuilder();
    Readable validMove = new StringReader("4 6 4 4\n4 3 4 5\n6 4 4 4\n2 3 4 3\nq\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view,
        validMove);

    controller.playGame();

    String[] outputs = out.toString().split(System.lineSeparator());

    // moves correctly when user makes his first valid move
    assertEquals("    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 32",
        outputs[0] + "\n" + outputs[1] + "\n" + outputs[2] + "\n" + outputs[3] + "\n" +
        outputs[4] + "\n" + outputs[5] + "\n" + outputs[6] + "\n" + outputs[7]);

    // moves correctly when user makes his second valid move
    assertEquals("    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O O _ _ O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 31",
        outputs[8] + "\n" + outputs[9] + "\n" + outputs[10] + "\n" + outputs[11] + "\n" +
        outputs[12] + "\n" + outputs[13] + "\n" + outputs[14] + "\n" + outputs[15]);

    // game quits correctly and displays the correct message
    assertEquals("Game quit!\n"
                 + "State of game when quit:\n"
                 + "    O O O\n"
                 + "    _ O O\n"
                 + "O O _ O O O O\n"
                 + "O O O O O _ O\n"
                 + "O O O _ O O O\n"
                 + "    O _ O\n"
                 + "    O O O\n"
                 + "Score: 28",
        outputs[40] + "\n" + outputs[41] + "\n" + outputs[42] + "\n" + outputs[43] + "\n" +
        outputs[44] + "\n" + outputs[45] + "\n" + outputs[46] + "\n" + outputs[47] + "\n"
        + outputs[48] + "\n" + outputs[49]);
  }

  // test playGame method and make sure it quits properly in all ways
  @Test
  public void testQuitGame() {
    // set up for case 1, when user inputs q only
    StringBuilder out1 = new StringBuilder();
    Readable case1 = new StringReader("q\n");
    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(this.m1, out1);
    MarbleSolitaireControllerImpl controller1 = new MarbleSolitaireControllerImpl(this.m1, view1,
        case1);

    controller1.playGame();

    // set up for case 2, when user inputs q followed by 3 inputs (e.g. q x x x)
    StringBuilder out2 = new StringBuilder();
    Readable case2 = new StringReader("q 6 4 4\n");
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(this.m1, out2);
    MarbleSolitaireControllerImpl controller2 = new MarbleSolitaireControllerImpl(this.m1, view2,
        case2);

    controller2.playGame();

    // set up for case 3, when user inputs q with 1 input in front
    // and followed by 2 inputs (e.g. x q x x)
    StringBuilder out3 = new StringBuilder();
    Readable case3 = new StringReader("4 q 4 4\n");
    MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(this.m1, out3);
    MarbleSolitaireControllerImpl controller3 = new MarbleSolitaireControllerImpl(this.m1, view3,
        case3);

    controller3.playGame();

    // set up for case 4, when user inputs q with 2 input in front
    // and followed by 1 inputs (e.g. x x q x)
    StringBuilder out4 = new StringBuilder();
    Readable case4 = new StringReader("4 6 q 4\n");
    MarbleSolitaireTextView view4 = new MarbleSolitaireTextView(this.m1, out4);
    MarbleSolitaireControllerImpl controller4 = new MarbleSolitaireControllerImpl(this.m1, view4,
        case4);

    controller4.playGame();

    // set up for case 5, when user inputs q as the last input with 3 inputs in front (e.g. x x x q)
    StringBuilder out5 = new StringBuilder();
    Readable case5 = new StringReader("4 6 4 q\n");
    MarbleSolitaireTextView view5 = new MarbleSolitaireTextView(this.m1, out5);
    MarbleSolitaireControllerImpl controller5 = new MarbleSolitaireControllerImpl(this.m1, view5,
        case5);

    controller5.playGame();

    // set up for case 6, when user inputs Q only
    StringBuilder out6 = new StringBuilder();
    Readable case6 = new StringReader("Q\n");
    MarbleSolitaireTextView view6 = new MarbleSolitaireTextView(this.m1, out6);
    MarbleSolitaireControllerImpl controller6 = new MarbleSolitaireControllerImpl(this.m1, view6,
        case6);

    controller6.playGame();

    String[] outputs1 = out1.toString().split(System.lineSeparator());
    String[] outputs2 = out2.toString().split(System.lineSeparator());
    String[] outputs3 = out3.toString().split(System.lineSeparator());
    String[] outputs4 = out4.toString().split(System.lineSeparator());
    String[] outputs5 = out5.toString().split(System.lineSeparator());
    String[] outputs6 = out6.toString().split(System.lineSeparator());

    // when user inputs q
    assertEquals("Game quit!\n"
                 + "State of game when quit:\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 32",
        outputs1[8] + "\n" + outputs1[9] + "\n" + outputs1[10] + "\n" + outputs1[11] + "\n" +
        outputs1[12] + "\n" + outputs1[13] + "\n" + outputs1[14] + "\n" + outputs1[15] + "\n"
        + outputs1[16] + "\n" + outputs1[17]);

    // when user inputs q followed by 3 inputs
    assertEquals("Game quit!\n"
                 + "State of game when quit:\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 32",
        outputs2[8] + "\n" + outputs2[9] + "\n" + outputs2[10] + "\n" + outputs2[11] + "\n" +
        outputs2[12] + "\n" + outputs2[13] + "\n" + outputs2[14] + "\n" + outputs2[15] + "\n"
        + outputs2[16] + "\n" + outputs2[17]);

    // when user inputs q with 1 input in front and followed by 2 inputs
    assertEquals("Game quit!\n"
                 + "State of game when quit:\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 32",
        outputs3[8] + "\n" + outputs3[9] + "\n" + outputs3[10] + "\n" + outputs3[11] + "\n" +
        outputs3[12] + "\n" + outputs3[13] + "\n" + outputs3[14] + "\n" + outputs3[15] + "\n"
        + outputs3[16] + "\n" + outputs3[17]);

    // when user inputs q with 2 inputs in front and followed by an input
    assertEquals("Game quit!\n"
                 + "State of game when quit:\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 32",
        outputs4[8] + "\n" + outputs4[9] + "\n" + outputs4[10] + "\n" + outputs4[11] + "\n" +
        outputs4[12] + "\n" + outputs4[13] + "\n" + outputs4[14] + "\n" + outputs4[15] + "\n"
        + outputs4[16] + "\n" + outputs4[17]);

    // when user inputs q as the last input out of the four
    assertEquals("Game quit!\n"
                 + "State of game when quit:\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 32",
        outputs5[8] + "\n" + outputs5[9] + "\n" + outputs5[10] + "\n" + outputs5[11] + "\n" +
        outputs5[12] + "\n" + outputs5[13] + "\n" + outputs5[14] + "\n" + outputs5[15] + "\n"
        + outputs5[16] + "\n" + outputs5[17]);

    // when user inputs Q
    assertEquals("Game quit!\n"
                 + "State of game when quit:\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "    O O O\n"
                 + "    O O O\n"
                 + "Score: 32",
        outputs6[8] + "\n" + outputs6[9] + "\n" + outputs6[10] + "\n" + outputs6[11] + "\n" +
        outputs6[12] + "\n" + outputs6[13] + "\n" + outputs6[14] + "\n" + outputs6[15] + "\n"
        + outputs6[16] + "\n" + outputs6[17]);
  }

  // test game is over when there are no valid moves and displays the correct game over message
  @Test
  public void testGameOver() {
    StringBuilder out = new StringBuilder();
    Readable inputs = new StringReader("2 4 4 4\n5 4 3 4\n7 4 5 4\n4 2 4 4\n4 5 4 3\n4 7 4 5\n");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(this.m1, view,
        inputs);

    controller.playGame();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Game over!\n"
                 + "    O O O\n"
                 + "    O _ O\n"
                 + "O O O O O O O\n"
                 + "O _ O _ O _ _\n"
                 + "O O O O O O O\n"
                 + "    O _ O\n"
                 + "    O _ O\n"
                 + "Score: 26", outputs[56] + "\n" + outputs[57] + "\n" + outputs[58] + "\n"
                                + outputs[59] + "\n" + outputs[60] + "\n" + outputs[61] + "\n"
                                + outputs[62] + "\n" + outputs[63] + "\n" + outputs[64]);
  }
}