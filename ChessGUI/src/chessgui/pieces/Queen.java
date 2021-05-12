package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;
import chessgui.pieces.Piece.Moves;

public class Queen extends Piece {

	private Piece p;
	private int diffX;
	private int diffY;
	
	public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
	private int currPosX;
	private int currPosY;
	
	
    public Queen(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    

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
							//System.out.println("qween added" + j + k +" Curr: " + getX() + getY() +  "CurrSize: " + LegalMoves.size());
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
    	
        // Remember: A Queen can move as many squares as she wants forward, 
        // backward, sideways, or diagonally, without jumping over any pieces.
        // She cannot attack her own pieces.
        
                // WRITE CODE HERE
    	currPosY = this.getY();
    	currPosX = this.getX();
    	p = Queen.this.board.getPiece(destination_x, destination_y);

    	if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
		{
    		
    		if(destOKBishop( destination_x, destination_y, getX(), getY()))
    		{
    			if (PathOKBishop(destination_x, destination_y, getX(), getY()))
    			{
    				setX(destination_x);
	        		setY(destination_y);
	        		if(isKingChecked() == true)
	        		{
	        			if((p != null) &&(p.isWhite() == true))
	        			{
	        				if(isWhite())
		        			{
			        			if((p != null) &&(p.isBlack() == true))
			        			{
				        			if(White_Pieces.get(0).getChecked().equals(String.valueOf(String.valueOf(destination_x) + String.valueOf(destination_y))))
				        			{
				        				return true;
				        			}
			        			}
		        			}
		        			else
		        			{
			        			if((p != null) &&(p.isWhite() == true))
			        			{
				        			if(Black_Pieces.get(0).getChecked().equals(String.valueOf(String.valueOf(destination_x) + String.valueOf(destination_y))))
				        			{
				        				return true;
				        			}
			        			}
		        			}
	        			}
	        			setX(currPosX);
		        		setY(currPosY);
	        			//System.out.println("false");
	        			return false;
	        		}
	        		setX(currPosX);
	        		setY(currPosY);
    				return true;
    			}
    			
    		}
    		else if(DestOKRook(getX(), getY(),destination_x, destination_y))
    		{
    			if (PathOKRook(destination_x, destination_y, getX(), getY()))
    			{
    				setX(destination_x);
	        		setY(destination_y);
	        		if(isKingChecked() == true)
	        		{
	        			if(isWhite())
	        			{
		        			if((p != null) &&(p.isBlack() == true))
		        			{
			        			if(White_Pieces.get(0).getChecked().equals(String.valueOf(String.valueOf(destination_x) + String.valueOf(destination_y))))
			        			{
			        				return true;
			        			}
		        			}
	        			}
	        			else
	        			{
		        			if((p != null) &&(p.isWhite() == true))
		        			{
			        			if(Black_Pieces.get(0).getChecked().equals(String.valueOf(String.valueOf(destination_x) + String.valueOf(destination_y))))
			        			{
			        				return true;
			        			}
		        			}
	        			}
	        			setX(currPosX);
		        		setY(currPosY);
	        			//System.out.println("Qfalse");
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
        // Remember: A Queen can move as many squares as she wants forward, 
        // backward, sideways, or diagonally, without jumping over any pieces.
        // She cannot attack her own pieces.
        
                // WRITE CODE HERE
    	currPosY = this.getY();
    	currPosX = this.getX();
    	p = Queen.this.board.getPiece(destination_x, destination_y);

    	//if((p == null) || (p.isWhite() != isWhite()) || (p.isBlack() != isBlack()))
		{
    		
    		if(destOKBishop( destination_x, destination_y, getX(), getY()))
    		{
    			if (PathOKBishop(destination_x, destination_y, getX(), getY()))
    			{
    				return true;
    			}
    			
    		}
    		else if(DestOKRook(getX(), getY(),destination_x, destination_y))
    		{
    			if (PathOKRook(destination_x, destination_y, getX(), getY()))
    			{
    				return true;
    			}
    		}
    		
		}
    	//System.out.println("Queen False!");
        return false;
    }
    
    
    
    public boolean DestOKRook(int x,int y, int destination_x, int destination_y)
    {
    	if((x == destination_x) || (y == destination_y))
    	{
    		//System.out.println("Dest OK");
    		return true;
    	}
    	
    	return false;
    }
    
    
    
    public boolean destOKBishop(int x, int y, int currX, int currY)
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
    
    @Override
    public boolean isKing()
    {
    	return false;
    }
    
    
    
    public boolean PathOKBishop(int x, int y, int currX, int currY)
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
    		if(Queen.this.board.getPiece(currX + 1, currY + 1) == null)
    		{
    			return PathOKBishop(x,y,currX + 1, currY + 1);	
    		}
    				
    	}
    	else if(x < currX && y > currY)
    	{
    		if(x == currX - 1 && y == currY + 1)
        	{
        		return true;
        	}
    		
    		if(Queen.this.board.getPiece(currX - 1, currY + 1) == null)
    		{
    			return PathOKBishop(x,y,currX - 1, currY + 1);
    			
    		}
    		//Haegri nidur
    	}
    	else if(x > currX && y < currY)
    	{
    		if(x == currX + 1 && y == currY - 1)
        	{
        		return true;
        	}
    		if(Queen.this.board.getPiece(currX + 1, currY - 1) == null)
    		{
    			return PathOKBishop(x,y,currX + 1, currY - 1);
    			
    		}
    		
    		//vinstri upp
    	}
    	else if(x < currX && y < currY)
    	{
    		if(x == currX - 1 && y == currY - 1)
        	{
        		return true;
        	}
    		
    		if(Queen.this.board.getPiece(currX - 1, currY - 1) == null)
    		{
    			return PathOKBishop(x,y,currX - 1, currY - 1);
    			
    		}
    		//vinstri nidur
    		
    	}
    	
    	
    	
    	return false;
    	
    	
    }
    
    
    public boolean PathOKRook(int x, int y, int currX, int currY)
    {
    	
    	
    	if(x > currX && y == currY)
    	{
    		if(currX + 1 == x) 
    		{
    			return true;
    		}
    		if(Queen.this.board.getPiece(currX + 1, currY) == null)
    		{
    			return PathOKRook(x, y, currX + 1, currY);
    		}
    		
    	}
    	else if((x < currX) && (y == currY))
    	{
    		if(currX - 1 == x) 
    		{
    			return true;
    		}
    		if(Queen.this.board.getPiece(currX - 1, currY) == null)
    		{
    			return PathOKRook(x, y, currX - 1, currY);
    		}
    		//return PathOK(x, y, currX - 1, currY);
    	}
    	else if((x == currX) && (y > currY))
    	{
    		if(currY + 1 == y) 
    		{
    			return true;
    		}
    		if(Queen.this.board.getPiece(currX, currY + 1) == null)
    		{
    			return PathOKRook(x, y, currX, currY + 1);
    		}
    		//return PathOK(x, y, currX, currY + 1);
    	}
    	else if((x == currX) && (y < currY))
    	{
    		if(currY - 1 == y) 
    		{
    			return true;
    		}
    		if(Queen.this.board.getPiece(currX, currY - 1) == null)
    		{
    			return PathOKRook(x, y, currX, currY - 1);
    		}
    		//return PathOK(x, y, currX, currY -1);
    	}
    	
    	return false;
    }
}
