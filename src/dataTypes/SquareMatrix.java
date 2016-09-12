package dataTypes;
import java.util.List;
/**
 * A class which represents an n x n Matrix of doubles
 * @author Sean Zimmerman
 *
 * @version 3.12.16
 */
public class SquareMatrix extends Matrix {

	/**
	 * Constructor which creates an empty m x m Matrix
	 */
	public SquareMatrix(int N) {
		super(N,N);
	}

	/**
	 * Constructor which allows you to create a Matrix filled with a 2D array
	 * @param data MUST be consistent (same number of columns for every row)
	 * @throws IllegalArgumentException
	 */
	public SquareMatrix(double[][] data) {
		super(data);
		if(data.length != data[0].length) {
			throw new IllegalArgumentException("A square matrix must be N x N, your data does not satisfy this.");
		}
	}

	/**
	 * A constructor which allows you to make a Matrix by giving a List of Vectors
	 * @param vecs MUST be consistent (same number of coordinates in every vector)
	 * @param row true if the List is of row vectors, false for column
	 */
	public SquareMatrix(List<Vector> vecs, boolean row) {
		super(vecs, row);
	}

	/**
	 * @return if the current matrix is symmetric (it equals its transpose)
	 */
	public boolean isSymmetric() {
		return this.equals(this.transpose());
	}

	/**
	 * A Square Matrix is diagonal only if it has non zero elements on the diagonal only
	 * @return if this square matrix is diagonal
	 */
	public boolean isDiagonal() {
		for(int i = 0; i < this.N; i++)
			for(int j = 0; j < this.N; j++) {
				if(i != j) 
					if(this.get(i, j) != 0)
						return false;
			}

		return true;
	}

	/**
	 * A Square Matrix is diagonal only if it has non zero elements on the diagonal or above it
	 * @return if this square matrix is upper diagonal
	 */
	public boolean isUpperDiagonal() {
		for(int i = 0; i < this.N; i++)
			for(int j = 0; j < this.N; j++) {
				if(i > j) 
					if(this.get(i, j) != 0)
						return false;
			}

		return true;
	}

	/**
	 * A Square Matrix is diagonal only if it has non zero elements on the diagonal or below it
	 * @return if this square matrix is lower diagonal
	 */
	public boolean isLowerDiagonal() {
		for(int i = 0; i < this.N; i++)
			for(int j = 0; j < this.N; j++) {
				if(i < j) 
					if(this.get(i, j) != 0)
						return false;
			}

		return true;
	}
}
