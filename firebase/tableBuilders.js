var rootRef = new Firebase('https://curo.firebaseio.com/');
var alertRef = new Firebase('https://curo.firebaseio.com/alerts/')
var areaRef = new Firebase('https://curo.firebaseio.com/areas/')

function buildAlertTable() {
    var alerts = alertRef.val()
    var columns = addAllColumnHeaders(alertRef)

    for (var i = 0 ; i < alerts.length ; i++) {
        var row$ = $('<tr/>');
        for (var colIndex = 0; colIndex < columns.length ; colIndex++) {
            var cellValue = alerts[i][columns[colIndex]];

            if (cellValue == null) {
                cellValue = "";
            }

            row$.append($('<td/>').html(cellValue));
        }
        $("#alertTable").append(row$);
    }
}

function addAllColumnHeaders(alertList) {
    var columnSet = [];
    var headerTr$ = $('<tr/>');

    for (var i=0; i < alertList.length ; i++) {
        var rowHash = alertList[i];
        for(var key in rowHash) {
            if($.inArray(key, columnSet) == -1) {
                columnSet.push(key);
                headerTr$.append($('<th/>').html(key));
            }
        }
    }
    $("#alertTable").append(headerTr$);
    return columnSet;
}