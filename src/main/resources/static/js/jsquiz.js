const questions = [
    {
        question: 'Você já tem alguma experiência com a área?',
        answers: [
            {
                texto: 'Sim',
                cPP: false,
                cSharp: false,
                piton: false,
                apenasC: false,
                htmEle: false,
                CSSgo: false,
                javaScrito: false,
                experiencia: true,
            },
            {
                texto: 'Não',
                cPP: false,
                cSharp: false,
                piton: true,
                apenasC: true,
                htmEle: true,
                CSSgo: true,
                javaScrito: true,
                experiencia: false,
            },
        ]
    },
    {
        question: 'Qual seu símbolo favorito?',
        answers: [
            {
                texto: 'Sim, amo o +',
                cPP: true,
                cSharp: false,
                piton: false,
                apenasC: false,
                htmEle: false,
                CSSgo: false,
                javaScrito: false,
                experiencia: false,
            },
            {
                texto: 'Obvio, adoro o #',
                cPP: false,
                cSharp: true,
                piton: false,
                apenasC: false,
                htmEle: false,
                CSSgo: false,
                javaScrito: false,
                experiencia: false,
            },
            {
                texto: '🐍 Sssssss',
                cPP: false,
                cSharp: false,
                piton: true,
                apenasC: false,
                htmEle: false,
                CSSgo: false,
                javaScrito: false,
                experiencia: false,
            },
            {
                texto: 'Não 𝘾urto símbolos',
                cPP: false,
                cSharp: false,
                piton: false,
                apenasC: true,
                htmEle: false,
                CSSgo: false,
                javaScrito: false,
                experiencia: false,
            },
        ]
    },
    {
        question: 'Você tem interesse na criação de sites?',
        answers: [
            {
                texto: 'Hey,Tenho Muita Labia para sites',
                cPP: false,
                cSharp: false,
                piton: false,
                apenasC: false,
                htmEle: true,
                CSSgo: false,
                javaScrito: false,
                experiencia: false,
            },
            {
                texto: 'Concordo Sem Suspirar, amo sites',
                cPP: false,
                cSharp: false,
                piton: false,
                apenasC: false,
                htmEle: false,
                CSSgo: true,
                javaScrito: false,
                experiencia: false,
            },
            {
                texto: 'JA VAi SCRIPTado tudo que faço',
                cPP: false,
                cSharp: false,
                piton: false,
                apenasC: false,
                htmEle: false,
                CSSgo: false,
                javaScrito: true,
                experiencia: false,
            },
            {
                texto: 'não gosto.',
                cPP: false,
                cSharp: false,
                piton: false,
                apenasC: false,
                htmEle: false,
                CSSgo: false,
                javaScrito: false,
                experiencia: false,
            },
        ]
    },
    {
        question: 'Entre Java e Java, qual você mais gosta.',
        answers: [
            {
                texto: 'Java',
                cPP: false,
                cSharp: false,
                piton: false,
                apenasC: false,
                htmEle: false,
                CSSgo: false,
                javaScrito: true,
                experiencia: false,
            },
            {
                texto: 'Java',
                cPP: false,
                cSharp: false,
                piton: false,
                apenasC: false,
                htmEle: false,
                CSSgo: false,
                javaScrito: true,
                experiencia: false,
            },
        ]
    },
]


var cPP = 0;
var cSharp = 0;
var piton = 0;
var apenasC = 0;
var htmEle = 0;
var CSSgo = 0;
var javaScrito = 0;
var experiencia = 0;

const startButton = document.getElementById('start-btn')
const nextButton = document.getElementById('nxt-btn')
const questionContainerElement = document.getElementById('container-pergunta')
const questionElement = document.getElementById('pergunta')
const answerButtonsElement = document.getElementById('respostas')

let sQuestions, currentQuestionIndex

startButton.addEventListener('click', startGame)
nextButton.addEventListener('click', () => {
    currentQuestionIndex++
    setNextQuestion()
})

function startGame() {
    sQuestions = questions
    currentQuestionIndex = 0
    questionContainerElement.classList.remove('sconde')
    nextButton.innerText = 'Próximo'
    setNextQuestion()
}

function setNextQuestion() {
    resetState()
    if (sQuestions.length > currentQuestionIndex) {
        sessionStorage.setItem("questaoAtual", JSON.stringify(sQuestions[currentQuestionIndex]))
        showQuestion(sQuestions[currentQuestionIndex])
    } else {
        //startButton.innerText = 'Responder novamente'
        quizResults()
    }
}

function showQuestion(question) {
    questionElement.innerText = question.question
    question.answers.forEach(answer => {
        const button = document.createElement('button')
        button.innerText = answer.texto
        button.classList.add('btn-quiz')
        button.addEventListener('click', selectAnswer)
        answerButtonsElement.appendChild(button)
    })
}

function resetState() {
    nextButton.classList.add('sconde')
    while (answerButtonsElement.firstChild) {
        answerButtonsElement.removeChild(answerButtonsElement.firstChild)
    }
}

function addScore(butao) {
    let i = 0;
    question = JSON.parse(sessionStorage.getItem('questaoAtual'))
    while (question.answers.length >> i)
        if (question.answers[i].texto == butao) {
            if (question.answers[i].cPP == true) cPP++;
            if (question.answers[i].cSharp == true) cSharp++;
            if (question.answers[i].piton == true) piton++;
            if (question.answers[i].apenasC == true) apenasC++;
            if (question.answers[i].htmEle == true) htmEle++;
            if (question.answers[i].CSSgo == true) CSSgo++;
            if (question.answers[i].javaScrito == true) javaScrito++;
            if (question.answers[i].experiencia == true) experiencia++;
            i++;
        } else {
            i++;
        }
}

function selectAnswer(e) {
    const selectedButton = e.target
    const answerButton = selectedButton.innerText;
    addScore(answerButton);
    if (sQuestions.length > currentQuestionIndex + 1) {
        nextButton.classList.remove('sconde')
    } else {
        //startButton.innerText = 'Responder novamente'
        nextButton.classList.remove('sconde')
        nextButton.innerText = 'Ver Resultado'
    }
}

function quizResults() {
    questionElement.innerHTML = `<div class="card-header text-center fs-5">
        E o seu resultado é:
    </div>
    `
    if (cPP == 1) {
        answerButtonsElement.innerHTML = `
     <div class="card-body">
    <h5 class="card-title text-center fs-4">A linguagem C++!!</h5>
    <p class="card-text text-center fs-6">Será de + te ensinar! Clique aqui para ver nossa página sobre C++.</p>
    <a href="linguagens.html?id=lingCpp" class="linkCard botaoModalQuiz position-absolute top-95 start-50 translate-middle">Acesse</a>
  </div>
</div>
    `
    } else if (piton == 2) {
        answerButtonsElement.innerHTML = `
     <div class="card-body">
    <h5 class="card-title text-center fs-4">A linguagem Python!!</h5>
    <p class="card-text text-center fs-6">🐍Sssserá um prazer te ensinar! Clique aqui para ver nossa página sobre Python.</p>
    <a href="linguagens.html?id=lingPython" class="linkCard botaoModalQuiz position-absolute top-95 start-50 translate-middle">Acesse</a>
  </div>
</div>
    `
    } else if (javaScrito == 2) {
        answerButtonsElement.innerHTML = `
     <div class="card-body">
    <h5 class="card-title text-center fs-4">A linguagem JavaScript!!</h5>
    <p class="card-text text-center fs-6">Com nossa ajuda, JA VAi aprender essa rapidinho! Clique aqui para ver nossa página sobre JavaScript.</p>
    <a href="linguagens.html?id=lingJavaScript" class="linkCard botaoModalQuiz position-absolute top-95 start-50 translate-middle">Acesse</a>
  </div>
</div>
    `
    } else if (htmEle == 2) {
        answerButtonsElement.innerHTML = `
     <div class="card-body">
    <h5 class="card-title text-center fs-4">A linguagem HTML!!</h5>
    <p class="card-text text-center fs-6">Hey, Tudo Muito Legal! Clique aqui para ver nossa página sobre HTML.</p>
    <a href="linguagens.html?id=lingHTML" class="linkCard botaoModalQuiz position-absolute top-95 start-50 translate-middle">Acesse</a>
  </div>
</div>
    `
    } else if (experiencia == 1) {
        answerButtonsElement.innerHTML = `
     <div class="card-body">
    <h5 class="card-title text-center fs-4">A linguagem CSS!!</h5>
    <p class="card-text text-center fs-6">Suas páginas vão ficar lindas com nossa ajuda!! Clique aqui para ver nossa página sobre CSS.</p>
    <a href="linguagens.html?id=lingCSS" class="linkCard botaoModalQuiz position-absolute top-95 start-50 translate-middle">Acesse</a>
  </div>
</div>
    `
    } else {
        answerButtonsElement.innerHTML = `
     <div class="card-body">
    <h5 class="card-title text-center fs-4">A linguagem C!!</h5>
    <p class="card-text text-center fs-6">Cê tá doido? Clique aqui para ver nossa página sobre C.</p>
    <a href="linguagens.html?id=lingC" class="linkCard botaoModalQuiz position-absolute top-95 start-50 translate-middle">Acesse</a>
  </div>
</div>
    `
    }
    cPP = 0;
    cSharp = 0;
    piton = 0;
    apenasC = 0;
    htmEle = 0;
    CSSgo = 0;
    javaScrito = 0;
    experiencia = 0;
}

const result = [
    {

    }
]
