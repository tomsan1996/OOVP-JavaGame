package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{

	public static final String npcName = "Dummy";
	
	public PlayerDummy(GamePanel gp) {
		super(gp);
		name = npcName;
		getImage();
	}
	public void getImage() {
		idleUp = setup ("/newPlayerSprite/idle/idleUp",gp.tileSize,gp.tileSize);
		idleDown = setup ("/newPlayerSprite/idle/idleDown",gp.tileSize,gp.tileSize);
		idleLeft = setup ("/newPlayerSprite/idle/idleLeft",gp.tileSize,gp.tileSize);
		idleRight = setup ("/newPlayerSprite/idle/idleRight",gp.tileSize,gp.tileSize);
		up1 = setup ("/newPlayerSprite/walk/wUp1",gp.tileSize,gp.tileSize);
		up2 = setup ("/newPlayerSprite/walk/wUp2",gp.tileSize,gp.tileSize);
		up3 = setup ("/newPlayerSprite/walk/wUp3",gp.tileSize,gp.tileSize);
		up4 = setup ("/newPlayerSprite/walk/wUp4",gp.tileSize,gp.tileSize);
		down1 = setup ("/newPlayerSprite/walk/wDown1",gp.tileSize,gp.tileSize);
		down2 = setup ("/newPlayerSprite/walk/wDown2",gp.tileSize,gp.tileSize);
		down3 = setup ("/newPlayerSprite/walk/wDown3",gp.tileSize,gp.tileSize);
		down4 = setup ("/newPlayerSprite/walk/wDown4",gp.tileSize,gp.tileSize);
		left1 = setup ("/newPlayerSprite/walk/wLeft1",gp.tileSize,gp.tileSize);
		left2 = setup ("/newPlayerSprite/walk/wLeft2",gp.tileSize,gp.tileSize);
		left3 = setup ("/newPlayerSprite/walk/wLeft3",gp.tileSize,gp.tileSize);
		left4 = setup ("/newPlayerSprite/walk/wLeft4",gp.tileSize,gp.tileSize);
		right1 = setup ("/newPlayerSprite/walk/wRight1",gp.tileSize,gp.tileSize);
		right2 = setup ("/newPlayerSprite/walk/wRight2",gp.tileSize,gp.tileSize);
		right3 = setup ("/newPlayerSprite/walk/wRight3",gp.tileSize,gp.tileSize);
		right4 = setup ("/newPlayerSprite/walk/wRight4",gp.tileSize,gp.tileSize);
	}
}
