package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;

public class Queen extends Piece {

	private Piece p;
	private int diffX;
	private int diffY;
	
	public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
	private int currPosX;
	private int currPosY;
	
	
    public Queen(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
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
    			System.out.println("King found");
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
    	
        // Remember: A Queen can move as many squares as she wants forward, 
        // backward, sideways, or diagonally, without jumping over any pieces.
        // She cannot attack her own pieces.
        
                // WRITE CODE HERE
    	currPosY = this.getY();
    	currPosX = this.getX();
    	p = Queen.this.board.getPiece(destination_x, destination_y);

    	if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
		{
    		
    		if(destOKBishop( destination_x, destination_y, getX(), getY()))
    		{
    			if (PathOKBishop(destination_x, destination_y, getX(), getY()))
    			{
    				setX(destination_x);
	        		setY(destination_y);
	        		if(isKingChecked() == true)
	        		{
	        			setX(currPosX);
		        		setY(currPosY);
	        			System.out.println("false");
	        			return false;
	        		}
	        		setX(currPosX);
	        		setY(currPosY);
    				return true;
    			}
    			
    		}
    		else if(DestOKRook(getX(), getY(),destination_x, destination_y))
    		{
    			if (PathOKRook(destination_x, destination_y, getX(), getY()))
    			{
    				setX(destination_x);
	        		setY(destination_y);
	        		if(isKingChecked() == true)
	        		{
	        			setX(currPosX);
		        		setY(currPosY);
	        			System.out.println("Qfalse");
	        			return false;
	        		}
	        		setX(currPosX);
	        		setY(currPosY);
    				return true;
    			}
    		}
    		
		}
    	//System.out.println("Queen False!");
        return false;
    }
    
    
    
    public boolean DestOKRook(int x,int y, int destination_x, int destination_y)
    {
    	if((x == destination_x) || (y == destination_y))
    	{
    		//System.out.println("Dest OK");
    		return true;
    	}
    	
    	return false;
    }
    
    
    
    public boolean destOKBishop(int x, int y, int currX, int currY)
    {
    	diffX = currX - x;
    	diffY = currY - y;
    	
    	if((x == currX) || (y == currY))
    	{
    		return false;
    	}
    	
    	if((diffX == diffY) || (-diffX == -diffY) || (diffX == -diffY) || (-diffX == diffY))
    	{
    		
    		return true;
    	}
    	
    	return false;
    }
    
    @Override
    public boolean isKing()
    {
    	return false;
    }
    
    
    
    public boolean PathOKBishop(int x, int y, int currX, int currY)
    {    		
    	if(x == currX && y == currY)
    	{
    		return true;
    	}
    	if(x > currX && y > currY)
    	{	
    		if(x == currX + 1 && y == currY + 1)
        	{
        		return true;
        	}
        	
    		//haegri upp
    		if(Queen.this.board.getPiece(currX + 1, currY + 1) == null)
    		{
    			return PathOKBishop(x,y,currX + 1, currY + 1);	
    		}
    				
    	}
    	else if(x < currX && y > currY)
    	{
    		if(x == currX - 1 && y == currY + 1)
        	{
        		return true;
        	}
    		
    		if(Queen.this.board.getPiece(currX - 1, currY + 1) == null)
    		{
    			return PathOKBishop(x,y,currX - 1, currY + 1);
    			
    		}
    		//Haegri nidur
    	}
    	else if(x > currX && y < currY)
    	{
    		if(x == currX + 1 && y == currY - 1)
        	{
        		return true;
        	}
    		if(Queen.this.board.getPiece(currX + 1, currY - 1) == null)
    		{
    			return PathOKBishop(x,y,currX + 1, currY - 1);
    			
    		}
    		
    		//vinstri upp
    	}
    	else if(x < currX && y < currY)
    	{
    		if(x == currX - 1 && y == currY - 1)
        	{
        		return true;
        	}
    		
    		if(Queen.this.board.getPiece(currX - 1, currY - 1) == null)
    		{
    			return PathOKBishop(x,y,currX - 1, currY - 1);
    			
    		}
    		//vinstri nidur
    		
    	}
    	
    	
    	
    	return false;
    	
    	
    }
    
    
    public boolean PathOKRook(int x, int y, int currX, int currY)
    {
    	
    	
    	if(x > currX && y == currY)
    	{
    		if(currX + 1 == x) 
    		{
    			return true;
    		}
    		if(Queen.this.board.getPiece(currX + 1, currY) == null)
    		{
    			return PathOKRook(x, y, currX + 1, currY);
    		}
    		
    	}
    	else if((x < currX) && (y == currY))
    	{
    		if(currX - 1 == x) 
    		{
    			return true;
    		}
    		if(Queen.this.board.getPiece(currX - 1, currY) == null)
    		{
    			return PathOKRook(x, y, currX - 1, currY);
    		}
    		//return PathOK(x, y, currX - 1, currY);
    	}
    	else if((x == currX) && (y > currY))
    	{
    		if(currY + 1 == y) 
    		{
    			return true;
    		}
    		if(Queen.this.board.getPiece(currX, currY + 1) == null)
    		{
    			return PathOKRook(x, y, currX, currY + 1);
    		}
    		//return PathOK(x, y, currX, currY + 1);
    	}
    	else if((x == currX) && (y < currY))
    	{
    		if(currY - 1 == y) 
    		{
    			return true;
    		}
    		if(Queen.this.board.getPiece(currX, currY - 1) == null)
    		{
    			return PathOKRook(x, y, currX, currY - 1);
    		}
    		//return PathOK(x, y, currX, currY -1);
    	}
    	
    	return false;
    }
}
