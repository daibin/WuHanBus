package com.example.wuhanbus.bean;

import java.util.List;

public class BusStopInfo {


    /**
     * resultCode : 1
     * resultDes :
     * data : {"lineName":"703","lineId":"027-703-0","lineNo":"703","direction":0,"startStopName":"汉口火车站","endStopName":"珞喻东路高坡店","firstTime":"5:30","lastTime":"23:00","intervalTime":"","price":"1.0~2.0","stopsNum":32,"measure":0,"beBus":"","line2Id":"027-703-1","stops":[{"stopId":"027-1569","stopName":"汉口火车站","stopOrder":1,"metro":""},{"stopId":"027-630","stopName":"后襄河北路","lng":114.254580158289,"lat":30.611619139562286,"stopOrder":2,"metro":""},{"stopId":"027-1296","stopName":"青年路市博物馆","lng":114.25728584840999,"lat":30.607083925575076,"stopOrder":3,"metro":""},{"stopId":"027-560","stopName":"青年路范湖","lng":114.26093215050082,"lat":30.605482623085837,"stopOrder":4,"metro":""},{"stopId":"027-683","stopName":"青年路机场河","lng":114.26308465718017,"lat":30.600089031997705,"stopOrder":5,"metro":""},{"stopId":"027-568","stopName":"青年路雪松路","lng":114.26321707427786,"lat":30.590775483682723,"stopOrder":6,"metro":""},{"stopId":"027-I-3300","stopName":"青年路地铁青年路站","lng":114.26476294130813,"lat":30.58521012426967,"stopOrder":7,"metro":""},{"stopId":"027-I-3287","stopName":"武胜路武胜西街","lng":114.2685925288944,"lat":30.57215077455038,"stopOrder":8,"metro":""},{"stopId":"027-91596","stopName":"鹦鹉大道地铁琴台站","lng":114.26631714076598,"lat":30.558470462555288,"stopOrder":9,"metro":""},{"stopId":"027-1140","stopName":"武珞路阅马场","lng":114.30558998409666,"lat":30.541130039734643,"stopOrder":10,"metro":""},{"stopId":"027-1435","stopName":"武珞路傅家坡客运站","lng":114.32732920082893,"lat":30.53601503269767,"stopOrder":11,"metro":""},{"stopId":"027-221","stopName":"武珞路丁字桥","stopOrder":12,"metro":""},{"stopId":"027-3313","stopName":"武珞路地铁宝通寺站","lng":114.34089771462524,"lat":30.53020945617543,"stopOrder":13,"metro":""},{"stopId":"027-18","stopName":"武珞路街道口","lng":114.34608460016322,"lat":30.528860846888417,"stopOrder":14,"metro":""},{"stopId":"027-710","stopName":"珞喻路广埠屯","lng":114.36232333935124,"lat":30.52367058662436,"stopOrder":15,"metro":""},{"stopId":"027-I-3350","stopName":"珞喻路卓刀泉中学","lng":114.37270626090243,"lat":30.518716142426243,"stopOrder":16,"metro":""},{"stopId":"027-2029","stopName":"珞喻路马家庄","lng":114.37826763631153,"lat":30.517274742554193,"stopOrder":17,"metro":""},{"stopId":"027-I-3256","stopName":"珞喻路科技会展中心","lng":114.3838910355411,"lat":30.515921969644914,"stopOrder":18,"metro":""},{"stopId":"027-1743","stopName":"珞喻路吴家湾","lng":114.39038050832441,"lat":30.511976372931475,"stopOrder":19,"metro":""},{"stopId":"027-2118","stopName":"珞喻路鲁巷","lng":114.3959353301365,"lat":30.50732862893219,"stopOrder":20,"metro":""},{"stopId":"027-648","stopName":"珞喻路光谷广场","lng":114.40057926222842,"lat":30.50562377905755,"stopOrder":21,"metro":""},{"stopId":"027-2002","stopName":"珞喻路湖北省中医院","lng":114.4075333539222,"lat":30.507012325785553,"stopOrder":22,"metro":""},{"stopId":"027-508","stopName":"珞喻路关山口","lng":114.41162777160059,"lat":30.507216913488463,"stopOrder":23,"metro":""},{"stopId":"4201001100403355139969024","stopName":"珞喻东路华中科技大学","lng":1830.5533,"lat":0,"stopOrder":24,"metro":""},{"stopId":"027-2772","stopName":"珞喻东路叶麻店","lng":114.41902340154655,"lat":30.506553002271446,"stopOrder":25,"metro":""},{"stopId":"4201001100403354372411392","stopName":"珞喻东路光谷大道","lng":1830.5617,"lat":0,"stopOrder":26,"metro":""},{"stopId":"027-2771","stopName":"珞喻东路大黄村","lng":114.4275741939103,"lat":30.505674700059703,"stopOrder":27,"metro":""},{"stopId":"027-867","stopName":"珞喻东路佳园路","lng":114.43226364510153,"lat":30.5054603803958,"stopOrder":28,"metro":""},{"stopId":"027-2769","stopName":"珞喻东路森林公园","lng":114.43904772941026,"lat":30.505572734486808,"stopOrder":29,"metro":""},{"stopId":"027-1684","stopName":"珞喻东路油篓口","lng":114.44476861785293,"lat":30.508421086858416,"stopOrder":30,"metro":""},{"stopId":"027-217","stopName":"珞喻东路长山","stopOrder":31,"metro":""},{"stopId":"027-119","stopName":"珞喻东路高坡店","lng":114.4629221428,"lat":30.50899574818192,"stopOrder":32,"metro":""}],"buses":["31373|12|6|0|114.26333823574225|30.59596213940074","31372|12|8|0|114.26847872203838|30.573547373207408","31468|12|10|0|114.3064495295415|30.539648007327862","31386|12|13|0|114.33363827649606|30.532815124680052","31382|12|15|0|114.35644506791272|30.52558144431725","3665|11|24|0|114.41303316673883|30.50746832939729","31369|12|32|0|114.45692009489427|30.51053182933003"]}
     */

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
        /**
         * lineName : 703
         * lineId : 027-703-0
         * lineNo : 703
         * direction : 0
         * startStopName : 汉口火车站
         * endStopName : 珞喻东路高坡店
         * firstTime : 5:30
         * lastTime : 23:00
         * intervalTime :
         * price : 1.0~2.0
         * stopsNum : 32
         * measure : 0.0
         * beBus :
         * line2Id : 027-703-1
         * stops : [{"stopId":"027-1569","stopName":"汉口火车站","stopOrder":1,"metro":""},{"stopId":"027-630","stopName":"后襄河北路","lng":114.254580158289,"lat":30.611619139562286,"stopOrder":2,"metro":""},{"stopId":"027-1296","stopName":"青年路市博物馆","lng":114.25728584840999,"lat":30.607083925575076,"stopOrder":3,"metro":""},{"stopId":"027-560","stopName":"青年路范湖","lng":114.26093215050082,"lat":30.605482623085837,"stopOrder":4,"metro":""},{"stopId":"027-683","stopName":"青年路机场河","lng":114.26308465718017,"lat":30.600089031997705,"stopOrder":5,"metro":""},{"stopId":"027-568","stopName":"青年路雪松路","lng":114.26321707427786,"lat":30.590775483682723,"stopOrder":6,"metro":""},{"stopId":"027-I-3300","stopName":"青年路地铁青年路站","lng":114.26476294130813,"lat":30.58521012426967,"stopOrder":7,"metro":""},{"stopId":"027-I-3287","stopName":"武胜路武胜西街","lng":114.2685925288944,"lat":30.57215077455038,"stopOrder":8,"metro":""},{"stopId":"027-91596","stopName":"鹦鹉大道地铁琴台站","lng":114.26631714076598,"lat":30.558470462555288,"stopOrder":9,"metro":""},{"stopId":"027-1140","stopName":"武珞路阅马场","lng":114.30558998409666,"lat":30.541130039734643,"stopOrder":10,"metro":""},{"stopId":"027-1435","stopName":"武珞路傅家坡客运站","lng":114.32732920082893,"lat":30.53601503269767,"stopOrder":11,"metro":""},{"stopId":"027-221","stopName":"武珞路丁字桥","stopOrder":12,"metro":""},{"stopId":"027-3313","stopName":"武珞路地铁宝通寺站","lng":114.34089771462524,"lat":30.53020945617543,"stopOrder":13,"metro":""},{"stopId":"027-18","stopName":"武珞路街道口","lng":114.34608460016322,"lat":30.528860846888417,"stopOrder":14,"metro":""},{"stopId":"027-710","stopName":"珞喻路广埠屯","lng":114.36232333935124,"lat":30.52367058662436,"stopOrder":15,"metro":""},{"stopId":"027-I-3350","stopName":"珞喻路卓刀泉中学","lng":114.37270626090243,"lat":30.518716142426243,"stopOrder":16,"metro":""},{"stopId":"027-2029","stopName":"珞喻路马家庄","lng":114.37826763631153,"lat":30.517274742554193,"stopOrder":17,"metro":""},{"stopId":"027-I-3256","stopName":"珞喻路科技会展中心","lng":114.3838910355411,"lat":30.515921969644914,"stopOrder":18,"metro":""},{"stopId":"027-1743","stopName":"珞喻路吴家湾","lng":114.39038050832441,"lat":30.511976372931475,"stopOrder":19,"metro":""},{"stopId":"027-2118","stopName":"珞喻路鲁巷","lng":114.3959353301365,"lat":30.50732862893219,"stopOrder":20,"metro":""},{"stopId":"027-648","stopName":"珞喻路光谷广场","lng":114.40057926222842,"lat":30.50562377905755,"stopOrder":21,"metro":""},{"stopId":"027-2002","stopName":"珞喻路湖北省中医院","lng":114.4075333539222,"lat":30.507012325785553,"stopOrder":22,"metro":""},{"stopId":"027-508","stopName":"珞喻路关山口","lng":114.41162777160059,"lat":30.507216913488463,"stopOrder":23,"metro":""},{"stopId":"4201001100403355139969024","stopName":"珞喻东路华中科技大学","lng":1830.5533,"lat":0,"stopOrder":24,"metro":""},{"stopId":"027-2772","stopName":"珞喻东路叶麻店","lng":114.41902340154655,"lat":30.506553002271446,"stopOrder":25,"metro":""},{"stopId":"4201001100403354372411392","stopName":"珞喻东路光谷大道","lng":1830.5617,"lat":0,"stopOrder":26,"metro":""},{"stopId":"027-2771","stopName":"珞喻东路大黄村","lng":114.4275741939103,"lat":30.505674700059703,"stopOrder":27,"metro":""},{"stopId":"027-867","stopName":"珞喻东路佳园路","lng":114.43226364510153,"lat":30.5054603803958,"stopOrder":28,"metro":""},{"stopId":"027-2769","stopName":"珞喻东路森林公园","lng":114.43904772941026,"lat":30.505572734486808,"stopOrder":29,"metro":""},{"stopId":"027-1684","stopName":"珞喻东路油篓口","lng":114.44476861785293,"lat":30.508421086858416,"stopOrder":30,"metro":""},{"stopId":"027-217","stopName":"珞喻东路长山","stopOrder":31,"metro":""},{"stopId":"027-119","stopName":"珞喻东路高坡店","lng":114.4629221428,"lat":30.50899574818192,"stopOrder":32,"metro":""}]
         * buses : ["31373|12|6|0|114.26333823574225|30.59596213940074","31372|12|8|0|114.26847872203838|30.573547373207408","31468|12|10|0|114.3064495295415|30.539648007327862","31386|12|13|0|114.33363827649606|30.532815124680052","31382|12|15|0|114.35644506791272|30.52558144431725","3665|11|24|0|114.41303316673883|30.50746832939729","31369|12|32|0|114.45692009489427|30.51053182933003"]
         */

        private String lineName;
        private String lineId;
        private String lineNo;
        private int direction;
        private String startStopName;
        private String endStopName;
        private String firstTime;
        private String lastTime;
        private String intervalTime;
        private String price;
        private int stopsNum;
        private double measure;
        private String beBus;
        private String line2Id;
        private List<StopsBean> stops;
        private List<String> buses;
        private int currentStopId;

        public void setCurrentStopId(int currentStopId){
            this.currentStopId = currentStopId;
        }

        public int getCurrentStopId(){
            return currentStopId;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
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

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
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

        public double getMeasure() {
            return measure;
        }

        public void setMeasure(double measure) {
            this.measure = measure;
        }

        public String getBeBus() {
            return beBus;
        }

        public void setBeBus(String beBus) {
            this.beBus = beBus;
        }

        public String getLine2Id() {
            return line2Id;
        }

        public void setLine2Id(String line2Id) {
            this.line2Id = line2Id;
        }

        public List<StopsBean> getStops() {
            return stops;
        }

        public void setStops(List<StopsBean> stops) {
            this.stops = stops;
        }

        public List<String> getBuses() {
            return buses;
        }

        public void setBuses(List<String> buses) {
            this.buses = buses;
        }

        public static class StopsBean {
            /**
             * stopId : 027-1569
             * stopName : 汉口火车站
             * stopOrder : 1
             * metro :
             * lng : 114.254580158289
             * lat : 30.611619139562286
             */

            private String stopId;
            private String stopName;
            private int stopOrder;
            private String metro;
            private double lng;
            private double lat;

            public String getStopId() {
                return stopId;
            }

            public void setStopId(String stopId) {
                this.stopId = stopId;
            }

            public String getStopName() {
                return stopName;
            }

            public void setStopName(String stopName) {
                this.stopName = stopName;
            }

            public int getStopOrder() {
                return stopOrder;
            }

            public void setStopOrder(int stopOrder) {
                this.stopOrder = stopOrder;
            }

            public String getMetro() {
                return metro;
            }

            public void setMetro(String metro) {
                this.metro = metro;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "lineName='" + lineName + '\'' +
                    ", lineId='" + lineId + '\'' +
                    ", lineNo='" + lineNo + '\'' +
                    ", direction=" + direction +
                    ", startStopName='" + startStopName + '\'' +
                    ", endStopName='" + endStopName + '\'' +
                    ", firstTime='" + firstTime + '\'' +
                    ", lastTime='" + lastTime + '\'' +
                    ", intervalTime='" + intervalTime + '\'' +
                    ", price='" + price + '\'' +
                    ", stopsNum=" + stopsNum +
                    ", measure=" + measure +
                    ", beBus='" + beBus + '\'' +
                    ", line2Id='" + line2Id + '\'' +
                    ", buses=" + buses +
                    '}';
        }
    }
}
