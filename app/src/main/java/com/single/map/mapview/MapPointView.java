package com.single.map.mapview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义 地图 坐标 点
 * Created by Administrator on 2016/6/30.
 */
public class MapPointView extends ImageView implements View.OnClickListener, View.OnLongClickListener {
    public static final int RED_POINT = 0;
    public static final int BLUE_POINT = 1;
    public static final int GRAY_POINT = 2;
    public static final int BLACK_POINT = 3;
    public static final int PEOPLE_POINT = 4;
    public static final int DEFAULT_POINT = 5;

    private Context context;
    private double firstX;
    private double firstY;
    private int state;
    private String title;

    public MapPointView(Context context) {
        super(context);
        init();
    }

    public MapPointView(Context context, double firstX, double firstY, int state, String title) {
        super(context);
        this.context = context;
        this.firstX = firstX;
        this.firstY = firstY;
        this.state = state;
        this.title = title;
        init();
    }



    public void init() {
        // 默认 点显示 在 左上角的位置
        String pointPictureName;
        switch (this.state) {
            case RED_POINT:
                pointPictureName = "PointIcon_red.png";
                break;
            case BLUE_POINT:
                pointPictureName = "PointIcon_blue.png";
                break;
            case GRAY_POINT:
                pointPictureName = "PointIcon_gray.png";
                break;
            case BLACK_POINT:
                pointPictureName = "PointIcon_black.png";
                break;
            case PEOPLE_POINT:
                pointPictureName = "pointIcon_people.png";
                break;
            case DEFAULT_POINT:
            default:
                pointPictureName = "PointIcon_default.png";
                break;
        }
        InputStream open = null;
        try {
            open = getResources().getAssets().open(pointPictureName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(open);
        this.setImageBitmap(bitmap);
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
    }

    public double getFirstX() {
        return firstX;
    }

    public double getFirstY() {
        return firstY;
    }

    public float getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, this.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(context, "长按", Toast.LENGTH_SHORT).show();
        return true;
    }
}
