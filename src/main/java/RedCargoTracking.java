/**
 * Raspberry Pi Code for FRC Team 2199.
 */
import edu.wpi.first.vision.VisionPipeline;

import java.util.List;
import java.util.ArrayList;

import org.opencv.core.*;
import org.opencv.imgproc.*;

/**
 * RedCargoTracking class
 * 
 * <p> An OpenCV pipeline generated with GRIP
 * 
 * @author Alex Pereira
 * @author Allwyn Pereira
 */
public class RedCargoTracking implements VisionPipeline {

	//Outputs
	private Mat cvResizeOutput = new Mat();
	private Mat hsvThreshold0Output = new Mat();
	private Mat hsvThreshold1Output = new Mat();
	private Mat cvCompareOutput = new Mat();
	private Mat maskOutput = new Mat();
	private Mat rgbThresholdOutput = new Mat();
	private Mat cvErodeOutput = new Mat();
	private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();
	private ArrayList<MatOfPoint> filterContoursOutput = new ArrayList<MatOfPoint>();

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * This is the primary method that runs the entire pipeline and updates the outputs.
	 */
	@Override
	public void process(Mat source0) {
		// Step CV_resize0:
		Mat cvResizeSrc = source0;
		Size cvResizeDsize = new Size(0, 0);
		double cvResizeFx = 3.0;
		double cvResizeFy = 3.0;
		int cvResizeInterpolation = Imgproc.INTER_LINEAR;
		cvResize(cvResizeSrc, cvResizeDsize, cvResizeFx, cvResizeFy, cvResizeInterpolation, cvResizeOutput);

		// Step HSV_Threshold0:
		Mat hsvThreshold0Input = cvResizeOutput;
		double[] hsvThreshold0Hue = {152.15827338129498, 180.0};
		double[] hsvThreshold0Saturation = {114.65827338129496, 255.0};
		double[] hsvThreshold0Value = {105.48561151079136, 255.0};
		hsvThreshold(hsvThreshold0Input, hsvThreshold0Hue, hsvThreshold0Saturation, hsvThreshold0Value, hsvThreshold0Output);

		// Step HSV_Threshold1:
		Mat hsvThreshold1Input = cvResizeOutput;
		double[] hsvThreshold1Hue = {0.0, 19.588618568743048};
		double[] hsvThreshold1Saturation = {133.00359712230215, 255.0};
		double[] hsvThreshold1Value = {112.36510791366906, 255.0};
		hsvThreshold(hsvThreshold1Input, hsvThreshold1Hue, hsvThreshold1Saturation, hsvThreshold1Value, hsvThreshold1Output);

		// Step CV_compare0:
		Mat cvCompareSrc1 = hsvThreshold0Output;
		Mat cvCompareSrc2 = hsvThreshold1Output;
		int cvCompareCmpop = Core.CMP_NE;
		cvCompare(cvCompareSrc1, cvCompareSrc2, cvCompareCmpop, cvCompareOutput);

		// Step Mask0:
		Mat maskInput = cvResizeOutput;
		Mat maskMask = cvCompareOutput;
		mask(maskInput, maskMask, maskOutput);

		// Step RGB_Threshold0:
		Mat rgbThresholdInput = maskOutput;
		double[] rgbThresholdRed = {71.08812949640287, 255.0};
		double[] rgbThresholdGreen = {0.0, 124.45392491467575};
		double[] rgbThresholdBlue = {0.0, 255.0};
		rgbThreshold(rgbThresholdInput, rgbThresholdRed, rgbThresholdGreen, rgbThresholdBlue, rgbThresholdOutput);

		// Step CV_erode0:
		Mat cvErodeSrc = rgbThresholdOutput;
		Mat cvErodeKernel = new Mat();
		Point cvErodeAnchor = new Point(-1, -1);
		double cvErodeIterations = 3.0;
		int cvErodeBordertype = Core.BORDER_CONSTANT;
		Scalar cvErodeBordervalue = new Scalar(-1);
		cvErode(cvErodeSrc, cvErodeKernel, cvErodeAnchor, cvErodeIterations, cvErodeBordertype, cvErodeBordervalue, cvErodeOutput);

		// Step Find_Contours0:
		Mat findContoursInput = cvErodeOutput;
		boolean findContoursExternalOnly = true;
		findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);

		// Step Filter_Contours0:
		ArrayList<MatOfPoint> filterContoursContours = findContoursOutput;
		double filterContoursMinArea = 20.0;
		double filterContoursMinPerimeter = 20.0;
		double filterContoursMinWidth = 35.0;
		double filterContoursMaxWidth = 150.0;
		double filterContoursMinHeight = 35.0;
		double filterContoursMaxHeight = 150.0;
		double[] filterContoursSolidity = {50.35971223021583, 100};
		double filterContoursMaxVertices = 1000000.0;
		double filterContoursMinVertices = 10.0;
		double filterContoursMinRatio = 0.0;
		double filterContoursMaxRatio = 1000.0;
		filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter, filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight, filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices, filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput);

	}

	/**
	 * This method is a generated getter for the output of a CV_resize.
	 * @return Mat output from CV_resize.
	 */
	public Mat cvResizeOutput() {
		return cvResizeOutput;
	}

	/**
	 * This method is a generated getter for the output of a HSV_Threshold.
	 * @return Mat output from HSV_Threshold.
	 */
	public Mat hsvThreshold0Output() {
		return hsvThreshold0Output;
	}

	/**
	 * This method is a generated getter for the output of a HSV_Threshold.
	 * @return Mat output from HSV_Threshold.
	 */
	public Mat hsvThreshold1Output() {
		return hsvThreshold1Output;
	}

	/**
	 * This method is a generated getter for the output of a CV_compare.
	 * @return Mat output from CV_compare.
	 */
	public Mat cvCompareOutput() {
		return cvCompareOutput;
	}

	/**
	 * This method is a generated getter for the output of a Mask.
	 * @return Mat output from Mask.
	 */
	public Mat maskOutput() {
		return maskOutput;
	}

	/**
	 * This method is a generated getter for the output of a RGB_Threshold.
	 * @return Mat output from RGB_Threshold.
	 */
	public Mat rgbThresholdOutput() {
		return rgbThresholdOutput;
	}

	/**
	 * This method is a generated getter for the output of a CV_erode.
	 * @return Mat output from CV_erode.
	 */
	public Mat cvErodeOutput() {
		return cvErodeOutput;
	}

	/**
	 * This method is a generated getter for the output of a Find_Contours.
	 * @return ArrayList<MatOfPoint> output from Find_Contours.
	 */
	public ArrayList<MatOfPoint> findContoursOutput() {
		return findContoursOutput;
	}

	/**
	 * This method is a generated getter for the output of a Filter_Contours.
	 * @return ArrayList<MatOfPoint> output from Filter_Contours.
	 */
	public ArrayList<MatOfPoint> filterContoursOutput() {
		return filterContoursOutput;
	}


	/**
	 * Resizes an image.
	 * @param src The image to resize.
	 * @param dSize size to set the image.
	 * @param fx scale factor along X axis.
	 * @param fy scale factor along Y axis.
	 * @param interpolation type of interpolation to use.
	 * @param dst output image.
	 */
	private void cvResize(Mat src, Size dSize, double fx, double fy, int interpolation,
		Mat dst) {
		if (dSize==null) {
			dSize = new Size(0,0);
		}
		Imgproc.resize(src, dst, dSize, fx, fy, interpolation);
	}

	/**
	 * Segment an image based on hue, saturation, and value ranges.
	 *
	 * @param input The image on which to perform the HSL threshold.
	 * @param hue The min and max hue
	 * @param sat The min and max saturation
	 * @param val The min and max value
	 * @param output The image in which to store the output.
	 */
	private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val,
	    Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
		Core.inRange(out, new Scalar(hue[0], sat[0], val[0]),
			new Scalar(hue[1], sat[1], val[1]), out);
	}

	/**
	 * Compares two Mats using designated method.
	 * @param src1 first Mat to compare.
	 * @param src2 second Mat to compare.
	 * @param cmpop Type of comparison to use.
	 * @param dst output Mat from the comparison.
	 */
	private void cvCompare(Mat src1, Mat src2, int cmpop, Mat dst) {
		Core.compare(src1,src2, dst, cmpop);
	}

	/**
	 * Filter out an area of an image using a binary mask.
	 * @param input The image on which the mask filters.
	 * @param mask The binary image that is used to filter.
	 * @param output The image in which to store the output.
	 */
	private void mask(Mat input, Mat mask, Mat output) {
		mask.convertTo(mask, CvType.CV_8UC1);
		Core.bitwise_xor(output, output, output);
		input.copyTo(output, mask);
	}

	/**
	 * Segment an image based on color ranges.
	 * @param input The image on which to perform the RGB threshold.
	 * @param red The min and max red.
	 * @param green The min and max green.
	 * @param blue The min and max blue.
	 * @param output The image in which to store the output.
	 */
	private void rgbThreshold(Mat input, double[] red, double[] green, double[] blue,
		Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2RGB);
		Core.inRange(out, new Scalar(red[0], green[0], blue[0]),
			new Scalar(red[1], green[1], blue[1]), out);
	}

	/**
	 * Expands area of lower value in an image.
	 * @param src the Image to erode.
	 * @param kernel the kernel for erosion.
	 * @param anchor the center of the kernel.
	 * @param iterations the number of times to perform the erosion.
	 * @param borderType pixel extrapolation method.
	 * @param borderValue value to be used for a constant border.
	 * @param dst Output Image.
	 */
	private void cvErode(Mat src, Mat kernel, Point anchor, double iterations,
		int borderType, Scalar borderValue, Mat dst) {
		if (kernel == null) {
			kernel = new Mat();
		}
		if (anchor == null) {
			anchor = new Point(-1,-1);
		}
		if (borderValue == null) {
			borderValue = new Scalar(-1);
		}
		Imgproc.erode(src, dst, kernel, anchor, (int)iterations, borderType, borderValue);
	}

	/**
	 * Sets the values of pixels in a binary image to their distance to the nearest black pixel.
	 * @param input The image on which to perform the Distance Transform.
	 * @param type The Transform.
	 * @param maskSize the size of the mask.
	 * @param output The image in which to store the output.
	 */
	private void findContours(Mat input, boolean externalOnly,
		List<MatOfPoint> contours) {
		Mat hierarchy = new Mat();
		contours.clear();
		int mode;
		if (externalOnly) {
			mode = Imgproc.RETR_EXTERNAL;
		}
		else {
			mode = Imgproc.RETR_LIST;
		}
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(input, contours, hierarchy, mode, method);
	}


	/**
	 * Filters out contours that do not meet certain criteria.
	 * @param inputContours is the input list of contours
	 * @param output is the the output list of contours
	 * @param minArea is the minimum area of a contour that will be kept
	 * @param minPerimeter is the minimum perimeter of a contour that will be kept
	 * @param minWidth minimum width of a contour
	 * @param maxWidth maximum width
	 * @param minHeight minimum height
	 * @param maxHeight maximimum height
	 * @param Solidity the minimum and maximum solidity of a contour
	 * @param minVertexCount minimum vertex Count of the contours
	 * @param maxVertexCount maximum vertex Count
	 * @param minRatio minimum ratio of width to height
	 * @param maxRatio maximum ratio of width to height
	 */
	private void filterContours(List<MatOfPoint> inputContours, double minArea,
		double minPerimeter, double minWidth, double maxWidth, double minHeight, double
		maxHeight, double[] solidity, double maxVertexCount, double minVertexCount, double
		minRatio, double maxRatio, List<MatOfPoint> output) {
		final MatOfInt hull = new MatOfInt();
		output.clear();
		//operation
		for (int i = 0; i < inputContours.size(); i++) {
			final MatOfPoint contour = inputContours.get(i);
			final Rect bb = Imgproc.boundingRect(contour);
			if (bb.width < minWidth || bb.width > maxWidth) continue;
			if (bb.height < minHeight || bb.height > maxHeight) continue;
			final double area = Imgproc.contourArea(contour);
			if (area < minArea) continue;
			if (Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < minPerimeter) continue;
			Imgproc.convexHull(contour, hull);
			MatOfPoint mopHull = new MatOfPoint();
			mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
			for (int j = 0; j < hull.size().height; j++) {
				int index = (int)hull.get(j, 0)[0];
				double[] point = new double[] { contour.get(index, 0)[0], contour.get(index, 0)[1]};
				mopHull.put(j, 0, point);
			}
			final double solid = 100 * area / Imgproc.contourArea(mopHull);
			if (solid < solidity[0] || solid > solidity[1]) continue;
			if (contour.rows() < minVertexCount || contour.rows() > maxVertexCount)	continue;
			final double ratio = bb.width / (double)bb.height;
			if (ratio < minRatio || ratio > maxRatio) continue;
			output.add(contour);
		}
	}

}

// End of RedCargoTracking Class