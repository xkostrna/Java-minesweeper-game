package gamescreen;

import game.GameField;
import lombok.Getter;
import lombok.Setter;
import mousehandler.MouseEventManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

@Getter
@Setter
public class GameScreen extends Canvas implements MouseListener {


    private static final int SIZE = 310;
    private static final int BLOCK_SIZE = 30;
    private static final int OFFSET = 10;

    private GameField gameField;
    private MouseEventManager manager;

    public int getSIZE() {
        return SIZE;
    }

    public int getBlockSize() {
        return BLOCK_SIZE;
    }

    public int getOFFSET() {
        return OFFSET;
    }

    public GameScreen() {
        super();
        this.gameField = new GameField();
        this.manager = new MouseEventManager(this, this.gameField);
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.setMinimumSize(new Dimension(SIZE, SIZE));
        this.setVisible(true);
        this.setFocusable(true);
        reDrawGameScreen();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0,0, SIZE, SIZE);
        int size = this.gameField.getFieldSize();
        int xOffset;
        int yOffset = OFFSET;
        g2d.setColor(Color.green);
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                yOffset += BLOCK_SIZE;
            }
            xOffset = OFFSET;
            for (int j = 0; j < size; j++) {
                g2d.fillRect(xOffset, yOffset, BLOCK_SIZE/2, BLOCK_SIZE/2);
                xOffset += BLOCK_SIZE;
            }
        }
    }

    public void drawRedSquareToEndGame(Point point) {
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.setColor(Color.red);
        g2d.fillRect(point.x, point.y, BLOCK_SIZE/2, BLOCK_SIZE/2);
    }

    public void drawStringEndGame() {
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.setColor(Color.red);
        g2d.drawString("GAME OVER", SIZE/2, SIZE/2);
    }

    public void drawClickedPoint(Point point) {
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.setColor(Color.white);
        int y = point.x * BLOCK_SIZE + OFFSET;
        int x = point.y * BLOCK_SIZE + OFFSET;
        g2d.fillRect(x, y, BLOCK_SIZE/2, BLOCK_SIZE/2);
    }

    public void drawNeighbours(ArrayList<Point> neighbours) {
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        for (Point point : neighbours) {
            g2d.setColor(Color.white);
            int y = point.x * BLOCK_SIZE + OFFSET;
            int x = point.y * BLOCK_SIZE + OFFSET;
            g2d.fillRect(x, y, BLOCK_SIZE/2, BLOCK_SIZE/2);
        }
    }

    private void reDrawGameScreen() {
        this.revalidate();
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.manager.handleMouseEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
