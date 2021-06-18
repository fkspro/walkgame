package com.dbcgames.alwk;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
    
public class AndroidLauncher extends FragmentActivity
    implements AndroidFragmentApplication.Callbacks {
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
	// XXX supposed to use newInstance() static method but no one is sure why
	// also need this to be an AlwkMapFragment not SupportMapFragment
	//AlwkMapFragment mapfrag = AlwkMapFragment.newInstance();
	AlwkMapFragment mapfrag = new AlwkMapFragment();
	AlwkOverlayFragment overlayfrag = AlwkOverlayFragment.create(mapfrag);
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.test);

	// stick the two fragments into the active layout
	FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
	trans.replace(R.id.game, overlayfrag);
	trans.replace(R.id.mappy, mapfrag);
	trans.commit();

    }

    @Override
    public void exit() {}
    
}
