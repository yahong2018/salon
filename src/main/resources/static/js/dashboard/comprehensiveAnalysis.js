var dom = document.getElementById("monthlyChart2");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '环形图';
var size1=600;
var size2=400;

var sumAmount;
var sumAmount3;
var ArreagesAmount;
var rejectAmount;

$.ajax({
    url:"/hy/basic/statistics/queryComprehensiveAnalysis",
    type:"get",
    async:false,
    dataType:"json",
    success:function(dataJson){
        sumAmount=dataJson.data.sumAmount+dataJson.data.sumAmount2;
        sumAmount3=dataJson.data.sumAmount3;
        ArreagesAmount=dataJson.data.ArreagesAmount;
        rejectAmount=dataJson.data.rejectAmount;

        // $.each(dataJson.data, function (i, n) {
        //     var source3=[]
        //     source3.push(0)
        //     source3.push(n.count1)
        //     source3.push(n.productName)
        //     productTotal2.push(source3)
        //
        //
        // });
    },
    error:function(){
        alert("驳回异常")
    }
});

option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:['充值','消费','还欠款','退款']
    },
    series: [
        {
            name:'综合分析',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '15px',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:sumAmount, name:'充值'},
                {value:sumAmount3, name:'消费'},
                {value:ArreagesAmount, name:'还欠款'},
                {value:rejectAmount, name:'退款'}
            ]
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
