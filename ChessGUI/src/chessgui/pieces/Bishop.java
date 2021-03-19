package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;

public class Bishop extends Piece {

	private int diffX;
	private int diffY;
	private Piece p;
	
	private int currPosX;
	private int currPosY;
	
	public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
    
    public Bishop(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    
    public int getXValue()
    {
    	return getX();
    }
    public int getYValue()
    {
    	return getY();
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
    	
    	//if p.board.White_Pieces
    	currPosY = getYValue();
    	currPosX = getXValue();
    	
    	if(calcMove(destination_x, destination_y,getXValue(),getYValue()) == true)
    	{
    		p = Bishop.this.board.getPiece(destination_x, destination_y);
    		
    		if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
    		{
	    		if(PathOK(destination_x, destination_y,getXValue(),getYValue()) == true)
	    		{
	    			//Bishop.this.setX(destination_x);
	        		//Bishop.this.setY(destination_y);
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
	        		
	    			System.out.println("true");
	    			return true;	    			
	    		}
	    		else
	    		{
	    		//	System.out.println("Dest OK Path Not!");
	    			return false;
	    		}
    		}
    	}
    	
    	//System.out.println("Bishop False!");
        return false;
    }
    
    
    
    //recursive spaghetti sem virkar?
    public boolean PathOK(int x, int y, int currX, int currY)
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
    		if(Bishop.this.board.getPiece(currX + 1, currY + 1) == null)
    		{
    			return PathOK(x,y,currX + 1, currY + 1);	
    		}
    				
    	}
    	else if(x < currX && y > currY)
    	{
    		if(x == currX - 1 && y == currY + 1)
        	{
        		return true;
        	}
    		
    		if(Bishop.this.board.getPiece(currX - 1, currY + 1) == null)
    		{
    			return PathOK(x,y,currX - 1, currY + 1);
    			
    		}
    		//Haegri nidur
    	}
    	else if(x > currX && y < currY)
    	{
    		if(x == currX + 1 && y == currY - 1)
        	{
        		return true;
        	}
    		if(Bishop.this.board.getPiece(currX + 1, currY - 1) == null)
    		{
    			return PathOK(x,y,currX + 1, currY - 1);
    			
    		}
    		
    		//vinstri upp
    	}
    	else if(x < currX && y < currY)
    	{
    		if(x == currX - 1 && y == currY - 1)
        	{
        		return true;
        	}
    		
    		if(Bishop.this.board.getPiece(currX - 1, currY - 1) == null)
    		{
    			return PathOK(x,y,currX - 1, currY - 1);
    			
    		}
    		//vinstri nidur
    		
    	}
    	
    	
    	
    	return false;
    	
    	
    }
    
    
    public boolean calcMove(int x, int y, int currX, int currY)
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
}
