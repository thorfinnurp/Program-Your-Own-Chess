package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;
import chessgui.pieces.Piece.Moves;

public class Rook extends Piece {
	private Piece p;
	private boolean hasMoved;
	public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
    
	private int currPosX;
	private int currPosY;
	
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
							LegalMoves.add(new Moves(getX(),getY(),j,k,getPoints(j,k)));
							//System.out.println("rook added" + j + k +" Curr: " + getX() + getY() +  "CurrSize: " + LegalMoves.size());
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
    			//System.out.println("King found");
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
    			//Lika notað í að tékka á skák, þarf að laga.
    			setX(destination_x);
        	 	setY(destination_y);
        		if(isKingChecked() == true)
        		{
        			
        			setX(currPosX);
	        		setY(currPosY);
        			System.out.println("Rook king checkedfalse");
        			return false;
        		}
        		setX(currPosX);
        		setY(currPosY);
        		
    		//	System.out.println("true");
    		
    			hasMoved = true;
    			
    			
    			return true;
    		}
    	}	
        return false;
    }
    
    
    @Override
    public boolean canMoveCheckMate(int destination_x, int destination_y)
    {
    	currPosY = this.getY();
    	currPosX = this.getX();
        
    	p = Rook.this.board.getPiece(destination_x, destination_y);
		
		//if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
    	if ((DestOK(destination_x, destination_y, Rook.this.getX(), Rook.this.getY()) == true))
    	{
    		if(PathOK(destination_x, destination_y, Rook.this.getX(), Rook.this.getY()) == true)
    		{
    			//Lika notað í að tékka á skák, þarf að laga.
   		
    			//System.out.println("true");
    		
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
