window.addEventListener("load", () => {
    let button = $(".game-time-limit");
    let time = button.text();
    let count = 0;
    let $timer = $(".timer");
    let $timeLeft = $(".time-left");
    let step = 1;
    let gameId = $(".game-id").text();
    console.log(gameId);
    let startButton = $(".game");

    startButton.on("click", (e) => {
        startTimer(e.target);
    })

    function startTimer() {
        startButton.off("click")
        startButton.on("click", () => {
            count += step;
            $timer.text(count);
        })
        let interval = setInterval(() => {
            time -= 0.01;
            $timeLeft.text(time.toFixed(2));
            if (time <= 0) {

                startButton.off("click");
                $timer.text("Times up! Your result: " + count);
                $timeLeft.text("0");

                let data = new FormData();
                data.set("gameId", gameId);
                data.set("result", count);

                sendDataToTheServer(data);
                clearInterval(interval);
            }
        }, 10);
    }

    function sendDataToTheServer(formData) {
        $.ajax({
            url: "/game/rest/saveResult",
            method: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: (data) => {
                if (data != "success") {
                    alert("server doesnt work (");
                }
            },
            error: () => {
                alert("error");
            }
        })
    }
})