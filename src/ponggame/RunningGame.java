package ponggame; /**
 * Created by JRTaylor on 12/19/2016.
 */
import java.awt.EventQueue;
import javax.swing.JFrame;

public class RunningGame extends JFrame {
    public RunningGame() {
        initUI();
    }

    private void initUI() {

        add(new Display(400,300));

        setSize(400, 300);
        setResizable(false);
        System.out.println(getSize().getHeight());
        System.out.println(getSize().getWidth());

        setTitle("Pong");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                RunningGame ex = new RunningGame();
                ex.setVisible(true);
            }
        });
    }
}
