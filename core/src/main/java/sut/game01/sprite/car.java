package sut.game01.sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import playn.core.Layer;
import playn.core.Pointer;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.GameScreen;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.MathUtils.*;
/**
 * Created by rst706 on 3/4/14.
 */
public class car  {
    public Sprite sprite;
    public int spriteIndex = 0;
    public boolean hasLoaded = false;
    public boolean contacted;
    public int contactCheck ;
    public Body other;



    public enum State{IDLE,RUN,ATTK,DIE,DEAD};

    private State state = State.IDLE;
    private int e=0;
    private int time;
    private int hp=100;
    private int offset = 0;
    public Body body;

    public car(final World world, final float x, final float y){
        this.sprite = SpriteLoader.getSprite("images/car.json");

        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {

                sprite.layer().setSize(110f,81f);
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(78, 100/2f);

                sprite.layer().setTranslation(x, y);





                body = initPhysicsBody(world, GameScreen.M_PER_PIXEL * x, GameScreen.M_PER_PIXEL * y);
                hasLoaded = true;

            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });


    }
    public void update(int delta){
        if(!hasLoaded) return;
        e +=delta;
        time +=delta;
        sprite.layer().setRotation(body.getAngle());




        if(e > 30){
            switch (state){
                case IDLE:
                    offset =0;
                    spriteIndex = offset + ((spriteIndex + 1) % 12);
                    break;


            }

            sprite.setSprite(spriteIndex);
            e =0;
        }
    }

    private Body initPhysicsBody(World world, float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(90 * GameScreen.M_PER_PIXEL / 2 , 50 * GameScreen.M_PER_PIXEL/2 , new Vec2(-0.9f, 0.0F), 0.0F);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0f;
        body.createFixture(fixtureDef);

        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);

        return body;

    }

    public Layer layer(){



        return sprite.layer();
    }
    public Boolean getHasLoaded(){

        return hasLoaded;
    }
    public void contact(Contact contact){
        contacted = true;
        contactCheck = 0;

        if (state == State.IDLE){
            state = State.ATTK;


            body.applyLinearImpulse(new Vec2(0f, -100f), body.getPosition());


    }
    }
 public void paint(Clock clock) {
    if (!hasLoaded)
        return;

    sprite.layer().setTranslation((body.getPosition().x/GameScreen.M_PER_PIXEL),
            (body.getPosition().y/GameScreen.M_PER_PIXEL));

}
    public Body getbody(){
        return this.body;
    }
}