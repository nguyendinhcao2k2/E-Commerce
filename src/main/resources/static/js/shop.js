const viewUrl = "http://localhost:8080/api/user";

function searchProduct() {
    let searchValue = $('input[name="searchProduct"]').val();
    let productDiv = $('div[name="product-div"]');
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: viewUrl + "/search-product?searchValue=" + searchValue,
        dataType: 'json',
        success: function (responseData) {
            productDiv.empty();
            $.each(responseData, function (index, pr) {
                let $productItem = $('<div>').addClass('card product-item border-0 mb-4');
                let $cardHeader = $('<div>').addClass('card-header product-img position-relative overflow-hidden bg-transparent border p-0');
                let $img = $('<img>').addClass('img-fluid w-100').attr('style', 'height: 350px').attr('src', '/static/img' + pr.img).attr('alt', '');
                $cardHeader.append($img);

                let $cardBody = $('<div>').addClass('card-body border-left border-right text-center p-0 pt-4 pb-3');
                let $h6ProductName = $('<h6>').addClass('text-truncate mb-3').text(pr.tenSP)
                $cardBody.append($h6ProductName);
                let $priceContainer = $('<div>').addClass('d-flex justify-content-center');
                let $h6Price = $('<h6>').attr('name', 'giaBan').text(pr.giaBan + '.00$');
                $priceContainer.append($h6Price);
                $cardBody.append($priceContainer);

                let $cardFooter = $('<div>').addClass('card-footer d-flex justify-content-between bg-light border');
                let $viewDetailLink = $('<a>').attr('href', "/api/user/view-detail-product/" + pr.id).addClass('btn btn-sm text-dark p-0');
                $viewDetailLink.append($('<i>').addClass('fas fa-eye text-primary mr-1')).text('View Detail');
                $cardFooter.append($viewDetailLink);

                productDiv.eq(index).append($productItem.append($cardHeader, $cardBody, $cardFooter));
            });
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function filterColorAndPrice() {
    let selectedPrice = $("input[name='price']:checked").val();
    let selectedColor = $("input[name='color']:checked").val();
    let productDiv = $('div[name="product-div"]');
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: viewUrl + "/filter-product?price=" + selectedPrice+"&color=" + selectedColor,
        dataType: 'json',
        success: function (responseData) {
            productDiv.empty();
            $.each(responseData, function (index, pr) {
                console.log(pr)
                var $productItem = $('<div>').addClass('card product-item border-0 mb-4');
                var $cardHeader = $('<div>').addClass('card-header product-img position-relative overflow-hidden bg-transparent border p-0');
                var $img = $('<img>').addClass('img-fluid w-100').attr('style', 'height: 350px').attr('src', '/static/img' + pr.img).attr('alt', '');
                $cardHeader.append($img);

                var $cardBody = $('<div>').addClass('card-body border-left border-right text-center p-0 pt-4 pb-3');
                var $h6ProductName = $('<h6>').addClass('text-truncate mb-3').text(pr.tenSP)
                $cardBody.append($h6ProductName);
                var $priceContainer = $('<div>').addClass('d-flex justify-content-center');
                var $h6Price = $('<h6>').attr('name', 'giaBan').text(pr.giaBan + '.00$');
                $priceContainer.append($h6Price);
                $cardBody.append($priceContainer);

                var $cardFooter = $('<div>').addClass('card-footer d-flex justify-content-between bg-light border');
                var $viewDetailLink = $('<a>').attr('href', "/api/user/view-detail-product/" + pr.id).addClass('btn btn-sm text-dark p-0');
                $viewDetailLink.append($('<i>').addClass('fas fa-eye text-primary mr-1')).text('View Detail');
                $cardFooter.append($viewDetailLink);

                productDiv.eq(index).append($productItem.append($cardHeader, $cardBody, $cardFooter));
            });
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}