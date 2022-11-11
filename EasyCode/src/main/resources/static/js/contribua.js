document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#cardLogin");
    const cadastrarConta = document.querySelector("#cardCriarConta");

    const clearFields = () => {
        const fields = document.querySelectorAll('.inputForm')
        fields.forEach(field => field.value = "")
        cadastrarConta.classList.add("sconde");
        loginForm.classList.remove("sconde");
    }


    document.querySelector("#linkCriar").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.add("sconde");
        cadastrarConta.classList.remove("sconde");
    })

    document.querySelector("#linkLogin").addEventListener("click", e => {
        e.preventDefault();
        cadastrarConta.classList.add("sconde");
        loginForm.classList.remove("sconde");
    })


});
