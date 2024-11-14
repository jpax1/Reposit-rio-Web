package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class JogosDAO {
	
	//Configurando a conexão com o SQL\\
	private String jdbcURL = "jdbc:mysql://localhost:3306/pataquada";
	private String jdbcUsername = "root";
	private String jdbcPassword = "03Programaçao1@";
	
	//Declarando as constantes para consultar o banco de dados\\
	private final static String INSERT_JOGOS_SQL = "INSERT INTO jogos (titulo, descriçao, data_publicaçao, preço) VALUES (?, ?, ?, ?)";
	private final static String SELECT_JOGOS_BY_ID = "SELECT id, titulo, descriçao, data_publicaçao, preço WHERE id = ?";
	private final static String SELECT_ALL_JOGOS = "SELECT * FROM jogos";
	private final static String DELETE_JOGOS_SQL = "DELETE FROM jogos WHERE id = ?";
	private final static String UPDATE_JOGOS_SQL = "UPDATE jogos SET titulo = ?, descriçao = ?, data_publicaçao = ?, preço = ? WHERE id = ?";
	private final static String PESQUISA_JOGOS_BY_NAME = "SELECT * FROM jogos where titulo = ?";
	
	//Criando a conexão com o banco de dados\\
	protected Connection getConnection() {
		Connection conn = null;
		try {
			//Carregando o driver JDBC e conectando ao SQL\\
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	// Criando os métodos CRUD \\
	
	// Método Insert: insere um novo jogo na table Jogos \\
	public void insertJogos(Jogos jogos) throws SQLException{
		try(Connection connection = getConnection();
			//Definindos os valores dos parâmetros na instrução SQL\\
			PreparedStatement preparedstatement = connection.prepareStatement(INSERT_JOGOS_SQL)){
			 preparedstatement.setString(1, jogos.getTitulo());
	         preparedstatement.setString(2, jogos.getDescriçao());
	         preparedstatement.setString(3, jogos.getData_publicaçao());
	         preparedstatement.setDouble(4, jogos.getPreço());
	         
	         //Executa a instrução para inserir os dados no SQL\\
	         preparedstatement.executeUpdate();
		}
	}
	
	//Método Select: busca um jogo com base na id\\
	public Jogos selectAluno(int id) {
        Jogos jogos = null;
        try (Connection connection = getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_JOGOS_BY_ID)) {
            
        	//Define a id do jogo como parâmetro e executa a consulta\\
        	preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            //Processa o resultado da consulta\\
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String descriçao = rs.getString("descriçao");
                String data_publicaçao = rs.getString("data_publicaçao");
                double preço = rs.getDouble("preço");
                
                //Cria o objeto Jogos e define a id do jogo\\
                jogos = new Jogos(titulo, descriçao, data_publicaçao, preço);
                jogos.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Retorna o jogo encontrado\\
        return jogos;
    }

	//Método Select All: Exibe todos os registros\\
    public List<Jogos> selectAllJogos() {
    	//Criando a lista de exibição de todos os jogos\\
        List<Jogos> jogos = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_JOGOS)) {
            ResultSet rs = preparedStatement.executeQuery(); //Executa a consulta\\
            
            //Processa o resultado da pesquisa\\
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descriçao = rs.getString("descriçao");
                String data_publicaçao = rs.getString("data_publicaçao");
                double preço = rs.getDouble("preço");
                
                //Cria o objeto Jogos, define a ID como parâmetro e adiciona o jogo a lista\\
                Jogos jogo = new Jogos(titulo, descriçao, data_publicaçao, preço);
                jogo.setId(id);
                jogos.add(jogo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Retorna a lista com todos os jogos cadastrados\\
        return jogos;
    }

    //Método para preparar a exibição de um jogo para edição\\
    public Jogos updateViewJogos(int id){
    	try(Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement("select * from jogos where id=?")) {
    		ps.setInt(1,id); //Prepara a ID como parâmetro de seleção para a edição\\
    		ResultSet rs = ps.executeQuery(); //Executa a consulta\\
    		
    		//Processa o resultado da consulta\\
    		while(rs.next()) {
    			String titulo = rs.getString("titulo");
        		String descricao = rs.getString("descriçao");
        		String dataPublicacao = rs.getString("data_publicaçao");
        		double preço = rs.getDouble("preço");
        		
        		//Cria o objeto jogo, define a ID do jogo e retorna o jogo encontrado\\
        		Jogos jogo = new Jogos(titulo, descricao, dataPublicacao, preço);
        		jogo.setId(rs.getInt("id"));
        		return jogo;
    		}
    		
    		//Caso não seja possível encontrar o jogo requerido, o resultado volta nulo\\
    		return null;
    	} catch(SQLException SQLe) {
    		SQLe.printStackTrace();
    		return null;
    	}
    }
    
    //Método Update: Atualiza os dados de um jogo no banco de dados\\
    public void updateJogos(Jogos jogos){
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_JOGOS_SQL)) {
            
        	//Define os valores dos parâmetros na instrução do SQL\\
        	statement.setString(1, jogos.getTitulo());
            statement.setString(2, jogos.getDescriçao());
            statement.setString(3, jogos.getData_publicaçao());
            statement.setDouble(4, jogos.getPreço());
            statement.setInt(5, jogos.getId());
            
            //Executa a instrução para atualizar o registro no banco de dados\\
            statement.executeUpdate();
        } catch(SQLException SQLe) {
        	SQLe.printStackTrace();
        }
    }

    //Método Delete: Deleta um registro do banco de dados com base na id do jogo\\
    public void deleteJogos(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_JOGOS_SQL)) {
            statement.setInt(1, id); //Define a ID do jogo como parâmetro//
            statement.executeUpdate(); //Executa a instrução SQL para deletar o registro\\
        }
    }
    
    //Método Search: Pesquisa um jogo com base em seu titulo\\
    public Jogos pesquisaJogos(String titulo) {
    	try(Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement(PESQUISA_JOGOS_BY_NAME)) {
    		ps.setString(1, titulo); //Define o titulo como parâmetro principal\\
    		ResultSet rs = ps.executeQuery(); //Executa a consulta\\
    		
    		//Processa o resultado da consulta\\
    		while(rs.next()) {
    			int id = rs.getInt("id");
    			String descricao = rs.getString("descriçao");
    			String dataPublicacao = rs.getString("data_publicaçao");
    			double preco = rs.getDouble("preço");
    			
    			//Cria o objeto jogo, define a id do jogo e retorna o valor encontrado\\
    			Jogos jogo = new Jogos(titulo, descricao, dataPublicacao, preco);
    			jogo.setId(id);
    			return jogo;
    		}
    		
    		//Caso não encontre o jogo, o valor retorna nulo\\
    		return null;
    	} catch(SQLException SQLe) {
    		SQLe.printStackTrace();
    		return null;
    	}
    }
	
}