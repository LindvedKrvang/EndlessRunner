package lindvedkrvang.endlessrunner.bll;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import lindvedkrvang.endlessrunner.be.Constants;
import lindvedkrvang.endlessrunner.be.IGameObject;
import lindvedkrvang.endlessrunner.be.Obstacle;

public class ObstacleManager implements IGameObject{

    private List<Obstacle> mObstacles;
    private int mObstacleGap;
    private int mObstacleHeight;
    private int mObstacleWidth;
    private int mColor;

    public ObstacleManager(int obstacleGap, int obstacleHeight, int obstacleWidth, int color){
        mObstacleGap = obstacleGap;
        mObstacleHeight = obstacleHeight;
        mObstacleWidth = obstacleWidth;
        mColor = color;

        mObstacles = new ArrayList<>();

        populateObstacles();
    }

    private void populateObstacles() {
        int currX = 5 * Constants.SCREEN_WIDTH / 3;
        while(currX > Constants.SCREEN_WIDTH){
            mObstacles.add(new Obstacle(mObstacleHeight, mObstacleWidth, currX, Constants.SCREEN_HEIGHT - 150, mColor));
            currX -= mObstacleGap;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for(Obstacle obj : mObstacles){
            obj.draw(canvas);
        }
    }

    @Override
    public void update() {
        for(Obstacle obj : mObstacles){
            obj.move(5f);
        }

        if(mObstacles.get(mObstacles.size()-1).getRect().right <= 0){
            mObstacles.add(new Obstacle(mObstacleHeight, mObstacleWidth,
                    mObstacles.get(0).getRect().right + mObstacleGap,
                    Constants.SCREEN_HEIGHT - 150, mColor));
            mObstacles.remove(mObstacles.size() - 1);
        }
    }
}
