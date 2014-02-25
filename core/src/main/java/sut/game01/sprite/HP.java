package sut.game01.sprite;

import playn.core.Layer;
import playn.core.Pointer;
import playn.core.util.Callback;
/**
 * Created by rst706 on 1/29/14.
 */
public class HP {
    public Sprite sprite;
    public int spriteIndex = 0;
    public boolean hasLoaded = false;

    public enum State{HP};

    private State state = State.HP;
    private int e=0;
    private int offset = 0;

    public HP(final float x, final float y){
        this.sprite = SpriteLoader.getSprite("images/hp.json");

        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f, sprite.height()/2f);
                sprite.layer().setTranslation(x, y);
                hasLoaded = true;
            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });

        sprite.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                state = State.HP;
                spriteIndex = -1;
                e = 0;
            }
        });

    }
    public void update(int delta){
        if(!hasLoaded) return;
        e +=delta;

        if(e > 150){
            switch (state){
                case HP: offset =0;
                    break;


            }
            spriteIndex = offset + ((spriteIndex + 1) % 3);
            sprite.setSprite(spriteIndex);
            e =0;
        }
    }

    public Layer layer(){



        return sprite.layer();
    }

}
