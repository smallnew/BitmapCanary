package hexin.androidbitmapcanary;

import java.util.HashMap;

/**
 * 图片检测工厂
 * Created by smallnew on 2018\5\5 0005.
 */

public class DetectorFactory {

    public static final int DETECT_TYPE_BACKGROUND = 1;
    public static final int DETECT_TYPE_IMAGESRC = 2;
    private static HashMap<Integer,Detector> detectorCache = new HashMap<>();

    public static Detector getDetector(int detectType){
        if(detectorCache.containsKey(detectType)){
            return detectorCache.get(detectType);
        }else {
            return produceDetector(detectType);
        }
    }

    private static Detector produceDetector(int detectType){
        if(DETECT_TYPE_BACKGROUND == detectType){
            return new BackgroundDetecotor();
        }else if(DETECT_TYPE_IMAGESRC == detectType){
            return new ImagesrcDetector();
        }else {//todo checkbox detector、progress detecor ... adding furtue
            throw new IllegalArgumentException("detectType not support "+detectType);
        }
    }
}
