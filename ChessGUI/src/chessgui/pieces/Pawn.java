package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;
import chessgui.pieces.Piece.Moves;

public class Pawn extends Piece {

    private boolean has_moved;
    
    private Piece p;
	public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
    
	private int currPosX;
	private int currPosY;
	
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
						//if(canMoveCheckMate(j, k))
						if(canMove(j, k))
						{
							LegalMoves.add(new Moves(getX(),getY(),j,k,getPoints(j,k)));
						//	System.out.println("Pawn added" + j + k +" Curr: " + getX() + getY() +  "CurrSize: " + LegalMoves.size());
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
    		if(piece.getClass().toString().equals("class chessgui.pieces.Queen")) //== "class chessgui.pieces.Queen")
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
    		
    	}
    	return 0;
    }
	
    
    public Pawn(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
        has_moved = false;
    }
    
    public void setHasMoved(boolean has_moved)
    {
        this.has_moved = has_moved;
    }
    
    public boolean getHasMoved()
    {
        return has_moved;
    }
    public int getXValue()
    {
    	return Pawn.this.getX();
    }
    public int getYValue()
    {
    	return Pawn.this.getY();
    }
    
    public boolean isKingChecked()
    {
    	White_Pieces = board.getWhitePieces();
    	Black_Pieces = board.getBlackPieces();
    	if(isWhite())
    	{
    		//System.out.println("IsWhite");
    		if (White_Pieces.get(0).isKing() == true)
    		{
    			//System.out.println("King found");
    			if(White_Pieces.get(0).isCheckmate() == true)
    			{
    				return true;
    			}
    		} 	
    	}
    	else
    	{
    		if (Black_Pieces.get(0).isKing() == true)
    		{
    			//System.out.println("King found");
    			if(Black_Pieces.get(0).isCheckmate() == true)
    			{
    				return true;
    			}
    		}	
    	}
    	return false;
    }
   
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
        // Remember: A pawn may only move towards the oponent's side of the board.
        // If the pawn has not moved yet in the game, for its first move it can 
        // move two spaces forward. Otherwise, it may only move one space. 
        // When not attacking it may only move straight ahead.
        // When attacking it may only move space diagonally forward

                // WRITE CODE HERE
    	
    	currPosY = getY();
    	currPosX = getX();
    	p = Pawn.this.board.getPiece(destination_x, destination_y);
    
    	
    	if(p == null) 
    	{
    		if(getXValue() == destination_x)
    		{
		    	if(Pawn.this.isWhite() == true)
		    	{
		    	
		    		if((destination_y == getYValue() + 1) || ((destination_y == getYValue() + 2) && (getYValue() == 1)))
		    		{
		    			
		    			if ((destination_y == getYValue() + 2) && (getYValue() == 1))
		    			{
		    				if(Pawn.this.board.getPiece(destination_x, destination_y - 1) != null)
		    				{
		    					return false;
		    				}
		    			}
		    			
		    			//System.out.println("white True!"); 
		    			setX(destination_x);
		        		setY(destination_y);
		        		if(isKingChecked() == true)
		        		{
		        			setX(currPosX);
			        		setY(currPosY);
		        		//	System.out.println("false");
		        			return false;
		        		}
		        		setX(currPosX);
		        		setY(currPosY);
		        		
		    			//System.out.println("true");
		        		createQueen(destination_x, destination_y);
		    			
		        		return true;
		    		}
		    	}
		    	else {
		    		
		    		if((destination_y == getYValue() - 1) || ((destination_y == getYValue() - 2) && (getYValue() == 6)))
		    		{
		    			
		    			if((destination_y == getYValue() - 2) && (getYValue() == 6))
		    			{
		    				if(Pawn.this.board.getPiece(destination_x, destination_y + 1) != null)
		    				{
		    					return false;
		    				}
		    			}
		    			
		    			
		    			
		    			//System.out.println("Black True!"); 
		    			setX(destination_x);
		        		setY(destination_y);
		        		if(isKingChecked() == true)
		        		{
		        			setX(currPosX);
			        		setY(currPosY);
		        			//System.out.println("false");
		        			return false;
		        		}
		        		setX(currPosX);
		        		setY(currPosY);
		        		
		    			//System.out.println("true");
		        		createQueen(destination_x, destination_y);
		        		return true;
		    		}
		    		
		    	}
    		}
    	}
    	if(Pawn.this.isWhite() == true)
    	{
    		if((destination_y == getYValue() + 1) && ((destination_x == getXValue() + 1) || (destination_x == getXValue()-1)) && (p!= null))
    		{
    			
    			if(p.isBlack() == Pawn.this.isWhite())
    	    	{
    	    		//System.out.println("W - Licence to Kill!");
    				setX(destination_x);
	        		setY(destination_y);
	        		if(isKingChecked() == true)
	        		{
	        			setX(currPosX);
		        		setY(currPosY);
	        			//System.out.println("false");
	        			return false;
	        		}
	        		setX(currPosX);
	        		setY(currPosY);
	        		
	    			//System.out.println("true");
	        		createQueen(destination_x, destination_y);
    	    		return true;
    	    	}
    		}
    	}
    	else
    	{
    		if((destination_y == getYValue() - 1) && ((destination_x == getXValue() + 1) || (destination_x == getXValue()-1)) && (p!= null))
    		{
    			if(p.isBlack() == Pawn.this.isWhite())
    	    	{
    	    		//System.out.println("B - Licence to Kill!");
    				setX(destination_x);
	        		setY(destination_y);
	        		if(isKingChecked() == true)
	        		{
	        			setX(currPosX);
		        		setY(currPosY);
	        			//System.out.println("false");
	        			return false;
	        		}
	        		setX(currPosX);
	        		setY(currPosY);
	        		
	    			//System.out.println("true");
	        		createQueen(destination_x, destination_y);
    	    		return true;
    	    	}	
    		}	
    	}
    	/*if((Pawn.this.isWhite()) & (destination_y == getYValue()+1)) {
    		
    		System.out.println("True"); 
    		return true;
    	}*/
        return false;
    }
    
    public void createQueen(int destX, int destY)
    {
    	ArrayList<Piece> White_Pieces = Pawn.this.board.getWhitePieces();
    	ArrayList<Piece> Black_Pieces = Pawn.this.board.getBlackPieces();
    	System.out.println("Create Queeen");
    	if(this.isWhite())
    	{
    		if(destY == 7)
    		{
    			White_Pieces.add(new Queen(destX,destY,true,"Queen.png",this.board));
    			White_Pieces.remove(this);
    			
    		}
    	}
    	else
    	{
    		if(destY == 0)
    		{
    			Black_Pieces.add(new Queen(destX,destY,false,"Queen.png",this.board));
    			Black_Pieces.remove(this);
    		
    		}
    	}
 
	}
    
    @Override
    public boolean canMoveCheckMate(int destination_x, int destination_y)
    {
        // Remember: A pawn may only move towards the oponent's side of the board.
        // If the pawn has not moved yet in the game, for its first move it can 
        // move two spaces forward. Otherwise, it may only move one space. 
        // When not attacking it may only move straight ahead.
        // When attacking it may only move space diagonally forward

                // WRITE CODE HERE
    	
    	currPosY = getY();
    	currPosX = getX();
    	p = Pawn.this.board.getPiece(destination_x, destination_y);
    
    	
    	//Ekki skák ef peð fer beint
    	//if(p == null) 
    	/*{
    		if(getXValue() == destination_x)
    		{
		    	if(Pawn.this.isWhite() == true)
		    	{
		    	
		    		if((destination_y == getYValue() + 1) || (destination_y == getYValue() + 2))
		    		{
		        		return true;
		    		}
		    	}
		    	else {
		    		
		    		if((destination_y == getYValue() - 1) || (destination_y == getYValue() - 2))
		    		{
		    			//System.out.println("Black True!"); 
		        		return true;
		    		}
		    		
		    	}
    		}
    	}*/
    	if(Pawn.this.isWhite() == true)
    	{
    		if((destination_y == getYValue() + 1) && ((destination_x == getXValue() + 1) || (destination_x == getXValue()-1)) && (p!= null))
    		{
    			
    			//if(p.isBlack() == Pawn.this.isWhite())
    	    	{
    	    		//System.out.println("W - Licence to Kill!");
    	    		return true;
    	    	}
    		}
    	}
    	else
    	{
    		if((destination_y == getYValue() - 1) && ((destination_x == getXValue() + 1) || (destination_x == getXValue()-1)) && (p!= null))
    		{
    			//if(p.isBlack() == Pawn.this.isWhite())
    	    	{
    	    		return true;
    	    	}	
    		}	
    	}
    	/*if((Pawn.this.isWhite()) & (destination_y == getYValue()+1)) {
    		
    		System.out.println("True"); 
    		return true;
    	}*/
        return false;
    }
}
