package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext(SpringConfigurator.class);

		Lwjgl3ApplicationConfiguration cfg =
				new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		cfg.setResizable(false);

		cfg.setWindowedMode(1000, 900);

		new Lwjgl3Application(springContext.getBean(Game.class), cfg);
	}
}
