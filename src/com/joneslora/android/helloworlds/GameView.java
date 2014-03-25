package com.joneslora.android.helloworlds;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
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
		
//		Paint paint = new Paint();
//		paint.setColor(Color.BLUE);
//		String path = "fonts/GillSans/GillSans.ttc";
//		Typeface englishTypeface = Typeface.createFromAsset(mContext.getAssets(), path);
//		paint.setTypeface(englishTypeface);
//		paint.setTextSize(25);
//		Words englishWord = new Words(this, "hello", paint);
//		words.add(englishWord);

		words.add(createWords(R.drawable.afrikaans_esperanto));
		words.add(createWords(R.drawable.albanian));
		words.add(createWords(R.drawable.arabic));
		words.add(createWords(R.drawable.azerbaijani));
		words.add(createWords(R.drawable.basque));
		words.add(createWords(R.drawable.belarusian));
		words.add(createWords(R.drawable.bosnian));
		words.add(createWords(R.drawable.bulgarian));
		words.add(createWords(R.drawable.catalan_spanish));
		words.add(createWords(R.drawable.chinese));
		words.add(createWords(R.drawable.croatian));
		words.add(createWords(R.drawable.czech_slovak));
		words.add(createWords(R.drawable.danish_finnish));
		words.add(createWords(R.drawable.dutch_german));
		words.add(createWords(R.drawable.english));
		words.add(createWords(R.drawable.estonian));
		words.add(createWords(R.drawable.filipino));
		words.add(createWords(R.drawable.french_maltese));
		words.add(createWords(R.drawable.galician));
		words.add(createWords(R.drawable.greek));
		words.add(createWords(R.drawable.haitiancreole_romanian));
		words.add(createWords(R.drawable.hebrew));
		words.add(createWords(R.drawable.hmong));
		words.add(createWords(R.drawable.hungarian));
		words.add(createWords(R.drawable.icelandic));
		words.add(createWords(R.drawable.igbo));
		words.add(createWords(R.drawable.indonesian));
		words.add(createWords(R.drawable.irish));
		words.add(createWords(R.drawable.italian));
		words.add(createWords(R.drawable.japanese));
		words.add(createWords(R.drawable.korean));
		words.add(createWords(R.drawable.latin));
		words.add(createWords(R.drawable.latvian_lithuanian));
		words.add(createWords(R.drawable.kaabo));
		words.add(createWords(R.drawable.macedonian_serbian));
		words.add(createWords(R.drawable.maori));
		words.add(createWords(R.drawable.mongolian));
		words.add(createWords(R.drawable.norwegian));
		words.add(createWords(R.drawable.polish));
		words.add(createWords(R.drawable.portuguese));
		words.add(createWords(R.drawable.russian));
		words.add(createWords(R.drawable.slovenian));
		words.add(createWords(R.drawable.swahili));
		words.add(createWords(R.drawable.swedish));
		words.add(createWords(R.drawable.turkish));
		words.add(createWords(R.drawable.ukrainian));
		words.add(createWords(R.drawable.vietnamese));
		words.add(createWords(R.drawable.welsh));
		words.add(createWords(R.drawable.yiddish));
		words.add(createWords(R.drawable.zulu));

	}
	private Words createWords(int resource) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
		return new Words(this,bmp);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		for (Words word : words) {
			word.onDraw(canvas);
		}
	}
}