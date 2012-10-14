import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
 
/**
 * The path is a collection
 * of discrete Point objects
 *
 */
public class Path {
	
        private ArrayList<Point> points;
        private final int STEP = 2; //distance between points
       
        /**
         * Constructs a path from a text file. The first line of the text file is the starting x and y
         * coordinates of the path. Every line thereafter contains a direction, and a distance. All
         * distances should be in increments of 5
         *
         * @param pathFile the name of the file to be read from
         */
        public Path(String pathFile){
                points = new ArrayList<Point>();
                File f = new File(pathFile);
                try {
                        Scanner scan = new Scanner(f);
                        String s = scan.next();
                        String[] coords = s.split(",");
                        points.add(new Point(Integer.parseInt(coords[0]),Integer.parseInt(coords[1])));
                        while(scan.hasNext()){
                                s = scan.next();
                                coords = s.split(",");
                                int dist = Integer.parseInt(coords[1]);
                                Point last = points.get(points.size()-1);
                               
                                if(coords[0].equals("r")){
                                        for(int i = STEP; i<=dist;i+=STEP){
                                                points.add(new Point(last.x+i,last.y));
                                        }
                                }
                               
                                if(coords[0].equals("l")){
                                        for(int i = STEP; i<=dist;i+=STEP){
                                                points.add(new Point(last.x-i,last.y));
                                        }
                                }
                               
                                if(coords[0].equals("u")){
                                        for(int i = STEP; i<=dist;i+=STEP){
                                                points.add(new Point(last.x,last.y-i));
                                        }
                                }
                               
                                if(coords[0].equals("d")){
                                        for(int i = STEP; i<=dist;i+=STEP){
                                                points.add(new Point(last.x,last.y+i));
                                        }
                                }
                        }
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
               
       
               
        }
       
        /**
         * Gets the next point in the path based on a current point and a speed.
         * A speed of one will take you to the next point in the path - 5 pixels away.
         *
         * @param current starting Point
         * @param speed int determining how far down the path to travel
         * @return the new position
         * @throws InterruptedException
         */
        public Point nextPosition(Point current, int speed){
                int index = points.indexOf(current);
                if(index+speed < points.size()){
                        return points.get(index+speed);
                }
                else
                        return getEnd();
        }
       
        /**
         * Draws the path
         *
         * @param g - the Graphics object on which the path is drawn
         */
        public void draw(Graphics g){
                g.setColor(Color.red);
                for(int i = 0; i<points.size()-1; i++){
                        g.drawLine(points.get(i).x,points.get(i).y,points.get(i+1).x,points.get(i+1).y);
                }
                g.setColor(Color.black);
        }
       
        /**
         * Gets the starting point of the path
         *
         * @return the starting Point of the path
         */
        public Point getStart(){
                return points.get(0);
        }
       
        /**
         * Gets the ending point of the path
         *
         * @return the ending Point of the path
         */
        public Point getEnd(){
                return points.get(points.size()-1);
        }
       
}