package dk.sdu.mmmi.cbse.playerSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {

        float deceleration = 200;
        float acceleration = 600;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2f;
        float y = gameData.getDisplayHeight() / 2f;
        float radians = (float) Math.PI / 2;
        
        Entity playerShip = new Player();
        playerShip.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed));
        playerShip.add(new PositionPart(x, y, radians));
        playerShip.add(new ColorPart(0, 255, 255, 0));

        playerShip.add(new SpritePart(playerShip, "assets/" + "unnamed.atlas", "ubuntu"));

        playerShip.setRadius(8);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
