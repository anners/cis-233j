import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private InputReader reader;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line
        reader = new InputReader();
        
        int input = Integer.parseInt(reader.getInput()); //int i = Integer.parseInt(str);
      
       
        reader = null;
        
        ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>();

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);
        int i,x, y;
        Random rand = new Random();
        for ( i = 0, x = 0; i < input ; i++, x += 16) {
            y = rand.nextInt(ground/2);
            balls.add(new BouncingBall(x, y, 16, Color.BLUE, ground, myCanvas));
            balls.get(i).draw();
        }
        // crate and show the balls
        /*BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw(); */

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);
            for ( i = 0; i < input ; i++) {
      
                balls.get(i).move();
              
           
            // stop once ball has travelled a certain distance on x axis
                if(balls.get(i).getXPosition() >= 550) {
                    finished = true;
                }
            }
        }
    }
}
