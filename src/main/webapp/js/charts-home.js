$(document).ready(function () {

    'use strict';

    Chart.defaults.global.defaultFontColor = '#75787c';


    // ------------------------------------------------------- //
    // Line Chart
    // ------------------------------------------------------ //
    var legendState = true;
    if ($(window).outerWidth() < 576) {
        legendState = false;
    }

    var LINECHART = $('#lineCahrt');
    var myLineChart = new Chart(LINECHART, {
        type: 'line',
        options: {
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        display: false
                    }
                }],
                yAxes: [{
                    ticks: {
                        max: 30,
                        min: 0
                    },
                    display: true,
                    gridLines: {
                        display: false
                    }
                }]
            },
            legend: {
                display: legendState
            }
        },
        data: {
            labels: ["10.21", "10.22", "10.23", "10.24", "10.25", "10.26", "10.27", "10.28", "10.29"],
            datasets: [
                {
                    label: "用户",
                    fill: true,
                    lineTension: 0.2,
                    backgroundColor: "transparent",
                    borderColor: 'rgb(134, 77, 217)',
                    pointBorderColor: '#864959',
                    pointHoverBackgroundColor: '#864959',
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 2,
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 5,
                    pointHoverRadius: 5,
                    pointHoverBorderColor: "#fff",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 0,
                    data: [5, 0, 0, 2, 4, 3, 2, 1, 0],
                    spanGaps: false
                },
               
                {
                    label: "文章",
                    fill: true,
                    lineTension: 0.2,
                    backgroundColor: "transparent",
                    borderColor: "rgb(233, 95, 113)",
                    pointBorderColor: 'rgb(233, 95, 113)',
                    pointHoverBackgroundColor: "rgb(233, 95, 113)",
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 2,
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 5,
                    pointHoverRadius: 5,
                    pointHoverBorderColor: "#fff",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    data: [7, 5, 6, 5, 6, 12, 8, 4, 9],
                    spanGaps: false
                },
                {
                    label: "会议",
                    fill: true,
                    lineTension: 0.2,
                    backgroundColor: "transparent",
                    borderColor: "rgb(0, 123, 255)",
                    pointBorderColor: 'rgb(0, 123, 255)',
                    pointHoverBackgroundColor: "rgb(0, 123, 255)",
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 2,
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 5,
                    pointHoverRadius: 5,
                    pointHoverBorderColor: "#fff",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    data: [1, 2, 3, 2, 5, 6, 1, 2, 4],
                    spanGaps: false
                }
            ]
        }
    });


    var pieChartExample = {
        responsive: true
    };

});
