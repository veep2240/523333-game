package sut.game01.sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.Layer;
import playn.core.Pointer;
import playn.core.util.Callback;
import sut.game01.core.GameScreen;

/**
 * Created by rst706 on 2/5/14.
 */
public class go {
    public Sprite sprite;
    public int spriteIndex = 0;
    public boolean hasLoaded = false;
    private Body body;




    public enum State{IDLE,RUN,ATTK,DIE,DEAD};

    private State state = State.IDLE;
    private int e=0;
    private int time;
    private int hp=100;
    private int offset = 0;

    public go(final World world, final float x_px, final float y_px){
        this.sprite = SpriteLoader.getSprite("images/zealot.json");
        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f, sprite.height()/2f);
                sprite.layer().setTranslation(x_px, y_px);

                body = initPhysicsBody(world, GameScreen.M_PER_PIXEL * x_px, GameScreen.M_PER_PIXEL * y_px);

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

    private Body initPhysicsBody(World world,float x, float y){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        Body body = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(1f,1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 0f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(16f, 14f), 0);
        return body;
    }

    public void update(int delta){
        if(!hasLoaded) return;
        e +=delta;
        time +=delta;
        if(hp==100 && time>=1800){
            hp =50;
        }
        if(hp==50 && time>=2250){
            hp=0;
        }




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

    public Layer layer(){



        return sprite.layer();
    }


}

