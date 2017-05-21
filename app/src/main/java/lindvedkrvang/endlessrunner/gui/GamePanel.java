package lindvedkrvang.endlessrunner.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import lindvedkrvang.endlessrunner.bll.GameLoopThread;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoopThread mGameLoopThread;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        mGameLoopThread = new GameLoopThread(getHolder(), this);

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

    }

    public void update(){

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
    }
}
