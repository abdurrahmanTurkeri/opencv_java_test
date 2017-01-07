package OpencvTest;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCvTest {
	
    public static void main(String args[]){
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	Mat im = Imgcodecs.imread("src/images/fatihterim.jpg");					
    	System.out.println("Its Started");
    	long startTime = System.nanoTime();
    	Mat mask = new Mat();							
        Mat bgModel = new Mat();					
        Mat fgModel = new Mat();					
        Rect rect = new Rect(150, 6,200,230);					
        Mat source = new Mat(1, 1, CvType.CV_8U, new Scalar(3));	
        System.out.println("Grabcut Starting");
        Imgproc.grabCut(im, mask, rect, bgModel, fgModel, 1, 0);		
        Core.compare(mask, source, mask, Core.CMP_EQ);
        Mat fg = new Mat(im.size(), CvType.CV_8UC1, new Scalar(0, 0, 0));
        im.copyTo(fg, mask);							
    	Imgcodecs.imwrite("src/images/test2.jpg", fg);
    	long endTime = System.nanoTime();
    	long duration = (endTime - startTime);
    	System.out.println("Its Done Grabcut suresi :"+duration/1000000/1000);
    	
    	}

    
	 public  static void grabCutWithRect(String imageName,int x,int y,int width,int height) throws Exception{
		 	Mat mainImageMat=Imgcodecs.imread("src/images/"+imageName);
			Rect rect=new Rect(x, y, width, height);
			Mat mask=new Mat();
			Mat bgdModel = new Mat();						
		    Mat fgdModel = new Mat();
		    Mat source = new Mat(1, 1, CvType.CV_8U, new Scalar(3));
			Imgproc.grabCut(mainImageMat, mask, rect, bgdModel, fgdModel, 1);
			 Core.compare(mask, source, mask, Core.CMP_EQ);
		        Mat fg = new Mat(mainImageMat.size(), CvType.CV_8UC1, new Scalar(0, 0, 0));	
		        mainImageMat.copyTo(fg, mask);							
			Imgcodecs.imwrite("src/images/result.jpg", fg);	
			
			
		 
	 }
	 
	
}
