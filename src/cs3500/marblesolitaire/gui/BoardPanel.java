package cs3500.marblesolitaire.gui;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardPanel extends JPanel implements BoardPanelListener {

  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX, originY;
  List<Features> featureListeners = new ArrayList<>();

  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
          new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
              , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (int) (this.getPreferredSize().getWidth() / 2
                     - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2
                     - this.modelState.getBoardSize() * cellDimension / 2);

    //your code to the draw the board should go here.
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell

    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        switch (this.modelState.getSlotAt(j, i)) {
          case Empty:
            g.drawImage(this.emptySlot, i * cellDimension + 4, j * cellDimension + 4,
                null);
            break;
          case Marble:
            g.drawImage(this.marbleSlot, i * cellDimension + 4, j * cellDimension + 4,
                null);
            break;
          case Invalid:
            g.drawImage(this.blankSlot, i * cellDimension + 4, j * cellDimension + 4,
                null);
            break;
          default:
            // should not reach here
            break;
        }
      }
    }
  }

  @Override
  public void addFeatures(Features object) {
    this.featureListeners.add(object);

    addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
      }
    });
  }
}
