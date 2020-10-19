package com.example.wuhanbus.bean;

import java.util.List;

public class BusLineInfo {

    private String resultCode;
    private String resultDes;
    private DataBean data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDes() {
        return resultDes;
    }

    public void setResultDes(String resultDes) {
        this.resultDes = resultDes;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<LinesBean> lines;

        public List<LinesBean> getLines() {
            return lines;
        }

        public void setLines(List<LinesBean> lines) {
            this.lines = lines;
        }
    }

        public static class LinesBean {
            private String lineName;
            private String tips;
            private String lineId;
            private String lineNo;
            private String direction;
            private String startStopName;
            private String endStopName;
            private String firstTime;
            private String lastTime;
            private String intervalTime;
            private String price;
            private int stopsNum;

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public String getLineId() {
                return lineId;
            }

            public void setLineId(String lineId) {
                this.lineId = lineId;
            }

            public String getLineNo() {
                return lineNo;
            }

            public void setLineNo(String lineNo) {
                this.lineNo = lineNo;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getStartStopName() {
                return startStopName;
            }

            public void setStartStopName(String startStopName) {
                this.startStopName = startStopName;
            }

            public String getEndStopName() {
                return endStopName;
            }

            public void setEndStopName(String endStopName) {
                this.endStopName = endStopName;
            }

            public String getFirstTime() {
                return firstTime;
            }

            public void setFirstTime(String firstTime) {
                this.firstTime = firstTime;
            }

            public String getLastTime() {
                return lastTime;
            }

            public void setLastTime(String lastTime) {
                this.lastTime = lastTime;
            }

            public String getIntervalTime() {
                return intervalTime;
            }

            public void setIntervalTime(String intervalTime) {
                this.intervalTime = intervalTime;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getStopsNum() {
                return stopsNum;
            }

            public void setStopsNum(int stopsNum) {
                this.stopsNum = stopsNum;
            }
        }

    @Override
    public String toString() {
        return "BusLineInfo{" +
                "resultCode='" + resultCode + '\'' +
                ", resultDes='" + resultDes + '\'' +
                ", data=" + data +
                '}';
    }
}
