package settlers.game.elements;

import java.util.Vector;
import java.util.Iterator;
import java.util.Stack;
import java.util.LinkedList;
import java.lang.Math;
import settlers.game.elements.*;
import settlers.game.ContainerGUI;

public class LongestRoad
{

    int roadCount;
    int longestRoad;

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
        if (roadCount == 1)
        {
            return 1;
        }
        if (roadCount == 2)
        {
            return 1;
        }
        
        Vector endNodeVector = new Vector();
        Settlement[][] v = ContainerGUI.gameBoard.vertex;
        int i,j, count;
        for (i = 0; i < v.length; i++)
        {
            for (j = 0; j < v[i].length; j++)
            {
                count = 0;
                if ( v[i][j].getTopRoad() != null && v[i][j].getTopRoad().getOwner() == r.getOwner()  )
                    count++;
                if ( v[i][j].getSideRoad() != null && v[i][j].getSideRoad().getOwner() == r.getOwner())
                    count++;
                if ( v[i][j].getBottomRoad() != null && v[i][j].getBottomRoad().getOwner() == r.getOwner()  )
                    count++;
                if (count == 1)
                {
                    endNodeVector.add(v[i][j]);
                }
                
            }
        }
        if (endNodeVector.isEmpty())
            return longestRoad+1;
        
        int maxAr[] = new int[endNodeVector.size()];
        
        for (i = 0; i < endNodeVector.size(); i++)
        {
            Settlement s = (Settlement)endNodeVector.get(i);
            if ( s.getTopRoad() != null && s.getTopRoad().getOwner() == r.getOwner() )
                maxAr[i] = rrs( s.getTopRoad(), s, new LinkedList(), 0);
            if ( s.getSideRoad() != null && s.getSideRoad().getOwner() == r.getOwner() )
                maxAr[i] = rrs(s.getSideRoad(), s, new LinkedList(), 0);
            if ( s.getBottomRoad() != null && s.getBottomRoad().getOwner() == r.getOwner() )
                maxAr[i] = rrs(s.getBottomRoad(), s, new LinkedList(), 0);
        }
        
        return longestRoad = maxInAr(maxAr);
    }
    
    
    private int rrs(Road r, Settlement prevSett, LinkedList searchedList, int foundLength)
    {
        
        Road nextR[];
        Settlement nextSett;
        int i;
        int maxAr[] = { -1, -1, -1, foundLength };
        if (prevSett == r.getS1() )
        {
            Road nextRr[] = { r.getS2().getTopRoad(), r.getS2().getBottomRoad(), r.getS2().getSideRoad() };
            nextR = nextRr;
            nextSett = r.getS2();
        }
        else
        {
            Road nextRr[] = { r.getS1().getTopRoad(), r.getS1().getBottomRoad(), r.getS1().getSideRoad() };
            nextR = nextRr;
            nextSett = r.getS1();
        }
        
        for (i = 0; i < nextR.length; i++)
        {
            if (nextR[i] == null)
                continue;
            if (nextR[i].getOwner() != r.getOwner())
                continue;
            if (searchedList.indexOf(r) != -1)
                continue;
            LinkedList clonedList = (LinkedList)searchedList.clone();
            clonedList.add(r);
            maxAr[i] = rrs(nextR[i], nextSett, clonedList, foundLength+1);
        }
        return maxInAr(maxAr);
    }

    private int maxInAr(int[] ar)
    {
        int i = 0;
        int max = -1;
        for ( i = 0; i < ar.length; i++ )
            if (ar[i] > max)
                max = ar[i];
        return max;
    }
}
    
    