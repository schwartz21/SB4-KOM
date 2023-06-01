package dk.sdu.mmmi.cbse.BulletSystem;

import dk.sdu.mmmi.cbse.Bullet.CommonBullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonAsteroid.CommonAsteroid;

/**
 *
 * @author corfixen
 */
public class Bullet extends CommonBullet {
    public final int size;

    public Bullet(int outerSize) {
        this.size = outerSize;
    }

    @Override
    public void handleCollision(Entity other, World world) {
        if (other instanceof CommonBullet){
            //split both?
            return;
        }
        if (other instanceof CommonAsteroid) {
            super.handleCollision(other, world);
            return;
        }
        other.handleCollision(this, world);
    }
}
