package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;

public class King extends Piece {
	private int diffY;
	private int diffX;
	private Piece p;
	
	private Piece Rook;
	public ArrayList<Piece> Black_Pieces;
	public ArrayList<Piece> White_Pieces;

	public boolean hasMoved;
	
    public King(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean isKing()
    {
    	//System.out.println("Is King");
    	return true;
    }
    
    @Override
    public boolean isCheckmate()
    {
    	if(isChecked() == true)
    	{
    		return true;
    	}
    	return false;
    }
    
   
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {	
    	if(isCheckMate())
    	{
    		return false;
    	}
    	diffX = getX() - destination_x;
    	diffY = getY() - destination_y;
    	
    	p = King.this.board.getPiece(destination_x, destination_y);
    	
    	if(castle(destination_x, destination_y))
    	{
    		
    		return true;
    	}
		
		if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
		{
	    	if(((diffY < 2) && (diffY > -2)) && ((diffX < 2) && (diffX > -2)))
	    	{
	    		if(check(destination_x, destination_y))
	    		{
	    			hasMoved = true;
	    			return true;
	    		}
	    	}
		}
        
        return false;
    }
    
    public boolean castle(int destination_x, int destination_y)
    {
    	
    	//System.out.println("Check destination xy:" + destination_x + ',' + destination_y );

    	if (isWhite())
    	{
    		//System.out.println("Check is White");
    		if(hasMoved == false)
    		{
    		//	System.out.println("Check has Moved");
    			if((destination_x == 1) && (destination_y == 0))
    			{
    				//System.out.println("Check dest ok 81");
    				
    				Rook = King.this.board.getPiece(0, 0);
    				
    				if((Rook != null) && (King.this.board.getPiece(1, 0) == null) &&(King.this.board.getPiece(2, 0 )) == null )
    				{
    					//System.out.println("HasMoved " + Rook.hasMoved());
    					
    					if(Rook.hasMoved() == false)
    					{
    						//Check for checks from opponents in castle path
    						if(check(1, 0) && check(2, 0))
    						{
    							Rook.setX(2);
    							Rook.setY(0);
    						
    							return true;
    						}
    					}
    				}
    			}
    			else if((destination_x == 5) && (destination_y == 0))
    			{
    				//System.out.println("Check dest 50" );
    				Rook = King.this.board.getPiece(7, 0);
    				//System.out.println("rook" + Rook.getX());
    				
    				if((Rook != null) &&(King.this.board.getPiece(4, 0 )) == null &&(King.this.board.getPiece(5, 0) == null) && (King.this.board.getPiece(6, 0) == null))
    				{	
    					//System.out.println("path check ok");
    					if(Rook.hasMoved() == false)
    					{
    						//Check for checks from opponents in castle path
    						if(check(4, 0) && check(5, 0) && check(6, 0))
    						{
	    						//System.out.println("Truetrue!!!");
	    						Rook.setX(4);
	    						Rook.setY(0);
	    						return true;
    						}
    					}
    				}	
    			}
    		}
    	}
    	else 
    	{
    		//System.out.println("Check is White");
    		if(hasMoved == false)
    		{
    			//System.out.println("Check has Moved");
    			if((destination_x == 1) && (destination_y == 7))
    			{
    				//System.out.println("Check dest ok 81");
    				
    				Rook = King.this.board.getPiece(0, 7);
    				
    				if((Rook != null) && (King.this.board.getPiece(1, 7) == null) &&(King.this.board.getPiece(2, 7 )) == null )
    				{
    					//System.out.println("HasMoved " + Rook.hasMoved());
    					
    					if(Rook.hasMoved() == false)
    					{
    						
    						if(check(1, 7) && check(2, 7))
    						{
    							Rook.setX(2);
    							Rook.setY(7);
    							return true;
    						}
    						
    						
    					}
    				}
    			}
    			else if((destination_x == 5) && (destination_y == 7))
    			{
    				//System.out.println("Check dest 50" );
    				Rook = King.this.board.getPiece(7, 7);
    				//System.out.println("rook" + Rook.getX());
    				
    				if((Rook != null) &&(King.this.board.getPiece(4, 7 )) == null &&(King.this.board.getPiece(5, 7) == null) && (King.this.board.getPiece(6, 7) == null))
    				{	
    					//System.out.println("path check ok");
    					if(Rook.hasMoved() == false)
    					{
    						if(check(4, 0) && check(5, 0) && check(6, 0))
    						{
	    						//System.out.println("Truetrue!!!");
	    						Rook.setX(4);
	    						Rook.setY(7);
	    						return true;
    						}
    					}
    				}	
    			}
    		}
    	}
    	
    	return false;
    }
    
    public boolean isChecked()
    {
    	if(check(getX(),getY()))
    	{
    		return false;
    	}
    	return true;
    }
    
    public boolean isCheckMate()
    {
    	
    	if(check(getX(), getY()))
	    {
    		System.out.println("Not checked1");
	    	return false;
		}
    	if((getX() + 1 <= 7 ) && (getY()+1 <= 7))
    	{
    		if(check(getX() + 1, getY()+1))
    		{
    			System.out.println("11111111111111111");
    			return false;
    		}
    		
    	}
    	if((getX() - 1 >= 0 ) && (getY() - 1 >= 0))
    	{
    		if(check(getX() - 1, getY() - 1))
    	    {
    			System.out.println("2222222222222222");
    	    	return false;
    		}
    	}
    	if((getX() - 1 >= 0 ) && (getY()+1 <= 7))
    	{
    		if(check(getX() - 1, getY() + 1))
    	    {
    			System.out.println("33333333333333");
    	    	return false;
    		}
    	}
    	if((getX() + 1 <= 7 ) && (getY() - 1 >= 0))
    	{
    		if(check(getX() + 1, getY() - 1))
    	    {
    			System.out.println("44444444444444444");
    	    	return false;
    		}
    	}
    	if((getY() - 1 >= 0))
    	{
    		if(check(getX(), getY() - 1))
    	    {
    			System.out.println("5555555555555555555555");
    	    	return false;
    		}
    	}
    	if((getY() + 1 >= 0))
    	{
    		if(check(getX(), getY() + 1))
    	    {
    			System.out.println("666666666666666");
    	    	return false;
    		}
    	}
    	if((getX() - 1 >= 0))
    	{
    		if(check(getX() -1, getY()))
    	    {
    			System.out.println("777777777777777777777777");
    	    	return false;
    		}
    	}
    	if((getX() + 1 >= 0))
    	{
    		if(check(getX(), getY() + 1))
    	    {
    			System.out.println("888888888888888888888");
    	    	return false;
    		}
    	}
    	return true;
    }
    
    //check if King can move to destination
    public boolean check(int destination_x, int destination_y)
    {
    	if(isBlack())
    	{
	    	White_Pieces = King.this.board.getWhitePieces();
	    	for(int i = 1; i < White_Pieces.size(); i++)
	    	{
	    		if(White_Pieces.get(i).canMove(destination_x, destination_y) == true)
	    		{
	    			return false;
	    		}
	    	}
    	}
    	else
    	{
    		Black_Pieces = King.this.board.getBlackPieces();
	    	for(int i = 1; i < Black_Pieces.size(); i++)
	    	{
	    		if(Black_Pieces.get(i).canMove(destination_x, destination_y) == true)
	    		{
	    			return false;
	    		}
	    	}
    	}
    	return true;
    }  
}
