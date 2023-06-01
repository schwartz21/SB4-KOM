package dk.sdu.mmmi.cbse.enemySystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonPlayer.CommonPlayer;


import java.util.List;

/**
 *
 * @author jcs
 */
public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> playerList = world.getEntities(CommonPlayer.class);

        boolean targetPlayer = playerList.size() >= 1;

        PositionPart playerPosition = null;

        if (targetPlayer){
            playerPosition = playerList.get(0).getPart(PositionPart.class);
        }

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            ColorPart colorPart = enemy.getPart(ColorPart.class);

            double turn = 0;

            if (targetPlayer){
                turn = targetPlayer(positionPart, playerPosition);
            }

            movingPart.setUp(false);

            movingPart.setLeft(turn > 0);
            movingPart.setRight(turn < 0);

            if(turn == 0 && targetPlayer){
                movingPart.setUp(true);
            }


            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            colorPart.process(gameData, enemy);

            assert enemy instanceof Enemy;

            updateShape(enemy, ((Enemy) enemy).outerSize, ((Enemy) enemy).innerSize);
        }
    }

    private void updateShape(Entity entity, float outerSize, float innersize) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * outerSize);
        shapey[0] = (float) (y + Math.sin(radians) * outerSize);

        shapex[1] = (float) (x + Math.cos(radians - 4 * Math.PI / 5) * outerSize);
        shapey[1] = (float) (y + Math.sin(radians - 4 * Math.PI / 5) * outerSize);

        shapex[2] = (float) (x + Math.cos(radians + Math.PI) * innersize);
        shapey[2] = (float) (y + Math.sin(radians + Math.PI) * innersize);

        shapex[3] = (float) (x + Math.cos(radians + 4 * Math.PI / 5) * outerSize);
        shapey[3] = (float) (y + Math.sin(radians + 4 * Math.PI / 5) * outerSize);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private double targetPlayer(PositionPart positionPart, PositionPart playerPosition){
        double xDiff = positionPart.getX() - playerPosition.getX();
        double yDiff = positionPart.getY() - playerPosition.getY();
        double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

        double targetAngle = Math.acos(xDiff / dist);
        targetAngle = yDiff < 0 ? -targetAngle : targetAngle;
        targetAngle = targetAngle < 0 ? Math.PI * 2 + targetAngle : targetAngle;

//        double anglediff = angle - ((radians + Math.PI) % (Math.PI * 2));
        double thisAngle = ((positionPart.getRadians() + Math.PI) % (Math.PI * 2));
        thisAngle = thisAngle < 0 ? thisAngle + (Math.PI * 2) : thisAngle;
        double angleDiff = targetAngle - thisAngle;

        angleDiff = angleDiff > Math.PI ? angleDiff - (Math.PI*2) : angleDiff;
        angleDiff = angleDiff < -Math.PI ? angleDiff + (Math.PI*2) : angleDiff;

//        System.out.println("Angle: " + targetAngle);
//        System.out.println("Anglediff: " + angleDiff + " Second part: " + (Math.PI * 2 - angleDiff));



        return Math.abs(angleDiff) > 0.05 ? angleDiff: 0;
//        return Math.min(anglediff, Math.PI * 2 - anglediff);

    }

}
