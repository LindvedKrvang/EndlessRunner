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
    private final int GRAVITY = 15;
    private final int JUMP_SPEED = 20;
    private final int JUMP_TIMER = 15;

    private int mJumpTimer;

    private GravityManager mGravityManager;
    private ObstacleManager mObstacleManager;

    private Player mPlayer;
    private Point mPlayerPoint;

    private Floor mFloor;

    private boolean mIsJumping;
    private boolean mGameOver;

    public GamePlayScene(){
        mGravityManager = new GravityManager();
        mObstacleManager = new ObstacleManager(300, 100, 100, Color.BLUE);

        mPlayer = new Player(new Rect(0, 0, 100, 100), Color.BLACK);
        mPlayerPoint = new Point(X_POSITION, Constants.SCREEN_HEIGHT/2);

        mFloor = new Floor(new Rect(0, 0, 0, 0), Color.BLUE);

        mJumpTimer = 0;

        mGameOver = false;
        mIsJumping = false;
    }

    @Override
    public void update() {
        if(!mGameOver){
            mPlayer.update(mPlayerPoint);

            playerGravity();

            mObstacleManager.update();
        }
    }

    private void playerGravity() {
        if(!mIsJumping && mGravityManager.isPlayerNotTouchingFloor(mPlayer, mFloor)){
            mPlayerPoint.set(mPlayerPoint.x, mPlayerPoint.y + GRAVITY);
        }else if(mIsJumping){
            mPlayerPoint.set(mPlayerPoint.x, mPlayerPoint.y - JUMP_SPEED);
            mJumpTimer++;
            if(mJumpTimer >= JUMP_TIMER){
                mIsJumping = false;
                mJumpTimer = 0;
            }
        }else{
            mIsJumping = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        mFloor.draw(canvas);
        mObstacleManager.draw(canvas);
        mPlayer.draw(canvas);

    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                if(!mIsJumping && !mGravityManager.isPlayerNotTouchingFloor(mPlayer, mFloor)){
//                    mPlayerPoint.set(mPlayerPoint.x, mPlayerPoint.y - 200);
                    mIsJumping = true;
                }
            }
        }
    }
}
