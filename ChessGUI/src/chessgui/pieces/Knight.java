package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;

public class Knight extends Piece {
	
	private Piece p;
	private int currPosX;
	private int currPosY;
	public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
    

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
    
    public boolean isKingChecked()
    {
    	White_Pieces = board.getWhitePieces();
    	Black_Pieces = board.getBlackPieces();
    	if(isWhite())
    	{
    		if (White_Pieces.get(0).isKing() == true)
    		{
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
    	currPosY = getY();
    	currPosX = getX();
    	White_Pieces = board.getWhitePieces();
    	Black_Pieces = board.getBlackPieces();
    	
       	p = Knight.this.board.getPiece(destination_x, destination_y);
    	
    	
    	if(Knight.this.isWhite())
    	{
    		if(calcMove(destination_x, destination_y, getX(), getY()) == true)
    		{
    			if((p == null)||(p.isBlack() == true))
    			{	
    				if(White_Pieces.get(0).getChecked().equals(String.valueOf(String.valueOf(destination_x) + String.valueOf(destination_y))))
        			{
        				return true;
        			}
    				
    				setX(destination_x);
	        		setY(destination_y);
	        		
	        		if(isKingChecked() == true)
	        		{
	        			setX(currPosX);
		        		setY(currPosY);
	        			System.out.println(White_Pieces.get(0).getChecked() + "   " + String.valueOf(destination_x) + String.valueOf(destination_y));
	        			
	        			return false;
	        		}
	        		
	        		setX(currPosX);
	        		setY(currPosY);
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
	        		System.out.println(Black_Pieces.get(0).getChecked() + "=" + String.valueOf(destination_x) + String.valueOf(destination_y));
	        			
	        		if(Black_Pieces.get(0).getChecked().equals(String.valueOf(String.valueOf(destination_x) + String.valueOf(destination_y))))// == String.valueOf(destination_x) + String.valueOf(destination_y))
        			{
        				return true;
        			}
	        		
    				setX(destination_x);
	        		setY(destination_y);
	        		if(isKingChecked() == true)
	        		{
	        			setX(currPosX);
		        		setY(currPosY);
	        			return false;
	        		}
	        		setX(currPosX);
	        		setY(currPosY);
    				return true;
    			}
    			
    		}
    	}
        return false;
    }
    
    
    
    @Override
    public boolean canMoveCheckMate(int destination_x, int destination_y)
    {
    	currPosY = getY();
    	currPosX = getX();
    	
    	
    	//System.out.println("1X,Y" + currPosX + currPosY);
    	p = Knight.this.board.getPiece(destination_x, destination_y);
    	
    	//if(p.isKing())
    //	{
   // 		System.out.println("kingisAt:" + destination_x + destination_y);
   // 	}
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
    		}
    	}
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
