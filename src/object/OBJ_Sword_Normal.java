package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

	public static final String objName = "Normal Sword";
	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		//Duration for atkspeed
		knockBackPower = 3;
		motion1_duration = 5;
		motion2_duration = 25;
//		durability = 100;
		down1 = setup("/objects/sword_normal",gp.tileSize,gp.tileSize);
		attackValue = 1;
		price = 20;
		attackArea.width =36;
		attackArea.height = 36;
		description = "["+name + "]\nAn old sword.";
	}
}
