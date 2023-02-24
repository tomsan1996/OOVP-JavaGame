package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{
	
	public static final String objName = "Lantern";
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = objName;
		down1 = setup("/objects/petromak", gp.tileSize, gp.tileSize);
		description = "[Petromak]\nForeign device used\nto light surroundings";
		price = 200;
		lightRadius = 220;
	}

}
