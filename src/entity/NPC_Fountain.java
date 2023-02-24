package entity;




import main.GamePanel;


public class NPC_Fountain extends Entity {
	
	
	public NPC_Fountain(GamePanel gp) {
		super(gp);
		npcId = 5;
		getImage();
		setDialogue();
		update();

	}
	
	

	
	public void getImage() {
	down1 = setup ("/npc/newNPC/fountain/1",gp.tileSize,gp.tileSize);;
	down2 = setup ("/npc/newNPC/fountain/2",gp.tileSize,gp.tileSize);
	down3 = setup ("/npc/newNPC/fountain/3",gp.tileSize,gp.tileSize);
	down4 = setup ("/npc/newNPC/fountain/4",gp.tileSize,gp.tileSize);
	}

	public void setDialogue() {
	
	dialogues[0][0] = "The water is so refreshing!\nHP and MP has been recovered!";
	dialogues[0][1] = "You sense strange energy permeating!";
	dialogues[1][0] = "It's not available now.";

	}

 
	public void speak() {
	if(fountainUsable == false) {
		startDialogue(this,1);		
	} else {
		startDialogue(this,0);
		gp.ui.npc = this;
		healingPool(gp.dialogueState);
	}
	
	}


	public void healingPool (int gameState) {
	
	if(gp.keyH.enterPressed == true && fountainUsable == true) {
		gp.gameState = gameState;
		gp.player.attackCanceled = true;
		gp.playSE(30);
		gp.player.life = gp.player.maxLife;
		gp.player.mana = gp.player.maxMana;
		gp.aSetter.setMonster();
		fountainUsable = false;
		
	}

	}
}





