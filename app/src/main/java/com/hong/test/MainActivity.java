package com.hong.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hong.test.large.LargeImageViewActivity;
import com.hong.test.recyclerview.RecyclerViewActivity;
import com.hong.test.webview.WebViewActivity;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    /**
     * ----------------------------------------------------------- *
     */
    private static final int REQUEST_IMAGE = 0x11;
    /**
     * ----------------------------------------------------------- *
     */
    private Context mContext;
    /**
     * ----------------------------------------------------------- *
     */
    private Button mBtnLargImage;
    private Button mBtnWebView;
    private Button mRecyclerView;
    private Button mImageSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * ----------------------------------------------------------- *
         */
        mContext = this;
        /**
         * ----------------------------------------------------------- *
         */
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_large_image:
                jumpActivity(LargeImageViewActivity.class);
                break;
            case R.id.btn_web_view:
                jumpActivity(WebViewActivity.class);
                break;
            case R.id.btn_recycler_view:
                jumpActivity(RecyclerViewActivity.class);
                break;
            case R.id.btn_image_selector:
                Intent intent = new Intent(mContext, MultiImageSelectorActivity.class);

                // whether show camera
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

                // max select image amount
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

                // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

                startActivityForResult(intent, REQUEST_IMAGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // do your logic ....

                Log.d(TAG, path.size() + "");
                Log.d(TAG, path.toString());
            }
        }
    }

    public void initView() {
        mBtnLargImage = (Button) findViewById(R.id.btn_large_image);
        mBtnLargImage.setOnClickListener(this);

        mBtnWebView = (Button) findViewById(R.id.btn_web_view);
        mBtnWebView.setOnClickListener(this);

        mRecyclerView = (Button) findViewById(R.id.btn_recycler_view);
        mRecyclerView.setOnClickListener(this);

        mImageSelector = (Button) findViewById(R.id.btn_image_selector);
        mImageSelector.setOnClickListener(this);
    }

    public void jumpActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
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
}
