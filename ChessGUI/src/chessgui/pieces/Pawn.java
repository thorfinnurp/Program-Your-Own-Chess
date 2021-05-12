package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;
import chessgui.pieces.Piece.Moves;

public class Pawn extends Piece {

    private boolean has_moved;
    
    private Piece p;
	public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
    
	private int currPosX;
	private int currPosY;
	
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
						if(canMove(j, k))
						{
							LegalMoves.add(new Moves(getX(),getY(),j,k,getPoints(j,k)));
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
    
    public boolean isKingChecked()
    {
    	White_Pieces = board.getWhitePieces();
    	Black_Pieces = board.getBlackPieces();
    	if(isWhite())
    	{
			if(White_Pieces.get(0).isChecked() == true)
			{
				return true;
			}
    	}
    	else
    	{
			if(Black_Pieces.get(0).isChecked() == true)
			{
				return true;
			}
    	}
    	return false;
    }
   
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {	
    	currPosY = getY();
    	currPosX = getX();
    	p = Pawn.this.board.getPiece(destination_x, destination_y);
    
    	if(p == null) 
    	{
    		if(getXValue() == destination_x)
    		{
		    	if(Pawn.this.isWhite() == true)
		    	{
		    		//White straight up 1 or 2
		    		if((destination_y == getYValue() + 1) || ((destination_y == getYValue() + 2) && (getYValue() == 1)))
		    		{
		    			if ((destination_y == getYValue() + 2) && (getYValue() == 1))
		    			{
		    				if(Pawn.this.board.getPiece(destination_x, destination_y - 1) != null)
		    				{
		    					return false;
		    				}
		    			}
		    			
		    			//System.out.println("white True!"); 
		    			return checkWhiteCheck(destination_x, destination_y);
		    			
		    		}
		    	}
		    	else {
		    		//Black Straight up 1 or 2
		    		if((destination_y == getYValue() - 1) || ((destination_y == getYValue() - 2) && (getYValue() == 6)))
		    		{
		    			
		    			if((destination_y == getYValue() - 2) && (getYValue() == 6))
		    			{
		    				if(Pawn.this.board.getPiece(destination_x, destination_y + 1) != null)
		    				{
		    					return false;
		    				}
		    			}
		    			return checkBlackCheck(destination_x, destination_y);
		    		}
		    	}
    		}
    	}
    	if(Pawn.this.isWhite() == true)
    	{
    		//White attack left/right
    		if((destination_y == getYValue() + 1) && ((destination_x == getXValue() + 1) || (destination_x == getXValue()-1)) && (p!= null))
    		{
    			if(p.isBlack() == true)
    	    	{
    	    		return checkWhiteCheck(destination_x, destination_y);
    	    	}
    		}
    	}
    	else
    	{
    		//Black attack left/right
    		if((destination_y == getYValue() - 1) && ((destination_x == getXValue() + 1) || (destination_x == getXValue()-1)) && (p!= null))
    		{
    			if(p.isBlack() == false)
    	    	{
    				return checkBlackCheck(destination_x, destination_y);
    	    	}	
    		}	
    	}
        return false;
    }
    
    public boolean checkWhiteCheck(int destination_x, int destination_y)
    {
    	setX(destination_x);
		setY(destination_y);
		if(isKingChecked() == true)
		{
			if((p != null) && (p.isWhite() == true))
			{
    			if(White_Pieces.get(0).getChecked().equals(String.valueOf(String.valueOf(destination_x) + String.valueOf(destination_y))))
    			{
    				return true;
    			}
			}
			setX(currPosX);
    		setY(currPosY);
			return false;
		}
		setX(currPosX);
		setY(currPosY);
		
		createQueen(destination_x, destination_y);
		return true;
    }
    
    
    public boolean checkBlackCheck(int destination_x, int destination_y)
    {
    	setX(destination_x);
		setY(destination_y);
		if(isKingChecked() == true)
		{
			if((p != null) && (p.isWhite() == true))
			{
    			if(Black_Pieces.get(0).getChecked().equals(String.valueOf(String.valueOf(destination_x) + String.valueOf(destination_y))))
    			{
    				return true;
    			}
			}
			setX(currPosX);
    		setY(currPosY);
			return false;
		}
		setX(currPosX);
		setY(currPosY);
		
		createQueen(destination_x, destination_y);
		return true;
    }
    
    public void createQueen(int destX, int destY)
    {
    	ArrayList<Piece> White_Pieces = Pawn.this.board.getWhitePieces();
    	ArrayList<Piece> Black_Pieces = Pawn.this.board.getBlackPieces();
    	System.out.println("Create Queeen");
    	if(this.isWhite())
    	{
    		if(destY == 7)
    		{
    			White_Pieces.add(new Queen(destX,destY,true,"Queen.png",this.board));
    			White_Pieces.remove(this);
    		}
    	}
    	else
    	{
    		if(destY == 0)
    		{
    			Black_Pieces.add(new Queen(destX,destY,false,"Queen.png",this.board));
    			Black_Pieces.remove(this);
    		}
    	}
	}
    
    @Override
    public boolean canMoveCheckMate(int destination_x, int destination_y)
    {
    	currPosY = getY();
    	currPosX = getX();
    	p = Pawn.this.board.getPiece(destination_x, destination_y);
    
    	if(Pawn.this.isWhite() == true)
    	{
    		if((destination_y == getYValue() + 1) && ((destination_x == getXValue() + 1) || (destination_x == getXValue()-1)) && (p!= null))
    		{
    	    	return true;
    		}
    	}
    	else
    	{
    		if((destination_y == getYValue() - 1) && ((destination_x == getXValue() + 1) || (destination_x == getXValue()-1)) && (p!= null))
    		{
    	    	return true;
    		}	
    	}
    	
        return false;
    }
}
