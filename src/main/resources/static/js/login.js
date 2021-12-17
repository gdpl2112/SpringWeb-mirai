let u1 = location.href
let i1 = u1.indexOf("=")
if (i1 > 0) {
    let e = u1.substr(i1 + 1);
    let e0 = $("#login-tips")
    switch (e) {
        case "err":
            e0.css("display", "")
            e0.css("color", "red")
            e0.text("密码或账号错误")
            break
    }
}
let subInput = $("commit-input")
let k1 = false
let k2 = false
subInput.click(function () {
    alert("密码或用户名不能为空")
})
$("#userInput").blur(function () {
    let str = $("#userInput").val()
    let tips = $("#userTips");
    if (str.length === 0) {
        tips.text("用户名不可为空")
        tips.css("display", "")
        k1 = false;
    } else {
        k1 = true
        tips.css("display", "none")
    }
}).focus(function () {
    let tips = $("#userTips");
    tips.css("display", "none")
})

function test() {
    if (k1 && k2) {
        subInput.click(function () {
            let data = $("#login-form")
            axios.post("/login", data).then(function (response) {
                location.href = response.data
            }).catch(function (err) {
                alert(err);
            })
        })
    }
}

$("#passwordInput").blur(function () {
    let str = $("#passwordInput").val()
    let tips = $("#passTips");
    k2 = false
    if (str.length === 0) {
        tips.text("密码不可为空")
        tips.css("display", "")
    } else if (str.length < 6) {
        tips.text("密码长度不低于6位")
        tips.css("display", "")
    } else {
        tips.css("display", "none")
        k2 = true
    }

    test()

}).focus(function () {
    let tips = $("#passTips");
    tips.css("display", "none")
})
