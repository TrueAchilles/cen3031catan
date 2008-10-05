/*
 * Tile.java
 *
 * Created on 4 de enero de 2003, 13:38
 */

package SettlersGUI;

import Settlers.SettlersConstants;

/**
 *  Tiles
 *
 *           __
 *        __/ 0\__
 *     __/ 1\__/ 2\__
 *    / 3\__/ 4\__/ 5\
 *    \__/ 6\__/ 7\__/
 *    / 8\__/ 9\__/10\
 *    \__/11\__/12\__/
 *    /13\__/14\__/15\
 *    \__/16\__/17\__/
 *       \__/18\__/
 *          \__/
 *
 *
 *
 *
 *  Settlement
 *    4  ____5
 *     /     \
 *    /       \
 *  3 \       /0
 *     \_____/
 *    2      1
 *
 *
 *  Road
 *       _4__
 *   3 /     \ 5
 *    /       \
 *    \       /
 *   2 \_____/ 0
 *        1
 *
 *  Adjacents
 *
 *
 *      3   4    5
 *       \  |  /
 *          X
 *       /  |  \
 *      2   1   0
 *
 *
 *
 *
 */


public class Tile implements SettlersConstants {
    private byte[] settlement;
    private byte[] road;
    private byte[] port;
    private Tile[] adjacent;
    private byte type;
    private byte num;
    private byte production;    
    
    /** Creates new Tile */
    public Tile(byte _type) {
        settlement=new byte[6];
        road=new byte[6];
        port=new byte[6];
        adjacent=new Tile[6];
        type=_type;
        for (int i=0;i<6;i++){
            settlement[i]=EMPTY_SETTLEMENT;
            road[i]=EMPTY_ROAD;
            port[i]=PORT[EMPTY];
        }
    }
    
    public byte getType(){
    	return type;
    }
    
    
    /**
     * Establishes the numbers of the tiles and the adjacent ones
     *
     *   Adyacentes
     *
     *
     *      3   4    5
     *       \  |  /
     *          X
     *       /  |  \
     *      2   1   0
     *
     *
     *  Tiles
     *
     *           __
     *        __/ 0\__
     *     __/ 1\__/ 2\__
     *    / 3\__/ 4\__/ 5\
     *    \__/ 6\__/ 7\__/
     *    / 8\__/ 9\__/10\
     *    \__/11\__/12\__/
     *    /13\__/14\__/15\
     *    \__/16\__/17\__/
     *       \__/18\__/
     *          \__/
     *
     */
    public void setNum(byte _num, Tile[] tiles){
        num=_num;
        switch (num){
            case 0:{
                adjacent[0]=tiles[2];
                adjacent[1]=tiles[4];
                adjacent[2]=tiles[1];
                adjacent[3]=null;
                adjacent[4]=null;
                adjacent[5]=null;
                break;
            }
            case 1:{
                adjacent[0]=tiles[4];
                adjacent[1]=tiles[6];
                adjacent[2]=tiles[3];
                adjacent[3]=null;
                adjacent[4]=null;
                adjacent[5]=tiles[0];
                break;
            }
            case 2:{
                adjacent[0]=tiles[5];
                adjacent[1]=tiles[7];
                adjacent[2]=tiles[4];
                adjacent[3]=tiles[0];
                adjacent[4]=null;
                adjacent[5]=null;
                break;
            }
            case 3:{
                adjacent[0]=tiles[6];
                adjacent[1]=tiles[8];
                adjacent[2]=null;
                adjacent[3]=null;
                adjacent[4]=null;
                adjacent[5]=tiles[1];
                break;
            }
            case 4:{
                adjacent[0]=tiles[7];
                adjacent[1]=tiles[9];
                adjacent[2]=tiles[6];
                adjacent[3]=tiles[1];
                adjacent[4]=tiles[0];
                adjacent[5]=tiles[2];
                break;
            }
            case 5:{
                adjacent[0]=null;
                adjacent[1]=tiles[10];
                adjacent[2]=tiles[7];
                adjacent[3]=tiles[2];
                adjacent[4]=null;
                adjacent[5]=null;
                break;
            }
            case 6:{
                adjacent[0]=tiles[9];
                adjacent[1]=tiles[11];
                adjacent[2]=tiles[8];
                adjacent[3]=tiles[3];
                adjacent[4]=tiles[1];
                adjacent[5]=tiles[4];
                break;
            }
            case 7:{
                adjacent[0]=tiles[10];
                adjacent[1]=tiles[12];
                adjacent[2]=tiles[9];
                adjacent[3]=tiles[4];
                adjacent[4]=tiles[2];
                adjacent[5]=tiles[5];
                break;
            }
            case 8:{
                adjacent[0]=tiles[11];
                adjacent[1]=tiles[13];
                adjacent[2]=null;
                adjacent[3]=null;
                adjacent[4]=tiles[3];
                adjacent[5]=tiles[6];
                break;
            }
            case 9:{
                adjacent[0]=tiles[12];
                adjacent[1]=tiles[14];
                adjacent[2]=tiles[11];
                adjacent[3]=tiles[6];
                adjacent[4]=tiles[4];
                adjacent[5]=tiles[7];
                break;
            }
            case 10:{
                adjacent[0]=null;
                adjacent[1]=tiles[15];
                adjacent[2]=tiles[12];
                adjacent[3]=tiles[7];
                adjacent[4]=tiles[5];
                adjacent[5]=null;
                break;
            }
            case 11:{
                adjacent[0]=tiles[14];
                adjacent[1]=tiles[16];
                adjacent[2]=tiles[13];
                adjacent[3]=tiles[8];
                adjacent[4]=tiles[6];
                adjacent[5]=tiles[9];
                break;
            }
            case 12:{
                adjacent[0]=tiles[15];
                adjacent[1]=tiles[17];
                adjacent[2]=tiles[14];
                adjacent[3]=tiles[9];
                adjacent[4]=tiles[7];
                adjacent[5]=tiles[10];
                break;
            }
            case 13:{
                adjacent[0]=tiles[16];
                adjacent[1]=null;
                adjacent[2]=null;
                adjacent[3]=null;
                adjacent[4]=tiles[8];
                adjacent[5]=tiles[11];
                break;
            }
            case 14:{
                adjacent[0]=tiles[17];
                adjacent[1]=tiles[18];
                adjacent[2]=tiles[16];
                adjacent[3]=tiles[11];
                adjacent[4]=tiles[9];
                adjacent[5]=tiles[12];
                break;
            }
            case 15:{
                adjacent[0]=null;
                adjacent[1]=null;
                adjacent[2]=tiles[17];
                adjacent[3]=tiles[12];
                adjacent[4]=tiles[10];
                adjacent[5]=null;
                break;
            }
            case 16:{
                adjacent[0]=tiles[18];
                adjacent[1]=null;
                adjacent[2]=null;
                adjacent[3]=tiles[13];
                adjacent[4]=tiles[11];
                adjacent[5]=tiles[14];
                break;
            }
            case 17:{
                adjacent[0]=null;
                adjacent[1]=null;
                adjacent[2]=tiles[18];
                adjacent[3]=tiles[14];
                adjacent[4]=tiles[12];
                adjacent[5]=tiles[15];
                break;
            }
            case 18:{
                adjacent[0]=null;
                adjacent[1]=null;
                adjacent[2]=null;
                adjacent[3]=tiles[16];
                adjacent[4]=tiles[14];
                adjacent[5]=tiles[15];
                break;
            }
        }
        
    }
    
    public void setProduction(byte prod){
        production=prod;
    }
    
    public byte getProduction(){
        return production;
    }
    
    public boolean addSettlement(byte pos, byte numPlayer){
        if (settlement[pos]!=EMPTY_SETTLEMENT){
            return false;
        }
        settlement[pos]=SETTLEMENT[numPlayer];
        byte a1,a2;
        a1=(byte)((pos+5)%6);
        a2=pos;
        if (adjacent[a1]!=null){
            adjacent[a1].addSettlement2((byte)((pos+2)%6),numPlayer);
        }
        if (adjacent[a2]!=null){
            adjacent[a2].addSettlement2((byte)((pos+4)%6),numPlayer);
        }
        
        return (true);
        
    }
    
    private void addSettlement2(byte pos, byte numPlayer){
        //To keep information redundant
        settlement[pos]=SETTLEMENT[numPlayer];
    }
    
    public boolean upgrade(byte pos,byte numPlayer){
        if (settlement[pos]!=SETTLEMENT[numPlayer]){
            return false;
        }
        
        settlement[pos]=CITY[numPlayer];
        byte a1,a2;
        a1=(byte)((pos+5)%6);
        a2=pos;
        if (adjacent[a1]!=null){
            adjacent[a1].addSettlement2((byte)((pos+2)%6),numPlayer);
        }
        if (adjacent[a2]!=null){
            adjacent[a2].addSettlement2((byte)((pos+4)%6),numPlayer);
        }
        
        return (true);
        
    }
    
    private void upgrade2(byte pos, byte numPlayer){
    	//To maintain redundancy
        settlement[pos]=CITY[numPlayer];
    }
    
    public boolean addRoad(byte pos, byte numPlayer){
        if (road[pos]!=EMPTY_ROAD){
            return false;
        }
        road[pos]=ROAD[numPlayer];
        if (adjacent[pos]!=null){
            adjacent[pos].addRoad2((byte)((pos+3)%6),numPlayer);
        }
        
        return (true);
        
    }
    
    private void addRoad2(byte pos, byte numPlayer){
    	//To maintain redundancy
        road[pos]=ROAD[numPlayer];
    }
    
    public byte[] getSettlements(){
        return settlement;    
    }
    
    public byte[] getRoads(){
        return road;    
    }
    
    public byte[] getPorts(){
        return port;    
    }
    
    
    
}
