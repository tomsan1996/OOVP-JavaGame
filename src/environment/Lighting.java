package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lighting {

	
	GamePanel gp;
	BufferedImage darknessFilter;
	public int dayCounter;
	public float filterAlpha = 0f;
	
	//Day state
	public final int day = 0;
	public final int dusk = 1;
	public final int night = 2;
	public final int dawn = 3;
	public int dayState = day;
	public int daySpent = 0;
	public Lighting(GamePanel gp) {
		
		this.gp = gp;
		setPlayerLightSource();
		
		
	}
	
	public void setPlayerLightSource() {
		// create a buffered image
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
		
		if(gp.player.currentLightSource == null) {
			g2.setColor(new Color(0,0,0,0.1f)); // can adjust filter color here
		} else {
			
			//get center x and y of the light circle
			int centerX = gp.player.screenX + gp.tileSize/2;
			int centerY = gp.player.screenY + gp.tileSize/2;

			
			//create a gradation effect within the light circle
			Color color[] = new Color[12];
			float fraction[] = new float[12];
			
			color[0] = new Color(0,0,0,0.1f);
			color[1] = new Color(0,0,0,0.22f);
			color[2] = new Color(0,0,0,0.42f);
			color[3] = new Color(0,0,0,0.52f);
			color[4] = new Color(0,0,0,0.62f);
			color[5] = new Color(0,0,0,0.68f);
			color[6] = new Color(0,0,0,0.72f);
			color[7] = new Color(0,0,0,0.76f);
			color[8] = new Color(0,0,0,0.85f);
			color[9] = new Color(0,0,0,0.90f);
			color[10] = new Color(0,0,0,0.99f);
			color[11] = new Color(0,0,0,0.1f);

			
			fraction[0]= 0.1f;
			fraction[1]= 0.25f;
			fraction[2]= 0.4f;
			fraction[3]= 0.55f;
			fraction[4]= 0.65f;
			fraction[5]= 0.7f;
			fraction[6]= 0.75f;
			fraction[7]= 0.8f;
			fraction[8]= 0.85f;
			fraction[9]= 0.9f;
			fraction[10]= 0.95f;
			fraction[11]= 1f;

		
			// create a gradation paint settings for the light circle
			RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLightSource.lightRadius, fraction, color);
			
			//set the gradient data on g2
			g2.setPaint(gPaint);
		}
		//Draw the screen rectangle without the light circle area
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.dispose();
		
	}
	
	public void resetDay(){
		dayState = day;
		filterAlpha = 0f;
		
	}
	public void update() {

		if(gp.player.lightUpdated == true) {
			setPlayerLightSource();
			gp.player.lightUpdated = false;
		}

		
		//check the state of the day (36000 = 3min?)
		if(dayState == day) {
			dayCounter++;
			if(dayCounter > 1200) {
				dayState = dusk;
				dayCounter = 0;
			}
		}
		if(dayState == dusk) {
			filterAlpha += 0.00002f;
			if(filterAlpha > 1f) {
				filterAlpha = 1f;
				dayState = night;
			}
		}
		if(dayState == night){
			dayCounter++;
			if(dayCounter > 360000) {
				dayState = dawn;
				dayCounter = 0;
				daySpent++;
			}
		}
		if(dayState == dawn) {
			filterAlpha -= 0.00002f;
			if(filterAlpha < 0f) {
				filterAlpha = 0f;
				dayState = day;
			}
		}
	}
	public void draw(Graphics2D g2){
		
		
		if(gp.currentArea == gp.outside) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
		}
		if(gp.currentArea == gp.outside || gp.currentArea == gp.dungeon) {
			g2.drawImage(darknessFilter, 0, 0, null);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
//		DEBUG
		String dayStatus = "";
		switch(dayState) {
		case day: dayStatus = "day"; break;
		case dusk: dayStatus = "dusk"; break;
		case night: dayStatus = "night"; break;
		case dawn: dayStatus = "dawn"; break;
		}
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(50f));
		g2.drawString(dayStatus, 800, 500);
		

	}
}
