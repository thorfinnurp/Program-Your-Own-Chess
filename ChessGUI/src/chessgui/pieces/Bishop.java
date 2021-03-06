package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;
import chessgui.pieces.Piece.Moves;

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
    
    
    public void getLegalMoves()
    {
    	
    }
    
    //public ArrayList<Moves> LegalMoves;
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
							//getPoints(j,k);
							
							LegalMoves.add(new Moves(getX(),getY(),j,k,getPoints(j,k)));
							
							//System.out.println("Bishop added" + j + k +" Curr: " + getX() + getY() +  "CurrSize: " + LegalMoves.size());
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
    		//if (White_Pieces.get(0).isKing() == true)
    		{
    			if(White_Pieces.get(0).isChecked() == true)
    			{
    				return true;
    			}
    		} 	
    	}
    	else
    	{
    		//if (Black_Pieces.get(0).isKing() == true)
    		{
    			if(Black_Pieces.get(0).isChecked() == true)
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
	        			//System.out.println("false");
	        			return false;
	        		}
	        		setX(currPosX);
	        		setY(currPosY);
	        		
	    			//System.out.println("true");
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
    
    
    @Override
    public boolean canMoveCheckMate(int destination_x, int destination_y)
    {
    	//if p.board.White_Pieces
    	currPosY = getYValue();
    	currPosX = getXValue();
    	
    	if(calcMove(destination_x, destination_y,getXValue(),getYValue()) == true)
    	{
    		p = Bishop.this.board.getPiece(destination_x, destination_y);
    		
    		//if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
    		{
	    		if(PathOK(destination_x, destination_y,getXValue(),getYValue()) == true)
	    		{
	    			return true;	    			
	    		}
	    		else
	    		{
	    			return false;
	    		}
    		}
    	}
    	
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
