package lindvedkrvang.endlessrunner.be;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Floor implements IGameObject {

    private Rect mRect;
    private int mColor;

    public Floor(Rect rect, int color){
        mRect = rect;
        mColor = color;
        mRect.set(0, Constants.SCREEN_HEIGHT - 50, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
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
}
