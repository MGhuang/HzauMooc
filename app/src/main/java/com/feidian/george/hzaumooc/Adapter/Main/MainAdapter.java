package com.feidian.george.hzaumooc.Adapter.Main;

/**
 * Created by Administrator on 2016/5/11.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.feidian.george.hzaumooc.Bmob.Bean.CloudClass;
import com.feidian.george.hzaumooc.Bmob.Bean.MainValue;
import com.feidian.george.hzaumooc.Bmob.Bean.PageViewResource;
import com.feidian.george.hzaumooc.Exception.OutBoundsException;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int ITEM_ACCOUT = 5;
    private static final int PAGEVIEW = 0 ; //  图片轮播组件
    private static final int GRIDVIEW = 1 ;
    private static final int LISTVIEW = 2 ;
    private static final int PAGE_POSITION = 0;
    public static final int RECOMMEND_POSITION = 1 ; //recommend 推荐课程
    public static final int PERFECTCLASS_POSITION =2 ; //精品课程
    public static final int CLOUDCLASS_POSITION = 3 ; //云课程
    public static final int HOT_POSITION = 4; //热门点击
    Activity activity;
    LayoutInflater layoutInflater;
    Map<String,ArrayList<?>> map;
    public MainAdapter(Activity activity, Map<String,ArrayList<?>> map)
    {
        this.activity=activity;
        layoutInflater=activity.getLayoutInflater();
        this.map=map;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if ( viewType == PAGEVIEW ) return new ViewHolderSlider( layoutInflater.inflate(R.layout.a_main_r_pageview , parent ,false) );
        if ( viewType == GRIDVIEW ) return new ViewHolderGird( layoutInflater.inflate(R.layout.a_main_r_gridview , parent ,false) );
        if ( viewType == LISTVIEW ) return new ViewHolderList( layoutInflater.inflate(R.layout.a_main_r_listview , parent ,false) );
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if( holder instanceof ViewHolderSlider && map.containsKey( Main_StaticValue.PAGE_NAME ))
        {
            viewHolderSliderOperate((ViewHolderSlider)holder);
        }
        else if( holder instanceof ViewHolderGird )
        {
            switch(position)
            {
                case RECOMMEND_POSITION:
                    if(map.containsKey(Main_StaticValue.RECOMMEND_NAME))
                    {
                        mainValueOperate((ViewHolderGird)holder, (ArrayList<MainValue>) map.get(Main_StaticValue.RECOMMEND_NAME),position);
                    }
                    break;
                case PERFECTCLASS_POSITION:
                    if(map.containsKey(Main_StaticValue.PERFECT_NAME))
                    {
                        mainValueOperate((ViewHolderGird)holder, (ArrayList<MainValue>) map.get(Main_StaticValue.PERFECT_NAME),position);
                    }
                    break;
                case CLOUDCLASS_POSITION:
                    if(map.containsKey(Main_StaticValue.CLOUD_NAME))
                    {
                        mainValueOperate((ViewHolderGird)holder, (ArrayList<MainValue>) map.get(Main_StaticValue.CLOUD_NAME),position);
                    }
                    break;
                default:
                    try
                    {
                        throw new OutBoundsException("MainAdapter 超过预定值"+"ViewHolderGird 选择超出");
                    }catch (OutBoundsException e)
                    {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        else if(holder instanceof ViewHolderList && map.containsKey(Main_StaticValue.HOT_NAME))
        {
            ListOperate((ViewHolderList)holder,(ArrayList<MainValue>)map.get(Main_StaticValue.HOT_NAME),position);

        }

    }

    @Override
    public int getItemCount() {
        if(map.size()!=ITEM_ACCOUT)
        {
            return 0;
        }
        return map.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ( position == PAGE_POSITION ) return PAGEVIEW; //轮播图片
        if ( position == RECOMMEND_POSITION ) return GRIDVIEW; //官方推荐
        if ( position == PERFECTCLASS_POSITION ) return GRIDVIEW; //精品课程
        if ( position == CLOUDCLASS_POSITION ) return GRIDVIEW; //云课堂
        if ( position == HOT_POSITION ) return LISTVIEW; //热门点击
        try
        {
            throw new OutBoundsException("MainAdapter 超过预定值");
        }catch (OutBoundsException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return super.getItemViewType(position);
    }
    private void viewHolderSliderOperate(ViewHolderSlider holder)
    {
        ArrayList<PageViewResource> list= (ArrayList<PageViewResource>) map.get(Main_StaticValue.PAGE_NAME);
         holder.sliderLayout.removeAllSliders();
         holder.sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
         holder.sliderLayout.startAutoCycle();
         holder.sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        BaseSliderView[] sliderView=new com.daimajia.slider.library.SliderTypes.DefaultSliderView[list.size()];
        for(int i=0;i<list.size();i++)
        {
            sliderView[i] = new com.daimajia.slider.library.SliderTypes.DefaultSliderView(activity);
            sliderView[i].image(list.get(i).getImageUrl()).setScaleType(BaseSliderView.ScaleType.CenterCrop);
             holder.sliderLayout.addSlider(sliderView[i]);
        }
    }
    private void mainValueOperate(ViewHolderGird holder, ArrayList<MainValue> list,int position)
    {
        holder.title.setText(list.get(0).getClass_kind());
        holder.image.setImageResource(Main_StaticValue.MAIN_TITLE_IMAGE[position-Main_StaticValue.DIFFERENCE]);
        holder.gridView.setAdapter(new GridAdapter(activity,list,position));
        holder.setListener(list,position,activity);
    }
    /*private void cloudClassOperate(ViewHolderGird holder, ArrayList<CloudClass> list,int position)
    {
        holder.title.setText("云课堂");
        holder.image.setImageResource(Main_StaticValue.MAIN_TITLE_IMAGE[position-Main_StaticValue.DIFFERENCE]);
        holder.gridView.setAdapter(new GridAdapter(activity,list,position));
    }*/
    private void ListOperate(ViewHolderList holder, ArrayList<MainValue> list,int position)
    {
        holder.title.setText("热门点击");
        holder.logo.setImageResource(Main_StaticValue.MAIN_TITLE_IMAGE[position-Main_StaticValue.DIFFERENCE]);
        holder.listView.setAdapter(new ListAdapter(activity,list));
        holder.setListener(list,activity);
    }
}

