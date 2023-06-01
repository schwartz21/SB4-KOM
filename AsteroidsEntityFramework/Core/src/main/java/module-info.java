module Core {
    exports dk.sdu.mmmi.cbse.main;

    requires Common;
    requires fps;
    requires spring.core;
    requires spring.context;
    requires spring.beans;

    opens dk.sdu.mmmi.cbse.main to spring.core;

    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IPostEntityProcessingService;
}