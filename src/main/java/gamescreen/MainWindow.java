package gamescreen;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainWindow extends JFrame {

    private static final int SIZE = 500;

    private GameScreen gameScreen;

    public MainWindow() {
        super("Minesweeper");
        this.setMinimumSize(new Dimension(SIZE, SIZE));
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeGameScreen();
    }

    private void initializeGameScreen() {
        this.gameScreen = new GameScreen();
        this.add(this.gameScreen);
        reDrawMainWindow();
    }

    private void reDrawMainWindow() {
        this.revalidate();
        this.repaint();
    }
}
