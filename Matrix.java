package JavaCalculator;

//Matrix class designed to perform various matrix operations
public class Matrix 
{
    private int numRows;
	private int numCols;
	private double[][] matrix;
 

	/**
	 * Make a copy of the input 2D double array and store it in matrix
	 * @param m Input 2D double array
	 */
    public Matrix(final double[][] mat) 
    {
		this.numRows = mat.length;
		this.numCols = mat[0].length;
		this.matrix = new double[this.numRows][this.numCols];
		for(int i = 0; i < this.numRows; ++i) { System.arraycopy(mat[i], 0, this.matrix[i], 0, this.numCols); }
	}


	/**
	 * Make a copy of the input matrix object and store it in matrix field
	 * @param m Input matrix object
	 */
	public Matrix(final Matrix mat)
	{
		this.numCols = mat.numCols; 
		this.numRows = mat.numRows;
		this.matrix = mat.GetMatrixCopy();
	}
 

	/**
	 * Swap rows in place by altering matrix field
	 * @param to_row Input row 1
	 * @param from_row Input row 2
	 */
	protected void InterchangeRow__InPlace(final int to_row, final int from_row)
	{
		if (to_row == from_row) { return; }
		double[] temp = this.matrix[to_row];
		this.matrix[to_row] = this.matrix[from_row];
		this.matrix[from_row] = temp;
	}


	/**
	 * Makes a row a leading one row in place by altering matrix field
	 * @param row Input row
	 */
	protected void leadOne__InPlace(double[] row)
	{
		double pivot = 0;

		//Find a non-zero number in the row as pivot and divide rest of the elements by pivot
		for (int i = 0; i < this.numCols; ++i) 
		{
			//Divide each non-zero row element by pivot
			if (row[i] != 0) 
			{
				if(pivot == 0) { pivot = row[i]; }
				row[i] /= pivot;
			}
		}
	}


	/**
	 * Makes a row a leading one row in place by altering matrix field
	 * @param row_pos Input row position
	 */
	protected void leadOne__InPlace(final int row_pos)
	{
		double pivot_val = 0;

		//Find a non-zero number in the row as pivot and divide rest of the elements by pivot
		for (int i = 0; i < this.numCols; ++i) 
		{
			//Divide each non-zero row element by pivot
			if (this.matrix[row_pos][i] != 0) 
			{
				if(pivot_val == 0) { pivot_val = this.matrix[row_pos][i]; }
				this.matrix[row_pos][i] /= pivot_val;
			}
		}
	}


	/**
	 * Makes a row a leading one row in place by altering matrix field
	 * @param row_pos Input row position
	 * @return Pivot position (-1 if none)
	 */
	protected int leadOne__InPlace__ReturnPivot(final int row_pos)
	{
		int pivot = -1;
		double pivot_val = 0;

		//Find a non-zero number in the row as pivot and divide rest of the elements by pivot
		for (int i = 0; i < this.numCols; ++i) 
		{
			//Divide each non-zero row element by pivot
			if (this.matrix[row_pos][i] != 0) 
			{
				if(pivot_val == 0) { pivot_val = this.matrix[row_pos][i]; pivot = i; }
				this.matrix[row_pos][i] /= pivot_val;
			}
		}

		return pivot;
	}


	/**
	 * @param row_pos_1 Row 1 location
	 * @param row_pos_2 Row 2 location
	 * @return True if two arrays contain same elements, otherwise false
	 */
	public boolean CompareTwoArray(final int row_pos_1, final int row_pos_2)
	{
		for(int i = 0; i < this.numCols; ++i) { if(this.matrix[row_pos_1][i] != this.matrix[row_pos_2][i]) { return false; } }
		return true;
	}


	/**
	 * Converts all the entries within the row zero by altering the matrix field
	 * @param row_pos Location of the row
	 */
	protected void ChangeRowToZeros__InPlace(final int row_pos)
	{
		for(int i = 0; i < this.numCols; ++i) { this.matrix[row_pos][i] = 0; }
	}


	/**
	 * Calculates the RREF of the matrix (New Fast Version) by altering matrix field
	 */
	public void RREF__InPlace()
	{
		for(int i = 0; i < this.numRows; ++i) 
		{ 
			int pivot = leadOne__InPlace__ReturnPivot(i);
			if(pivot == -1) { continue; }

			for(int j = 0; j < this.numRows; ++j)
			{
				if(this.matrix[j][pivot] != 0 && i != j)
				{
					double scalar = this.matrix[j][pivot];
					for(int k = 0; k < this.numCols; ++k)
					{
						this.matrix[j][k] -= scalar * this.matrix[i][k];
					}
				}
			}
		}
		
		//Get a non-zero element along or above the diagonal of the matrix
		for (int j = 0, count = 0; j < this.numCols; ++j)
		{
			for (int i = count; i < this.numRows; ++i) 
			{
				if (this.matrix[i][j] != 0) 
				{
					InterchangeRow__InPlace(count, i);
					++count; 
					break;
				}
			}
		}
	}


	/**
	 * Calculate and store RREF of the matrix (Old Slow Version) by altering matrix field
	 */
	public void Rref__InPlace()
	{
		//Get a non-zero element along the diagonal of the matrix
		for (int j = 0, count = 0; j < this.numCols; ++j)
		{
			for (int i = count; i < this.numRows; ++i) 
			{ 
				if (this.matrix[i][j] != 0.0) 
				{ 
					InterchangeRow__InPlace(count, i);
					++count; 
					break;
				} 
			}
		}

		//Get a leading zero along the diagonal, and zero out each entry in other rows along the column
		for (int i = 0; i < this.numRows; ++i)
		{
			leadOne__InPlace(i);
			int pivot_col = 0;

			//Find pivot column
			for (int k = 0; k < this.numCols; ++k) 
			{ 
				if (this.matrix[i][k] != 0.0) 
				{ 
					pivot_col = k; 
					break; 
				} 
			}

			if (pivot_col < this.numCols)
			{
				//Subtract the scalar multiple of the current row to other rows within the column such that all other enties are evaluated to zero
				for (int j = 0; j < this.numRows; ++j)
				{
					if (this.matrix[j][pivot_col] != 0.0 && i != j) 
					{
						double scalar = this.matrix[j][pivot_col];

						for (int x = 0; x < this.numCols; ++x) 
						{ 
							this.matrix[j][x] -= scalar * this.matrix[i][x]; 
						}
					}
				}
			}
		}

		//Get a leading 1 across the diagonal
		for (int i = 0; i < this.numRows; ++i) { leadOne__InPlace(i); }

		//Get a non-zero element along the diagonal of the matrix
		for (int j = 0, count = 0; j < this.numCols; ++j)
		{
			for (int i = count; i < this.numRows; ++i) 
			{ 
				if (this.matrix[i][j] != 0.0) 
				{ 
					if(count != i) { InterchangeRow__InPlace(count, i); } 
					++count; 
					break;
				} 
			}
		}
	}

	/**
	 * Finds and stores the inverse of the matrix by altering matrix field itself
	 * @throws Exception When number of rows and columns do not match or the matix is not invertible
	 */
	public void Inverse__InPlace() throws Exception
	{
		if(this.numRows != this.numCols) { throw new Exception("NEED SQUARE MATRIX"); }

		//If the matrix is not invertible
		if((new Matrix(this.matrix)).Det__InPlace() == 0) { throw new Exception("MATRIX DET IS ZERO"); }
			
		double[][] inverse_matrix = new double[this.numRows][this.numCols];

		//Creates a matrix with ones across the diagonal, and zeros elsewhere
		for(int i = 0; i < this.numRows; ++i)
		{
			for(int j = 0; j < this.numCols; ++j)
			{
				if(i == j) { inverse_matrix[i][j] = 1; }
				else { inverse_matrix[i][j] = 0; }
			}
		}

		int count = 0;

		//Interchange matrix rows if needed in order to have non-zero values across the diagonal
		for (int j = 0; j < this.numCols; ++j)
		{
			for (int i = count; i < this.numRows && count < this.numRows; ++i) 
			{ 
				if (this.matrix[i][j] != 0.0) 
				{ 
					if(count != i) 
					{
						InterchangeRow__InPlace(count, i);
						double[] temp = new double[this.numCols];
						temp = inverse_matrix[count];
						inverse_matrix[count] = inverse_matrix[i];
						inverse_matrix[i] = temp;
					}
					++count; 
					break;
				} 
			}
		}

		for (int i = 0; i < this.numRows; ++i)
		{
			double pivot = 0;

			//Find pivot of the row
			for (int a = 0; a < this.numCols; ++a) 
			{ 
				if (this.matrix[i][a] != 0.0) 
				{ 
					pivot = this.matrix[i][a]; 
					break; 
				} 
			}
			
			if (pivot != 0) 
			{ 
				//Divide entire row of original matrix and inverse matrix by the pivot 
				for (int a = 0; a < this.numCols; ++a) 
				{ 
					this.matrix[i][a] /= pivot; 
					inverse_matrix[i][a] /= pivot; 
				} 
			}

			int pivot_col = 0;

			//Find a non-zero column pivot
			for (int k = 0; k < this.numCols; ++k) 
			{ 
				if (this.matrix[i][k] != 0.0) 
				{ 
					pivot_col = k; 
					break; 
				} 
			}

			if (pivot_col < this.numCols)
			{
				for (int j = 0; j < this.numRows; ++j)
				{
					if (this.matrix[j][pivot_col] != 0.0 && i != j) 
					{
						double scalar = this.matrix[j][pivot_col];

						for (int x = 0; x < this.numCols; ++x) 
						{ 
							this.matrix[j][x] -= scalar * this.matrix[i][x]; 
							inverse_matrix[j][x] -= scalar * inverse_matrix[i][x]; 
						}
					}
				}
			}
		}

		for (int i = 0; i < this.numRows; ++i) 
		{ 
			double pivot = 0;

			for (int a = 0; a < this.numCols; ++a) 
			{ 
				if (this.matrix[i][a] != 0.0) 
				{ 
					pivot = this.matrix[i][a]; 
					break; 
				} 
			}
			
			if (pivot != 0) 
			{ 
				for (int a = 0; a < this.numCols; ++a) 
				{ 
					this.matrix[i][a] /= pivot; 
					inverse_matrix[i][a] /= pivot; 
				} 
			}
		}

		count = 0;
		for (int j = 0; j < this.numCols; ++j)
		{
			for (int i = count; i < this.numRows && count < this.numRows; ++i) 
			{ 
				if (this.matrix[i][j] != 0.0) 
				{ 
					if(count != i) 
					{
						InterchangeRow__InPlace(count, i); 
						if (count != i)
						{
							double[] temp = new double[this.numCols];
							temp = inverse_matrix[count];
							inverse_matrix[count] = inverse_matrix[i];
							inverse_matrix[i] = temp;
						}
					} 
					++count; 
					break;
				} 
			}
		}
		this.matrix = inverse_matrix;
	}


	/**
	 * @return Gets the product of each diagonal entry
	 */
	protected double DiagonalProduct()
	{
		double diag_multi = 1;

		for(int i = 0; i < ((this.numRows<this.numCols)?(this.numRows):(this.numCols)); ++i) 
		{
			diag_multi *= this.matrix[i][i];
		}
		
		return diag_multi;
	}
	

	/**
	 * @param mat Input matrix
	 * @param curr_col Row at which determinant is being calculated
	 * @return Submatrix of the input matrix
	 */
	protected double[][] DetSubmatrix(final double[][] mat, final int curr_col)
	{
		double[][] new_mat = new double[mat.length-1][mat[0].length-1];

		for(int i = 1, ii = 0; i < mat.length; ++i, ++ii)
		{
			for(int j = 0, jj = 0; j < mat[0].length; ++j)
			{
				if(j == curr_col) { continue; }
				new_mat[ii][jj] = mat[i][j];
				++jj;
			}
		}

		return new_mat;
	}


	/**
	 * @param mat 2D double matrix array
	 * @return Determinant of the matrix
	 */
	private double recursiveDet(double[][] mat)
	{
		if(mat.length == 1) { return mat[0][0]; }

		double det = 0;
		for(int i = 0; i < mat.length; ++i)
		{
			if(i%2 == 0) { det += mat[0][i] * recursiveDet(DetSubmatrix(mat,i)); }
			else { det -= mat[0][i] * recursiveDet(DetSubmatrix(mat,i)); }
		}

		return det;
	}


	/**
	 * Calculate determinant and keeps matrix field intact (Slow Version)
	 * @return Determinant of the matrix using recursive calls
	 * @throws Exception When number of rows and columns do not match
	 */
	public double RecursiveDet() throws Exception
	{
		if(this.numRows != this.numCols) { throw new Exception("NEED SQUARE MATRIX"); }
		return recursiveDet(this.matrix);
	}


	/**
	 * Calculates the determinant by altering matrix field (Fast Version)
	 * @return Determinant of the matrix
	 * @throws Exception When number of rows and columns do not match
	 */
	public double Det__InPlace() throws Exception
	{
		if(this.numRows != this.numCols) { throw new Exception("NEED SQUARE MATRIX"); }

		//If there is a zero entry in the diagnoal entry of the RREF, the the det is zero
		Matrix temp_matrix = new Matrix(this.matrix);
		temp_matrix.RREF__InPlace();		
		if(temp_matrix.DiagonalProduct() == 0.0) { return 0; }

		//Initialize the determinant of the matrix
		double det_ele_matrix = 1;

		//RREF()
		int count = 0;
		for (int j = 0; j < this.numCols; ++j)
		{
			for (int i = count; i < this.numRows; ++i) 
			{ 
				if (this.matrix[i][j] != 0.0) 
				{ 
					//CHANGED
					if(count != i) 
					{
						InterchangeRow__InPlace(count, i); 
						det_ele_matrix *= -1;
					}

					++count; 
					break; 
				} 
			}
		}

		for (int i = 0; i < this.numRows; ++i)
		{
			//leadOne(matrix[i])
			{
				double pivot = 0;
				for (int i2 = 0; i2 < this.numCols; ++i2) 
				{ 
					if (this.matrix[i][i2] != 0.0) 
					{ 
						pivot = this.matrix[i][i2]; 
						break; 
					} 
				}

				if (pivot != 0) 
				{ 
					det_ele_matrix *= pivot;

					for (int i2 = 0; i2 < this.numCols; ++i2) 
					{ 
						this.matrix[i][i2] /= pivot; 
					} 
				}
			}

			int pivot_col = 0;
			for (int k = 0; k < this.numCols; ++k) 
			{ 
				if (this.matrix[i][k] != 0.0) 
				{ 
					pivot_col = k; 
					break; 
				} 
			}

			if (pivot_col < this.numCols)
			{
				for (int j = 0; j < this.numRows; ++j)
				{
					if (this.matrix[j][pivot_col] != 0.0 && i != j) 
					{
						double scalar = this.matrix[j][pivot_col];

						for (int x = 0; x < this.numCols; ++x) 
						{ 
							this.matrix[j][x] -= scalar * this.matrix[i][x]; 
						}
					}
				}
			}
		}

		for (int i = 0; i < this.numRows; ++i) 
		{ 
			//leadOne(matrix[i])
			double pivot = 0;

			for (int i2 = 0; i2 < this.numCols; ++i2) 
			{ 
				if (this.matrix[i][i2] != 0.0) 
				{ 
					pivot = this.matrix[i][i2]; 
					break; 
				} 
			}

			if (pivot != 0) 
			{ 
				det_ele_matrix *= pivot; 
				
				for (int i2 = 0; i2 < this.numCols; ++i2) 
				{ 
					this.matrix[i][i2] /= pivot; 
				} 
			} 
		}

		count = 0;
		for (int j = 0; j < this.numCols; ++j)
		{
			for (int i = count; i < this.numRows; ++i) 
			{ 
				if (this.matrix[i][j] != 0.0) 
				{ 
					//CHANGED
					if(count != i) 
					{
						InterchangeRow__InPlace(count, i); 
						det_ele_matrix *= -1;
					}

					++count; 
					break; 
				} 
			}
		}

		return (det_ele_matrix);
	}


	/**
	 * @return 2D double matrix copy
	*/
	public double[][] GetMatrixCopy() 
	{
		double[][] ret_matrix = new double[this.numRows][this.numCols];
		for(int i = 0; i < this.numRows; ++i) { System.arraycopy(this.matrix[i], 0, ret_matrix[i], 0, this.numCols); }
		return ret_matrix; 
	}


	/**
	 * @return Exact 2D array matrix object
	 */
	public double[][] GetExactMatrix() { return this.matrix; }

}//Matrix()
