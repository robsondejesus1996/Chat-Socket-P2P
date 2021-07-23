<!-- Visualizador online: https://stackedit.io/ -->
 ![Logo da UDESC Alto Vale](http://www1.udesc.br/imagens/id_submenu/2019/marca_alto_vale_horizontal_assinatura_rgb_01.jpg)

---


Trabalho realizado para a disciplina de Desenvolvimento de Sistemas Paralelos e Distribuídos do [Centro de Educação Superior do Alto Vale do Itajaí (CEAVI/UDESC)](https://www.udesc.br/ceavi)<br>O objetivo do trabalho é implementar e praticar o desenvolvimento de sistemas concorrentes e distribuídos através da implementação de um sistema distribuído de livre escolha pelos alunos.



# Sumário 
* [Equipe](#equipe)

* [Descrição e Requisitos Funcionais](#descricao)

* [Requisitos Não Funcionais](#requisitos)

* [Especificação Mensagens(Cliente/Servidor)](#ClienteServidor)

* [Especificação Mensagens(Cliente/Cliente)](#ClienteCliente)

* [Diagramas](#diagramas)


---

## [Equipe](#equipe)
 - [**Robson de Jesus**](mailto:robson.jesus@edu.udesc.br) - [robsondejesus1996](https://github.com/robsondejesus1996)
 - [**Brenda Paetzoldt Silva**](mailto:brenda.bps@edu.udesc.br) - [brendapaetzoldt](https://github.com/brendapaetzoldt)


---

## [Descrição e Requisitos Funcionais](#descricao)

<h1>Descrição</h1>
O trabalho tem como objetivo desenvolver uma aplicação Servivor/Cliente e Cliete/Cliente P2P para o troca de mensagens. Logo vamos desenvolver aqui uma aplicação estilo Messenger que os usuários ao entrar<br>
devem informar o nome e uma porta para a comunicação entre as redes. Vamos utilizar de uma tecnologia chamada socket (network socket) que seria um ponto final de um fluxo de comunicação entre porcessos<br>
através de uma rede de computadores.<br>

<h1>Funcionamento comunicação</h1>
A comunicação do chat vai ser através de todo o cliente se comunica com o servidor para ter a lista de todos os clientes que estão online, ou seja, o servidor me retorna o endereço IP,<br>
a porta e o nome do cliente que está conectado. Após o cliente receber está lista do servidor ele pode se conectar diretamente com cada um dos clientes. A mensagem que esse chat for enviar<br> 
não vai passar pelo servidor, e depois o servidor enviar para o cliente. O cliente vai fazer uma conexão diretamente com o outro cliente P2P. O servidor só será utilizado para dizer quem <br>
está online no momento e quem não está.<br>
<br>

Requisitos Funcionais
[RN1] - <br>
[RN2] - <br>
[RN3] - <br>
[RN4] - <br>
[RN5] - <br>
[RN6] - <br>
[RN7] - <br>
[RN8] - <br>
[RN9] - <br>
[RN10] - <br>

---

## [Requisitos Não Funcionais](##requisitos)

<b>[RNF1]</b> - Deve ser distribuído e executar simultanamente em no mínimo 3 clientes (hosts) diferentes. <br>
        A) Considerar o uso do ngrok para comunicação entre os diferentes hosts.<br>
<b>[RNF2]</b> - Deve haver comunicação entre os diferentes clientes através de: <br>
        A)Sockets, ou<br>
        B)RMI. Ler seções 5.4 e 5.5 de (COULOURIS, DOLLIMORE, et al., 2013) ou,<br>
        C)CORBA. Ler seção 8.3 de (COULOURIS, DOLLIMORE, et al., 2013).<br>
<b>[RNF3]</b> -  A comunicação cliente/cliente deve ser direta, ou seja, sem passar por qualquer servidor. <br>
<b>[RNF4]</b> -  Deve ser possível realizar comunicação entre diversos clientes simultaneamente.<br>
<b>[RNF5]</b> -  Se necessário, pode ser implementado um servidor simples para ser acessado pelos clientes.<br>
        A)Este servidor jamais deve criar conexões com clientes, apenas receber conexões.<br>
        B)A comunicação cliente→servidor deve ser a mínima possível. O funcionamento do sistema deve<br>
        ser baseado principalmente na comunicação ser cliente→cliente.<br>
        C)Não se deve utilizar nenhuma implementação pronta de qualquer servidor (ex: Express JS). Em<br>
        caso de dúvida, consulte o professor.<br>
<b>[RNF6]</b> -  Se utilizar Sockets no requisito (2), então deve-se observar os seguintes requisitos: <br>
        A)Sockets ociosos não podem existir (todos devem ser fechados logo após a comunicação).
        B)Os dados enviados entre cliente/servidor e cliente/cliente podem estar no formato JSON ou<br>
        XML. Não é permitido o uso de serialização de objetos.<br>
<b>[RNF7]</b> -  A aplicação cliente deve ter interface gráfica. <br>
        A) A equipe pode utilizar interface gráfica disponível em outros projetos, citando a fonte/projeto.<br>
        MAS ATENÇÃO: o projeto utilizado como base não pode ser distribuído (não pode já ter<br>
        comunicação entre clientes)<br>

---
## [Especificação Mensagens(Cliente/Servidor)](#ClienteServidor)

teste mensagens trocadas cliente/cliente

---


## [Especificação Mensagens(Cliente/Cliente)](#ClienteCliente)

teste mensagens trocadas cliente/cliente

---

## [Diagramas](#diagramas)

teste diagramas<br>