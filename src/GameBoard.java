/*
 * Name: GameBoard.java
 * Date: 1/1/2016
 * Created By: Zhang, Garland
 * Description: Where game actions occur
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Vector;
import javax.imageio.ImageIO;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GameBoard extends ShapeClass
{
	private Vector<Player> players; //those in the game
	private StandardDeck handDeck; //where deck is dealed out to players
	private Combo playDeck; //where current round is held
	private int currentPosition; //what current player position is
	private final int MAX_PLAYERS = 4;
	
	private BufferedImage big2Image;
	
	private AudioStream big2Music;	
	
	/*
	 * GameBoard -> initializes base components of game board
	 */
	public GameBoard()
	{
		super();
		
			try {
				big2Image = ImageIO.read( getClass().getResource( "ImagesFiles/background.jpg" ) );
	    		big2Music = new AudioStream( new FileInputStream( "MusicFiles/music.wav" ) );
	    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		players = new Vector<Player>( 0, 1 );
		addPlayer( new Human( "You" ) );//you, the player
		
		handDeck = new StandardDeck();
		
		for( int i = 0; i < 10; i = i + 1 )
		{
			handDeck.shuffle();
		}

		playDeck = new Combo();
		
		currentPosition = 0;
		
		//ai
		for( int i = getPlayerSize(); i < MAX_PLAYERS; i = i + 1 )
		{
			addPlayer( new AI( "Player" + ( i + 1 ) ) );
		}

		dealOutCards();
		initializeTurn();

		for( Player p : players )
		{
			p.getHand().setAll();
			
			if( p instanceof AI )
			{
				p.getHand().arrange();
				p.getHand().toggle();
			}
		}	
	}
	
	public GameBoard( int uHeight, int uWidth, int uCentreX, int uCentreY, int uRotation )
	{
		this();
		setHeight( uHeight );
		setWidth( uWidth );
		setCentre( uCentreX, uCentreY );
		setRotation( uRotation );
	}
	
	/*
	 * setHeight -> overrides setHeight in ShapeClass
	 */
	public void setHeight( int uHeight )
	{
		super.setHeight( uHeight );
		playDeck.setHeight( uHeight / 10 );
		
		players.get( 0 ).getHand().setHeight( uHeight / 10 );
		for( int i = 1; i < getPlayerSize(); i = i + 1 )
		{
				getPlayer( i ).getHand().setHeight( uHeight / 15 );
		}
	}
	
	/*
	 * setWidth -> overrides setWidth in ShapeClass
	 */
	public void setWidth( int uWidth )
	{
		super.setWidth( uWidth );
		playDeck.setWidth( uWidth / 10 );
		
		players.get( 0 ).getHand().setWidth( uWidth / 10 );
		for( int i = 1; i < getPlayerSize(); i = i + 1 )
		{
				getPlayer( i ).getHand().setWidth( uWidth / 15 );
		}
	}
	
	/*
	 * setCentre -> overrides setCentre in ShapeClass
	 */
	public void setCentre( int uX, int uY )
	{
		super.setCentre( uX, uY );
		playDeck.setCentre( getWidth() / 2, getHeight() / 2 - playDeck.getHeight() / 2 );
		
		//players
		players.get( 0 ).getHand().setCentre( getWidth() / 2, getHeight() - players.get( 0 ).getHand().getHeight() / 2 );
		players.get( 1 ).getHand().setCentre( players.get( 1 ).getHand().getWidth() / 2, getHeight() / 2 - 50 );
		players.get( 2 ).getHand().setCentre( getWidth() / 2, players.get( 2 ).getHand().getHeight() / 2 );
		players.get( 3 ).getHand().setCentre( getWidth() - players.get( 3 ).getHand().getWidth() / 2 - 50, getHeight() / 2 - 55 );
	}
	
	/*
	 * setRotation -> overrides setRotation in ShapeClass
	 */
	public void setRotation( int iRotation )
	{
		super.setRotation(iRotation);
		for( int i = 0; i < getPlayerSize(); i = i + 1 )
		{
			getPlayer( i ).getHand().setRotation( i * 90 );
		}
	}
	
	/*
	 * setLevel -> sets the type of level for game for AI
	 * @param level -> the level to set AI to
	 */
	public void setLevel( int level )
	{
		Player p;
		for( int i = 0; i < getPlayerSize(); i = i + 1 )
		{
			p = getPlayer( i );
			if( !( p instanceof Human ) )
			{
				( ( AI )p ).setLevel( level );
			}
		}
	}
	
	/*
	 * dealOutCards -> deals out cards at beginning of game
	 */
	public void dealOutCards()
	{
		for( int numCards = 0; numCards < 13 && !handDeck.isEmpty(); numCards = numCards + 1 )
		{
			for( int i = 0; i < players.size(); i = i + 1 )
			{
				getPlayer( i ).getHand().addCard( handDeck.getTopCard() );
				handDeck.removeCard( 0 );
			}
		}
	}
	
	/*
	 * addPlayer -> adds player to game
	 * @param newPlayer -> the player to add
	 */
	public void addPlayer( Player newPlayer )
	{
		players.add( newPlayer );
	}
	
	/*
	 * removePlayer -> removes player from game
	 */
	public void removePlayer()
	{
		players.remove( players.size() - 1 );
	}
	
	/*
	 * getPlayers -> gets players in game
	 * @return Vector<Player> -> the players to return
	 */
	public Vector<Player> getPlayers()
	{
		return players;
	}

	/*
	 * getPlayer -> get player in game
	 * @param name -> the player by name
	 * @return Player -> the player to return
	 */
	public Player getPlayer( String name )
	{
		for( Player p : players )
		{
			if( p.getName().equals( name ) )
			{
				return p;
			}
		}
		return null;
	}
	
	/*
	 * getPlayer -> gets player in game by index
	 * @param position -> the position of player in vector
	 * @return Player -> the player to return
	 */
	public Player getPlayer( int position )
	{
		return players.get( position );
	}
	
	//setCurrentPlayer -> sets current player's turn to true
	//@param position -> position of current player
	public void setCurrentPlayer( int position )
	{
		setCurrentPlayerPosition( position );
		players.get( position ).setIsPassed( false );
		players.get( position ).setPlayerTurn( true );
	}
	
	/*
	 * setNextPlayer -> sets the next player's turn
	 */
	public void setNextPlayer()
	{
		setCurrentPlayer( ( currentPosition + 1 ) % getPlayerSize() );
	}
	
	/*
	 * getCurrentPlayer -> gets the current player
	 * @return Player -> the current player to return
	 */
	public Player getCurrentPlayer()
	{
		return players.get( currentPosition );
	}
	
	/*
	 * setCurrentPlayerPosition -> sets the current player position 
	 * @param position -> the position in the players vector to set with
	 */
	public void setCurrentPlayerPosition( int position )
	{
		currentPosition = position;
	}
	
	/*
	 * getCurrentPlayerPosition -> gets the current player position
	 * @return int -> the current player position
	 */
	public int getCurrentPlayerPosition()
	{
		return currentPosition;
	}
	
	/*
	 * getPlayerSize -> gets the number of players in game
	 * @return int -> the number of players
	 */
	public int getPlayerSize()
	{
		return getPlayers().size();
	}
		
	/*
	 * getHandDeck -> the standard deck to distribute cards to
	 * @return StandardDeck -> the deck to distribute
	 */
	public StandardDeck getHandDeck()
	{
		return handDeck;
	}
	
	/*
	 * getPlayDeck -> the deck where cards are collected during game
	 * @return Combo -> the combo to return
	 */
	public Combo getPlayDeck()
	{
		return playDeck;
	}
	
	/*
	 * allPassed -> checks if all players passed
	 * @return boolean -> if all players passed
	 */
	public boolean allPassed()
	{
		for( int i = 1; i < getPlayerSize(); i = i + 1 )
		{
			if( !getPlayer( ( i + currentPosition ) % getPlayerSize() ).getIsPassed() )
			{
				return false;
			}
		}
		return true;
	}
	
	/*
	 * initializeTurn -> at beginning of game, initializes first player
	 */
	public void initializeTurn()
	{
		//look for who has 3D to start game
		CardClass threeD = new CardClass( 3, 1 );
		for( int i = 0; i < getPlayerSize(); i = i + 1 )
		{
			if( getPlayer( i ).getHand().hasCard( threeD ) )
			{
				getPlayer( i ).setPlayerTurn( true );
				setCurrentPlayer( i );
				break;
			}
		}
	}
	
	/*
	 * startNewRound -> the start of a new round
	 */
	public void startNewRound()
	{
		playDeck.removeAll();
		playDeck.setComboType();
	}
	
	/*
	 * draw -> overrides draw in ShapeClass
	 */
	public void draw(Graphics g)
	{
		g.drawImage( big2Image, getX(), getY(), getWidth(), getHeight(), null );
	}
	
	/*
	 * drawPlayers -> draws all players' hands, name, and current state
	 * @param g -> the graphics to draw with
	 */
	public void drawPlayers( Graphics g )
	{
		Player p;
    	Graphics2D g2 = (Graphics2D)g;
        AffineTransform old = g2.getTransform();

        Font f = new Font( "Times New Roman", Font.PLAIN, 30 );
        g2.setFont( f );
        
		for( int i = 0; i < getPlayerSize(); i = i + 1 )
		{	        
			p = getPlayer( ( i + 1 ) % 4 );
			p.getHand().draw( g );
			
	    	g2.rotate( Math.toRadians( p.getHand().getRotation() ), p.getHand().getX(), p.getHand().getY() );
			g2.setColor( Color.BLUE );
			
			if( p.getIsPassed() )
			{
				g2.drawString( "Pass", p.getHand().getX(), p.getHand().getY() - ( 10 + p.getHand().getHeight() / 2 ) );
			}
			else if( p.getPlayerTurn() )
			{
				g2.drawString( "Thinking...", p.getHand().getX(), p.getHand().getY() - ( 10 + p.getHand().getHeight() / 2 ) );
				g2.setColor( Color.YELLOW );
			}
			if( !( p instanceof Human ) )
			{
				g2.drawString( p.getName(), p.getHand().getX() - 100, p.getHand().getY() - ( 10 + p.getHand().getHeight() / 2 ) );
			}
			else
			{
				g2.drawString( p.getName(), p.getHand().getX() - ( 200 + 10 * p.getName().length() ), p.getHand().getY() - ( 10 + p.getHand().getHeight() / 2 ) );
			}
			
	        g2.setTransform(old);
		}
	}

	/*
	 * winnerFound -> checks if there is a winner
	 * @return boolean -> if player wins
	 */
	public boolean winnerFound()
	{
		for( Player p : players )
		{
			if( p.isWinner() )
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * delay -> delays time in game
	 * @param iDelayTime -> the amount of time to delay with
	 */
    public void delay (int iDelayTime)
    {
		long lFinalTime = System.currentTimeMillis () + iDelayTime;
		do
		{
		}
	
		while (lFinalTime >= System.currentTimeMillis ());
	}
    
    /*
     * getImage -> gets the image background
     * @return BufferedImage -> the image to project in the background
     */
    public BufferedImage getImage()
    {
    	return big2Image;
    }
    
    /*
     * turnMusic -> turns on music depending on state
     */
    public void turnMusic( boolean state )
    {
    	if( state )
    	{
    		//play music
    		AudioPlayer.player.start( big2Music );
    	}
    	else
    	{
    		//stop music
    		AudioPlayer.player.stop( big2Music );
    	}
    }
}
