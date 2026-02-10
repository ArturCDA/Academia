package br.com.academia.servlet;

import br.com.academia.dao.AlunoDAO;
import br.com.academia.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/aluno")
public class AlunoServlet extends HttpServlet {

    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    Aluno aluno = new Aluno();

    aluno.setNome(request.getParameter("nome"));
    aluno.setCpf(request.getParameter("cpf"));
    aluno.setEmail(request.getParameter("email"));
    aluno.setTelefone(request.getParameter("telefone"));
    aluno.setDataNascimento(LocalDate.parse(request.getParameter("dataNascimento")));
    aluno.setIdPlano(Integer.parseInt(request.getParameter("idPlano")));
    aluno.setIdPersonal(Integer.parseInt(request.getParameter("idPersonal")));

    String id = request.getParameter("idAluno");

    if (id == null || id.isEmpty()) {
        dao.salvar(aluno);
    } else {
        aluno.setIdAluno(Integer.parseInt(id));
        dao.atualizar(aluno);
    }

    response.sendRedirect("aluno-list.jsp");
}


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String acao = request.getParameter("acao");
    int id = Integer.parseInt(request.getParameter("id"));

    if ("remover".equals(acao)) {
        dao.remover(id);
        response.sendRedirect("aluno-list.jsp");

    } else if ("editar".equals(acao)) {
        Aluno aluno = dao.buscarPorId(id);
        request.setAttribute("aluno", aluno);
        request.getRequestDispatcher("aluno-form.jsp").forward(request, response);
    }
}

}
