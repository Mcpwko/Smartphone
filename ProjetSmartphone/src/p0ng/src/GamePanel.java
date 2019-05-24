import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 *  This is the main class. GamePanel derives from JPanel, and
 *  all the drawing is performed in the paintComponent() method.
 *  It implements ActionListener to handle Timer events, and
 *  implements KeyListener to handle keyboard events.
 *
 *  @author Andrew Lim
 *  @version 0.1
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener
{
  // Game Constants
  public static final int GAME_WIDTH      = 480 ;
  public static final int GAME_HEIGHT     = 800 ;
  public static final int STATE_MENU      = 0 ;
  public static final int STATE_PLAYING   = 1 ;  
  public static final int STATE_RIGHTWINS = 2 ;
  public static final int STATE_LEFTWINS  = 3 ;
  public static final int STATE_PAUSED    = 4 ;
  public static final Color GAME_BACKGROUND_COLOR = Color.BLACK ;
  public static final int  START_POINT = 0;

  // Ball Constants
  public static int BALL_WIDTH  = 6 ;
  public static int BALL_HEIGHT = 6 ;
  public static final Color BALL_COLOUR = Color.WHITE ;

  
  // Paddle Constants
  public static final int PADDLE_WIDTH  = 6 ;
  public static final int PADDLE_HEIGHT = 90 ;
  public static final int PADDLE_ARC    = PADDLE_WIDTH/2 ;    
  public static final int PADDLE_START_Y = (GAME_HEIGHT-PADDLE_HEIGHT)/2;
  public static final int LEFT_PADDLE_START_X = PADDLE_WIDTH ;
  public static final int RIGHT_PADDLE_START_X = GAME_WIDTH-PADDLE_WIDTH*2;
  public static final Color LEFT_PADDLE_COLOUR  = Color.GREEN ;
  public static final Color RIGHT_PADDLE_COLOUR = Color.RED ;
  public static final int PADDLE_SPEED = 8 ;
  
  // Game Timer
  Timer timer = new Timer( 20, this );
  
  // Game state - initial state is menu
  int gameState = STATE_MENU ;
  
  // number of players
  int playerCount = 1 ;
  
  // Ball data
  public static final int BALL_SPEED = 4 ;
  public static final int MAX_SPEED = 12;
  public int acceleration = 1;
  int ballXi = BALL_SPEED, ballYi = BALL_SPEED ;
  Rectangle ball = new Rectangle
  (
    (GAME_WIDTH-BALL_WIDTH)/2,
    (GAME_HEIGHT-BALL_HEIGHT)/2,
    BALL_WIDTH,
    BALL_HEIGHT
  );
  
  // Left Paddle
  Rectangle rightPaddle = new Rectangle( LEFT_PADDLE_START_X,
                                        PADDLE_START_Y,
                                        PADDLE_WIDTH,
                                        PADDLE_HEIGHT);
  
  // Right Paddle
  Rectangle leftPaddle = new Rectangle( RIGHT_PADDLE_START_X,
                                         PADDLE_START_Y,
                                         PADDLE_WIDTH,
                                         PADDLE_HEIGHT);
  
  // Game rectangle
  Rectangle gameRectangle = new Rectangle( 0, 0, GAME_WIDTH, GAME_HEIGHT );
  
  // This array of boolean values saves the keyboard state.
  // If K is a key constant, keys[K] is true if K is being
  // pressed and false if K is not being pressed.
  boolean[] keys = new boolean[256];
  
  /**
   *  Creates a GamePanel.
   */ 
  public GamePanel()
  {
    setBackground( GAME_BACKGROUND_COLOR );
    setPreferredSize( new Dimension(GAME_WIDTH+1,GAME_HEIGHT+1) );
  }
  
  /**
   *  Resets the position of the ball.
   */
  void resetBall()
  {
    ball.x = (GAME_WIDTH-BALL_WIDTH)/2 ;
    ball.y = (GAME_HEIGHT-BALL_HEIGHT)/2 ;
    //ballXi = BALL_SPEED ;
    //ballYi = BALL_SPEED ;
  }
  
  /**
   *  Resets the positions of the paddles.
   */
  void resetPaddles()
  {
    leftPaddle.x  = LEFT_PADDLE_START_X ;
    rightPaddle.x = RIGHT_PADDLE_START_X ;    
    leftPaddle.y  = rightPaddle.y = PADDLE_START_Y ;        
  }
  
  public static void main( String[] args )
  {
    GamePanel gamePanel = new GamePanel();
    JFrame frame = new JFrame( "p0ng v0.1 © Andrew Lim 2005" );
    frame.setResizable( false );
    frame.setContentPane( gamePanel );  
    frame.pack();
    frame.setLocationRelativeTo( null );
    frame.setVisible( true );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   
    frame.addKeyListener( gamePanel );    
    gamePanel.timer.start();
  }
  
  /**
   *  Called each game cycle, this lets the computer move
   *  the left paddle. The AI used by the computer is very
   *  simple. It just tries to align the left paddle's 
   *  vertical center with the ball's center.
   */
  void moveLeftPaddle()
  {
    int paddleCenterY = rightPaddle.y + PADDLE_HEIGHT/2 ;
    int ballCenterY = ball.y + BALL_HEIGHT/2 ;
    
    if ( paddleCenterY > ballCenterY )
    {
      if ( rightPaddle.y - PADDLE_SPEED >= 0 )
      {
        rightPaddle.y -= PADDLE_SPEED ;
      }
    }
    
    else if ( paddleCenterY < ballCenterY )
    {
      if ( rightPaddle.y + PADDLE_HEIGHT + PADDLE_SPEED <= GAME_HEIGHT )
      {
        rightPaddle.y += PADDLE_SPEED ;
      }
    }
  }
  
  /**
   *  Called every game cycle.
   */
  private void update()
  {
    if ( gameState != STATE_PLAYING )
      return ;
      
    // right paddle player's movement
    if ( keys[KeyEvent.VK_UP] )
      if ( rightPaddle.y - PADDLE_SPEED >= 0 )
        leftPaddle.y -= PADDLE_SPEED ;
    if ( keys[KeyEvent.VK_DOWN] )
      if ( leftPaddle.y + PADDLE_HEIGHT + PADDLE_SPEED <= GAME_HEIGHT )
        leftPaddle.y += PADDLE_SPEED ;
    
    if ( playerCount == 2 )
    {
    // left paddle player's movement
    
      if ( keys[KeyEvent.VK_A] )
        if ( rightPaddle.y - PADDLE_SPEED >= 0 )
          rightPaddle.y -= PADDLE_SPEED ;
      if ( keys[KeyEvent.VK_Z] )
        if ( rightPaddle.y + PADDLE_HEIGHT + PADDLE_SPEED <= GAME_HEIGHT )
          rightPaddle.y += PADDLE_SPEED ;
    }
    else
    {
      moveLeftPaddle();          
    }
      
    ball.x += ballXi ;
    ball.y += ballYi ;

    if ( ball.intersects( leftPaddle ) )
    {
      if(ballXi >(-13)) {
        System.out.println("AVANT : " +ballXi);
        ball.x = leftPaddle.x + PADDLE_WIDTH;
        ballXi = -ballXi + acceleration ;
        System.out.println(ballXi);
      }else {
        ball.x = leftPaddle.x + PADDLE_WIDTH;
        ballXi = -ballXi;
        System.out.println(ballXi);
      }
    }
    else if ( ball.intersects( rightPaddle ) )
    {
      ball.x = rightPaddle.x - BALL_WIDTH;
      ballXi = -ballXi ;
    }    
    else if ( ball.x < 0 )
    {
      ball.x = 0 ;
      gameState = STATE_RIGHTWINS ;
    }    
    else if ( ball.x > GAME_WIDTH - BALL_WIDTH )
    {
      ball.x = GAME_WIDTH - BALL_WIDTH ;     
      gameState = STATE_LEFTWINS ;
    }
    else if ( ball.y < 0 )
    {
      ball.y = 0 ;        
      ballYi = -ballYi ;     
    }      
    else if ( ball.y > GAME_HEIGHT - BALL_HEIGHT )
    {
      ball.y = GAME_HEIGHT - BALL_HEIGHT ;    
      ballYi = -ballYi ;    
    }
  } 
  
  /**
   *  Paints the GamePanel. This method draws the game.
   */
  public void paintComponent( Graphics g )
  {
    Graphics2D g2D = (Graphics2D)g;
    super.paintComponent( g );
    
    // Ball
    g2D.setColor( BALL_COLOUR );
    g2D.fill( ball );
    
    // Left Paddle
    g2D.setColor( LEFT_PADDLE_COLOUR );
    g2D.fillRoundRect( leftPaddle.x, 
                       leftPaddle.y, 
                       leftPaddle.width, 
                       leftPaddle.height, 
                       PADDLE_ARC, 
                       PADDLE_ARC );
    
    // Right Paddle
    g2D.setColor( RIGHT_PADDLE_COLOUR );
    g2D.fillRoundRect( rightPaddle.x, 
                       rightPaddle.y, 
                       rightPaddle.width, 
                       rightPaddle.height, 
                       PADDLE_ARC, 
                       PADDLE_ARC );
    
    if ( gameState != STATE_PLAYING )
    {
      String msg ;
      
      switch( gameState )
      {
        case STATE_LEFTWINS:
          msg = "Left player wins! Press SPACE to Continue" ;
          break;
          
        case STATE_RIGHTWINS:        
          msg = "Right player Wins! Press SPACE to Continue" ;
          break;
          
        case STATE_PAUSED:
          msg = "Game Paused. Press SPACE to Continue" ; 
          break;
          
        default:
          msg = "New Game: How many players? (1/2)" ;
          break;        
      }
      
      FontMetrics fm = g2D.getFontMetrics();
      
      int x = (GAME_WIDTH - fm.stringWidth(msg)) / 2;
      int y = (GAME_HEIGHT + fm.getAscent()) / 2 - fm.getDescent()-100;

      g2D.setColor( Color.WHITE );
      g2D.drawString( msg, x, y );
    }
  }
  
  /**
   *  Handles action events.
   */
  public void actionPerformed( ActionEvent e )
  {
    // Timer
    if ( e.getSource() == timer )
    {
      update();
      repaint();
    }
  }
  
  public void keyPressed(KeyEvent e) 
  {
    keys[ e.getKeyCode() ] = true ;
    
    if ( gameState == STATE_MENU )
    {
      if ( keys[KeyEvent.VK_1] )
      {
        playerCount = 1 ; 
        gameState = STATE_PLAYING ;
        resetBall();
        resetPaddles();
      }
      else if ( keys[KeyEvent.VK_2] )
      {
        playerCount = 2 ;
        gameState = STATE_PLAYING ;
        resetBall();
        resetPaddles();
      }      
    }    
    else if  ( gameState == STATE_RIGHTWINS || gameState == STATE_LEFTWINS )
    {
      if ( keys[KeyEvent.VK_SPACE] )
      {    
        gameState = STATE_MENU ;
      }
    }
    else if ( gameState == STATE_PLAYING )
    {
      if ( keys[KeyEvent.VK_P] )
      {
        gameState = STATE_PAUSED ;
      }
    }
    else if ( gameState == STATE_PAUSED )
    {
      if ( keys[KeyEvent.VK_SPACE] )
      {
        gameState = STATE_PLAYING ;
      }
    }    
  }
  
  public void keyReleased(KeyEvent e) 
  {
    keys[ e.getKeyCode() ] = false ;
  }

  public void keyTyped(KeyEvent e) 
  {
  
  }
}
