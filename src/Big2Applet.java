//Name: Big2Applet.java
//Date: 1/1/2016
//Created by: Zhang, Garland
//Description: Main program for applet

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.*;
import java.io.File;
import java.util.Scanner;


@SuppressWarnings("serial")
public class Big2Applet extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //used to help identify screen size of applelt
	private int SCREEN_WIDTH = (int)screenSize.getWidth(); //screen width of applet
	private int SCREEN_HEIGHT = (int)screenSize.getHeight(); //screen height of applet
	
	private Graphics g; //helps to draw 

	private GridBagLayout globalLayout; //layout used for applet
	private GridBagConstraints constraint; //constraints to restrict button position and size
	
	//main menu
	
	private Button rulesButton; //button that redirects to rules "window"
	private Button settingsButton; //button that redirects to settings "window"
	private Button originsButton; //button that redirects to origins "window"
	private Button backButton; //button that redirects back to game "window"
	
	private Button quitButton; //button that exits the applet and terminates program
	private Button restartButton; //button that restarts the game and automatically directs to game "window"
	
	//main menu
	
	//rules
	
	//rules
	
	//settings
	
	private TextField nameBox; //where user can input name choice
	private CheckboxGroup difficulty;
			private Checkbox easyLevel;
			private Checkbox normalLevel;
			private Checkbox hardLevel;
			
	private Choice screenSizes;
	private Checkbox musicOn;
				
	//settings
	
	//game
	
	private GameBoard game; //object of gameboard used to play game
	private Human person; //pointer to human player
	
	private Button playButton; //button used for playing current combo selected
	private Button passButton; //button used to pass user
	private Button arrangeButton; //button used to rearrange order of cards, ascending or descending

	//game
	
	
	//ending

	//ending
	
	private String currentFrame; //used to track which frame or "window" the user is currently on
	
	//init -> used to initialize variables
	public void init()
	{
		this.setSize( SCREEN_WIDTH, SCREEN_HEIGHT ); //sets applet screen size to be SCREEN_WIDTH, SCREEN_HEIGHT
		
		globalLayout = new GridBagLayout(); //instantiates an object of GridBagLayout
		setLayout( globalLayout ); //sets applet to globalLayout
		
		constraint = new GridBagConstraints(); //instantiates an object of GridBagCOnstraints to restrict AWT objects
	
		constraint.ipadx = 20; //increases size of buttonsx
		constraint.ipady = 20; //increases size of buttonsy
		constraint.weightx = 1; //evenly distributes x coordinates of objects
		constraint.weighty = 1; //evenly distribute y coordinates of objects
		constraint.anchor = GridBagConstraints.FIRST_LINE_START; //repositions successive AWT objects to be positioned at the top of the screen
		
		addMouseListener( this ); //adds a mouse listener to applet for mouse input
		addMouseMotionListener( this ); //adds a mouse motion listener to applet for mouse input
		
		g = getGraphics(); //gets graphics of applet
		
		currentFrame = "Start"; //initializes current frame	
		
		//main menu
		
		constraint.gridx = 0; //positions successive objects to be at the 0 x mark
		constraint.gridy = 0; //positions successive objects to be at the 0 y mark
		
		rulesButton = new Button( "How to play" ); //instantiates an object of Button; used to redirect to Rules "window"
		rulesButton.addActionListener( this ); //adds action listener to rules button
		
		globalLayout.setConstraints( rulesButton, constraint ); //sets the constraints for the button
		
		constraint.gridx = 1;
		constraint.gridy = 0;
		
		settingsButton = new Button( "Settings" );
		settingsButton.addActionListener( this );
		
		globalLayout.setConstraints( settingsButton, constraint );
		
		constraint.gridx = 2;
		constraint.gridy = 0;
		
		originsButton = new Button( "Creators" );
		originsButton.addActionListener( this );
		
		globalLayout.setConstraints( originsButton, constraint );
		
		constraint.gridx = 3;
		constraint.gridy = 0;
		
		add( rulesButton ); //adds button to the applet
		add( settingsButton );
		add( originsButton );
		
		backButton = new Button( "Back to game" );
		backButton.addActionListener( this );			
		
		globalLayout.setConstraints( backButton, constraint );
		
		add( backButton );
		
		constraint.gridx = 4;
		constraint.gridy = 0;
		
		constraint.anchor = GridBagConstraints.FIRST_LINE_END;
		
		restartButton = new Button( "Restart" );
		restartButton.addActionListener( this );
		
		globalLayout.setConstraints( restartButton, constraint );
		
		constraint.gridx = 5;
		constraint.gridy = 0;
		
		quitButton = new Button( "Quit" );
		quitButton.addActionListener( this );
		
		globalLayout.setConstraints( quitButton, constraint );
		
		add( restartButton );
		add( quitButton );

		//main menu

		//settings

		constraint.ipadx = 60;
		constraint.ipady = 30;
		
		constraint.gridx = 1;
		constraint.gridy = 1;
		
		constraint.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		
		nameBox = new TextField(); //instantiates an object of TextField
		nameBox.addActionListener( this );
		
		globalLayout.setConstraints( nameBox, constraint );
		
		difficulty = new CheckboxGroup();
		
		constraint.gridx = 1;
		constraint.gridy = 2;
		
		easyLevel = new Checkbox( "Easy", difficulty, true );
		
		globalLayout.setConstraints( easyLevel, constraint );
		
		constraint.gridx = 2;
		constraint.gridy = 2;
		
		normalLevel = new Checkbox( "Normal", difficulty, false );
		
		globalLayout.setConstraints( normalLevel, constraint );
		
		constraint.gridx = 3;
		constraint.gridy = 2;
		
		hardLevel = new Checkbox( "JOHNCENA", difficulty, false );
		
		globalLayout.setConstraints( hardLevel, constraint );
		
		constraint.gridx = 1;
		constraint.gridy = 3;
		
		constraint.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		
		screenSizes = new Choice();
		screenSizes.add( "Small" );
		screenSizes.add( "Medium" );
		screenSizes.add( "Large" );
		
		globalLayout.setConstraints( screenSizes, constraint );

		constraint.gridx = 1;
		constraint.gridy = 4;
		
		constraint.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		
		musicOn = new Checkbox( "On", true );
		
		globalLayout.setConstraints( musicOn, constraint );
		
		add( nameBox );
		add( easyLevel );
		add( normalLevel );
		add( hardLevel );
		add( screenSizes );
		add( musicOn );
		
		//settings
		
		//game
		
		constraint.ipadx = 50;
		constraint.ipady = 50;
		
		constraint.gridx = 0;
		constraint.gridy = 4;
		
		constraint.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		
		playButton = new Button( "Play" );
		playButton.addActionListener( this );
		
		globalLayout.setConstraints( playButton, constraint );
		
		constraint.gridx = 2;
		constraint.gridy = 4;
		
		constraint.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		
		passButton = new Button( "Pass" );
		passButton.addActionListener( this );
		
		globalLayout.setConstraints( passButton, constraint );
		
		constraint.gridx = 4;
		constraint.gridy = 4;
		
		constraint.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		
		arrangeButton = new Button( "Arrange" );
		arrangeButton.addActionListener( this );
		
		globalLayout.setConstraints( arrangeButton, constraint );

		add( playButton );
		add( arrangeButton );
		add( passButton );
		
		//game
		
	}

	//start -> after init
	public void start()
	{
		if( currentFrame.equals( "Start" ) || currentFrame.equals( "Restart" ) )
		{
			
			//global
			
				setGlobalButtons( false );
			
			//global
			
			//game
			
				currentFrame = "Game";
			
				game = new GameBoard( this.getHeight(), this.getWidth(), 0, 0, 0 );
		
				person = (Human) game.getPlayer( 0 ); //person is first player	

			//game
				
			//settings
				setSettings( false );
				musicOn.setState( true );
				game.turnMusic( musicOn.getState() );
			//settings
				
				
				if( !person.getPlayerTurn() )
				{
					paint( g );
					game.delay( 1000 );
					playAI();
				}
		}
	}
	
	/*
	 * action -> overrides action from Applet
	 */
	public boolean action( Event e, Object o )
	{
		if( e.target instanceof Choice )
		{
			if( e.target == screenSizes )
			{
				this.setSize( SCREEN_WIDTH = (int)screenSize.getWidth() / ( 3 - screenSizes.getSelectedIndex() ), SCREEN_HEIGHT = (int)screenSize.getHeight() / ( 3 - screenSizes.getSelectedIndex() ) );			}
			}
		else if( e.target instanceof Checkbox )
		{
			if( e.target == musicOn )
			{
				game.turnMusic( musicOn.getState() );
			}
			else if( e.target == easyLevel )
			{
				game.setLevel( 1 );
			}
			else if( e.target == normalLevel )
			{
				game.setLevel( 2 );
			}
			else if( e.target == hardLevel )
			{
				game.setLevel( 3 );
			}
		}
		return true;
	}
	
	/*
	 * actionPerformed -> overrides actionPerformed in ActionListener
	 */
	public void actionPerformed( ActionEvent e )
	{
		Object o = e.getSource();
		
			//if button
			if( o instanceof Button )
			{	
				if( o == rulesButton )
				{
					currentFrame = "Rules";
					setPlayButtons( false );
					setSettings( false );
				}
				else if( o == settingsButton )
				{
					currentFrame = "Settings";
					setPlayButtons( false );
					setSettings( true );
				}
				else if( o == originsButton )
				{
					currentFrame = "Origins";
					setPlayButtons( false );
					setSettings( false );
				}
				else if( o == backButton )
				{
					if( game.winnerFound() )
					{
						currentFrame = "End";
					}
					else
					{
						currentFrame = "Game";
					}
					setPlayButtons( true );
					setSettings( false );
				}
				else if( o == restartButton )
				{
					currentFrame = "Restart";
					setPlayButtons( true );
					start();
				}
				else if( o == quitButton )
				{
					System.exit( 0 );
				}
				else if( o == arrangeButton )
				{
					person.getHand().arrange();
					person.getHand().setCentre( person.getHand().getX(), person.getHand().getY() );
				}
				else
				{
					if( person.getPlayerTurn() )
					{
						if( o == playButton )
						{
							if( person.getCombo().isPlayable( game.getPlayDeck(), person.getHand().hasCard( new CardClass( 3, 1 ) ) ) )
							{
								game.getPlayDeck().setDeck( person.getCombo().getDeck() );
								game.getPlayDeck().setAll();
								person.play();
								
								if( person.getHand().getSize() == 0 )
								{
									person.win();
								}
								else
								{
									game.setNextPlayer();
									playAI();
								}
							}
						}
						else if( o == passButton )
						{
							if( !game.allPassed() && !person.getHand().hasCard( new CardClass( 3, 1 ) ) )
							{
								//can pass, AI play
								person.pass();
								game.setNextPlayer();
								playAI();
							}
						}
					}
					if( game.winnerFound() )
					{
						currentFrame = "End";
						setPlayButtons( false );
						setGlobalButtons( false );
					}
				}
			}
			//if text field
			else if( o instanceof TextField )
			{
				if( o == nameBox )
				{
					person.setName( ((TextField) o).getText() );
				}
			}
			
		repaint();
	}
	
	/*
	 * paint -> overrides paint in Applet
	 */
	public void paint( Graphics g )
	{
		game.draw( g );
		
		if( currentFrame.equals( "Rules" ) || currentFrame.equals( "Settings" ) || currentFrame.equals( "Origins" ) )
		{
			Font f = new Font( "Times New Roman", Font.PLAIN, SCREEN_HEIGHT / 35 );
			g.setFont( f );
			
			try {
				
				g.setColor( Color.WHITE );
					Scanner sc = new Scanner( new File( getClass().getResource( "/TextFiles/" + currentFrame + ".txt" ).toURI() ) );
					for( int i = 0; sc.hasNextLine(); i = i + 1 )
					{
						g.drawString( sc.nextLine(), 0, 100 + i * ( 3 * f.getSize() / 2 ) );
					}
					/*if( currentFrame.equals( "Rules" ) )
					{
						g.drawString( "<a href=\"http://www.pagat.com/climbing/bigtwo.html#combinations\"", 0, i * ( 3 * f.getSize() / 2 ) );
					}*/
					sc.close();
				} 
			catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else if( currentFrame.equals( "Game" ) || currentFrame.equals( "End" ) )
		{
			game.getPlayDeck().draw( g );
			game.drawPlayers( g );
		
			if( currentFrame.equals( "End" ) )
			{
				Font f = new Font( "Times New Roman", Font.PLAIN, 100 );
				g.setFont( f );
				g.setColor( Color.BLUE );
				g.drawString( game.getCurrentPlayer().getName() + " wins!", getWidth() / 4, getHeight() / 2 );
			}
		}
		
	}

	/*
	 * update -> overrides update in Applet 
	 */
	 public void update(Graphics g)
	 {
		Image offScreenImage = createImage( getWidth(), getHeight() );
		
		Graphics offScreenG = offScreenImage.getGraphics();
		    
		offScreenG.setColor( getBackground() );
		offScreenG.fillRect( getX(), getY(), getWidth(), getHeight() );
		offScreenG.setColor( getForeground() );
		 
		paint( offScreenG );
		    
		g.drawImage( offScreenImage, getX(), getY(), this );
	 }
	 
	 /*
	  * playAI -> plays the AI in game
	  */
	 public void playAI()
		{
			// TODO Auto-generated method stub
			
				//play anything
				Player temp;
				
				while( !( game.getCurrentPlayer() instanceof Human ) )
				{
					update( g );
					game.delay( 500 );
					
					temp = game.getCurrentPlayer();

					if( game.allPassed() )
					{
						game.startNewRound();
					}

						if( game.getPlayDeck().getComboType() == 0 )
						{
							( ( AI ) temp ).getAnything();
						}
						else
						{
							( ( AI ) temp ).findWeakestCombo( game.getPlayDeck() );
						}
						
						if( temp.getCombo().getComboType() != 0 )
						{						
							game.getPlayDeck().setDeck( temp.getCombo().getDeck() );
							game.getPlayDeck().setAll();
							temp.play();
							game.getPlayDeck().toggle();
							
							if( temp.getHand().getSize() == 0 )
							{
								temp.win();
								break;
							}
						}
						else
						{
							temp.pass();
						}
						
						update( g );
						game.delay( 500 );
						game.setNextPlayer();
				}
				if( game.allPassed() )
				{
					game.startNewRound();
				}
		}
	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseClicked( MouseEvent me ) {
		// TODO Auto-generated method stub
			for( int i = person.getHand().getSize() - 1; i >= 0; i = i - 1 )
			{
				if( person.getHand().dealCard( i ).isPointInside( me.getX(), me.getY() ) )
				{
					person.select( person.getHand().dealCard( i ) );
					repaint();
					break;
				}
			}
	}

	/*
	 * mouseEntered -> override mouseEntered in MouseEventListener
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * mouseExited -> override mouseExited in MouseEventListener
	 */	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * mousePressed -> override mousePressed in MouseEventListener
	 */
	public void mousePressed(MouseEvent me ) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * mouseReleased -> override mouseReleased in MouseEventListener
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * setGlobalButtons -> sets the global buttons to the given state
	 * @param state -> the state of the global buttons
	 */
	public void setGlobalButtons( boolean state )
	{
		backButton.setVisible( state );
	}
	
	/*
	 * setSettings -> sets the settings of the given state
	 * @param state -> the state of the settings buttons
	 */
	public void setSettings( boolean state )
	{
		nameBox.setVisible( state );
		easyLevel.setVisible( state );
		normalLevel.setVisible( state );
		hardLevel.setVisible( state );
		screenSizes.setVisible( state );
		musicOn.setVisible( state );
	}
	
	/*
	 * setPlayButtons -> sets the buttons in game for playing of the given state
	 * @param state -> the state of the play button
	 */
	public void setPlayButtons( boolean state )
	{
		backButton.setVisible( state );
		playButton.setVisible( state );
		passButton.setVisible( state );
		arrangeButton.setVisible( state );
		setGlobalButtons( !state );
	}
}
