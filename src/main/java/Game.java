import gamescreen.MainWindow;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private MainWindow window;
    public Game() {
        this.window = new MainWindow();
    }
}
