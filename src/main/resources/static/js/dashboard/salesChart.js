var dom = document.getElementById("salesChart");
var myChart = echarts.init(dom);
var app = {};
option = null;

// var source1=[]
// var source2=[]
// source2.push('score')
// source2.push('amount')
// source2.push('product')
// source1.push(source2)
// var source3=[]
// source3.push(89.3)
// source3.push(58212)
// source3.push('爽肤水')
// source1.push(source3)
// console.log("+++json"+JSON.stringify(source1));
var productTotal2=[];
var source2=[]
source2.push('score')
source2.push('amount')
source2.push('product')
productTotal2.push(source2)

$.ajax({
    url:"/hy/basic/statistics/queryProductTotal",
    type:"get",
    async:false,
    dataType:"json",
    success:function(dataJson){
        $.each(dataJson.data, function (i, n) {

            var source3=[]
            source3.push(0)
            source3.push(n.count1)
            source3.push(n.productName)
            productTotal2.push(source3)


        });
    },
    error:function(){
        alert("驳回异常")
    }
});


var option = {
    dataset: {
        source: productTotal2
    },
    grid: {containLabel: true},
    xAxis: {name: 'amount'},
    yAxis: {type: 'category'},
    visualMap: {
        orient: 'horizontal',
        left: 'center',
        min: 10,
        max: 100,
        text: ['High Score', 'Low Score'],
        // Map the score column to color
        dimension: 0,
        inRange: {
            color: ['#D7DA8B', '#E15457']
        }
    },
    series: [
        {
            type: 'bar',
            encode: {
                // Map the "amount" column to X axis.
                x: 'amount',
                // Map the "product" column to Y axis
                y: 'product'
            }
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}