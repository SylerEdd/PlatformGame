package github.sd2.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import github.sd2.Main;
import com.badlogic.gdx.scenes.scene2d.ui.Label;;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private int worldTimer;
    private float timeCount;
    private int score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label onePieceLabel;

    public Hud(SpriteBatch spriteBatch) {

        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        onePieceLabel = new Label("Pirate", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(onePieceLabel).expandX().padTop(2);
        table.add(worldLabel).expandX().padTop(2);
        table.add(timeLabel).expandX().padTop(2);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);


    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
