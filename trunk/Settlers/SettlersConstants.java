/*
 * SettlersConstants.java
 *
 * Created on 4 de enero de 2003, 13:24
 */

package Settlers;

/**
 *
 * @author  Dodger
 * @version 0.1
 */

import java.awt.*;

public interface SettlersConstants {

    //Materials
    public static final byte EMPTY=0;
    public static final byte WOOD=1;
    public static final byte BRICK=2;
    public static final byte WHEAT=3;
    public static final byte SHEEP=4;
    public static final byte ORE=5;

    
    //Terrains
    public static final byte[] TERRAINS={0,1,2,3,4,5};
    
    
    //Settlements
    public static final byte EMPTY_SETTLEMENT=0;
    public static final byte[] SETTLEMENT={1,2,3,4};
    public static final byte[] CITY={5,6,7,8};

    //Ports
    public static final byte P3_A_1=6;
    public static final byte[] PORT={0,1,2,3,4,5,6}; 
    
    //Roads
    public static final byte EMPTY_ROAD=0;
    public static final byte[] ROAD={1,2,3,4};
    
    //Production
    public static final byte PRODUCTION[]={5,2,6,3,8,10,9,12,11,4,8,10,9,4,5,6,3,11};
    public static final byte PRODUCTION_ORDER[]={18,17,15,10,5,2,0,1,3,8,13,16,14,12,7,4,6,11,9};
    
    //Colores
    public static final Color COLORS[]={Color.white,Color.lightGray,Color.orange,Color.yellow,Color.green,Color.darkGray};
    public static final Color PLAYER_COLORS[]={Color.red,Color.darkGray,Color.cyan,Color.gray,Color.pink};
    
    //Actions
    public static final byte ACTION_ADD_SETTLEMENT=0;
    public static final byte ACTION_ADD_CITY=1;
    public static final byte ACTION_ADD_ROAD=2;
    
   

}

