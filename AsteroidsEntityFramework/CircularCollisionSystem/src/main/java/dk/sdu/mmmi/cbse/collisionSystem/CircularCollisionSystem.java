package dk.sdu.mmmi.cbse.collisionSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IPostEntityProcessingService;

import java.util.List;

/**
 * @author jcs
 */
public class CircularCollisionSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entities = world.getEntityList();
        for (int i = 0; i < entities.size() - 1; i++) {
            Entity entity = entities.get(i);
            PositionPart positionPart = entity.getPart(PositionPart.class);

            double myX = positionPart.getX();
            double myY = positionPart.getY();

            for (int j = i +1; j < entities.size(); j++) {
                Entity other = entities.get(j);
                PositionPart otherPositionPart = other.getPart(PositionPart.class);

                // Check for collision
                double dist = Math.sqrt(
                        ((myX - otherPositionPart.getX()) * (myX - otherPositionPart.getX()))
                                +
                                ((myY - otherPositionPart.getY()) * (myY - otherPositionPart.getY()))
                );

                if (dist <= entity.getRadius() + other.getRadius()){
                    // collision
                    entity.handleCollision(other, world);

                }
            }
        }
    }
}
