package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_BlueSlime extends Entity{
	
	GamePanel gp;
	
	public MON_BlueSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_monster;
		monsterId = 1;
		name = "Blue Slime";
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 5;
		life = maxLife;
		life = maxLife;
		attack = 3;
		defense = 1;
		exp = 4;

		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		playSFX();
	}
	public void getImage() {
		up1 = setup ("/monster/blueSlime/1",gp.tileSize,gp.tileSize);
		up2 = setup ("/monster/blueSlime/2",gp.tileSize,gp.tileSize);
		up3 = setup ("/monster/blueSlime/3",gp.tileSize,gp.tileSize);
		up4 = setup ("/monster/blueSlime/4",gp.tileSize,gp.tileSize);
		down1 = setup ("/monster/blueSlime/1",gp.tileSize,gp.tileSize);
		down2 = setup ("/monster/blueSlime/2",gp.tileSize,gp.tileSize);
		down3 = setup ("/monster/blueSlime/3",gp.tileSize,gp.tileSize);
		down4 = setup ("/monster/blueSlime/4",gp.tileSize,gp.tileSize);
		left1 = setup ("/monster/blueSlime/1",gp.tileSize,gp.tileSize);
		left2 = setup ("/monster/blueSlime/2",gp.tileSize,gp.tileSize);
		left3 = setup ("/monster/blueSlime/3",gp.tileSize,gp.tileSize);
		left4 = setup ("/monster/blueSlime/4",gp.tileSize,gp.tileSize);
		right1 = setup ("/monster/blueSlime/1",gp.tileSize,gp.tileSize);
		right2 = setup ("/monster/blueSlime/2",gp.tileSize,gp.tileSize);
		right3 = setup ("/monster/blueSlime/3",gp.tileSize,gp.tileSize);
		right4 = setup ("/monster/blueSlime/4",gp.tileSize,gp.tileSize);
		}
	

	public void setAction () {
		if(onPath == true) {
			
			// Check if it stop chasing
			checkEndAggro(gp.player, 10, 100);
			// Search the direction to go			
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
			// check if it shoot a projectile
			// REMOVE IF MONSTER CANT SHOOT PROJECTILE
			// checkProjectileLife(200, 30);
			
			} else {
			//check if it's start chasing
			// monster will aggro when player is near slime with 30% chance.
			checkStartAggro(gp.player, 4, 50);
			// get random direction if it's not on path.
			getRandomDirection();
			}		
		}

	public void damageReaction() {
		
		actionLockCounter = 0;
		onPath = true;
	}
	public void checkDrop() {
		
		//CAST A DIE
		int i = new Random().nextInt(100)+1;
		//SET THE MONSTER DROP
		if(i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if(i >= 50 && i <75) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i >= 75 && i <100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
	}

	public void playSFX() {
		if(this.spriteNum == 3) {
			gp.playSE(65);
		}
		if(this.life <= 0) {
			gp.playSE(64);
		}
	}

}
