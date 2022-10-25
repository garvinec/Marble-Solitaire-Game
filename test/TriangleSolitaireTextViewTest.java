import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link MarbleSolitaireTextView}. Test if the view interacts and presents the model
 * correctly.
 */
public class TriangleSolitaireTextViewTest {

  private TriangleSolitaireTextView v1;
  private TriangleSolitaireTextView v2;
  private TriangleSolitaireTextView v3;
  private TriangleSolitaireTextView v4;

  private TriangleSolitaireModel t1;
  private TriangleSolitaireModel t2;
  private TriangleSolitaireModel t3;
  private TriangleSolitaireModel t4;

  @Before
  public void init() {
    this.t1 = new TriangleSolitaireModel();
    this.t2 = new TriangleSolitaireModel(3, 3);
    this.t3 = new TriangleSolitaireModel(7);
    this.t4 = new TriangleSolitaireModel(5, 4, 3);

    this.v1 = new TriangleSolitaireTextView(this.t1);
    this.v2 = new TriangleSolitaireTextView(this.t2);
    this.v3 = new TriangleSolitaireTextView(this.t3);
    this.v4 = new TriangleSolitaireTextView(this.t4);
  }

  // tests for invalid constructors, when it is given null model
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullParameter() {
    new TriangleSolitaireTextView(null);
  }

  // when the constructor is given an invalid empty slot
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlot() {
    new TriangleSolitaireTextView(new TriangleSolitaireModel(-1, 1));
  }

  // when the constructor is given a negative dimension
  @Test(expected = IllegalArgumentException.class)
  public void invalidEvenAT() {
    new TriangleSolitaireTextView(new TriangleSolitaireModel(-1));
  }

  // when the constructor is given a valid empty slot but invalid dimension
  @Test(expected = IllegalArgumentException.class)
  public void invalidAT() {
    new TriangleSolitaireTextView(new TriangleSolitaireModel(4, 3, -2));
  }

  // when the constructor takes in a valid appendable but a null model
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullModel() {
    StringBuilder out = new StringBuilder();
    new TriangleSolitaireTextView(null, out);
  }

  // when the constructor takes in a valid model but a null appendable
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullAppendable() {
    StringBuilder out = null;
    new TriangleSolitaireTextView(new TriangleSolitaireModel(), out);
  }

  // test if the toString method illustrate the EnglishSolitaire model correctly
  @Test
  public void testToString() {
    assertEquals("    _\n"
                 + "   O O\n"
                 + "  O O O\n"
                 + " O O O O\n"
                 + "O O O O O", v1.toString());

    t1.move(2, 0, 0, 0);

    assertEquals("    O\n"
                 + "   _ O\n"
                 + "  _ O O\n"
                 + " O O O O\n"
                 + "O O O O O", v1.toString());

    t1.move(2, 2, 2, 0);

    assertEquals("    O\n"
                 + "   _ O\n"
                 + "  O _ _\n"
                 + " O O O O\n"
                 + "O O O O O", v1.toString());

    assertEquals(
        "    O\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O _\n"
        + "O O O O O", v2.toString());

    t2.move(3, 1, 3, 3);

    assertEquals(
        "    O\n"
        + "   O O\n"
        + "  O O O\n"
        + " O _ _ O\n"
        + "O O O O O", v2.toString());

    assertEquals("      _\n"
                 + "     O O\n"
                 + "    O O O\n"
                 + "   O O O O\n"
                 + "  O O O O O\n"
                 + " O O O O O O\n"
                 + "O O O O O O O", v3.toString());

    t3.move(2, 2, 0, 0);

    assertEquals("      O\n"
                 + "     O _\n"
                 + "    O O _\n"
                 + "   O O O O\n"
                 + "  O O O O O\n"
                 + " O O O O O O\n"
                 + "O O O O O O O", v3.toString());

    assertEquals("    O\n"
                 + "   O O\n"
                 + "  O O O\n"
                 + " O O O O\n"
                 + "O O O _ O", v4.toString());

    t4.move(5, 2, 5, 0);

    assertEquals("     O\n"
                 + "    O O\n"
                 + "   O O O\n"
                 + "  O O O O\n"
                 + " O O O O O\n"
                 + "O _ _ O O O", v4.toString());
  }

  // test if the RenderBoard method renders EnglishSolitaire model correctly.
  @Test
  public void testRenderBoard() throws IOException {
    StringBuilder out = new StringBuilder();
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, out);

    view.renderBoard();
    assertEquals("    _\n"
                 + "   O O\n"
                 + "  O O O\n"
                 + " O O O O\n"
                 + "O O O O O", out.toString());

    model.move(2, 0, 0, 0);

    StringBuilder changed = new StringBuilder();
    TriangleSolitaireTextView moved = new TriangleSolitaireTextView(model, changed);
    moved.renderBoard();

    assertEquals("    O\n"
                 + "   _ O\n"
                 + "  _ O O\n"
                 + " O O O O\n"
                 + "O O O O O", changed.toString());

    // tests for IOExceptions
    TriangleSolitaireModel validModel = new TriangleSolitaireModel();
    TriangleSolitaireTextView invalidView = new TriangleSolitaireTextView(validModel,
        new InvalidAppendable());

    try {
      invalidView.renderBoard();
      fail("Can't render board");
    } catch (IOException e) {
      // it should catch exception since it was given an InvalidAppendable
    }
  }

  // test if the RenderMessage method renders the given message correctly
  @Test
  public void testRenderMessage() throws IOException {
    StringBuilder out = new StringBuilder();
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, out);

    view.renderMessage("Game over!");
    assertEquals("Game over!", out.toString());

    // tests for IOExceptions
    TriangleSolitaireModel validModel = new TriangleSolitaireModel();
    TriangleSolitaireTextView invalidView = new TriangleSolitaireTextView(validModel,
        new InvalidAppendable());

    try {
      invalidView.renderMessage("Should throw exception");
      fail("Can't render message");
    } catch (IOException e) {
      // it should catch exception since it was given an InvalidAppendable
    }
  }
}