package hexin.androidbitmapcanary;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

import static hexin.androidbitmapcanary.DrawableDetectUtil.MAX_SCALE;

/**
 * 背景图片检测
 * Created by smallnew on 2018\5\5 0005.
 */

public class BackgroundDetecotor extends Detector<View> {
    public BackgroundDetecotor() {
        super();
    }

    @Override
    public void detect(View view) {
        Drawable backGroupDrawable = view.getBackground();
        if(backGroupDrawable instanceof StateListDrawable){
            backGroupDrawable = backGroupDrawable.getCurrent();
        }
        if(backGroupDrawable!=null&&backGroupDrawable instanceof BitmapDrawable){
            Bitmap bitmap = ((BitmapDrawable) backGroupDrawable).getBitmap();
            if(bitmap.getHeight()>view.getHeight()*MAX_SCALE
                    ||bitmap.getWidth()>view.getWidth()*MAX_SCALE){
                markScaleView(bitmap,view);
            }
        }
    }
}
