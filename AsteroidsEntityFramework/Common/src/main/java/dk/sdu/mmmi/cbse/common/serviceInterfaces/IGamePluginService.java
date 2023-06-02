package dk.sdu.mmmi.cbse.common.serviceInterfaces;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**<h2>Description:</h2>
     * Called to start the plugin and add the entity to the world. <br>
     * <br>
     * <h2>Pre:</h2>
     * The start method must not have been called before.
     * <br><br>
     * <h2>Post:</h2>
     * The world contains the entity created.
     * <br><br>
     * @param gameData Information about the Game, such as screen size etc.
     * @param world The world object that the Plugin will be added to.
     */
    void start(GameData gameData, World world);

    /**<h2>Description:</h2>
     * Called to remove the plugin/the entity from the world. <br>
     * <br>
     * <h2>Pre:</h2>
     * The start method have been called.
     * <br><br>
     * <h2>Post:</h2>
     * The world to remove the entity from.
     * <br><br>
     * @param gameData Information about the Game, such as screen size etc.
     * @param world The world object that the Plugin will be removed from.
     */
    void stop(GameData gameData, World world);
}
