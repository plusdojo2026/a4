document.addEventListener('DOMContentLoaded', () => {

    const form = document.querySelector("form");
    const cowIdInput = document.getElementById("cow_id");
    const errorMessage = document.getElementById("error_message");

    cowIdInput.addEventListener("input", function () {

        this.value = this.value.replace(/[^0-9]/g, "");

        errorMessage.textContent = "";
    });

    form.addEventListener("submit", function (e) {

        const id = cowIdInput.value;

        if (!/^[0-9]{10}$/.test(id)) {

            e.preventDefault();

            if (id.length == 0) {
                errorMessage.textContent = "ウシIDを入力してください";
            } else {
                errorMessage.textContent = "ウシIDは半角数字10桁で入力してください";
            }

            cowIdInput.focus();
        }

    });

});