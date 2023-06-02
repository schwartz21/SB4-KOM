package dk.sdu.mmmi.cbse.enemySystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    private final int outerSize = 5;
    private final int innerSize = 3;

    public EnemyPlugin() {}

    @Override
    public void start(GameData gameData, World world) {
        // Add enemies to the world
        for (int i= 0; i< 5; i++){
            enemy = createEnemyShip(gameData, 50 * i, 50 * i);
            world.addEntity(enemy);
        }
    }

    private Entity createEnemyShip(GameData gameData, float x, float y) {
        float deceleration = 200;
        float acceleration = 400 + deceleration;
        float maxSpeed = 300;
        float rotationSpeed = 8;
        float radians = (float) Math.PI / 2;

        Entity enemyShip = new Enemy(outerSize, innerSize);
        enemyShip.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new ColorPart(1.0, 0, 0, 1));
        enemyShip.setRadius(outerSize);

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }
}
