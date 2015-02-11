(function () {
    /* PROCESS ADD FORM */
    var addForm = document.getElementById("addForm");

    addForm.onsubmit = function () {
        var url = addForm.getAttribute("action");

        var type = document.getElementById("addType").value;
        var id = document.getElementById("addID").value;
        var firstName = document.getElementById("addFirstName").value;
        var secondName = document.getElementById("addSecondName").value;
        var number = document.getElementById("addNumber").value;

        AjaxUtil.ajax(url, {
                method: "POST",
                data: {
                    type: type,
                    id: id,
                    firstName: firstName,
                    secondName: secondName,
                    number: number
                },
                complete: function (response) {
                    alert(response);
                }
            }
        );

        return false;
    };

    /* PROCESS GET FORM */
    var getForm = document.getElementById("getForm");

    getForm.onsubmit = function () {
        var url = addForm.getAttribute("action");

        var type = document.getElementById("getType").value;
        var id = document.getElementById("getID").value;

        AjaxUtil.ajax(url, {
            method: "POST",
            data: {
                type: type,
                id: id
            },
            complete: function (response) {
                alert(response.id + " "
                + response.firstName + " "
                + response.secondName + " "
                + response.number);
            }
        });

        return false;
    };

    /* PROCESS REMOVE FORM */
    var removeForm = document.getElementById("removeForm");

    removeForm.onsubmit = function () {
        var url = removeForm.getAttribute("action");

        var type = document.getElementById("removeType").value;
        var id = document.getElementById("removeID").value;

        AjaxUtil.ajax(url, {
            method: "POST",
            data: {
                type: type,
                id: id
            },
            complete: function(response) {
                alert(response);
            }
        });

        return false;
    };

    /* PROCESS REMOVE FORM */
    var commitForm = document.getElementById("commitForm");

    commitForm.onsubmit = function () {
        var url = removeForm.getAttribute("action");

        var type = document.getElementById("commitType").value;

        AjaxUtil.ajax(url, {
            method: "POST",
            data: {
                type: type
            },
            complete: function (response) {
                alert(response);
            }
        });

        return false;
    }

})();