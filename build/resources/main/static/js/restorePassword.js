function doSubmit() {
    const newPassword = document.getElementById("newPassword").value;
    if (newPassword !== document.getElementById("newPasswordConfirm").value) {
        alert("Введенные пароли не совпадают")
    } else {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState !== 4)
                return;

            if (this.status === 200) {
                window.location = '/restoreSuccess.html';
                return;
            }
            alert("Неверный пароль! Пароль должен содержать не менее 8 символов, в том числе цифры, прописные и строчные буквы латинского алфавита");
        };
        xhr.open("POST", document.URL, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            newPassword: newPassword
        }));
    }
}

