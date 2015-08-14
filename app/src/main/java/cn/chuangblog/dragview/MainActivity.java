package cn.chuangblog.dragview;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends Activity implements View.OnTouchListener {

    @Bind(R.id.layout)
    public LinearLayout mRrootLayout;

    private int _xDelta;
    private int _yDelta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRrootLayout.setOnTouchListener(this);
    }

    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();

                Log.e("TAG", layoutParams.leftMargin + ":" + layoutParams.topMargin);

                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;


                if (layoutParams.leftMargin < 0)
                    layoutParams.leftMargin = 0;
                if(layoutParams.topMargin<0)
                    layoutParams.topMargin = 0;


                if(layoutParams.leftMargin>440)
                    layoutParams.leftMargin = 440;
                if(layoutParams.topMargin>1045)
                    layoutParams.topMargin = 1045;

                view.setLayoutParams(layoutParams);
                break;
        }
        mRrootLayout.invalidate();
        return true;
    }

    @OnClick({R.id.imageView, R.id.imageView2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                showToast("imageView");
                break;

            case R.id.imageView2:
                showToast("imageView2");
                break;
        }
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
