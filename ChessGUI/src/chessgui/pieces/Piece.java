package chessgui.pieces;

import java.util.ArrayList;

import chessgui.Board;
//import chessgui.pieces.Bishop.Moves;

public class Piece {
    private int x;
    private int y;
    final private boolean is_white;
    private String file_path;
    public Board board;
   // public ArrayList<Moves> LegalMoves;
    
    public class Moves
    {
    	int destX;
    	int	destY;
    	int currX;
    	int currY;
    	int score;
    	
    	public int getX()
    	{
    		return this.destX;
    	}
    	
    	public int getY()
    	{
    		return this.destY;
    	}
    	
    	public int getCurrY()
    	{
    		return this.currY;
    	}
    	public int getCurrX()
    	{
    		return this.currX;
    	}
    	public int getScore()
    	{
    		return this.score;
    	}
    	public Moves(int currX, int currY, int destX, int destY, int score)
    	{
    		this.destX = destX;
    		this.destY = destY;
    		this.score = score;
    		this.currX = currX;
    		this.currY = currY;
    	}
    }
    
    public Piece(int x, int y, boolean is_white, String file_path, Board board)
    {
        this.is_white = is_white;
        this.x = x;
        this.y = y;
        this.file_path = file_path;
        this.board = board;
    }
    
    public boolean hasMoved()
    {
    	return false;
    }
    
    public String getFilePath()
    {
        return file_path;
    }
    
    public void setFilePath(String path)
    {
        this.file_path = path;
    }
    
    public boolean isWhite()
    {
        return is_white;
    }
    
    public boolean isBlack()
    {
        return !is_white;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public boolean isKing()
    {
    	return false;
    }
    
    public boolean isCheckmate()
    {
    	return false;
    }
    
    public boolean isChecked()
    {
    	return false;
    }
    
    public boolean canMove(int destination_x, int destination_y)
    {
        return false;
    }
    
    public boolean canMoveCheckMate(int destination_x, int destination_y)
    {
        return false;
    }
    
    public String getChecked()
    {
    	return "XX";
    }
    
    public ArrayList<Moves> getAvailibleMoves()
    {
    	return null;
    }
}
