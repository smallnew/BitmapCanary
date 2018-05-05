package hexin.androidbitmapcanary;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewOverlay;

import static hexin.androidbitmapcanary.DrawableDetectUtil.getTipColorByScale;

/**
 * Created by smallnew on 2018\5\5 0005.
 */

public abstract class Detector<T extends View> {

    public Detector(){

    }

    abstract public void detect(T view);

    protected void markScaleView(Bitmap bitmap, T view){
        float scale = Math.max(bitmap.getHeight()*1.0f/view.getHeight(),bitmap.getWidth()*1.0f/view.getWidth());
        if(Build.VERSION.SDK_INT>=18){
            ViewOverlay overlay = view.getOverlay();
            overlay.clear();
            DrawableDetectUtil.TextDetectDrawable detectDrawable = new DrawableDetectUtil.TextDetectDrawable();
            detectDrawable.setText(String.format("%.1f",scale));
            detectDrawable.setBgColor(getTipColorByScale(scale));
            detectDrawable.setBounds(0,0,view.getWidth(),view.getHeight());
            overlay.add(detectDrawable);
        }else {
            view.setBackgroundColor(getTipColorByScale(scale));
        }
    }


}
