const viewUrl = "http://localhost:8080/api/admin";
let idGH = "";

function cancel(id) {
    $('#cancel').modal('show');
    idGH = id;
}

function confrimPayment(id) {
    $('#paymentConfirm').modal('show');
    idGH = id;
}

function shipping(id){
    $('#shipping').modal('show');
    idGH = id;
}

$('[name="cancelModel"]').click(function () {
    if (idGH.length > 0) {
        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: viewUrl + "/cancel/" + idGH,
            success: function (responseData) {
                alert(responseData);
                window.location.href = "/api/admin/hoa-don"
                $('#cancel').modal('hide');
            },
            error: function (xhr) {
                alert(xhr.responseText);
                $('#cancel').modal('hide');
            }
        });
    }
});

$('[name="confirmModel"]').click(function () {
    if (idGH.length > 0) {
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: viewUrl + "/confirm/" + idGH,
            success: function (responseData) {
                alert(responseData);
                window.location.href = "/api/admin/hoa-don"
                $('#paymentConfirm').modal('hide');
            },
            error: function (xhr) {
                alert(xhr.responseText);
                $('#paymentConfirm').modal('hide');
            }
        });
    }
});

$('[name="shippingbtn"]').click(function () {
    if (idGH.length > 0) {
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: viewUrl + "/shipping/" + idGH,
            success: function (responseData) {
                alert(responseData);
                window.location.href = "/api/admin/hoa-don"
                $('#shipping').modal('hide');
            },
            error: function (xhr) {
                alert(xhr.responseText);
                $('#shipping').modal('hide');
            }
        });
    }
});


function detailGH(id) {
    let tbody = $('tbody[name="body"]');
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: viewUrl + "/detail-hoa-don/" + id,
        success: function (responseData) {
            tbody.empty();
            $.each(responseData, function (index, ctgh) {
                let $tr = $('<tr>');
                let $td1 = $('<td>').addClass('align-middle').text(index + 1);
                let $td2 = $('<td>').addClass('align-middle').text(ctgh.tenSP);
                let $td3 = $('<td>').addClass('align-middle').text(ctgh.color);
                let $td4 = $('<td>').addClass('align-middle').text(ctgh.size);
                let $td5 = $('<td>').addClass('align-middle').text(ctgh.donGia);
                let $td6 = $('<td>').addClass('align-middle').text(ctgh.soLuong);
                let $td7 = $('<td>').addClass('align-middle').text(ctgh.donGia * ctgh.soLuong + "$");
                $tr.append($td1, $td2, $td3, $td4, $td5, $td6, $td7);
                tbody.append($tr)
            });
            $('#chiTietGH').modal('show');
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
