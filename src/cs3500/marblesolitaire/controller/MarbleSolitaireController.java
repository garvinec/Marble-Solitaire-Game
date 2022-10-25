package cs3500.marblesolitaire.controller;

/**
 * This interface represents operations that should be offered by a controller for the Marble
 * solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * This method plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input or
   *                               transmit output
   */
  void playGame() throws IllegalStateException;

}
