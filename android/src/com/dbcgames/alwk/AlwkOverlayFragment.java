package com.dbcgames.alwk;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.Gdx;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

public class AlwkOverlayFragment extends AndroidFragmentApplication {
    ALWK appy;

    // Single empty constructor, required for fragments
    public AlwkOverlayFragment() { }

    // Use this to make one with mapfragment linked
    // XXX this may cause problems, check that entire android app comes back up right in all scenarios
    static public AlwkOverlayFragment create(AlwkMapFragment m) {
	AlwkOverlayFragment f = new AlwkOverlayFragment();
	f.appy = new ALWK(m);
	return(f);
    }

    // public void init(SupportMapFragment mapfrag) {
    // 	mappy = new AndroidMap(this);
    // 	mappy.GoogleApiConnect();
    // 	mapfrag.getMapAsync(mappy);
    // 	appy.setMap(mappy);
    // }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
	cfg.r = cfg.g = cfg.b = cfg.a = 8;
	View view = initializeForView(appy, cfg);

	if (view instanceof SurfaceView) {
	    SurfaceView glView = (SurfaceView) view;
	    glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
	    glView.setZOrderOnTop(true);
	}

	return view;
    }

    @Override
    public void onStart() {
	// if(null != mappy) {
	//     mappy.GoogleApiConnect();
	// }
	super.onStart();
	Gdx.app.log("alwk", "alwkfrag onstart");
    }

    @Override
    public void onStop() {
	// if(null != mappy) {
	//     mappy.GoogleApiDisconnect();
	// }
	super.onStop();
	Gdx.app.log("alwk", "alwkfrag onstop");
    }

}
