package sut.game01.sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.Layer;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.GameScreen;

/**
 * Created by rst706 on 3/21/14.
 */
public class carshop {
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

    public carshop(final float x, final float y){
        this.sprite = SpriteLoader.getSprite("images/car.json");

        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {

                sprite.layer().setSize(110f,81f);
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(78, 100/2f);

                sprite.layer().setTranslation(x, y);

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


    public Layer layer(){



        return sprite.layer();
    }
    public Boolean getHasLoaded(){

        return hasLoaded;
    }

    public void paint(Clock clock) {
        if (!hasLoaded)
            return;



    }
    public Body getbody(){
        return this.body;
    }
}

