CREATE TABLE Plano (
id_plano INT PRIMARY KEY,
nome VARCHAR (100) NOT NULL,
descricao VARCHAR (50),
duracao INT NOT NULL,
valor FLOAT NOT NULL
);

CREATE TABLE Personal (
id_personal INT PRIMARY KEY,
nome VARCHAR (100) NOT NULL,
cpf VARCHAR(14) NOT NULL UNIQUE,
telefone VARCHAR (20) NOT NULL,
email VARCHAR (100) NOT NULL,
especialidade VARCHAR(100) NOT NULL
);

CREATE TABLE Aluno (
id_aluno INT PRIMARY KEY,
nome VARCHAR (100) NOT NULL,
cpf VARCHAR(14) NOT NULL UNIQUE,
email VARCHAR (100) NOT NULL,
telefone VARCHAR(20) NOT NULL,
data_nascimento DATE,
id_plano INT NOT NULL,
id_personal INT NOT NULL,
foreign key (id_plano) references Plano(id_plano),
foreign key (id_personal) references Personal(id_personal)
);

CREATE TABLE endereco_aluno (
id_endereco INT PRIMARY KEY,
rua VARCHAR (100) NOT NULL,
numero VARCHAR(10) NOT NULL,
complemento VARCHAR (50) NOT NULL,
bairro VARCHAR(100) NOT NULL,
cidade VARCHAR(50) NOT NULL,
estado VARCHAR(2) NOT NULL,
cep VARCHAR(9) NOT NULL,
id_aluno INT NOT NULL,
foreign key (id_aluno) references Aluno(id_aluno)
);

CREATE TABLE Pagamento (
id_pagamento INT PRIMARY KEY,
data DATE NOT NULL,
valor DECIMAL(10,2) NOT NULL,
forma_pagamento VARCHAR (50) NOT NULL,
status_pagamento VARCHAR(30) NOT NULL,
id_aluno INT NOT NULL,
foreign key (id_aluno) references Aluno(id_aluno)
);

CREATE TABLE Frequencia (
id_frequencia INT PRIMARY KEY,
data_entrada DATE NOT NULL,
data_saida DATE NOT NULL,
hora_entrada TIME NOT NULL,
hora_saida TIME NOT NULL,
id_aluno INT NOT NULL,
foreign key (id_aluno) references Aluno(id_aluno)
);

CREATE TABLE Treino (
id_treino INT PRIMARY KEY,
nome VARCHAR (50) NOT NULL,
objetivo VARCHAR (50) NOT NULL,
observacoes VARCHAR(200) NOT NULL,
id_aluno INT NOT NULL,
id_personal INT NOT NULL,
foreign key (id_aluno) references Aluno(id_aluno),
foreign key (id_personal) references Personal(id_personal)
);

CREATE TABLE Exercicio (
id_exercicio INT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(200) NOT NULL,
grupo_muscular VARCHAR(100) NOT NULL
);

CREATE TABLE Treino_Exercicio(
id_treino_exercicio INT PRIMARY KEY,
series INT NOT NULL,
repeticoes INT NOT NULL,
carga FLOAT NOT NULL,
tempo_descanso INT NOT NULL,
id_treino INT NOT NULL,
id_exercicio INT NOT NULL,
foreign key (id_treino) references Treino(id_treino),
foreign key (id_exercicio) references Exercicio(id_exercicio)
);

