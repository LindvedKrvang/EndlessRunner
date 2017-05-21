package lindvedkrvang.endlessrunner.bll;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import lindvedkrvang.endlessrunner.gui.GamePanel;

public class GameLoopThread extends Thread{

    public static final int MAX_FPS = 30;
    public static Canvas mCanvas;

    private SurfaceHolder mSurfaceHolder;
    private GamePanel mGamePanel;

    private boolean mRunning;
    private double mAverageFPS;

    public GameLoopThread(SurfaceHolder holder, GamePanel gamePanel){
        super();
        mSurfaceHolder = holder;
        mGamePanel = gamePanel;
    }

    public void setRunning(boolean running){
        mRunning = running;
    }

    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while(mRunning){
            startTime = System.nanoTime();
            mCanvas = null;

            //This takes care of keeping our game running.
            try{
                //Tries to get hold of the surfaceHolders canvas in a state where it can be edited.
                mCanvas = mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder){
                    //Calls the gamePanel to update and draw thereby continuing the game.
                    //This is required to be done in synchronized.
                    mGamePanel.update();
                    mGamePanel.draw(mCanvas);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                if(mCanvas != null){
                    try{
                        //We need to unlock the canvas again else our changes to it won't be shown.
                        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }

            //Here we make sure we don't go over our allowed MAX_FPS.
            //First we find the time in milliseconds since we started this frame.
            timeMillis = (System.nanoTime() - startTime)/1000000;
            //We see if the frame finished quicker than we want it to.
            waitTime = targetTime - timeMillis;
            try{
                if(waitTime > 0){
                    //If it did take longer, we sleep for the remaining time before creating the next frame.
                    this.sleep(waitTime);
                }
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }

            //This is purely for calculating and printing the averageFPS. It's not necessary to make the gameLoop work.
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == MAX_FPS){
                mAverageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                Log.d("AverageFPS", mAverageFPS + "");
            }
        }
    }
}
