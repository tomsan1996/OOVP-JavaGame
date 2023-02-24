package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;


public class MON_Zombie extends Entity{
	
	GamePanel gp;
	
	public MON_Zombie(GamePanel gp) {
		super(gp);
		this.gp = gp;
		monsterId = 5;
		type = type_monster;
		name = "Zombie";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 15;
		life = maxLife;
		life= maxLife;
		attack = 5;
		defense = 2;
		exp = 10;

		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage() {
		up1 = setup ("/monster/zombie/u1",gp.tileSize,gp.tileSize);
		up2 = setup ("/monster/zombie/u2",gp.tileSize,gp.tileSize);
		up3 = setup ("/monster/zombie/u3",gp.tileSize,gp.tileSize);
		up4 = setup ("/monster/zombie/u2",gp.tileSize,gp.tileSize);
		down1 = setup ("/monster/zombie/d1",gp.tileSize,gp.tileSize);
		down2 = setup ("/monster/zombie/d2",gp.tileSize,gp.tileSize);
		down3 = setup ("/monster/zombie/d3",gp.tileSize,gp.tileSize);
		down4 = setup ("/monster/zombie/d2",gp.tileSize,gp.tileSize);
		left1 = setup ("/monster/zombie/l1",gp.tileSize,gp.tileSize);
		left2 = setup ("/monster/zombie/l2",gp.tileSize,gp.tileSize);
		left3 = setup ("/monster/zombie/l3",gp.tileSize,gp.tileSize);
		left4 = setup ("/monster/zombie/l2",gp.tileSize,gp.tileSize);
		right1 = setup ("/monster/zombie/r1",gp.tileSize,gp.tileSize);
		right2 = setup ("/monster/zombie/r2",gp.tileSize,gp.tileSize);
		right3 = setup ("/monster/zombie/r3",gp.tileSize,gp.tileSize);
		right4 = setup ("/monster/zombie/r2",gp.tileSize,gp.tileSize);
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
			checkStartAggro(gp.player, 5, 20);
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

}
