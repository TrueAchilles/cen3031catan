/*
 * SettlersController.java
 *
 * Created on 4 de enero de 2003, 15:03
 */

package Settlers;
import java.util.*;

public class SettlersController implements SettlersConstants {
    Tile[] tiles;
    int[][] graphic=new int[16][16];
    int[] house=new int[16];
    SettlersGui gui=null;
    ArrayList players=new ArrayList();
    Random dice;
    byte action=-1;
    byte actualPlayer=0;
    
    /** Creates new SettlersController */
    public SettlersController() {
        initialize();
    }
    
    public void setGui(SettlersGui _gui){
        gui=_gui;
    }
    
    public byte getType(int pos){
        return tiles[pos].getType();
    }
    
    public Tile[] getTiles(){
        return tiles;
    }
    public byte[] getAllTypes(){
        byte[] ret=new byte[19];
        for (int i=0;i<19;i++){
            ret[i]=tiles[i].getType();
        }
        return ret;
    }
    
    private void initialize(){
        ArrayList v=new ArrayList();
        for (int i=0;i<4;i++){
            v.add(new Byte(TERRAINS[WOOD]));
            v.add(new Byte(TERRAINS[WHEAT]));
            v.add(new Byte(TERRAINS[SHEEP]));
        }
        for (int i=0;i<3;i++){
            v.add(new Byte(TERRAINS[BRICK]));
            v.add(new Byte(TERRAINS[ORE]));
        }
        v.add(new Byte(TERRAINS[EMPTY]));
        
        Random rnd=new Random();
        
        tiles=new Tile[19];
        for (int i=0;i<19;i++){
            int sig=rnd.nextInt(v.size());
            byte type=((Byte)v.get(sig)).byteValue();
            v.remove(sig);
            tiles[i]=new Tile(type);
        }
        for (int i=0;i<19;i++){
            tiles[i].setNum((byte)i,tiles);            
        }
        int pos=0;
        for (int i=0;i<19;i++){
            if (tiles[PRODUCTION_ORDER[i]].getType()!=TERRAINS[EMPTY]){
                //It isn't a desert
                tiles[PRODUCTION_ORDER[i]].setProduction(PRODUCTION[pos]);
                pos++;
            }
            else{
                //Desert
                tiles[PRODUCTION_ORDER[i]].setProduction((byte)0);
            }
            
        }
        
        
        
        
    }
    
    
    public boolean addRoad(Tile c, byte pos, byte numPlayer){
        boolean ret=c.addRoad(pos,numPlayer);
        if (ret){
            //addRoadGraphic();
        }
        repaint();
        return ret;
    }
    
     public boolean addSettlement(Tile c, byte pos, byte numPlayer){
        boolean ret=c.addSettlement(pos,numPlayer);
        if (ret){
            //addSettlementGraphic();
            repaint();
        }        
        return ret;
    }
     
     public boolean addCity(Tile c, byte pos, byte numPlayer){
        boolean ret=c.upgrade(pos,numPlayer);
        if (ret){
            //addSettlementGraphic();
            repaint();
        }        
        return ret;
    }
    public void write(String txt){
        process(txt);
        
    }
    
    private void process(String txt){
        //The orders arrive in the format player#action#tileNumber#pos
        StringTokenizer st=new StringTokenizer(txt,"#");
        byte player= Byte.parseByte(st.nextToken());
        byte action=Byte.parseByte(st.nextToken());
        byte tileNumber=Byte.parseByte(st.nextToken());
        byte pos=Byte.parseByte(st.nextToken());
        doAction (player, action, tileNumber, pos);
    }
        
    private void doAction (byte player, byte action, byte tileNumber, byte pos){
        switch (action){
            case ACTION_ADD_SETTLEMENT:{
                if (!addSettlement(tiles[tileNumber],pos,player)){
                    gui.showError("You can't built a settlement there. That spot is already taken.");
                }     
                break;
            }
            case ACTION_ADD_CITY:{
                if (!addCity(tiles[tileNumber],pos,player)){
                    gui.showError("You can't built a city there. You can only built cities over settlements.");
                }
                break;
            }
            case ACTION_ADD_ROAD:{
                if (!addRoad(tiles[tileNumber],pos,player)){
                    gui.showError("You can't built a road there. That spot is already taken.");
                }
            }
        } 
        gui.setAction((byte)-1);
    }
    
    
    private void repaint(){
        gui.getBoard().setTiles(tiles);
        gui.repaint();
    }
    
    
    ///////////////////////// LONGEST ROAD ///////////////////////
    private boolean travel(ArrayList road, int start, int end){
        for (int i=0;i<road.size()-1;i++){
            int c0=((Integer)road.get(i)).intValue();
            int c1=((Integer)road.get(i+1)).intValue();
            if (((c0==start)&&(c1==end))||((c0==end)&&(c1==start))){
                return true;
            }
        }
        return false;
        
    }
    
    private ArrayList longestRoad(ArrayList road, int start, byte val){
        //Finds the road with the largest "val" that can be followed from "start" along "road"
        int i=0;
        ArrayList roadSol=(ArrayList)road.clone();
        int maxLength=road.size();
        
        for (i=0;i<graphic.length;i++){
            //Try and walk through each node
            if (
            (graphic[start][i]==val) //there is a road
            && (!travel(road,start,i)) //I haven't counted that part
            && ((house[start]==val)||(house[start]==0) || maxLength==1) //nor is there a house, or is it part of the same road, nor did we begin at this node
            ){
                road.add(new Integer(i));
                ArrayList road2=longestRoad(road,i,val);
                if (road2.size()>=maxLength){ //New longest road
                    roadSol=(ArrayList)road2.clone();
                    maxLength=road2.size();
                }
                road.remove(road.size()-1); //Remove it to keep testing
            }
        }
        return roadSol;
    }
    
    
    
    private void addRoadGraphic(int start, int end, int val){
        graphic[start][end]=val;
        graphic[end][start]=val;
    }
    
    private void addSettlementGraphic(int pos, int type){        
        house[pos]=type;
    }
    
    
    
    private void initializeRoad(){
        for (int i=0;i<16;i++){
            for (int j=0;j<16;j++){
                graphic[i][j]=0;
            }
        }
        
        for (int i=0;i<16;i++){
            house[i]=0;
        }
        
                /*house[0]=3;
                house[9]=3;
                 
                 
                 
                addRoad(0,3,1);
                addRoad(3,7,1);
                addRoad(7,8,1);
                addRoad(7,11,1);
                addRoad(11,14,1);
                addRoad(14,15,1);
                 
                addRoad(15,12,1);
                addRoad(12,8,1);
                 
                addRoad(12,13,1);
                addRoad(13,9,1);
                addRoad(9,5,1);
                addRoad(5,4,1);
                addRoad(4,8,1);
                addRoad(4,1,1);
                addRoad(0,1,1);
                 
                 
                var road=[];
                road[road.length]=0;
                var sol=solve(road,0,1);
                alert ("Solution: "+sol);
                 */
    }
    
    public void addPlayer(String name){
        Player j=new Player(name);
        players.add(j);
    }
    
    private int rollDie(){                
        return dice.nextInt(12)+1;
    }
    
    public void press(byte tile, byte vertex, byte edge){
         switch (action){
            case ACTION_ADD_SETTLEMENT:
            case ACTION_ADD_CITY:{
                if (vertex>-1){
                    doAction(actualPlayer,action,tile,vertex);
                }
                break;
            }
            case ACTION_ADD_ROAD:{
                if (edge>-1){
                    doAction(actualPlayer,action,tile,edge);
                }
                break;
            }
        }                
         
        
    }
    
    public void buttonRoad(){
        action=ACTION_ADD_ROAD;
        gui.setAction(action);
    }
    
    public void buttonSettlement(){
        action=ACTION_ADD_SETTLEMENT;        
        gui.setAction(action);
    }
    
    public void buttonCity(){
        action=ACTION_ADD_CITY;
        gui.setAction(action);
    }
    
    public void buttonCard(){
    }
    
}
