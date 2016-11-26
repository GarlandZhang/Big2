public class AI extends Player
{
	
	private int difficultyLevel; //level of difficulty of AI

	/*
	 * AI -> sets basic components of AI
	 */
	public AI()
	{
		super();
		difficultyLevel = 1;
	}
	
	public AI( String name )
	{
		super( name );
	}
	
	/*
	 * setLevel -> sets level of AI
	 * @param level -> the level to set AI to
	 */
	public void setLevel( int level )
	{
		if( level == 1 )
		{
			getHand().arrange();
		}
		else if( level == 3 )
		{
			getHand().shuffle();
		}
		
		difficultyLevel = level;
	}
	
	/*
	 * getLevel -> gets level of AI
	 * @return int -> level of AI
	 */
	public int getLevel()
	{
		return difficultyLevel;
	}
	
	//getWeakestComboHelper -> helps findWeakestCombo; brute force technique is applied to iterate through all possible combinations. . .recursive approach takes too much space
	//@param compareCombo -> see getWeakestCombo
	//@param size -> size of compareCombo
	public void findWeakestComboHelper( Combo compareCombo, int size )
	{
		Combo combo = getCombo();
		
		Hand hand = getHand();
		
		int handSize = hand.getSize();
		boolean has3D = hand.hasCard( new CardClass( 3, 1 ) );
		
		//brute force to find all possible combinations
		for( int a = 0; a < handSize - size + 1; a = a + 1 )
		{
			combo.addCard( hand.dealCard( a ) );
			
			if( size >= 2 )
			{
				for( int b = a + 1; b < handSize - size + 2; b = b + 1 )
				{
					combo.addCard( hand.dealCard( b ) );
					
					if( size >= 3 )
					{
						for( int c = b + 1; c < handSize - size + 3; c = c + 1 )
						{
							combo.addCard( hand.dealCard( c ) );
							
							if( size >= 4 )
							{
								for( int d = c + 1; d < handSize - size + 4; d = d + 1 )
								{
									combo.addCard( hand.dealCard( d ) );
									
									if( size == 5 )
									{
										for( int e = d + 1; e < handSize; e = e + 1 )
										{
											combo.addCard( hand.dealCard( e ) );
											
											if( combo.isPlayable( compareCombo, has3D ) )
											{
												return;
											}
											combo.removeCard( hand.dealCard( e ) );
										}
									}
									else if( combo.isPlayable( compareCombo, has3D ) )
									{
										return;
									}
									combo.removeCard( hand.dealCard( d ) );
								}
							}
							else if( combo.isPlayable( compareCombo, has3D ) )
							{
								return;
							}
							combo.removeCard( hand.dealCard( c ) );
						}
					}
					else if( combo.isPlayable( compareCombo, has3D ) )
					{
						return;
					}
					combo.removeCard( hand.dealCard( b ) );
				}
			}
			else if( combo.isPlayable( compareCombo, has3D ) )
			{
				return;
			}
			combo.removeCard( hand.dealCard( a ) );
		}
		combo.setComboType();
	}
	
	//findWeakestCombo -> finds the weakest but still stronger combo (or in this case, possible combo) to compare to
	//@param compareCombo -> the combo to compare to, should be play deck
	//@return Combo -> return least possible 
	public void findWeakestCombo( Combo compareCombo )
	{
		findWeakestComboHelper( compareCombo, compareCombo.getSize() );
	}

	/*
	 * getAnything -> gets any possible and valid combination
	 */
	public void getAnything() {
		// TODO Auto-generated method stub
		int i = 0;
		
		if( difficultyLevel <= 2 )
		{
			i = (int)( Math.random() * 5 + 1 );
		}
		else if( difficultyLevel == 3 )
		{
			i = 5;
		}
		
		for( ; i >= 1 && getCombo().getComboType() == 0; i = i - 1 )
		{
			if( i != 4 )
			{
				findWeakestComboHelper( null, i );
			}
		}
	}
}
