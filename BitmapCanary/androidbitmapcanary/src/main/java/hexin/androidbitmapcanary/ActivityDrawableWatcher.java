package hexin.androidbitmapcanary;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by smallnew on 2018/5/2.
 */

public class ActivityDrawableWatcher {
    private static HashMap<Integer,DrawableDetectListener> drawableListenerRecord;
    public static void watchDrawable(Application application){
        new ActivityDrawableWatcher(application).startWatch();
    }
    private static class DrawableDetectListener implements ViewTreeObserver.OnGlobalLayoutListener{
        private WeakReference<View> rootView;
        public DrawableDetectListener(View rootView){
            this.rootView = new WeakReference<View>(rootView);
        }
        @Override
        public void onGlobalLayout() {
            if(rootView!=null&&rootView.get()!=null&&rootView.get() instanceof ViewGroup){
                DrawableDetectUtil.detectDrawableSize((ViewGroup)rootView.get());
            }
        }
    }
    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks =
            new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    View decorView = activity.getWindow().getDecorView();
                    ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                    DrawableDetectListener drawableDetectListener = new DrawableDetectListener(decorView);
                    viewTreeObserver.addOnGlobalLayoutListener(drawableDetectListener);
                    drawableListenerRecord.put(decorView.hashCode(),drawableDetectListener);
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    View decorView = activity.getWindow().getDecorView();
                    ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                    DrawableDetectListener detectListener = drawableListenerRecord.get(decorView.hashCode());
                    if(detectListener!=null) {
                        viewTreeObserver.removeOnGlobalLayoutListener(detectListener);
                    }
                }
            };
    private final Application application;

    public ActivityDrawableWatcher(Application application){
        this.application = application;
        drawableListenerRecord = new HashMap<>();
    }

    public void startWatch(){
        stopWatch();
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public void stopWatch(){
        application.unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
    }
}
