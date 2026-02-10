package br.com.academia;

import br.com.academia.util.ConnectionFactory;

public class TesteConexao {
    public static void main(String[] args) {
        ConnectionFactory.getConnection();
        System.out.println("Conectado ao PostgreSQL!");
    }
}

