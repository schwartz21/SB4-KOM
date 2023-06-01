package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class PostEntityProcessingService {
    private static PostEntityProcessingService service;
    private ServiceLoader<IPostEntityProcessingService> loader;

    private PostEntityProcessingService(){
        loader = ServiceLoader.load(IPostEntityProcessingService.class);
    }

    public static synchronized PostEntityProcessingService getInstance(){
        if (service == null){
            service = new PostEntityProcessingService();
        }

        return service;
    }

    public void processAll(GameData gameData, World world){
        try{
            for (IPostEntityProcessingService service : loader) {
                service.process(gameData, world);
            }
        }catch (ServiceConfigurationError serviceConfigurationError){
            serviceConfigurationError.printStackTrace();
        }
    }

    public List<IPostEntityProcessingService> getAll(){
        List<IPostEntityProcessingService> out = new ArrayList<>();
        for (IPostEntityProcessingService service : loader) {
            out.add(service);
        }

        return out;
    }
}
