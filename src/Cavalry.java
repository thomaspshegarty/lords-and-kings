/**
 * Created by Stephen on 3/13/2015.
 */
public class Cavalry extends Unit{
    private int cost = 200;
    private int speed = 5;
    private int strength = 6;
    private int siege = 1;

    Cavalry( String color) {
        super(color);
        setFilename(color+" cavalry.png");
    }


}
