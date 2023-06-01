package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.EntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.GamePluginService;
import dk.sdu.mmmi.cbse.common.services.PostEntityProcessingService;
import org.springframework.context.annotation.Bean;

public class SpringConfigurator {

    @Bean
    public Game gameCreator(){
        return new Game(GamePluginService.getInstance().getAll(), EntityProcessingService.getInstance().getAll(), PostEntityProcessingService.getInstance().getAll());
    }
}
