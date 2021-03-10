function usersTable() {
    $.ajax({
        type: "GET",
        url: "users",
        success: function (result) {
            $('#user').empty();
            $.each(result, function (e, u) {
                let user = `<tr><td>${u.id}</td>
                           <td id="f${u.id}">${u.firstName}</td>
                           <td id="l${u.id}">${u.lastName}</td>
                           <td id="a${u.id}">${u.age}</td>
                           <td id="e${u.id}">${u.email}</td>
                           <td id="p${u.id}">${u.password}</td>
                           <td id="r${u.id}">${getStringFromRoles(u.roles)}</td>
                           <td><button type="button" onclick="setUpdateModal(${u.id})" class="btn btn-info"
                           data-toggle="modal" data-target="#exampleModalUpdate">Edit</td>
                           <td><button type="button" onclick="setDeleteModal(${u.id})" class="btn btn-danger"
                           data-toggle="modal" data-target="#exampleModalDelete">Delete</td>
                           </tr>`;
                $('#user').append(user);
            })
        }
    })
}

function addUser() {
    let formData = {
        id: $("#id").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        age: $("#age").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        roles: getRolesFromString($("#roles").val())
    }

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "add",
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function () {
            $("#userForm")[0].reset();
            $("#user-table-but").click();
            usersTable();
        },
        error: function () {
            alert("error");
            usersTable();
        }
    })
}

function setUpdateModal(id) {
    $("#upId").attr("value", id);
    $("#upFirstName").attr("value", $("#f" + id)[0].innerHTML);
    $("#upLastName").attr("value", $("#l" + id)[0].innerHTML);
    $("#upAge").attr("value", $("#a" + id)[0].innerHTML);
    $("#upEmail").attr("value", $("#e" + id)[0].innerHTML);
    $("#upPassword").attr("value", $("#p" + id)[0].innerHTML);
    $("#upRoles").attr("value", $("#r" + id)[0].innerHTML);

}

function editUser() {

    let formData = {
        id: $("#upId").val(),
        firstName: $("#upFirstName").val(),
        lastName: $("#upLastName").val(),
        age: $("#upAge").val(),
        email: $("#upEmail").val(),
        password: $("#upPassword").val(),
        roles: getRolesFromString($("#upRoles").val())
    }

    $.ajax({
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        url: "update",
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function () {
            location.reload();
            $('.cl').empty();
            usersTable();
        },
        error: function () {
            alert("error");
            usersTable();
        }
    })
}

function setDeleteModal(id) {
    $("#dId").attr("value", id);
    $("#dFirstName").attr("value", $("#f" + id)[0].innerHTML);
    $("#dLastName").attr("value", $("#l" + id)[0].innerHTML);
    $("#dAge").attr("value", $("#a" + id)[0].innerHTML);
    $("#dEmail").attr("value", $("#e" + id)[0].innerHTML);
    $("#dPassword").attr("value", $("#p" + id)[0].innerHTML);
    $("#dRoles").attr("value", $("#r" + id)[0].innerHTML);
}

function deleteUser(id) {
    $.ajax({
        type: "POST",
        url: "delete/" + id,
        success: function () {
            usersTable();
        },
        error: function () {
            alert("error");
            usersTable();
        }
    })
}

// Get one user
function oneUser() {
    $.ajax({
        type: "GET",
        url: "getUser",
        success: function (u) {
            $('#cuser').empty();
            let forNav = `<p>${u.email} with roles: ${getStringFromRoles(u.roles)}</p>`;
            $('#userForNav').append(forNav);
            let curUser = `<tr><td>${u.id}</td>
                           <td>${u.firstName}</td>
                           <td>${u.lastName}</td>
                           <td>${u.age}</td>
                           <td>${u.email}</td>
                           <td>${u.password}</td>
                           <td>${getStringFromRoles(u.roles)}</td></tr>`;
            $('#currentUser').append(curUser);

        }
    })
}

$(document).ready(
    function () {
        usersTable();
        oneUser();

        $("#userForm").submit(function (event) {
            event.preventDefault();
            addUser();
        })

        $("#deleteButton").click(function (event) {
            event.preventDefault();
            deleteUser($("#dId").val());
            $('#exampleModalDelete').modal('hide');
        })

        $("#updateButton").click(function (event) {
            event.preventDefault();
            editUser();
            $('#exampleModalUpdate').modal('hide');
        })
    }
)

function getStringFromRoles(array) {
    return array.map(item => item.name.replace("ROLE_", "")).join(',');
}

function getRolesFromString(string) {
    let array = new Array();
    if (string.indexOf('ROLE_USER') >= 0) {
        array.push({'id': 1, 'name': 'ROLE_USER'});
    }
    if (string.indexOf('ROLE_ADMIN') >= 0) {
        array.push({'id': 2, 'name': 'ROLE_ADMIN'});
    }
    return array;
}