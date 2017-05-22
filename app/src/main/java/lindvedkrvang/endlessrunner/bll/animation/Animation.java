package lindvedkrvang.endlessrunner.bll.animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;

public class Animation {

    private Bitmap[] mFrames;
    private int mFrameIndex;
    private float mFrameTime;
    private long mLastFrame;

    private boolean mIsPlaying;

    public Animation(Bitmap[] frames, float animationTime){
        mIsPlaying = false;
        mFrames = frames;
        mFrameIndex = 0;
        mFrameTime = animationTime / mFrames.length;
        mLastFrame = System.currentTimeMillis();
    }

    public boolean isPlaying(){
        return mIsPlaying;
    }

    public void play(){
        mIsPlaying = true;

        mFrameIndex = 0;
        mLastFrame = System.currentTimeMillis();
    }

    public void stop(){
        mIsPlaying = false;
    }

    public void update(){
        if(!isPlaying()){
            return;
        }
        if(System.currentTimeMillis() - mLastFrame > mFrameTime * 1000){
            mFrameIndex++;
            mFrameIndex = mFrameIndex >= mFrames.length ? 0 : mFrameIndex;
            mLastFrame = System.currentTimeMillis();
        }
    }

    public void draw(Canvas canvas, Rect destination){
        if(!isPlaying()){
            return;
        }
        scaleRect(destination);
        canvas.drawBitmap(mFrames[mFrameIndex], null, destination, new Paint());
    }

    private void scaleRect(Rect rect) {
        float whRatio = (float)(mFrames[mFrameIndex].getWidth()) / (float)(mFrames[mFrameIndex].getHeight());
        if(rect.width() > rect.height()){
            rect.left = rect.height() - (int)((rect.height() * whRatio));
        }else{
            rect.top = rect.bottom - (int)((rect.width() * (1/whRatio)));
        }
    }
}
