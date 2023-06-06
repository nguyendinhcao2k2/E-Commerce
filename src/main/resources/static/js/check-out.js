const viewUrl = "http://localhost:8080/api/user";

function checkOut() {
    let firstName = $('#firstName').val();
    let lastName = $('#lastName').val();
    let email = $('#email').val();
    let address = $('#address').val();
    let phoneNumber = $('#phoneNumber').val();
    let note = $('#note').val();


    let errorFirstName = $('#error_first_name');
    let errorLastName = $('#error_last_name');
    let errorEmail = $('#error_email');
    let errorAddress = $('#error_address');
    let errorPhoneNumber = $('#error_phone_number');

    let check = true;

    if (firstName.trim() === '') {
        errorFirstName.text("Please enter your first name");
        check = false;
    } else if (firstName.length <= 4) {
        errorFirstName.text("First name must be longer than 4 characters");
        check = false;
    } else if (/\d/.test(firstName)) {
        errorFirstName.text("First name cannot contain numbers");
        check = false;
    } else {
        errorFirstName.text("");
    }

    if (lastName.trim() === '') {
        errorLastName.text("Please enter your last name");
        check = false;
    } else if (lastName.length <= 2) {
        errorLastName.text("Last name must be longer than 2 characters");
        check = false;
    } else if (/\d/.test(lastName)) {
        errorLastName.text("Last name cannot contain numbers");
        check = false;
    } else {
        errorLastName.text("");
    }

    if (email.trim() === '') {
        errorEmail.text("Please enter your Email");
        check = false;
    } else if (!validateEmail(email)) {
        errorEmail.text("Please enter valid email");
        check = false;
    } else {
        errorEmail.text("");
    }

    if (address.trim() === '') {
        errorAddress.text("Please enter your Adrress");
        check = false;
    } else if (address.length <= 10) {
        errorAddress.text("Address  must be longer than 10 characters");
        check = false;
    } else {
        errorAddress.text("");
    }

    if (phoneNumber.trim() === '') {
        errorPhoneNumber.text("Please enter your phone number");
        check = false;
    } else if (!validatePhoneNumber(phoneNumber)) {
        errorPhoneNumber.text("Please enter valid phone number");
        check = false;
    } else {
        errorAddress.text("");
    }

    if (check) {
        let cartInfo = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            address: address,
            phoneNumber: phoneNumber,
            note: note
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: viewUrl + "/check-out-cart",
            data: JSON.stringify(cartInfo),
            dataType: "json",
            success: function (response) {
                alert(response.data);
                window.location.href = "/api/user/home"
            },
            error: function (e) {
                alert("Error")
                console.log("ERROR:", e);
            }
        });
    }
}

// Hàm kiểm tra định dạng email
function validateEmail(email) {
    let emailRegex = /\S+@\S+\.\S+/;
    return emailRegex.test(email);
}

function validatePhoneNumber(phoneNumber) {
    // Loại bỏ các ký tự không phải số
    let cleanedPhoneNumber = phoneNumber.replace(/\D/g, '');

    // Kiểm tra độ dài số điện thoại và định dạng
    let phoneNumberRegex = /^0\d{9}$/;
    return phoneNumberRegex.test(cleanedPhoneNumber);
}
