window.addEventListener("load", () => {
    let regForm = $(".register-form");

    regForm.submit(() => {
        let user = {};

        user.username = regForm.find("input[name = 'username']").val();
        user.password = regForm.find("input[name = 'password']").val();
        user.passwordConfirm = regForm.find("input[name = 'passwordConfirm']").val();
        user.email = regForm.find("input[name = 'email']").val();
        let error = "";


        if (user.username.length < 4 || user.username.length > 30) {
            error += "Username name must to be from 4 to 30 letters \n"
        }
        if (user.email.length < 5 || user.email.length > 25) {
            error += "Email is incorrect \n"
        }
        if (user.password.length < 5 || user.password.length > 18) {
            error += "Password must to be from 5 to 18 letters \n"
        }
        if (user.password !== user.passwordConfirm) {
            error += "Passwords are not the same \n"
        }
        if (error !== "") {
            alert(error);
        } else {
            $.ajax({
                url: "/admin/rest/submitRegister",
                method: "post",
                contentType: "application/json",
                data: JSON.stringify(user),
                success: (data) => {
                    if (data === "success") {
                        window.location.href = "/games"
                    } else {
                        alert(data);
                    }
                },
                error: (xl, er, errMsg) => {
                    alert(er)
                }
            })
        }
        return false;
    })
});