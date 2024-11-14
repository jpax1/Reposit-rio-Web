package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Jogos;
import Model.JogosDAO;

@WebServlet(urlPatterns ={"/insert", "/delete", "/update", "/view", "/update_view", "/search"})
public class JogosServlet extends HttpServlet {
    private JogosDAO jogosDAO;

    //Inicializando o objeto JogosDAO, responsável pela interação com o banco de dados\\
    public void init() {
        jogosDAO = new JogosDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Redireciona as requisições do POST para o método doGet\\
    	doGet(request, response);
    }

    // Obtém o caminho da ação a ser executada a partir da URL\\
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        action = action.substring(1,action.length());
        try {
        	
        	//Determinará a ação a ser executada com base na URL\\
            switch (action) {
            	
            //Chama o método de inserir um jogo\\
                case "insert":
                    insertJogos(request, response);
                    break;
                
                    //Chama o método responsável por deletar o jogo da lista\\
                case "delete":
                    deleteJogos(request, response);
                    break;
                
                    //Chama o método responsável por atualizar um jogo já cadastrado\\
                case "update":
                    updateJogos(request, response);
                    break;
                
                    //Prepara a exibição de um jogo para edição\\
                case "update_view":
                	updateViewJogos(request, response);
                	break;
                
                	//Responsável pela pesquisa dos jogos\\
                case "search":
                	searchJogos(request, response);
                	break;
                
                	//Lista todos os jogos\\
                default:
                    listJogos(request, response);
                    break;
            }
            
        //Tratamento de erro, lança a exceção em caso de erro no SQL\\    
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listJogos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	//Obtem todos os jogos cadastrados no banco de dados\\
        List<Jogos> listJogos = jogosDAO.selectAllJogos();
        //Formata a data de acordo com o padrão brasileiro
        for(Jogos jogo : listJogos) {
        	String[] data = jogo.getData_publicaçao().split("-");
        	jogo.setData_publicaçao(data[2] + " / " + data[1] + " / " + data[0]);
        }
        HttpSession session = request.getSession();
        //Armazena a lista de jogos na sessão e redireciona para a página principal\\
        session.setAttribute("listJogos", listJogos);
        response.sendRedirect("index.jsp");
    }

    private void insertJogos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	//Extrai os parâmetros para o cadastro de um novo jogo\\
        String titulo = request.getParameter("titulo");
        String descriçao = request.getParameter("descricao");
        String data_publicaçao = request.getParameter("data");
        double preço = Double.parseDouble(request.getParameter("preco"));
        
        //Cria o objeto Jogos e o insere no banco de dados\\
        Jogos novoJogo = new Jogos(titulo, descriçao, data_publicaçao, preço);
        jogosDAO.insertJogos(novoJogo);
        request.getRequestDispatcher("view").forward(request, response);
    }
    
    private void updateViewJogos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	
    	//Obtem o jogo a ser editado e o armazena na seção para ser exibido\\
    	HttpSession session = request.getSession();
    	session.setAttribute("jogoEditar",jogosDAO.updateViewJogos(Integer.parseInt(request.getParameter("idEditaJogo"))));
    	response.sendRedirect("EditarJogo.jsp");
    }

    private void updateJogos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	//Extrai os novos parâmetros após a edição\\
        int id = Integer.parseInt(request.getParameter("idJogoEditar"));
        String titulo = request.getParameter("titulo");
        String descriçao = request.getParameter("descricao");
        String data_publicaçao = request.getParameter("data");
        double preço = Double.parseDouble(request.getParameter("preco"));

        //Atualiza o objeto Jogos e salva no banco de dados\\
        Jogos jogo = new Jogos(titulo, descriçao, data_publicaçao, preço);
        jogo.setId(id);
        jogosDAO.updateJogos(jogo);
        
        //Redireciona para a visualização dos jogos atualizada\\
        request.getRequestDispatcher("view").forward(request, response);
    }

    private void deleteJogos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	//Obtem a id do jogo a ser deletado do banco de dados\\
        int id = Integer.parseInt(request.getParameter("idDeletaJogo"));
        
        //Deleta o jogo do banco de dados\\
        jogosDAO.deleteJogos(id);
        request.getRequestDispatcher("view").forward(request, response);
    }
    
    private void searchJogos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	//Realiza a pesquisa de jogos\\
    	Jogos jogo = jogosDAO.pesquisaJogos(request.getParameter("pesquisa"));
    	//Formata a data de acordo com o padrão brasileiro
    	if(jogo != null) {
    		String[] data = jogo.getData_publicaçao().split("-");
        	jogo.setData_publicaçao(data[2] + " / " + data[1] + " / " + data[0]);
    	}
    	//O jogo pesquisado é armazenado na sessão
    	HttpSession session = request.getSession();
    	session.setAttribute("resultadoPesquisa", jogo);
    	response.sendRedirect("Pesquisa.jsp"); //Redireciona para a página de resultado de pesquisa\\
    }
}