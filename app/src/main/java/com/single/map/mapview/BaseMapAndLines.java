package com.single.map.mapview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 地图 地图
 * Created by Administrator on 2016/6/30.
 * @author li
 */
public class BaseMapAndLines extends ImageView {

    // 线 坐标
    public ArrayList<MapPointWithTitleView> mapLineCoords;

    public List<List<MapPointWithTitleView>> allMapLines;

    private Context mContext;
    private MapView mapView;

    private Paint mPaint;
    private int lineColor = Color.RED;
    private float lineWidth = 5;
    private float pointRadius = 10;
    private float firstScale;

    public BaseMapAndLines(Context context) {
        this(context,null);
        this.mContext = context;
    }

    public BaseMapAndLines(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        mapLineCoords = new ArrayList<>();
        allMapLines = new ArrayList<>();

        mPaint = new Paint();
        mPaint.setColor(lineColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(lineWidth);
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public ArrayList<MapPointWithTitleView> getMapLineCoords() {
        return mapLineCoords;
    }

    /**
     * 移除某条线上的节点
     * @param mapLineCoord
     */
    public void removeMainLineCoord(MapPointWithTitleView mapLineCoord){
        if(mapLineCoords.contains(mapLineCoord)){
            mapLineCoords.remove(mapLineCoord);
            this.invalidate();
        }
    }


    public int getMapLineSize() {
        return mapLineCoords == null? 0:mapLineCoords.size();
    }

    public void clearMapLine() {
        mapLineCoords.clear();
        this.invalidate();
    }

    public MapPointWithTitleView getLine(int index) {
        return mapLineCoords.get(index);
    }

    public void addLine(MapPointWithTitleView mapLineCoord) {
        mapLineCoords.add(mapLineCoord);
        this.invalidate();
    }

    public List<List<MapPointWithTitleView>> getAllMapLines() {
        return allMapLines;
    }

    public void addLines(List<MapPointWithTitleView> mapLineCoords) {
        this.allMapLines.add(mapLineCoords);
        this.invalidate();
    }

    public int getAllMapLineSize(){
        return allMapLines.size();
    }

    public List<MapPointWithTitleView> getMapLine(int i){
        return allMapLines.get(i);
    }

    public void clearAllMapLines(){
        allMapLines.clear();
        this.invalidate();
    }

    public void addLines(List<MapPointWithTitleView> mapLineCoords,boolean isRepaint) {
        if (isRepaint) {
            addLines(mapLineCoords);
        }else {
            this.allMapLines.add(mapLineCoords);
        }

    }
        @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

            if(mapView !=  null){
                firstScale = mapView.getFirstScale();
                Log.i("BaseMapAndLines","--firstScale--->" + firstScale);
            }

            for (int i = 0; i < mapLineCoords.size(); i++) {
                if(i == 0){
                    canvas.drawCircle(mapLineCoords.get(0).getX() + 40,
                            mapLineCoords.get(0).getY() + 45,
                            10, mPaint);
                }

                //不为1的时候再画结束的点
                if (mapLineCoords.size() != 1) {
                    canvas.drawCircle(mapLineCoords.get(mapLineCoords.size() - 1).getX() + 40,
                            mapLineCoords.get(mapLineCoords.size() - 1).getY() + 45,
                            10, mPaint);
                }

                if(i > 0) {
                    //画线
                    canvas.drawLine(mapLineCoords.get(i - 1).getX() + 40,
                            mapLineCoords.get(i - 1).getY() + 45,
                            mapLineCoords.get(i).getX() + 40,
                            mapLineCoords.get(i).getY() + 45 , mPaint);

                }
            }

            //画原有的线
            for (int i = 0; i < allMapLines.size(); i++) {
                List<MapPointWithTitleView> mapLineCoords = allMapLines.get(i);
                if(mapLineCoords != null && mapLineCoords.size() > 0){

                    for (int k = 0; k < mapLineCoords.size(); k++) {
                        if(k == 0){
                            canvas.drawCircle(mapLineCoords.get(0).getX() + 40,
                                    mapLineCoords.get(0).getY() + 45,
                                    10, mPaint);
                        }

                        //不为1的时候再画结束的点
                        if (mapLineCoords.size() != 1) {
                            canvas.drawCircle(mapLineCoords.get(mapLineCoords.size() - 1).getX() + 40,
                                    mapLineCoords.get(mapLineCoords.size() - 1).getY() + 45,
                                    10, mPaint);
                        }

                        if (k > 0) {
                            //画线
                            canvas.drawLine(mapLineCoords.get(k - 1).getX() + 40,
                                    mapLineCoords.get(k - 1).getY() + 45,
                                    mapLineCoords.get(k).getX() + 40,
                                    mapLineCoords.get(k).getY() + 45 , mPaint);

                        }
                    }
                }

            }
    }

    /**
     * 地图 线 拐点 坐标
     */
    public class MapLineCoord {
        private float firstX;
        private float firstY;

        private float viewX;
        private float viewY;

        private int index;

        public MapLineCoord() {
        }

        public MapLineCoord(float firstX, float firstY, float viewX, float viewY) {
            this.firstX = firstX;
            this.firstY = firstY;
            this.viewX = viewX;
            this.viewY = viewY;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public float getFirstX() {
            return firstX;
        }

        public void setFirstX(float firstX) {
            this.firstX = firstX;
        }

        public float getFirstY() {
            return firstY;
        }

        public void setFirstY(float firstY) {
            this.firstY = firstY;
        }

        public float getViewX() {
            return viewX;
        }

        public void setViewX(float viewX) {
            this.viewX = viewX;
        }

        public float getViewY() {
            return viewY;
        }

        public void setViewY(float viewY) {
            this.viewY = viewY;
        }

        @Override
        public String toString() {
            return "MapLineCoord{" +
                    "firstX=" + firstX +
                    ", firstY=" + firstY +
                    ", viewX=" + viewX +
                    ", viewY=" + viewY +
                    '}';
        }
    }
}
