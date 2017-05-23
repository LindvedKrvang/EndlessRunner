package lindvedkrvang.endlessrunner.bll.managers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import lindvedkrvang.endlessrunner.be.Constants;

public class HealthManager {

    private final int BAR_WIDTH = 100;
    private final int BAR_HEIGHT = 200;
    private final int HEALTH_MULTIPLIER = 4;
    private final int LEFT_COORDINATE = Constants.SCREEN_WIDTH - (BAR_WIDTH * 2);
    private final int RIGHT_COORDINATE = LEFT_COORDINATE + BAR_WIDTH;
    private final int TOP_COORDINATE = BAR_WIDTH;
    private final int BOTTOM_COORDINATE = BAR_WIDTH + BAR_HEIGHT;

    private Rect mRectDamage;
    private Rect mRectHealth;

    public HealthManager(){
        mRectDamage = new Rect(LEFT_COORDINATE, TOP_COORDINATE, RIGHT_COORDINATE, 0);
        mRectHealth = new Rect(LEFT_COORDINATE, TOP_COORDINATE, RIGHT_COORDINATE, BOTTOM_COORDINATE);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();

        paint.setColor(Color.RED);
        canvas.drawRect(mRectDamage, paint);

        paint.setColor(Color.GREEN);
        canvas.drawRect(mRectHealth, paint);
    }

    public void update(int damageTaken){
        if(damageTaken <= BAR_HEIGHT / HEALTH_MULTIPLIER){
            damageTaken = damageTaken * HEALTH_MULTIPLIER;
            mRectDamage.set(LEFT_COORDINATE, TOP_COORDINATE, RIGHT_COORDINATE, TOP_COORDINATE + damageTaken);
            mRectHealth.set(LEFT_COORDINATE, TOP_COORDINATE + damageTaken, RIGHT_COORDINATE, BOTTOM_COORDINATE);
        }
    }
}
