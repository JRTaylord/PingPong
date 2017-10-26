package ponggame;

/**
 * Created by JRTaylor on 12/19/2016.
 */
public class Ball implements IBall {
    private float x;
    private float y;
    private double v;
    private int theta;
    //side length
    private int s;

    public Ball(float x, float y, int s){
        this.x = x;
        this.y = y;
        this.v = 0;
        this.theta = 0;
        this.s = s;
    }

    public boolean scoreForRight(){
        return this.x < 0;
    }

    public boolean scoreForLeft(){
        return this.x > 400;
    }

    private void paddleBounce(Paddle l, Paddle r){
        // check paddle l's hitbox
        if ((90 < theta) && (theta < 270)){
            if(this.collidesWith(l)){
                v += 0.25;
                bounce(l);
            }
        }
        // check paddle r's hitbox
        else{
            if(this.collidesWith(r)){
                v += 0.25;
                bounce(r);
                theta = (540 - theta) % 360;
            }
        }
    }


    /**
     * Collision Management section
     * 7 Zones
     * 1 in the middle with angle of 0
     * 2 in above and below with angle 15
     * 2 more with angle of 30
     * 2 more with angle of 45
     */

    private void bounce(Paddle p){
        //Orientation of the ball should only be relative to the paddle
        double ballT=this.y;
        double ballB=this.y+this.s;
        double ballRelPosn = y + s*0.5 - p.y;
        double padT=p.y;
        double padB=p.y+p.h;
        // 45 degree sections
        if (ballRelPosn>(p.h*6/7))
            theta = 45;
        else if (ballRelPosn<(p.h/7))
            theta = 315;
        // 30 degree sections
        else if (ballRelPosn>(p.h*5/7))
            theta = 30;
        else if (ballRelPosn<(p.h*2/7))
            theta = 330;
        // 15 degree sections
        else if (ballRelPosn>(p.h*4/7))
            theta = 15;
        else if (ballRelPosn<(p.h*3/7))
            theta = 345;
        // Middle section
        else
            theta = 0;
    }

    //hitbox collision detection
    private boolean collidesWith(Paddle p){
        double ballT=this.y;
        double ballB=this.y+this.s;
        double ballR=this.x+this.s;
        double ballL=this.x;
        double padT=p.y;
        double padB=p.y+p.h;
        double padR=p.x+p.w;
        double padL=p.x;
        return (((ballB>=padT && ballB<=padB) || (ballT >= padT && ballT <= padB))// y collision detection
                &&((ballL>=padL && ballL<=padR) || (ballR>=padL && ballR<=padR)));// x collision detection
    }


    //Causes the ball to ricochet off the top and bottom edges
    private void edgeBounce(){
        //Since the y axis is flipped compared to a normal cartesian graph ...
        if((y + s >= 275) || (y - s <= 0))
            theta = 360 - theta;
    }

    /**
     * Updates the ball
     * @param l
     * @param r
     */
    public int update(Paddle l, Paddle r){
        this.edgeBounce();
        this.paddleBounce(l, r);
        //System.out.println(theta);
        if(this.scoreForLeft()){
            System.out.println("Score for Left");
            return -1;
        }
        else if(this.scoreForRight()){
            System.out.println("Score for Right");
            return 1;
        }
        else {
            this.x += this.v*Math.cos(theta*Math.PI/180);
            this.y += this.v*Math.sin(theta*Math.PI/180);
            return 0;
        }
    }
    //serves the ball
    public void serve(Paddle p){
        //left paddle
        if(p.x<100){
            this.v = 2;
            theta = 0;
        }
        // right paddle
        else{
            this.v = 2;
            theta = 180;
        }
    }

    public void servesFrom(Paddle p){
        v = 0;
        this.y = p.getY()+(p.getH()-this.s)/2;
        // left paddle
        if(p.getX()<100){
            this.x = p.getX()+p.getW();
        }
        // right paddle
        else{
            this.x = p.getX()-this.s;
        }
    }

    // Gets Position
    public int getX(){
        return Math.round(x);
    }
    public int getY(){
        return Math.round(y);
    }
    // Gets side length
    public int getS(){
        return this.s;
    }
}
