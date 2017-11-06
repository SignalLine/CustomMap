package com.single.map.mapview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author li
 *         Create on 2017/11/3.
 * @Description
 *      点的位置model
 */

public class PointPosition implements Parcelable{

    private int id;
    private String position;
    private String title;
    private String deviceName;
    private String dwlevel;
    private int pid;
    private String py;
    private String dwindex;
    private String fullname;

    private double pointX;
    private double pointY;
    private int state;
    private boolean isTitleShow;

    private boolean isXunJD;//是否是巡检点
    private InspectNode mDeviceNode;//该巡检点的对象信息
    private PathPosition mPathPosition;//该巡检路径的对象信息

    public PointPosition() {
    }

    public PointPosition(String position, String title, String deviceName) {
        this.position = position;
        this.title = title;
        this.deviceName = deviceName;
    }

    public PointPosition(String position, String title,String inspectNodeName ,boolean isXunJD,Object object) {
        this.position = position;
        this.title = title;
        this.deviceName = inspectNodeName;
        this.isXunJD = isXunJD;

        if(isXunJD){
            mDeviceNode = (InspectNode) object;
        }else {
            mPathPosition = (PathPosition) object;
        }
    }



    public boolean isXunJD() {
        return isXunJD;
    }

    public void setXunJD(boolean xunJD) {
        isXunJD = xunJD;
    }

    public InspectNode getDeviceNode() {
        return mDeviceNode;
    }

    public void setDeviceNode(InspectNode deviceNode) {
        mDeviceNode = deviceNode;
    }

    public PathPosition getPathPosition() {
        return mPathPosition;
    }

    public void setPathPosition(PathPosition pathPosition) {
        mPathPosition = pathPosition;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isTitleShow() {
        return isTitleShow;
    }

    public void setTitleShow(boolean titleShow) {
        isTitleShow = titleShow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDwlevel() {
        return dwlevel;
    }

    public void setDwlevel(String dwlevel) {
        this.dwlevel = dwlevel;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getDwindex() {
        return dwindex;
    }

    public void setDwindex(String dwindex) {
        this.dwindex = dwindex;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "DevicePosition{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", title='" + title + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", dwlevel='" + dwlevel + '\'' +
                ", pid=" + pid +
                ", py='" + py + '\'' +
                ", dwindex='" + dwindex + '\'' +
                ", fullname='" + fullname + '\'' +
                ", pointX=" + pointX +
                ", pointY=" + pointY +
                ", state=" + state +
                ", isTitleShow=" + isTitleShow +
                ", isXunJD=" + isXunJD +
                ", mDeviceNode=" + mDeviceNode +
                ", mPathPosition=" + mPathPosition +
                '}';
    }

    protected PointPosition(Parcel in) {
        id = in.readInt();
        position = in.readString();
        title = in.readString();
        deviceName = in.readString();
        dwlevel = in.readString();
        pid = in.readInt();
        py = in.readString();
        dwindex = in.readString();
        fullname = in.readString();
        pointX = in.readDouble();
        pointY = in.readDouble();
        state = in.readInt();
        isTitleShow = in.readByte() != 0;
        isXunJD = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(position);
        dest.writeString(title);
        dest.writeString(deviceName);
        dest.writeString(dwlevel);
        dest.writeInt(pid);
        dest.writeString(py);
        dest.writeString(dwindex);
        dest.writeString(fullname);
        dest.writeDouble(pointX);
        dest.writeDouble(pointY);
        dest.writeInt(state);
        dest.writeByte((byte) (isTitleShow ? 1 : 0));
        dest.writeByte((byte) (isXunJD ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<PointPosition> CREATOR = new Parcelable.Creator<PointPosition>() {
        @Override
        public PointPosition createFromParcel(Parcel in) {
            return new PointPosition(in);
        }

        @Override
        public PointPosition[] newArray(int size) {
            return new PointPosition[size];
        }
    };


}
