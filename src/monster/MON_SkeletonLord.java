package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_DoorIron;
import object.OBJ_Heart;
import object.OBJ_KeyIron;
import object.OBJ_ManaCrystal;


public class MON_SkeletonLord extends Entity {
	
	GamePanel gp;
	public static final String monName = "Skeleton Lord";
	public MON_SkeletonLord(GamePanel gp) {
		super(gp);
		this.gp = gp;
		boss = true;
		type = type_monster;
		monsterId = 6;
		name = monName;
		sleep = true;
		
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 300;
		life = maxLife;
		life = maxLife;
		attack = 10;
		defense = 4;
		exp = 1000;
		motion1_duration = 25;
		motion2_duration = 50;
		knockBackPower = 5;
		
		int size = gp.tileSize*5;
		
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48*2;
		solidArea.height = size - 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 150;
		attackArea.height = 150;
		
		getImage();
		getAttackImage();
		setDialogue();
	}
	public void getImage() {
		int i = 5;
		if(rageStatus == false) {
			up1 = setup ("/monster/skeleLord/skeletonlord_up_1",gp.tileSize*i,gp.tileSize*i);
			up2 = setup ("/monster/skeleLord/skeletonlord_up_2",gp.tileSize*i,gp.tileSize*i);
			up3 = setup ("/monster/skeleLord/skeletonlord_up_1",gp.tileSize*i,gp.tileSize*i);
			up4 = setup ("/monster/skeleLord/skeletonlord_up_2",gp.tileSize*i,gp.tileSize*i);
			down1 = setup ("/monster/skeleLord/skeletonlord_down_1",gp.tileSize*i,gp.tileSize*i);
			down2 = setup ("/monster/skeleLord/skeletonlord_down_2",gp.tileSize*i,gp.tileSize*i);
			down3 = setup ("/monster/skeleLord/skeletonlord_down_1",gp.tileSize*i,gp.tileSize*i);
			down4 = setup ("/monster/skeleLord/skeletonlord_down_2",gp.tileSize*i,gp.tileSize*i);
			left1 = setup ("/monster/skeleLord/skeletonlord_left_1",gp.tileSize*i,gp.tileSize*i);
			left2 = setup ("/monster/skeleLord/skeletonlord_left_2",gp.tileSize*i,gp.tileSize*i);
			left3 = setup ("/monster/skeleLord/skeletonlord_left_1",gp.tileSize*i,gp.tileSize*i);
			left4 = setup ("/monster/skeleLord/skeletonlord_left_2",gp.tileSize*i,gp.tileSize*i);
			right1 = setup ("/monster/skeleLord/skeletonlord_right_1",gp.tileSize*i,gp.tileSize*i);
			right2 = setup ("/monster/skeleLord/skeletonlord_right_2",gp.tileSize*i,gp.tileSize*i);
			right3 = setup ("/monster/skeleLord/skeletonlord_right_1",gp.tileSize*i,gp.tileSize*i);
			right4 = setup ("/monster/skeleLord/skeletonlord_right_2",gp.tileSize*i,gp.tileSize*i);
		}
		if(rageStatus == true) {
			up1 = setup ("/monster/skeleLord/skeletonlord_phase2_up_1",gp.tileSize*i,gp.tileSize*i);
			up2 = setup ("/monster/skeleLord/skeletonlord_phase2_up_2",gp.tileSize*i,gp.tileSize*i);
			up3 = setup ("/monster/skeleLord/skeletonlord_phase2_up_1",gp.tileSize*i,gp.tileSize*i);
			up4 = setup ("/monster/skeleLord/skeletonlord_phase2_up_2",gp.tileSize*i,gp.tileSize*i);
			down1 = setup ("/monster/skeleLord/skeletonlord_phase2_down_1",gp.tileSize*i,gp.tileSize*i);
			down2 = setup ("/monster/skeleLord/skeletonlord_phase2_down_2",gp.tileSize*i,gp.tileSize*i);
			down3 = setup ("/monster/skeleLord/skeletonlord_phase2_down_1",gp.tileSize*i,gp.tileSize*i);
			down4 = setup ("/monster/skeleLord/skeletonlord_phase2_down_2",gp.tileSize*i,gp.tileSize*i);
			left1 = setup ("/monster/skeleLord/skeletonlord_phase2_left_1",gp.tileSize*i,gp.tileSize*i);
			left2 = setup ("/monster/skeleLord/skeletonlord_phase2_left_2",gp.tileSize*i,gp.tileSize*i);
			left3 = setup ("/monster/skeleLord/skeletonlord_phase2_left_1",gp.tileSize*i,gp.tileSize*i);
			left4 = setup ("/monster/skeleLord/skeletonlord_phase2_left_2",gp.tileSize*i,gp.tileSize*i);
			right1 = setup ("/monster/skeleLord/skeletonlord_phase2_right_1",gp.tileSize*i,gp.tileSize*i);
			right2 = setup ("/monster/skeleLord/skeletonlord_phase2_right_2",gp.tileSize*i,gp.tileSize*i);
			right3 = setup ("/monster/skeleLord/skeletonlord_phase2_right_1",gp.tileSize*i,gp.tileSize*i);
			right4 = setup ("/monster/skeleLord/skeletonlord_phase2_right_2",gp.tileSize*i,gp.tileSize*i);
		}
		

		}
	public void getAttackImage() {
		int i = 5;
		if(rageStatus == false) {
			attackUp1 = setup ("/monster/skelelord/skeletonlord_phase2_attack_up_1",gp.tileSize*i,gp.tileSize*i*2);
			attackUp2 = setup ("/monster/skelelord/skeletonlord_phase2_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
			attackUp3 = setup ("/monster/skelelord/skeletonlord_phase2_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
			attackDown1 = setup ("/monster/skelelord/skeletonlord_phase2_attack_down_1",gp.tileSize*i,gp.tileSize*i*2);
			attackDown2 = setup ("/monster/skelelord/skeletonlord_phase2_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
			attackDown3 = setup ("/monster/skelelord/skeletonlord_phase2_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
			attackLeft1 = setup ("/monster/skelelord/skeletonlord_phase2_attack_left_1",gp.tileSize*i*2,gp.tileSize*i);
			attackLeft2 = setup ("/monster/skelelord/skeletonlord_phase2_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
			attackLeft3 = setup ("/monster/skelelord/skeletonlord_phase2_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
			attackRight1 = setup ("/monster/skelelord/skeletonlord_phase2_attack_right_1",gp.tileSize*i*2,gp.tileSize*i);
			attackRight2 = setup ("/monster/skelelord/skeletonlord_phase2_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
			attackRight3 = setup ("/monster/skelelord/skeletonlord_phase2_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
		}
		if(rageStatus == true) {
			attackUp1 = setup ("/monster/skelelord/skeletonlord_phase2_attack_up_1",gp.tileSize*i,gp.tileSize*i*2);
			attackUp2 = setup ("/monster/skelelord/skeletonlord_phase2_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
			attackUp3 = setup ("/monster/skelelord/skeletonlord_phase2_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
			attackDown1 = setup ("/monster/skelelord/skeletonlord_phase2_attack_down_1",gp.tileSize*i,gp.tileSize*i*2);
			attackDown2 = setup ("/monster/skelelord/skeletonlord_phase2_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
			attackDown3 = setup ("/monster/skelelord/skeletonlord_phase2_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
			attackLeft1 = setup ("/monster/skelelord/skeletonlord_phase2_attack_left_1",gp.tileSize*i*2,gp.tileSize*i);
			attackLeft2 = setup ("/monster/skelelord/skeletonlord_phase2_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
			attackLeft3 = setup ("/monster/skelelord/skeletonlord_phase2_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
			attackRight1 = setup ("/monster/skelelord/skeletonlord_phase2_attack_right_1",gp.tileSize*i*2,gp.tileSize*i);
			attackRight2 = setup ("/monster/skelelord/skeletonlord_phase2_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
			attackRight3 = setup ("/monster/skelelord/skeletonlord_phase2_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
		}

	}

	
	public void setDialogue() {
		dialogues[0][0] = "Thou dare to interrupt my slumber!!";
		dialogues[0][1] = "I will make you suffer!!!";
		dialogues[0][2] = "Feel the music of my kind!!!";
	}
	public void setAction () {
		
		
		if(rageStatus == false && life < maxLife/3) {
			rageStatus = true;
			getImage();
			getAttackImage();
			defaultSpeed += 2;
			speed = defaultSpeed;
			attack += 5;
			defense += 8;
		}
		
		
		if(getTileDistance(gp.player) < 10) {
			moveTowardPlayer(60);
		}
		
		
		if(onPath == true) {


		} else {

			// get random direction if it's not on path.
			getRandomDirection();
			}	
		
		//Check if it attack
		if(attacking == false) {
			checkAttack(60, gp.tileSize*6, gp.tileSize*5);
		}
	}

	public void damageReaction() {
		
		actionLockCounter = 0;

	}
	public void checkDrop() {
		
		gp.bossBattleOn = false;
		Progress.skeletonLordDefeated = true;
		
		//restore previous music
		gp.stopMusic();
		gp.playMusic(59);
		
		//remove iron door
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_DoorIron.objName)) {
				gp.playSE(51);
				gp.obj[gp.currentMap][i] = null;
			}

		}
		dropItem(new OBJ_KeyIron(gp));
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


