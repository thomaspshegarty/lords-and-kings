public class NormalTile extends Tile {

    NormalTile(int across, int hi, String land) {
        super(across, hi, land);
        setSelectedFilename("images/basic tile selected.png");
        setFilename("images/basic tile.png");
        setValues(land);
    }

    public void setValues(String type){
        setLandType(type);
        if(getLandType().equals("forest")){
            if(isControlled()) {
                setFilename("images/" + getController().getColor() + " forest tile.png");
                setSelectedFilename("images/" + getController().getColor() + " forest tile selected.png");
            }else{
                setFilename("images/forest tile.png");
                setSelectedFilename("images/forest tile selected.png");
            }
            setDefense(2.0);
            setIncome(1.0);
            setSteelProduction(0.0);
            setStrength(2.0);
            setUnrest(0.0);
            setWoodProduction(2.0);
        }else if(getLandType().equals("capital")){
        	if(isControlled()) {
                setFilename("images/"+getController().getColor()+" basic castle tile.png");
                setSelectedFilename("images/" + getController().getColor() + " basic castle tile selected.png");
            }else{
                setFilename("images/basic castle tile.png");
                setSelectedFilename("images/basic castle tile selected.png");
            }
            setDefense(3.0);
            setIncome(3.0);
            setSteelProduction(0.0);
            setStrength(3.0);
            setUnrest(0.0);
            setWoodProduction(0.0);
        	
        }else if(getLandType().equals("plain")){
       
            if(isControlled()) {
                setFilename("images/"+getController().getColor()+" plain tile.png");
                setSelectedFilename("images/" + getController().getColor() + " plain tile selected.png");
            }else{
                setFilename("images/plain tile.png");
                setSelectedFilename("images/plain tile selected.png");
            }
            setDefense(1.0);
            setIncome(1.0);
            setSteelProduction(1.0);
            setStrength(1.0);
            setUnrest(0.0);
            setWoodProduction(1.0);
        }else if(getLandType().equals("rocks")){
            if(isControlled()) {
                setFilename("images/"+getController().getColor()+" rock tile.png");
                setSelectedFilename("images/" + getController().getColor() + " rock tile selected.png");
            }else{
                setFilename("images/rock tile.png");
                setSelectedFilename("images/rock tile selected.png");
            }
            setDefense(2.0);
            setIncome(1.0);
            setSteelProduction(2.0);
            setStrength(1.0);
            setUnrest(0.0);
            setWoodProduction(0.0);
        }

        if(!getVisibility()){
            setFilename("images/unseen tile.png");
            setSelectedFilename("images/unseen tile.png");
        }
    }

}
