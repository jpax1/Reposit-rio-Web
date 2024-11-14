<!-- Define a linguagem da página como Java e o tipo de conteúdo como HTML, especificando a codificação UTF-8 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Pataquada Indie - Cadastrar Jogo</title>
		
		<!-- Link para arquivo css para caracterização da página -->
		<link rel="stylesheet" href="css/style.css">
		
		<!-- Define o icone da página que apareca na aba do navegador -->
		<link rel="icon" href="imagens/Logo_Pataquada.png">
	</head>
	<body>
		<!-- Cabeçalho do site -->
		<header>
			<div class="logo">
				<!-- Define a logo e o título da página -->
				<img src="imagens/Logo_Pataquada.png" height="100px"><h1>Pataquada Indie</h1>
			</div>
			<!-- Cria o espaço de navegação do site -->
			<nav>
				<ul>
					<li><a href="#">Recomendados</a></li>
					<li><a href="#">Últimos Jogos</a></li>
					<li><a href="#">Sobre</a></li>
					<li>
						<!-- Formulário de pesquisa que envia uma busca para o servlet "search" via POST -->					
						<form action="search" method="post">
							<input type="text" id="pesquisa" name="pesquisa" placeholder="Pesquisar 🔍">
						</form>
					</li>
				</ul>
			</nav>
		</header>
		<main>
			<br><br>
			
			<!-- Título principal da página para a edição de um jogo já cadastrado -->
			<h1 style="text-align: center;color: #30acba;">Editar Jogo</h1>
			
			<!-- Formulário que envia os dados editados para o servlet "update" com o parâmetro id do jogo via método POST -->
			<p style="text-align: center;">Página dedicada para a edição do perfil do jogo.</p>
			<div class="organizar">
				<form action="update?idJogoEditar=${jogoEditar.getId()}" method="post">
				
					<!-- Campo de texto para o título do jogo, com valor pré-preenchido a partir do objeto "jogoEditar" -->
					<label for="titulo" class="rotulo">Título</label><br>
					<input type="text" id="titulo" name="titulo" class="campo" placeholder="Nome do Jogo" value="${jogoEditar.getTitulo()}" required>
					<br><br>
				
					<!-- Campo de texto para a descrição do jogo, com valor pré-preenchido -->
					<label for="descricao" class="rotulo">Descrição</label><br>
					<input id="descricao" name="descricao" class="campo" placeholder="Descrição do Jogo" value="${jogoEditar.getDescriçao()}" required>
					<br><br>
				
					<!-- Campo de seleção de data para a data de publicação do jogo, com valor pré-preenchido -->
					<label for="data" class="rotulo">Data</label><br>
					<input type="date" id="data" name="data" class="campo" value="${jogoEditar.getData_publicaçao()}" required>
					<br><br>
				
					<!-- Campo numérico para o preço do jogo, permitindo valores com duas casas decimais e valor pré-preenchido -->
					<label for="preco" class="rotulo">Preço</label><br>
					<input type="number" id="preco" name="preco" step="0.01" class="campo" placeholder="R$" value="${jogoEditar.getPreço()}" required>
					<br><br>
				
					<!-- Botão para enviar o formulário com os dados editados -->
					<button type="submit">Enviar os dados</button>
				</form>
			</div>
		</main>
		<br><br><br>
		<!-- Rodapé fixo com a mensagem de direitos reservados -->
		<footer style="position: fixed; bottom: 0;">
				<h4 style="color: white; margin-left: 20px;">© Pataquada Indie 2024 - Todos os direitos reservados</h4>
		</footer>
	</body>
</html>