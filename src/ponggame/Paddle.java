package ponggame;

/**
 * Created by JRTaylor on 12/19/2016.
 */
public class Paddle implements IPaddle {
    public int x;
    public int y;
    private int dy;
    public int h;
    public int w;
    // prevents controls from stopping each other
    private int set;

    public Paddle(int x, int y, int h, int w){
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.dy = 0;
        this.set = 0;
    }

    //Moves the paddle down
    public void moveDown(){
        if (set==0 || set==-1) {
            if (this.y + this.h + dy <= 274) {
                this.dy = 3;
            } else {
                this.dy = 0;
            }
            set=-1;
        }
    }

    //Moves the paddle up
    public void moveUp(){
        if (set==0 || set==1) {
            if (this.y + dy >= 1) {
                this.dy = -3;
            } else {
                this.dy = 0;
            }
            set=1;
        }
    }

    public void stop(String dir){
        if(dir.equals("up") && set==1) {
            this.dy = 0;
            this.set = 0;
        }
        else if(dir.equals("down") && set==-1){
            this.dy = 0;
            this.set = 0;
        }
    }

    public void update(){
        if(y+dy>0&&y+h+dy<275) {
            this.y += this.dy;
        }
        else if(y+dy<=0){
            y=1;
        }
        else{
            y=274-h;
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getH(){
        return h;
    }

    public int getW(){
        return w;
    }

    public int getDy() {
        return dy;
    }
}
