package chessgui.pieces;

import chessgui.Board;

public class Pawn extends Piece {

    private boolean has_moved;
    
    private Piece p;
    
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
   
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
        // Remember: A pawn may only move towards the oponent's side of the board.
        // If the pawn has not moved yet in the game, for its first move it can 
        // move two spaces forward. Otherwise, it may only move one space. 
        // When not attacking it may only move straight ahead.
        // When attacking it may only move space diagonally forward

                // WRITE CODE HERE
    	
    	
    	p = Pawn.this.board.getPiece(destination_x, destination_y);
    	
    	if(p == null) 
    	{
    		if(getXValue() == destination_x)
    		{
		    	if(Pawn.this.isWhite() == true)
		    	{
		    	
		    		if((destination_y == getYValue() + 1) || (destination_y == getYValue() + 2))
		    		{
		    			System.out.println("white True!"); 
		        		return true;
		    		}
		    	}
		    	else {
		    		
		    		if((destination_y == getYValue() - 1) || (destination_y == getYValue() - 2))
		    		{
		    			System.out.println("Black True!"); 
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
    	    		System.out.println("W - Licence to Kill!");
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
    	    		System.out.println("B - Licence to Kill!");
    	    		return true;
    	    	}	
    		}	
    	}
    	
    	
    	/*if((Pawn.this.isWhite()) & (destination_y == getYValue()+1)) {
    		
    		System.out.println("True"); 
    		return true;
    	}*/
    	
    	System.out.println("Current pos xy: " + getXValue() + getYValue());
    	System.out.println("Dest pos xy: " + destination_x + destination_y);
                
    	System.out.println("Wrong!");       
        return false;
    }
}
