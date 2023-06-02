package dk.sdu.mmmi.cbse.asteroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity[] asteroids;
    private final int size = 22;
    public static final int verticeCount = 10;

    public AsteroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        int count = 10;
        asteroids = new Entity[count];
        // Add entities to the world
        for (int i = 0; i < count; i++) {
            Entity asteroid = createAsteroid(gameData);
            asteroids[i] = asteroid;
            world.addEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {

        float deceleration = 200;
        float acceleration = 400 + deceleration;

        float maxSpeed = 30;
        float speedVariance = maxSpeed / 3;
        maxSpeed -= speedVariance * Math.random();
        float rotationSpeed = 2;
        float rotationVariance = rotationSpeed / 2;
        rotationSpeed -= rotationVariance * Math.random();


        //Position
        float x = gameData.getDisplayWidth() * (float)(Math.random());
        float y = gameData.getDisplayHeight() * (float)(Math.random());
        float radians = (float) (Math.PI * 2 * Math.random());

        Entity asteroid1 = new Asteroid(size);
        asteroid1.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid1.add(new PositionPart(x, y, radians));
        asteroid1.add(new ColorPart(128, 255, 0, 1));

        asteroid1.setRadius(size);

        MovingPart movingPart = asteroid1.getPart(MovingPart.class);
        movingPart.setUp(true);
        // Causes the asteroids to move in a circular pattern.
        // To make them spin nicely but still move straight, the visual must be disconnected from the positionPart
//        boolean spinLeft = Math.random() >0.5;
//        movingPart.setLeft(spinLeft);
//        movingPart.setRight(!spinLeft);

        asteroid1.setShapeX(new float[verticeCount]);
        asteroid1.setShapeY(new float[verticeCount]);

        return asteroid1;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : asteroids) {
            world.removeEntity(asteroid);
        }
    }

}
