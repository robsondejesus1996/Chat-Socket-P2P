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
O trabalho tem como objetivo desenvolver uma aplicação Servivor/Cliente e Cliete/Cliente P2P para a troca de mensagens. Logo vamos desenvolver aqui uma aplicação estilo Messenger que os usuários ao entrar<br>
devem informar o nome e uma porta para a comunicação entre as redes. Vamos utilizar de uma tecnologia chamada socket (network socket) que seria um ponto final de um fluxo de comunicação entre porcessos<br>
através de uma rede de computadores.<br>

<h1>Funcionamento comunicação</h1>
A comunicação do chat vai ser através de todo o cliente se comunica com o servidor para ter a lista de todos os clientes que estão online, ou seja, o servidor me retorna o endereço IP
a porta e o nome do cliente que está conectado. Após o cliente receber está lista do servidor ele pode se conectar diretamente com cada um dos clientes. A mensagem que esse chat for enviar
não vai passar pelo servidor, e depois o servidor enviar para o cliente. O cliente vai fazer uma conexão diretamente com o outro cliente P2P. O servidor só será utilizado para dizer quem 
está online no momento e quem não está.
<br>

<h1>Requisitos Funcionais</h1>
<b>[RF01]</b> - O sistema deve permitir a verificação do usuário ao fazer o login (Nome usuário e Porta) <br>
<b>[RF02]</b> – O sistema deve permitir o envio de mensagens entre os usuários, a comunicação será direta entre cada usuário, logo será de forma privada. <br>
<b>[RF03]</b> - O sistema deve ter um mecanismo para retorna um erro de execução se o Server não estiver em execução. <br>
<b>[RF04]</b> - O sistema deve ter em cada tela ao entrar no sistema o nome especificado do cliente que acabou de logar no sistema. <br>
<b>[RF05]</b> - O sistema deve ter um botão para sempre atualizar a lista de usuários onlines no momento. Assim vamos conseguir ter a informação do nome e a rede que esse usuário está. <br>
<b>[RF06]</b> - O sistema ao iniciar uma conversa com outro usuário deve abrir duas janelas de chat, uma para quem inicio a conversa e outra para quem está sendo solicitado no momento da conversa. <br>
<b>[RF07]</b> - O sistema deve identificar se um usuário fechou a janela do chat com outro usuário. Assim as duas janelas devem ser encerradas ao mesmo tempo. <br>


---

## [Requisitos Não Funcionais](##requisitos)

<b>[RNF1]</b> - Deve ser distribuído e executar simultanamente em no mínimo 3 clientes (hosts) diferentes. <br>
        A) Considerar o uso do ngrok para comunicação entre os diferentes hosts.<br>
<b>[RNF2]</b> - Deve haver comunicação entre os diferentes clientes através de: <br>
        A)Sockets, cada cliente ira se conectar com outro cliente, logo não vai haver a necessidade de um servidor que vai receber a mensagem, passar pelo servidor e depois mandar para o cliente. Vamos utilizar o conceito de P2P cliente se conectando com cliente<br>
<b>[RNF3]</b> -  A comunicação cliente/cliente deve ser direta, ou seja, sem passar por qualquer servidor. <br>
<b>[RNF4]</b> -  Deve ser possível realizar comunicação entre diversos clientes simultaneamente.<br>
<b>[RNF5]</b> -  Se necessário, pode ser implementado um servidor simples para ser acessado pelos clientes.<br>
        A)Este servidor jamais deve criar conexões com clientes, apenas receber conexões.<br>
        B)A comunicação cliente→servidor deve ser a mínima possível. O funcionamento do sistema deve<br>
        ser baseado principalmente na comunicação ser cliente→cliente.<br>
        C)Não se deve utilizar nenhuma implementação pronta de qualquer servidor (ex: Express JS). Em<br>
<b>[RNF6]</b> -  Se utilizar Sockets no requisito (2), então deve-se observar os seguintes requisitos: <br>
        A)Sockets ociosos não podem existir (todos devem ser fechados logo após a comunicação).
        B)Os dados enviados entre cliente/servidor e cliente/cliente podem estar no formato JSON ou<br>
        XML. Não é permitido o uso de serialização de objetos.<br>


---
## [Especificação Mensagens(Cliente/Servidor)](#ClienteServidor)

A comunicação entre essas duas partes será minima, o servidor me retorna o endereço IP a porta e o nome do cliente que está conectado. A verificação do nome do cliente e a porta se da pelo metodo checkLogin() na classe server, esse metodo vai testar o nome e a porta. Vamos desenvolver a comunicação cliente e servidor para coisas simples como:

Especificação mensagens Servidor

1) Retorno de sucesso se ele conseguir conectar no chat.<br>
2) Retorno de erro se o usuário entrar com o mesmo nome e porta(A porta de entrada deve ser única se o usuário estiver na mesma rede de acesso).<br>
3) Retorna o erro de execução do servidor se por acasso o servidor no momento não estiver em execução.<br>

<h1>Diagrama de Sequências</h1>

![Diagrama_sequencia_cliente_servidor](https://user-images.githubusercontent.com/31260719/129609235-db95b8ec-bba0-4166-b4cf-f96966872784.png)

<h1>Diagrama de Classe</h1>

![Classe_servidor](https://user-images.githubusercontent.com/31260719/129617695-696027e5-faee-4bb3-9270-3f5cfd093a26.png)




---


## [Especificação Mensagens(Cliente/Cliente)](#ClienteCliente)

Após o cliente receber está lista do servidor ele pode se conectar diretamente com cada um dos clientes. A mensagem que esse chat enviar,
não irá passar pelo servidor, depois, o servidor enviará para o cliente. O cliente irá realizar uma conexão diretamente com outros clientes.

Especificação mensagens Cliente

1) Quando o usuário já estiver logado no sistema teremos um atualizar lista de contatos, essa é a unica função que também está conectada com o Server(Lembrado isso é só para pegar a lista de usuários conectados no momento)

2) Ao abrir uma conversa com outro cliente, teremos o ClienteListener se conectando com outro ClienteListener


![Diagrama_sequencia_cliente_cliente](https://user-images.githubusercontent.com/31260719/129609417-7a1b38b7-48c4-4a41-92b9-164ddd40bdbd.png)


---

<!--## [Diagramas](#diagramas)

<h1>Comunicação Cliente/Servidor diagrama de classes</h1>


![Cliente-Servidor](https://user-images.githubusercontent.com/31260719/127002691-9f293ee2-fd06-44f6-b0ed-3660fa48aee1.png)

<h1>Comunicação Cliente/Servidor diagrama de sequências</h1>

![Cliente-ServidorSequencia](https://user-images.githubusercontent.com/31260719/129446020-d89ba9ba-d854-4f35-b01b-61f0bc252502.png)


<h1>Comunicação Cliente/Cliente diagrama de classes</h1>


![Cliente-Cliente](https://user-images.githubusercontent.com/31260719/127002858-9bdb0cf6-e2ae-41b3-8255-5a13be53a0d3.png)


<h1>Comunicação Cliente/Cliente diagrama de sequências</h1> -->
