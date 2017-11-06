package com.single.map.mapview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.single.map.R;

/**
 * @author li
 *         Create on 2017/11/3.
 * @Description
 *  地图点的 点击弹出提示
 */

public class PointClickPopupWindow implements  View.OnClickListener, View.OnTouchListener{

    public static String CANCEL_SKIP = "cancelSkip";
    public static String CANCEL_MARK = "cancelMark";
    public static String SKIP = "skip";
    public static String MARK = "mark";

    private String skipTag=SKIP,markTag=MARK;

    private Context mContext;
    private OnTextClickListener mOnTextClickListener;
    private PopupWindow mPopupWindow;
    private PointPosition mDevicePosition;
    private static final String TAG = PointClickPopupWindow.class.getSimpleName();




    public PointClickPopupWindow(Context context){
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_skip:
                if(mOnTextClickListener != null){
                    if(mDevicePosition != null){
                        mOnTextClickListener.onSkipClick(v,mDevicePosition,this.skipTag,this);
                    }else {
                        Log.i(TAG,"DevicePosition  =   null ~~~~~");
                    }

                }
                break;
//            case R.id.pop_mark:
//                if(mOnTextClick != null){
//                    if(mDevicePosition != null){
//                        mOnTextClick.onMarkClick(v,mDevicePosition,this.markTag,this);
//                    }else {
//                        LogUtils.i(TAG,"~~~~~~~ DevicePosition  ==   null ~~~~~");
//                    }
//
//                }
//                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(null != mPopupWindow && mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        return false;
    }

    public interface OnTextClickListener{
        /**
         * 跳检回调监听
         *
         * @param view
         * @param devicePosition
         * @param skipTag
         * @param skipPopupWindow
         */
        void onSkipClick(View view, PointPosition devicePosition,String skipTag,PointClickPopupWindow skipPopupWindow);

//        void onMarkClick(View view, DevicePosition devicePosition,String markTag,SkipPopupWindow skipPopupWindow);
    }

    public void setOnTextClickListener(OnTextClickListener onTextClickListener){
        mOnTextClickListener = onTextClickListener;
    }

    /**
     * 初始化控件  实现控件的点击事件
     *
     * @param v
     */
    private void init(View v,String skipTag,String markTag){
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.view_map_point_popup, null);
        TextView skipText = (TextView) convertView.findViewById(R.id.pop_skip);
//        TextView markText = (TextView) convertView.findViewById(R.id.pop_mark);
        skipText.setOnClickListener(this);
//        markText.setOnClickListener(this);

        if(skipTag.equals(SKIP)){
            skipText.setText("跳过");
        }else {
            skipText.setText("取消跳过");
            this.skipTag = CANCEL_SKIP;
        }

//        if(markTag.equals(MARK)){
//            markText.setText("标记");
//        }else {
//            markText.setText("取消标记");
//            this.markTag = CANCEL_MARK;
//        }

        mPopupWindow = new PopupWindow(convertView, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,true);
        convertView.setOnTouchListener(this);//触摸事件，在其他区域触摸屏幕   取消popupWindow

        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());//保证popupwindow响应返回按钮事件.

        int[] location = new int[2];
        v.getLocationOnScreen(location);

        mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY,location[0]-mPopupWindow.getWidth()-v.getWidth()/4, location[1]-mPopupWindow.getHeight()-v.getHeight()/2);//显示在顶部
//        mPopupWindow.showAsDropDown(v);
    }

    public PointClickPopupWindow showPop(View view, PointPosition devicePosition){
        if(null != mPopupWindow){
            mPopupWindow.dismiss();
        }else {
            this.mDevicePosition = devicePosition;
            init(view,MARK,SKIP);
        }

        return this;
    }

    public PointClickPopupWindow showPop(View view, PointPosition devicePosition,String skipTag,String markTag){
        if(null != mPopupWindow){
            mPopupWindow.dismiss();
        }else {
            this.mDevicePosition = devicePosition;
            init(view,skipTag,markTag);
        }

        return this;
    }

    public void dismiss(){
        if(null != mPopupWindow){
            mPopupWindow.dismiss();
        }
    }

    public boolean dismissPop(){
        boolean isShowing = false;
        if(null != mPopupWindow){
            isShowing = mPopupWindow.isShowing();
            mPopupWindow.dismiss();
        }
        return isShowing;
    }


}
