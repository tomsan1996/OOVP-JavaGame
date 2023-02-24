package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {

	public static final String objName = "Bronze Coin";
	GamePanel gp;
	public OBJ_Coin_Bronze(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 100;
		down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
		down3 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
	}
public boolean use(Entity entity) {
		
		
		gp.playSE(1);
		gp.ui.addMessage("Get "+value+" Coin.");
		gp.player.coin += value;
		return true;
	}

}
