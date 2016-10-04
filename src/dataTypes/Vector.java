package dataTypes;
/**
 * Class which represents a Vector of real numbers. 
 * The type of a Vector is a double but you have a vector of ints too.
 * @author Sean Zimmerman
 *
 * @version 3.10.2016
 */
public class Vector {

	final int N;   // the length of the vector
	double[] data; // array of vector's components
	
	/**
	 * Create a zero vector of length N
	 */
	public Vector(int N) {
		this.N = N;
		this.data = new double[N];
	}
	
	/**
	 * Create a vector from an array
	 */
	public Vector(double[] data) {
		this.N = data.length;
		
		this.data = new double[N];
		for(int i = 0; i < N; i++) {
			this.data[i] = data[i];
		}
	}
	
	/**
	 * @return the length (# dimensions) of the vector
	 */
	public int length() {
		return N;
	}
	
	/**
	 * @return the inner product of this vector and input vector
	 */
	public double dot(Vector other) {
		if(this.N != other.N) {
			throw new RuntimeException("Dimensions don't agree.");
		}
		
		double sum = 0.0;
		
		for(int i = 0; i < N; i++) {
			sum += (this.data[i] * other.data[i]);
		}
		
		return sum;
	}
	
	/**
	 * @return the magnitude of this vector
	 */
	public double magnitude() {
		return Math.sqrt(this.dot(this));
	}
	
	/**
	 * @return the distance between two vectors
	 */
	public double distance(Vector other) {
		if(this.N != other.N) {
			throw new RuntimeException("Dimensions don't agree.");
		}
		
		return this.subtract(other).magnitude();
	}
	
	/**
	 * @return this + other
	 */
	public Vector add(Vector other) {
		if(this.N != other.N) {
			throw new RuntimeException("Dimensions don't agree.");
		}
		
		Vector result = new Vector(N);
		for(int i = 0; i < N; i++) {
			result.data[i] = this.data[i] + other.data[i];
		}
		
		return result;
	}
	
	/**
	 * @return this - other
	 */
	public Vector subtract(Vector other) {
		if(this.N != other.N) {
			throw new RuntimeException("Dimensions don't agree.");
		}
		
		Vector result = new Vector(N);
		for(int i = 0; i < N; i++) {
			result.data[i] = this.data[i] - other.data[i];
		}
		
		return result;
	}
	
	/**
	 * @return the corresponding coordinate
	 * Pre: i <= 0 < N
	 */
	public double coordinate(int i) {
		return this.data[i];
	}
	
	/**
	 * @return a Vector whose value is this * factor
	 */
	public Vector scalarMult(double factor) {
		Vector result = new Vector(N);
		for(int i = 0; i < N; i++) {
			result.data[i] = factor * this.data[i];
		}
		
		return result;
	}
	
	/**
	 * @return the unit vector of this vector
	 */
	public Vector unitVector() {
		if(this.magnitude() == 0.0) {
			throw new RuntimeException("Cannot get the unit vector of the Zero-vector.");
		}

		return this.scalarMult(1.0 / this.magnitude()); //unit v = v / mag v
	}
	
	/**
	 * @return the Cartesian cross product of this x other
	 */
	public Vector cross(Vector other) {
		if(N != 3 || other.N != 3) {
			throw new RuntimeException("Can only compute the cross product of 2 3-D vector's.");
		}

		Vector result = new Vector(3);
		
		//this = u; other = v
		result.data[0] = (this.data[1] * other.data[2]) - (this.data[2] * other.data[1]); //x = u_y*v_z - u_z*v_y
		result.data[1] = (this.data[2] * other.data[0]) - (this.data[0] * other.data[2]); //y = u_z*v_x - u_x*v_z
		result.data[2] = (this.data[0] * other.data[1]) - (this.data[1] * other.data[0]); //z = u_x*v_y - u_y*v_x
		
		return result;
	}
	
	/**
	 * Two Vectors are equal when they have the same size and all their components are the same
	 * @return if this vector is equal to the object
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof Vector)) {
			return false;
		}
		
		Vector other = (Vector)obj;
		
		if(this.N != other.N) { //must have same size
			return false;
		}
		
		for(int i = 0; i < this.N; i++) {
			if(this.coordinate(i) != other.coordinate(i)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @return a formatted String representing a vector in format [x, y, z]
	 */
	public String toString() {
		String result = data[0] + "";
		
		for(int i = 1; i < N; i++) {
			result += ", " + data[i];
		}
		
		return "[" + result + "]";
	}
}
