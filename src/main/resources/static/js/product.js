const viewUrl = "http://localhost:8080/api/admin";

function saveProduct() {
    let shoeSize = $('input[name="shoesize"]:checked').map(function () {
        return $(this).val();
    }).get();
    let clothingSize = $('input[name="clothingsize"]:checked').map(function () {
        return $(this).val();
    }).get();
    let colors = $('input[name="color"]:checked').map(function () {
        return $(this).val();
    }).get();
    // Lấy giá trị từ các trường dữ liệu
    let tenSP = document.getElementById("tenSP").value;
    let price = document.getElementById("price").value;
    let amount = document.getElementById("amount").value;
    let image = document.getElementById("image").files[0];
    let category = document.getElementById("category").value;
    let season = document.getElementById("season").value;
    let description = document.getElementById("description").value;

    //Lấy giá trị error field
    let error_tenSP = document.getElementById("error_tenSP");
    let error_price = document.getElementById("error_price");
    let error_amount = document.getElementById("error_amount");
    let error_image = document.getElementById("error_image");
    let error_description = document.getElementById("error_description");
    let error_shoeSize = document.getElementById("error_shoezise");
    let error_clothingSize = document.getElementById("error_clothingsize");
    let error_color = document.getElementById("error_color");

    let check = true;

    if (tenSP.trim() === '') {
        error_tenSP.textContent = "Tên sản phẩm không được để trống ";
        check = false;
    } else if (tenSP.length <= 6) {
        error_tenSP.textContent = "Tên sản phẩm phải lớn hơn 6 ký tự";
        check = false;
    } else {
        error_tenSP.textContent = "";
    }

    if (description.trim() === '') {
        error_description.textContent = "Description không được để trống ";
        check = false;
    } else if (description.length <= 10) {
        error_description.textContent = "Description phải lớn hơn 10 ký tự ";
        check = false;
    } else {
        error_description.textContent = "";
    }

    if (amount.trim() === '') {
        error_amount.textContent = "Amount không được để trống";
        check = false;
    } else if (parseInt(amount) <= 0) {
        error_amount.textContent = "Amount phải lớn hơn 0";
        check = false;
    } else {
        error_amount.textContent = "";
    }

    if (price.trim() === '') {
        error_price.textContent = "Price không được để trống";
        check = false;
    } else if (parseInt(price) <= 0) {
        error_price.textContent = "Price phải lớn hơn 0";
        check = false;
    } else {
        error_price.textContent = "";
    }

    if (!image) {
        error_image.textContent = "Image không được để trống";
        check = false;
    } else {
        error_image.textContent = "";
    }

    if (shoeSize.length === 0 && clothingSize.length === 0) {
        error_shoeSize.textContent = "Vui lòng chọn Shoe Size hoặc Clothing Size";
        error_clothingSize.textContent = "Vui lòng chọn Shoe Size hoặc Clothing Size";
        check = false;
    } else {
        error_shoeSize.textContent = "";
        error_clothingSize.textContent = "";
    }

    if (shoeSize.length > 0 && clothingSize.length > 0) {
        error_shoeSize.textContent = "Chỉ được chọn Shoes Size hoặc Clothing Size";
        error_clothingSize.textContent = "Chỉ được chọn Shoes Size hoặc Clothing Size";
        check = false;
    }

    if (colors.length === 0) {
        error_color.textContent = "Vui lòng chon color"
        check = false;
    } else {
        error_color.textContent = ""
    }
    if (check) {
        let formData = new FormData();
        formData.append("tenSP", tenSP);
        formData.append("price", price);
        formData.append("amount", amount);
        formData.append("image", image);
        formData.append("category", category);
        formData.append("season", season);
        formData.append("description", description);
        formData.append("shoeSize", shoeSize);
        formData.append("clothingSize", clothingSize);
        formData.append("colors", colors);

        $.ajax({
            type: "POST",
            url: viewUrl + "/save-product",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                alert(response);
                $('#addProduct').modal('hide');
                window.location.href = "/api/admin/home"
            },
            error: function (xhr, status, error) {
                // Xử lý lỗi
                console.error(error);
            }
        });
    }
}

function deleteProduct(id) {
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: viewUrl + "/delete-product/" + id,
        success: function (responseData) {
            alert(responseData);
            window.location.href = "/api/admin/home"
        },
        error: function (xhr) {
            alert(xhr.responseText);
            $('#deleteProduct').modal('hide');
        }
    });
}

function updateProduct(id) {
// Lấy giá trị từ các trường dữ liệu
    let tenSP = document.getElementById("tenSP").value;
    let price = document.getElementById("price").value;
    let amount = document.getElementById("amount").value;
    let image = document.getElementById("image").files[0];
    let category = document.getElementById("category").value;
    let season = document.getElementById("season").value;
    let description = document.getElementById("description").value;

    //Lấy giá trị error field
    let error_tenSP = document.getElementById("error_tenSP");
    let error_price = document.getElementById("error_price");
    let error_amount = document.getElementById("error_amount");
    let error_image = document.getElementById("error_image");
    let error_description = document.getElementById("error_description");


    let check = true;

    if (tenSP.trim() === '') {
        error_tenSP.textContent = "Tên sản phẩm không được để trống ";
        check = false;
    } else if (tenSP.length <= 6) {
        error_tenSP.textContent = "Tên sản phẩm phải lớn hơn 6 ký tự";
        check = false;
    } else {
        error_tenSP.textContent = "";
    }

    if (description.trim() === '') {
        error_description.textContent = "Description không được để trống ";
        check = false;
    } else if (description.length <= 10) {
        error_description.textContent = "Description phải lớn hơn 10 ký tự ";
        check = false;
    } else {
        error_description.textContent = "";
    }

    if (amount.trim() === '') {
        error_amount.textContent = "Amount không được để trống";
        check = false;
    } else if (parseInt(amount) <= 0) {
        error_amount.textContent = "Amount phải lớn hơn 0";
        check = false;
    } else {
        error_amount.textContent = "";
    }

    if (price.trim() === '') {
        error_price.textContent = "Price không được để trống";
        check = false;
    } else if (parseInt(price) <= 0) {
        error_price.textContent = "Price phải lớn hơn 0";
        check = false;
    } else {
        error_price.textContent = "";
    }

    if (!image) {
        error_image.textContent = "Image không được để trống";
    } else {
        error_image.textContent = "";
    }

    if (check) {
        let formData = new FormData();
        formData.append("tenSP", tenSP);
        formData.append("price", price);
        formData.append("amount", amount);
        formData.append("image", image);
        formData.append("category", category);
        formData.append("season", season);
        formData.append("description", description);

        $.ajax({
            type: "PUT",
            url: viewUrl + "/update-product/" + id,
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                alert(response);
                window.location.href = "/api/admin/home"
            },
            error: function (xhr, status, error) {
                // Xử lý lỗi
                console.error(error);
            }
        });
    }
}