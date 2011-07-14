package org.blanco.sportstablet.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingSurface extends View {

	public DrawingSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
		
        mPaint = new Paint((Paint.ANTI_ALIAS_FLAG));
	}
   
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private final Rect mRect = new Rect();
    private final Paint mPaint;
    private float mCurX;
    private float mCurY;
    private float mWidth = 10.0f; //default
    

    public void clear() {
        if (mCanvas != null) {
            //Transparent Bitmap again.
        	Bitmap newBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(),
                    Bitmap.Config.ALPHA_8);
					Canvas newCanvas = new Canvas();
					newCanvas.setBitmap(newBitmap);
					if (mBitmap != null) {
					newCanvas.drawBitmap(newBitmap, 0, 0, null);
					}
					mBitmap = newBitmap;
					mCanvas = newCanvas;
        	invalidate();
        }
    }

    @Override protected void onSizeChanged(int w, int h, int oldw,
            int oldh) {
        int curW = mBitmap != null ? mBitmap.getWidth() : 0;
        int curH = mBitmap != null ? mBitmap.getHeight() : 0;
        if (curW >= w && curH >= h) {
            return;
        }

        if (curW < w) curW = w;
        if (curH < h) curH = h;

        Bitmap newBitmap = Bitmap.createBitmap(curW, curH,
                                               Bitmap.Config.ALPHA_8);
        Canvas newCanvas = new Canvas();
        newCanvas.setBitmap(newBitmap);
        if (mBitmap != null) {
            newCanvas.drawBitmap(mBitmap, 0, 0, null);
        }
        mBitmap = newBitmap;
        mCanvas = newCanvas;
    }

    @Override protected void onDraw(Canvas canvas) {
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

   @Override public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        if (action != MotionEvent.ACTION_UP && action != MotionEvent.ACTION_CANCEL) {
            int N = event.getHistorySize();
            int P = event.getPointerCount();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < P; j++) {
                    mCurX = event.getHistoricalX(j, i);
                    mCurY = event.getHistoricalY(j, i);
                    drawPoint(mCurX, mCurY, mWidth);
                }
            }
            for (int j = 0; j < P; j++) {
                mCurX = event.getX(j);
                mCurY = event.getY(j);
                drawPoint(mCurX, mCurY, mWidth);
            }
        }
        return true;
    }

    private void drawPoint(float x, float y, float width) {
        if (width < 1) width = 1;
        if (mBitmap != null) {
            float radius = width / 2;
            mCanvas.drawCircle(x, y, radius,mPaint);
            mRect.set((int) (x - radius - 2), (int) (y - radius - 2),
                    (int) (x + radius + 2), (int) (y + radius + 2));
            invalidate(mRect);
        }
    }
    
    public void reloadPaintWidth(float width){
    	mWidth = width;
    }
}