package github.sd2.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import github.sd2.Main;
import github.sd2.Scenes.Hud;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import github.sd2.Sprites.Characters;
import github.sd2.Tools.WorldCreator;

public class PlayScreen implements Screen {

    private Main game;

    //HUD variables
    private OrthographicCamera gameCamera;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer box2dr;

    private Characters player;

    public PlayScreen(Main game) {
        this.game = game;

        //create a camera used to follow the character through the world
        gameCamera = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(Main.V_WIDTH / Main.PPM, Main.V_HEIGHT / Main.PPM, gameCamera);

        //create a HUD for game scores and other infos
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tutorialMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ Main.PPM);

        // initially set our gameCamera to be centered correctly at the start of the world
        gameCamera.position.set(gamePort.getWorldWidth()/2 , gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10/ Main.PPM), true);
        box2dr = new Box2DDebugRenderer();

        new WorldCreator(world, map);

        player = new Characters(world);

    }

    @Override
    public void show() {

    }

    public void handleInput(float delta){

        //Create a Input Key handler to go right, left and jump also where you can change the speed
        if(Gdx.input.isKeyJustPressed(Input.Keys.W))
            player.box2dBody.applyLinearImpulse(new Vector2(0, 2f), player.box2dBody.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.box2dBody.getLinearVelocity().x<= 2)
            player.box2dBody.applyLinearImpulse(new Vector2(0.3f, 0), player.box2dBody.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.box2dBody.getLinearVelocity().x>= -2)
            player.box2dBody.applyLinearImpulse(new Vector2(-0.3f, 0), player.box2dBody.getWorldCenter(), true);

    }

    public void update(float delta) {

        //handle user input first
        handleInput(delta);

        world.step(1/60f, 6, 2);

        //handling gameCamera to follow the character
        gameCamera.position.x = player.box2dBody.getPosition().x;
        gameCamera.position.y = player.box2dBody.getPosition().y;

        //update our gameCamera with correct coordinates after changes
        gameCamera.update();
        // tell our renderer to draw only what our camera can see in our game world.
        renderer.setView(gameCamera);

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        box2dr.render(world, gameCamera.combined);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        map.dispose();
        renderer.dispose();
        box2dr.dispose();
        world.dispose();
        hud.dispose();

    }
}
