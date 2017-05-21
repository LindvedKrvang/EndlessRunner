package lindvedkrvang.endlessrunner.be;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements IGameObject {

    private Rect mRect;
    private int mColor;

    public Player(Rect rect, int color){
        mRect = rect;
        mColor = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mColor);
        canvas.drawRect(mRect, paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point){
        //Left, Top, Right, Back
        mRect.set(point.x - mRect.width()/2,
                point.y - mRect.height()/2,
                point.x + mRect.width()/2,
                point.y + mRect.height()/2);
    }

    public Rect getRect(){
        return mRect;
    }

}
