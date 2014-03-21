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
 * Created by rst706 on 3/21/14.
 */
public class divershop1 {public Sprite sprite;
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

    public divershop1(final float x, final float y){
        this.sprite = SpriteLoader.getSprite("images/diver.json");

        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {

                sprite.layer().setSize(60f,10f);

                sprite.layer().setOrigin(47, 10 / 2f);
                sprite.setSprite(spriteIndex);
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


}