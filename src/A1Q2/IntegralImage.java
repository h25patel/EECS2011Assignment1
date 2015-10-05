package A1Q2;

/**
 * Represents an integer integral image, which allows the user to query the mean
 * value of an arbitrary rectangular subimage in O(1) time.  Uses O(n) memory,
 * where n is the number of pixels in the image.
 *
 * @author jameselder
 */
public class IntegralImage {

    private final int[][] integralImage;
    private final int imageHeight; // height of image (first index)
    private final int imageWidth; // width of image (second index)
    private int val; //values to calculate
    private double meanValue; //mean value of the values

    /**
     * Constructs an integral image from the given input image.  
     *
     * @author jameselder
     * @param image The image represented
     * @throws InvalidImageException Thrown if input array is not rectangular
     */
    public IntegralImage(int[][] image) throws InvalidImageException {
        //implement this method.
    	
       	int[] row; //array - row
    	imageWidth = image.length; //row
    	
    	
    	if(imageWidth > 0)
    	{
    		imageHeight = image[1].length; //column
    	}
    	else
    	{
    		imageHeight = 0;
    	}
    	 integralImage = new int[imageWidth][imageHeight];
    	 //values in integer
    	 for (int i = 0; i < imageWidth; i++) { //check for the image. return error if not rectangle
    	        
    	        row = image[i];
    	        int rl = row.length;
    	        if (rl != imageHeight) {
    	            throw new InvalidImageException("Image is not rectangular");
    	        }
    	        for (int j = 0; j < imageHeight; j++) { //constructing the image if it passes the invalidImageException loop above
    	        	val = image[i][j];
    	            if (i > 0) {
    	                val += integralImage[i - 1][j];
    	            }
    	            if (j > 0) {
    	                val += integralImage[i][j - 1];
    	            }
    	            if (i > 0 && j > 0) {
    	                val -= integralImage[i - 1][j - 1];
    	            }
    	            integralImage[i][j] = val;
    	        }
    	    }
    }

    /**
     * Returns the mean value of the rectangular sub-image specified by the
     * top, bottom, left and right parameters. The sub-image should include
     * pixels in rows top and bottom and columns left and right.  For example,
     * top = 1, bottom = 2, left = 1, right = 2 specifies a 2 x 2 sub-image starting
     * at (top, left) coordinate (1, 1).  
     *
     * @author jameselder
     * @param top top row of sub-image
     * @param bottom bottom row of sub-image
     * @param left left column of sub-image
     * @param right right column of sub-image
     * @return 
     * @throws BoundaryViolationException if image indices are out of range
     * @throws NullSubImageException if top > bottom or left > right
     */
    public double meanSubImage(int top, int bottom, int left, int right) throws BoundaryViolationException, NullSubImageException {
        //implement this method
    	if(top > bottom || left > right)
    	{
    		throw new NullSubImageException();
    	}
    	
    	if(bottom <0 || left < 0 || top < 0 || right < 0) //loop to check for boundaries
    	{
    		throw new BoundaryViolationException();
    	}
    	else //if no violations are done then calculate the mean of the sub image values
    	{
    		int tLess = top -1;
    		int lLess = left - 1;
    		int botTop = (bottom - top + 1);
    		int rigLef = (right - left + 1);
    		
    		meanValue = integralImage[bottom][right];
            if (top > 0) {
                meanValue -= integralImage[tLess][right];
            }
            if (left > 0) {
                meanValue -= integralImage[bottom][lLess];
            }
            if (top > 0 && left > 0) {
                meanValue += integralImage[tLess][lLess];
            }
            meanValue /= botTop * rigLef;        	
    	}
    	
    	return meanValue; //returns the mean value
    	
    }
    
}