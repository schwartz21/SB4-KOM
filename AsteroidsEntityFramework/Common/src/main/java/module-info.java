module Common {
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IPostEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;

    exports dk.sdu.mmmi.cbse.common.data;
    exports dk.sdu.mmmi.cbse.common.data.entityparts;
    exports dk.sdu.mmmi.cbse.common.events;
    exports dk.sdu.mmmi.cbse.common.serviceInterfaces;
    exports dk.sdu.mmmi.cbse.common.services;
}