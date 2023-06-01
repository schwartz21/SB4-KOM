module Core {
    requires java.desktop;
    requires fps;

    requires spring.core;
    requires spring.context;
    requires spring.beans;

    opens dk.sdu.mmmi.cbse.main to spring.core;
    exports dk.sdu.mmmi.cbse.main;

    requires Common;
    requires CommonAsteroid;
    requires CommonBullet;

    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IPostEntityProcessingService;
}