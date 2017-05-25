package lindvedkrvang.endlessrunner.bll.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import lindvedkrvang.endlessrunner.be.Constants;
import lindvedkrvang.endlessrunner.be.Floor;
import lindvedkrvang.endlessrunner.bll.IScene;
import lindvedkrvang.endlessrunner.bll.managers.SceneManager;

public class Menu implements IScene {

    private Floor mFloor;

    public Menu(){
        mFloor = new Floor(new Rect(0, 0, 0, 0), Color.BLUE);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        mFloor.draw(canvas);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                SceneManager.ACTIVE_SCENE = Constants.GAME_SCENE;
                break;
            }
        }
    }
}
