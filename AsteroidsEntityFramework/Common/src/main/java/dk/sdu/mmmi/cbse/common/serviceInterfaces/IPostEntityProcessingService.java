package dk.sdu.mmmi.cbse.common.serviceInterfaces;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        /**<h2>Description:</h2>
         * Called after the movement updates have happened from {@link IEntityProcessingService#process(GameData, World)}.
         * <br><br>
         * <h2>Pre:</h2>
         * The entities must be started, and before each call to this process, {@link IEntityProcessingService#process(GameData, World)} must be called.
         * <br><br>
         * <h2>Post:</h2>
         * The entities in the world will have been post-processed.
         * <br><br>
         * @param gameData Information about the Game, such as screen size etc.
         * @param world The world object that the Plugin will be added to.
         */
        void process(GameData gameData, World world);
}
