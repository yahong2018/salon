var dom = document.getElementById("salesChart");
var myChart = echarts.init(dom);
var app = {};
option = null;
var option = {
    dataset: {
        source: [
            ['score', 'amount', 'product'],
            [89.3, 58212, '爽肤水'],
            [57.1, 78254, '乳液'],
            [74.4, 41032, '面霜'],
            [50.1, 12755, '精华素'],
            [89.7, 20145, '原液'],
            [68.1, 79146, 'Tea'],
            [19.6, 91852, '面部润肤'],
            [10.6, 101852, 'BB霜'],
            [32.7, 20112, '纯露']
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