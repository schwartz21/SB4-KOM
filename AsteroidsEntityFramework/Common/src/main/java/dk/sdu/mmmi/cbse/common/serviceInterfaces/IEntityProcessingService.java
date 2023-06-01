package dk.sdu.mmmi.cbse.common.serviceInterfaces;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**<h2>Description:</h2>
     * Called to update movement and similar behaviour of a specific set of components, that exist in the world.
     * This process can and should be used for behaviour that can or must happen before collision checks.
     * <br>
     * <br>
     * <h2>Pre:</h2>
     * The previous call to process must have finished.
     * <br><br>
     * <h2>Post:</h2>
     * The data in the world entities will have been updated.
     * <br><br>
     * @param gameData Contains the GameData information used for processing the entities.
     *                 Including the DeltaTime since the last update.
     * @param world Contains all the entities present in the world. Process should operate on a subset of these.
     */
    void process(GameData gameData, World world);
}
