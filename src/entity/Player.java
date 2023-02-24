package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Axe;
import object.OBJ_Fireball;
import object.OBJ_KeyIron;
import object.OBJ_KeySkull;
import object.OBJ_KeyWooden;
import object.OBJ_Lantern;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import tile.Tile;

public class Player extends Entity{
	
	

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
	
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(8, 16, 24, 24);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		//attackArea.width = 36;
		//attackArea.height = 36;
		
		setDefaultValues();

	}
	
	public void setDefaultValues() {
		
//		worldX = gp.tileSize*17;
//		worldY = gp.tileSize*39;
		// def overworld map debug ALSO STARTING VALUE
		worldX = gp.tileSize*33;
		worldY = gp.tileSize*41;

		direction = "down";
		defaultSpeed = 4;
		speed = defaultSpeed;
		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = 6;
		maxMana = 4;
		mana = maxMana;
		ammo = 10;
		strength = 2;
		dexterity = 6;
		exp = 0;
		nextLevelExp = 5;
		coin = 500;
		currentLightSource = null;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Fireball(gp);
		//projectile = new OBJ_Rock(gp);
		attack = getAttack();
		defense = getDefense();
		
		getImage();
		getAttackImage();
		getGuardImage();
		setItems();
		setDialogue();
	}
	
	
	public void setDefaultPositions() {
		// SET SPAWN LOCATION WHEN RESPAWN
//		setSpawnPoint(gp.currentMap);
//		gp.currentMap = 0;
		if(gp.currentMap == 0) {
			gp.currentArea = gp.outside;
			worldX = gp.tileSize*39;
			worldY = gp.tileSize*29;
		}
		if(gp.currentMap == 3) {
			gp.currentArea = gp.outside;
			worldX = gp.tileSize*25;
			worldY = gp.tileSize*29;
		}
		if(gp.currentMap == 5) {
			gp.currentArea = gp.dungeon;
			worldX = gp.tileSize*29;
			worldY = gp.tileSize*12;
		}
		if(gp.currentMap == 6) {
			gp.currentArea = gp.dungeon;
			worldX = gp.tileSize*3;
			worldY = gp.tileSize*26;
		}

		direction = "down";
	}
	public void restoreStatus () {
		
		life = maxLife;
		mana = maxMana;
		invincible = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
		speed = defaultSpeed;
	}
	public void setItems() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Potion_Red(gp));
	}
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		motion1_duration = currentWeapon.motion1_duration;
		motion2_duration = currentWeapon.motion2_duration;
		return attack = strength + currentWeapon.attackValue;
	}
	
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for (int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for (int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}

	public int getDefense () {
	
	return defense = dexterity * currentShield.defenseValue;
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
	
	public void getAttackImage() {
		if(currentWeapon.type == type_sword) {
		attackUp1 = setup ("/newPlayerSprite/atk/Sword/atkUp1",gp.tileSize,gp.tileSize*2);
		attackUp2 = setup ("/newPlayerSprite/atk/Sword/atkUp2",gp.tileSize,gp.tileSize*2);
		attackUp3 = setup ("/newPlayerSprite/atk/Sword/atkUp2",gp.tileSize,gp.tileSize*2);
		attackDown1 = setup ("/newPlayerSprite/atk/Sword/atkDown1",gp.tileSize,gp.tileSize*2);
		attackDown2 = setup ("/newPlayerSprite/atk/Sword/atkDown2",gp.tileSize,gp.tileSize*2);
		attackDown3 = setup ("/newPlayerSprite/atk/Sword/atkDown2",gp.tileSize,gp.tileSize*2);
		attackLeft1 = setup ("/newPlayerSprite/atk/Sword/atkLeft1",gp.tileSize*2,gp.tileSize);
		attackLeft2 = setup ("/newPlayerSprite/atk/Sword/atkLeft2",gp.tileSize*2,gp.tileSize);
		attackLeft3 = setup ("/newPlayerSprite/atk/Sword/atkLeft2",gp.tileSize*2,gp.tileSize);
		attackRight1 = setup ("/newPlayerSprite/atk/Sword/atkRight1",gp.tileSize*2,gp.tileSize);
		attackRight2 = setup ("/newPlayerSprite/atk/Sword/atkRight2",gp.tileSize*2,gp.tileSize);
		attackRight3 = setup ("/newPlayerSprite/atk/Sword/atkRight2",gp.tileSize*2,gp.tileSize);
		}
		if(currentWeapon.type == type_pickaxe) {
			attackUp1 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeUp1",gp.tileSize,gp.tileSize*2);
			attackUp2 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeUp2",gp.tileSize,gp.tileSize*2);
			attackUp3 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeUp2",gp.tileSize,gp.tileSize*2);
			attackDown1 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeDown1",gp.tileSize,gp.tileSize*2);
			attackDown2 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeDown2",gp.tileSize,gp.tileSize*2);
			attackDown3 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeDown2",gp.tileSize,gp.tileSize*2);
			attackLeft1 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeLeft1",gp.tileSize*2,gp.tileSize);
			attackLeft2 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeLeft2",gp.tileSize*2,gp.tileSize);
			attackLeft3 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeLeft2",gp.tileSize*2,gp.tileSize);
			attackRight1 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeRight1",gp.tileSize*2,gp.tileSize);
			attackRight2 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeRight2",gp.tileSize*2,gp.tileSize);
			attackRight3 = setup ("/newPlayerSprite/atk/pickAxe/pickAxeRight2",gp.tileSize*2,gp.tileSize);
			}
		if(currentWeapon.type == type_axe) {
			attackUp1 = setup ("/newPlayerSprite/atk/Axe/AxeUp1",gp.tileSize,gp.tileSize*2);
			attackUp2 = setup ("/newPlayerSprite/atk/Axe/AxeUp2",gp.tileSize,gp.tileSize*2);
			attackUp3 = setup ("/newPlayerSprite/atk/Axe/AxeUp2",gp.tileSize,gp.tileSize*2);
			attackDown1 = setup ("/newPlayerSprite/atk/Axe/AxeDown1",gp.tileSize,gp.tileSize*2);
			attackDown2 = setup ("/newPlayerSprite/atk/Axe/AxeDown2",gp.tileSize,gp.tileSize*2);
			attackDown3 = setup ("/newPlayerSprite/atk/Axe/AxeDown2",gp.tileSize,gp.tileSize*2);
			attackLeft1 = setup ("/newPlayerSprite/atk/Axe/AxeLeft1",gp.tileSize*2,gp.tileSize);
			attackLeft2 = setup ("/newPlayerSprite/atk/Axe/AxeLeft2",gp.tileSize*2,gp.tileSize);
			attackLeft3 = setup ("/newPlayerSprite/atk/Axe/AxeLeft2",gp.tileSize*2,gp.tileSize);
			attackRight1 = setup ("/newPlayerSprite/atk/Axe/AxeRight1",gp.tileSize*2,gp.tileSize);
			attackRight2 = setup ("/newPlayerSprite/atk/Axe/AxeRight2",gp.tileSize*2,gp.tileSize);
			attackRight3 = setup ("/newPlayerSprite/atk/Axe/AxeRight2",gp.tileSize*2,gp.tileSize);
			}
	}
	
	public void getGuardImage() {
		guardUp = setup ("/newPlayerSprite/guard/u",gp.tileSize,gp.tileSize);
		guardDown = setup ("/newPlayerSprite/guard/d",gp.tileSize,gp.tileSize);
		guardLeft = setup ("/newPlayerSprite/guard/l",gp.tileSize,gp.tileSize);
		guardRight = setup ("/newPlayerSprite/guard/r",gp.tileSize,gp.tileSize);
	}
	public void getSleepImage(BufferedImage image) {
		idleUp = image;
		idleDown = image;
		idleLeft = image;
		idleRight = image;
		up1 = image;
		up2 = image;
		up3 = image;
		up4 = image;
		down1 = image;
		down2 = image;
		down3 = image;
		down4 = image;
		left1 = image;
		left2 = image;
		left3 = image;
		left4 = image;
		right1 = image;
		right2 = image;
		right3 = image;
		right4 = image;
		
	}
	public void update() {
		
			if(knockBack == true) {
				//CHECK TILE COLLISION
				collisionOn = false;
				gp.cChecker.checkTile(this);
				
				//CHECK OBJECT COLLISION
				gp.cChecker.checkObject(this,true);
				//CHECK NPC COLLISION
				gp.cChecker.checkEntity(this,gp.npc);
				//CHECK MONSTER COLLISION
				gp.cChecker.checkEntity(this,gp.monster);
				//CHECK INTERACTIVE TILE COLLISION
				gp.cChecker.checkEntity(this, gp.iTile);
				
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
			}
			else if(keyH.spacePressed == true) {
				guarding = true;
				guardCounter++;
			}
		//DIRECTION FROM KEY INPUT
			else if(keyH.upPressed == true ||
					keyH.downPressed == true ||
					keyH.leftPressed == true ||
					keyH.rightPressed == true ||
					keyH.enterPressed == true) {
						
			if(keyH.upPressed == true) {direction ="up";}
			else if(keyH.downPressed == true) {direction ="down";}
			else if(keyH.leftPressed == true) {direction ="left";}
			else if(keyH.rightPressed == true) {direction ="right";}
			
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this,true);
			pickUpObject(objIndex);
			
			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
			interactNPC(npcIndex);
			
			
			//CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
			contactMonster(monsterIndex);
			
			//CHECK INTERACTIVE TILE COLLISION
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			// CHECK EVENT
			gp.eHandler.checkEvent();
			
		
			
			// IF COLLISION FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.enterPressed == false) {
				
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;					
				}
				
			}
			
			if(keyH.enterPressed == true && attackCanceled == false) {
				//attack SFX and animation
				attacking = true;
				spriteCounter = 0;
				gp.playSE(11);
				
				//DECREASE DURABILITY
//				currentWeapon.durability--;
			}	
			
			attackCanceled = false;
			gp.keyH.enterPressed = false;
			guarding = false;
			guardCounter = 0;
			
			//walk animation
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					gp.playSE(3);
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					gp.playSE(4);
					spriteNum = 4;
				}
				else if(spriteNum == 4) {
					
					spriteNum = 1;
				}
				spriteCounter = 0;
				}
			
		
		} else {
			guarding = false;
			guardCounter = 0;
		}
			if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30
					&& shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
				
				//SET DEFAULT COORDINATES, DIRECTION AND USER
				projectile.set(worldX, worldY,direction, true, this);
				
				// SUBTRACT THE COST (MANA, AMMO ETC.)
				projectile.subtractResource(this);

				// check vacancy
				for (int i = 0; i < gp.projectile[i].length; i++) {
					if(gp.projectile[gp.currentMap][i]== null) {
						gp.projectile[gp.currentMap][i] = projectile;
						break;
					}
				}
				
				shotAvailableCounter = 0;
				gp.playSE(14);
			}
		
		//This needs to be outside of key if statement!
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailableCounter <30) {
			shotAvailableCounter++;
		}
		if(life > maxLife) {
			life= maxLife;
		}
		if(mana > maxMana) {
			life= maxMana;
		}
		if(keyH.godModeOn == false) {
			if(life <= 0) {
				gp.gameState = gp.gameOverState;
				gp.ui.commandNum = -1;
				gp.stopMusic();
				gp.playSE(16);
			}
		}

		
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
			if(drawing == true ) {
				g2.drawImage(image,tempScreenX, tempScreenY,null);
			}
			
			// reset alpha for transparency	
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
	}
	
	public void interactNPC (int i) {
		
			if(i != 999) { 
				if(gp.keyH.enterPressed == true) {
				attackCanceled = true;
				gp.npc[gp.currentMap][i].speak();
			}
			gp.npc[gp.currentMap][i].move(direction);
		}
		
	}

	
	public void contactMonster(int i) {
		
		if(i != 999) {
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				
				gp.playSE(9);
				
				int damage =  gp.monster[gp.currentMap][i].attack - defense;
				 if(damage < 1) {
					 damage = 1;
				 }
				life -= damage;
				transparent = true;
				invincible = true;
				
			}
			
		}
	}
	
	public void damageMonster (int i, Entity attacker, int attack, int knockBackPower) {
		
		if(i != 999) {
			
		if(gp.monster[gp.currentMap][i].invincible == false) {
			
			 gp.playSE(10);
			 if(knockBackPower > 0) {
				 setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower); 
			 }
			 if(gp.monster[gp.currentMap][i].offBalance == true) {
				 attack *= 3;
			 }
			 
			 
			 int damage = attack - gp.monster[gp.currentMap][i].defense;
			 if(damage < 0) {
				 damage = 0;
			 }
			gp.monster[gp.currentMap][i].life -= damage;
			gp.ui.addMessage(damage + " damage!");
			
			gp.monster[gp.currentMap][i].invincible = true;
			gp.monster[gp.currentMap][i].damageReaction();
			
			if(gp.monster[gp.currentMap][i].life <= 0) {
				gp.monster[gp.currentMap][i].dying = true;
				gp.ui.addMessage( "killed the " + gp.monster[gp.currentMap][i].name + "!");
				gp.ui.addMessage( "Exp + " + gp.monster[gp.currentMap][i].exp );
				exp += gp.monster[gp.currentMap][i].exp;
				checkLevelUp();
			}
		}
		}
	}
	
	
	//PROJECTILE PARRY
	public void damageProjectile(int i)	{
		if(i != 999) {
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}

	public void damageInteractiveTile(int i) {
		
		if(i != 999 && 
				gp.iTile[gp.currentMap][i].destructible == true &&
				gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && 
				gp.iTile[gp.currentMap][i].invincible == false) {
				
				gp.iTile[gp.currentMap][i].playSE();
				gp.iTile[gp.currentMap][i].life--;
				gp.iTile[gp.currentMap][i].invincible = true;
				
				// GENERATE particle
				generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
				
				if(gp.iTile[gp.currentMap][i].life == 0) {
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
				gp.iTile[gp.currentMap][i].playSE();
				}
		}
	}
	public void checkLevelUp() {
		
		if(exp >= nextLevelExp) {
			if(level <= 5) {
				strength+=2;
				dexterity++;
				maxLife++;
				life++;
			}
			level ++;
			exp = exp - nextLevelExp;
			nextLevelExp = 2 * level + nextLevelExp;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			setDialogue();
			startDialogue(this,0);
		}
	}
	

	public void pickUpObject(int i) {
		if(i != 999) {

//			PICKUP ONLY ITEM
			if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			// OBSTACLE
			else if(gp.obj[gp.currentMap][i].type == type_obstacle){
				if(keyH.enterPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
						
				}
			} else {
				//INVENTORY ITEMS
				String text;
				
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {
					
					gp.playSE(26);
					text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
					gp.obj[gp.currentMap][i] = null;
				}
				else {
					text = "You cannot carry any more!";			
					}
			
				gp.ui.addMessage(text);
				
				}
		}
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You are level " + level + " now!\n" + "You feel stronger!";
	}
	
	

	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_pickaxe) {
				
				currentWeapon = selectedItem;
				attack = getAttack();
				getAttackImage();
			}
			if(selectedItem.type == type_shield) {
				
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_boots) {
				
				speed += selectedItem.speedValue;
				inventory.remove(itemIndex);
			}
			if(selectedItem.type == type_light) {
				if(currentLightSource == selectedItem) {
					currentLightSource = null;
				} else {
					currentLightSource = selectedItem;
					lightUpdated = true;
				}
			}
			if(selectedItem.type == type_consumable) {
				if(selectedItem.use(this) == true) {
					if(selectedItem.amount > 1) {
						selectedItem.amount--;
					} else {
						inventory.remove(itemIndex);
					}
				
				}
			}
		}
	}
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		for(int i = 0 ; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		Entity newItem = gp.eGenerator.getObject(item.name);
		
		// CHECK IF ITEM IS STACKABLE
		if(newItem.stackable == true) {
			int index = searchItemInInventory(newItem.name);
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			} else {
				// NEW ITEM , need to check empty slot
				if(inventory.size() != maxInventorySize) {
					inventory.add(newItem);
					canObtain = true;
				}
			}
		} else {
			// NOT STACKABLE, check empty slot.
			if(inventory.size() != maxInventorySize) {
				inventory.add(newItem);
				canObtain = true;
			}
		}
		return canObtain;
		
	}
	

	
	
}
	
	


















