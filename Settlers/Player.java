/*
 * Player.java
 *
 * Created on 2 de febrero de 2003, 12:36
 */

package Settlers;

public class Player implements SettlersConstants{    
    private String name=null;
    private int[] resources={0,0,0,0,0};
    private boolean[] ports={false,false,false,false,false,false};
    
    /** Creates a new instance of Player */
    public Player(String _name) {        
        name=_name;
    }
    
    public boolean hasPort(int type){
        return (ports[type]);
    }
    
    public void addPort(int type){
        ports[type]=true;
    }
    
    public int getResource(int type){
        return(resources[type]);
    }
    
    public void setResource(int type, int value){
        resources[type]=value;
    }
    
    public void addResource(int type, int quantity){
        resources[type]+=quantity;
    }
    
    public boolean subResource(int type, int quantity){
        if (resources[type]<quantity)
            return false;
        resources[type]-=quantity;
        return true;
    }
    
}
