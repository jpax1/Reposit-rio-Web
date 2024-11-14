#Pataquada Indie

#Descrição
O projeto Pataquada Indie é um sistema de cadastro de jogos indies desenvolvido em Java utilizando Servlets, JSP's e Banco de Dados com o MYSql Workbench. O projeto permite a criação, leitura, atualização e exclusão (CRUD) de jogos indies, com campos como título, descrição, data de publicação e preço.

#Tecnologias utilizadas para desenvolvimento do projeto
- Java (Servlet e JSP para o beck-end)
- Apache Tomcat 9.0 (para a execução do projeto no servidor)
- MySql (para a criação do banco de dados e armazenamento)
- HTML e CSS (Para o front-end)

#Executando o projeto:
#Pré-requisitos
- Java versão 8 ou superior.
- Apache Tomcat 9.0 (versões superiores como 10.0 irão desconfigurar o servlet).
- Eclipse IDE 2024-06.
- MySql Workbench 8.0
- MySql Connector J 9.1.0 (caso o projeto não apresente o conector)

### Instalação dos Requisitos
- Eclipse IDE: Acesse [eclipse.org](https://eclipse.org) e baixe a versão **2024-06** (recomendado) em "Other Releases". Instale selecionando "Eclipse IDE for Enterprise Java and Web Developers". 
- MySQL Workbench: No site [mysql.com/downloads](https://mysql.com/downloads), baixe **MySQL Installer for Windows** e, ao instalar, use o modo **Full** e configure usuário como "root" e senha como "12345".
- Apache Tomcat 9.0: Acesse [tomcat.apache.org](https://tomcat.apache.org) e baixe a versão **9.0**. Extraia o arquivo .zip.
- MySQL Connector Baixe o `.jar` do conector em [MySQL Connectors](https://dev.mysql.com/downloads/connector/), extraia e coloque em `lib`.

#Configuração do Banco de Dados SQL
Após a instalação e configuração adequada do MySql, execute o seguinte código:
-- Criando e utilizando o banco de dados --
CREATE DATABASE pataquada;
USE pataquada;

-- Criando a tabela de jogos --
CREATE TABLE jogos(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
titulo VARCHAR (60) NOT NULL,
descrição TEXT,
data_publicaçao DATE,
preço DOUBLE
);

Isso irá garantir que os padrões estão de acordo com o projeto.

#Configurando o Servidor
1- Baixe o repositório deste projeto e descompacte-o.
2- No Eclipse, selecione File -> Import -> General -> Existing Projects into Workspace e importe a pasta do projeto descompactado.
3-Configure o Apache Tomcat 9.0 no Eclipse, associando o projeto ao servidor.
4- Verifique a configuração do banco de dados no código (DBConnection ou similar).
5- Inicie o servidor e acesse http://localhost:8080/Pataquada/index.jsp.

#Como usar o sistema
Cadastro de jogos: Permite adicionar novos jogos ao banco de dados.
Exibição de jogos cadastrados: Lista todos os jogos existentes na tabela.
Edição de jogos: Atualiza as informações de um jogo existente.
Exclusão de jogos: Remove um jogo da lista.

#Estrutura do Projeto:
src/ : Código Java para Servlets e classes auxiliares.
WebContent/ : Arquivos JSP, HTML, CSS, e o diretório WEB-INF com bibliotecas.
lib/ : Arquivos .jar necessários (ex.: MySQL Connector).

Licença
Este projeto foi desenvolvido como parte de um trabalho acadêmico para fins educacionais. O código-fonte e demais materiais fornecidos são de uso restrito e não possuem 
permissão para redistribuição, uso comercial, ou cópia sem autorização. Qualquer uso fora do contexto educacional deverá ser previamente autorizado pelo autor.
