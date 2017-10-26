package ponggame; /**
 * Created by JRTaylor on 12/19/2016.
 */

import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;

public class GameWorld {
    private int rightScore;
    private int leftScore;
    private Paddle rightPaddle;
    private Paddle leftPaddle;
    private Ball ball;
    private int maxX;
    private int maxY;
    private boolean roundRunning;
    private boolean leftServe;

    public GameWorld(){
        rightScore=0;
        leftScore=0;
        this.ball = new Ball(50,100,10);
        this.leftPaddle = new Paddle(20,100,100,20);
        this.rightPaddle = new Paddle(350,100,100,20);
        this.roundRunning = false;
        this.leftServe = true;
    }

    public Paddle getRightPaddle(){
        return rightPaddle;
    }

    public Paddle getLeftPaddle(){
        return leftPaddle;
    }

    public Ball getBall(){
        return ball;
    }

    public String getLeftScore(){
        return Integer.toString(this.leftScore);
    }

    public String getRightScore(){
        return Integer.toString(this.rightScore);
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightPaddle.moveUp();
        }

        if (key == KeyEvent.VK_DOWN) {
            rightPaddle.moveDown();
        }

        if (key == KeyEvent.VK_W) {
            leftPaddle.moveUp();
        }
        if (key == KeyEvent.VK_S){
            leftPaddle.moveDown();
        }
        if (key == KeyEvent.VK_LEFT){
            if(!roundRunning && ball.getX()>= 100){
                ball.serve(rightPaddle);
                roundRunning = true;
            }
        }
        if (key == KeyEvent.VK_D){
            if (!roundRunning && ball.getX()<100) {
                ball.serve(leftPaddle);
                roundRunning = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightPaddle.stop("up");
        }

        if (key == KeyEvent.VK_DOWN) {
            rightPaddle.stop("down");
        }

        if (key == KeyEvent.VK_W) {
            leftPaddle.stop("up");
        }
        if (key == KeyEvent.VK_S){
            leftPaddle.stop("down");
        }
    }

    /**
     * Updates the world each tick
     */
    public void update(){
        int scrCheck;
        rightPaddle.update();
        leftPaddle.update();
        // if the round is running, go through standard game loop
        // otherwise keep the ball in front of one of the paddles until served by that side
        if (roundRunning) {
            scrCheck = ball.update(leftPaddle, rightPaddle);
            if (scrCheck != 0) {
                roundRunning = false;
                if (scrCheck > 0) {
                    rightScore += 1;
                    System.out.printf("Right Score: %d\n", rightScore);
                    this.leftServe = true;
                } else {
                    leftScore += 1;
                    System.out.printf("Left Score: %d\n", leftScore);
                    this.leftServe = false;
                }
            }
        }
        else{
            if(leftServe) {
                ball.servesFrom(leftPaddle);
            }
            else{
                ball.servesFrom(rightPaddle);
            }
        }
    }


}
