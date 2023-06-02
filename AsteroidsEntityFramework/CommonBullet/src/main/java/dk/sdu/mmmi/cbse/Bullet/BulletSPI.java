package dk.sdu.mmmi.cbse.Bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;

public interface BulletSPI {
    /**
     * IS called to spawn a new bullet from the position of the entity provided
     * @param e The entity that the bullet will be spawned from
     * @return a new instance of CommonBullet, that can be added to the world
     */
    CommonBullet createBullet(Entity e);
}
