package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;



public class Entity {
	
	public boolean debug = false;
	GamePanel gp;
	public int speed;
	
	public BufferedImage up1, up2, up3, up4,
						 down1, down2, down3, down4,
						 left1, left2, left3, left4,
						 right1, right2, right3, right4,
						 idleUp, idleDown, idleLeft, idleRight;
	public BufferedImage attackUp1,attackUp2,attackUp3, 
						 attackDown1, attackDown2, attackDown3,
						 attackLeft1,attackLeft2,attackLeft3,
						 attackRight1,attackRight2,attackRight3,
						 guardUp, guardDown, guardLeft, guardRight;
						 
	public BufferedImage image, image2, image3;
	
	public Rectangle solidArea = new Rectangle(0,0,32,32);
	public Rectangle attackArea = new Rectangle (0,0,0,0);
	public int solidAreaDefaultX = 0, solidAreaDefaultY= 0;
	public String dialogues[][] = new String[50][50];
	public Entity attacker;
	public Entity linkedEntity;
	public boolean temp = false;
	
	//STATE
	public int worldX, worldY;
	public boolean collision = false;
	public String direction = "down";
	public int spriteNum = 1;
	public int dialogueIndex = 0;
	public int dialogueSet = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public String knockBackDirection;
	public boolean guarding = false;
	public boolean transparent = false;
	public boolean offBalance = false;
	public boolean rageStatus = false;
	public boolean sleep = false;
	public boolean drawing = true;
	
	//STATE FOR ENV/OBJECTS/ITILE
	public Entity loot;
	public boolean opened = false;
	public boolean isUnlocked = false;
	public boolean isDestroyed = false;
	public boolean fountainUsable = true;
	
	//COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	int dyingCounter = 0;
	public int hpBarCounter = 0;
	int knockBackCounter = 0;
	public int guardCounter = 0;
	int offBalanceCounter = 0;
	public int fountainCooldown = 0;
	
	
	
	//ENTITY ATTRIBUTES
	public String name;
	public int npcId;
	public int monsterId;
	public int defaultSpeed;
	public int maxLife;
	public int life;
	public int level;
	public int maxMana;
	public int mana;
	public int ammo;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int motion1_duration;
	public int motion2_duration;
	public Entity currentWeapon;
	public Entity currentShield;
	public Entity currentLightSource;
	public Projectile projectile;
	public boolean boss;
//	public int durability;
	
	// ITEM ATTRIBUTES
	public ArrayList<Entity> inventory = new ArrayList<>();	
	public final int maxInventorySize = 20;
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int price;
	public int knockBackPower = 0;
	public boolean stackable = false;
	public int amount = 1;
	public int lightRadius;
	public int speedValue;
	
	//TYPE
	public int type; // 0= player, 1 = NPC, 2 =monster
	public final int type_player 		= 0;
	public final int type_npc 			= 1;
	public final int type_monster 		= 2;
	public final int type_sword 		= 3;
	public final int type_axe 			= 4;
	public final int type_pickaxe 		= 5;
	public final int type_shield 		= 6;
	public final int type_consumable 	= 7;
	public final int type_pickupOnly 	= 8;
	public final int type_obstacle 		= 9;
	public final int type_light 		= 10;
	public final int type_boots			= 11;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public int getLeftX() {
		return worldX + solidArea.x;
	}
	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}
	public int getTopY() {
		return worldY + solidArea.y;
	}
	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}
	public int getCol() {
		return (worldX + solidArea.x)/gp.tileSize;
	}
	public int getRow() {
		return (worldY + solidArea.y)/gp.tileSize;
	}
	

	public int getTileDistance(Entity target) {
		int tileDistance = (getXdistance(target)/gp.tileSize + getYdistance(target)/gp.tileSize);
		return tileDistance;
	}
	public int getGoalCol (Entity target) {
		int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
		return goalCol;
	}
	public int getGoalRow (Entity target) {
		int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
		return goalRow;
	}
	
	public int getCenterX() {
		int centerX = worldX + left1.getWidth()/2;
		return centerX;
	}
	
	public int getCenterY() {
		int centerY = worldY + up1.getHeight()/2;
		return centerY;
	}
	
	public int getXdistance(Entity target) {
		int xDistance = Math.abs(getCenterX() - target.getCenterX());
		return xDistance;
	}
	public int getYdistance(Entity target) {
		int yDistance = Math.abs(getCenterY() - target.getCenterY());
		return yDistance;
	}
	public int getScreenX() {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		return screenX;
	}
	public int getScreenY() {
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		return screenY;
	}
	
	
	
	public void resetCounter() {
		spriteCounter = 0;
		actionLockCounter = 0;
		invincibleCounter = 0;
		shotAvailableCounter = 0;
		dyingCounter = 0;
		hpBarCounter = 0;
		knockBackCounter = 0;
		guardCounter = 0;
		offBalanceCounter = 0;
		fountainCooldown = 0;
	}
	
	
	public void setLoot(Entity loot) {
		
	}
	
	public void setAction () {
		
		
		
	}
	public void damageReaction() {
		
	}
	
	public void speak () {


	}
	
	public void facePlayer() {
		switch(gp.player.direction) {
		case "up":direction = "down";break;
		case "down":direction = "up";break;
		case "left":direction = "right";break;
		case "right":direction = "left";break;
			
		}
	}
	
	public void move(String direction) {
		
	}
	
	public void startDialogue(Entity entity, int setNum) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.npc = entity;
		dialogueSet = setNum;
		
	}
	
	public void interact() {
		
	}
	public boolean use (Entity entity) {
		return false;
		
	}
	public void checkDrop() {
		
	}
	public void dropItem(Entity droppedItem) {
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			}
		}
	}
	public Color getParticleColor() {
		Color color = null;
		return color;
	}
	public int getParticleSize() {
		int size = 0;
		return size;
		
	}
	public int getParticleSpeed() {
		int speed = 0;
		return speed;
		
	}
	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}
	public void generateParticle(Entity generator, Entity target) {
		
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -1, 1);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);
	}
	
	public void checkCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this,gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == type_monster && contactPlayer == true) {
			damagePlayer(attack);
		}
	}
	
	public void update () {
		// Fountain Cooldown
		if(fountainUsable == false) {
			fountainCooldown++;
			if(fountainCooldown > 10000) {
				fountainUsable = true;
				fountainCooldown = 0;
			}
		}
		if(sleep == false) {
			if(knockBack == true) {
				checkCollision();
				if(collisionOn == true) {
					knockBackCounter = 0;
					knockBack = false;
					speed = defaultSpeed;
				}
				else if(collisionOn == false) {
					switch(knockBackDirection) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;	
					}
				}
				knockBackCounter++;
				if(knockBackCounter == 10) {
					knockBackCounter = 0;
					knockBack = false;
					speed = defaultSpeed;
				}
			}
			else if(attacking == true) {
				attacking();
				
			} else {
				setAction();
				checkCollision();
				// IF COLLISION FALSE, PLAYER CAN MOVE
				if(collisionOn == false) {
					switch(direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;					
					}
				}
				
				spriteCounter++;
				if(spriteCounter > 10) {
					if	   (spriteNum == 1) {spriteNum = 2;}
					else if(spriteNum == 2) {spriteNum = 3;}
					else if(spriteNum == 3) {spriteNum = 4;}
					else if(spriteNum == 4) {spriteNum = 1;}
					spriteCounter = 0;
					}	
				
			}
			if(invincible == true) {
				invincibleCounter++;
				if(invincibleCounter > 30) {
					invincible = false;
					transparent = false;
					invincibleCounter = 0;
				}
			}
			if(shotAvailableCounter <30) {
				shotAvailableCounter++;
			}
			if(offBalance == true) {
				offBalanceCounter++;
				if(offBalanceCounter > 60) {
					offBalance = false;
					offBalanceCounter = 0;
				}
			}
		}
		
		

		
	}
	
	public String getOppositeDirection(String direction) {
		String oppositeDirection = "";
		switch(direction) {
		case "up" : oppositeDirection = "down";	break;
		case "down" : oppositeDirection = "up";	break;
		case "left" : oppositeDirection = "right";	break;
		case "right" : oppositeDirection = "left";	break;
		}
		return oppositeDirection;
	}
	
	public void attacking() {
		
		spriteCounter++;
		if(spriteCounter <= motion1_duration) {
			spriteNum = 1;
		}
		if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
			spriteNum = 2;
			//Save the current worldX, worldY, solidArea
			
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case "up" : worldY -= attackArea.height; break;
			case "down" : worldY += attackArea.height; break;
			case "left" : worldX -= attackArea.width; break;
			case "right" : worldX += attackArea.width; break;
			}
			//ArrackArea become solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			if(type == type_monster) {
				if(gp.cChecker.checkPlayer(this) == true) {
					damagePlayer(attack);
				}
			} else { //FOR PLAYER
				//check monster collision with the updated worldX, worldYand solidArea
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
				
				int ItileIndex = gp.cChecker.checkEntity(this, gp.iTile);
				gp.player.damageInteractiveTile(ItileIndex);
				
				int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
				gp.player.damageProjectile(projectileIndex);
			}

			//After checking collision, restore the original data
			worldX =  currentWorldX;
			worldY =  currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCounter > motion2_duration) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
		
	public void damagePlayer(int attack) {
		
		if(gp.player.invincible == false) {
			int damage = attack - gp.player.defense;
			//get an opposite direction of this attacker
			String canGuardDirection = getOppositeDirection(direction);
			if(gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)) {
				
				//PARRY
				if(gp.player.guardCounter < 10) {
					damage = 0;
					gp.playSE(27);
					setKnockBack(this, gp.player, knockBackPower/3);
					offBalance = true;
					spriteCounter =- 60;
				} else {
					//player guarding for reduced damage
					damage /= 3;
					gp.playSE(43);
				}
			} else {
				//player not guarding
				gp.playSE(6);
				 if(damage < 1) {
					 damage = 1;
				 }
			}
			if(damage != 0) {
				gp.player.transparent = true;
				setKnockBack(gp.player, this, knockBackPower);
			}
			gp.player.life -= damage;
			gp.player.invincible = true;
		}
	}
	
	public void checkProjectileLife(int rate, int shotInterval) {
		int i = new Random().nextInt(rate);
		if(i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) { 
			 projectile.set(worldX, worldY, direction, true, this);
			 //CHECK VACANCY
			 for(int ii = 0; ii < gp.projectile[1].length; ii++) {
				 if(gp.projectile[gp.currentMap][ii] == null) {
					 gp.projectile[gp.currentMap][ii] = projectile;
					 break;
				 }
			 }
			 
			 shotAvailableCounter = 0;
		}
	}
	public void setKnockBack(Entity target, Entity attacker,int knockBackPower) {
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		target.speed += knockBackPower;
		target.knockBack = true;
	}
	
	public void checkAttack(int rate, int v, int h) {
		
		boolean targetInRange = false;
		int xDis = getXdistance(gp.player);
		int yDis = getYdistance(gp.player);
		switch(direction){
		case "up" :
			if(gp.player.getCenterY() < getCenterY() && yDis < v && xDis < h) {
				targetInRange = true;
			}
			break;
		case "down" :
			if(gp.player.getCenterY() > getCenterY() && yDis < v && xDis < h) {
				targetInRange = true;
			}
			break;
		case "left" :
			if(gp.player.getCenterX() < getCenterX() && xDis < v && yDis < h) {
				targetInRange = true;
			}
			break;
		case "right" :
			if(gp.player.getCenterX() > getCenterX() && xDis < v && yDis < h) {
				targetInRange = true;
			}
			break;
		}
		
		if(targetInRange == true) {
			// Check if it initiates attack or not
			int i = new Random().nextInt(rate);
			if(i == 0) {
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
				shotAvailableCounter = 0;
			}
		}
		
	}
	public void checkStartAggro(Entity target, int distance, int rate) {
		if(getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if(i == 0){
				onPath = true;
			}
		}
	}
	public void checkEndAggro(Entity target, int distance, int rate) {
		if(getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if(i == 0){
				onPath = false;
			}
		}
	}
	
	public void getRandomDirection() {
		actionLockCounter ++;
		 
		if(actionLockCounter > 120) {
				Random random = new Random();
				//pick up random number
				int i = random.nextInt(100)+1;
				if (i <= 25) {direction = "up";}
				if (i > 25 && i <= 50) {direction = "down";}
				if (i > 50 && i <= 75) {direction = "left";}
				if (i > 75 && i <= 100) {direction = "right";}
				actionLockCounter = 0;
			}
	}
	
	public void moveTowardPlayer(int interval) {
		
		actionLockCounter++;
		if(actionLockCounter > interval) {
			if(getXdistance(gp.player) > getYdistance(gp.player)) {
				if(gp.player.getCenterX() < getCenterX()) {
					direction = "left";
				} else {
					direction = "right";
				}
			}
			else if(getXdistance(gp.player)< getYdistance(gp.player)) {
				if(gp.player.getCenterY() < getCenterY()) {
					direction = "up";
				} else {
					direction = "down";
				}
			}
			actionLockCounter = 0;
		}
		
	}
	
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i) {changeAlpha(g2,0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2,1f);}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2,0f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2,1f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2,0f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2,1f);}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2,0f);}
		if(dyingCounter > i *7&& dyingCounter <= i*8) {changeAlpha(g2,1f);}
		if(dyingCounter > i*8)
		{
			alive = false;
			}
	}
	
	public void changeAlpha (Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image =  ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			image = uTool.ScaleImage(image, width,height);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return image;
		
	}
	

	//AI PATH FINDING
	public void searchPath(int goalCol, int goalRow) { 
		
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/gp.tileSize; 
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		if(gp.pFinder.search() == true) {
			// next WorldX & worldY
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			//Entity current solid area position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			}
			else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				//left or right
				if(enLeftX > nextX) {
					direction ="left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				// either up or left
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY > nextY && enLeftX < nextX) {
				// either up or right
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
					
				}
			}
			else if(enTopY < nextY && enLeftX > nextX) {
				// either down or left
				direction  = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY < nextY && enLeftX < nextX) {
				// either down or right
				direction  = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			// if goal is reached, stop the search.
			// IF FOLLOW PLAYER, COMMENT OUT THESE LINE
			int nextCol = gp.pFinder.pathList.get(0).col;
			int nextRow = gp.pFinder.pathList.get(0).row;
			if(nextCol == goalCol && nextRow == goalRow) {
				onPath = false;
			}
			
		}
	}

	public int getDetected(Entity user, Entity target[][], String targetName) {
		int index = 999;
		
		//check surrounding object;
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch(user.direction) {
		case "up": nextWorldY = user.getTopY()-1; break;
		case "down":nextWorldY = user.getBottomY()+1; break;
		case "left": nextWorldX = user.getLeftX()-1; break;
		case "right": nextWorldX = user.getRightX()+1; break;

		}
		
		int col = nextWorldX/gp.tileSize;
		int row = nextWorldY/gp.tileSize;
		
		for (int i = 0;i < target[1].length;i++) {
			if(target[gp.currentMap][i] != null) {
				if(target[gp.currentMap][i].getCol() == col &&
						target[gp.currentMap][i].getRow() == row &&
						target[gp.currentMap][i].name.equals(targetName)) {
					
					index = i;
					break;
					
				}
			}
		}
		return index;
		
	}
	
	public boolean inCamera() {
		boolean inCamera = false;
		if(worldX + gp.tileSize*5 > gp.player.worldX - gp.player.screenX && 
				   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				   worldY + gp.tileSize*5 > gp.player.worldY - gp.player.screenY &&
				   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			inCamera = true;
		}
		return inCamera;
	}
	
	
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		
		if(inCamera() == true) {
			int tempScreenX =  getScreenX();
			int tempScreenY = getScreenY();
			
			switch(direction) {
			case "up":
				if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				if(spriteNum == 4) {image = up4;}	
				}
				if(attacking == true) {
					tempScreenY = getScreenY() - up1.getHeight();
					if(spriteNum == 1) {image = attackUp1;}
					if(spriteNum == 2) {image = attackUp2;}
					if(spriteNum == 3) {image = attackUp3;}
				}
				if(guarding == true	) {image = guardUp;}
				break;
			case "down":
				if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				if(spriteNum == 4) {image = down4;}
				}
				if(attacking == true) {
					
					if(spriteNum == 1) {image = attackDown1;}
					if(spriteNum == 2) {image = attackDown2;}
					if(spriteNum == 3) {image = attackDown3;}
				}
				if(guarding == true	) {image = guardDown;}
				break;
			case "left":
				
				if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				}
				if(attacking == true) {
					tempScreenX = getScreenX() - left1.getWidth();
					if(spriteNum == 1) {image = attackLeft1;}
					if(spriteNum == 2) {image = attackLeft2;}
					if(spriteNum == 3) {image = attackLeft3;}
				}
				if(guarding == true	) {image = guardLeft;}
				break;
			case "right":
				if(attacking == false) {
					if(spriteNum == 1) {image = right1;}
					if(spriteNum == 2) {image = right2;}
					if(spriteNum == 3) {image = right3;}
					if(spriteNum == 4) {image = right4;}
				}
				if(attacking == true) {
					if(spriteNum == 1) {image = attackRight1;}
					if(spriteNum == 2) {image = attackRight2;}
					if(spriteNum == 3) {image = attackRight3;}
					
				}
				if(guarding == true	) {image = guardRight;}
				break;
			}

			

			
			if(invincible == true) {
				hpBarOn = true;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}		
			if(dying == true) {
				dyingAnimation(g2);
			}		
			g2.drawImage(image,tempScreenX, tempScreenY,null);
			// reset alpha for transparency	
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
	}


	public void playSFX() {
		
	}

}






















