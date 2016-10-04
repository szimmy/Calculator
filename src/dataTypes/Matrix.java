package dataTypes;
import java.util.List;
import java.util.ArrayList;
/**
 * Class which represents a generic m x n matrix of doubles.
 * 0,0 represents the top left index of the Matrix
 * @author Sean Zimmerman
 * 
 * @version 3.11.2016
 */
public class Matrix {

	List<Vector> rows;    // a Matrix can be represented by its row vectors
	List<Vector> columns; // or column vectors

	final int M; // number of rows
	final int N; // number of columns
	
	/**
	 * Constructor which creates a m x n matrix which is empty.
	 * @throws IllegalArgumentException
	 */
	public Matrix(int rows, int columns) {
		this.M = rows;
		if(M <= 0) {
			throw new IllegalArgumentException("Invalid data, you cannot have a non positive number of rows");
		}

		this.N = columns;
		if(N <= 0) {
			throw new IllegalArgumentException("Invalid data, you cannot have a non positive number of columns");
		}

		this.rows = new ArrayList<Vector>();
		this.columns = new ArrayList<Vector>();
	}

	/**
	 * Constructor which allows you to create a matrix by specifying a 2D array
	 * @param data MUST be consistent (same number of columns for every row)
	 * @throws IllegalArgumentException
	 */
	public Matrix(double[][] data) {		
		this.M = data.length;
		if(M <= 0) {
			throw new IllegalArgumentException("Invalid data, you cannot have a non positive number of rows");
		}

		this.N = data[0].length;

		if(N <= 0) {
			throw new IllegalArgumentException("Invalid data, you cannot have a non positive number of columns");
		}

		constructRows(data);
		constructColumns(data);
	}
	
	/**
	 * A constructor which allows you to make a Matrix by giving a List of Vectors
	 * @param vecs MUST be consistent (same number of coordinates in every vector)
	 * @param row true if the List is of row vectors, false for column
	 * @throws IllegalArgumentException
	 */
	public Matrix(List<Vector> vecs, boolean row) {
		if(row) {
			this.M = vecs.size();
			if(M <= 0) {
				throw new IllegalArgumentException("Invalid data, you cannot have 0 number of rows");
			}

			this.N = vecs.get(0).length();
			if(N <= 0) {
				throw new IllegalArgumentException("Invalid data, you cannot have 0 number of columns");
			}

			this.rows = vecs;
		}
		else {
			this.N = vecs.size();
			if(N <= 0) {
				throw new IllegalArgumentException("Invalid data, you cannot have 0 number of columns");
			}

			this.M = vecs.get(0).length();
			if(M <= 0) {
				throw new IllegalArgumentException("Invalid data, you cannot have 0 number of rows");
			}

			this.columns = vecs;
		}
		
	}

	/**
	 * Protected helper method which fills the list of row vectors used for Matrix operations
	 */
	protected void constructRows(double[][] data) {
		rows = new ArrayList<Vector>();

		// fill row vector List
		for(int i = 0; i < this.M; i++) {
			double[] values = new double[this.N];
			for(int j = 0; j < this.N; j++) {
				values[j] = data[i][j];
				if(j == this.N-1) {
					rows.add(new Vector(values));
				}
			}
		}
	}
	
	/**
	 * Protected helper method which fills the list of column vectors used for Matrix operations
	 */
	protected void constructColumns(double[][] data) {
		columns = new ArrayList<Vector>();

		//fill column vector List
		for(int j = 0; j < this.N; j++) {
			double[] values = new double[this.M];
			for(int i = 0; i < this.M; i++) {
				values[i] = data[i][j];
				if(i == this.M-1) {
					columns.add(new Vector(values));
				}
			}
		}
	}

	/**
	 * Mutator method which sets the value at index [row][column] to value
	 * Pre - 0 <= row < M; 0 <= column < N
	 * @throws IllegalArgumentException
	 */
	public void set(int row, int column, double value) {
		if(validIndicies(row, column)) {
			throw new IllegalArgumentException("Invalid index: Acceptable indicies are, 0 <= row < Row Num; 0 <= column < Column Num");
		}
		
		// update rows and columns Lists
		rows.get(row).data[column] = value; 
		columns.get(column).data[row] = value;
	}

	/**
	 * Pre - 0 <= row < M; 0 <= column < N
	 * @return the value at the index [row][column] in the Matrix
	 * @throws IllegalArgumentException
	 */
	public double get(int row, int column) {
		if(!validIndicies(row, column)) {
			throw new IllegalArgumentException("Invalid index: Acceptable indicies are, 0 <= row < Row Num; 0 <= column < Column Num");
		}
		
		return rows.get(row).coordinate(column);
	}

	/**
	 * @return a list of row vectors which makes up this Matrix
	 */
	public List<Vector> getRows() {
		return this.rows;
	}

	/**
	 * @return a list of column vectors which makes up this Matrix
	 */
	public List<Vector> getColumns() {
		return this.columns;
	}
	
	/**
	 * @return the number of rows in this M x N Matrix
	 */
	public int getRowNum() {
		return this.M;
	}
	
	/**
	 * @return the number of columns in this M x N Matrix
	 */
	public int getColumnNum() {
		return this.N;
	}

	/**
	 * @return the result of adding this matrix with other
	 * @throws RuntimeException
	 * Matricies must be the same size in order to be added
	 */
	public Matrix add(Matrix other) {
		if(this.M != other.M || this.N != other.N) {
			throw new RuntimeException("You can only add an m x n Matrix with another m x n matrix");
		}

		List<Vector> resultRows = new ArrayList<Vector>();
		
		int count = 0;
		for(Vector v: this.rows) {
			resultRows.add(v.add(other.getRows().get(count)));
			count++;
		}
				
		return new Matrix(resultRows, true);
	}

	/**
	 * @return the result of subtracting this matrix and other
	 * @throws RuntimeException
	 * Matricies must be the same size in order to be added
	 */
	public Matrix subtract(Matrix other) {
		if(this.M != other.M || this.N != other.N) {
			throw new RuntimeException("You can only add an m x n Matrix with another m x n matrix");
		}

		List<Vector> resultRows = new ArrayList<Vector>();
		
		int count = 0;
		for(Vector v: this.rows) {
			resultRows.add(v.subtract(other.getRows().get(count)));
			count++;
		}
		
		
		return new Matrix(resultRows, true);
	}

	/**
	 * @return the result of multiplying every element in this Matrix by a value
	 */
	public Matrix scalarMultiple(double value) {
		List<Vector> resultRows = new ArrayList<Vector>();

		for(Vector v: this.rows) {
			resultRows.add(v.scalarMult(value));
		}
		
		
		return new Matrix(resultRows, true);
	}

	/**
	 * @return the result of multiplying two matricies by one another
	 * @throws IllegalArgumentException
	 * Pre condition - The number of columns of this Matrix needs to match the number of rows of the input to be valid
	 */
	public Matrix multiply(Matrix other) {
		if(this.N != other.M) {
			throw new IllegalArgumentException("Error: to multiply Matrix A(m x n) by Matrix B(q x v) then n must be the same as q.");
		}

		Matrix result = new Matrix(this.M, other.N);

		List<Vector> theseRows = this.rows;
		List<Vector> otherColumns = other.getColumns();

		for(int i = 0; i < this.M; i++) {
			for (int j = 0; j < this.N; j++) {
				result.set(i, j, theseRows.get(i).dot(otherColumns.get(j)));
			}
		}
		return result;
	}

	/**
	 * @return the transpose of this Matrix which is gotten by taking c_ij and putting it in index c_ji, this is N x M
	 * or alternatively, swapping the rows and columns
	 */
	public Matrix transpose() {
		Matrix result = new Matrix(this.N, this.M);
		
		result.rows = this.columns;
		result.columns = this.rows;

		return result;
	}

	/**
	 * Matricies are equal if they have same number of rows, same number of colummns, and same elements in each index
	 * @return if the Matricies are equal
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof Matrix)) { // can't be equal if it is not a matrix
			return false;
		}

		Matrix other = (Matrix)obj;

		if(this.M != other.M || this.N != other.N) { // must be same size
			return false;
		}

		return this.getRows().equals(other.getRows()); //if the row vectors are the same they are the same
	}

	/**
	 * Private helper method which returns if the given indicies are valid for a matrix position
	 */
	private boolean validIndicies(int row, int column) {
		return row < 0 || row >= M || column < 0 || column >= N;
	}
	
	public String toString() {
		String result = "--------------------";
		
		for(int i = 0; i < this.getRowNum(); i++) {
			result += "\n" + this.rows.get(i).toString();
		}
		
		return result + "\n" + "--------------------";
	}
}
