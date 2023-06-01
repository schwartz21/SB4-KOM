import dk.sdu.mmmi.cbse.asteroidSystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroidSystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;

    provides IEntityProcessingService with AsteroidControlSystem;
    provides IGamePluginService with AsteroidPlugin;
}