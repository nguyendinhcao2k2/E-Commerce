const viewUrl = "http://localhost:8080/api/user";

function addToCart(id) {
    let selectedColor = $('input[name="color"]:checked').val();
    let selectedSize = $('input[name="size"]:checked').val();
    let amount = $('#amount').val();

    if (!selectedSize || selectedColor === undefined) {
        alert("Vui lòng chọn Size và Color")
    } else if (amount < 1) {
        alert("Vui lòng chọn số lượng lớn hơn 1");
    } else {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: viewUrl + "/add-to-cart?color=" + selectedColor + "&size=" + selectedSize + "&amount=" + amount + "&id=" + id,
            success: function (responseData) {
                alert(responseData);
                window.location.href = "/api/user/cart"
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }
}

function deleteCart(id, size, color) {
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: viewUrl + "/delete-cart?color=" + color + "&size=" + size + "&id=" + id,
        success: function (responseData) {
            alert(responseData);
            window.location.href = "/api/user/cart"
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
}

function minus(id, size, color, index) {
    let amountInput = $('input[name="amount' + index + '"]');
    let amount = parseInt(amountInput.val()) - 1;
    let trGH = $('tr[name="trGH"]');
    if (amount <= 0) {
        amountInput.val(1);
        alert("Số lượng phải lớn hơn 0")
    } else {
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: viewUrl + "/update-amount-cart?color=" + color + "&size=" + size + "&amount=" + amount + "&id=" + id,
            dataType: 'json',
            success: function (responseData) {
                let sumPrice = 0.0;
                trGH.empty();
                $.each(responseData, function (index, gh) {
                    let $td1 = $('<td>').addClass('align-middle').text(gh.chiTietSP.tenSP);
                    let $img = $('<img>').attr('src', '/static/img' + gh.chiTietSP.img).attr('alt', '').attr('style', 'width: 50px;');
                    $td1.append($img);

                    let $td2 = $('<td>').addClass('align-middle').text(gh.chiTietSP.giaBan);

                    let $td3 = $('<td>').addClass('align-middle');
                    let $quantityDiv = $('<div>').addClass('input-group quantity mx-auto').attr('style', 'width: 100px;');
                    let $btnMinus = $('<button>').attr('onclick', 'minus(' + JSON.stringify(gh.chiTietSP.id) + ',' + JSON.stringify(gh.sizeName) + ',' + JSON.stringify(gh.color_name) + ',' + index + ')').addClass('btn btn-sm btn-primary btn-minus');
                    $btnMinus.append($('<i>').addClass('fa fa-minus'));
                    $quantityDiv.append($('<div>').addClass('input-group-btn').append($btnMinus));
                    let $inputAmount = $('<input>').attr('type', 'text').attr('name', 'amount' + index).addClass('form-control form-control-sm bg-secondary text-center').val(gh.soLuong);
                    $quantityDiv.append($inputAmount);
                    let $btnPlus = $('<button>').attr('onclick', 'plusAmount(' + JSON.stringify(gh.chiTietSP.id) + ',' + JSON.stringify(gh.sizeName) + ',' + JSON.stringify(gh.color_name) + ',' + index + ')').addClass('btn btn-sm btn-primary btn-plus');
                    $btnPlus.append($('<i>').addClass('fa fa-plus'));
                    $quantityDiv.append($('<div>').addClass('input-group-btn').append($btnPlus));
                    $td3.append($quantityDiv);

                    let $td4 = $('<td>').addClass('align-middle').text(gh.sizeName);
                    let $td5 = $('<td>').addClass('align-middle').text(gh.color_name);
                    let $td6 = $('<td>').addClass('align-middle').text(gh.soLuong * gh.chiTietSP.giaBan + "$");

                    let $td7 = $('<td>').attr('onclick', 'deleteCart(' + JSON.stringify(gh.chiTietSP.id) + ',' + JSON.stringify(gh.sizeName) + ',' + JSON.stringify(gh.color_name) + ')').addClass('align-middle');
                    let $deleteButton = $('<button>').addClass('btn btn-sm btn-primary');
                    $deleteButton.append($('<i>').addClass('fa fa-times'));
                    $td7.append($deleteButton);

                    trGH.eq(index).append($td1, $td2, $td3, $td4, $td5, $td6, $td7);
                    sumPrice += gh.soLuong * gh.chiTietSP.giaBan;
                });
                $('#sumPrice').text(sumPrice + '$');
                $('#totalPrice').text(sumPrice + 10 + '$');
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }
}

function plusAmount(id, size, color, index) {
    let amountInput = $('input[name="amount' + index + '"]');
    let amount = parseInt(amountInput.val()) + 1;
    let trGH = $('tr[name="trGH"]');
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: viewUrl + "/update-amount-cart?color=" + color + "&size=" + size + "&amount=" + amount + "&id=" + id,
        dataType: 'json',
        success: function (responseData) {
            let sumPrice = 0.0;
            trGH.empty();
            $.each(responseData, function (index, gh) {
                let $td1 = $('<td>').addClass('align-middle').text(gh.chiTietSP.tenSP);
                let $img = $('<img>').attr('src', '/static/img' + gh.chiTietSP.img).attr('alt', '').attr('style', 'width: 50px;');
                $td1.append($img);

                let $td2 = $('<td>').addClass('align-middle').text(gh.chiTietSP.giaBan);

                let $td3 = $('<td>').addClass('align-middle');
                let $quantityDiv = $('<div>').addClass('input-group quantity mx-auto').attr('style', 'width: 100px;');
                let $btnMinus = $('<button>').attr('onclick', 'minus(' + JSON.stringify(gh.chiTietSP.id) + ',' + JSON.stringify(gh.sizeName) + ',' + JSON.stringify(gh.color_name) + ',' + index + ')').addClass('btn btn-sm btn-primary btn-minus');
                $btnMinus.append($('<i>').addClass('fa fa-minus'));
                $quantityDiv.append($('<div>').addClass('input-group-btn').append($btnMinus));
                let $inputAmount = $('<input>').attr('type', 'text').attr('name', 'amount' + index).addClass('form-control form-control-sm bg-secondary text-center').val(gh.soLuong);
                $quantityDiv.append($inputAmount);
                let $btnPlus = $('<button>').attr('onclick', 'plusAmount(' + JSON.stringify(gh.chiTietSP.id) + ',' + JSON.stringify(gh.sizeName) + ',' + JSON.stringify(gh.color_name) + ',' + index + ')').addClass('btn btn-sm btn-primary btn-plus');
                $btnPlus.append($('<i>').addClass('fa fa-plus'));
                $quantityDiv.append($('<div>').addClass('input-group-btn').append($btnPlus));
                $td3.append($quantityDiv);

                let $td4 = $('<td>').addClass('align-middle').text(gh.sizeName);
                let $td5 = $('<td>').addClass('align-middle').text(gh.color_name);
                let $td6 = $('<td>').addClass('align-middle').text(gh.soLuong * gh.chiTietSP.giaBan + "$");

                let $td7 = $('<td>').attr('onclick', 'deleteCart(' + JSON.stringify(gh.chiTietSP.id) + ',' + JSON.stringify(gh.sizeName) + ',' + JSON.stringify(gh.color_name) + ')').addClass('align-middle');
                let $deleteButton = $('<button>').addClass('btn btn-sm btn-primary');
                $deleteButton.append($('<i>').addClass('fa fa-times'));
                $td7.append($deleteButton);

                trGH.eq(index).append($td1, $td2, $td3, $td4, $td5, $td6, $td7);
                sumPrice += gh.soLuong * gh.chiTietSP.giaBan;
            });
            $('#sumPrice').text(sumPrice + '$');
            $('#totalPrice').text(sumPrice + 10 + '$');
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

