package mousehandler;

import game.GameField;
import game.Tile;
import gamescreen.GameScreen;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

@Getter
@Setter
public class MouseEventManager {
    private GameScreen gameScreen;
    private GameField gameField;

    public MouseEventManager(GameScreen gameScreen, GameField gameField) {
        this.gameScreen = gameScreen;
        this.gameField = gameField;
    }

    public void handleMouseEvent(MouseEvent event) {
        int xOnCanvas = event.getX();
        int yOnCanvas = event.getY();
        int dividerX = xOnCanvas / this.gameScreen.getBlockSize();
        int dividerY = yOnCanvas / this.gameScreen.getBlockSize();
        int blockX = dividerX * this.gameScreen.getBlockSize() + this.gameScreen.getOFFSET();
        int blockY = dividerY * this.gameScreen.getBlockSize() + this.gameScreen.getOFFSET();
        if (xOnCanvas > blockX && xOnCanvas < blockX + this.gameScreen.getBlockSize()) {
            if (yOnCanvas > blockY && yOnCanvas < blockY + this.gameScreen.getBlockSize()) {
                makeInteractionWithTile(new Point(blockX, blockY));
            }
        }
    }

    private void makeInteractionWithTile(Point tile) {
        int tileX = (tile.y-10)/30;
        int tileY = (tile.x-10)/30;
        Tile clickedTile = this.gameField.getGameField()[tileX][tileY];
        if (!clickedTile.isMine()) {
            checkNeighbours(new Point(tileX, tileY));
        }
        else {
            gameOver(tile);
        }
    }

    private void checkNeighbours(Point point) {
        int xLowerBoundX = point.x - 1;
        int xUpperBoundY = point.x + 1;

        int yLowerBoundX = point.y - 1;
        int yUpperBoundY = point.y + 1;

        Point xPoint = new Point(xLowerBoundX, xUpperBoundY);
        Point yPoint = new Point(yLowerBoundX, yUpperBoundY);

        ArrayList<Point> freePositions = countFreeNeighboursDiagonal(xPoint, yPoint);

        if (checkNeighbours(freePositions, point)) {
            this.gameScreen.drawClickedPoint(point);
            this.gameScreen.drawNeighbours(freePositions);
        }
        else {
            this.gameScreen.drawClickedPoint(point);
            System.out.println("number of mines in close distance : " + countMines(xPoint, yPoint));
        }

    }

    private ArrayList<Point> countFreeNeighboursDiagonal(Point xPoint, Point yPoint) {
        ArrayList<Point> freePositions = new ArrayList<>();
        for (int i = xPoint.x; i <= xPoint.y; i++) {
            for (int j = yPoint.x; j <= yPoint.y; j++) {
                try {
                    if (!this.gameField.getGameField()[i][j].isMine()) {
                        freePositions.add(new Point(i,j));
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return freePositions;
    }

    private int countMines(Point xPoint, Point yPoint) {
        int counter = 0;
        for (int i = xPoint.x; i <= xPoint.y; i++) {
            for (int j = yPoint.x; j <= yPoint.y; j++) {
                try {
                    if (this.gameField.getGameField()[i][j].isMine()) {
                        counter++;
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return counter;
    }

    private boolean checkNeighbours(ArrayList<Point> freePositions, Point centerPoint) {
        // x-1, y-1 // x = x, y = y-1 // x = x+1, y = y // x = x, y = y+1
        Iterator<Point> pointIterator = freePositions.iterator();
        int counter = 0;
        while (pointIterator.hasNext()) {
            Point point = pointIterator.next();
            if (point.x == centerPoint.x - 1 && point.y == centerPoint.y) {
                if (!this.gameField.getGameField()[point.x][point.y].isMine()) {
                    counter++;
                }
            }
            else if (point.x == centerPoint.x + 1 && point.y == centerPoint.y) {
                if (!this.gameField.getGameField()[point.x][point.y].isMine()) {
                    counter++;
                }
            }
            else if (point.x == centerPoint.x && point.y == centerPoint.y -1) {
                if (!this.gameField.getGameField()[point.x][point.y].isMine()) {
                    counter++;
                }
            }
            else if (point.x == centerPoint.x && point.y == centerPoint.y + 1) {
                if (!this.gameField.getGameField()[point.x][point.y].isMine()) {
                    counter++;
                }
            }
            else {
                pointIterator.remove();
            }
        }
        return counter == 4;
    }

    private void gameOver(Point point) {
        this.gameScreen.drawRedSquareToEndGame(point);
        this.gameScreen.drawStringEndGame();
        this.gameScreen.removeMouseListener(this.gameScreen);
    }


}
