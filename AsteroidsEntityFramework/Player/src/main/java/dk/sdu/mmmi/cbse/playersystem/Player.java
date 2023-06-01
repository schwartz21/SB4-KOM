package dk.sdu.mmmi.cbse.playerSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonPlayer.CommonPlayer;

/**
 *
 * @author corfixen
 */
public class Player extends CommonPlayer {

    @Override
    public void handleCollision(Entity other, World world) {
        super.handleCollision(this, world);
    }
}
