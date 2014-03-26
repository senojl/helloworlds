package com.joneslora.android.helloworlds;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private List<Words> words = new ArrayList<Words>();
	private Context mContext;

	public GameView(Context context) {
		super(context);
		mContext = context;
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					}catch (InterruptedException e){

					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				createWords();
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
			}
		});
	}
	private void createWords() {
		
		// GillSans-Semibold
		String path = "fonts/GillSans/GillSans-SemiBold.ttf"; // Path from root of project to typeface
		Typeface gillSansSemiBoldTypeface = Typeface.createFromAsset(mContext.getAssets(), path);
		// KufiStandardGK - Arabic
		path = "fonts/Arabic/KufiStandardGK.ttf";
		Typeface kufiTypeface = Typeface.createFromAsset(mContext.getAssets(), path);
		// - Korean
		
		// Make English
		words.add(createWords(gillSansSemiBoldTypeface, "hello"));
		
		// Make English
		words.add(createWords(gillSansSemiBoldTypeface, "hello"));
		
		// Make Latin
		words.add(createWords(gillSansSemiBoldTypeface, "salve"));

		// Make Arabic
		words.add(createWords(kufiTypeface, "مرحبا"));
		
	}
	
	private Words createWords(Typeface typeface, String wordStr) {
		Paint paint = new Paint();
		paint.setTextSize(48);
		paint.setTypeface(typeface);
		Random dice = new Random();
		paint.setARGB(255, dice.nextInt(256), dice.nextInt(256), dice.nextInt(256));
		
		Words word = new Words(this, wordStr, paint);
		return word;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		for (Words word : words) {
			word.onDraw(canvas);
		}
	}
	
	
}