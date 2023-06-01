package dk.sdu.mmmi.cbse.BulletSystem;

import dk.sdu.mmmi.cbse.Bullet.BulletSPI;
import dk.sdu.mmmi.cbse.Bullet.CommonBullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

import java.util.ArrayList;

public class BulletPlugin implements IGamePluginService, BulletSPI {

    private ArrayList<Entity> bullets = new ArrayList<>();
    private final int size = 10;
    private final float lifeTimeS = 1;
    public static final int verticeCount = 10;

    public BulletPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        int count = 0;
        // Add entities to the world
        for (int i = 0; i < count; i++) {
            PositionPart positionPart = new PositionPart((float) (100 + i * 30), 100 + i * 30, (float) (Math.PI * Math.random()));
            spawnBullet(positionPart, world);
        }
    }

    public void spawnBullet(PositionPart positionPart, World world){
        Entity bullet = createBullet(positionPart, 0);
        bullets.add(bullet);
        world.addEntity(bullet);
    }

    private CommonBullet createBullet(PositionPart positionPart, float shooterSize) {
        float acceleration = 8000;
        float maxSpeed = 1000;

        Bullet bullet = new Bullet(size);
        bullet.add(new MovingPart(0, acceleration, maxSpeed, 0));
        PositionPart position = positionPart.copy();
        bullet.add(position);
        bullet.add(new ColorPart(0, 91, 46, 1));

        bullet.setRadius(size);
        position.setX(position.getX() + (float) Math.cos(position.getRadians()) * (shooterSize + size)*2);
        position.setY(position.getY() + (float) Math.sin(position.getRadians()) * (shooterSize + size)*2);

        MovingPart movingPart = bullet.getPart(MovingPart.class);
        movingPart.setUp(true);

        bullet.setShapeX(new float[verticeCount]);
        bullet.setShapeY(new float[verticeCount]);

        LifePart lifePart = new LifePart(1, lifeTimeS);
        bullet.add(lifePart);

        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : bullets) {
            world.removeEntity(asteroid);
        }
    }

    @Override
    public CommonBullet createBullet(Entity e) {
        PositionPart p = e.getPart(PositionPart.class);
        return createBullet(p, e.getRadius());
    }
}
