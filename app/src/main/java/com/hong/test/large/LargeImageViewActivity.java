package com.hong.test.large;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hong.test.R;
import com.hong.test.view.LargeImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hong
 * @Name: LargeImageViewActivity
 * @Package com.hong.test
 * @Description: ${todo}
 * @date 15/11/4
 * @time 下午3:53
 * @copyright 广州市金税信息系统集成有限公司
 */
public class LargeImageViewActivity extends AppCompatActivity {

    private ImageView image1;
    private ImageView image2;

    private LargeImageView mImageLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image_view);

        initView();
//        loadImage();
        loadLargeImage();
    }

    public void initView() {
        image1 = (ImageView) findViewById(R.id.imageView);
        image2 = (ImageView) findViewById(R.id.imageView2);

        mImageLarge = (LargeImageView) findViewById(R.id.image_large);
    }

    public void loadLargeImage() {
        try {
            InputStream is = getAssets().open("qm.jpg");
            mImageLarge.setInputStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadImage() {
        try {
            InputStream is = getAssets().open("tangyan.jpg");

            // 获取图片的宽高
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, tmpOptions);
            int width = tmpOptions.outWidth;
            int height = tmpOptions.outHeight;

            // 设置图片显示中间区域
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(is, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(
                    new Rect(width / 2 - 100, height / 2 - 100, width / 2 + 100, height / 2 + 100),
                    options);
            image1.setImageBitmap(bitmap);

            Bitmap bitmap1 = BitmapFactory.decodeStream(is);
            image2.setImageBitmap(bitmap1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
