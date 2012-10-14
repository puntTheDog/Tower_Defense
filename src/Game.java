import java.awt.Point;
import java.util.ArrayList;
 
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
/**
 * TowerDefense 1.0
 * 
 * Game class
 * 
 * @authors Kevin, Thomas
 *
 */

public class Game extends BasicGame{
       
        private Monster mon;
        private Path path;
        private Tower tower;
        private ArrayList<Monster> monsters;
        private ArrayList<Tower> towers;
        private Input input;
        private Image reset = null;
       
 
    public Game()
    {
    	//chooses the window title
        super("FUCK YE HOMIE");
   
    }
 
    @Override
    //this method is called only when the game initially starts
    public void init(GameContainer gc)
                        throws SlickException {
       
    	//create the path for the monsters 
        path = new Path("data/path1.txt");
        mon = new Monster(new Image("data/monster.png"), path, path.getStart());
        
        //establish the input object for taking in mouse positions
        input = new Input(600);
        
        //these arraylists keep track of the current towers and monsters
        towers = new ArrayList<Tower>();
        monsters = new ArrayList<Monster>();
        
        
        monsters.add(mon);
        
        //reset button
        reset = new Image("data/reset.png");
       
 
    }
 
    @Override
    //this method is called every frame update
    public void update(GameContainer gc, int delta)
                        throws SlickException    
    {
       
    	//enhanced for-loop.
    	//moves the monsters every frame udpate
       for (Monster m: monsters){
    	   m.move();
       }
        
       //for every tower, check if every monster is within range.
       //if so, shoot it. 
        for (Tower t: towers){
        	for (Monster m: monsters){
        		if (t.getAoe().intersects(m.getBounds())){
        			t.shoot(m);
        		}
        	}
        }
        
        //if you click in the region of the screen where the reset button is...
        //clear the monsters, clear the towers, and reset the game. 
        if ((input.getMouseX()>725 && input.getMouseX()<772)&&(input.getMouseY()>550 && input.getMouseY()<566)){
        	if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){

        		towers.clear();
        		monsters.clear();
        		
        	}
        }
       
        //make a tower where you click (as long as its not on the reset button)
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && input.getMouseX() < 725){
                tower = new Tower(new Image("data/tower.png"), new Point(input.getMouseX(),input.getMouseY()));
                towers.add(tower);
        }
        
        //this is just for my desktop, for some reason the program runs at like 10000000 fps
        //so this just tells the game loop to chill out for 30 milliseconds before firing again
        //you can just comment this block out
        try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
 
    //called every frame update just like update() method
    //this method is just for graphical updates though
    public void render(GameContainer gc, Graphics g)
                        throws SlickException
    {
 
        path.draw(g);
        g.setColor(Color.blue);
        
        reset.draw(725,550);
       
        //draw the tower, as well as an oval representing it's AoE.
        //the drawLine was just for me to make sure that the actual AoE lined up
        //with the circle that was being drawn. 
        for (Tower t: towers){
                t.draw();
                g.drawOval(t.getX()-t.getAoe().getBoundingCircleRadius(), t.getY()-t.getAoe().getBoundingCircleRadius(), t.getRadius()*2, t.getRadius()*2);
                g.drawLine(t.getAoe().getCenterX(), t.getAoe().getCenterY(), t.getAoe().getCenterX(), t.getAoe().getCenterY()-t.getRadius());
 
        }
        
        //draw the monsters, as well as their fail hit boxes. 
        //FIX THIS SHIT THOMMY BOY
        for (Monster m: monsters){
        	m.draw();
        	g.drawRect((float)m.getX()-m.getImage().getWidth()/2,(float)m.getY()-m.getImage().getHeight()/2,m.getImage().getWidth(),m.getImage().getHeight());
        	g.drawLine(m.getX()-22, m.getY()-13, m.getX()+44, m.getY()+27);
        }
       
    }
 
    public static void main(String[] args)
                        throws SlickException
    {
         AppGameContainer app =
                        new AppGameContainer(new Game());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}