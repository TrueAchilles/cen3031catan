package settlers.game.elements;

import java.util.Vector;
import java.util.Iterator;
import java.util.Stack;
import java.lang.Math;
import settlers.game.elements.*;

// IGNORE THIS CODE FOR NOW. 
// Do not make modifications to it.
// It will be entirely rewritten in a much short, graph based way.
// This was only a quick fix to get the longest roads working in the most basic manner.
// It current does not work.
// the actual solution will look different then this.
// Do not program the actual solution unless specifically told to do so.
// etc etc etc.

public class LongestRoad
{

    int roadCount;
    int longestRoad;
    Settlement startSettlement1;
    Road startRoad1;
    Settlement startSettlement2;
    Road startRoad2;

    Player owner;
    Stack roadStack;
    
    public LongestRoad(Player p)
    {
        roadCount = 0;
        longestRoad = 0;
        owner = p;
        
    }
    
    // IGNORE THIS CODE FOR NOW. 
    // Do not make modifications to it.
    // It will be entirely rewritten in a much short, graph based way.
    // This was only a quick fix to get the longest roads working in the most basic manner.
    // the actual solution will look different then this.
    // Do not program the actual solution unless specifically told to do so.
    public int addRoad(Road r)
    {
        roadCount++;
        Settlement s1 = r.getS1();
        Settlement s2 = r.getS2();
        if (roadCount == 1)
        {
            startRoad1 = r;
            return 1;
        }
        if (roadCount == 2)
        {
            startRoad2 = r;
            return 1;
        }
        

        /*
        if ( s1 == startSettlement1) {
            startSettlement1 = s2;
            startRoad1 = r;
        } else if ( s2 == startSettlement1) {
            startSettlement1 = s1;
            startRoad1 = r;
        } else if (s1 == startSettlement2) {
            startSettlement2 = s2;
            startRoad1 = r;
        } else if (s2 == startSettlement2) {
            startSettlement2 = s1;
            startRoad1 = r;
        }
            */
     /*   int depth = 0;
        int longestDepth = 0;
        roadStack = new Stack();
        roadStack.push(startRoad1);
        
    

        
        while ( !roadStack.empty() )
        {
            depth++;
            longestDepth = Math.max(longestDepth, depth);
            Settlement s = (Settlement)settlementStack.peek();
            r = (Road)roadStack.peek();
            System.out.println(s.toString() + " " + r.toString() );
            Road checkR = this.checkAndGetRoad(s, r, "Top");
            if ( checkR != null && roadStack.search(checkR) == 0)
            {
                settlementStack.push(s.getTopNode());
                roadStack.push(checkR);
                continue;
            }
            checkR = this.checkAndGetRoad(s, r, "Side");
            if ( checkR != null && roadStack.search(checkR) == 0)
            {
                settlementStack.push(s.getSideNode());
                roadStack.push(checkR);
                continue;
            }
            checkR = this.checkAndGetRoad(s, r, "Bottom");
            if ( checkR != null && roadStack.search(checkR) == 0)
            {
                settlementStack.push(s.getBottomNode());
                roadStack.push(checkR);
                continue;
            }
            spentStack.push(r);
            settlementStack.pop();
            roadStack.pop();
            depth--;
            if (s == startSettlement1) {
                spentStack = new Stack();
                depth = 0;
            }
            
        } 
    
        longestRoad = longestDepth;
        return longestDepth;*/
        return 0;
    }
    
    
    private void rrs(Road r)
    {
        Road nextR[] = { r.getS1().getTopRoad(), r.getS1().getBottomRoad(), r.getS1().getSideRoad(),
                   r.getS2().getTopRoad(), r.getS2().getBottomRoad(), r.getS2().getSideRoad() };
        int i;
        for (i = 0; i < nextR.length/2; i++)
        {
            if (true)
            {}
        }    
        for (i = nextR.length/2; i < nextR.length; i++)
        {
        }
    }
    
    private Road checkAndGetRoad(Settlement cs, Road cr, String a)
    {
        Road r;
        Settlement s;
        if (a == "Top") {
            r = cs.getTopRoad();
            s = cs.getTopNode();
        } else if (a == "Bottom") {
            r = cs.getBottomRoad();
            s = cs.getBottomNode();
        } else {
            r = cs.getSideRoad();
            s = cs.getSideNode();
        }
        
        if (cr == r) {
            System.out.println("Road is going backwards");
            return null;            
        }
            
        if ( s.getOwner() != null && s.getOwner() != owner)
        {
            System.out.println("Blocked by another player");
            return null;
        }
        if (r.getOwner() == owner)
        {
            System.out.println("Now we're cookin");
            return r;
        }
        System.out.println("Simply failed");
        return null;
    }
}
    
    