<!-- Define a linguagem da página como Java e o tipo de conteúdo como HTML, especificando a codificação UTF-8 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Pataquada Indie</title>
		
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
							<input type="text" id="pesquisa" name="pesquisa" onclick="confirmarCampo('NAOdeletar')" placeholder="Pesquisar 🔍">
						</form>
					</li>
				</ul>
			</nav>
		</header>
		<main>
			<br><br><br>
			<h1 style="margin-left: 56px; margin-bottom: 14px;">RESULTADO DA PESQUISA</h1>
			<%if(session.getAttribute("resultadoPesquisa") == null) { 
				out.println("<br><br>");
				out.println("<h2 style='color: rgb(255,0,0); text-align: center;'>:( Opps. Nenhum Jogo Indie foi encontrado!</h2>");
			} else {
				
			%>
			<div class="jogos">
				<div class="jogo">
					<!-- Imagem do jogo padrão -->
					<div class="imagem">
						<img src="imagens/Controle_de_VideoGame.jpg" height="150px" width="200px">
					</div>
					<div class="conteudo">
					
						<!-- Título do jogo pesquisado -->
						<h1 style="text-align: center;">${resultadoPesquisa.getTitulo() }</h1>
					
						<!-- Descrição do jogo pesquisado -->
						<h4 style="text-align: center;">${resultadoPesquisa.getDescriçao()}</h4><br>
						
						<!-- Preço do jogo pesquisado -->
						<span>R$ ${resultadoPesquisa.getPreço()}</span><br>
						
						<!-- Data de publicação do jogo pesquisado -->
						<span>${resultadoPesquisa.getData_publicaçao()}</span><br>
						<ul class="botoes"> 
							<li class="botao1"><a href="update_view?idEditaJogo=${resultadoPesquisa.getId()}" onclick="confirmarCampo('NAOdeletar')" style="text-decoration: none;background-color: rgb(32, 189, 198); color: white; font-weight: bold; border: solid 8px rgb(32, 189, 198); border-radius: 10px;">Editar</a></li>
							<li class="botao2"><a href="delete?idDeletaJogo=${resultadoPesquisa.getId()}" onclick="confirmarCampo('deletar')" style="text-decoration: none;background-color: rgb(32, 189, 198); color: white; font-weight: bold; border: solid 8px rgb(32, 189, 198); border-radius: 10px;">Deletar</a></li>
						</ul>										
					</div>
				</div>
			</div>
			<%} %> <!-- Fecha o bloco de código condicional -->
		</main>
		<br><br><br>
		<!-- Rodapé fixo com a mensagem de direitos reservados -->
		<footer style="position: fixed; bottom: 0;">
				<h4 style="color: white; margin-left: 20px;">© Pataquada Indie 2024 - Todos os direitos reservados</h4>
		</footer>
		<script>
			//Confirmação se o usuário realmente quer reletar o jogo selecionado
			function confirmarCampo(deletar) {
				if(deletar === 'deletar') {
					document.addEventListener('click', function(event) {
						let confirmar = confirm("Tem certeza que deseja deletar este jogo?");
						if(!confirmar) {
							event.preventDefault();
						}
					})	
				}
			}
		</script>
	</body>
</html>