package object;

import entity.Entity;
//import entity.Player;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
	
	
	public static final String objName = "Red Potion";
	GamePanel gp;
	
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		name = objName;
		stackable = true;
		value = 4;
		down1 = setup("/objects/potion_red", gp.tileSize,gp.tileSize);
		down3 = setup("/objects/potion_red", gp.tileSize,gp.tileSize);
		description = "[Red Potion]\nHeal your HP by " + value + " points.";
		price = 20;
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "you drink the "+name+ "!\n" + "you recovered " + value + " HP.";
	}
	public boolean use(Entity entity) {
		startDialogue(this, 0);
		entity.life += value;
		gp.playSE(2);
		return true;
	}

	
	
}
