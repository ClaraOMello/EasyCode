document.addEventListener("DOMContentLoaded", () => {
    const showLing = document.querySelector("#areaLig");
    const showTop = document.querySelector("#areaTop");

    document.querySelector("#btnLing").addEventListener("click", e => {
        e.preventDefault();
        showTop.classList.add("sconde");
        showLing.classList.remove("sconde");
    })

    document.querySelector("#btnTop").addEventListener("click", e => {
        e.preventDefault();
        showLing.classList.add("sconde");
        showTop.classList.remove("sconde");
    })

    const ediletarLing = (event) => {
        if (event.target.type == 'button') {
            console.log("alo 2")
            const [action, id] = event.target.value.split('-')
            if (action == 'editarL') {
                document.getElementById('inserirLing').action = "/perfilUser/lingUp/" + id;
                document.querySelector('#inserirLing>#IDLing').value = id;
                document.querySelector('#inserirLing>#nomeLing').value = document.querySelector(`#conteudotable>#nome${CSS.escape(id)}`).textContent
                document.querySelector('#inserirLing>#imgLing').value = document.querySelector(`#conteudotable>#img${CSS.escape(id)}`).textContent
            }
            if (action == 'deletarL') {
                console.log("alo 3")
            }
        }
    }

    document.querySelector('#tbCursosL>tbody')
        .addEventListener('click', ediletarLing)
});