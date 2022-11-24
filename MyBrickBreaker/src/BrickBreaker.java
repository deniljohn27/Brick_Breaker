import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BrickBreaker extends JFrame {
    BrickBreaker(){

        this.setSize(560,638);
        this.setLayout(null);
        this.setTitle("BRICK BREAKER");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        BreakerInterface game= new BreakerInterface();

        URL iconURL = getClass().getResource("wall.png");
        ImageIcon icon = new ImageIcon(iconURL);

        this.setIconImage(icon.getImage());
        this.add(game);
        this.setVisible(true);


    }
}
