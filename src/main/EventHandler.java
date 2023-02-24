package main;

import java.awt.Rectangle;

import data.Progress;
import entity.Entity;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect [][][];
	Entity eventMaster;

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	public int tempMap;
	int tempCol;
	int tempRow;
	

	public EventHandler (GamePanel gp) {
		this.gp = gp;
		eventMaster = new Entity(gp);
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol] [gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
		eventRect[map][col][row]= new EventRect();
		eventRect[map][col][row].x = 8;
		eventRect[map][col][row].y= 8;
		eventRect[map][col][row].width= 40;
		eventRect[map][col][row].height = 40;
		eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
		eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
		
		col++;
		if(col == gp.maxWorldCol) {
			col = 0;
			row ++;
		}
		if(row == gp.maxWorldRow) {
			row = 0;
			map ++;
		}
		}
		setDialogue();
		
	}
	
	public void setDialogue() {
		
		eventMaster.dialogues[0][0] = "You fall into a pit!";
		eventMaster.dialogues[1][0] = "The water is so refreshing!\nYour HP has been recovered!";
		eventMaster.dialogues[1][1] = "You feel strange energy\npermeating the island!";
		
	}
	
	public void checkEvent() {
		
		// CHECK IF THE PLAYER CHARACTER IS MORE THAN I TILE AWAY FROM THE LAST EVENT
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		
		if (distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			
			
			//DAMAGE PIT
			if(hit(0,44,44, "any") == true) {damagePit(gp.dialogueState);}
			
			
			// HEAL POOL
//			else if(hit(3,11,24, "up") == true) {healingPool(gp.dialogueState);}
			
			// TELEPORTATION FROM MAP TO MAP
			
			// teleport from overworld_01 to interior_01.
			else if(hit(0,16,32, "up") == true) 	{teleport(1,16,30,gp.indoor);} 
			// teleport to overworld_01  from interior_01.
			else if(hit(1,16,31, "down") == true) 	{teleport(0,16,33,gp.outside);} 
			// teleport from overworld_01(bot_entrance) to dungeon_01.(bot_entrance)
			else if(hit(0,18,16, "down") == true) 	{teleport(2,18,18,gp.dungeon);}
			// teleport to overworld_01(bot_entrance)  from dungeon_01.(bot_entrance)
			else if(hit(2,18,17, "up") == true) 	{teleport(0,18,15,gp.outside);}
			// teleport to overworld_01(top_exit)  from dungeon_01.(top_exit)
			else if(hit(2,16,31, "down") == true) 	{teleport(0,17,25,gp.outside);} 
			// teleport from overworld_01(top_exit)  to dungeon_01.(top_exit)
			else if(hit(0,17,24, "up") == true) 	{teleport(2,16,30,gp.dungeon);} 
			
			// teleport between overworld_01 and overworld 02
			else if(hit(0,44,28, "right") == true) 	{teleport(3,6,28,gp.outside);}
			else if(hit(0,44,29, "right") == true) 	{teleport(3,6,29,gp.outside);}
			else if(hit(3,5,28, "left") == true) 	{teleport(0,43,28,gp.outside);}
			else if(hit(3,5,29, "left") == true) 	{teleport(0,43,29,gp.outside);}
			
			// teleport between overworld_02 and dungeon 02 1F
			else if(hit(3,29,14, "up") == true) 	{teleport(5,29,14,gp.dungeon);}
			else if(hit(5,29,15, "down") == true) 	{teleport(3,29,15,gp.outside);}
			
			
			//teleport between dungeon 02 and dungeon 02 2F
			else if(hit(5,17,19, "up") == true) 	{teleport(6,3,27,gp.dungeon);}
			else if(hit(6,3,28, "down") == true) 	{teleport(5,17,20,gp.dungeon);}
			
			//teleport between dungeon 02 2F and overworld 02
			else if(hit(6,43,44, "down") == true) 	{teleport(3,37,35,gp.outside);}
			else if(hit(3,37,34, "up") == true) 	{teleport(6,43,43,gp.dungeon);}
			
			//SKELETON LORD CUTSCENE
			else if(hit(6,11,23, "any") == true) 	{skeletonLord();}
			
			//TALKING WITH NPC
//			else if(hit(1,12,9, "up") == true) {speak(gp.npc[1][0]);}

		
		}
		
		
	}
	public boolean hit (int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if(map == gp.currentMap) {
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
		eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
		
		if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false ) {
			if(gp.player.direction.contentEquals(reqDirection)  || reqDirection.contentEquals("any")) {
				hit = true;
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
		eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		return hit;
	}
	
	public void teleport (int map, int col, int row, int area) {
		
		gp.currentMap = tempMap;
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		
		//gp.currentMap = map;
		//gp.player.worldX = gp.tileSize * col;
		//gp.player.worldY = gp.tileSize * row;
		//previousEventX = gp.player.worldX;
		//previousEventY = gp.player.worldY;
		canTouchEvent = false;
		
	}
	public void damagePit (int gameState) {
		
		gp.gameState = gameState;
		gp.playSE(6);
		eventMaster.startDialogue(eventMaster, 0);
		gp.player.life -= 1;
		canTouchEvent = false;
	}
	
	public void healingPool (int gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(30);
			eventMaster.startDialogue(eventMaster, 1);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();
			
		}
		

	}
	
	public void speak(Entity entity) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
	
	public void skeletonLord() {
		if(gp.bossBattleOn == false && Progress.skeletonLordDefeated == false) {
			gp.gameState = gp.cutSceneState;
			gp.csManager.sceneNum = gp.csManager.skeletonLord;
		}
	}

	
}
