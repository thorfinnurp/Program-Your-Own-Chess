package chessgui.pieces;

import chessgui.Board;

public class Bishop extends Piece {

	private int diffX;
	private int diffY;
	private Piece p;
    public Bishop(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    
    public int getXValue()
    {
    	return Bishop.this.getX();
    }
    public int getYValue()
    {
    	return Bishop.this.getY();
    }
    
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
        // Remember: For attacking or just moving, a bishop is allowed to move 
        // as many squares diagonally as it wants without jumping over another 
        // piece. He cannot attack his own pieces.
        
                // WRITE CODE HERE
    	//System.out.println("Current pos xy: " + getXValue() + getYValue());
    	//System.out.println("Dest pos xy: " + destination_x + destination_y);
    	
    	if(calcMove(destination_x, destination_y,getXValue(),getYValue()) == true)
    	{
    		p = Bishop.this.board.getPiece(destination_x, destination_y);
    		
    		if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
    		{
	    		if(PathOK(destination_x, destination_y,getXValue(),getYValue()) == true)
	    		{
	    		//	System.out.println("Path OK!!!");
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
