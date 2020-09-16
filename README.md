# rmi_otes11
 Trabalho final da matéria de sistemas distribuídos do curso TADS na UDESC
 
## Como executar
 - Clone ou faça download do projeto e abra na sua IDE
 - Ao realizar build do projeto, abra a pasta onde se encontra o .class do MessageMasterServer, abra o cmd e rode o comando `rmiregistry`
 - Agora na sua IDE, rode o MessageServer pelo menos duas vezes para subir os servidores
 - Rode também o MessageClient
 - Agora você pode inserir alguns mensagens e testar se elas estão sendo salvas enviando a letra 'S' no input
 - Derrube o primeiro MessageServer que você subiu
 - Envie uma nova mensagem no client, ele deve conectar-se ao servidor que ainda está de pé
