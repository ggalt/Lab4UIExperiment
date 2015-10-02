package coursera.lab.george.labuiexperiment;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.MainLayout);
        DisplayMetrics metrics = getBaseContext().getResources().getDisplayMetrics();

        GradientView gv = new GradientView(this, metrics.widthPixels, metrics.heightPixels);
        relativeLayout.addView(gv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class GradientView extends View {

        private Paint mPaint;
        private float centerX;
        private float centerY;
        private int width;
        private int height;
        private int[] gColors = new int[]{Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN};
        private boolean mSetShader = false;
        Bitmap bitmap;

        OnTouchListener touchListener;

        public GradientView(Context c, int w, int h) {
            super(c);
            mPaint = new Paint();
            mPaint.setAntiAlias(false);
            mPaint.setStyle(Paint.Style.FILL);
            width = w;
            height = h;
            bitmap  = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            setClickable(true);
            centerX = (float) getWidth()/2F;
            centerY = (float) getHeight()/2F;
            touchListener = new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    centerX = event.getX();
                    centerY = event.getY();
                    invalidate();
                    return false;
                }
            };
            setOnTouchListener(touchListener);
        }

        @Override
        public void setOnTouchListener(OnTouchListener l) {
            super.setOnTouchListener(l);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if(!mSetShader) {
                RadialGradient grad = new RadialGradient(centerX, centerY, (float)canvas.getHeight(), gColors, null, Shader.TileMode.CLAMP);
                mPaint.setShader(grad);
            }
            canvas.drawPaint(mPaint);
            invalidate();
        }

    }
}
