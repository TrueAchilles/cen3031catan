package settlers.game;

import java.awt.*;

/**
 * Contains all of the aliases for all of the values used throughout the game.
 */
public class GlobalVar {

    public static final Object LOCK = new Object();

    // Game Modifiers
    public static final boolean DEBUG_MODE = true;
    public static final int MAX_NUMBER_PLAYERS = 8;
    public static final int ODD_SWITCH = 0; // odd name for an odd variable, eh? Switch this to break all. 
    public static final int MAP[][] = {
    
        { 0,0,0,0,0,0,0,0 },
        { 0,0,0,2,1,0,0,0 },
        { 0,0,2,1,2,1,0,0 },
        { 0,2,1,2,1,2,1,0 },
        { 0,1,2,1,2,1,1,0 },
        { 0,2,1,2,1,2,1,0 },
        { 0,1,2,1,2,1,1,0 },
        { 0,2,1,2,1,2,1,0 },
        { 0,1,2,1,2,1,1,0 },
        { 0,1,1,2,1,1,1,0 },
        { 0,0,1,1,1,1,0,0 },
        { 0,0,0,1,1,0,0,0 },
        { 0,0,0,0,0,0,0,0 } };
        
            
        public static final int BIGMAP[][] = {
        
        { 0,0,0,0,0,0,0,0,0,0,0,0 },
        { 0,0,0,0,0,2,1,0,0,0,0,0 },
        { 0,0,0,0,2,1,2,1,0,0,0,0 },
        { 0,0,0,2,1,2,1,2,1,0,0,0 },
        { 0,0,2,1,2,1,2,1,2,1,0,0 },
        { 0,0,1,2,1,2,1,2,1,1,0,0 },
        { 0,0,2,1,2,1,2,1,2,1,0,0 },
        { 0,0,1,2,1,2,1,2,1,1,0,0 },
        { 0,0,2,1,2,1,2,1,2,1,0,0 },
        { 0,0,1,2,1,2,1,2,1,1,0,0 },
        { 0,0,2,1,2,1,2,1,2,1,0,0 },
        { 0,0,1,2,1,2,1,2,1,1,0,0 },
        { 0,0,1,1,2,1,2,1,1,1,0,0 },
        { 0,0,0,1,1,2,1,1,1,0,0,0 },
        { 0,0,0,0,1,1,1,1,0,0,0,0 },
        { 0,0,0,0,0,1,1,0,0,0,0,0 }, 
        { 0,0,0,0,0,0,0,0,0,0,0,0 }       
        
        };

    public static final int BIG_RESOURCE_TYPE_ARRAY[] = { 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5,1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5  };
    public static final boolean HAS_DESERT = true;   
    public static final boolean ROBBER = true;
    public static final int BIG_RESOURCE_NUMBER_ARRAY[] = { 2, 3, 3, 4, 4 , 5 , 5, 6 ,6 , 8 , 8, 9 , 9, 10, 10, 11, 11, 12,2, 3, 3, 4, 4 , 5 , 5, 6 ,6 , 8 , 8, 9 , 9, 10, 10, 11, 11, 12 }; 
    
    public static final int RESOURCE_TYPE_ARRAY[] = { 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5 };
    public static final int RESOURCE_NUMBER_ARRAY[] = { 2, 3, 3, 4, 4 , 5 , 5, 6 ,6 , 8 , 8, 9 , 9, 10, 10, 11, 11, 12 }; 
    
    
    // Map Related
    public static final int EDGE_LENGTH = 53;
    public static final int STEP_LENGTH = (int)(53 * 0.7071d );
    public static final boolean CENTER_MAP = true;
    public static final boolean RESIZED = true;

    //Materials
    public static final int DESERT=0;
    public static final int WOOD=1;
    public static final int BRICK=2;
    public static final int WHEAT=3;
    public static final int SHEEP=4;
    public static final int ORE=5;

    
    //Terrains
    //public static final int[] TERRAINS={0,1,2,3,4,5};
    
    
    //Settlements
    //public static final int EMPTY_SETTLEMENT=0;
    //public static final int[] SETTLEMENT={1,2,3,4};
    //public static final int[] CITY={5,6,7,8};

    //Ports
    //public static final int P3_A_1=6;
    //public static final int[] PORT={0,1,2,3,4,5,6}; 
    
    //Roads
    //public static final int EMPTY_ROAD=0;
    //public static final int[] ROAD={1,2,3,4};
    
    //Production
    //public static final int PRODUCTION[]={5,2,6,3,8,10,9,12,11,4,8,10,9,4,5,6,3,11};
    //public static final int PRODUCTION_ORDER[]={18,17,15,10,5,2,0,1,3,8,13,16,14,12,7,4,6,11,9};
    
    //Colors
    //First, some custom colors
    //public static final Color COLOR_BEIGE = Color.decode("#F5F5DC"); //Desert
    //public static final Color COLOR_KHAKI = Color.decode("#F0E68C"); //Desert
    //public static final Color COLOR_FIREBRICK = Color.decode("#B22222"); //Brick
    //public static final Color COLOR_SADDLEBROWN = Color.decode("#8B4513"); //Forest
    //public static final Color COLOR_GOLD = Color.decode("#FFD700"); //Wheat
    //public static final Color COLOR_DARKGREEN = Color.decode("#006400"); //Forest
    //public static final Color COLOR_LAWNGREEN = Color.decode("#7CFC00"); //Sheep
    
    /* The order of the colors is desert, woods, brick, wheat, sheep, ore */
    //public static final Color COLORS[]={COLOR_KHAKI,COLOR_DARKGREEN,COLOR_FIREBRICK,COLOR_GOLD,COLOR_LAWNGREEN,Color.darkGray};
    //public static final Color PLAYER_COLORS[]={Color.red,Color.blue,Color.green,java.awt.Color.magenta};
    
    //Actions
    public static final int ACTION_ADD_SETTLEMENT=1;
    public static final int ACTION_ADD_CITY=2;
    public static final int ACTION_ADD_ROAD=3;
    public static final int ACTION_ADD_DEV=4;
    public static final int ACTION_MOVE_ROBBER=5;
    
    //Game states
    public static final int GAME_LOADING = 0;
    public static final int GAME_INIT = 1;
    public static final int GAME_STARTED = 2;

}

