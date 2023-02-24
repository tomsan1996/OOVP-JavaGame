package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_Orc extends Entity {
	
	GamePanel gp;
	
	public MON_Orc(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_monster;
		monsterId = 4;
		name = "Orc";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 20;
		life = maxLife;
		life= maxLife;
		attack = 8;
		defense = 4;
		exp = 15;
		motion1_duration = 40;
		motion2_duration = 85;
		knockBackPower = 5;
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 48;
		attackArea.height = 48;
		
		getImage();
		getAttackImage();
	}
	public void getImage() {
		up1 = setup ("/monster/orc/walk/u1",gp.tileSize,gp.tileSize);
		up2 = setup ("/monster/orc/walk/u2",gp.tileSize,gp.tileSize);
		up3 = setup ("/monster/orc/walk/u1",gp.tileSize,gp.tileSize);
		up4 = setup ("/monster/orc/walk/u2",gp.tileSize,gp.tileSize);
		down1 = setup ("/monster/orc/walk/d1",gp.tileSize,gp.tileSize);
		down2 = setup ("/monster/orc/walk/d2",gp.tileSize,gp.tileSize);
		down3 = setup ("/monster/orc/walk/d1",gp.tileSize,gp.tileSize);
		down4 = setup ("/monster/orc/walk/d2",gp.tileSize,gp.tileSize);
		left1 = setup ("/monster/orc/walk/l1",gp.tileSize,gp.tileSize);
		left2 = setup ("/monster/orc/walk/l2",gp.tileSize,gp.tileSize);
		left3 = setup ("/monster/orc/walk/l1",gp.tileSize,gp.tileSize);
		left4 = setup ("/monster/orc/walk/l2",gp.tileSize,gp.tileSize);
		right1 = setup ("/monster/orc/walk/r1",gp.tileSize,gp.tileSize);
		right2 = setup ("/monster/orc/walk/r2",gp.tileSize,gp.tileSize);
		right3 = setup ("/monster/orc/walk/r1",gp.tileSize,gp.tileSize);
		right4 = setup ("/monster/orc/walk/r2",gp.tileSize,gp.tileSize);
		}
	public void getAttackImage() {
		
		attackUp1 = setup ("/monster/orc/atk/u1",gp.tileSize,gp.tileSize*2);
		attackUp2 = setup ("/monster/orc/atk/u2",gp.tileSize,gp.tileSize*2);
		attackUp3 = setup ("/monster/orc/atk/u2",gp.tileSize,gp.tileSize*2);
		attackDown1 = setup ("/monster/orc/atk/d1",gp.tileSize,gp.tileSize*2);
		attackDown2 = setup ("/monster/orc/atk/d2",gp.tileSize,gp.tileSize*2);
		attackDown3 = setup ("/monster/orc/atk/d2",gp.tileSize,gp.tileSize*2);
		attackLeft1 = setup ("/monster/orc/atk/l1",gp.tileSize*2,gp.tileSize);
		attackLeft2 = setup ("/monster/orc/atk/l2",gp.tileSize*2,gp.tileSize);
		attackLeft3 = setup ("/monster/orc/atk/l2",gp.tileSize*2,gp.tileSize);
		attackRight1 = setup ("/monster/orc/atk/r1",gp.tileSize*2,gp.tileSize);
		attackRight2 = setup ("/monster/orc/atk/r2",gp.tileSize*2,gp.tileSize);
		attackRight3 = setup ("/monster/orc/atk/r2",gp.tileSize*2,gp.tileSize);
	}

	public void setAction () {
		if(onPath == true) {
			// Check if it stop chasing
			checkEndAggro(gp.player, 10, 100);
			// Search the direction to go			
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

			} else {
			//check if it's start chasing
			// monster will aggro when player is near slime with 30% chance.
			checkStartAggro(gp.player, 5, 100);
			// get random direction if it's not on path.
			getRandomDirection();
			}	
		
		//Check if it attack
		if(attacking == false) {
			checkAttack(30, gp.tileSize*4, gp.tileSize);
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

}


