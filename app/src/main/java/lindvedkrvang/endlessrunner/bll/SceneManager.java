package lindvedkrvang.endlessrunner.bll;

import android.graphics.Canvas;
import android.transition.Scene;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    public static int ACTIVE_SCENE;

    private List<IScene> mScenes = new ArrayList<>();


    public SceneManager(){
        ACTIVE_SCENE = 0;
        mScenes.add(new GamePlayScene());
    }

    public void update(){
        mScenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas){
        mScenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public void receiveTouch(MotionEvent event){
        mScenes.get(ACTIVE_SCENE).recieveTouch(event);
    }
}
