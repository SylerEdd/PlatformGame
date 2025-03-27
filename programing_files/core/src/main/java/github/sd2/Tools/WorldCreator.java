package github.sd2.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.maps.tiled.TiledMap;
import github.sd2.Main;

public class WorldCreator {

    public WorldCreator(World world, TiledMap map) {

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //create ground bodies and fixtures
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX()+ rect.getWidth() /2)/ Main.PPM , (rect.getY()+rect.getHeight()/2)/ Main.PPM );

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth()/2/ Main.PPM, rect.getHeight()/2/ Main.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }



    }

}
