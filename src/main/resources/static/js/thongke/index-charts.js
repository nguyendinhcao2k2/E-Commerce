const viewUrl = "http://localhost:8080/api/admin";
'use strict';

/* Chart.js docs: https://www.chartjs.org/ */

window.chartColors = {
    green: '#75c181',
    gray: '#a9b5c9',
    text: '#252930',
    border: '#e7e9ed'
};

/* Random number generator for demo purpose */
let randomDataPoint = function () {
    return Math.round(Math.random() * 10000)
};


//Chart.js Line Chart Example 
function createLineChart(data, sortBy) {
    let lb = [];
    let dt = [];
    for (let i = 0; i < data.length; i++) {
        lb.push(data[i].dataLabel);
        dt.push(data[i].amount);
    }
    let lineChartConfig = {
        type: 'line',

        data: {
            labels: lb,

            datasets: [{
                label: sortBy,
                fill: false,
                backgroundColor: window.chartColors.green,
                borderColor: window.chartColors.green,
                data: dt,
            }]
        },
        options: {
            responsive: true,
            aspectRatio: 1.5,

            legend: {
                display: true,
                position: 'bottom',
                align: 'end',
            },

            title: {
                display: true,
            },
            tooltips: {
                mode: 'index',
                intersect: false,
                titleMarginBottom: 10,
                bodySpacing: 10,
                xPadding: 16,
                yPadding: 16,
                borderColor: window.chartColors.border,
                borderWidth: 1,
                backgroundColor: '#fff',
                bodyFontColor: window.chartColors.text,
                titleFontColor: window.chartColors.text,

                callbacks: {
                    //Ref: https://stackoverflow.com/questions/38800226/chart-js-add-commas-to-tooltip-and-y-axis
                    label: function (tooltipItem, data) {
                        if (parseInt(tooltipItem.value) >= 1000) {
                            return "$" + tooltipItem.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        } else {
                            return '$' + tooltipItem.value;
                        }
                    }
                },

            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        drawBorder: false,
                        color: window.chartColors.border,
                    },
                    scaleLabel: {
                        display: false,

                    }
                }],
                yAxes: [{
                    display: true,
                    gridLines: {
                        drawBorder: false,
                        color: window.chartColors.border,
                    },
                    scaleLabel: {
                        display: false,
                    },
                    ticks: {
                        beginAtZero: true,
                        userCallback: function (value, index, values) {
                            return '$' + value.toLocaleString();   //Ref: https://stackoverflow.com/questions/38800226/chart-js-add-commas-to-tooltip-and-y-axis
                        }
                    },
                }]
            }
        }
    };
    return lineChartConfig;
}


// Generate charts on load
window.addEventListener('load', function () {
    searchByWeek();
});


function searchByWeek() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: viewUrl + "/amount-by-week",
        success: function (responseData) {
            let lineChart = document.getElementById('canvas-linechart').getContext('2d');
            window.myLine = new Chart(lineChart, createLineChart(responseData, "Current Week"));
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function searchByMonth() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: viewUrl + "/amount-by-month",
        success: function (responseData) {
            let lineChart = document.getElementById('canvas-linechart').getContext('2d');
            window.myLine = new Chart(lineChart, createLineChart(responseData, "Current Month"));
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function searchByYear() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: viewUrl + "/amount-by-year",
        success: function (responseData) {
            let lineChart = document.getElementById('canvas-linechart').getContext('2d');
            window.myLine = new Chart(lineChart, createLineChart(responseData, "Current Year"));
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function search(value) {
   if(value === 'week'){
       searchByWeek();
   }else  if(value === 'month'){
       searchByMonth();
   }else if(value === 'year'){
       searchByYear();
   }
}

