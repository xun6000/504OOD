package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.cmd.InteractCmd;
import edu.rice.comp504.model.cmd.MoveCmd;
import edu.rice.comp504.model.paintobj.*;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.NullStrategy;

import java.awt.*;
import java.util.Observable;

public class Gameboard extends Observable {
    private Point dims;
    private Boolean trigger;
    private APaintObject player;
    private int score;
    private int lifeNum;
    private int beanNum;
    private Boolean ifOver;
    private Boolean ifWin;

    public void SetWall(){
        Wall obj1= new Wall(new Point(3,3),new Point(2,4));
        Wall obj2=new Wall(new Point(7,3),new Point(2,4));
        Wall obj3=new Wall(new Point(3,9),new Point(2,6));
        Wall obj4=new Wall(new Point(3,17),new Point(4,2));
        Wall obj5=new Wall(new Point(3,21),new Point(2,6));
        Wall obj6=new Wall(new Point(3,29),new Point(7,2));
        Wall obj7=new Wall(new Point(3,33),new Point(3,9));
        Wall obj8=new Wall(new Point(3,44),new Point(5,3));
        Wall obj9=new Wall(new Point(8,33),new Point(2,9));
        Wall obj10=new Wall(new Point(10,44),new Point(3,3));


        Wall obj11=new Wall(new Point(15,44),new Point(4,3));
        Wall obj12=new Wall(new Point(13,13),new Point(6,2));
        Wall obj13=new Wall(new Point(14,21),new Point(5,2));
        Wall obj14=new Wall(new Point(11,25),new Point(8,2));
        Wall obj15=new Wall(new Point(20,9),new Point(4,2));
        Wall obj16=new Wall(new Point(21,13),new Point(3,5));
        Wall obj17=new Wall(new Point(21,31),new Point(3,4));
        Wall obj18=new Wall(new Point(26,37),new Point(3,3));
        Wall obj19=new Wall(new Point(31,37),new Point(3,3));


        Wall obj20=new Wall(new Point(31,42),new Point(4,5));
        Wall obj21=new Wall(new Point(36,37),new Point(7,3));
        Wall obj22=new Wall(new Point(36,25),new Point(5,4));
        Wall obj23=new Wall(new Point(45,31),new Point(2,13));
        Wall obj24=new Wall(new Point(31,17),new Point(3,6));
        Wall obj25=new Wall(new Point(26,9),new Point(5,2));
        Wall obj26=new Wall(new Point(33,3),new Point(4,8));



        Wall obj27=new Wall(new Point(0,0),new Point(1,50));
        Wall obj28=new Wall(new Point(1,0),new Point(19,1));
        Wall obj29=new Wall(new Point(7,9),new Point(4,6));
        Wall obj30=new Wall(new Point(7,21),new Point(2,6));
        Wall obj31=new Wall(new Point(9,21),new Point(3,2));
        Wall obj32=new Wall(new Point(11,9),new Point(7,2));
        Wall obj33=new Wall(new Point(11,3),new Point(1,4));
        Wall obj34=new Wall(new Point(12,3),new Point(5,1));
        Wall obj35=new Wall(new Point(12,29),new Point(2,9));
        Wall obj36=new Wall(new Point(14,29),new Point(5,2));
        Wall obj37=new Wall(new Point(16,33),new Point(3,7));
        Wall obj38=new Wall(new Point(12,40),new Point(7,2));
        Wall obj39=new Wall(new Point(21,37),new Point(3,12));
        Wall obj40=new Wall(new Point(19,0),new Point(5,7));
        Wall obj41=new Wall(new Point(14,6),new Point(5,1));
        Wall obj42=new Wall(new Point(26,0),new Point(5,7));
        Wall obj43=new Wall(new Point(31,0),new Point(19,1));
        Wall obj44=new Wall(new Point(40,1),new Point(2,2));

        Wall obj45=new Wall(new Point(21,20),new Point(1,9));
        //Wall obj45=new Wall(new Point(21,20),new Point(9,1));
        Wall obj46=new Wall(new Point(28,20),new Point(1,9));

        Wall obj47=new Wall(new Point(22,20),new Point(6,1));
        Wall obj48=new Wall(new Point(22,28),new Point(6,1));

        Wall obj49=new Wall(new Point(26,13),new Point(11,2));
        Wall obj50=new Wall(new Point(26,15),new Point(3,3));
        Wall obj51=new Wall(new Point(31,25),new Point(3,6));
        Wall obj52=new Wall(new Point(26,31),new Point(43-27+1,4));

        Wall obj53=new Wall(new Point(39,9),new Point(2,8));
        Wall obj54=new Wall(new Point(36,17),new Point(5,2));
        Wall obj55=new Wall(new Point(39,5),new Point(4,2));
        Wall obj56=new Wall(new Point(43,3),new Point(4,9));
        Wall obj57=new Wall(new Point(37,42),new Point(6,2));
        Wall obj58=new Wall(new Point(37,44),new Point(3,3));
        Wall obj59=new Wall(new Point(36,21),new Point(7,2));
        Wall obj60=new Wall(new Point(43,14),new Point(4,15));

        Wall obj61=new Wall(new Point(42,46),new Point(5,3));
        Wall obj62=new Wall(new Point(26,42),new Point(3,7));
        Wall obj63=new Wall(new Point(26,49),new Point(50-27+1,1));
        Wall obj64=new Wall(new Point(49,1),new Point(1,49));
        Wall obj65=new Wall(new Point(1,49),new Point(23,1));
        Wall obj66=new Wall(new Point(9,17),new Point(10,2));
        addObserver(obj1);
        addObserver(obj2);
        addObserver(obj3);
        addObserver(obj4);addObserver(obj5);addObserver(obj6);


        addObserver(obj7);
        addObserver(obj8);
        addObserver(obj9);addObserver(obj10);
        addObserver(obj11);addObserver(obj12);addObserver(obj13);addObserver(obj14);addObserver(obj15);
        addObserver(obj16);addObserver(obj17);addObserver(obj18);addObserver(obj19);addObserver(obj20);

        addObserver(obj20);
        addObserver(obj21);addObserver(obj22);addObserver(obj23);addObserver(obj24);addObserver(obj25);
        addObserver(obj26);addObserver(obj27);addObserver(obj28);addObserver(obj29);
        addObserver(obj30);
        addObserver(obj31);addObserver(obj32);addObserver(obj33);addObserver(obj34);addObserver(obj35);
        addObserver(obj36);addObserver(obj37);addObserver(obj38);addObserver(obj39);
        addObserver(obj40);
        addObserver(obj41);addObserver(obj42);addObserver(obj43);addObserver(obj44);addObserver(obj45);
        addObserver(obj46);addObserver(obj47);addObserver(obj48);addObserver(obj49);

        addObserver(obj50);
        addObserver(obj51);addObserver(obj52);addObserver(obj53);addObserver(obj54);addObserver(obj55);
        addObserver(obj56);addObserver(obj57);addObserver(obj58);addObserver(obj59);
        addObserver(obj60);
        addObserver(obj61);addObserver(obj62);addObserver(obj63);addObserver(obj64);
        addObserver(obj65);
        addObserver(obj66);
    }

    public void SetBean(){
        Bean b1=new Bean(new Point(2,2),5);
        Bean b2=new Bean(new Point(2,2),5);
        Bean b3=new Bean(new Point(2,2),5);
        Bean b4=new Bean(new Point(2,2),5);
        Bean b5=new Bean(new Point(2,2),5);
        Bean b6=new Bean(new Point(2,2),5);
        Bean b7=new Bean(new Point(2,2),5);
        Bean b8=new Bean(new Point(2,2),5);

        for(int i =2;i<48;i=i+2){
            Bean b9=new Bean(new Point(2,i),5);
            addObserver(b9);
        }
        for(int i =2;i<18;i=i+2){
            Bean b10=new Bean(new Point(i,2),5);
            addObserver(b10);
        }
        for(int i =4;i<18;i=i+2){
            Bean b10=new Bean(new Point(i,48),5);
            addObserver(b10);
        }
        for(int i =4;i<18;i=i+2){
            Bean b10=new Bean(new Point(i,43),5);
            addObserver(b10);
        }
        for(int i =4;i<18;i=i+2){
            Bean b10=new Bean(new Point(i,28),5);
            addObserver(b10);
        }
        for(int i =4;i<18;i=i+2){
            Bean b10=new Bean(new Point(i,20),5);
            addObserver(b10);
        }
        for(int i =4;i<18;i=i+2){
            Bean b10=new Bean(new Point(i,16),5);
            addObserver(b10);
        }
        for(int i =4;i<32;i=i+2){
            Bean b10=new Bean(new Point(i,8),5);
            addObserver(b10);
        }
        for(int i =12;i<49;i=i+2){
            Bean b10=new Bean(new Point(20,i),5);
            addObserver(b10);
        }
        for(int i =26;i<42;i=i+2){
            Bean b10=new Bean(new Point(i,36),5);
            addObserver(b10);
        }
        for(int i =26;i<42;i=i+2){
            Bean b10=new Bean(new Point(i,41),5);
            addObserver(b10);
        }
        for(int i =2;i<48;i=i+2){
            Bean b10=new Bean(new Point(48,i),5);
            addObserver(b10);
        }
        for(int i =2;i<16;i=i+2){
            Bean b10=new Bean(new Point(25,i),5);
            addObserver(b10);
        }
        for(int i =30;i<48;i=i+2){
            Bean b10=new Bean(new Point(25,i),5);
            addObserver(b10);
        }

        addObserver(b1);
        addObserver(b2);
        addObserver(b3);
        addObserver(b4);
        addObserver(b5);
        addObserver(b6);
        addObserver(b7);
    }





    /**
     *  trigger everything to move
     */


    /**
     * Make up the game board, initial everything so player can see
     */
    public void Gameboard() {
        this.trigger = false;
        this.score = 0;
        this.lifeNum = 3;
        this.beanNum = 200; // TODO how many beans do u want to eat
        this.ifWin = false;
        this.ifOver = false;
        //TODO initial the plaer
        this.player = Player.makePlayer(new Point(240, 310), 20);

    }

    public void initialObservers() {
        //TODO initial the door, banner, ghost, power, bean, wall
        APaintObject door = Door.makeDoor(new Point(270, 300), new Point(40, 10));
        APaintObject banner = new Banner(new Point(300, 300), new Point(100, 50));
        //TODO add observers
        addObserver(door);
        addObserver(banner);
    }

    public APaintObject getPlayer() {
        return player;
    }

    public Boolean getIfOver() {
        return this.ifOver;
    }

    public Boolean getIfWin() {
        return this.ifWin;
    }

    public int getBeanNum() {
        return this.beanNum;
    }

    public void setBeanNum(int beanNum) {
        this.beanNum = beanNum;
    }

    public int getLifeNum() {
        return this.lifeNum;
    }

    public void setLifeNum(int lifeNum) {
        this.lifeNum = lifeNum;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     *  trigger everything to move
     */
    public void start(){
        trigger = true;

    }

    /**
     * Set the canvas dimensions
     * @param dims The canvas width (x) and height (y)
     */
    public void setCanvasDims(Point dims) {
        this.dims = dims;
        SetWall();
        SetBean();

    }

    /**
     * Get the canvas dimensions
     * @return The canvas dimensions
     */
    public Point getCanvasDims() {
        return dims;
    }

    /**
     * Call the update method on all the paintobj observers to update their position in the paintobj world
     */
    public void updateGameboard() {
        if(!trigger)
            return;
        //TODO else start game
        setChanged();
        notifyObservers(new InteractCmd(this));
        // check if game end
        endGame();
    }


    /**
     *  If win, stop everything and prompt win banner
     *  if lose, stop everything and prompt lose banner
     */
    public void endGame(){
        if (beanNum <= 0) {
            trigger = false;
            ifOver = true;
            ifWin = true;
        } else if (lifeNum <= 0) {
            trigger = false;
            ifOver = true;
            ifWin = false;
        }
    }

    /**
     * Move the player, actually change direction
     */
    public void move(int dir){
        IPaintObjCmd mc = new MoveCmd(dir);
        mc.execute(player);
    }

}
