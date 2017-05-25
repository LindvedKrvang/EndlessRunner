package lindvedkrvang.endlessrunner.be;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import lindvedkrvang.endlessrunner.R;

public class Floor implements IGameObject {

    private Rect mRect;
    private int mColor;

    private Bitmap mFloorImage;

    public Floor(Rect rect, int color){
        mRect = rect;
        mColor = color;
        mRect.set(0, Constants.SCREEN_HEIGHT - 50, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        BitmapFactory bf = new BitmapFactory();
        mFloorImage = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.floor);
    }

    @Override
    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setColor(mColor);
//        canvas.drawRect(mRect, paint);
        canvas.drawBitmap(mFloorImage, null, mRect, new Paint());
    }

    @Override
    public void update() {

    }

    public Rect getRect(){
        return mRect;
    }
}
