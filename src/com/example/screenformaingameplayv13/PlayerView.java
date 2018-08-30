package com.example.screenformaingameplayv13;

import com.example.gameplaywithbluetoothnetworking13.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PlayerView extends View {
	Paint p;
	Bitmap image;
	int facingDirection;
	private LayoutParams params;
	private int leftMargin;

	public PlayerView(Context context) {
		super(context);
		p = new Paint();
		//image = null;
		image = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle1);
		facingDirection = -1;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    //MUST CALL THIS
	    setMeasuredDimension(300, 200);
	}	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (image != null) {
			if (facingDirection == GameConstants.FACING_RIGHT) {
				canvas.drawBitmap(image, 100, 0, p);
			}
			else { // facing left
				canvas.drawBitmap(image, -30, 0, p);
			}
		}
	}
	
	public void updateImage(Bitmap image, int facingDirection) {
		this.image = image;
		this.facingDirection = facingDirection;
		invalidate();
	}
	
	// I made this method because Android 2.3 and below
	// doesn't support View.setX/Y() methods.
	public void setParams(int w, int h) {
		params = new RelativeLayout.LayoutParams(w, h);
	}
	
	public LayoutParams getParams() {
		return params;
	}
}
