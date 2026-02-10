package br.com.academia.dao;

import br.com.academia.model.Aluno;
import br.com.academia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void salvar(Aluno aluno) {

        String sql = """
            INSERT INTO aluno
            (nome, cpf, email, telefone, data_nascimento, id_plano, id_personal)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setDate(5, Date.valueOf(aluno.getDataNascimento()));
            stmt.setInt(6, aluno.getIdPlano());
            stmt.setInt(7, aluno.getIdPersonal());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno", e);
        }
    }

    public List<Aluno> listar() {

        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(rs.getInt("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));

                Date data = rs.getDate("data_nascimento");
                if (data != null) {
                    aluno.setDataNascimento(data.toLocalDate());
                }

                aluno.setIdPlano(rs.getInt("id_plano"));
                aluno.setIdPersonal(rs.getInt("id_personal"));

                alunos.add(aluno);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos", e);
        }

        return alunos;
    }

    public Aluno buscarPorId(int id) {
    String sql = "SELECT * FROM aluno WHERE id_aluno = ?";
    Aluno aluno = null;

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            aluno = new Aluno();
            aluno.setIdAluno(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setEmail(rs.getString("email"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            aluno.setIdPlano(rs.getInt("id_plano"));
            aluno.setIdPersonal(rs.getInt("id_personal"));
        }

    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    return aluno;
}


    public void atualizar(Aluno aluno) {

        String sql = """
            UPDATE aluno SET
                nome = ?,
                cpf = ?,
                email = ?,
                telefone = ?,
                data_nascimento = ?,
                id_plano = ?,
                id_personal = ?
            WHERE id_aluno = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setDate(5, Date.valueOf(aluno.getDataNascimento()));
            stmt.setInt(6, aluno.getIdPlano());
            stmt.setInt(7, aluno.getIdPersonal());
            stmt.setInt(8, aluno.getIdAluno());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }

    public void remover(int idAluno) {

        String sql = "DELETE FROM aluno WHERE id_aluno = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover aluno", e);
        }
    }
}
