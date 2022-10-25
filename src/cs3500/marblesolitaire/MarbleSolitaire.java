package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import java.io.InputStreamReader;

/**
 * The MarbleSolitaire game.
 */
public final class MarbleSolitaire {

  /**
   * The main method that takes in user inputs.
   *
   * @param args the user inputs
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    MarbleSolitaireModel model = null;
    MarbleSolitaireView view = null;

    int size = 0;
    int holeR = 0;
    int holeC = 0;

    // when user only specifies board mode
    if (args.length == 1) {
      switch (args[0]) {
        case "english":
          model = new EnglishSolitaireModel();
          view = new MarbleSolitaireTextView(model, ap);
          break;
        case "european":
          model = new EuropeanSolitaireModel();
          view = new MarbleSolitaireTextView(model, ap);
          break;
        case "triangular":
          model = new TriangleSolitaireModel();
          view = new TriangleSolitaireTextView(model, ap);
          break;
        default:
          System.out.println("please specify the game mode");
          break;
      }
    }

    // when the user only put board mode and -size
    if (args.length == 3) {
      try {
        size = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        System.err.println("Argument must be an integer.");
        System.exit(1);
      }

      switch (args[0]) {
        case "english":
          model = new EnglishSolitaireModel(size);
          view = new MarbleSolitaireTextView(model, ap);
          break;
        case "european":
          model = new EuropeanSolitaireModel(size);
          view = new MarbleSolitaireTextView(model, ap);
          break;
        case "triangular":
          model = new TriangleSolitaireModel(size);
          view = new TriangleSolitaireTextView(model, ap);
          break;
        default:
          System.out.println("please specify the game mode");
          break;
      }
    }

    // when the user only put board mode and -hole
    if (args.length == 4) {
      try {
        holeR = Integer.parseInt(args[2]);
        holeC = Integer.parseInt(args[3]);
      } catch (NumberFormatException e) {
        System.err.println("Argument must be an integer.");
        System.exit(1);
      }

      switch (args[0]) {
        case "english":
          model = new EnglishSolitaireModel(holeR, holeC);
          view = new MarbleSolitaireTextView(model, ap);
          break;
        case "european":
          model = new EuropeanSolitaireModel(holeR, holeC);
          view = new MarbleSolitaireTextView(model, ap);
          break;
        case "triangular":
          model = new TriangleSolitaireModel(holeR, holeC);
          view = new TriangleSolitaireTextView(model, ap);
          break;
        default:
          System.out.println("please specify the game mode");
          break;
      }
    }

    // when the user puts in the board mode, -size, and -hole
    if (args.length == 6) {
      switch (args[1]) {
        case "-size":
          try {
            size = Integer.parseInt(args[2]);
            holeR = Integer.parseInt(args[4]);
            holeC = Integer.parseInt(args[5]);
          } catch (NumberFormatException e) {
            System.err.println("Argument must be an integer.");
            System.exit(1);
          }
          break;
        case "-hole":
          try {
            holeR = Integer.parseInt(args[2]);
            holeC = Integer.parseInt(args[3]);
            size = Integer.parseInt(args[5]);
          } catch (NumberFormatException e) {
            System.err.println("Argument must be an integer.");
            System.exit(1);
          }
          break;
        default:
          System.out.println("please specify the game mode");
          break;
      }

      switch (args[0]) {
        case "english":
          model = new EnglishSolitaireModel(size, holeR, holeC);
          view = new MarbleSolitaireTextView(model, ap);
          break;
        case "european":
          model = new EuropeanSolitaireModel(size, holeR, holeC);
          view = new MarbleSolitaireTextView(model, ap);
          break;
        case "triangular":
          model = new TriangleSolitaireModel(size, holeR, holeC);
          view = new TriangleSolitaireTextView(model, ap);
          break;
        default:
          System.out.println("please specify the game move");
          break;
      }
    }

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
  }
}