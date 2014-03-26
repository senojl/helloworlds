package com.joneslora.android.helloworlds;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class Words {
	private static final int MAX_SPEED = 25;
	private GameView gameView;
	private Bitmap bmp;
	private int x = 0;
	private int y = 0;
	private int xSpeed;
	private int ySpeed;
	private int width;
	private int height;

	private String text;
	private Paint paint;
	
	public Words(GameView gameView, Bitmap bmp) {
		this.width = bmp.getWidth();  
		this.height = bmp.getHeight();
		this.gameView=gameView;
		this.bmp=bmp;

		Random rnd = new Random();
		x = rnd.nextInt(gameView.getWidth() - width);
		y = rnd.nextInt(gameView.getHeight() - height);
		xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
		ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
	}

	public Words(GameView gameView, String text, Paint paint) {
		this.width = (int) paint.measureText(text);  
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		this.height = bounds.height();
		this.gameView = gameView;
		this.text = text;
		this.paint = paint;
		
		Random rnd = new Random();
		x = rnd.nextInt(gameView.getWidth() - width);
		y = rnd.nextInt(gameView.getHeight() - height);
		xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
		ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
	}

	private void update() {
		if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
			xSpeed = -xSpeed;
		}
		x = x + xSpeed;
		if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
			ySpeed = -ySpeed;
		}
		y = y + ySpeed;
	}

	public void onDraw(Canvas canvas) {
		update();
//		canvas.drawBitmap(bmp, x , y, null);
		canvas.drawText(text, x, y, paint);
	}
}  
