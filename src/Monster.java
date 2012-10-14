import java.awt.Point;
 
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
 
 
public class Monster {
       
        private int speed;
        private int xLoc, yLoc;
        private Point loc;
        private Image image;
        private Path path;
        
        //the monster's hit box. stored as a rectangle object.
        private Rectangle bounds;
       
        public Monster(Image image, Path path, Point loc){
 
                this.loc = loc;
                this.image = image;
                this.path = path;
                xLoc = (int) loc.getX();
                yLoc = (int) loc.getY();
                speed = 1;
                
                //this is wrong. the bounding box is like 2x too big. 
                //the starting point is close to correct though, probably only ~5-10 pixels lower than it should be.
                //THOMMY BOY FIX THIS SHIT YO
                bounds = new Rectangle((float)loc.getX()-image.getWidth()/2,(float)loc.getY()-image.getHeight()/2,image.getWidth(),image.getHeight());
               
        }
       
        //moves the monster along the path
        public void move(){
               
                loc = path.nextPosition(loc, speed);
                xLoc = (int) (path.nextPosition(loc, speed)).getX();
                yLoc = (int) (path.nextPosition(loc, speed)).getY();
                //move the bounding box along with the monster
                bounds.setLocation(xLoc,yLoc);
        }
        
        public void draw(){
               
                image.draw(xLoc-image.getWidth()/2,yLoc-image.getWidth()/2);
               
        }
 
        public Rectangle getBounds(){
        	return bounds;
        }
        
        public int getX(){
                return xLoc;
        }
        public int getY(){
                return yLoc;
        }
       
        public Image getImage(){
                return image;
        }
       
}