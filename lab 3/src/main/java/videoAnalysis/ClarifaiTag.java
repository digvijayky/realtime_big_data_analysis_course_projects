/**
 * Created by digvijayky on 9/14/2016.
 */
package main.java.videoAnalysis;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.typography.hershey.HersheyFont;
import org.openimaj.video.xuggle.XuggleVideoWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ClarifaiTag {
    public static void main(String[] args) throws IOException {
        ClarifaiClient clarifai = new ClarifaiClient("5SvOVwrGoSSrqIp-1cZVV1dKCir5iWaLlJAyFWM4", "CMzm_bCl1TpChOQeJ_ozLPJr2BvxAxM57TB28cR6");
        File path1 = new File("lab 3/data/output/mainframes");
        File[] files1 = path1.listFiles();
        for (int i = 0; i < files1.length; i++){
            List<RecognitionResult> results =
                    clarifai.recognize(new RecognitionRequest(new File(files1[i].getPath())));
            System.out.println("\n\n%%%%%%%%%%%%%%%%%%%%%%%" + "frame" + i);
            List<String> tagList =new ArrayList<String>();
            for (Tag tag : results.get(0).getTags()) {
                System.out.println(tag.getName() + ": " + tag.getProbability());
                tagList.add(tag.getName());
            }
            MBFImage image = ImageUtilities.readMBF(new File(files1[i].getPath()));
            for(int y=0;y<(tagList.size())-1;y++) {
                image.drawText(tagList.get(y), 0, 150+y*25, HersheyFont.TIMES_BOLD, 25, RGBColour.CYAN);
            }
            DisplayUtilities.displayName(image, "videoFrames");

            File generatedImage =new File("lab 3/data/output/generated/generatedImage"+i+".jpg");
            ImageUtilities.write(image,generatedImage);

            XuggleVideoWriter forVideoWriting = new XuggleVideoWriter("lab 3/data/output/video/generatedVideo.mkv",480,360,60);
            BufferedImage img = null;
            MBFImage forWritingMBF = ImageUtilities.readMBF(new File("lab 3/data/output/mainframes/generatedImage0.jpg"));
            forVideoWriting.addFrame(forWritingMBF);
        }
    }
}

