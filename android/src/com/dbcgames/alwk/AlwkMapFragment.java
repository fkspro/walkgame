package com.dbcgames.alwk;

import com.badlogic.gdx.Gdx;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
    
public class AlwkMapFragment extends SupportMapFragment
    implements MapFacade, OnMapReadyCallback {
    Boolean gotmap = false;
    GoogleMap gmap;
    AndroidWherat wherat;

    // XXX do this better
    public Double getLat() { 
	return(wherat.getlat());
    }
    public Double getLong() { 
	return(wherat.getlong());
    }


    @Override
    public void onStart() {
	// XXX the builder in AndroidWherat needs the activity to be working, putting
	// it here seems to ensure that.  Don't really need it to exist until here.
	if(null == wherat) {
	    wherat = new AndroidWherat(getActivity());
	}
	wherat.connect();
	getMapAsync(this);
	super.onStart();
	Gdx.app.log("alwk", "mapfrag onstart");
    }

    @Override
    public void onStop() {
	wherat.disconnect();
	super.onStop();
	Gdx.app.log("alwk", "mapfrag onstop");
    }

    @Override
    public void onMapReady(GoogleMap map) {
	gmap = map;
	gotmap = true;
	// may not have lat/long yet
	// gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 15.5f));
// 	gmap.addMarker(new MarkerOptions()
// 		       .position(new LatLng(latitude, longitude))
// 		       .title("U R HERE!"));
    }


    // test function, just print to stdout
    public void testy() {
	if(gotmap && wherat.gotloc()) {
	    Gdx.app.log("alwk", "ayyylmao, location: "
			+ String.valueOf(wherat.getlat()) + ":"
			+ String.valueOf(wherat.getlong()));

	    getActivity().runOnUiThread(new Runnable() {
	    	    @Override
	    	    public void run() {
			gmap.moveCamera(CameraUpdateFactory
					.newLatLngZoom(new LatLng(wherat.getlat(), wherat.getlong()), 19f));
			//	    		Gdx.app.log("alwk", "ran gmap from runnable on gl thread");
	    	    }
	    	});

	    wherat.locdone();
	}
    }


}
