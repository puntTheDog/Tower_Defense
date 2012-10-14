import java.awt.Point;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
 
 
public class Tower
{
        private Image image;
        private Point loc;
        
        //the circle's area of effect. basically it's hit box.
        private Circle aoe;
        
        private int radius;
        private float x,y;
       
        public Tower(Image image, Point loc)
        {
                this.image = image;
                this.loc = loc;
                radius = 150;
                aoe = new Circle((int)loc.getX(), (int)loc.getY(), (int)radius);
               
        }
       
        public void draw()
        {
                image.draw((int)loc.getX()-image.getWidth()/2, (int)loc.getY()-image.getWidth()/2);
               
        }
        
        //shoots a given monster
    	public void shoot(Monster m){
    		
    		System.out.println("bam shot ya bitch");
    	}
        
        public int getRadius(){
                return radius;
               
        }
        public Shape getAoe(){
                return aoe;
        }
       
        public float getX(){
                x = (float) loc.getX();
                return x;
        }
        public float getY(){
                y = (float) loc.getY();
                return y;
        }
 
}