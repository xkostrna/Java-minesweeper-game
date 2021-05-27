package game;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class GameField {
    private static final int FIELD_SIZE = 10;
    private Tile[][] gameField;

    public GameField() {
        this.gameField = new Tile[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                this.gameField[i][j] = new Tile();
                Random random = new Random();
                if (random.nextBoolean()) {
                    this.gameField[i][j].setMine(true);
                }
            }
        }
        printField();
    }

    private void printField() {
        for (Tile[] block : this.gameField) {
            System.out.println();
            for (Tile tile : block) {
                System.out.print(tile.isMine() + " ");
            }
        }
        System.out.println();
    }

    public int getFieldSize() {
        return FIELD_SIZE;
    }
}
