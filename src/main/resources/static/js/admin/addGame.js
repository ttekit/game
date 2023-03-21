window.addEventListener("load", ()=>{
    let $form = $(".add-game-form");
    $form.on("submit", (e)=>{
        let formData = Object.fromEntries(new FormData(e.target));
        console.log(formData);


        $.ajax({
            url: "/admin/rest/addNewGame",
            method: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: (data) => {
                if (data === "success") {
                    // window.location.href = "/admin/editUsers"
                    //TODO: add good alerts
                } else {
                    alert("data");
                }
            },
            error: (xl, er, errMsg) => {
                alert(errMsg)
            }
        })
        // console.log(user)


        return false;

    })
})