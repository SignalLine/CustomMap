package com.single.map.mapview;

import java.io.Serializable;

/**
 * @author li
 *         Create on 2017/11/3.
 * @Description
 */

public class PathPosition implements Serializable {

    private int _id;
    private int id;
    private int pathId;
    private int weizhiId;
    private int nodeOrder;
    private int preNodeId;//前一个位置点的id

    private int positionX;
    private int positionY;
    private float gpsX;
    private float gpsY;
    private int deltag;
    private String syntex;

    private int positionType;
    private String typeString;


    private int pathPositionId;
    private String updateTime;


    public PathPosition( int pathId, int nodeOrder, int positionX, int positionY, int deltag) {
        this.pathId = pathId;
        this.nodeOrder = nodeOrder;
        this.positionX = positionX;
        this.positionY = positionY;
        this.deltag = deltag;
    }

    //---一定要加上默认的无参构造函数，要不然，创建表的时候会报错，没法插入数据----------------
    public PathPosition() {
    }

    public PathPosition(int id,int pathId, int weizhiId, int nodeOrder, int preNodeId, int positionX, int positionY, float gpsX, float gpsY, int deltag, String syntex) {
        this.id = id;
        this.pathId = pathId;
        this.weizhiId = weizhiId;
        this.nodeOrder = nodeOrder;
        this.preNodeId = preNodeId;
        this.positionX = positionX;
        this.positionY = positionY;
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.deltag = deltag;
        this.syntex = syntex;
    }

    public int getPathPositionId() {
        return pathPositionId;
    }

    public void setPathPositionId(int pathPositionId) {
        this.pathPositionId = pathPositionId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getPositionType() {
        return positionType;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public int getWeizhiId() {
        return weizhiId;
    }

    public void setWeizhiId(int weizhiId) {
        this.weizhiId = weizhiId;
    }

    public int getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(int nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public float getGpsX() {
        return gpsX;
    }

    public void setGpsX(float gpsX) {
        this.gpsX = gpsX;
    }

    public float getGpsY() {
        return gpsY;
    }

    public void setGpsY(float gpsY) {
        this.gpsY = gpsY;
    }

    public int getDeltag() {
        return deltag;
    }

    public void setDeltag(int deltag) {
        this.deltag = deltag;
    }

    public String getSyntex() {
        return syntex;
    }

    public void setSyntex(String syntex) {
        this.syntex = syntex;
    }

    public int getPreNodeId() {
        return preNodeId;
    }

    public void setPreNodeId(int preNodeId) {
        this.preNodeId = preNodeId;
    }

    @Override
    public String toString() {
        return "PathPosition{" +
                "_id=" + _id +
                ", id=" + id +
                ", pathId=" + pathId +
                ", weizhiId=" + weizhiId +
                ", nodeOrder=" + nodeOrder +
                ", preNodeId=" + preNodeId +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", gpsX=" + gpsX +
                ", gpsY=" + gpsY +
                ", deltag=" + deltag +
                ", syntex='" + syntex + '\'' +
                ", positionType=" + positionType +
                ", typeString='" + typeString + '\'' +
                ", pathPositionId=" + pathPositionId +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        PathPosition that = (PathPosition) o;

        if (_id != that._id) {return false;}
        if (id != that.id){ return false;}
        if (pathId != that.pathId){ return false;}
        if (weizhiId != that.weizhiId) {return false;}
        if (nodeOrder != that.nodeOrder){ return false;}
        if (preNodeId != that.preNodeId){ return false;}
        if (positionX != that.positionX){ return false;}
        return pathPositionId == that.pathPositionId;

    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + id;
        result = 31 * result + pathId;
        result = 31 * result + weizhiId;
        result = 31 * result + nodeOrder;
        result = 31 * result + preNodeId;
        result = 31 * result + positionX;
        result = 31 * result + pathPositionId;
        return result;
    }

}
