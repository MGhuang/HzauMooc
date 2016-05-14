package com.feidian.george.hzaumooc.Adapter.Main;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.feidian.george.hzaumooc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/11.
 */
class ViewHolderSlider extends RecyclerView.ViewHolder{
    @Bind(R.id.main_rv_slider)
    public SliderLayout sliderLayout;
    @Bind(R.id.main_rv_pager)
    public PagerIndicator indicator;
    public ViewHolderSlider(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        //自动适配高度
        Context context = itemView.getContext();
        ViewGroup.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.sliderLayout.getLayoutParams();
        if (layoutParams == null || layoutParams.width <= 0) {
            //可用高度小于100dp
            Point point = new Point();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
            int width = point.x;
            layoutParams = new FrameLayout.LayoutParams(width, (int) (((float) width) / 2.333333f));

        } else {
            layoutParams.height = (int) (((float) layoutParams.width) / 2.333333f);
        }
        sliderLayout.setLayoutParams(layoutParams);
        sliderLayout.setCustomIndicator(indicator);
    }
}
