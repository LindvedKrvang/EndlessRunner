package lindvedkrvang.endlessrunner.be;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements IGameObject {

    private Rect mRect;
    private int mColor;

    public Obstacle(int rectHeight, int obstacleWidth, int startX, int startY, int color){
        mRect = new Rect(startX, startY, startX + obstacleWidth, startY + rectHeight);
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

    /**
     * Moves the obstacle from right to left with the given speed.
     * @param speed
     */
    public void move(float speed){
        mRect.left -= speed;
        mRect.right -= speed;
    }

    public Rect getRect(){
        return mRect;
    }

    /**
     * Checks if the obstacle intersects with the player.
     * @param playerRect
     * @return
     */
    public boolean collisionWithPlayer(Rect playerRect){
        return mRect.intersects(playerRect.left, playerRect.top, playerRect.right, playerRect.bottom);
    }
}
