package lindvedkrvang.endlessrunner.bll.managers;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import lindvedkrvang.endlessrunner.be.Constants;
import lindvedkrvang.endlessrunner.bll.scenes.GamePlayScene;
import lindvedkrvang.endlessrunner.bll.IScene;
import lindvedkrvang.endlessrunner.bll.scenes.Menu;

public class SceneManager {

    public static int ACTIVE_SCENE;

    private List<IScene> mScenes = new ArrayList<>();


    public SceneManager(){
        ACTIVE_SCENE = Constants.MENU_SCENE;
        mScenes.add(new Menu());
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
