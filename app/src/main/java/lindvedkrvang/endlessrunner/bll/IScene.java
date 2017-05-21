package lindvedkrvang.endlessrunner.bll;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface IScene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void recieveTouch(MotionEvent event);
}
