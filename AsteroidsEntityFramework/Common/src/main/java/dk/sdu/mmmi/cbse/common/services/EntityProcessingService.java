package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

@SuppressWarnings("FieldCanBeLocal")
public class EntityProcessingService {
    private static EntityProcessingService service;
    private final ServiceLoader<IEntityProcessingService> loader;
    private final List<IEntityProcessingService> services;

    private EntityProcessingService(){
        loader = ServiceLoader.load(IEntityProcessingService.class);
        services = new ArrayList<>();

        try{
            for (IEntityProcessingService service : loader) {
                services.add(service);
            }
        }catch (ServiceConfigurationError serviceConfigurationError){
            serviceConfigurationError.printStackTrace();
        }
    }

    public static synchronized EntityProcessingService getInstance(){
        if (service == null){
            service = new EntityProcessingService();
        }

        return service;
    }

    public void processAll(GameData gameData, World world){
        for (IEntityProcessingService service : services) {
            service.process(gameData, world);
        }
    }
    public List<IEntityProcessingService> getAll(){
        return new ArrayList<>(services);
    }
}
