package com.joneslora.android.helloworlds;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class Words {
	public static final int MAX_SPEED = 25;
	private GameView gameView;
	private Bitmap bmp;
	private int x = 0;
	private int y = 0;
	private int xSpeed;
	private int ySpeed;
	private int naturalXSpeed, naturalYSpeed;
	private int width;
	private int height;

	private String text;
	private Paint paint;
	
	private boolean autoMove = true;
	
	private Rect clickedRect;
	
	public Words(GameView gameView, Bitmap bmp) {
		this.width = bmp.getWidth();  
		this.height = bmp.getHeight();
		this.gameView=gameView;
		this.bmp=bmp;

		x = randomX();
		y = randomY();
		xSpeed = randomSpeed();
		ySpeed = randomSpeed();
		naturalXSpeed = xSpeed;
		naturalYSpeed = ySpeed;
	}

	public Words(GameView gameView, String text, Paint paint) {
		this.width = (int) paint.measureText(text);  
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		this.height = bounds.height();
		this.gameView = gameView;
		this.text = text;
		this.paint = paint;
		
		x = randomX();
		y = randomY();
		xSpeed = randomSpeed();
		ySpeed = randomSpeed();
	}
	
	private int randomSpeed() {
		Random rnd = new Random();
		return rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
	}
	
	private int randomX() {
		Random rnd = new Random();
		return rnd.nextInt(gameView.getWidth() - width);
	}
	
	private int randomY() {
		Random rnd = new Random();
		return rnd.nextInt(gameView.getHeight() - height);
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getLeft() {
		return this.x;
	}
	
	public int getTop() {
		return this.y - this.height;
	}
	
	public int getRight() {
		return this.x + this.width;
	}
	
	public int getBottom() {
		return this.y;
	}
	
	public void setXSpeed(int newXSpeed) {
		this.xSpeed = newXSpeed;
	}
	
	public void setYSpeed(int newYSpeed) {
		this.ySpeed = newYSpeed;
	}
	
	public void setX(int x) {
		if(x < 0) {
			this.x = 0;
		}
		else if(x > gameView.getWidth()-width) {
			this.x = gameView.getWidth()-width;
		}
		else {
			this.x = x;
		}
		Log.d("prisoner setX", "actual x = " + x);
	}
	
	public void setY(int y) {
		if(y < 0) {
			this.y = 0;
		}
		else if(y > gameView.getHeight()-height) {
			this.y = gameView.getHeight()-height;
		}
		else {
			this.y = y;
		}
		Log.d("prisoner setY", "actual y = " + y);
	}
	
	public boolean isTouched(int x, int y) {
		int left = getLeft()-30, right = getRight()+30, top = getTop()-30, bottom = getBottom()+30;
		clickedRect = new Rect(left, top, right, bottom);
		if(left <= x && x <= right) {
			if(top <= y && y <= bottom) {
				return true;
			}
		}
		
		clickedRect = null;
		return false;
	}
	
	public void setAutoMove(boolean autoMove) {
		this.autoMove = autoMove;
	}
	
	public void toggleAutoMove() {
		if(autoMove)
			autoMove = false;
		else
			autoMove = true;
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
		if(autoMove) {
			update();
			canvas.drawText(text, x, y, paint);
		}
		else {
			canvas.drawText(text, x, y, paint);
//			if(clickedRect!=null) {
//				Paint clickPaint = new Paint();
//				clickPaint.setARGB(200, 255, 0, 0);
//				canvas.drawRect(clickedRect, clickPaint);
//				clickedRect = null;
			}
		}
	}
  
