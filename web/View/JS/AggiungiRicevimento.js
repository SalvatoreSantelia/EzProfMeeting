$(document).ready(
    function () {
        function addNow() {
            nowDate = moment().tz("Europe/London").format('YYYY-MM-DD');
            nowTime = moment().tz("Europe/London").format('HH:mm:ss');
            document.getElementById('registration-date').value = nowDate;
            document.getElementById('registration-time').value = nowTime;
            set = setTimeout(function () {
                addNow();
            }, 1000);
        }

        function stopNow() {
            clearTimeout(set);
        }

    }
);