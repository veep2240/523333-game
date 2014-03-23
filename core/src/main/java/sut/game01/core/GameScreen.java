package sut.game01.core;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.MathUtils;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.joints.*;
import playn.core.*;
import playn.core.util.Clock;
import sut.game01.sprite.*;
import tripleplay.game.ScreenStack;
import tripleplay.game.Screen;
import org.jbox2d.common.Vec2;
import sut.game01.sprite.go;
import sut.game01.core.DebugDrawBox2D;


import playn.core.util.Callback;
import react.UnitSlot;
import sut.game01.sprite.zealot;
import tripleplay.game.ScreenStack;
import tripleplay.game.Screen;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import playn.core.Pointer;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.MathUtils.*;
import sut.game01.sprite.dragon1;
import sut.game01.sprite.firemon1;

import java.util.ArrayList;

import static playn.core.PlayN.*;

/**
 * Created by rst706 on 2/4/14.
 */
public class GameScreen extends Screen {

    public static float M_PER_PIXEL =1 / 26.666667f;

    private static int width =24;
    private static int height = 18;
    private ScreenStack ss;
    private Body ground ;


    private DebugDrawBox2D debugDraw;
    private World world;
    private boolean showDebugDraw = true;
    private Body body1,body,body4;
    private Body body2;
    private zealot z ;
    private car c;
    private diver d;
    public dragon1 dra1;
    public firemon1 fm1;
    private fire f;
    private int a=0,time=0;
    public float xm,ym;
    private RevoluteJoint joint3;
    private LineJoint joint;
    private RevoluteJointDef jd3 = new RevoluteJointDef();
    private RevoluteJointDef jd4 = new RevoluteJointDef();
    private RevoluteJointDef jd5 = new RevoluteJointDef();
    private RevoluteJointDef jd6 = new RevoluteJointDef();
    private LineJointDef jd2 = new LineJointDef();
    public static boolean firebox = true;
    public static boolean firexy = true;
    public static float fireX,fireY,angle;

    ArrayList<fire> fireArrayList = new ArrayList<fire>();
    ArrayList<firemon1> firemon1ArrayList = new ArrayList<firemon1>();

    public GameScreen(ScreenStack ss) {
        this.ss = ss;
    }

    @Override
    public void wasAdded() {
        super.wasAdded();
        Image bgImage = assets().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        layer.add(bgLayer);

        Vec2 gravity = new Vec2(0.0f,10.0f);
        world = new World(gravity, true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                for (fire f : fireArrayList){

                    if (contact.getFixtureB().getBody()==f.getbody()&&contact.getFixtureA().getBody()==dra1.getbody()){
                        dra1.contact(contact);
                        dra1.HP = dra1.HP -50;
                        f.closebody();


                    }
                }
                if(contact.getFixtureB().getBody()==dra1.getbody()&&contact.getFixtureA().getBody()==ground){
                    dra1.closebody();

                }
                if (contact.getFixtureA().getBody()==body&&contact.getFixtureB().getBody()==d.getbody()){
                if (firebox==false){
                    if (firexy==true){
                    f = new fire(world,90f,310f);
                    fireArrayList.add(f);
                    layer.add(f.layer());
                    firexy=false;
                    }
                }
//



                }
                for (firemon1 fi1 : firemon1ArrayList){
                    if (contact.getFixtureA().getBody()==fi1.getbody()&&contact.getFixtureB().getBody()==c.getbody()){
                        car.HP = car.HP - 100;
                        fi1.closebody();

                    }
                    if (contact.getFixtureB().getBody()==fi1.getbody()){
                        fi1.closebody();

                    }
                    if (contact.getFixtureA().getBody()==fi1.getbody()){
                        fi1.closebody();

                    }


                }

                if (contact.getFixtureA().getBody()==c.getbody()&&contact.getFixtureB().getBody()==d.getbody()){

                        firebox=true;
                        firexy=true;


                }
                if (contact.getFixtureA().getBody()==d.getbody()&&contact.getFixtureB().getBody()==c.getbody()){

                    firebox=true;
                    firexy=true;


                }


            }

            @Override
            public void endContact(Contact contact) {
                for (fire f : fireArrayList){


                if (contact.getFixtureA().getBody()==f.getbody()||contact.getFixtureB().getBody()==f.getbody()){
//                    f.closebody();

                }
                }
//                System.out.println(contact.getFixtureA().getBody());
//                System.out.println(contact.getFixtureB().getBody());


            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        });


        if(showDebugDraw){
            CanvasImage image = graphics().createImage((int) (width / GameScreen.M_PER_PIXEL), (int) (height / GameScreen.M_PER_PIXEL));
            layer.add(graphics().createImageLayer(image));
            debugDraw = new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit| DebugDraw.e_jointBit|DebugDraw.e_aabbBit);
            debugDraw.setCamera(0, 0, 1f / GameScreen.M_PER_PIXEL);
            world.setDebugDraw(debugDraw);

        }
        ground = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(0.3f, height-2),new Vec2(width+10,height-2));
        PolygonShape groundShape1 = new PolygonShape();
        groundShape1.setAsEdge(new Vec2(0.3f, height-2),new Vec2(width-23.68f,height-5));
        ground.createFixture(groundShape1, 0.0f);
        ground.createFixture(groundShape, 0.0f);

        Image bg1Image = assets().getImage("images/back.png");
        ImageLayer backLayer = graphics().createImageLayer(bg1Image);


        backLayer.setSize(150,99);
        backLayer.setOrigin(30, 30);
        backLayer.setTranslation(300, 99);
        layer.add(backLayer);
        backLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(ss.top());


            }
        });
        createBox();
       createBox2();
        createBox3();
        createBox4();

//        z = new zealot(world,100f,100f);
        c = new car(world,80f,400f);
        d = new diver(world,80f,355f);
        dra1 = new dragon1(world,750,200);
        layer.add(c.layer());
        layer.add(d.layer());
        layer.add(dra1.layer());

//        layer.add(z.layer());







        keyboard().setListener(new Keyboard.Adapter(){
            @Override
            public void onKeyDown(Keyboard.Event event) {
                super.onKeyDown(event);
                switch (event.key()){
                    case A:
                        z.vee();
                        break;
                }

            }
        });
        d.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);

//               d.rdiver();
//                body1.applyLinearImpulse(new Vec2(0f, -1.5f), body1.getPosition());


            }
        });

        pointer().setListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {



            }

            @Override
            public void onPointerStart(Pointer.Event event) {
                super.onPointerStart(event);
                if (firebox==true){
                Vec2 delta = new Vec2(event.x()-90,event.y()-310);
                angle = MathUtils.atan2(delta.y, delta.x);


                body1.applyLinearImpulse(new Vec2(0f, -1.5f), body1.getPosition());
                firebox = false;}
            }

        });

    }
    private void createBox(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body2 = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(0.1f,1.3f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body2.createFixture(fd);
        body2.setLinearDamping(0.5f);
        body2.setTransform(new Vec2(60f* GameScreen.M_PER_PIXEL ,330f * GameScreen.M_PER_PIXEL ), 0);



    }
    private void createBox4(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body4 = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(0.1f,0.1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body4.createFixture(fd);
        body4.setLinearDamping(0.5f);
        body4.setTransform(new Vec2(80f* GameScreen.M_PER_PIXEL ,395f * GameScreen.M_PER_PIXEL ), 0);}

    private void createBox3(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(0.1f,1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(90 * GameScreen.M_PER_PIXEL, 350f * GameScreen.M_PER_PIXEL), 0);



    }

    private void createBox2(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);

        body1 = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(0.1f,0.1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 0f;
        body1.createFixture(fd);
        body1.setLinearDamping(0.5f);
        body1.setTransform(new Vec2(80f* GameScreen.M_PER_PIXEL ,355f * GameScreen.M_PER_PIXEL), 0);





    }

    @Override
    public void update(int delta) {
        if(c.getHasLoaded()==true){
            if (a==0)  {
                jd4.initialize(body2, body, body2.getWorldCenter());
                jd4.enableLimit = true;
                jd4.enableMotor = true;
                jd4.maxMotorTorque = 1000.0f;
//                jd2.maxMotorForce = 20.f;
                jd4.motorSpeed = 1.0f;
                jd4.localAnchorA = new Vec2(0f,0f);
                jd4.localAnchorB = new Vec2(-0.3f,3f);
                world.createJoint(jd4);

                jd5.initialize(c.getbody(), body4, c.body.getWorldCenter());
                jd5.enableLimit = true;
                jd5.enableMotor = true;
                jd5.maxMotorTorque = 1000.0f;
//                jd2.maxMotorForce = 20.f;
                jd5.motorSpeed = 1.0f;
                jd5.localAnchorA = new Vec2(-0.2f,0f);
                jd5.localAnchorB = new Vec2(0f,0.3f);
                world.createJoint(jd5);

                jd5.initialize(body4, body2, body4.getWorldCenter());
                jd5.enableLimit = true;
                jd5.enableMotor = true;
                jd5.maxMotorTorque = 1000.0f;
//                jd2.maxMotorForce = 20.f;
                jd5.motorSpeed = 1.0f;
                jd5.localAnchorA = new Vec2(-0.5f,0f);
                jd5.localAnchorB = new Vec2(-0.5f,0f);
                world.createJoint(jd5);

                jd3.initialize(c.getbody(), body2, c.body.getWorldCenter());
                jd3.enableLimit = true;
                jd3.enableMotor = true;
                jd3.maxMotorTorque = 1000.0f;
//                jd2.maxMotorForce = 20.f;
                jd3.motorSpeed = 1.0f;
                jd3.localAnchorA = new Vec2(-0.2f,0);
                jd3.localAnchorB = new Vec2(0f,0.7f);
                joint3 = (RevoluteJoint) world.createJoint(jd3);





                LineJointDef joint_def = new LineJointDef();
                joint_def.bodyA = d.getbody();
                joint_def.bodyB = body2;
                joint_def.enableLimit=true;
                joint_def.enableMotor=true;
                joint_def.maxMotorForce = 00.0f;
                joint_def.motorSpeed =0.0f;
                //connect the centers - center in local coordinate - relative to body is 0,0
                joint_def.localAnchorA.addLocal(new Vec2(0f, 0));
                joint_def.localAnchorB.addLocal(new Vec2(0f, -1.05f));


                //add the joint to the world
                world.createJoint(joint_def);

                RevoluteJointDef joint_def1 = new RevoluteJointDef();
                joint_def1.bodyA = body1;
                joint_def1.bodyB = d.getbody();
                joint_def1.enableLimit=true;
                joint_def1.enableMotor=true;
                joint_def1.maxMotorTorque = 1000.0f;
                joint_def1.motorSpeed =1.0f;
                //connect the centers - center in local coordinate - relative to body is 0,0
                joint_def1.localAnchorA = new Vec2(0, 0);
                joint_def1.localAnchorB = new Vec2(-1.8f, 0f);

                //add the joint to the world
                world.createJoint(joint_def1);



                a++;

            }
            super.update(delta);



       world.step(0.033f,10,10);
//        z.update(delta);
        c.update(delta);
        d.update(delta);
            dra1.update(delta);
        for (fire f : fireArrayList){
                f.update(delta);
            }
            time+=delta;
            if (dra1.HP>0){
            if (time>=1000){
                xm = dra1.getbody().getPosition().x;
                ym = dra1.getbody().getPosition().y;
                fm1 = new firemon1(world,(xm/GameScreen.M_PER_PIXEL)-100,(ym/GameScreen.M_PER_PIXEL)+100);
                firemon1ArrayList.add(fm1);
                layer.add(fm1.layer());
                time=0;
            }



            }
            for (firemon1 fi1 : firemon1ArrayList){

                fi1.update(delta);
            }



        }


    }




    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        if (showDebugDraw){
        debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
//        z.paint(clock);
        c.paint(clock);
        d.paint(clock);
        dra1.paint(clock);
        for (fire f : fireArrayList){
            f.paint(clock);
        }
        for (firemon1 fi1 : firemon1ArrayList){
            fi1.paint(clock);
        }

    }
    public float getangle(){

        return angle;
    }



}
