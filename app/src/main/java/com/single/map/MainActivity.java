package com.single.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.single.map.mapview.MapPointWithTitleView;
import com.single.map.mapview.MapView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements MapView.MapViewOnLongClickListener {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.mapView);
        InputStream open = null;
        try {
            open = getResources().getAssets().open("inspect.png");
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            mapView.setMapState(bitmap, bitmap.getWidth(), bitmap.getHeight());
            Log.i("MainActivity", bitmap.getWidth() + "=========" + bitmap.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int x = 20;
        int y = 20;
        for (int j = 0; j < 4; j++) {
            x += 66;
            y += 66;
            MapPointWithTitleView mapPointWithTitleView =
                    new MapPointWithTitleView(this, x, y, MapPointWithTitleView.RED_POINT, true, "点标题");
            mapView.addMapPoint(mapPointWithTitleView);
        }


        mapView.setMapOnLongClickListener(this);
    }

    int i = 0;

    @Override
    public void mapViewOnLongClick(float downX, float downY) {
        MapPointWithTitleView myMapPointWithTitleView =
                new MapPointWithTitleView(this, downX, downY, MapPointWithTitleView.RED_POINT, true, "" + (i++));
        mapView.addMapPoint(myMapPointWithTitleView);
        Toast.makeText(this, "" + downX + "-" + downY, Toast.LENGTH_SHORT).show();
    }

    float x = 400;
    float y = 400;

    public void addLine(View view) {
        x += 50;
        y -= 50;
        mapView.addMapPoint(new MapPointWithTitleView(this,x,y,MapPointWithTitleView.RED_POINT,true,"" + (i++)));
    }

    public void addOtherLine(View view) {


        Random random = new Random();
//
        List<MapPointWithTitleView> views = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            int x = 400 - random.nextInt(100);
            int y = random.nextInt(100) + 50;
            views.add(new MapPointWithTitleView(this,x,y,MapPointWithTitleView.BLACK_POINT,true,"" + (i++)));
        }

        mapView.addMapLines(views);

    }
}
