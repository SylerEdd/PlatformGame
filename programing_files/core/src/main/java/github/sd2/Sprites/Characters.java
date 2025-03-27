package github.sd2.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import github.sd2.Main;

public class Characters extends Sprite {

    public World world;
    public Body box2dBody;

    public Characters(World world) {

        this.world = world;
        defineCharacter();

    }

    public void defineCharacter() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32/ Main.PPM, 50/ Main.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        box2dBody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ Main.PPM);

        fixtureDef.shape = shape;
        box2dBody.createFixture(fixtureDef);


    }

}
