package cs3500.marblesolitaire.gui;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.util.ArrayList;
import java.util.List;

public class MarbleSolitaireControllerGUIImpl implements Features {

  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;

  public MarbleSolitaireControllerGUIImpl(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    this.model = model;
    this.view = view;

    view.addFeatures(this);
  }

  @Override
  public void coordinate(int row, int col) {
    List<Integer> values = new ArrayList<>();

    if (model.getSlotAt(row, col) == SlotState.Marble) {
      values.add(row);
      values.add(col);
    }

    if (model.getSlotAt(row, col) == SlotState.Empty) {
      values.add(row);
      values.add(col);
      try {
        this.model.move(values.remove(0), values.remove(0), values.remove(0),
            values.remove(0));
        view.refresh();
      } catch (IllegalArgumentException e) {
        view.renderMessage("Invalid Move!");
      }
    }

    if (this.model.isGameOver()) {
      view.renderMessage("Game Over!");
    }
  }


}
