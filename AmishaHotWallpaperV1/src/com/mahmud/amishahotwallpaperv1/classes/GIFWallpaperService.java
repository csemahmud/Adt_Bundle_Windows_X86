/**
 * 
 */
package com.mahmud.amishahotwallpaperv1.classes;

import java.io.IOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
@SuppressLint("NewApi")
public class GIFWallpaperService extends WallpaperService {
	
	private GIFWallpaperEngine engine;
	/**
	 * 
	 */
	public GIFWallpaperService() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.service.wallpaper.WallpaperService#onCreateEngine()
	 */
	@Override
	public Engine onCreateEngine() {
		// TODO Auto-generated method stub
		
		try {
	 
	        return new GIFWallpaperEngine(Movie.decodeStream(
					getResources().getAssets().open("amisha.gif")));
	    }catch(IOException e){
	        Log.e("Exception", "Could not load asset");
	        return null;
	    }
	}

	/**
	 * @author Mahmudul Hasan Khan CSE
	 *
	 */
	public class GIFWallpaperEngine extends WallpaperService.Engine {
		
		private final int frameDuration = 20;
		 
	    private SurfaceHolder holder;
	    private boolean visible;
	    private Handler handler;
	    private Movie movie;
	
		/**
		 * 
		 */
		public GIFWallpaperEngine() {
			// TODO Auto-generated constructor stub
			super();
			handler = new Handler();
		}
	
		/**
		 * @param movie
		 */
		public GIFWallpaperEngine(Movie movie) {
			this();
			this.movie = movie;
		}

		/* (non-Javadoc)
		 * @see android.service.wallpaper.WallpaperService.Engine#onCreate(android.view.SurfaceHolder)
		 */
		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			// TODO Auto-generated method stub
			super.onCreate(surfaceHolder);
			this.holder = surfaceHolder;
		}
		
		private Runnable drawGIF = new Runnable() {
			
		    public void run() {
		    	draw();
		    }
		};
		 
		private void draw() {
		    if (visible) {
	    		Canvas canvas = holder.lockCanvas();
	    		try{
			        canvas.save();
			            // Adjust size and position so that
			            // the image looks good on your screen
			        DisplayMetrics displaymetrics = new DisplayMetrics();
			        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
					wm.getDefaultDisplay().getMetrics(displaymetrics);
					float height = (float) displaymetrics.heightPixels;
					float width = (float) displaymetrics.widthPixels;
			        float xSize = width/435f;
			        float ySize = height/557f;
			        if(xSize < ySize){
			            canvas.scale(xSize, xSize);
			        } else {
			        	canvas.scale(ySize, ySize);
			        }
			            movie.draw(canvas, -100, 0);
			        canvas.restore();
			        holder.unlockCanvasAndPost(canvas);
	    		}catch (Exception ex) {
					// TODO: handle exception
	    			Log.e("Exception", ex.getMessage());
				}
		        try{
		        	movie.setTime((int) (System.currentTimeMillis() % movie.duration()));
		        } catch (Exception ex){
		        	//Log.e("Exception", ex.getMessage());
		        	//Log.e("Exception", "movie.duration() = " + movie.duration());
		        	movie.setTime(1000);
		        }
		 
		        handler.removeCallbacks(drawGIF);
		        handler.postDelayed(drawGIF, frameDuration);
		    }
		}
		 
		/* (non-Javadoc)
		 * @see android.service.wallpaper.WallpaperService.Engine#onDestroy()
		 */
		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			handler.removeCallbacks(drawGIF);
		}
	
		/* (non-Javadoc)
		 * @see android.service.wallpaper.WallpaperService.Engine#onVisibilityChanged(boolean)
		 */
		@Override
		public void onVisibilityChanged(boolean visible) {
			// TODO Auto-generated method stub
			this.visible = visible;
		    if (visible) {
		        handler.post(drawGIF);
		    } else {
		        handler.removeCallbacks(drawGIF);
		    }
			super.onVisibilityChanged(visible);
		}
	
	}

	/**
	 * @return the engine
	 */
	public GIFWallpaperEngine getEngine() {
		return engine;
	}
}
