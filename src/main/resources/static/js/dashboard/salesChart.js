var dom = document.getElementById("salesChart");
var myChart = echarts.init(dom);
var app = {};
option = null;

var source1=[]
var source2=[]
source2.push('score')
source2.push('amount')
source2.push('product')
source1.push(source2)
var source3=[]
source3.push(89.3)
source3.push(58212)
source3.push('爽肤水')
source1.push(source3)
console.log("+++json"+JSON.stringify(source1));
var option = {
    dataset: {
        source: [
            ['score', 'amount', 'product'],
            [0, 58212, '爽肤水'],
            [0, 78254, '乳液'],
            [0, 41032, '面霜'],
            [0, 12755, '精华素'],
            [0, 20145, '原液'],
            [0, 79146, 'Tea'],
            [0, 91852, '面部润肤'],
            [0, 101852, 'BB霜'],
            [0, 20112, '纯露']
        ]
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