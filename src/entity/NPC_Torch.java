package entity;



import main.GamePanel;


public class NPC_Torch extends Entity {
	
	public boolean lightUpdated = false;

	public NPC_Torch(GamePanel gp) {
		super(gp);
		npcId = 4;
		type = type_light;
		lightRadius = 220;
		currentLightSource = this;
		getImage();
	}
	
	

	
public void getImage() {

	up1 = setup ("/npc/newNPC/Torch/1",gp.tileSize,gp.tileSize);
	up2 = setup ("/npc/newNPC/Torch/2",gp.tileSize,gp.tileSize);
	up3 = setup ("/npc/newNPC/Torch/3",gp.tileSize,gp.tileSize);
	up4 = setup ("/npc/newNPC/Torch/4",gp.tileSize,gp.tileSize);
	down1 = setup ("/npc/newNPC/Torch/1",gp.tileSize,gp.tileSize);;
	down2 = setup ("/npc/newNPC/Torch/2",gp.tileSize,gp.tileSize);
	down3 = setup ("/npc/newNPC/Torch/3",gp.tileSize,gp.tileSize);
	down4 = setup ("/npc/newNPC/Torch/4",gp.tileSize,gp.tileSize);
	left1 = setup ("/npc/newNPC/Torch/1",gp.tileSize,gp.tileSize);
	left2 = setup ("/npc/newNPC/Torch/2",gp.tileSize,gp.tileSize);
	left3 = setup ("/npc/newNPC/Torch/3",gp.tileSize,gp.tileSize);
	left4 = setup ("/npc/newNPC/Torch/4",gp.tileSize,gp.tileSize);
	right1 = setup ("/npc/newNPC/Torch/1",gp.tileSize,gp.tileSize);
	right2 = setup ("/npc/newNPC/Torch/2",gp.tileSize,gp.tileSize);
	right3 = setup ("/npc/newNPC/Torch/3",gp.tileSize,gp.tileSize);
	right4 = setup ("/npc/newNPC/Torch/4",gp.tileSize,gp.tileSize);
	}



	 

public void speak() {
	

}

}





