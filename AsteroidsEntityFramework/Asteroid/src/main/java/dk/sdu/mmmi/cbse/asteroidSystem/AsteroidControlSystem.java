package dk.sdu.mmmi.cbse.asteroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            ColorPart colorPart = asteroid.getPart(ColorPart.class);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            colorPart.process(gameData, asteroid);

            assert asteroid instanceof Asteroid;

            updateShape(asteroid, ((Asteroid) asteroid).size);
        }
    }

    private void updateShape(Entity entity, float outerSize) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();


        double inc = (Math.PI * 2) / AsteroidPlugin.verticeCount;
        for (int i = 0; i < shapey.length ; i++) {
            shapex[i] = (float) (x + Math.cos(radians + (inc * i)) * outerSize);
            shapey[i] = (float) (y + Math.sin(radians + (inc * i)) * outerSize);
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
