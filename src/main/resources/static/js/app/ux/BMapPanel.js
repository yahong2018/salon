Ext.define('app.ux.BMapPanel', {
    extend: 'Ext.Panel',
    alias: 'widget.bmappanel',
    requires: ['Ext.window.MessageBox'],

    arrowData: {},
    listeners: {},
    mouseDropOverlays: [],
    constructor: function(configParam) {
        var configBase = {
            html: "<div id='" + configParam.containerId + "' style='height:100%;'></div>"
        };
        Ext.applyIf(configParam, configBase);
        this.mapMeta = Ext.create('app.ux.MapDataMeta');
        this.arrowData = this.getInitArrowData();

        this.callParent(arguments);
    },

    getInitArrowData: function() {
        return {
            lineList: [],
            arrowLineList: [],
            isFirstLoad: true,
            arrowLineLengthRate: (15 / 10)
        };
    },

    clearMap: function() {
        this.getMapData().clear();
        if (this.getMap()) {
            this.getMap().clearOverlays();
        }
        this.map = null;
        this.arrowData = this.getInitArrowData();
        this.mouseDropOverlays = [];

        this.body.update("<div id='" + this.containerId + "' style='height:100%;'></div>");
    },
    createMap: function() {
        this.initMap();
        this.buildMap();
    },

    getMapData: function() {
        return this.mapMeta;
    },

    initMap: function() {
        var map = this.getMap();
        var me = this;
        if (!map) {
            map = new BMap.Map(this.containerId);
            this.map = map;

            var top_left_control = new BMap.ScaleControl({
                anchor: BMAP_ANCHOR_TOP_LEFT
            });
            var top_left_navigation = new BMap.NavigationControl();
            map.addControl(top_left_control);
            map.addControl(top_left_navigation);
            map.enableScrollWheelZoom(true);
          //  map.addControl(new BMap.MapTypeControl());

            this.centAndZoomByXY(this.initCenter.X, this.initCenter.Y, this.initCenter.zoomLevel);

            this.arrowData.isFirstLoad = true;
            //地图加载完毕事件
            map.addEventListener("tilesloaded", function() {
                me.onMapTilesLoaded();
            });
        } else {
            this.getMapData().clear();
            map.clearOverlays();
        }
    },
    centAndZoomByXY: function(x, y, level) {
        var point = new BMap.Point(x, y); //创建point位置
        this.getMap().centerAndZoom(point, level);
    },

    createMarker: function(x, y) {
        var marker = new BMap.Marker(new BMap.Point(x, y));
        this.getMap().addOverlay(marker); //将Marker点覆盖到地图上
        return marker;
    },

    getMap: function() {
        return this.map;
    },

    beforedestroy: function() {
        if (this.map) {
            this.map = null;
        }
    },

    getMouseDrawingLinePoints: function() {
        var lineDirections = [];

        for (var i = 0; i < this.mouseDropOverlays.length; i++) {
            var p1 = this.mouseDropOverlays[i].getPath()[0];
            var p2 = this.mouseDropOverlays[i].getPath()[1]

            lineDirections.push({
                X1: p1.lng,
                Y1: p1.lat,
                X2: p2.lng,
                Y2: p2.lat
            });
        }

        return lineDirections;
    },

    openMouseDrawing: function() {
        var me = this;
        var overlaycomplete = function(e) {
            me.mouseDropOverlays.push(e.overlay);
        };
        var styleOptions = {
                strokeColor: "red", //边线颜色。
                fillColor: "red", //填充颜色。当参数为空时，圆形将没有填充效果。
                strokeWeight: 3, //边线的宽度，以像素为单位。
                strokeOpacity: 0.8, //边线透明度，取值范围0 - 1。
                fillOpacity: 0.6, //填充的透明度，取值范围0 - 1。
                strokeStyle: 'solid' //边线的样式，solid或dashed。
            }
            //实例化鼠标绘制工具
        var drawingManager = new BMapLib.DrawingManager(me.getMap(), {
            isOpen: false, //是否开启绘制模式
            enableDrawingTool: true, //是否显示工具栏
            drawingToolOptions: {
                anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
                offset: new BMap.Size(5, 5), //偏离值
            },
            circleOptions: styleOptions, //圆的样式
            polylineOptions: styleOptions, //线的样式
            polygonOptions: styleOptions, //多边形的样式
            rectangleOptions: styleOptions //矩形的样式
        });
        //添加鼠标绘制工具监听事件，用于获取绘制结果
        drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    },

    onMapTilesLoaded: function() {
        var me = this;
        var map = me.getMap();

        if (!me.arrowData.isFirstLoad) {
            for (var i = 0; i < me.arrowData.arrowLineList.length; i++) {
                map.removeOverlay(me.arrowData.arrowLineList[i]);
            }
            me.arrowData.arrowLineList.length = 0;
            //重新绘制箭头线
            for (var i = 0; i < me.arrowData.lineList.length; i++) {
                me.arrowData.arrowLineList[me.arrowData.arrowLineList.length] = me.addArrow(me.arrowData.lineList[i], 15 / me.arrowData.arrowLineLengthRate, Math.PI / 7); //记录绘制的箭头线
            }
        }
        me.arrowData.isFirstLoad = false;
    },

    createArrowLine: function(p1, p2) {
        var polyline = new BMap.Polyline([
            new BMap.Point(p1.X, p1.Y),
            new BMap.Point(p2.X, p2.Y)
        ], {
            strokeColor: "red",
            strokeWeight: 3,
            strokeOpacity: 0.5
        });
        this.map.addOverlay(polyline);
        this.arrowData.lineList[this.arrowData.lineList.length] = polyline; //记录要绘制的线
        this.arrowData.arrowLineList[this.arrowData.arrowLineList.length] = this.addArrow(polyline, 10, Math.PI / 7); //记录绘制的箭头线
    },

    addArrow: function(polyline, length, angleValue) { //绘制箭头的函数
        var linePoint = polyline.getPath(); //线的坐标串
        var map = this.getMap();

        var arrowCount = linePoint.length;
        for (var i = 1; i < arrowCount; i++) { //在拐点处绘制箭头
            var pixelStart = map.pointToPixel(linePoint[i - 1]);
            var pixelEnd = map.pointToPixel(linePoint[i]);
            var angle = angleValue; //箭头和主线的夹角
            var r = length; // r/Math.sin(angle)代表箭头长度
            var delta = 0; //主线斜率，垂直时无斜率
            var param = 0; //代码简洁考虑
            var pixelTemX, pixelTemY; //临时点坐标
            var pixelX, pixelY, pixelX1, pixelY1; //箭头两个点
            if (pixelEnd.x - pixelStart.x == 0) { //斜率不存在是时
                pixelTemX = pixelEnd.x;
                if (pixelEnd.y > pixelStart.y) {
                    pixelTemY = pixelEnd.y - r;
                } else {
                    pixelTemY = pixelEnd.y + r;
                }
                //已知直角三角形两个点坐标及其中一个角，求另外一个点坐标算法
                pixelX = pixelTemX - r * Math.tan(angle);
                pixelX1 = pixelTemX + r * Math.tan(angle);
                pixelY = pixelY1 = pixelTemY;
            } else //斜率存在时
            {
                delta = (pixelEnd.y - pixelStart.y) / (pixelEnd.x - pixelStart.x);
                param = Math.sqrt(delta * delta + 1);

                if ((pixelEnd.x - pixelStart.x) < 0) //第二、三象限
                {
                    pixelTemX = pixelEnd.x + r / param;
                    pixelTemY = pixelEnd.y + delta * r / param;
                } else //第一、四象限
                {
                    pixelTemX = pixelEnd.x - r / param;
                    pixelTemY = pixelEnd.y - delta * r / param;
                }
                //已知直角三角形两个点坐标及其中一个角，求另外一个点坐标算法
                pixelX = pixelTemX + Math.tan(angle) * r * delta / param;
                pixelY = pixelTemY - Math.tan(angle) * r / param;

                pixelX1 = pixelTemX - Math.tan(angle) * r * delta / param;
                pixelY1 = pixelTemY + Math.tan(angle) * r / param;
            }

            var pointArrow = map.pixelToPoint(new BMap.Pixel(pixelX, pixelY));
            var pointArrow1 = map.pixelToPoint(new BMap.Pixel(pixelX1, pixelY1));
            var Arrow = new BMap.Polyline(
                [
                    pointArrow,
                    linePoint[i],
                    pointArrow1
                ], {
                    strokeColor: "red",
                    strokeWeight: 3,
                    strokeOpacity: 0.5
                });
            map.addOverlay(Arrow);
            return Arrow;
        }
    }

});