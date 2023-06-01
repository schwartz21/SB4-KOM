import dk.sdu.mmmi.cbse.BulletSystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.BulletSystem.BulletPlugin;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
import dk.sdu.mmmi.cbse.Bullet.BulletSPI;

module Bullet {
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;

    provides IEntityProcessingService with BulletControlSystem;
    provides IGamePluginService with BulletPlugin;
    provides BulletSPI with BulletPlugin;
}