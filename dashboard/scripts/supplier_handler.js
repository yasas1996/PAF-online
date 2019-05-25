$("#suppliers_supplierlist").ready(function () {

    $.ajax(`${getHost()}supplier`, {
        contentType: 'application/json',
        type: 'GET'
    }).done(function (response) {
        var newItem = "";
        $.each(response, function (index, value) {

            newItem += `<hr class="soften">
            <div class="row-fluid">
               
                <div class="span6">
                    
                <h5 class="supplier_header_id">${value.suppliername}</h5>
                    <p>
                       Company Name: ${value.companyname}
                    </p>
                    </br>
                    <p>
                       product Item: ${value.productitem}
                    </p>
                    </br>
                    <p>
                        address: ${value.address}
                    </p>
                </div>
                <div class="span4 alignR">
                    <form class="form-horizontal qtyFrm">
                        <div class="btn-group">
                        <button type="button" id="supplier_editProduct_btn" class="btn btn-primary">
                            EDIT
                        </button>
                        <br/>
                        <br/>
                        <br/>
                        <button type="button" id="supplier_deleteSupplier_btn" class="btn btn-danger">
                            DELETE
                        </button>
                        </div>
                    </form>
                </div>
            </div>`;
        });

        $("#suppliers_supplierlist").append(newItem);
    });
});

$('body').on('click', '#supplier_editProduct_btn', function (event) {
    var suppliername = $(event.target).parent().parent().parent().parent().find('.supplier_header_id').html();

    $.ajax(`${getHost()}supplier/${suppliername}`, {
        contentType: 'application/json',
        type: 'GET'
    }).done(function (response) {
        $("#editSupplier_suppliername").val(response.suppliername);
        $("#editSupplier_companyname").val(response.companyname);
        $("#editSupplier_product").val(response.productitem);
        $("#editSupplier_address").val(response.address);
    });

    $('#exampleModal2').modal('show');
});

$('body').on('click', '#supplier_deleteSupplier_btn', function (event) {
    var Suppliername = $(event.target).parent().parent().parent().parent().find('.supplier_header_id').html();

    $.ajax(`${getHost()}supplier/${Suppliername}`, {
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

$("#Supplier_form").submit(function (event) {
    event.preventDefault();

    var requestData = {
        supplierName : $("#editSupplier_suppliername").val(),
        companyName : $("#editSupplier_companyname").val(),
        productItem : $("#editSupplier_product").val(),
        address : $("#editSupplier_address").val()
    };
    console.log(requestData);

    $.ajax(`${getHost()}supplier`, {
                data: JSON.stringify(requestData),
                contentType: 'application/json',
                type: 'PUT'
            }).done(function (response) {
                if (response === true) {
                    location.reload();
                    alert("Edited successfully");
                }
                else {
                    alert("Editing user failed");
                }
            });

    $('#exampleModal2').modal('toggle');
});