package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;

public class Rook extends Piece {
	private Piece p;
	private boolean hasMoved;
	public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
    
	private int currPosX;
	private int currPosY;
	


    public Rook(int x, int y, boolean is_white, String file_path, Board board)
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
    
    public boolean hasMoved()
    {
    	//System.out.println("HasMoved from ROOOK! " + hasMoved);
    	return hasMoved;
    }
    
    @Override
    public boolean isKing()
    {
    	return false;
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
    	currPosY = this.getY();
    	currPosX = this.getX();
        
    	p = Rook.this.board.getPiece(destination_x, destination_y);
		
		if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
    	if ((DestOK(destination_x, destination_y, Rook.this.getX(), Rook.this.getY()) == true))
    	{
    		if(PathOK(destination_x, destination_y, Rook.this.getX(), Rook.this.getY()) == true)
    		{
    			//Lika nota� � a� t�kka � sk�k, �arf a� laga.
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
    		
    			hasMoved = true;
    			
    			
    			return true;
    		}
    	}	
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
