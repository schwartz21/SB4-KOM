package dk.sdu.mmmi.cbse.asteroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonAsteroid.CommonAsteroid;

/**
 *
 * @author corfixen
 */
public class Asteroid extends CommonAsteroid {
    public final int size;

    public Asteroid(int outerSize) {
        this.size = outerSize;
    }

    @Override
    public void handleCollision(Entity other, World world) {
        if (other instanceof Asteroid) {
            return;
        }
        other.handleCollision(this, world);
    }
}
