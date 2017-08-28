/**
 * Created by Stephen on 3/13/2015.
 */
public class Artillery extends Unit {
    private int cost = 250;
    private int speed = 3;
    private int strength = 4;
    private int siege = 6;

    Artillery(String s) {
        super(s);
        setFilename(s+" artillery.png");
    }
}
