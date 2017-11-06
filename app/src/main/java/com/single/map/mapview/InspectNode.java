package com.single.map.mapview;

import java.io.Serializable;

/**
 * @author li
 *         Create on 2017/11/3.
 * @Description
 */

public class InspectNode implements Serializable{

    private int _id = 1;
    private int id;

    private int pathId;
    private int weizhiId;//位置表的id
    private int positionX;
    private int positionY;
    private float gpsX;
    private float gpsY;
    private String nfcCode;//nfc内容

    private int pid;//父级节点


    private String typeString;
    private int nodeType;//绑定巡检点的类型 1保养 2维修  3巡检

    private int nodeOrder;

    //0 未巡检   1 巡检    2 跳检   3标记
    private int deltag;
    private String syntex;//备注信息
    private int isMark = 0;//0不标记   1标记
    private int isSkip = 0;//0不跳检     1跳检


    private int nodeId;
    private String updateTime;
    private int groupId;


    public InspectNode() {
    }

    public InspectNode(int id, int pathId, int deltag, int positionX, int positionY) {
        this.id = id;
        this.pathId = pathId;
        this.deltag = deltag;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public InspectNode( int pathId, int deltag, String syntex) {
        this.pathId = pathId;
        this.deltag = deltag;
        this.syntex = syntex;
    }

    public InspectNode(int id,int pathId, int weizhiId, int positionX, int positionY, float gpsX, float gpsY, int nodeOrder, int pid, String nfcCode,int deltag, String syntex) {
        this.id = id;
        this.pathId = pathId;
        this.weizhiId = weizhiId;
        this.positionX = positionX;
        this.positionY = positionY;
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.nodeOrder = nodeOrder;
        this.pid = pid;
        this.nfcCode = nfcCode;
        this.deltag = deltag;
        this.syntex = syntex;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public int getIsMark() {
        return isMark;
    }

    public void setIsMark(int isMark) {
        this.isMark = isMark;
    }

    public int getIsSkip() {
        return isSkip;
    }

    public void setIsSkip(int isSkip) {
        this.isSkip = isSkip;
    }

    public String getNfcCode() {
        return nfcCode;
    }

    public void setNfcCode(String nfcCode) {
        this.nfcCode = nfcCode;
    }

    public int getWeizhiId() {
        return weizhiId;
    }

    public void setWeizhiId(int weizhiId) {
        this.weizhiId = weizhiId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    @Override
    public String toString() {
        return "InspectNode{" +
                "_id=" + _id +
                ", id=" + id +
                ", pathId=" + pathId +
                ", weizhiId=" + weizhiId +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", gpsX=" + gpsX +
                ", gpsY=" + gpsY +
                ", nfcCode='" + nfcCode + '\'' +
                ", pid=" + pid +
                ", typeString='" + typeString + '\'' +
                ", nodeType=" + nodeType +
                ", nodeOrder=" + nodeOrder +
                ", deltag=" + deltag +
                ", syntex='" + syntex + '\'' +
                ", isMark=" + isMark +
                ", isSkip=" + isSkip +
                ", nodeId=" + nodeId +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        InspectNode that = (InspectNode) o;

        if (_id != that._id) {return false;}
        if (id != that.id) {return false;}
        if (pathId != that.pathId) {return false;}
        if (weizhiId != that.weizhiId) {return false;}
        if (positionX != that.positionX) {return false;}
        if (pid != that.pid) {return false;}
        if (nodeId != that.nodeId) {return false;}
        return groupId == that.groupId;

    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + id;
        result = 31 * result + pathId;
        result = 31 * result + weizhiId;
        result = 31 * result + positionX;
        result = 31 * result + pid;
        result = 31 * result + nodeId;
        result = 31 * result + groupId;
        return result;
    }

}
