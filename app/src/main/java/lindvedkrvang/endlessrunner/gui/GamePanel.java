package lindvedkrvang.endlessrunner.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import lindvedkrvang.endlessrunner.bll.GameLoopThread;
import lindvedkrvang.endlessrunner.bll.SceneManager;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoopThread mGameLoopThread;
    private SceneManager mSceneManager;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        mGameLoopThread = new GameLoopThread(getHolder(), this);

        mSceneManager = new SceneManager();

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mGameLoopThread = new GameLoopThread(holder, this);
        mGameLoopThread.setRunning(true);
        mGameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                mGameLoopThread.setRunning(false);
                mGameLoopThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update(){
        mSceneManager.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        mSceneManager.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mSceneManager.receiveTouch(event);
        return true;
    }
}
