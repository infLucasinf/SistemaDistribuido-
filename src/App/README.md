# INF020Trabalho

## Trabalho da disciplina sistemas distribuídos.

### Equipe: 
Matheus Bonifm,
Uriel Lincoln e
Lucas Fonsêca

Esse é um projeto sem o uso do "Maven", segue os passos para executar aplicação: 


Ao executar a classe TestAPP, inicia o servidor RMI.
Define um grupo inicial de membros.
Executa lógica para verificar e iniciar eleições, se necessário.
Pode enviar dados replicados para as réplicas.

Executar a classe ReplicCliente
tenta se conectar à réplica do servidor.
Verifica se o líder está ativo.
Inicia uma eleição se o líder não estiver ativo.
Exibe mensagens indicando o resultado da eleição (se o membro se tornou líder ou não).
Continua enviando dados para as réplicas.

## Documentação das classes que consta no projeto: 

**Classe Membro**
 Um membro de um grupo. 
Cada membro tem três atributos:

id: Um número único que identifica cada membro.
lider: Um indicador se o membro é o líder do grupo.
ativo: Um indicador se o membro está ativo no grupo.
O construtor é chamado quando um novo membro é criado. Ele recebe valores para id, lider e ativo e os atribui aos atributos.


**Interface RepInterface**
A interface "RepInterface" tem vários métodos relacionados à replicação e interação entre membros de um grupo.

Métodos na Interface:

sendDataToReplicas: Este método é utilizado para enviar dados para todas as réplicas do grupo.


verificarLider: Verifica se um membro específico é o líder do grupo.

solicitarAdesao: Utilizado por um membro para solicitar adesão ao grupo.

iniciarEleicao: Inicia um processo de eleição no grupo.

Descrição: Recebe dados replicados de outra réplica.


receberNotificacao: Recebe notificações no grupo e recebe dados replicados de outra réplica



# **TestApp**
Uma aplicação de teste e membros podem enviar e receber dados replicados, além de realizar eleições para escolher um líder.


Objetivo: Ponto de entrada do programa quando é executado.
Funcionalidade:
Cria uma instância da aplicação.
A ideia é inicia o servidor RMI.
Define um grupo inicial de membros.

sendDataToReplicas:

Envia dados replicados para as réplicas.
Dados a serem replicados e ID do remetente.
Verifica se o membro é o líder.
Para cada membro ativo diferente do remetente, envia dados replicados para a réplica correspondente.


solicitarAdesao:

Objetivo: Adiciona um novo membro ao grupo.
Parâmetros: ID do novo membro.
Funcionalidade: Adiciona um novo membro ao grupo e verifica a liderança.

iniciarEleicao:

A ideia é iniciar uma eleição.
Parâmetros: ID do candidato à liderança.
Funcionalidade:
Exibe uma mensagem indicando o início da eleição.
Aguarda um curto período de tempo (simulando o processo de eleição).
Exibe uma mensagem indicando a conclusão da eleição e o novo líder.
Notifica os outros membros sobre o novo líder.


liderId: Armazena o ID do líder atual.
membros: Lista de membros no grupo, representados pela classe Membro.

**ReplicCliente**
Representa cliente em um sistema de replicação distribuída, membros de um grupo compartilham informações e elegem um líder para coordenar as operações.


Define o endereço e a porta do servidor RMI.
Cria uma string de dados a ser replicada.
Tenta se conectar a uma réplica do servidor.
Verifica se o líder está ativo.
Inicia uma eleição se o líder não estiver ativo.
Exibe mensagens indicando se o membro se tornou líder ou não.


connectToReplica:

A ideia é conectar-se a uma réplica específica do servidor RMI.
Usa o endereço e a porta para obter uma referência à réplica do servidor.

CANDIDATO_ID: Uma constante que representa o ID do membro que deseja se tornar líder.


O cliente é executado.
Tenta se conectar à réplica do servidor.
Verifica se o líder está ativo.
Inicia uma eleição se o líder não estiver ativo.
Exibe mensagens indicando o resultado da eleição (se o membro se tornou líder ou não).
Continua enviando dados para as réplicas.



