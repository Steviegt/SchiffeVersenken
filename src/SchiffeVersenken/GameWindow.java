package SchiffeVersenken;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public static GamePanel gamePanel;

    public GameWindow(int width, int height){
        setTitle("Schiffe Versenken");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0,0,width,height);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        setVisible(true);

    }
}
