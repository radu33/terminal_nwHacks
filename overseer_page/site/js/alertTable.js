buildAlertTable();

function buildAlertTable() {
    var body = document.getElementsByTagName("body")[0];
    var myDataRef = new Firebase('https://curo.firebaseio.com/');

    var taskList = myDataRef.child('tasks');
    var theTaskTable = document.createElement("alertTable");
    var tblBody = document.createElement("tblBody");

    var incomingMessageArray = [];

    $.getJSON('https://curo.firebaseio.com/conversations/.json', function(data) {
        var headerRow = document.createElement("tr");
        var header1 = document.createElement("th")
        header1.appendChild(document.createTextNode("Type"));
        headerRow.appendChild(header1);
        var header2 = document.createElement("th")
        header2.appendChild(document.createTextNode("From"));
        headerRow.appendChild(header2);
        var header3 = document.createElement("th")
        header3.appendChild(document.createTextNode("Message"));
        headerRow.appendChild(header3);
        tblBody.appendChild(headerRow);
        $.each(data, function (key, val) {
            var sender = key;
            var receiver = val;

            if (receiver == "Overseer") {
                addAllChildren(sender, receiver, incomingMessageArray);
            }
        });
    });
    
    var iArray = 0;

    for (iArray = 0; iArray < incomingMessageArray.length; iArray++) {
        var row = document.createElement("tr");
        var cellAlert = document.createElement("td");

        var sender = "";
        var isAlert = false;
        var message = "";

        var curItem = incomingMessageArray[iArray];
        var msgArray = curItem.split("!!!");
        if (msgArray.length == 3) {
            sender = msgArray[0];
            isAlert = msgArray[1];
            message = msgArray[2];
        }
        
        if (isAlert) {
            isAlert = "ALERT! ALERT!";
        } else {
            isAlert = "Info Request";
        };
        cellAlert.appendChild(document.createTextNode(isAlert));
        row.appendChild(cellAlert);

        var cellFrom = document.createElement("td");
        cellFrom.appendChild(document.createTextNode(sender));
        row.appendChild(cellAlert);

        var cellMsg = document.createElement("td");
        cellMsg.appendChild(document.createTextNode(message));
        row.appendChild(cellMsg);

        tblBody.appendChild(row);
    }

    theTaskTable.appendChild(tblBody);
    body.appendChild(theTaskTable);
}

function addAllChildren(sender, receiver, incomingMessageArray) {

    $.getJSON('https://curo.firebaseio.com/conversations/' + sender + '/' + receiver + '/.json', function(data) {
        var msgCount = 0;
        $.each(data, function(key, val) {
            // TODO add msg count to data store
            incomingMessageArray[sender + msgCount] = key + '!!!' + value.isAlert + '!!!' + value.messageBody;
        });
    });
}



