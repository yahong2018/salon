var dom = document.getElementById("monthlyChart3");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '环形图';
var size1=600;
var size2=400;

var cashAmount;
var bankCardAmount;
var weixinAmount;
var alipayAmount;

$.ajax({
    url:"/hy/basic/statistics/queryBudgetAnalysis",
    type:"get",
    async:false,
    dataType:"json",
    success:function(dataJson){
        cashAmount=dataJson.data.cashAmount
        bankCardAmount=dataJson.data.bankCardAmount;
        weixinAmount=dataJson.data.weixinAmount;
        alipayAmount=dataJson.data.alipayAmount;


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
        data:['现金','银行卡','微信','支付宝']
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
                {value:cashAmount, name:'现金'},
                {value:bankCardAmount, name:'银行卡'},
                {value:weixinAmount, name:'微信'},
                {value:alipayAmount, name:'支付宝'}
            ]
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
