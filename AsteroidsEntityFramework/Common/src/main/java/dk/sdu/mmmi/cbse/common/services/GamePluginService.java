package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class GamePluginService {
    private static GamePluginService service;
    private ServiceLoader<IGamePluginService> loader;

    private GamePluginService(){
        loader = ServiceLoader.load(IGamePluginService.class);
    }

    public static synchronized GamePluginService getInstance(){
        if (service == null){
            service = new GamePluginService();
        }

        return service;
    }

    public void startAll(GameData gameData, World world){
        try{
            for (IGamePluginService service : loader) {
                service.start(gameData, world);
            }
        }catch (ServiceConfigurationError serviceConfigurationError){
            serviceConfigurationError.printStackTrace();
        }
    }

    public void stopAll(GameData gameData, World world){
        try{
            for (IGamePluginService service : loader) {
                service.stop(gameData, world);
            }
        }catch (ServiceConfigurationError serviceConfigurationError){
            serviceConfigurationError.printStackTrace();
        }
    }

    public List<IGamePluginService> getAll(){
        List<IGamePluginService> out = new ArrayList<>();
        for (IGamePluginService service : loader) {
            out.add(service);
        }

        return out;
    }
}
