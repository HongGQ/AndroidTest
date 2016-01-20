package com.hong.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hong.test.large.MoveGestureDetector;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hong
 * @Name: LargeImageView
 * @Package com.hong.test.view
 * @Description: ${todo}
 * @date 15/11/4
 * @time 下午4:12
 * @copyright 广州市金税信息系统集成有限公司
 */
public class LargeImageView extends View {

    private static final BitmapFactory.Options mOptions = new BitmapFactory.Options();

    static {
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    }

    private BitmapRegionDecoder mRegionDecoder;
    /**
     * 图片宽高
     */
    private int mImageHeight;
    private int mImageWidth;
    /**
     * 绘制区域
     */
    private volatile Rect mRect = new Rect();

    /**
     * 手势控制
     */
    private MoveGestureDetector mDetector;

    public LargeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMove();
    }

    public void setInputStream(InputStream is) {

        try {
            mRegionDecoder = BitmapRegionDecoder.newInstance(is, false);
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, tmpOptions);
            mImageHeight = tmpOptions.outHeight;
            mImageWidth = tmpOptions.outWidth;

            requestLayout();
            invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化手势控制
     */
    public void initMove() {
        mDetector = new MoveGestureDetector(getContext(), new MoveGestureDetector.SimpleMoveGestureDetector() {
            @Override
            public boolean onMove(MoveGestureDetector detector) {

                int moveX = (int) detector.getMoveX();
                int moveY = (int) detector.getMoveY();

                if (mImageWidth > getWidth()) {
                    mRect.offset(-moveX, 0);
                    checkWidth();
                    invalidate();
                }

                if (mImageHeight > getHeight()) {
                    mRect.offset(0, -moveY);
//                    checkHeight();
                    invalidate();
                }


                return true;
            }
        });
    }

    private void checkWidth() {

        Rect rect = mRect;
        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        // 如果滑动超出右边界，则设置为最右
        if (rect.right > imageWidth) {
            rect.right = imageWidth;
            rect.left = imageWidth - getWidth();
        }

        // 如果滑动超出左边界，则设置为最左
        if (rect.left < 0) {
            rect.left = 0;
            rect.right = getWidth();
        }
    }


    private void checkHeight() {

        Rect rect = mRect;
        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        if (rect.bottom > imageHeight) {
            rect.bottom = imageHeight;
            rect.top = imageHeight - getHeight();
        }

        if (rect.top < 0) {
            rect.top = 0;
            rect.bottom = getHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onToucEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bm = mRegionDecoder.decodeRegion(mRect, mOptions);
        canvas.drawBitmap(bm, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        //默认直接显示图片的中心区域，可以自己去调节
//        mRect.left = imageWidth / 2 - width / 2;
//        mRect.top = imageHeight / 2 - height / 2;
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;
    }
}
