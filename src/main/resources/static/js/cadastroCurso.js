'use strict'

const conteudos = [
    {
        topico: "O Inicio de C",
        autor: "EasyCode",
        lingua: "C",
        conteudo: "<p>Em breve...</p>",
        link: "conteudoDaLinguagem.html?principal=lingC&id=lingCtop1",
        status:"Online!",
        exercicios: "",
    },
    {
        topico: "Introdução a CSS",
        autor: "EasyCode",
        lingua: "CSS",
        conteudo: "<p>Em breve...</p>",
        link: "conteudoDaLinguagem.html?principal=lingCSS&id=lingCtop1",
        status:"Online!",
        exercicios: "<p>Em breve...</p>",
    },
    {
        topico: "Sobre C#",
        autor: "EasyCode",
        lingua: "C#",
        conteudo: "<p>Em breve...</p>",
        link: "conteudoDaLinguagem.html?principal=lingC#&id=lingCtop1",
        status:"Online!",
        exercicios: "<p>Em breve...</p>",
    },
    {
        topico: "Um pouco sobre C++",
        autor: "EasyCode",
        lingua: "C++",
        conteudo: "<p>Em breve...</p>",
        link: "conteudoDaLinguagem.html?principal=lingCpp&id=lingCtop1",
        status:"Online!",
        exercicios: "<p>Em breve...</p>",
    },
    {
        topico: "Início de HTML",
        autor: "EasyCode",
        lingua: "HTML",
        conteudo: "<p>Em breve...</p>",
        link: "conteudoDaLinguagem.html?principal=lingHTML&id=lingCtop1",
        status:"Online!",
        exercicios: "<p>Em breve...</p>",
    },
    {
        topico: "A mordida da Python",
        autor: "EasyCode",
        lingua: "Python",
        conteudo: "<p>Em breve...</p>",
        link: "conteudoDaLinguagem.html?principal=lingPython&id=lingCtop1",
        status:"Online!",
        exercicios: "<p>Em breve...</p>",
    },
    {
        topico: "JAVAli assado",
        autor: "EasyCode",
        lingua: "Java",
        conteudo: "<p>Em breve...</p>",
        link: "conteudoDaLinguagem.html?principal=lingJAVA&id=lingCtop1",
        status:"Online!",
        exercicios: "<p>Em breve...</p>",
    },
    {
        topico: "Introdução a JS",
        autor: "EasyCode",
        lingua: "JavaScript",
        conteudo: "<p>Em breve...</p>",
        status:"Online!",
        exercicios: "<p>Em breve...</p>",
    },
]

const getLocalStorage = () => JSON.parse(localStorage.getItem('dbCurso')) ?? conteudos
const setLocalStorage = (dbCurso) => localStorage.setItem("dbCurso", JSON.stringify(dbCurso))

// CRUD - create read update delete

//Faz o C de Cellbit cabeludo
const createCurso = (curso) => {
    const dbCurso = getLocalStorage()
    dbCurso.push(curso)
    setLocalStorage(dbCurso)
}

//Faz o R de Roraima abraço pra galera de Roraima
const readCurso = () => getLocalStorage()

//Faz o U de Uma Esposa de Mentirinha
const updateCurso = (index, curso) => {
    const dbCurso = readCurso()
    dbCurso[index] = curso
    setLocalStorage(dbCurso)
}

//Faz o D de DaVinci um dos melhores pilotos de kart do mundo
const deleteCurso = (index) => {
    const dbCurso = readCurso()
    dbCurso.splice(index, 1)
    setLocalStorage(dbCurso)
}

//Fim CRUD :(

const isValidFields = () => {
    return document.getElementById('frmCadastro').reportValidity()
}

//Interação com Layout
const clearFields = () => {
    const fields = document.querySelectorAll('.form-control')
    fields.forEach(field => field.value = "")
}

const cadastrarCurso = () => {
    if (isValidFields()) {
        const curso = {
            topico: document.getElementById('inputTitulo').value,
            autor: document.getElementById('inputAutor').value,
            lingua: document.getElementById('inputLingua').value,
            conteudo: "<p>Em breve...</p>",
            link: "#",
            status: "Offline",
            exercicios: "<p>Em breve...</p>",
            }
        const index = document.getElementById('inputTitulo').dataset.index
        if (index == 'new') {
            createCurso(curso)
            updateTable()
        } else {
            updateCurso(index, curso)
            updateTable()
        }

        console.log("Cadastrando...")
        clearFields()
    }
}

const createRow = (curso, index) => {
    const newRow = document.createElement('tr')
    newRow.setAttribute("id", "conteudotable");
    newRow.classList.add('table-hover')
    newRow.innerHTML = `
        <th scope="col" class="fs-4">${index + 1}°</th>
        <td class="tituloTabela">${curso.topico}</td>
        <td class="autorTabela">${curso.autor}</td>
        <td class="linguaTabela">${curso.lingua}</td>
        <td class="text-muted linkTabela"><a href="${curso.link}"><p>${curso.status}</p></a></td>
        <td class="botoesTabela>
        <div class="form-group">
        <button type="button" value="editar-${index}" id="btnEditar"
        class="btn" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
        <i class="fa-solid fa-pen"></i> Editar
        </button>
        </div>
        <div class="form-group">
        <button type="button" value="deletar-${index}" id="btnDeletar"
        class="btn">
        <i class="fa-solid fa-trash"></i> Excluir
        </button>
        </div></td>
        `
    document.querySelector('#tbCursos>tbody').appendChild(newRow)
}

const clearTable = () => {
    const rows = document.querySelectorAll('#tbCursos>tbody tr')
    rows.forEach(row => row.parentNode.removeChild(row))
}

const updateTable = () => {
    const dbCurso = readCurso()
    clearTable()
    dbCurso.forEach(createRow)
    document.getElementById('inputTitulo').dataset.index = 'new'
}

const fillCampos = (curso) => {
    document.getElementById('inputTitulo').value = curso.topico
    document.getElementById('inputAutor').value = curso.autor
    document.getElementById('inputLingua').value = curso.lingua
    document.getElementById('inputTitulo').dataset.index = curso.index
}

const editarCurso = (index) => {
    const curso = readCurso()[index]
    curso.index = index
    fillCampos(curso)
}

const ediletarCurso = (event) => {
    if (event.target.type == 'button') {
        const [action, index] = event.target.value.split('-')
        if (action == 'editar') {
            editarCurso(index)
        }
        if (action == 'deletar') {
            const curso = readCurso()[index]
            const response = confirm(`Deseja excluir o curso ${curso.topico}?`)
            if (response) {
                deleteCurso(index)
                updateTable()
            }
        }
    }
}



//Eventos
document.addEventListener("DOMContentLoaded", () => {
    updateTable()
});
document.getElementById('btnCadastrar')
  .addEventListener('click', cadastrarCurso)

document.querySelector('#tbCursos>tbody')
    .addEventListener('click', ediletarCurso)
