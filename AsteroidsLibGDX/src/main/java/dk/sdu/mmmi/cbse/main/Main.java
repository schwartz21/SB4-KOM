package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {

		Lwjgl3ApplicationConfiguration cfg =
			new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		cfg.setResizable(false);
//		cfg.width = 500;
//		cfg.height = 400;
//		cfg.useGL30 = false;
//		cfg.resizable = false;
		
		new Lwjgl3Application(new Game(), cfg);
		
	}
	
}
