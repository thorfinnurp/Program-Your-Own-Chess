package chessgui.pieces;

import chessgui.Board;

public class Rook extends Piece {
	private Piece p;
	private boolean hasMoved;
	

    public Rook(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    public boolean hasMoved()
    {
    	//System.out.println("HasMoved from ROOOK! " + hasMoved);
    	return hasMoved;
    }
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
        // Remember: A rook can move as many squares as he wants either forward,
        // backward, or sideways without jumping any pieces. He cannot attack
        // his own pieces.
        
                // WRITE CODE HERE
        
    	p = Rook.this.board.getPiece(destination_x, destination_y);
		
		if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
    	if ((DestOK(destination_x, destination_y, Rook.this.getX(), Rook.this.getY()) == true))
    	{
    		if(PathOK(destination_x, destination_y, Rook.this.getX(), Rook.this.getY()) == true)
    		{
    			//Lika notað í að tékka á skák, þarf að laga.
    			hasMoved = true;
    			return true;
    		}
    	}	
		//System.out.println("ROOK False!");
        return false;
    }
    
    public boolean DestOK(int x,int y, int destination_x, int destination_y)
    {
    	if((x == destination_x) || (y == destination_y))
    	{
    		//System.out.println("Dest OK");
    		return true;
    	}
    	
    	return false;
    }
    
    
    
    public boolean PathOK(int x, int y, int currX, int currY)
    {
    	
    	
    	if(x > currX && y == currY)
    	{
    		if(currX + 1 == x) 
    		{
    			return true;
    		}
    		if(Rook.this.board.getPiece(currX + 1, currY) == null)
    		{
    			return PathOK(x, y, currX + 1, currY);
    		}
    		
    	}
    	else if((x < currX) && (y == currY))
    	{
    		if(currX - 1 == x) 
    		{
    			return true;
    		}
    		if(Rook.this.board.getPiece(currX - 1, currY) == null)
    		{
    			return PathOK(x, y, currX - 1, currY);
    		}
    		//return PathOK(x, y, currX - 1, currY);
    	}
    	else if((x == currX) && (y > currY))
    	{
    		if(currY + 1 == y) 
    		{
    			return true;
    		}
    		if(Rook.this.board.getPiece(currX, currY + 1) == null)
    		{
    			return PathOK(x, y, currX, currY + 1);
    		}
    		//return PathOK(x, y, currX, currY + 1);
    	}
    	else if((x == currX) && (y < currY))
    	{
    		if(currY - 1 == y) 
    		{
    			return true;
    		}
    		if(Rook.this.board.getPiece(currX, currY - 1) == null)
    		{
    			return PathOK(x, y, currX, currY - 1);
    		}
    		//return PathOK(x, y, currX, currY -1);
    	}
    	
    	return false;
    }
}
