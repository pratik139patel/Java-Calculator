package Java_Calculator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Random;
import org.junit.Test;

public class TestMatrix 
{
    /**
     * Tests the determinant of 1x1 matrix
     */
    @Test
    public void testOneByOneMatrixDetInverseRREF() 
    {
        try
        {
            for(double i = 0; i < 100; ++i)
            {
                final double[][] arr = new double[][] {{((new Random()).nextDouble()-0.5)*100}};

                //Test old fast determinant function
                Matrix test_matrix = new Matrix(arr);
                assertEquals(arr[0][0], test_matrix.Det__InPlace(), 0.1);

                //Test new slow determinant function
                test_matrix = new Matrix(arr);
                assertEquals(arr[0][0], test_matrix.RecursiveDet(), 0.1);

                if(arr[0][0] != 0)
                {
                    //Test inverse function
                    test_matrix = new Matrix(arr);
                    test_matrix.Inverse__InPlace();
                    assertEquals(1/arr[0][0], test_matrix.GetExactMatrix()[0][0], 0.1);
                }

                //Test new fast RREF function
                test_matrix = new Matrix(arr);
                test_matrix.RREF__InPlace();
                assertEquals(arr[0][0] == 0 ? 0 : 1, test_matrix.GetExactMatrix()[0][0], 0.1);

                //Test old slow RREF function
                test_matrix = new Matrix(arr);
                test_matrix.Rref__InPlace();
                assertEquals(arr[0][0] == 0 ? 0 : 1, test_matrix.GetExactMatrix()[0][0], 0.1);
            }
        }
        catch(Exception ex) { fail(); }
    }

    /**
     * Tests the determinant of 2x2 matrix
     */
    @Test
    public void testTwoByTwoMatrixDet() 
    {
        try
        {
            for(double i = 0; i < 100; ++i)
            {
                final double[][] arr = new double[2][2];
                for(int a = 0; a < arr.length; ++a) { for(int b = 0; b < arr[0].length; ++b) { arr[a][b] = ((new Random()).nextDouble()-0.5)*100; } }
                final double det = arr[0][0]*arr[1][1]-arr[0][1]*arr[1][0];

                //Test old determinant function
                Matrix test_matrix = new Matrix(arr);
                assertEquals(det, test_matrix.Det__InPlace(), 0.1);

                //Test new determinant function
                test_matrix = new Matrix(arr);
                assertEquals(det, test_matrix.RecursiveDet(), 0.1);
            }
        }
        catch(Exception ex) { fail(); }
    }

    /**
     * Tests the determinant of 3x3 matrix
     */
    @Test
    public void testThreeByThreeMatrixDet() 
    {
        try
        {
            for(double i = 0; i < 100; ++i)
            {
                final double[][] arr = new double[3][3];
                for(int a = 0; a < arr.length; ++a) { for(int b = 0; b < arr[0].length; ++b) { arr[a][b] = ((new Random()).nextDouble()-0.5)*100; } }
                final double det = arr[0][0]*(arr[1][1]*arr[2][2]-arr[1][2]*arr[2][1]) - arr[0][1]*(arr[1][0]*arr[2][2]-arr[1][2]*arr[2][0]) + arr[0][2]*(arr[1][0]*arr[2][1]-arr[1][1]*arr[2][0]);

                //Test old determinant function
                Matrix test_matrix = new Matrix(arr);
                assertEquals(det, test_matrix.Det__InPlace(), 0.1);

                //Test new determinant function
                test_matrix = new Matrix(arr);
                assertEquals(det, test_matrix.RecursiveDet(), 0.1);
            }
        }
        catch(Exception ex) { fail(); }
    }

    /**
     * Tests non square matrix determinant and Inverse
     */
    @Test
    public void testNonSquareMatrixDetAndInverse() 
    {
        for(int i = 0; i < 20; ++i)
        {
            int numRows = (new Random()).nextInt(50), numCols = (new Random()).nextInt(50);
            if(numRows < 0) { numRows = -numRows; }
            if(numCols < 0) { numCols = -numCols; }

            while(numCols == numRows || numCols == 0 || numRows == 0)
            {
                numRows = (new Random()).nextInt(50);
                numCols = (new Random()).nextInt(50);
                if(numRows < 0) { numRows = -numRows; }
                if(numCols < 0) { numCols = -numCols; }
            }

            Matrix non_square_matrix = new Matrix(new double[numRows][numCols]);

            try { non_square_matrix.Det__InPlace(); fail(); } catch(Exception ex) {}
            try { non_square_matrix.RecursiveDet(); fail(); } catch(Exception ex) {}
            try { non_square_matrix.Inverse__InPlace(); fail(); } catch(Exception ex) {}
        }
    }
}
