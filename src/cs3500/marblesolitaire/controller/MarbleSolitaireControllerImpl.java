package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the controller implementation class. It takes in inputs from users and updates the model
 * and view accordingly.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable in;

  /**
   * This constructor is for the controller of the game.
   *
   * @param model the model of Marble Solitaire
   * @param view  the view of Marble Solitaire
   * @param in    input given by the user
   * @throws IllegalArgumentException if either one of the parameters is null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
      Readable in)
      throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("model, view, and the input must not be empty");
    }

    this.model = model;
    this.view = view;
    this.in = in;
  }

  /**
   * playGame is the method that prompts the program to run the game, render the board and message.
   *
   * @throws IllegalStateException if the user didn't enter any inputs
   */
  @Override
  public void playGame() throws IllegalStateException {

    this.drawBoard();
    this.writeMessage("\nScore: " + this.model.getScore() + "\n");

    Scanner scan = new Scanner(this.in);
    ArrayList<Integer> values = new ArrayList<>();
    String userInput;

    while (!this.model.isGameOver()) {
      try {
        userInput = scan.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("Please enter inputs\n");
      }

      if (userInput.equalsIgnoreCase("q")) {
        this.writeMessage("Game quit!\n");
        this.writeMessage("State of game when quit:\n");
        this.drawBoard();
        this.writeMessage("\nScore: " + this.model.getScore());
        return;
      }

      if (validInput(userInput)) {
        values.add(Integer.parseInt(userInput) - 1);
      } else {
        writeMessage("Input has to be a positive integer, please re-enter an input\n");
      }

      if (values.size() >= 4) {
        try {
          this.model.move(values.remove(0), values.remove(0), values.remove(0),
              values.remove(0));
          this.drawBoard();
          this.writeMessage("\nScore: " + this.model.getScore() + "\n");
        } catch (IllegalArgumentException e) {
          writeMessage("Please enter a valid move\n");
        }
      }
    }

    this.writeMessage("Game over!\n");
    this.drawBoard();
    this.writeMessage("\nScore: " + this.model.getScore());
  }

  // if the input is an int greater than 0 return true
  // otherwise false
  private boolean validInput(String userInput) {
    try {
      return Integer.parseInt(userInput) > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  // helper for render message
  private void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  // helper for render board
  private void drawBoard() throws IllegalStateException {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
