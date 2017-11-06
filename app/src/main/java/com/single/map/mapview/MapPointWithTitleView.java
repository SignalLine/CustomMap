package com.single.map.mapview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.single.map.R;

/**
 * 自定义 地图 坐标 点
 * Created by Administrator on 2016/6/30.
 *  @author li
 */
public class MapPointWithTitleView extends LinearLayout
        implements View.OnClickListener, View.OnLongClickListener, PointClickPopupWindow.OnTextClickListener {

    private static String TAG = MapPointWithTitleView.class.getSimpleName();

    public static final int RED_POINT = 0;
    public static final int BLUE_POINT = 1;
    public static final int GRAY_POINT = 2;
    public static final int BLACK_POINT = 3;
    public static final int PEOPLE_POINT = 4;
    public static final int DEFAULT_POINT = 5;
    public static final int GREEN_POINT = 6;
    public static final int YELLOW_POINT = 7;

    /**
     * 点的底图
     */
    private ImageView textPointIcon;
    private TextView textPointName;
    /**
     * 点的右上角tag
     */
    private TextView textPointTag;
    /**
     *  根布局
     */
    private RelativeLayout pointLayout;

    private Context context;
    private AppCompatActivity mActivity;
    /**
     * 初始 位置
     */
    private double firstX;
    private double firstY;

    /**
     * 点的 边界
     */
    private double borderTop;
    private double borderLeft;
    /**
     * 点的中心点位置
     */
    private double pointCenterX;
    private double pointCenterY;
    /** 
     * 点 的 显示 状态
     */
    private int pointState;
    /**
     * 点 的 名称
     */
    private String pointName;
    /**
     * 是否 显示 点 名称
     */
    private boolean isNameShow;
    private View mView;
    /**
     * 顺序 在集合中的顺序
     */
    private int pointIndex;
    /**
     * 构造方法传递过来的数据参数
     */
    private PointPosition mPointPosition;

    public MapPointWithTitleView(Context context) {
        super(context);
        init();
    }

    public MapPointWithTitleView(Context context, AppCompatActivity activity) {
        super(context);
        this.context = context;
        this.mActivity = activity;
        init();
    }

    public MapPointWithTitleView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        this.context = context;
        init();
    }

    public MapPointWithTitleView(Context context, double pointX, double pointY,
                                 int pointState, boolean isNameShow, String pointName) {
        super(context);
        this.context = context;
        this.firstX = pointX;
        this.firstY = pointY;
        this.pointState = pointState;
        this.pointName = pointName;
        this.isNameShow = isNameShow;
        init();
    }

    /**
     * 传递对象参数进来
     *
     * @param context
     * @param activity
     * @param pointX
     * @param pointY
     * @param pointState
     * @param isNameShow
     * @param pointPosition
     */
    public MapPointWithTitleView(Context context, AppCompatActivity activity,
                                   double pointX, double pointY, int pointState,
                                   boolean isNameShow, PointPosition pointPosition){
        super(context);
        this.context = context;
        this.mActivity = activity;
        this.firstX = pointX;
        this.firstY = pointY;
        this.pointState = pointState;
        this.isNameShow = isNameShow;
        this.mPointPosition = pointPosition;
        init();
    }

    public void setFirstXShow(float x) {
        //改为中心点
        x -= pointCenterX;
        setX(x);
    }

    public void setFirstYShow(float y) {
        //改为中心点
        y -= pointCenterY;
        setY(y);
    }


    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_map_point, this);
        textPointIcon = (ImageView) view.findViewById(R.id.pointIcon);
        textPointName = (TextView) view.findViewById(R.id.pointName);
        textPointTag = (TextView) view.findViewById(R.id.pointTag);
        pointLayout = (RelativeLayout) view.findViewById(R.id.map_point_relative);
        // 设置 显示 内容
        setPointIcon();
        textPointName.setText(pointName);

        // 设置 显示 内容
        setPointIcon();
        //传递title过来
        if(!TextUtils.isEmpty(pointName)){
            textPointName.setText(pointName);
            //传递参数对象过来
        }else if(mPointPosition != null){
            this.pointName = mPointPosition.getTitle();
            textPointName.setText(mPointPosition.getTitle());
            //int型
            String tag = "p" + mPointPosition.getPosition();
            textPointTag.setText(tag);
        }

        // 测量 边界
        measureBorder();

        // 设置 监听
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
    }

    public void setLayoutVisible(boolean isVisible){
        if(!isVisible){
            pointLayout.setVisibility(GONE);
            mView.setClickable(false);
        }
    }

    /**
     * 返回指定的pos
     * @return pos
     */
    public String getPosition(){
        if (mPointPosition != null) {
            return mPointPosition.getPosition();
        }

        return "";
    }

    public int getPointIndex() {
        return pointIndex;
    }

    public void setPointIndex(int pointIndex) {
        this.pointIndex = pointIndex;
    }

    /**
     * 测量 地图点 的 边界
     */
    private void measureBorder() {
        int height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        this.textPointIcon.measure(width, height);
        this.textPointName.measure(width, height);
        this.measure(width, height);

        if (!isNameShow) {
            textPointName.setVisibility(INVISIBLE);
        } else {
            textPointName.setVisibility(VISIBLE);

            if(!TextUtils.isEmpty(pointName)){
                textPointName.setText(pointName);
                //传递参数对象过来
            }else if(mPointPosition != null){
                this.pointName = mPointPosition.getTitle();
                textPointName.setText(mPointPosition.getTitle());
                //int型
                String tag = "p" + mPointPosition.getPosition();
                textPointTag.setText(tag);
            }
        }

        //图形的左上角坐标点------相对于底图的位置
        borderLeft = (this.getMeasuredWidth() - this.textPointIcon.getMeasuredWidth()) / 2;
        borderTop = (this.getMeasuredHeight() - this.textPointIcon.getMeasuredHeight()
                            - this.textPointName.getMeasuredHeight());
        //测量title的宽度，居中点

        this.pointCenterX = this.getMeasuredWidth()/2;
        this.pointCenterY = this.getMeasuredHeight()/2;
        Log.i(TAG, this.pointCenterX + "==x==point==y===" + this.pointCenterY);
    }

    /**
     * 设置 显示 图标
     */
    private void setPointIcon() {
        // 默认 点显示 在 左上角的位置
        int pointPictureName;
        switch (this.pointState) {
            case RED_POINT:
                pointPictureName = R.mipmap.point_icon_red;
                break;
            case BLUE_POINT:
                pointPictureName = R.mipmap.point_icon_blue;
                break;
            case GRAY_POINT:
                pointPictureName = R.mipmap.point_icon_gray;
                break;
            case BLACK_POINT:
                pointPictureName = R.mipmap.point_icon_black;
                break;
            case PEOPLE_POINT:
                pointPictureName = R.mipmap.point_icon_people;
                break;
            case GREEN_POINT:
                pointPictureName = R.mipmap.point_icon_green;
                break;
            case YELLOW_POINT:
                pointPictureName = R.mipmap.point_icon_yellow;
                break;
            case DEFAULT_POINT:
            default:
                pointPictureName = R.mipmap.point_icon_blue;
                break;
        }

        this.textPointIcon.setImageResource(pointPictureName);
    }

    public void setPointIcon(int pointIcon){
        this.textPointIcon.setImageResource(pointIcon);
        this.invalidate();
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG,"downX--->>" + firstX + "downY--->>" + firstY);
        if (mOnPointClickListener != null) {
            mOnPointClickListener.onPointClick(v, mPointPosition.getPosition(),
                    mPointPosition,firstX,firstY,pointState);
        }
    }

    @Override
    public boolean onLongClick(View v) {

        String skipTag=PointClickPopupWindow.SKIP,markTag=PointClickPopupWindow.MARK;
        Log.i(TAG,"长按时间");

        new PointClickPopupWindow(context).showPop(v,mPointPosition,skipTag,markTag).setOnTextClickListener(this);


        return true;
    }

    /**
     * 跳过  标记--点击事件
     *
     * @param view
     * @param devicePosition
     * @param skipTag
     * @param skipPopupWindow
     */
    @Override
    public void onSkipClick(View view, PointPosition devicePosition, String skipTag, PointClickPopupWindow skipPopupWindow) {
        
        Log.i(TAG,"------>是否跳检");
        Toast.makeText(context, "是否设置标识", Toast.LENGTH_SHORT).show();
         
        skipPopupWindow.dismiss();
    }

    /**
     * 设置巡检点的标记 颜色
     *
     * @param devicePosition
     * @param deltag
     * @param color
     */
    private void setDeviceNodeDeltag(PointPosition devicePosition,int deltag,int color){
        Log.i(TAG,"设置标识");
        Toast.makeText(context, "设置标识", Toast.LENGTH_SHORT).show();
    }

    public interface OnPointClickListener{
        /**
         * 点击回调
         *
         * @param view
         * @param position
         * @param devicePosition
         * @param x
         * @param y
         * @param state
         */
        void onPointClick(View view,String position,PointPosition devicePosition,double x,double y,int state);
    }

    private OnPointClickListener mOnPointClickListener;

    public void setOnPointClickListener(OnPointClickListener onPointClickListener){
        this.mOnPointClickListener = onPointClickListener;
    }

    public PointPosition getPointPosition() {
        return mPointPosition;
    }

    public void setPointPosition(PointPosition pointPosition) {
        mPointPosition = pointPosition;
    }

    public double getFirstY() {
        return firstY;
    }

    public void setFirstY(double firstY) {
        this.firstY = firstY;
    }

    public double getFirstX() {
        return firstX;
    }

    public void setFirstX(double firstX) {
        this.firstX = firstX;
    }

    public int getPointState() {
        return pointState;
    }

    public void setPointState(int pointState) {
        this.pointState = pointState;
        this.invalidate();
    }

    public double getPointCenterX() {
        return pointCenterX;
    }

    public void setPointCenterX(double pointCenterX) {
        this.pointCenterX = pointCenterX;
    }

    public double getPointCenterY() {
        return pointCenterY;
    }

    public void setPointCenterY(double pointCenterY) {
        this.pointCenterY = pointCenterY;
    }
}
