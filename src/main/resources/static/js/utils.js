function jsDateFormatter(dateInput) {
    let year = dateInput.getFullYear();
    let month = dateInput.getMonth() + 1;
    let theDate = dateInput.getDate();

    let hour = dateInput.getHours();
    let minute = dateInput.getMinutes();
    let second = dateInput.getSeconds();

    if (month < 10) {
        month = '0' + month;
    }

    if (theDate < 10) {
        theDate = '0' + theDate;
    }
    if (hour < 10) {
        hour = '0' + hour;
    }
    if (minute < 10) {
        minute = '0' + minute;
    }
    if (second < 10) {
        second = '0' + second;
    }
    return year + "-" + month + "-" + theDate + " " + hour + ":" + minute + ":" + second;
}