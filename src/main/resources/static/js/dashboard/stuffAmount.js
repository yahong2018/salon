var dom = document.getElementById("stuffAmount");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '员工业绩前十';

var stuffList=[];

var paymentAmountList=[]

var consumptionAmountList=[]

var rechargeAmountList=[]


$.ajax({
    url:"/hy/basic/statistics/queryStuffAnalysis",
    type:"get",
    async:false,
    dataType:"json",
    success:function(dataJson){
        $.each(dataJson.data.stuffAmountList2, function (i, n) {

            stuffList.push(n.stuffName);

            paymentAmountList.push(n.paymentAmount);
            consumptionAmountList.push(n.consumptionAmount);
            rechargeAmountList.push(n.rechargeAmount);

        });
    },
    error:function(){
        alert("驳回异常")
    }
});
console.log("+++"+JSON.stringify(stuffList));

option = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data: ['业绩', '卡耗','消费','充值']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:  {
        type: 'value'
    },
    yAxis: {
        type: 'category',
        data: stuffList
    },
    series: [
        {
            name: '业绩',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [0, 0, 0, 0, 0, 0, 0,0, 0, 0]
        },
        {
            name: '卡耗',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: paymentAmountList
        },
        {
            name: '消费',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: consumptionAmountList
        },
        {
            name: '充值',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: rechargeAmountList
        }
    ]
};



if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
