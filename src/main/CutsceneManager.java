package main;

import java.awt.Graphics2D;

import entity.PlayerDummy;
import monster.MON_SkeletonLord;
import object.OBJ_DoorIron;

public class CutsceneManager {
	
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	
	//scene number
	public final int NA = 0;
	public final int skeletonLord = 1;
	
	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		
		switch(sceneNum) {
		case skeletonLord: scene_skeletonLord(); break;
		}
	}
	public void scene_skeletonLord() {
		
		if(scenePhase == 0) {
			gp.bossBattleOn = true;
			
			//Shut the iron door
			for(int i = 0; i < gp.obj[1].length; i++) {
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_DoorIron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*9;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*23;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(50);
					break;
				}
			}
			// search a vacant slot for dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null){
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			gp.player.worldX += 2;
			if(gp.player.worldX > gp.tileSize*24){
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			
			// Search the boss
			for(int i = 0; i < gp.monster[1].length; i++){
				if(gp.monster[gp.currentMap][i] != null &&
					gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName){
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 3) {
			// boss Speaks
			gp.ui.drawDialogueScreen();
			gp.stopMusic();
		}
		if(scenePhase == 4) {		
			// return to player	
			//search the dummy in npc array
			
			for (int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
					// restore player position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					//delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			// Start Drawing the player again
			gp.player.drawing = true;
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			gp.playMusic(67);
		
		}
		
	}

}
