package com.dbcgames.alwk.desktop;

import com.badlogic.gdx.Gdx;
import com.dbcgames.alwk.MapFacade;

public class DesktopMap implements MapFacade {

    Boolean didtesty = false;

    // test function, just print to stdout
    public void testy() {
	if(!didtesty) {
	    Gdx.app.log("alwk", "ayyylmao");
	    didtesty = true;
	}
    }

}

