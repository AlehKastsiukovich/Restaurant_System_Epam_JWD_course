var check = function () {
    if (document.getElementById('password').value ==
        document.getElementById('password_repeat').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'correct';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'not matching';
    }
}


