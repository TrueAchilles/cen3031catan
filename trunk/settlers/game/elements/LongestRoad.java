package settlers.game.elements;

import java.util.Vector;
import java.util.Iterator;
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
    private class Point
    {
        Road road;
        Settlement settlement;
        int length;
        Point startPath;
        
        Point(Road r, Settlement s, Point path)
        {
            road = r;
            settlement = s;
            startPath = path;
            length = 1;
        }
        int extend(Road r, Settlement s)
        {
            road = r;
            settlement = s;
            return ++length;
        }
    }
    
    int roadCount;
    
    Point startPoint1;
    Point startPoint2;
    Vector<Point> endPointCollection;
    
    public LongestRoad()
    {
        roadCount = 0;
        endPointCollection = new Vector<Point>();
        
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
            startPoint1 = new Point(r, s1, null);
            endPointCollection.add( new Point(r, s2, startPoint1));
            return 1;
        }
        if (roadCount == 2)
        {
            startPoint2 = new Point(r, s1, null);
            endPointCollection.add( new Point(r, s2, startPoint2));
            return 1;
        }
        
        
        
        if (this.isEndPoint(s1) && this.isStartPoint(s2))
        {
            System.out.println("s1 is an end point, and s2 is a startpoint");
            
        }
        else if (this.isEndPoint(s2) && this.isStartPoint(s1))
        {
            System.out.println("s2 is an end point, and s1 is a startpoint");
            
        }
        else if (this.isEndPoint(s1) && this.isEndPoint(s2))
        {
            System.out.println("s1 is an end point, and s2 is a end point");
            
        }
        else if (this.isEndPoint(s1))
        {
            System.out.println("s1 is an end point");
            /* There is one end point and nothing else. Alright! A very typical move. */
            return this.getEndPoint(s1).extend(r, s2);
        }
        else if (this.isEndPoint(s2))
        {
            System.out.println("s2 is an end point");
            return this.getEndPoint(s2).extend(r,s1);
        }
        else if (this.isStartPoint(s1))
        {
            System.out.println("s1 is a startpoint");
            /*Just building where the path begins */
            Point sp = this.getStartPoint(s1);
            sp.settlement = s2;
            sp.road = r;
            incrementPaths(sp);
            
        }
        else if (this.isStartPoint(s2))
        {
            System.out.println("s2 is a startpoint");
            /*Just building where the path begins */
            Point sp = this.getStartPoint(s2);
            sp.settlement = s1;
            sp.road = r;
            incrementPaths(sp);  
                        
        } else {
        /* It must be creating a new end point */
        /* This is a big deal as it is common, but i'll do it later... */
        
        System.out.println("new point");
        }
        return this.max();
    }
    
    private boolean isStartPoint(Settlement s)
    {
        if (s == startPoint1.settlement || s == startPoint2.settlement)
            return true;
        return false;
    }
    
    private Point getStartPoint(Settlement s)
    {
        if (s == startPoint1.settlement)
            return startPoint1;
        if (s == startPoint2.settlement)
            return startPoint2;
        return null;
    }
    
    private boolean isEndPoint(Settlement s)
    {
        Iterator itr = endPointCollection.iterator();
        while(itr.hasNext())
        {
            Point p = (Point)itr.next();
            if (p.settlement == s)
                return true;
        }
        return false;
    }
    
    private void mergePaths(Point sp, int len)
    {
        Iterator itr = endPointCollection.iterator();
        while(itr.hasNext())
        {
            Point p = (Point)itr.next();
            if (p.startPath == sp)
                p.length += len;
        }
        if (sp == startPoint1)
            startPoint1 = startPoint2;
        else
            startPoint2 = startPoint1;
    }
    
    private void incrementPaths(Point sp)
    {
        Iterator itr = endPointCollection.iterator();
        while(itr.hasNext())
        {
            Point p = (Point)itr.next();
            if (p.startPath == sp)
                p.length++;
        }
    }
    
    
    private Point getEndPoint(Settlement s)
    {
        Iterator itr = endPointCollection.iterator();
        while(itr.hasNext())
        {
            Point p = (Point)itr.next();
            if (p.settlement == s)
                return p;
        }
        return null;
    }
    
    private int max()
    {
        Iterator itr = endPointCollection.iterator();
        int max = -1;
        while(itr.hasNext())
        {
            Point p = (Point)itr.next();
            if (p.length > max)
                max = p.length;
        }
        return max;
    }
}
    
    