package com.dbcgames.alwk;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

// XXX create an interface for this

public class AndroidWherat
    implements GoogleApiClient.ConnectionCallbacks,
	       GoogleApiClient.OnConnectionFailedListener,
	       LocationListener {
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Double latitude = 0d, longitude = 0d;
    Boolean gotloc = false;

    public AndroidWherat(Context ctx) {
	mGoogleApiClient = new GoogleApiClient.Builder(ctx)
	    .addConnectionCallbacks(this)
	    .addOnConnectionFailedListener(this)
	    .addApi(LocationServices.API)
	    .build();
    }

    // methods
    public void connect() {
	mGoogleApiClient.connect();
    }

    public void disconnect() {
	mGoogleApiClient.disconnect();
    }

    public Boolean gotloc() {
	return(gotloc);
    }

    public Double getlat() {
	return(latitude);
    }

    public Double getlong() {
	return(longitude);
    }

    public void locdone() {
	gotloc = false;
    }

    // callbacks
    @Override
    public void onConnected (Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
	    gotloc = true;

	    // streaming location requests
	    LocationRequest request = LocationRequest.create();
	    request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	    request.setInterval(5);
	    request.setFastestInterval(1);
	    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
								     request, this);

	    Gdx.app.log("alwk", "gclient connect callback, set lat/long");
        }
    }

    @Override
    public void onConnectionSuspended (int number) {
	// XXX do something here
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
	// An unresolvable error has occurred and a connection to Google APIs
	// could not be established. Display an error message, or handle
	// the failure silently
    }

    // callback for location updates
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
	latitude = mLastLocation.getLatitude();
	longitude = mLastLocation.getLongitude();
	gotloc = true;
    }

}
