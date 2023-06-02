package dk.sdu.mmmi.cbse.BulletSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            ColorPart colorPart = bullet.getPart(ColorPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            colorPart.process(gameData, bullet);
            lifePart.process(gameData, bullet);

            if(lifePart.getExpiration() <= 0){
                world.removeEntity(bullet);
            }

            assert bullet instanceof Bullet;

            updateShape(bullet, ((Bullet) bullet).size);
        }
    }

    private void updateShape(Entity entity, float outerSize) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        float ratio = 4;


        double inc = (Math.PI * 2) / BulletPlugin.verticeCount;

        float offsetSize =  (outerSize - (outerSize / ratio)); // should be multiplied by 2 to get the true offset
        // we dont do that however, since we divide the offset evenly between upper and lower half below
        float offsetX = (float) Math.cos(radians + Math.PI) * offsetSize;
        float offsetY = (float) (Math.sin(radians + Math.PI) * offsetSize);

        // upperhalf gets offset negatively
        for (int i = 0; i < shapey.length / 2 ; i++) {
            shapex[i] = (float) (x - offsetX + Math.cos(radians + (inc * i) - Math.PI/2) * outerSize / ratio);
            shapey[i] = (float) (y - offsetY + Math.sin(radians + (inc * i) - Math.PI/2) * outerSize / ratio);
        }
        //bottom half gets offset positively
        for (int i = shapey.length / 2; i < shapey.length ; i++) {
            shapex[i] = (float) (x + offsetX + Math.cos(radians + (inc * i) - Math.PI/2) * outerSize / ratio);
            shapey[i] = (float) (y + offsetY + Math.sin(radians + (inc * i) - Math.PI/2) * outerSize / ratio);
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
