package sut.game01.sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.Layer;
import playn.core.Pointer;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.GameScreen;

/**
 * Created by all user on 28/1/2557.
 */


public class zealot {
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
    private Body body;

    public zealot(final World world,final float x, final float y){
       this.sprite = SpriteLoader.getSprite("images/zealot.json");
        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f, sprite.height()/2f);
                sprite.layer().setTranslation(x, y);

                body = initPhysicsBody(world,GameScreen.M_PER_PIXEL * x,GameScreen.M_PER_PIXEL * y);
                hasLoaded = true;
            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });

            sprite.layer().addListener(new Pointer.Adapter(){
                @Override
                public void onPointerEnd(Pointer.Event event) {
                    state = State.ATTK;
                    spriteIndex = -1;
                    e = 0;
                }
            });

    }
    public void update(int delta){
        if(!hasLoaded) return;
        e +=delta;
        time +=delta;





        if(e > 150){
            switch (state){
                case IDLE: offset =0;
                        if (hp==50){
                        state=State.RUN;
                    }
                    spriteIndex = offset + ((spriteIndex + 1) % 9);
                    break;
                case RUN:
                    offset=9;
                    if(spriteIndex == 16 && hp==0){
                        state = State.DIE;
                   }
                    spriteIndex = offset + ((spriteIndex + 1) % 8);
                    break;
                 case ATTK: offset=17;
                     if(spriteIndex == 23){
                         state = State.IDLE;

                     }
                     spriteIndex = offset + ((spriteIndex + 1) % 8);
                    break;
                case DIE: offset=25;
                    if(spriteIndex == 30){
                        state = State.DEAD;

                    }
                    spriteIndex = offset + ((spriteIndex + 1) % 6);
                    break;
                case DEAD: offset=31;

                    spriteIndex = offset + ((spriteIndex + 1) % 2);
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
    shape.setAsBox(56 * GameScreen.M_PER_PIXEL / 2 , sprite.layer().height() * GameScreen.M_PER_PIXEL/2);
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

        body.applyLinearImpulse(new Vec2(100f, 0f), body.getPosition());

    }

    public void paint(Clock clock) {
        if (!hasLoaded)
            return;

        sprite.layer().setTranslation((body.getPosition().x/GameScreen.M_PER_PIXEL),
                (body.getPosition().y/GameScreen.M_PER_PIXEL));
        body.applyTorque(200);
    }
    public Body getbody(){
        return this.body;
    }
}
