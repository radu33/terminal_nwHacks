    var body = document.getElementsByTagName("body")[0];
    var myDataRef = new Firebase('https://curo.firebaseio.com/');

 //   console.out(httpGet('https://curo.furebaseio.com/areas.json'));
    var taskList = myDataRef.child('tasks');

    var jsontemp = jQuery.getJSON('https://curo.firebaseio.com/tasks/.json');
//    console.log(jsontemp.responseText);
 //   var parsedJson = $.parseJSON(jsontemp);
//    console.log(parsedJson.count);

$.getJSON('https://curo.firebaseio.com/tasks/.json', function(data) {
        var items = [];
        $.each(data, function (key, val) {
            alert(key + val.desc);
            var row = document.createElement("tr");
            var cell = document.createElement("td");
            var cellText = document.createTextNode(key + ": " + val.desc);
            cell.appendChild(cellText);
            row.appendChild(cell);
        });

        $( "</table>", {
            "class" : "my-new-table",
            html: items.join("")
        }).appendTo("body");
    });


    var theTaskTable = document.createElement("taskTable");
    var tblBody = document.createElement("tblBody");

    for (iTask = 0; iTask < taskList.length; iTask++) {
        var row = document.createElement("tr");
        for (iField = 0; iField < taskList[iTask],length; iField++) {
            var cell = document.createElement("td");
            var cellText = document.createTextNode(taskList[iTask][iField]);
            cell.appendChild(cellText);
            row.appendChild(cell);
        }
        tblBody.appendChild(row);
    }
    theTaskTable.appendChild(tblBody);
    body.appendChild(theTaskTable);

//window.alert("This is the alert");
