package com.dbcgames.alwk.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dbcgames.alwk.ALWK;

public class DesktopLauncher {
    
    public static void main (String[] arg) {
	ALWK appy = new ALWK();
	appy.setMap(new DesktopMap());
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	new LwjglApplication(appy, config);
    }
}
