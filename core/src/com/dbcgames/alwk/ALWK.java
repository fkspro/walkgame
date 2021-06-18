package com.dbcgames.alwk;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ALWK extends ApplicationAdapter {
    MapFacade mappy;
    SpriteBatch batch;
    Stage stage;
    Image dude;
    Image coin;
    Group overlay;
    BitmapFont font;

    public ALWK(MapFacade m) {
	this.mappy = m;
    }

    @Override
    public void create () {
	batch = new SpriteBatch();
	stage = new Stage(new ScreenViewport());
	Gdx.input.setInputProcessor(stage);
	dude = new Image(new Texture("prospector.png"));
	dude.setScale(0.25f);
	dude.setPosition((Gdx.graphics.getWidth() - dude.getWidth() / 4f) / 2f,
			 (Gdx.graphics.getHeight() - dude.getHeight() / 4f) / 2f);
	stage.addActor(dude);
	
	coin = new Image(new Texture("gold-coin-icon.jpg"));
	coin.setScale(0.25f);
	coin.setDebug(true);
	//	coin.setPosition(300f, 300f);
	//	stage.addActor(coin);
	overlay = new Group();
	overlay.setDebug(true);
	overlay.setPosition(200f, 200f);
	overlay.addActor(coin);
	overlay.setColor(0f, 1f, 0f, 1f);
	stage.addActor(overlay);

	font = new BitmapFont();
	font.setColor(Color.BLACK);
	font.getData().setScale(8f);
    }

    @Override
    public void render () {
	Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	stage.act();
	stage.draw();

	batch.begin();
	//	dude.draw(batch, 1f);
	font.draw(batch, String.valueOf(mappy.getLat()) + " : "
		  + String.valueOf(mappy.getLong()),
		  10f, 2000f);
	batch.end();

	mappy.testy();
    }

    public void resize (int width, int height) {
	stage.getViewport().update(width, height, true);
    }

    public void dispose() {
	stage.dispose();
	font.dispose();
    }
}
