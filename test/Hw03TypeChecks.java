import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.InputStreamReader;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw03TypeChecks {

  /**
   * the main program for the game.
   *
   * @param args needed args to run the game
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, ap);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    //helper(new EnglishSolitaireModel(),
    //        rd, ap);
    //helper(new EnglishSolitaireModel
    //                (3, 3),
    //        rd, ap);
    //  }

    //private static void helper
    //(cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model, Readable rd, Appendable ap) {
    //  new cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl(model,
    //      new cs3500.marblesolitaire.view.MarbleSolitaireTextView(model, ap), rd);
    //}
  }
}
