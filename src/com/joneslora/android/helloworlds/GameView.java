package com.joneslora.android.helloworlds;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Scroller;

public class GameView extends SurfaceView {
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private List<Words> words = new ArrayList<Words>();
	private List<Words> prison = new ArrayList<Words>();
	private Context mContext;
	private Scroller mScroller;
	private GestureDetector mGestureDetector;
	
	public GameView(Context context) {
		super(context);
		mContext = context;
		gameLoopThread = new GameLoopThread(this);
		mGestureDetector = new GestureDetector((Activity) context, new MyGestureListener());
		mScroller = new Scroller(mContext);
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
				if(!gameLoopThread.isAlive())
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
		// Chinese
		path = "fonts/Chinese/ChinesePro.ttf";
		Typeface chineseTypeface = Typeface.createFromAsset(mContext.getAssets(), path);
		// Hebrew
		path = "fonts/Hebrew/NewPeninimMT.ttc";
		Typeface hebrewTypeface = Typeface.createFromAsset(mContext.getAssets(), path);
		// Japanese
		path = "fonts/Japanese/JapaneseStd.otf";
		Typeface japaneseTypeface = Typeface.createFromAsset(mContext.getAssets(), path);
		// Korean
		path = "fonts/Korean/KoreanChar.otf";
		Typeface koreanTypeface = Typeface.createFromAsset(mContext.getAssets(), path);
		// Hindi
		path = "fonts/Hindi/DevanagariMT.ttf";
		Typeface hindiTypeface = Typeface.createFromAsset(mContext.getAssets(), path);
		
		// Make Afrikaans
		words.add(createWords(gillSansSemiBoldTypeface, "hallo"));
		
		// Make Albanian
		words.add(createWords(gillSansSemiBoldTypeface, "përshëndetje"));
		
		// Make Azerbaijani
		words.add(createWords(gillSansSemiBoldTypeface, "salam"));
		
		// Make Basque
		words.add(createWords(gillSansSemiBoldTypeface, "kaixo"));
		
		// Make Belarusian
		words.add(createWords(gillSansSemiBoldTypeface, "прывітанне"));
		
		// Make Bengali
		words.add(createWords(hindiTypeface, "হ্যালো"));
		
		// Make Bosnian
		words.add(createWords(gillSansSemiBoldTypeface, "zdravo"));
		
		// Make Bulgarian
		words.add(createWords(gillSansSemiBoldTypeface, "здравей"));
		
		// Make Catalan
		words.add(createWords(gillSansSemiBoldTypeface, "hola"));
		
		// Make Chinese
		words.add(createWords(chineseTypeface, "你好"));
		
		// Make Croation
		words.add(createWords(gillSansSemiBoldTypeface, "bok"));
		
		// Make Czech
		words.add(createWords(gillSansSemiBoldTypeface, "ahoj"));
		
		// Make Danish
		words.add(createWords(gillSansSemiBoldTypeface, "Hej"));
		
		// Make Dutch
		words.add(createWords(gillSansSemiBoldTypeface, "hallo"));
		
		// Make Esperanto
		words.add(createWords(gillSansSemiBoldTypeface, "saluton"));
		
		// Make Estonian
		words.add(createWords(gillSansSemiBoldTypeface, "tere"));
		
		// Make English
		words.add(createWords(gillSansSemiBoldTypeface, "hello"));
		
		// Make Filipino
		words.add(createWords(gillSansSemiBoldTypeface, "kumusta"));
		
		// Make Finnish
		words.add(createWords(gillSansSemiBoldTypeface, "hei"));
		
		// Make French
		words.add(createWords(gillSansSemiBoldTypeface, "bonjour"));
		
		// Make Galician
		words.add(createWords(gillSansSemiBoldTypeface, "Ola"));
		
		// Make German
		words.add(createWords(gillSansSemiBoldTypeface, "hallo"));
		
		// Make Greek
		words.add(createWords(gillSansSemiBoldTypeface, "γειά σου"));
		
		// Make Haitian Creole
		words.add(createWords(gillSansSemiBoldTypeface, "alo"));
		
		// Make Hebrew
		words.add(createWords(hebrewTypeface, "שלום"));
		
		// Make Hindi
		words.add(createWords(hindiTypeface, "नमस्ते"));
		
		// Make Hmong
		words.add(createWords(gillSansSemiBoldTypeface, "nyob zoo"));
		
		// Make Hungarian
		words.add(createWords(gillSansSemiBoldTypeface, "helló"));
		
		// Make Icelandic
		words.add(createWords(gillSansSemiBoldTypeface, "halló"));
		
		// Make Igbo
		words.add(createWords(gillSansSemiBoldTypeface, "ndewo"));
		
		// Make Indonesian
		words.add(createWords(gillSansSemiBoldTypeface, "halo"));
		
		// Make Irish
		words.add(createWords(gillSansSemiBoldTypeface, "dia duit"));
		
		// Make Italian
		words.add(createWords(gillSansSemiBoldTypeface, "ciao"));
		
		// Make Japanese
		words.add(createWords(japaneseTypeface, "こんにちは"));
		
		// Make Korean
		words.add(createWords(koreanTypeface, "안녕하세요"));
		
		// Make Latin
		words.add(createWords(gillSansSemiBoldTypeface, "salve"));
		
		// Make Latvian
		words.add(createWords(gillSansSemiBoldTypeface, "sveiki"));
		
		// Make Macedonian
		words.add(createWords(gillSansSemiBoldTypeface, "здраво"));
		
		// Make Maori
		words.add(createWords(gillSansSemiBoldTypeface, "kia ora"));
		
		// Make Macedonian
		words.add(createWords(gillSansSemiBoldTypeface, "здраво"));
		
		// Make Mongolian
		words.add(createWords(gillSansSemiBoldTypeface, "Сайн байна"));
		
		// Make Nepali
		words.add(createWords(hindiTypeface, "नमस्ते"));
		
		// Make Persian
		words.add(createWords(kufiTypeface, "سلام"));
		
		// Make Polish
		words.add(createWords(gillSansSemiBoldTypeface, "cześć"));
		
		// Make Portuguese
		words.add(createWords(gillSansSemiBoldTypeface, "Olá"));

		// Make Romanian
		words.add(createWords(gillSansSemiBoldTypeface, "alo"));
		
		// Make Russian
		words.add(createWords(gillSansSemiBoldTypeface, "привет"));
		
		// Make Serbian
		words.add(createWords(gillSansSemiBoldTypeface, "здраво"));
		
		// Make Serbian
		words.add(createWords(gillSansSemiBoldTypeface, "здраво"));
		
		// Make Slovak
		words.add(createWords(gillSansSemiBoldTypeface, "ahoj"));
		
		// Make Slovenian
		words.add(createWords(gillSansSemiBoldTypeface, "Pozdravi"));
		
		// Make Spanish
		words.add(createWords(gillSansSemiBoldTypeface, "hola"));
		
		// Make Swahili
		words.add(createWords(gillSansSemiBoldTypeface, "hujambo"));
		
		// Make Swedish
		words.add(createWords(gillSansSemiBoldTypeface, "Hallå"));
		
		// Make Turkish
		words.add(createWords(gillSansSemiBoldTypeface, "merhaba"));
		
		// Make Ukrainian
		words.add(createWords(gillSansSemiBoldTypeface, "привіт"));
		
		// Make Ukrainian
		words.add(createWords(kufiTypeface, "خوش"));
		
		// Make Vietnamese
		words.add(createWords(gillSansSemiBoldTypeface, "chào"));
		
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
	public boolean onTouchEvent(MotionEvent event) {
		mGestureDetector.onTouchEvent(event);
		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		for (Words word : words) {
			word.onDraw(canvas);
		}
	}

	private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

		
		
		// Another possible step 2 - didn't move
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
    		int x = (int) e.getX();
    		int y = (int) e.getY();
    		for(Words word : words) {
    			if(word.isTouched(x, y)) {
    				// Do whatever happens when a word is touched
    				word.toggleAutoMove();    				
    			}
    		}
            return true;
        }
        
      
        
        // Step 1 - tap down
        @Override
        public boolean onDown(MotionEvent e) {
    		for(Words prisoner : words) {
    			if(prisoner.isTouched((int) e.getX(), (int) e.getY())) {
    				// Do whatever happens when a word is captured by a tap
    				prisoner.setAutoMove(false);
    				prison.add(prisoner);
    			}
    		}
        	return true;
        }
        
        // Step 2 - move
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    		for(Words prisoner : words) {
    			if(prisoner.isTouched((int) e2.getX(), (int) e2.getY())) {
    				// Do whatever happens when a word is scrolled through
    				if(!prison.contains(prisoner)) {
        				
    					prison.add(prisoner);
    				}
    			}
    		}
    		
    		for(Words prisoner : prison) {
    			int newPrisonerX = prisoner.getX() - (int) distanceX;
    			int newPrisonerY = prisoner.getY() - (int) distanceY;

    			Log.d("prisoner onScroll()", "trying newPrisonerX = " + newPrisonerX + ", newPrisonerY = " + newPrisonerY);
    			prisoner.setX((int)e2.getX());
    			prisoner.setY((int)e2.getY());
    		}
    		
        	return true;
        }
        
        private void pardonPrisoners() {
        	for(Words prisoner : prison) {
        		prisoner.setAutoMove(true);
        		prison.remove(prisoner);
        	}
        }
        
    

        // Step 3 - lifted finger and gives you first onDown event and event where you lifted finger
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
        float velocityY) {
            try {
            	int gameViewWidth = getWidth();
            	int gameViewHeight = getHeight();
            	
                float dx = (e1.getX() - e2.getX());
                float dy = (e1.getY() - e2.getY());

                float percentChangedX = -dx / gameViewWidth;
                float percentChangedY = -dy / gameViewHeight;
                
                float normalizedVelocityX = Words.MAX_SPEED * percentChangedX;
                float normalizedVelocityY = Words.MAX_SPEED * percentChangedY;
        		
        		Log.d("onFling()", "e1.x: " + e1.getX() + ", e1.y: " + e1.getY() + 
        				", e2.x: " + e2.getX() + ", e2.y: " + e2.getY() + 
        				", velocityX: " + velocityX + ", velocityY: " + velocityY);
        		
        		// update their new velocities
        		
        		// Let them be freeee!!
        		pardonPrisoners();
                
                return true;
            } catch (Exception e) {
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
       
    }
}