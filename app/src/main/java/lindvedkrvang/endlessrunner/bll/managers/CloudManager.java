package lindvedkrvang.endlessrunner.bll.managers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import lindvedkrvang.endlessrunner.R;
import lindvedkrvang.endlessrunner.be.Cloud;
import lindvedkrvang.endlessrunner.be.Constants;
import lindvedkrvang.endlessrunner.be.IGameObject;

public class CloudManager implements IGameObject {

    private final int CLOUD_MINIMUM_HEIGHT = 200;
    private final int CLOUD_MAXIMUM_HEIGHT = 400;
    private final int CLOUD_MINIMUM_WIDTH = 200;
    private final int CLOUD_MAXIMUM_WIDTH = 400;

    private final int CLOUD_MAXIMUM_Y_COORDINATE = Constants.SCREEN_HEIGHT - (CLOUD_MAXIMUM_HEIGHT * 2);

    private final int CLOUD_SPEED = 5;

    private List<Cloud> mClouds;
    private List<Bitmap> mImages;

    public CloudManager(){
        createImages();
        populateClouds();
    }

    /**
     * Creates all the images used for the clouds.
     */
    private void createImages() {
        mImages = new ArrayList<>();
        BitmapFactory bf = new BitmapFactory();
        mImages.add(bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloud1));
        mImages.add(bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloud2));
        mImages.add(bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloud3));
        mImages.add(bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloud4));
        mImages.add(bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.cloud5));
    }

    /**
     * Creates all the clouds at the beginning of the game.
     */
    private void populateClouds() {
        mClouds = new ArrayList<>();
        for(Bitmap image : mImages){
            mClouds.add(createCloud(image));
        }
    }

    /**
     * Creates a single Cloud with the parsed Bitmap that has a random size and position.
     * @param image
     * @return
     */
    private Cloud createCloud(Bitmap image){
        double height = Math.random() * CLOUD_MAXIMUM_HEIGHT + CLOUD_MINIMUM_HEIGHT;
        double width = Math.random() *CLOUD_MAXIMUM_WIDTH + CLOUD_MINIMUM_WIDTH;

        double yCoordinate = Math.random() * CLOUD_MAXIMUM_Y_COORDINATE;
        double xCoordinate = (Math.random() * Constants.SCREEN_WIDTH) + Constants.SCREEN_WIDTH;

        Rect rect = new Rect((int)xCoordinate, (int)yCoordinate, (int)(xCoordinate + width), (int)(yCoordinate + height));

        return new Cloud(rect, image, CLOUD_SPEED);
    }

    @Override
    public void draw(Canvas canvas) {
        for(Cloud cloud : mClouds){
            cloud.draw(canvas);
        }
    }

    @Override
    public void update() {
        for(Cloud cloud : mClouds){
            cloud.move();
        }

        //Checks if a cloud has parsed the screen. If yes - removes it and creates a new Cloud with the same image.
        if(mClouds.get(mClouds.size() - 1).getRect().right <= 0){
            int pos = mClouds.size() - 1;
            Bitmap image = mClouds.get(pos).getImage();
            mClouds.remove(pos);
            mClouds.add(0, createCloud(image));
        }


    }
}
