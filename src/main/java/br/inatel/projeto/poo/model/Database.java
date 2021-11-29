package br.inatel.projeto.poo.model;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection connection; // objeto responsável por fazer a conexão com mysql
    Statement statement; // objeto responsável por preparar consultas "SELECT"
    ResultSet result; // objeto responsável por executar consultas "SELECT"
    PreparedStatement pst; // objeto responsável por preparar querys de manipulação dinâmica(INSERT, UPDATE, DELETE)

    static final String user = "root"; // usuário da instância local do servidor
    static final String password = "17208"; // senha do usuário da instância local do servidor
    static final String database = "projeto"; // nome do banco de dados a ser utilizado

    // String com URL de conexão com o servidor
    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private boolean check = false;


    public void connect(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

    //--------------------INSERINDO NOVO REGISTRO--------------------
    public boolean insertAxie(Axie axie){
        connect();
        String sql = "INSERT INTO usuario(nome,cpf) VALUES (?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,user.getNome());// concatena nome no primeiro ? do comando
            pst.setString(2,user.getCpf());// concatena nome no segundo ? do comando
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }
    //--------------------BUSCANDO NOVO REGISTRO--------------------
    public ArrayList<User> researchUser(){
        connect();
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                User userTemp = new User(result.getString("nome"), result.getString("cpf"));
                userTemp.id = result.getInt("id");
                System.out.println("ID = " + userTemp.id);
                System.out.println("Nome = " + userTemp.getNome());
                System.out.println("CPF = " + userTemp.getCpf());
                System.out.println("---------------------------------");
                users.add(userTemp);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return users;
    }

    //--------------------ATUALIZANDO NOME DO REGISTRO--------------------
    public boolean updateUser(int id, String nome){
        connect();
        String sql = "UPDATE usuario SET nome=? WHERE id=?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,nome);
            pst.setInt(2,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }
    //--------------------EXCLUINDO REGISTRO--------------------
    public boolean deleteUser(int id){
        connect();
        String sql = "DELETE FROM usuario WHERE id =?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }


}
