package chessgui.pieces;

import chessgui.Board;

public class King extends Piece {
	private int diffY;
	private int diffX;
	private Piece p;

    public King(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
        // Remember: a king can move one square up, right, left, or down, or
        // diagonally, but he can never put himself in danger of an oposing 
        // piece attacking him on the next turn. He cannot attack his own pieces.
        
                // WRITE CODE HERE
    	
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
    
    
}
