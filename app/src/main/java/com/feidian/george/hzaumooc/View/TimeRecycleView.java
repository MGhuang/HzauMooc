package com.feidian.george.hzaumooc.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.feidian.george.hzaumooc.R;


/**
 * Created by Administrator on 2016/5/17.
 */
public class TimeRecycleView extends RecyclerView{
    //=============================================================元素定义
    private Bitmap mIcon;//末尾图标
    //line location
    private int lineMarginSide;//时间轴偏移值（靠左）
    private int lineDynamicDimen;//时间轴动态调整值
    //line property
    private int lineStrokeWidth;//线宽
    private int lineColor;//线颜色
    //point property
    private int pointSize;//点的大小
    private int pointColor;//点的颜色
    private Paint linePaint;//线笔
    private Paint pointPaint;//点笔
    //=============================================================辅助参数
    //第一个点的位置
    private int firstX;
    private int firstY;
    //最后一个图的位置
    private int lastX;
    private int lastY;
    private Context mContext;

    //开关
    private boolean drawLine = true;

    public TimeRecycleView(Context context) {
        this(context, null);
    }

    public TimeRecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.UnderLineLinearLayout);//从xml获得自定义的属性数组
        lineMarginSide = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_margin_side, 10);
        lineDynamicDimen = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_dynamic_dimen, 0);
        lineStrokeWidth = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_stroke_width, 2);
        lineColor = attr.getColor(R.styleable.UnderLineLinearLayout_line_color, 0xbfffffff);//0xff3dd1a5);
        pointSize = attr.getDimensionPixelSize(R.styleable.UnderLineLinearLayout_point_size, 8);
        pointColor = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_point_color, 0xffffffff);//0xff3dd1a5);


        attr.recycle();//用于及时回收资源，方便下次再用，释放内存
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;

        linePaint = new Paint();
        linePaint.setAntiAlias(true);//防止边缘的锯齿
        linePaint.setDither(true);//设置防抖动
        linePaint.setColor(lineColor);//设置画笔颜色
        linePaint.setStrokeWidth(lineStrokeWidth);//设置画笔宽度
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔风格

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setDither(true);
        pointPaint.setColor(pointColor);
        pointPaint.setStyle(Paint.Style.FILL);
    }//初始化画笔和Context

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawLine) {
            drawTimeLine(canvas);
        }
    }

    private void drawTimeLine(Canvas canvas) {
        int childCount = getChildCount();//获得子界面个数

        if (childCount > 0) {
            //大于1，证明至少有2个，也就是第一个和第二个之间连成线，第一个和最后一个分别有点/icon
            if (childCount > 1) {
                drawFirstChildViewVertical(canvas);
                drawLastChildViewVertical(canvas);
                drawBetweenLineVertical(canvas);
            }
            else if (childCount == 1) {
                drawFirstChildViewVertical(canvas);
            }
        }
    }

    private void drawFirstChildViewVertical(Canvas canvas) {
        if (getChildAt(0) != null) {//第一个子视图不为空
            int top = getChildAt(0).getTop();
            //记录值
            firstX = lineMarginSide;
            firstY = top + getChildAt(0).getPaddingTop() + lineDynamicDimen;
            //画一个圆
            canvas.drawCircle(firstX, firstY+100, pointSize, pointPaint);//画第一个圈
        }
    }

    private void drawLastChildViewVertical(Canvas canvas) {
        if (getChildAt(getChildCount() - 1) != null) {
            int top = getChildAt(getChildCount() - 1).getTop();
            lastX=lineMarginSide;
            lastY=top+getChildAt(getChildCount() - 1).getPaddingTop()+ lineDynamicDimen;
            //记录值
            /*lastX = lineMarginSide - (mIcon.getWidth() >> 1);
            lastY = top + getChildAt(getChildCount() - 1).getPaddingTop() + lineDynamicDimen;*/
            //画一个图
            canvas.drawCircle(lastX, lastY+80, pointSize, pointPaint);//画第一个圈
            //canvas.drawBitmap(mIcon, lastX, lastY, null);
        }
    }

    private void drawBetweenLineVertical(Canvas canvas) {
        for (int i = 0; i < getChildCount() - 1; i++) {
            //画剩下的
            canvas.drawLine(lineMarginSide, firstY+100, lineMarginSide, lastY+100, linePaint);
            //画了线，就画圆
            if (getChildAt(i) != null && i != 0) {
                int top = getChildAt(i).getTop();
                //记录值
                int Y = top + getChildAt(i).getPaddingTop() + lineDynamicDimen;
                canvas.drawCircle(lineMarginSide, Y+100, pointSize, pointPaint);
            }
        }
    }
}
