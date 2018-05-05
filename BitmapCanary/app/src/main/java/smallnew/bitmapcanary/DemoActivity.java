package smallnew.bitmapcanary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        findViewById(R.id.iv_go2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemoActivity.this,DemoActivity2.class));
            }
        });
    }
}
