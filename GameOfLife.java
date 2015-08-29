import java.io.File;
import java.util.Random;

public class GameOfLife{
	
	/*
	 * Private Grid variables.
	 */
	private char[][] currentGrid;
	private char[][] bufferGrid;

	/*
	 * Private variables for number of rows and nuber of columns.
	 */
	private int numOfRows, numOfCols;
	
	/**
	 *Contructs instance of object GameOfLife using a seed file. 
	 *
	 *@param seedFile that that is linked to in the command line.  
	 */
	public GameOfLife(File seedFile){
		//TODO
	}
	
	/**
	 * Called from the seed file contructor to read from the file
	 * and assign initial living cells.
	 *
	 * @param file this is the handle to the file that will be read
	 */
	public void initializeGridFromSeed(File file){
		//TODO
	}
		
	/**
	 * Contructs an instance of the class GameOfLife using the 
	 * parameters rows and cols.
	 *
	 * @param rows number of rows in the grid
	 * @param cols number of columns in the grid
	 */
	public GameOfLife(int rows, int cols){
		
		numOfRows = rows;
		numOfCols = cols;	

		initializeGridsRandomly();
	}

	/**
	 * Initializes grid by using the '#' chracter to represent a
	 * living cell. 
	 */
	public void initializeGridsRandomly(){
		Random rand = new Random();
		currentGrid = new char[numOfRows][numOfCols];
		bufferGrid = new char[numOfRows][numOfCols];

		for(int r = 0; r < numOfRows; r++){
			for(int c = 0; c < numOfCols; c++){
				if(rand.nextInt(10)+1 > 9){
					currentGrid[r][c] = '#';
					bufferGrid[r][c] = '#';
				}else{
					currentGrid[r][c] = ' ';
					bufferGrid[r][c] = ' ';
					
				}
			
			}//column loop
		}//row loop
	}
	
	/**
	 * This method goes about updating the current state of the grid
	 * variables based on the state of the past one.
	 *
	 * It does this by maintaining two arrays. The buffer array is
	 * modified based on the state of the current array. The current
	 * array is then reasigned to a copy of the buffered array.
	 * 
	 */
	public void update(){
		
		for(int r = 0; r < numOfRows; r++){
			for(int c = 0; c < numOfCols; c++){
				
				//
				if((checkLN(r,c) < 2 && currentGrid[r][c] == '#') || (checkLN(r,c) > 3  && currentGrid[r][c] == '#')){
					bufferGrid[r][c] = ' ';
				}
				else if (currentGrid[r][c] == ' ' && checkLN(r,c) == 3){
					bufferGrid[r][c] = '#';
				}				
			}//column loop
			System.out.println();
		}//row loop
		
		currentGrid = bufferGrid.clone();

	}
	
	/**
	 * This Method is used to identify how many neighbors ajacent
	 * to a specific cell are alive or dead.
	 * 
	 * @param row index of the row that needs checking
	 * @param col index of the column that needs checking
	 * @return the number of neighbors around the identified cell that are alive
	 */
	public int checkLN(int row, int col){
		int numOfNeighbors = 0;
		
		//Checks everything besides outer boarder
		if((row > 0 && row < numOfRows-1) && ( col > 0 && col < numOfCols-1)){
			if (currentGrid[row-1][col-1] == '#'){
				++numOfNeighbors;
			}
			if (currentGrid[row-1][col] == '#'){
				++numOfNeighbors;
			}
			if (currentGrid[row-1][col+1] == '#'){
				++numOfNeighbors;
			}
			if (currentGrid[row][col-1] == '#'){
				++numOfNeighbors;
			}
			if (currentGrid[row][col+1] == '#'){
				++numOfNeighbors;
			}
			if (currentGrid[row+1][col-1] == '#'){
				++numOfNeighbors;
			}
			if (currentGrid[row+1][col] == '#'){
				++numOfNeighbors;
			}
			if (currentGrid[row+1][col+1] == '#'){
				++numOfNeighbors;
			}
		}
		//Upper-Left Corner
		else if ( row == 0 && col == 0){
			if(currentGrid[row][col+1] == '#'){
				++numOfNeighbors;
			}
			if(currentGrid[row+1][col] == '#'){
				++numOfNeighbors;
			}
			if(currentGrid[row+1][col+1] == '#'){
				++numOfNeighbors;
			}

		}
		//Upper-Right Corner
		else if ( row == 0 && col == numOfCols -1){
			if(currentGrid[row][col-1] == '#'){
				++numOfNeighbors;
			}
			if(currentGrid[row+1][col] == '#'){
				++numOfNeighbors;
			}
			if(currentGrid[row+1][col-1] == '#'){
				++numOfNeighbors;
			}

		}
		//Lower-Right Corner
		else if ( row == numOfRows-1  && col == numOfCols -1){
			if(currentGrid[row-1][col] == '#'){
				++numOfNeighbors;
			}
			if(currentGrid[row-1][col-1] == '#'){
				++numOfNeighbors;
			}
			if(currentGrid[row][col-1] == '#'){
				++numOfNeighbors;
			}

		}
		//Lower-Left Corner
		else if ( row == numOfRows -1 && col == 0){
			if(currentGrid[row-1][col] == '#'){
				++numOfNeighbors;
			}
			if(currentGrid[row][col] == '#'){
				++numOfNeighbors;
			}
			if(currentGrid[row][col+1] == '#'){
				++numOfNeighbors;
			}

		}
		//Check upper boundry
		else if (row == 0 && (col > 0 && col < numOfCols -1)){
			//Left
			if(currentGrid[row][col-1] == '#'){
				++numOfNeighbors;
			}
			//Below	
			if(currentGrid[row+1][col] == '#'){
				++numOfNeighbors;
			}
			//Right
			if(currentGrid[row][col+1] == '#'){
				++numOfNeighbors;
			}
		}
		//Check right boundry
		else if (col == numOfCols -1 && (row > 0 && row < numOfRows -1)){
			//Left
			if(currentGrid[row][col-1] == '#'){
				++numOfNeighbors;
			}
			//Below	
			if(currentGrid[row+1][col] == '#'){
				++numOfNeighbors;
			}
			//Above
			if(currentGrid[row-1][col] == '#'){
				++numOfNeighbors;
			}
		}
		//Check lower boundry
		else if (row == numOfCols-1 && (col > 0 && col < numOfCols -1)){
			//Left
			if(currentGrid[row][col-1] == '#'){
				++numOfNeighbors;
			}
			//Above	
			if(currentGrid[row-1][col] == '#'){
				++numOfNeighbors;
			}
			//Right
			if(currentGrid[row][col+1] == '#'){
				++numOfNeighbors;
			}
		}
		//Check left boundry
		else if (row == 0 && (col > 0 && col < numOfCols -1)){
			//Above
			if(currentGrid[row-1][col] == '#'){
				++numOfNeighbors;
			}
			//Below	
			if(currentGrid[row+1][col] == '#'){
				++numOfNeighbors;
			}
			//Right
			if(currentGrid[row][col+1] == '#'){
				++numOfNeighbors;
			}
		}
		return numOfNeighbors;

	}

	/**
	 * Prints state of current grid
	 */
	public void printGrid(){
		System.out.println();

		for(int r = 0; r < numOfRows; r++){
			for(int c = 0; c < numOfCols; c++){
				System.out.print(currentGrid[r][c]);
			
			}//column loop
			System.out.println();
		}//row loop

		System.out.println();
	}

	/**
	 * Main loop where program continues to loop until user kills
	 * Should probably change 
	 */
	public void run(){
		while(true){
			printGrid();
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){
				Thread.currentThread().interrupt();
			}
			update();
		}

	}
	
	/**
	 * Entry point for your application
	 *
	 * @param args string arguments from the console
	 */
	public static void main (String[] args){
			
		GameOfLife gol = new GameOfLife(100,100);	
		
		//run Game of Life Object
		gol.run();
	
	}
}
