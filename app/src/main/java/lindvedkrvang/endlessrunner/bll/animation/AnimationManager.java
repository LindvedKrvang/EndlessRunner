package lindvedkrvang.endlessrunner.bll.animation;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

public class AnimationManager {

    private Animation[] mAnimations;
    private int mAnimationIndex;

    public AnimationManager(Animation[] animations){
        mAnimations = animations;
        mAnimationIndex = 0;
    }

    public void playAnimation(int index){
        for(int i = 0; i < mAnimations.length; i++){
            if(i == index){
                if(!mAnimations[i].isPlaying()){
                    mAnimations[i].play();
                }
            }else{
                mAnimations[i].stop();
            }
        }
        mAnimationIndex = index;
    }

    public void draw(Canvas canvas, Rect rect){
        if(mAnimations[mAnimationIndex].isPlaying()){
            mAnimations[mAnimationIndex].draw(canvas, rect);
        }
    }

    public void update(){
        if(mAnimations[mAnimationIndex].isPlaying()){
            mAnimations[mAnimationIndex].update();
        }
    }
}
