import cs3500.marblesolitaire.gui.MarbleSolitaireControllerGUIImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.gui.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.gui.SwingGuiView;

public class MarbleSolitaireGUI {
  public static void main(String[] args) {
    MarbleSolitaireModelState model = new EnglishSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    MarbleSolitaireControllerGUIImpl controller = new MarbleSolitaireControllerGUIImpl(
        (MarbleSolitaireModel) model, view);
  }
}
