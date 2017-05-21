package lindvedkrvang.endlessrunner.bll;

import android.graphics.Rect;
import android.util.Log;

import lindvedkrvang.endlessrunner.be.Floor;
import lindvedkrvang.endlessrunner.be.Player;

public class GravityManager {

    /**
     * Checks if the bottom of the player is touching the top of the floor.
     * @param player
     * @param floor
     * @return
     */
    public boolean isPlayerNotTouchingFloor(Player player, Floor floor){
        int playerBottom = player.getRect().bottom;
        int floorTop = floor.getRect().top;

        return playerBottom < floorTop;
    }
}
