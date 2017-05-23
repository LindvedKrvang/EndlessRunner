package lindvedkrvang.endlessrunner.bll.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import lindvedkrvang.endlessrunner.be.Constants;
import lindvedkrvang.endlessrunner.be.Floor;
import lindvedkrvang.endlessrunner.be.Player;
import lindvedkrvang.endlessrunner.bll.IScene;
import lindvedkrvang.endlessrunner.bll.managers.GravityManager;
import lindvedkrvang.endlessrunner.bll.managers.HealthManager;
import lindvedkrvang.endlessrunner.bll.managers.ObstacleManager;
import lindvedkrvang.endlessrunner.bll.managers.SceneManager;

public class GamePlayScene implements IScene {

    private final int X_POSITION = Constants.SCREEN_WIDTH / 4;
    private final int GRAVITY_TRESHOLD = 20;

    private GravityManager mGravityManager;
    private ObstacleManager mObstacleManager;
    private HealthManager mHealthManager;

    private Player mPlayer;
    private Point mPlayerPoint;

    private Floor mFloor;

    private float mGravity;
    private float mJumpStartTimer;
    private boolean mIsJumping;
    private boolean mDoubleJumpAvailable;
    private boolean mGameOver;
    private boolean mAllowedToJump;

    private int mAmountOfDamage;

    public GamePlayScene(){
        newGame();
    }

    @Override
    public void update() {
        if(!mGameOver){
            mPlayer.update(mPlayerPoint, mIsJumping);

            playerGravity();
            checkCollisionObstacle();

            mObstacleManager.update();
            if(mHealthManager.update(mAmountOfDamage)){
                newGame();
                SceneManager.ACTIVE_SCENE = 0;
            }
        }
    }

    private void checkCollisionObstacle() {
        if(mObstacleManager.collisionWithPlayer(mPlayer.getRect())){
            mAmountOfDamage++;
        }
    }

    /**
     * Checks if the player is suspended in the air. If yes - adjust the gravity accordingly.
     */
    private void playerGravity() {
        mPlayerPoint.set(mPlayerPoint.x, mPlayerPoint.y + (int) mGravity);
        if(!mAllowedToJump && !mGravityManager.isPlayerNotTouchingFloor(mPlayer, mFloor)){
            mGravity = 0;
            mIsJumping = false;
            mDoubleJumpAvailable = true;
            mPlayerPoint.set(mPlayerPoint.x, Constants.SCREEN_HEIGHT - 120);
        }else if(mIsJumping && mGravity < 0){
            mGravity += 1;
            decreaseJumpStartTimer();
        }else if(mIsJumping && mGravity < GRAVITY_TRESHOLD){
            mGravity += 1.5f;
            decreaseJumpStartTimer();
        }

    }

    /**
     * Adjust the gravity so the player "jumps".
     */
    private void jump(){
        if(!mIsJumping) {
            mGravity = -GRAVITY_TRESHOLD;
            mIsJumping = true;
            mJumpStartTimer = 2;
        }else if(mIsJumping && mDoubleJumpAvailable){
            mGravity = -GRAVITY_TRESHOLD;
            mDoubleJumpAvailable = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        mFloor.draw(canvas);
        mObstacleManager.draw(canvas);
        mPlayer.draw(canvas);

        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLACK);
        canvas.drawText("Times hit: " + mAmountOfDamage, 50, 50 + paint.descent() - paint.ascent(), paint);

        mHealthManager.draw(canvas);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                if(!mIsJumping && !mGravityManager.isPlayerNotTouchingFloor(mPlayer, mFloor)){
                    jump();
                }
                jump();
            }
        }
    }

    private void decreaseJumpStartTimer(){
        if(mJumpStartTimer > 0){
            mJumpStartTimer -= 0.5f;
        }
    }

    private void newGame(){
        mGravityManager = new GravityManager();
        mObstacleManager = new ObstacleManager(300, 100, 100, Color.BLUE);
        mHealthManager = new HealthManager();

        mPlayer = new Player(new Rect(0, 0, 100, 100), Color.BLACK);
        mPlayerPoint = new Point(X_POSITION, Constants.SCREEN_HEIGHT/2);

        mFloor = new Floor(new Rect(0, 0, 0, 0), Color.BLUE);

        mGravity = 0;
        mGameOver = false;
        mIsJumping = true;
        mDoubleJumpAvailable = true;
        mAllowedToJump = false;


        mAmountOfDamage = 0;
    }
}
