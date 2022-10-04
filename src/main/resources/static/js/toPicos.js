//json
const conteudos = [
    {
        linguagem: "C",
        imagem: "images/miniCardC.png",
        topicos: [
            {
                topico: "Introdução",
                autor: "EasyCode",
                conteudo: "<p> A linguagem C foi criada por Dennis Ritchie nos anos 1970 e continua sendo muito utilizada nos dias de hoje. Possui como principais características a simplicidade, a facilidade de uso e a capacidade de gerar códigos eficientes. Além disso, C influenciou diretamente diversas outras linguagens, que podem ser classificadas como linguagens C-Like (Java, C++, C#, ...). </p> <p>  Um programa em C é composto das seguintes partes: <ul> <li>Comandos do preprocessador</li> <li>Comentários</li> <li>Declaração de funções/ procedimentos (assinatura)</li> <li>Definição de variáveis e tipos</li> <li>Definição de funções, que contêm os comandos e expressões do programa.</li>  </ul>  </p> <p> Os comandos do preprocessador (linhas iniciando com ''#'') são aqueles responsáveis por gerar uma série de definições que podem existir em diversos módulos de um mesmo programa (exemplos: ''#define'' - utilizado para definir uma constante, ''#include'' - utilizado para se acrescentar alguma biblioteca). Os cometários são aquelas linhas de código que serão desconsideradas pelo compilador - são indicados por ''//'' ou ''/**/'' caso tenha mais de uma linha - o que permite maior organização do código. </p> <p> Os métodos (funções e procedimentos) são blocos de comando que contém uma série de instruções e que são executados assim que o método é chamado com os argumentos necessários. Uma variável é uma informação que se pode usar dentro de um programa C. Ela é associada a um endereço de memória (isso é feito pelo compilador) e a um nome (definido na declaração da variável). A informação pode ser alterada a qualquer momento, porém o endereço de memório e o nome permanecem os mesmos. Alguns tipos de variáveis em C: inteiros (int), números decimais (float/ double) e caracteres (char). </p> <p> Sites para aprender C: <ul> <li>https://www.w3schools.com/c/index.php</li> <li>https://www.codecademy.com/learn/learn-c</li>  </ul>  </p>  ",
                exemplo: [
                    {
                        texto: "",
                        imagem: "",
                    }
                ],
                exercicios: "",
                id: "lingCtop1"
            },
            {
              topico: "Vetores",
              autor: "EasyCode",
              conteudo: "<p>Em breve...</p>",
              exemplo: [
                  {
                      texto: "<p>Em breve...</p>",
                      imagem: "",
                  }
              ],
              exercicios: "",
              id: "lingCtop2"
            }
        ],
        id: "lingC"
    },
    {
        linguagem: "CSS",
        imagem: "images/miniCardCSS.png",
        topicos: [
            {
                topico: "Introdução",
                autor: "EasyCode",
                conteudo: "<p> CSS é a sigla em inglês para o termo <i>Cascading Style Sheets</i>(Folha de Estilo em Cascata). É a linguagem usada para estilizar documentos HTML, tornando as possibilidades de personalização quase infinitas. Assim, o CSS é responsável por definir como os elementos da página web serão apresentados e por proporcionar uma melhor experiência ao usuário. </p><p> Com a evolução dos recursos de programação, foi adotado mais estilo às páginas web. Então, o CSS foi criado com o objetivo de facilitar a formatação das aplicações. Ele foi desenvolvido, primeiramente, para habilitar a separação do conteúdo e formato de um documento, o que possibilitou maior flexibilidade de estilização e controle na especificação de como as características serão exibidas. </p> <p> O CSS permite: <ul> <li>manter um padrão de formatação em diferentes navegadores</li> <li>controlar o layout de uma página em apenas um lugar, assim evitando repetição de conteúdo dna estrutura do código</li> <li>criar formatações mais responsivas</li> </ul> </p> <p> Sites para aprender CSS:	<ul> <li>https://www.w3schools.com/css/default.asp</li> <li>https://developer.mozilla.org/pt-BR/docs/Learn/CSS/First_steps</li> </ul> </p>",
                exemplo: "",
                exercicios: "",
                id: "lingCtop1"
            }
        ],
        id: "lingCSS"
    },
    {
        linguagem: "C#",
        imagem: "images/miniCardCSharp.png",
        topicos: [
            {
                topico: "Introdução",
                autor: "EasyCode",
                conteudo: "<p> O C# é uma linguagem de programação multiparadigma criada pela Microsoft, sendo a principal da plataforma .NET. Por ser uma linguagem que suporta, entre outros paradigmas, a orientação à objetos, ela suporta conceitos comuns como encapsulamento, herança e polimorfismo. Trata-se também de uma linguagem fortemente tipada e case-sensitive, ou seja, faz diferenciação entre letras minúsculas e maiúsculas. </p> <p> Ele veio para facilitar o processo de desenvolvimento, tendo inúmeros recursos que proporcionam uma grande produtividade para os desenvolvedores que a utilizam. </p> <p> É uma linguagem multiplataforma. Sendo assim, você pode utilizá-la para desenvolver para plataformas web, dispositivos móveis e aplicações desktop. Sua sintaxe é simples e de fácil aprendizagem, muito familiar com a sintaxe de C (é uma linguagem C-Like). Com a praticidade dessa linguagem, você pode, de forma relativamente fácil, desenvolver desde projetos mais simples até projetos complexos e multiplataforma. </p><p> Sites para aprender C#:	<ul>		<li>https://www.treinaweb.com.br/blog/o-que-e-e-como-comecar-com-c-sharp</li>		<li>https://docs.microsoft.com/pt-br/dotnet/csharp/</li>		<li>https://www.w3schools.com/cs/index.php</li>	</ul></p>",
                exemplo: "",
                exercicios: "",
                id: "lingCtop1"
            }
        ],
        id: "lingCSharp"
    },
    {
        linguagem: "C++",
        imagem: "images/miniCardC++.png",
        topicos: [
            {
                topico: "Introdução",
                autor: "EasyCode",
                conteudo: "<p> A linguagem C++, inventada por Bjarne Stroustrup por volta de 1983 e descrita no livro 'A Linguagem de Programação C++', é uma evolução orientada a objetos da linguagem C de Brian Kernighan e Denis Ritchie. </p><p> Alguns fatos:<ul><li>O C++ é uma linguagem criada para ser tão eficiente quanto o C, porém com novas funções.</li><li>É uma linguagem multi-paradigma, pois suporta mais de um estilo de programação</li><li>A linguagem da liberdade para o programador escolher as opções, mesmo sendo a opção errada.</li><li>Muitos códigos podem ser transferidos para C facilmente, pois o C++ foi criado para ter compatibilidade com o C.</li><li>É uma linguagem de nível médio, ou seja, possui comandos simples das linguagens de alto nível e comandos complexos das linguagens de baixo nível.</li></ul></p><p> Sites para aprender C#:	<ul>		<li>https://docs.microsoft.com/pt-br/cpp/cpp/?view=msvc-170>		<li>https://www.w3schools.com/cpp/default.asp</li>	</ul></p>",
                exemplo: "",
                exercicios: "",
                id: "lingCtop1"
            }
        ],
        id: "lingCpp"
    },
    {
        linguagem: "HTML",
        imagem: "images/miniCardHTML.png",
        topicos: [
            {
                topico: "Introdução",
                autor: "EasyCode",
                conteudo: "<p>A linguagem HTML foi desenvolvida pelo físico Tim Berners-Lee, com o objetivo de resolver o problema de a comunicação e disseminação das pesquisas entre ele e o seu grupo de colegas. Atualmente, a linguagem é utilizada na construção de páginas da Web. Diferente de outras linguagens, o HTML é uma linguagem de marcação, ou seja, não possui estruturas de controle como os comandos condicionais e de repetição.</p>",
                exemplo: [
                    {
                        texto: "",
                        imagem: "images/exemplo.png",
                    }
                ],
                exercicios: "",
                id: "lingCtop1"
            }
        ],
        id: "lingHTML"
    },
    {
        linguagem: "Python",
        imagem: "images/miniCardPython.png",
        topicos: [
            {
                topico: "Introdução",
                autor: "EasyCode",
                conteudo: "<p>A linguagem Python é uma linguagem de programação de alto nível e orientada a objetos — uma forma específica de organizar softwares onde os procedimentos estão submetidos às classes, o que possibilita maior controle e estabilidade de códigos para projetos de grandes proporções. É bastante popular por ser de fácil compreensão e possuir um grande número de bibliotecas.</p>",
                exemplo: "",
                exercicios: "",
                id: "lingCtop1"
            }
        ],
        id: "lingPython"
    },
    {
        linguagem: "JAVA",
        imagem: "images/miniCardJAVA2.png",
        topicos: [
            {
                topico: "Introdução",
                autor: "EasyCode",
                conteudo: "<p>A linguagem Java é outra linguagem orientada por objetos. Porém, diferente das linguagens de programação modernas, que são compiladas para código nativo, a linguagem Java é compilada para um bytecode que é interpretado por uma máquina virtual. Uma das grandes vantagens de aprender Java é que você consegue criar aplicações para vários tipos de dispositivos, como por exemplo: web, mobile, TV Digital, dentre outros.</p>",
                exemplo: "",
                exercicios: "",
                id: "lingCtop1"
            }
        ],
        id: "lingJAVA"
    },
    {
        linguagem: "JavaScript",
        imagem: "images/miniCardJavaScript.png",
        topicos: [
            {
                topico: "Introdução",
                autor: "EasyCode",
                conteudo: "<p>A linguagem JavaScript (JS), é uma linguagem de programação dinâmica cheia de recursos que quando aplicada em um documento HTML, pode fornecer interatividade dinâmica em sites.Juntamente com HTML e CSS, é uma das três principais tecnologias da World Wide Web. </p>",
                exemplo: "",
                exercicios: "",
                id: "lingCtop1"
            }
        ],
        id: "lingJavaScript"
    }
]

const usuarios = [
    {
        usuario: "easycode",
        senha: "senha1234",
        descricao: "Administrador do site",
        avatar: "images/logo.png",
        detalhes: [
            {
                nome: "EasyCode",
                idade: "",
                localizacao: "Belo Horizonte - MG",
                redesSociais: [
                    {
                        facebook: "",
                        instagram: "",
                        twitter: ""
                    }
                ]
            }
        ],
        conteudos: [
            {
                publicacao: "publico",
                criacao: "08/06/2022",
                edicao: "12/06/2022",
                //titulo
                //linguagem
                id: "lingCtop1"
            },
            {
                publicacao: "publico",
                criacao: "08/06/2022",
                edicao: "12/06/2022",
                id: "lingCtop2"
            }
        ],
        id: "user0"
    }
]

/* ------------------------------------------------ */

let txtTopicos = '';

let params = new URLSearchParams(location.search);
let ID = params.get('id');

onload = () => {

    for (let i = 0; i < conteudos.length; i++)
    {
        if(conteudos[i].id == ID)
        {
            txtTopicos += `<h2 class="tituloLinguagemTopicos">${conteudos[i].linguagem}</h2>
            <ul class="textoTopicos">`;

            document.getElementById('tituloPagina').innerHTML = `${conteudos[i].linguagem}`;


            for (let j = 0; j < conteudos[i].topicos.length; j++)
            {
                txtTopicos += `<a href="conteudoDaLinguagem.html?principal=${conteudos[i].id}&id=${conteudos[i].topicos[j].id}"><li>${conteudos[i].topicos[j].topico}</li></a>`
                /*
                if (conteudos[i].topicos[j].subtopicos.length != 0)
                {
                    txtTopicos += `<ol>`;
                    for (let k = 0; k < conteudos[i].topicos[j].subtopicos.length; k++)
                    {
                        txtTopicos += `<li><a href="conteudoDaLinguagem.html?principal=${conteudos[i].id}&id=${conteudos[i].topicos[j].subtopicos[k].id}">${conteudos[i].topicos[j].subtopicos[k].subtopico}</a></li>`;
                    }
                    txtTopicos += `</ol>`;
                }
                */
                //retirado os subtopicos ^^^
            }
            txtTopicos += `</ul>`;

                document.getElementById('areaParaTopicos').innerHTML= `${txtTopicos}`;
        }
    }

    for (let i = 0; i < conteudos.length; i++)
    {

    }
}
