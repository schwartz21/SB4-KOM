import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
import dk.sdu.mmmi.cbse.enemySystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemySystem.EnemyPlugin;

module Enemy {
    requires Common;
    requires CommonPlayer;
    requires CommonEnemy;

    provides IEntityProcessingService with EnemyControlSystem;
    provides IGamePluginService with EnemyPlugin;
}

