package dk.sdu.mmmi.cbse.playerSystem;

import dk.sdu.mmmi.cbse.Bullet.BulletSPI;
import dk.sdu.mmmi.cbse.Bullet.CommonBullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;

import java.time.Instant;
import java.util.ServiceLoader;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;

/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {
    private Instant lastBulletFiredTime = Instant.now();
    private int shootDelay = 1; // In seconds

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);

            movingPart.setLeft(gameData.getKeys().isDown(LEFT));
            movingPart.setRight(gameData.getKeys().isDown(RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(UP));

            if (gameData.getKeys().isDown(SPACE)){
                shoot(player, world);
            }


            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

            updateShape(player);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * Math.PI / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * Math.PI / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + Math.PI) * 5);
        shapey[2] = (float) (y + Math.sin(radians + Math.PI) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * Math.PI / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * Math.PI / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);

    }

    public void shoot(Entity player, World world){
        if (this.readyToShoot()) {
            ServiceLoader<BulletSPI> loader = ServiceLoader.load(BulletSPI.class);

            for (BulletSPI bulletSPI : loader) {
                CommonBullet bullet = bulletSPI.createBullet(player);
                world.addEntity(bullet);
            }
        }
    }

    public boolean readyToShoot() {
        boolean weaponIsReady = false;
        if (getLastBulletFiredTime().isBefore(Instant.now().minusSeconds(shootDelay))) {
            this.setLastBulletFiredTime(Instant.now());
            weaponIsReady = true;
        }
        return weaponIsReady;
    }

    public Instant getLastBulletFiredTime() {
        return lastBulletFiredTime;
    }

    public void setLastBulletFiredTime(Instant lastBulletFiredTime) {
        this.lastBulletFiredTime = lastBulletFiredTime;
    }
}
