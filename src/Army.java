import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Tommy on 4/1/2015.
 */
public class Army {
    private ArrayList<Unit> army;
    private String color;
    private Tile loc;
    private int inf;
    private int cav;
    private int art;

    private Event event;
    private int distance;

    Army(Unit u, String c, Tile t){
        army = new ArrayList<Unit>();
        army.add(u);
        color = c;
        loc = t;
    }
    Army(ArrayList<Unit> au, String c){
        army = au;
        color = c;
    }
    public void setAction(Event e){event = e;}
    public Event getAction(){return event;}

    public void refresh(){
        inf=0;
        cav=0;
        art=0;
        for(Unit u : army){
            if(u.getFilename().contains("infantry")){
                inf++;
            }else if(u.getFilename().contains("cavalry")){
                cav++;
            }else{
                art++;
            }
        }
        if(inf == 0 && art == 0){
        	distance = 2;
        }else{
        	distance = 1;
        }
    }
    
    public int getDistance(){
    	return distance;
    }
    
    public BufferedImage getImage(){
        refresh();
        if(inf>art){
            if(inf>cav){
                Infantry f = new Infantry(color);
                return f.getImage();
            }else{
                Cavalry f = new Cavalry(color);
                return f.getImage();
            }
        }else if(cav>art) {
            Cavalry f = new Cavalry(color);
            return f.getImage();
        }else{
            Artillery f = new Artillery(color);
            return f.getImage();
        }
    }

    public ArrayList<Unit> getArmy(){return army;}

    public int getSize(){return army.size();}
    public int getInf(){return inf;}
    public int getCav(){return cav;}
    public int getArt(){return art;}

    public String getColor() {
        return color;
    }
    public void setLocation(Tile t){
    	loc = t;
    }
    public Tile getLocation(){
    	return loc;
    }
}
