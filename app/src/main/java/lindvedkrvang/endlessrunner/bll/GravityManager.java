package lindvedkrvang.endlessrunner.bll;

import android.graphics.Rect;
import android.util.Log;

import lindvedkrvang.endlessrunner.be.Floor;
import lindvedkrvang.endlessrunner.be.Player;

public class GravityManager {

    public boolean isPlayerNotTouchingFloor(Player player, Floor floor){
        int playerBottom = player.getRect().bottom;
        int floorTop = floor.getRect().top;

        return playerBottom < floorTop;
    }
}
