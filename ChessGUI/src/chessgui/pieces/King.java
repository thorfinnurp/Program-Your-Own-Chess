package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;
import chessgui.pieces.Piece.Moves;

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
    	int currPosX = getX();
    	int currPosY = getY();
    	if(isCheckMate())
    	{
    		return false;
    	}
    	diffX = getX() - destination_x;
    	diffY = getY() - destination_y;
    	
    	p = King.this.board.getPiece(destination_x, destination_y);
    	
    	if(castle(destination_x, destination_y))
    	{
    		
    		setX(destination_x);
    		setY(destination_y);
    		if(isChecked() == true)
    		{
    			setX(currPosX);
        		setY(currPosY);
    			return false;
    		}
    		setX(currPosX);
    		setY(currPosY);
    		  		
    		return true;
    	}
		
		if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
		{
	    	if(((diffY < 2) && (diffY > -2)) && ((diffX < 2) && (diffX > -2)))
	    	{
	    		if(check(destination_x, destination_y))
	    		{
	    			setX(destination_x);
	        		setY(destination_y);
	        		if(isChecked() == true)
	        		{
	        			setX(currPosX);
	            		setY(currPosY);
	        			return false;
	        		}
	        		setX(currPosX);
	        		setY(currPosY);
	    			hasMoved = true;
	    			return true;
	    		}
	    	}
		}
        
        return false;
    }
    
    @Override
    public boolean canMoveCheckMate(int destination_x, int destination_y)
    {	

    	diffX = getX() - destination_x;
    	diffY = getY() - destination_y;
    	
    	p = King.this.board.getPiece(destination_x, destination_y);
		
		if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
		{
	    	if(((diffY < 2) && (diffY > -2)) && ((diffX < 2) && (diffX > -2)))
	    	{
	    		return true;
	    	}
		}
        
        return false;
    } 
    
    public boolean castle(int destination_x, int destination_y)
    {
    	if (isWhite())
    	{
    		if(hasMoved == false)
    		{
    			if((destination_x == 1) && (destination_y == 0))
    			{    				
    				Rook = King.this.board.getPiece(0, 0);
    				if((Rook != null) && (King.this.board.getPiece(1, 0) == null) &&(King.this.board.getPiece(2, 0 )) == null )
    				{    					
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
    				Rook = King.this.board.getPiece(7, 0);
    				if((Rook != null) &&(King.this.board.getPiece(4, 0 )) == null &&(King.this.board.getPiece(5, 0) == null) && (King.this.board.getPiece(6, 0) == null))
    				{	
    					if(Rook.hasMoved() == false)
    					{
    						//Check for checks from opponents in castle path
    						if(check(4, 0) && check(5, 0))
    						{
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
    		if(hasMoved == false)
    		{
    			if((destination_x == 1) && (destination_y == 7))
    			{
    				Rook = King.this.board.getPiece(0, 7);
    				if((Rook != null) && (King.this.board.getPiece(1, 7) == null) &&(King.this.board.getPiece(2, 7 )) == null )
    				{	
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
    				Rook = King.this.board.getPiece(7, 7);
    				if((Rook != null) &&(King.this.board.getPiece(4, 7 )) == null &&(King.this.board.getPiece(5, 7) == null) && (King.this.board.getPiece(6, 7) == null))
    				{	
    					if(Rook.hasMoved() == false)
    					{
    						if(check(4, 7) && check(5, 7))
    						{
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
	    	return false;
		}
    	if((getX() + 1 <= 7 ) && (getY()+1 <= 7))
    	{
    		if(check(getX() + 1, getY()+1))
    		{
    			return false;
    		}
    		
    	}
    	if((getX() - 1 >= 0 ) && (getY() - 1 >= 0))
    	{
    		if(check(getX() - 1, getY() - 1))
    	    {
    	    	return false;
    		}
    	}
    	if((getX() - 1 >= 0 ) && (getY()+1 <= 7))
    	{
    		if(check(getX() - 1, getY() + 1))
    	    {
    	    	return false;
    		}
    	}
    	if((getX() + 1 <= 7 ) && (getY() - 1 >= 0))
    	{
    		if(check(getX() + 1, getY() - 1))
    	    {
    	    	return false;
    		}
    	}
    	if((getY() - 1 >= 0))
    	{
    		if(check(getX(), getY() - 1))
    	    {
    	    	return false;
    		}
    	}
    	if((getY() + 1 >= 0))
    	{
    		if(check(getX(), getY() + 1))
    	    {
    	    	return false;
    		}
    	}
    	if((getX() - 1 >= 0))
    	{
    		if(check(getX() -1, getY()))
    	    {
    	    	return false;
    		}
    	}
    	if((getX() + 1 >= 0))
    	{
    		if(check(getX(), getY() + 1))
    	    {
    	    	return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public String getChecked()
    {
    	Integer newX;
    	Integer newY;
    	
    	if(isBlack())
    	{
	    	White_Pieces = King.this.board.getWhitePieces();
	    	for(int i = 1; i < White_Pieces.size(); i++)
	    	{
	    		if(White_Pieces.get(i).canMoveCheckMate(getX(), getY()) == true)
	    		{
	    			newX = White_Pieces.get(i).getX();
	    			newY = White_Pieces.get(i).getY();
	    				
	    			return newX.toString() + newY.toString();
	    		}
	    	}
    	}
    	else
    	{
    		Black_Pieces = King.this.board.getBlackPieces();
	    	for(int i = 1; i < Black_Pieces.size(); i++)
	    	{ 
	    		if(Black_Pieces.get(i).canMoveCheckMate(getX(), getY()) == true)
	    		{
	    			newX = Black_Pieces.get(i).getX();
    				newY = Black_Pieces.get(i).getY();
    				return newX.toString() + newY.toString();
	    		}
	    	}
    	}
    	
    	return "X";
    }
    
    
    public ArrayList<Moves> getAvailibleMoves()
    {    
    	Black_Pieces = board.getBlackPieces();
    	White_Pieces = board.getWhitePieces();
   
    	ArrayList<Moves> LegalMoves = new ArrayList<Moves>();
    	LegalMoves = new ArrayList<Moves>();
    	
			for(int j = 0; j < 8;j++)
			{
				for(int k=0; k<8;k++)
				{
					if(!((j ==getX() ) && (k == getY())))
					{
						if(canMove(j, k))
			        	{
							LegalMoves.add(new Moves(getX(),getY(),j,k,getPoints(j,k)));
			        	}
					}
				}
			}

    	return LegalMoves;
    }
    
    public int getPoints(int destX, int destY)
    {
    	Piece piece = this.board.getPiece(destX, destY);
    	if(piece != null)
    	{
    		System.out.println("Class:" + piece.getClass().toString());
    		if(piece.getClass().toString().equals("class chessgui.pieces.Queen")) 
    		{
    			System.out.println("Point Qween");
    			return 10;
    		}
    		else if(piece.getClass().toString().equals("class chessgui.pieces.Rook"))
    		{
    			return 9;
    		}
    		else if((piece.getClass().toString().equals("class chessgui.pieces.Bishop")) || (piece.getClass().toString().equals("class chessgui.pieces.Knight")))
    		{
    			return 8;
    		}
    		else if(piece.getClass().toString().equals("class chessgui.pieces.Pawn"))
    		{
    			return 7;
    		}
    		else if(castle(destX,destY))
    		{
    			return 11;
    		}
    		
    	}
    	return 0;
    }
    
    //check if King can move to destination
    public boolean check(int destination_x, int destination_y)
    {
    	if(isBlack())
    	{
	    	White_Pieces = King.this.board.getWhitePieces();
	    	for(int i = 0; i < White_Pieces.size(); i++)
	    	{
	    		if(White_Pieces.get(i).canMoveCheckMate(destination_x, destination_y) == true)
	    		{
	    			return false;
	    		}
	    	}
    	}
    	else
    	{
    		Black_Pieces = King.this.board.getBlackPieces();
	    	for(int i = 0; i < Black_Pieces.size(); i++)
	    	{ 
	    		if(Black_Pieces.get(i).canMoveCheckMate(destination_x, destination_y) == true)
	    		{
	    			return false;
	    		}
	    	}
    	}
    	return true;
    }  
}
