import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BrickGenerator {
    int [][]mat ;
    int row,col,width,height;


    BrickGenerator(int row,int col){
        mat=new int[row][col];
        this.row=row;
        this.col=col;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                mat[i][j]=1;
            }
        }
        width=440/col;
        height=200/row;
    }
    public void paint(Graphics g){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(mat[i][j]>0){
//                    g.setColor(Color.WHITE);
//                    g.fillRect(j*width+40,i*height+35,width,height);
                    URL iconURL = getClass().getResource("wall.png");
                    Image iconb = new ImageIcon(iconURL).getImage();
                    g.drawImage(iconb,j*width+50,i*height+40,width,height,null);

                    //border
                    g.setColor(Color.BLACK);
                    g.drawRect(j*width+50,i*height+35,width,height);

                }
            }
        }
    }
    public void setZero(int zero,int i,int j){
        mat[i][j]=0;
    }
}
