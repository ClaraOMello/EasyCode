document.addEventListener("DOMContentLoaded", () => {
    document.getElementById('descricaoUser').innerHTML = `${JSON.parse(sessionStorage.getItem('userAtual')).descricao}`
    document.getElementById('nomeUser').innerHTML = `${JSON.parse(sessionStorage.getItem('userAtual')).nome }`
    document.getElementById('tipoUser').innerHTML = `${JSON.parse(sessionStorage.getItem('userAtual')).tipo}`
    document.getElementById('anoUser').innerHTML = `${JSON.parse(sessionStorage.getItem('userAtual')).ano}`
    const deslogar = () => {
        sessionStorage.setItem("isLogged", "Off")
        sessionStorage.setItem("userAtual", "")
        window.location.href = "contribua.html";

        document.getElementById('btnTestinho')
            .addEventListener('click', deslogar)

    }
    if (sessionStorage.getItem('isLogged') == "Off") {
        deslogar();
    }
    document.getElementById('btnTestinho')
        .addEventListener('click', deslogar)
});
