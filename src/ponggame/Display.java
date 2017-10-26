package ponggame; /**
 * Created by JRTaylor on 12/19/2016.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.Element;


//Setting for the game
public class Display extends JPanel implements ActionListener {

    private Timer timer;
    private GameWorld game;
    private final int DELAY = 10;
    private int maxX;
    private int maxY;

    public Display(int maxX, int maxY){
        this.maxX = maxX;
        this.maxY = maxY;

        initDisplay();
    }

    private void initDisplay(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);

        game = new GameWorld();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }


    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        Paddle lP = game.getLeftPaddle();
        Paddle rP = game.getRightPaddle();
        Ball ball = game.getBall();

        g2d.setColor(Color.WHITE);

        g2d.drawString(game.getLeftScore(),100,20);

        g2d.drawString(game.getRightScore(),300,20);

        for(int y=0; y<275; y+=20) {
            g2d.fillRect(195, y, 10, 10);
        }

        g2d.fillRect(lP.getX(),
                lP.getY(), lP.getW(), lP.getH());

        g2d.fillRect(rP.getX(),
                rP.getY(), rP.getW(), rP.getH());

        g2d.fillRect(ball.getX(), ball.getY(), ball.getS(),ball.getS());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        game.update();
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e){
            game.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e);
        }

    }

}
