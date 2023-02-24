package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {
	public static final String objName = "Mana Crystal";
	GamePanel gp;
	
	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 1;
		down1 = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
		down3 = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
		image = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/manacrystal_blank",gp.tileSize,gp.tileSize);
	
	}
public boolean use(Entity entity) {
		
		if(entity.mana == entity.maxMana) {
			gp.ui.addMessage("Mana full.");
			return false;
		}
		else {
		gp.playSE(1);
		gp.ui.addMessage("Mana+" + value);
		entity.mana += value;
		return true;
		
	}

}
}
