package chessgui.pieces;

import chessgui.Board;

public class Knight extends Piece {
	
	private Piece p;

    public Knight(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    
    public int getXValue()
    {
    	return Knight.this.getX();
    }
    public int getYValue()
    {
    	return Knight.this.getY();
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
        // Remember: a knight can move in any L shape and can jump over anyone
        // in order to do so. He cannot attack his own pieces.
        // By an L shape, I mean it can move to a square that is 2 squares away
        // horizontally and 1 square away vertically, or 1 square away horizontally
        // and 2 squares away vertically.
        // some examples:
        //
        //  * *       * * *           *       *
        //  *             *       * * *       *
        //  *                                 * *
            
                // WRITE CODE HERE
    	
    	p = Knight.this.board.getPiece(destination_x, destination_y);
    	
    	if(Knight.this.isWhite())
    	{
    		if(calcMove(destination_x, destination_y, getX(), getY()) == true)
    		{
    			if((p == null)||(p.isBlack() == true))
    			{
    				return true;
    			}
    		}
    	}
    	else
    	{
    		if(calcMove(destination_x, destination_y, getX(), getY()) == true)
    		{
    			if((p == null)||(p.isWhite() == true))
    			{
    				return true;
    			}
    			;
    		}
    	}
    	
    	
    	
    	
    	
    	
    	System.out.println("Current pos xy: " + getXValue() + getYValue());
    	System.out.println("Dest pos xy: " + destination_x + destination_y);
                
    	System.out.println("Wrong!");       
        
        return false;
    }
    
    public boolean calcMove(int x, int y, int currX, int currY)
    {
    	
    	if((x == currX) || (y == currY))
    	{
    		return false;
    	}
    	
    	if(currY + 1 == y)
    	{
    		if((currX + 2 == x)||(currX - 2 == x))
    		{
    			return true;
    		}
    	}
    	else if(currY + 2 == y)
    	{
    		if((currX + 1 == x)||(currX - 1 == x))
    		{
    			return true;
    		}
    		
    	}
    	else if(currY - 1 == y)
    	{
    		if((currX + 2 == x)||(currX - 2 == x))
    		{
    			return true;
    		}
    		
    	}
    	else if(currY -2 == y)
    	{
    		if((currX + 1 == x)||(currX - 1 == x))
    		{
    			return true;
    		}
    	}
    	
    	
    	return false;
    }
    
   
    
}
