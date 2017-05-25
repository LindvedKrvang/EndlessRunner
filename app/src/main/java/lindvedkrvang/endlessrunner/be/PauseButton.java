package lindvedkrvang.endlessrunner.be;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import lindvedkrvang.endlessrunner.R;

public class PauseButton implements IGameObject {

    private final int WIDTH = 175;
    private final int HEIGHT = 175;
    private final int X_COORDINATE = (3 * Constants.SCREEN_WIDTH) / 4 ;
    private final int Y_COORDINATE = HEIGHT / 2;

    private Rect mRect;

    private Bitmap mImage;

    public PauseButton(){
        mRect = new Rect(X_COORDINATE, Y_COORDINATE, X_COORDINATE + WIDTH, Y_COORDINATE + HEIGHT);
        BitmapFactory bf = new BitmapFactory();
        mImage = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pause);
    }

    public Rect getRect(){
        return mRect;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(mImage, null, mRect, new Paint());
    }

    @Override
    public void update() {

    }
}
