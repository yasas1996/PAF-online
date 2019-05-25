$("#users_userlist").ready(function () {

    $.ajax(`${getHost()}user`, {
        contentType: 'application/json',
        type: 'GET'
    }).done(function (response) {
        var newItem = "";
        $.each(response, function (index, value) {

            newItem += `<hr class="soften">
            <div class="row-fluid">
                <div class="span2">
                    <img src="${value.profilePicture}" alt="" height="130" width="100">
                </div>
                <div class="span6">
                    <h5 class="username_header_id">${value.username}</h5>
                    <p>
                        ${value.email}
                    </p>
                    </br>
                    <p>
                        ${value.contactNo}
                    </p>
                </div>
                <div class="span4 alignR">
                    <form class="form-horizontal qtyFrm">
                        <h3> ${value.userType}</h3><br>
                        <br>
                        <div class="btn-group">
                        
                        <button type="button" id="user_deleteUser_btn" class="btn btn-danger">
                            DELETE
                        </button>
                        </div>
                    </form>
                </div>
            </div>`;
        });

        $("#users_userlist").append(newItem);
    });
});


$('body').on('click', '#user_deleteUser_btn', function (event) {
    var username = $(event.target).parent().parent().parent().parent().find('.username_header_id').html();

    $.ajax(`${getHost()}user/${username}`, {
        contentType: 'application/json',
        type: 'DELETE'
    }).done(function (response) {
        location.reload();
        if (response) {
            alert("Successfully Deleted");
        } else {
            alert("Delete Failed");
        }
    });
});