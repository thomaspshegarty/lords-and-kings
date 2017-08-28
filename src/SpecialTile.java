
public class SpecialTile extends Tile {
    SpecialTile(int across, int hi, String land) {
        super(across, hi, land);
        setFilename("images/special tile.png");
        setSelectedFilename("images/special tile selected.png");
    }

    public void setValues(String type) {
        super.setValues(type);
        if(getVisibility()){
            setFilename("images/special tile.png");
            setSelectedFilename("images/special tile selected.png");
        }
    }
}
