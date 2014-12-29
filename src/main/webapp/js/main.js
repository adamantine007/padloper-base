var xmlHttpRequest;
if (window.XMLHttpRequest) {
    xmlHttpRequest = new XMLHttpRequest();
} else if (window.ActiveXObject) {
    xmlHttpRequest = new window.ActiveXObject();
}

function cleanAddInputFields() {
    document.getElementById("id").value = "";
    document.getElementById("firstName").value = "";
    document.getElementById("secondName").value = "";
    document.getElementById("number").value = "";
}

function addPersonToDB() {
    var requestString = "addpersoncontroller?id=" +
        document.getElementById("id").value + "&firstName=" +
        document.getElementById("firstName").value + "&secondName=" +
        document.getElementById("secondName").value + "&number=" +
        document.getElementById("number").value;

    xmlHttpRequest.open("POST", requestString, true);
    xmlHttpRequest.onreadystatechange = getAddReportFromServer;
    xmlHttpRequest.send();
    cleanAddInputFields();
}

function getAddReportFromServer() {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        //document.getElementById("reportPlaceAdd").innerHTML = xmlHttpRequest.responseXML.getElementsByTagName("serverResponse")[0].textContent;
        document.getElementById("reportPlaceAdd").innerHTML = xmlHttpRequest.responseText;
    }
}

function selectPersonFromDB() {
    var requestString = "getpersoncontroller?selectId=" +
            document.getElementById("selectId").value;

    xmlHttpRequest.open("POST", requestString, true);
    xmlHttpRequest.onreadystatechange = getSelectReportFromServer;
    xmlHttpRequest.send();
    document.getElementById("selectId").value = "";
}

function getSelectReportFromServer() {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        //document.getElementById("reportPlaceSelect").innerHTML = xmlHttpRequest.responseXML.getElementsByTagName("serverResponse")[0].textContent;
        document.getElementById("reportPlaceSelect").innerHTML = xmlHttpRequest.responseText;
    }
}

function removePersonFromDB() {
    var requestString = "removepersoncontroller?removeId=" +
        document.getElementById("removeId").value;

    xmlHttpRequest.open("POST", requestString, true);
    xmlHttpRequest.onreadystatechange = getRemoveReportFromServer;
    xmlHttpRequest.send();
    document.getElementById("removeId").value = "";
}

function getRemoveReportFromServer() {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        //document.getElementById("reportPlaceSelect").innerHTML = xmlHttpRequest.responseXML.getElementsByTagName("serverResponse")[0].textContent;
        document.getElementById("reportPlaceRemove").innerHTML = xmlHttpRequest.responseText;
    }
}


function commitPersonDB() {
    xmlHttpRequest.open("POST", "commitpersondbcontroller", true);
    xmlHttpRequest.onreadystatechange = getCommitReportFromServer;
    xmlHttpRequest.send();
}

function getCommitReportFromServer() {
    if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        document.getElementById("reportPlaceCommit").innerHTML = xmlHttpRequest.responseText;
    }
}

