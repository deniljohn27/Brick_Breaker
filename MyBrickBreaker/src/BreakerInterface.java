import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class BreakerInterface extends JPanel implements ActionListener, KeyListener {

    int score=0;

    int xCoball=230;
    int yCoball=540;
    int ballWidth=25;
    int ballHeight=25;
    int max_xCOball;
    int max_yCOball;
    int ballSpeedX=-2;
    int ballSpeedY=-3;
    Timer time;
    BrickGenerator brick;
    boolean start=false;
    int row=4,col=6,bricks=row*col;
    Graphics g;
    JLabel starts,restart,scores;

    BreakerInterface(){
         starts=new JLabel();
         restart=new JLabel();
         scores=new JLabel();

        this.setLayout(null);
        this.setSize(544,600);
        this.setBackground(Color.BLACK);
        this.max_xCOball=530-ballWidth;
        this.max_yCOball=600-ballHeight;

        starts.setText("PRESS SPACE TO START/PAUSE");
        starts.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        starts.setBounds(110,240,400,100);

        restart.setText("SHIFT TO RESTART");
        restart.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        restart.setBounds(160,285,300,100);

        scores.setText("SCORE : : 0");
        scores.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        scores.setBounds(385,-35,300,100);
        scores.setForeground(Color.RED);


        brick=new BrickGenerator(row,col);

        this.add(starts);
        this.add(restart);
        this.add(scores);
        this.addKeyListener(this);
        this.setFocusable(true);
        time=new Timer(11,this);
        time.start();

    }


    int padX=230;
    int padY=565;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //ball
//        g.setColor(Color.BLUE);
//        g.fillOval(xCoball,yCoball,ballWidth,ballHeight);

          URL iconURL = getClass().getResource("fitness-ball.png");
          Image iconb = new ImageIcon(iconURL).getImage();
          g.drawImage(iconb,xCoball,yCoball,ballWidth,ballHeight,null);

        //down Pad section
//        g.setColor(Color.YELLOW);
//        g.fillRect(padX,padY,100,15);
        URL iconURLp = getClass().getResource("padd.png");
        Image iconp = new ImageIcon(iconURLp).getImage();
        g.drawImage(iconp,padX,padY,100,15,null);

        //brick
        brick.paint(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(start) {



            this.remove(starts);
            this.remove(restart);
            //ballMove
            if (xCoball < 0 || xCoball > max_xCOball) {
                ballSpeedX = -ballSpeedX;
            }//direction change in x
            if (yCoball < 0) {
                ballSpeedY = -ballSpeedY;
            }//direction change in y
            xCoball = xCoball + ballSpeedX;//ball moves x
            yCoball = yCoball + ballSpeedY;//ball moves y


            //bounce form pad
            Rectangle ballRect = new Rectangle(xCoball - 2, yCoball - 2, ballWidth + 2, ballHeight + 2);
            Rectangle padRect = new Rectangle(padX - 1, padY - 1, 100, 16);

            if (ballRect.intersects(padRect)) {
                ballSpeedY = -ballSpeedY;
            }
            //create brick rectangle
           A: for (int i = 0; i < brick.mat.length; i++) {
                for (int j = 0; j < brick.mat[0].length; j++) {
                    if (brick.mat[i][j] > 0) {
                        int width = brick.width;
                        int height = brick.height;
                        int xBrick = j * width + 28;
                        int yBrick = i * height + 25;

                        Rectangle coverBrick = new Rectangle(xBrick, yBrick, width, height);
                        if (coverBrick.intersects(ballRect)) {
                            brick.mat[i][j] = 0;
                            if (xCoball + 26 <= coverBrick.x || xCoball+1 >= coverBrick.x + width) {
                                ballSpeedX = -ballSpeedX;
                            }
                            else if(yCoball+1>=coverBrick.y+height||yCoball+26<= coverBrick.y) {
                                ballSpeedY = -ballSpeedY;
                            }
                            score=score+5;
                            scores.setText("SCORE : :" + score);
                            //bricks--;

                        }
                    }
                }
                if(score==bricks*5){
                    starts.setText("YOU WON");
                    starts.setForeground(Color.GREEN);
                    starts.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
                    starts.setBounds(150,250,400,100);
                    this.add(starts);
                   // time.stop();
                    start=false;
                    repaint();

                    break A;
                }
            }
            if(yCoball>padY-12){
                restart.setText("YOU FAILED");
                restart.setForeground(Color.RED);
                restart.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
                restart.setBounds(150,275,400,100);
                this.add(restart);

                start=false;
                repaint();

            }

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){//start pause game
            if(start==false){start=true;}
            else{start=false;}
        }
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){//restart game
            if(start==false){start=true;}
            else{start=false;}
             score=0;
             xCoball=230;
             yCoball=540;
             ballWidth=25;
             ballHeight=25;
             ballSpeedX=-2;
             ballSpeedY=-3;
             brick=new BrickGenerator(row,col);
             brick.paint(g);
             repaint();

             start=false;


        }
        if(start) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (padX >= 442) {
                    padX = 442;
                } else {
                    padX = padX + 20;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (padX <= 0) {
                    padX = 0;
                } else {
                    padX = padX - 20;
                }

            }
            repaint();
        }


    }



    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}
