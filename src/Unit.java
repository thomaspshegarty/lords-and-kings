import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Unit{
    private int cost;
    private int speed;
    private int strength;
    private int siege;
    private String filename;
    private String c;

    Unit(String color){
        c = color;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public void moveTo(){}
    public BufferedImage getImage(){
        try {
            File f = new File(getClass().getResource("images/"+filename).toURI());
            BufferedImage image = ImageIO.read(f);
            return image;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void paintComponent(Graphics g){
        g.drawImage(getImage(),0,0,null);
    }

    public String getFilename() {
        return filename;
    }
}
