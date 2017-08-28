/**
 * Created by Stephen on 3/13/2015.
 */
public class Infantry extends Unit{
    private int cost = 100;
    private int speed = 3;
    private int strength = 5;
    private int siege = 3;

    Infantry(String s){
        super(s);
        setFilename(s+" infantry.png");
    }
}
