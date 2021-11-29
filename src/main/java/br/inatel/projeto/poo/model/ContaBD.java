package br.inatel.projeto.poo.model;

import br.inatel.projeto.poo.controller.Axie;
import br.inatel.projeto.poo.controller.Conta;

import java.sql.SQLException;

public class ContaBD {


    public boolean insertUser(Conta c){

        connect();
        String sql = "INSERT INTO usuario(nome,cpf) VALUES (?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,c.());// concatena nome no primeiro ? do comando
            pst.setString(2,a.getTipo());
            pst.setString(3,a.getPoder())
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
