    var body = document.getElementsByTagName("body")[0];
    var myDataRef = new Firebase('https://curo.firebaseio.com/');
    var taskList = myDataRef.child('areas');

    window.alert(taskList);

    //console.out(Firebase(taskList));

    taskList.once('value', function(snapshot) {
        //window.alert(snapshot.val());
        var tempVar = snapshot.key();
        window.alert(tempVar);
        console.out(snapshot.toString());
        console.out(snapshot.val().desc);
    });

    var num = taskList.child(0);
    var lat = num.child('lat');
    var lon = num.child('lon');
    var desc = num.child('desc');

    num.once('value', function(snapshot) {
        console.out(snapshot.val());
    });


    //var theTaskTable = document.getElementByID("taskTable");
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

//function httpGet(taskList){
//        var xmlHttp = null;
//
//        xmlHttp = new XMLHttpRequest();
//        xmlHttp.open("GET",taskList,false);
//        xmlHttp.send(null);
//        window.alert(xmlHttp.responseText);
//        return xmlHttp.responseText;
//    }
//
//window.alert("This is the alert");
