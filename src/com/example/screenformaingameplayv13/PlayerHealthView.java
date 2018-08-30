package com.example.screenformaingameplayv13;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;

public class PlayerHealthView extends View {

	String characterName;
	int x1;
	int y1;
	int width;
	int bottom;
	private int y2;
	private int nameLocX;
	private int lifeBottomY;
	private int y3;
	private int rhythmBottomY;
	private int lifeBarWidth;
	private int rhythmBarWidth;

	public PlayerHealthView(Context context, String characterName) {
		super(context);
		this.characterName = characterName;
		x1 = 0;
		y1 = 20;
		y2 = y1 + 10;
		nameLocX = GameConstants.LIFE_BAR_WIDTH / 2;
		lifeBarWidth = GameConstants.LIFE_BAR_WIDTH;
		rhythmBarWidth = GameConstants.LIFE_BAR_WIDTH;
		lifeBottomY = y2 + GameConstants.LIFE_BAR_HEIGHT;
		
		y3 = lifeBottomY + 5;
		rhythmBottomY = y3 + GameConstants.RHYTHM_BAR_HEIGHT;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// name
		Paint p = new Paint();
		p.setTextSize(20);
		canvas.drawText(characterName, nameLocX, y1, p);
		
		// life
		p.setColor(Color.RED);
		canvas.drawRect(new Rect(x1, y2, lifeBarWidth, lifeBottomY), p);
		
		// combo
		p.setColor(Color.YELLOW);
		canvas.drawRect(new Rect(0, y3, 384, y3+10), p);
	}
	
	public void updateLife(int life) {
		lifeBarWidth = life;
		invalidate();
	}

}

