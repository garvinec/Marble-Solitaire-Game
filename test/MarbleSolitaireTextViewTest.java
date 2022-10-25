import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link MarbleSolitaireTextView}. Test if the view interacts and presents the model
 * correctly.
 */
public class MarbleSolitaireTextViewTest {

  private MarbleSolitaireTextView v1;
  private MarbleSolitaireTextView v2;
  private MarbleSolitaireTextView v3;
  private MarbleSolitaireTextView v4;
  private MarbleSolitaireTextView v5;
  private MarbleSolitaireTextView v6;
  private MarbleSolitaireTextView v7;
  private MarbleSolitaireTextView v8;

  private EnglishSolitaireModel e1;
  private EnglishSolitaireModel e2;
  private EnglishSolitaireModel e3;
  private EnglishSolitaireModel e4;

  private EuropeanSolitaireModel m1;
  private EuropeanSolitaireModel m2;
  private EuropeanSolitaireModel m3;
  private EuropeanSolitaireModel m4;

  @Before
  public void init() {
    this.e1 = new EnglishSolitaireModel();
    this.e2 = new EnglishSolitaireModel(2, 4);
    this.e3 = new EnglishSolitaireModel(7);
    this.e4 = new EnglishSolitaireModel(5, 0, 4);

    this.m1 = new EuropeanSolitaireModel();
    this.m2 = new EuropeanSolitaireModel(2, 4);
    this.m3 = new EuropeanSolitaireModel(7);
    this.m4 = new EuropeanSolitaireModel(5, 0, 4);

    this.v1 = new MarbleSolitaireTextView(this.e1);
    this.v2 = new MarbleSolitaireTextView(this.e2);
    this.v3 = new MarbleSolitaireTextView(this.e3);
    this.v4 = new MarbleSolitaireTextView(this.e4);

    this.v5 = new MarbleSolitaireTextView(this.m1);
    this.v6 = new MarbleSolitaireTextView(this.m2);
    this.v7 = new MarbleSolitaireTextView(this.m3);
    this.v8 = new MarbleSolitaireTextView(this.m4);
  }

  // tests for invalid constructors, when it is given null model
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullParameterEnglish() {
    new MarbleSolitaireTextView(null);
  }

  // when the constructor is given an invalid empty slot
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlotEnglish() {
    new MarbleSolitaireTextView(new EnglishSolitaireModel(0, 1));
  }

  // when the constructor is given an even arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void invalidEvenATEnglish() {
    new MarbleSolitaireTextView(new EnglishSolitaireModel(4));
  }

  // when the constructor is given a negative arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void invalidNegativeATEnglish() {
    new MarbleSolitaireTextView(new EnglishSolitaireModel(-3));
  }

  // when the constructor is given a valid empty slot but invalid arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void invalidATEnglish() {
    new MarbleSolitaireTextView(new EnglishSolitaireModel(4, 3, 3));
  }

  // when the constructor takes in a valid appendable but a null model
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullModelEnglish() {
    StringBuilder out = new StringBuilder();
    new MarbleSolitaireTextView(null, out);
  }

  // when the constructor takes in a valid model but a null appendable
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullAppendableEnglish() {
    StringBuilder out = null;
    new MarbleSolitaireTextView(new EnglishSolitaireModel(), out);
  }

  // tests for invalid constructors, when it is given null model
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullParameterEuropean() {
    new MarbleSolitaireTextView(null);
  }

  // when the constructor is given an invalid empty slot
  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptySlotEuropean() {
    new MarbleSolitaireTextView(new EuropeanSolitaireModel(0, 1));
  }

  // when the constructor is given an even side length
  @Test(expected = IllegalArgumentException.class)
  public void invalidEvenATEuropean() {
    new MarbleSolitaireTextView(new EnglishSolitaireModel(4));
  }

  // when the constructor is given a negative side length
  @Test(expected = IllegalArgumentException.class)
  public void invalidNegativeATEuropean() {
    new MarbleSolitaireTextView(new EuropeanSolitaireModel(-3));
  }

  // when the constructor is given a valid empty slot but invalid side length
  @Test(expected = IllegalArgumentException.class)
  public void invalidATEuropean() {
    new MarbleSolitaireTextView(new EuropeanSolitaireModel(4, 3, 3));
  }

  // when the constructor takes in a valid appendable but a null model
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullModelEuropean() {
    StringBuilder out = new StringBuilder();
    new MarbleSolitaireTextView(null, out);
  }

  // when the constructor takes in a valid model but a null appendable
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullAppendableEuropean() {
    StringBuilder out = null;
    new MarbleSolitaireTextView(new EuropeanSolitaireModel(), out);
  }

  // test if the toString method illustrate the EnglishSolitaire model correctly
  @Test
  public void testToStringEnglish() {
    assertEquals("    O O O\n" +
                 "    O O O\n" +
                 "O O O O O O O\n" +
                 "O O O _ O O O\n" +
                 "O O O O O O O\n" +
                 "    O O O\n" +
                 "    O O O", v1.toString());

    e1.move(1, 3, 3, 3);

    assertEquals("    O O O\n" +
                 "    O _ O\n" +
                 "O O O _ O O O\n" +
                 "O O O O O O O\n" +
                 "O O O O O O O\n" +
                 "    O O O\n" +
                 "    O O O", v1.toString());

    e1.move(2, 5, 2, 3);

    assertEquals("    O O O\n" +
                 "    O _ O\n" +
                 "O O O O _ _ O\n" +
                 "O O O O O O O\n" +
                 "O O O O O O O\n" +
                 "    O O O\n" +
                 "    O O O", v1.toString());

    assertEquals(
        "    O O O\n" +
        "    O O O\n" +
        "O O O O _ O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O", v2.toString());

    e2.move(4, 4, 2, 4);

    assertEquals(
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O _ O O\n" +
        "O O O O _ O O\n" +
        "    O O O\n" +
        "    O O O", v2.toString());

    assertEquals("            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O _ O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O", v3.toString());

    e3.move(7, 9, 9, 9);

    assertEquals("            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O _ O O O O O O O O O\n" +
                 "O O O O O O O O O _ O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O\n" +
                 "            O O O O O O O", v3.toString());

    assertEquals("        _ O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O", v4.toString());

    e4.move(0, 6, 0, 4);

    assertEquals("        O _ _ O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "O O O O O O O O O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O\n" +
                 "        O O O O O", v4.toString());
  }

  // test if the toString method illustrate the EuropeanSolitaire model correctly
  @Test
  public void testToStringEuropean() {
    assertEquals("    O O O\n"
                 + "  O O O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "  O O O O O\n"
                 + "    O O O", v5.toString());

    m1.move(1, 3, 3, 3);

    assertEquals("    O O O\n"
                 + "  O O _ O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "O O O O O O O\n"
                 + "  O O O O O\n"
                 + "    O O O", v5.toString());

    m1.move(2, 5, 2, 3);

    assertEquals("    O O O\n"
                 + "  O O _ O O\n"
                 + "O O O O _ _ O\n"
                 + "O O O O O O O\n"
                 + "O O O O O O O\n"
                 + "  O O O O O\n"
                 + "    O O O", v5.toString());

    assertEquals(
        "    O O O\n"
        + "  O O O O O\n"
        + "O O O O _ O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", v6.toString());

    m2.move(4, 4, 2, 4);

    assertEquals(
        "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ O O\n"
        + "O O O O _ O O\n"
        + "  O O O O O\n"
        + "    O O O", v6.toString());

    assertEquals("            O O O O O O O\n"
                 + "          O O O O O O O O O\n"
                 + "        O O O O O O O O O O O\n"
                 + "      O O O O O O O O O O O O O\n"
                 + "    O O O O O O O O O O O O O O O\n"
                 + "  O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O _ O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "  O O O O O O O O O O O O O O O O O\n"
                 + "    O O O O O O O O O O O O O O O\n"
                 + "      O O O O O O O O O O O O O\n"
                 + "        O O O O O O O O O O O\n"
                 + "          O O O O O O O O O\n"
                 + "            O O O O O O O", v7.toString());

    m3.move(7, 9, 9, 9);

    assertEquals("            O O O O O O O\n"
                 + "          O O O O O O O O O\n"
                 + "        O O O O O O O O O O O\n"
                 + "      O O O O O O O O O O O O O\n"
                 + "    O O O O O O O O O O O O O O O\n"
                 + "  O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O _ O O O O O O O O O\n"
                 + "O O O O O O O O O _ O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O O O O O O O\n"
                 + "  O O O O O O O O O O O O O O O O O\n"
                 + "    O O O O O O O O O O O O O O O\n"
                 + "      O O O O O O O O O O O O O\n"
                 + "        O O O O O O O O O O O\n"
                 + "          O O O O O O O O O\n"
                 + "            O O O O O O O", v7.toString());

    assertEquals("        _ O O O O\n"
                 + "      O O O O O O O\n"
                 + "    O O O O O O O O O\n"
                 + "  O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "  O O O O O O O O O O O\n"
                 + "    O O O O O O O O O\n"
                 + "      O O O O O O O\n"
                 + "        O O O O O", v8.toString());

    m4.move(0, 6, 0, 4);

    assertEquals("        O _ _ O O\n"
                 + "      O O O O O O O\n"
                 + "    O O O O O O O O O\n"
                 + "  O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "O O O O O O O O O O O O O\n"
                 + "  O O O O O O O O O O O\n"
                 + "    O O O O O O O O O\n"
                 + "      O O O O O O O\n"
                 + "        O O O O O", v8.toString());
  }

  // test if the RenderBoard method renders EnglishSolitaire model correctly.
  @Test
  public void testRenderBoardEnglish() throws IOException {
    StringBuilder out = new StringBuilder();
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, out);

    view.renderBoard();
    assertEquals("    O O O\n" +
                 "    O O O\n" +
                 "O O O O O O O\n" +
                 "O O O _ O O O\n" +
                 "O O O O O O O\n" +
                 "    O O O\n" +
                 "    O O O", out.toString());

    model.move(1, 3, 3, 3);

    StringBuilder changed = new StringBuilder();
    MarbleSolitaireTextView moved = new MarbleSolitaireTextView(model, changed);
    moved.renderBoard();

    assertEquals("    O O O\n" +
                 "    O _ O\n" +
                 "O O O _ O O O\n" +
                 "O O O O O O O\n" +
                 "O O O O O O O\n" +
                 "    O O O\n" +
                 "    O O O", changed.toString());

    // tests for IOExceptions
    EnglishSolitaireModel validModel = new EnglishSolitaireModel();
    MarbleSolitaireTextView invalidView = new MarbleSolitaireTextView(validModel,
        new InvalidAppendable());

    try {
      invalidView.renderBoard();
      fail("Can't render board");
    } catch (IOException e) {
      // it should catch exception since it was given an InvalidAppendable
    }
  }

  // test if the RenderBoard method renders EuropeanSolitaire model correctly.
  @Test
  public void testRenderBoardEuropean() throws IOException {
    StringBuilder out = new StringBuilder();
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, out);

    view.renderBoard();
    assertEquals("    O O O\n"
                 + "  O O O O O\n"
                 + "O O O O O O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "  O O O O O\n"
                 + "    O O O", out.toString());

    model.move(1, 3, 3, 3);

    StringBuilder changed = new StringBuilder();
    MarbleSolitaireTextView moved = new MarbleSolitaireTextView(model, changed);
    moved.renderBoard();

    assertEquals("    O O O\n"
                 + "  O O _ O O\n"
                 + "O O O _ O O O\n"
                 + "O O O O O O O\n"
                 + "O O O O O O O\n"
                 + "  O O O O O\n"
                 + "    O O O", changed.toString());

    // tests for IOExceptions
    EuropeanSolitaireModel validModel = new EuropeanSolitaireModel();
    MarbleSolitaireTextView invalidView = new MarbleSolitaireTextView(validModel,
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
    StringBuilder outEnglish = new StringBuilder();
    EnglishSolitaireModel englishModel = new EnglishSolitaireModel();
    MarbleSolitaireTextView englishView = new MarbleSolitaireTextView(englishModel, outEnglish);

    englishView.renderMessage("Game over!");
    assertEquals("Game over!", outEnglish.toString());

    // tests for IOExceptions
    EnglishSolitaireModel validEnglishModel = new EnglishSolitaireModel();
    MarbleSolitaireTextView invalidEnglishView = new MarbleSolitaireTextView(validEnglishModel,
        new InvalidAppendable());

    try {
      invalidEnglishView.renderMessage("Should throw exception");
      fail("Can't render message");
    } catch (IOException e) {
      // it should catch exception since it was given an InvalidAppendable
    }

    StringBuilder outEuropean = new StringBuilder();
    EuropeanSolitaireModel europeanModel = new EuropeanSolitaireModel();
    MarbleSolitaireTextView europeanView = new MarbleSolitaireTextView(europeanModel, outEuropean);

    europeanView.renderMessage("Game over!");
    assertEquals("Game over!", outEuropean.toString());

    // tests for IOExceptions
    EuropeanSolitaireModel validEuropeanModel = new EuropeanSolitaireModel();
    MarbleSolitaireTextView invalidEuropeanView = new MarbleSolitaireTextView(validEuropeanModel,
        new InvalidAppendable());

    try {
      invalidEuropeanView.renderMessage("Should throw exception");
      fail("Can't render message");
    } catch (IOException e) {
      // it should catch exception since it was given an InvalidAppendable
    }
  }
}