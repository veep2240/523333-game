package sut.game01.sprite;

import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.Layer;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.GameScreen;

/**
 * Created by rst706 on 3/5/14.
 */
public class diver {
    public Sprite sprite;
    public int spriteIndex = 0;
    public boolean hasLoaded = false;
    public boolean contacted;
    public int contactCheck ;
    public Body other;
    public MassData massD;



    public enum State{IDLE,RUN,ATTK,DIE,DEAD};

    private State state = State.IDLE;
    private int e=0;
    private int time;
    private int hp=100;
    private int offset = 0;
    private Body body;

    public diver(final World world, final float x, final float y){
        this.sprite = SpriteLoader.getSprite("images/diver.json");

        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {

                sprite.layer().setSize(60f,10f);

                sprite.layer().setOrigin(47, 10 / 2f);
                sprite.setSprite(spriteIndex);
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
        sprite.layer().setRotation(body.getAngle());
//        float a= 0.6f;
//        float i;
//        for(i=0f;i<a;i=i+0.1f){
//            body.setTransform(body.getPosition(),i);}


    }

    private Body initPhysicsBody(World world, float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        Transform fx = new Transform();
        fx.position.set(100f,100f);
        shape.centroid(fx);

        shape.setAsBox(sprite.layer().width() * GameScreen.M_PER_PIXEL / 2, sprite.layer().height() * GameScreen.M_PER_PIXEL / 2, new Vec2(-0.7f, 0.0F), 0.0F);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0f;
        body.createFixture(fixtureDef);
        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x, y), 0f);



//        MassData md = body.getMassData();
//        massD.center.set(2f, 0); body.setMassData(massD);


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
        if (contact.getFixtureA().getBody() == body){

            other = contact.getFixtureB().getBody();
        }else {

            other = contact.getFixtureA().getBody();
        }


    }

    public void vee(){



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
    public void rdiver(){
//        float a= 0.6f;
//        float i;
//        for(i=0f;i<a;i=i+0.001f){
//        body.setTransform(body.getPosition(),i);}
        body.applyLinearImpulse(new Vec2(0f, 5f), body.getPosition());




    }
}