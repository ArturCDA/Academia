<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.academia.dao.AlunoDAO" %>
<%@ page import="br.com.academia.model.Aluno" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Alunos</title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
<div class="container">


<h2>Alunos Cadastrados</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>CPF</th>
        <th>Email</th>
        <th>Ações</th>
    </tr>

<%
    List<Aluno> alunos = null;

    try {
        AlunoDAO dao = new AlunoDAO();
        alunos = dao.listar();
    } catch (Exception e) {
%>
    <tr>
        <td colspan="5" style="color:red;">
            Erro ao carregar alunos: <%= e.getMessage() %>
        </td>
    </tr>
<%
    }

    if (alunos != null && !alunos.isEmpty()) {
        for (Aluno aluno : alunos) {
%>
    <tr>
        <td><%= aluno.getIdAluno() %></td>
        <td><%= aluno.getNome() %></td>
        <td><%= aluno.getCpf() %></td>
        <td><%= aluno.getEmail() %></td>
        <td>
            <a href="aluno?acao=editar&id=<%= aluno.getIdAluno() %>">Editar</a>
    |
            <a href="aluno?acao=remover&id=<%= aluno.getIdAluno() %>"
                onclick="return confirm('Deseja remover?');">
                Remover
            </a>
        </td>

    </tr>
<%
        }
    } else if (alunos != null) {
%>
    <tr>
        <td colspan="5">Nenhum aluno cadastrado.</td>
    </tr>
<%
    }
%>

</table>

<br>
<a href="index.jsp">Voltar</a>
</div>
</body>
</html>
