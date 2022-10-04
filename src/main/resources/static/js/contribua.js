
const loginCorreto = () => {
    sessionStorage.setItem("isLogged", "On")
    window.location.href = "perfilUser.html";
}

document.addEventListener("DOMContentLoaded", () => {
    if (sessionStorage.getItem('isLogged') == "On") {
        loginCorreto();
    }
    const loginForm = document.querySelector("#cardLogin");
    const cadastrarConta = document.querySelector("#cardCriarConta");

    const userPadrao = [
        {
            usuario: "admin1",
            senha: "senhaDaoraLegal",
            descricao: "Olá! Tenho 18 anos e sou um estudante de Ciências da Computação, após muito tempo tentando decidir.  A EasyCode foi fundamental na minha escolha, e me prepara hoje para um futuro na área de programação.",
            nome: "Kauã",
            tipo: "Admin",
            ano: "2022",
        },
        {
            usuario: "admin2",
            senha: "123321",
            descricao: "Oi! Estou aqui para ajudar a EasyCode a se tornar ainda melhor.",
            nome: "Murilo",
            tipo: "Admin",
            ano: "2022",
        },
        {
            usuario: "user1",
            senha: "amityBright",
            descricao: "Salve turma! Espero aprender muito aqui! Aquele icone do Youtube ali é legal né... 🕺",
            nome: "Vitória",
            tipo: "Usuário",
            ano: "2022",
        },

    ];

    const getLocalStorage = () => JSON.parse(localStorage.getItem('dbUser')) ?? userPadrao
    const setLocalStorage = (dbUser) => localStorage.setItem("dbUser", JSON.stringify(dbUser))

    const createUser = (User) => {
        const dbUser = getLocalStorage()
        dbUser.push(User)
        setLocalStorage(dbUser)
    }

    const readUser = () => getLocalStorage()

    const clearFields = () => {
        const fields = document.querySelectorAll('.inputForm')
        fields.forEach(field => field.value = "")
        cadastrarConta.classList.add("sconde");
        loginForm.classList.remove("sconde");
    }

    const testValidade = (usuarioLogin) => {
        var i = 0;
        const dbUser = readUser();
        for (const userDB of dbUser) {
            if (usuarioLogin.usuario != userDB.usuario || usuarioLogin.senha != userDB.senha) {
            } else if (usuarioLogin.usuario == userDB.usuario && usuarioLogin.senha == userDB.senha) {
                sessionStorage.setItem("userAtual", JSON.stringify(userDB))
                loginCorreto();
            }
            i++;
        }
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

    cadastrarConta.addEventListener("submit", e => {
        e.preventDefault();
        var cadastroDados = {
            usuario: document.getElementById('criarUsuario').value,
            senha: document.getElementById('criarSenha').value,
            descricao: "Em breve será possível modificar sua descrição,aguarde! ",
            nome: document.getElementById('criarNome').value,
            tipo: "Usuário",
            ano: new Date().getFullYear(),
        };
        createUser(cadastroDados)
        clearFields()
    });

    loginForm.addEventListener("submit", e => {
        e.preventDefault();

        const loginDados = {
            usuario: document.getElementById('loginUsuario').value,
            senha: document.getElementById('loginSenha').value,
        }
        testValidade(loginDados)
    });
});
