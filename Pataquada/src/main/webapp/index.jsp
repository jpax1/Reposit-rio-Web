<!-- Define a linguagem da página como Java e o tipo de conteúdo como HTML, especificando a codificação UTF-8 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Importa a classe ArrayList e a classe Jogos para uso na página -->
<%@ page import="java.util.ArrayList" %>
<%@ page import ="Model.Jogos" %>

<!-- Importa a biblioteca de tags JSP Standard Tag Library (JSTL) para controle de fluxo e interação com dados no JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
							<input type="text" id="pesquisa" onclick="confirmarCampo('NAOdeletar')" name="pesquisa" placeholder="Pesquisar 🔍">
						</form>
					</li>
				</ul>
			</nav>
		</header>
		<main>
			<br><br><br>
			<!-- Botão que direciona para a página 'CadastroJogo.jsp' para adicionar um novo jogo -->
			<h1 style="margin-left: 56px; margin-bottom: 14px;">MEUS JOGOS</h1>
			<a href="CadastroJogo.jsp" onclick="confirmarCampo('NAOdeletar')" style="text-decoration: none;background-color: rgb(32, 189, 198); color: white; font-weight: bold; border: solid 8px rgb(32, 189, 198); border-radius: 10px; position: absolute; right: 5%; top: 20%;">Adicionar Jogos Indie</a>
			<%	//Recupera a lista de jogos da seção\\
				ArrayList<Jogos> jogos = (ArrayList<Jogos>) session.getAttribute("listJogos");
				//Verifica se a lista de jogos não é nula antes de retornar o resultado\\
				if(jogos != null && !jogos.isEmpty()) {
				%>
			<div class="jogos">
				<!-- Itera sobre cada jogo na lista de jogos usando JSTL -->
				<c:forEach items="${listJogos}" var="jogo">
					<div class="jogo">
						<div class="imagem">
							<!-- Exibe uma imagem padrão para cada jogo -->
							<img src="imagens/Controle_de_VideoGame.jpg" height="150px" width="200px">
						</div>
						<!-- Exibe os detalhes do jogo como titulo, descrição, preço e data de publicação -->
						<div class="conteudo">
							<h1 style="text-align: center;">${jogo.getTitulo()}</h1>
							<h4 style="text-align: center;">${jogo.getDescriçao()}</h4><br>
							<span>R$ ${jogo.getPreço()}</span><br>
							<span>${jogo.getData_publicaçao()}</span><br>
							<!-- Links para editar ou deletar o jogo correspondente -->
							<ul class="botoes">
								<li class="botao1"><a href="update_view?idEditaJogo=${jogo.getId()}" onclick="confirmarCampo('NAOdeletar')" style="text-decoration: none;background-color: rgb(32, 189, 198); color: white; font-weight: bold; border: solid 8px rgb(32, 189, 198); border-radius: 10px;">Editar</a></li>
								<li class="botao2"><a href="delete?idDeletaJogo=${jogo.getId()}"  onclick="confirmarCampo('deletar')" style="text-decoration: none;background-color: rgb(32, 189, 198); color: white; font-weight: bold; border: solid 8px rgb(32, 189, 198); border-radius: 10px;">Deletar</a></li>
							</ul>										
						</div>
					</div>
				</c:forEach>
				<%} else {
					// Caso a lista esteja vazia ou nula, exibe uma mensagem informando que não há jogos indie
					out.println("<br><br>");
					out.println("<h2 style='color: rgb(255,0,0); text-align: center;'>:( Opps. Não há nenhum jogo indie!</h2>");
				}
				%>
			</div>
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