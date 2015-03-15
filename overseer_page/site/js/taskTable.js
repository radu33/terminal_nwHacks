buildTaskTable();

function buildTaskTable() {
    var body = document.getElementsByTagName("body")[0];
    var myDataRef = new Firebase('https://curo.firebaseio.com/');

    var taskList = myDataRef.child('tasks');
    var theTaskTable = document.createElement("taskTable");
    var tblBody = document.createElement("tblBody");

$.getJSON('https://curo.firebaseio.com/tasks/.json', function(data) {
        var headerRow = document.createElement("tr");
        var header1 = document.createElement("th")
        header1.appendChild(document.createTextNode("Description"));
        headerRow.appendChild(header1);
        var header2 = document.createElement("th")
        header2.appendChild(document.createTextNode("Location Num"));
        headerRow.appendChild(header2);
        var header3 = document.createElement("th")
        header3.appendChild(document.createTextNode("Time Range"));
        headerRow.appendChild(header3);
        var header4 = document.createElement("th")
        header4.appendChild(document.createTextNode("Progress"));
        headerRow.appendChild(header4);
        tblBody.appendChild(headerRow);
        $.each(data, function (key, val) {
            var row = document.createElement("tr");
            var cell1 = document.createElement("td");
            var cellDesc = document.createTextNode(val.desc + '\n');
            cell1.appendChild(cellDesc);
            row.appendChild(cell1);
            var cell2 = document.createElement("td");
            var cellLoc = document.createTextNode(val.location);
            cell2.appendChild(cellLoc);
            row.appendChild(cell2);
            var cellTime = document.createTextNode(val.time.start + "-" + val.time.finish);
            var cell3 = document.createElement("td");
            cell3.appendChild(cellTime);
            row.appendChild(cell3);
            var completionString = ""
            if (val.completion == 0) {
                completionString = "Done";
            }; 
            if (val.completion == 1) {
                completionString == "In Progress";
            } else {
                completionString == "Not Started";
            }
            var cellProg = document.createTextNode(completionString);
            var cell4 = document.createElement("td");
            cell4.appendChild(cellProg);
            row.appendChild(cell4);
            tblBody.appendChild(row);
            
        });
    });
    theTaskTable.appendChild(tblBody);
    body.appendChild(theTaskTable);
}
