package lindvedkrvang.endlessrunner.be;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Cloud implements IGameObject{

    private Rect mRect;
    private Bitmap mImage;
    private int mSpeed;

    public Cloud(Rect rect, Bitmap image, int speed){
        mRect = rect;
        mImage = image;
        mSpeed = speed;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(mImage, null, mRect, new Paint());
    }

    @Override
    public void update() {

    }

    public void move(){
        mRect.right -= mSpeed;
        mRect.left -= mSpeed;
    }

    public Bitmap getImage(){
        return  mImage;
    }

    public Rect getRect(){
        return mRect;
    }
}
