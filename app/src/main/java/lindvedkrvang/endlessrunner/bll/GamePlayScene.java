package lindvedkrvang.endlessrunner.bll;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import lindvedkrvang.endlessrunner.be.Constants;
import lindvedkrvang.endlessrunner.be.Floor;
import lindvedkrvang.endlessrunner.be.Player;

public class GamePlayScene implements  IScene {

    private final int X_POSITION = Constants.SCREEN_WIDTH / 4;

    private Player mPlayer;
    private Point mPlayerPoint;

    private Floor mFloor;

    private boolean mGameOver;

    public GamePlayScene(){
        mPlayer = new Player(new Rect(0, 0, 100, 100), Color.BLACK);
        mPlayerPoint = new Point(X_POSITION, Constants.SCREEN_HEIGHT/2);

        //TODO RKL: Check if floor has no collision now.
        mFloor = new Floor(new Rect(0, 0, 0, 0), Color.BLUE);

        mGameOver = false;
    }

    @Override
    public void update() {
        if(!mGameOver){
            mPlayer.update(mPlayerPoint);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        mFloor.draw(canvas);
        mPlayer.draw(canvas);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {

    }
}
