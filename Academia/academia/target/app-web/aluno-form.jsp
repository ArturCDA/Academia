<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="br.com.academia.model.Aluno" %>

<%
    Aluno aluno = (Aluno) request.getAttribute("aluno");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Aluno</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">

<h2>Cadastro de Aluno</h2>

<form action="aluno" method="post">

    <input type="hidden" name="idAluno"
           value="<%= aluno != null ? aluno.getIdAluno() : "" %>">

    <label>Nome:</label>
    <input type="text" name="nome"
           value="<%= aluno != null ? aluno.getNome() : "" %>" required>

    <label>CPF:</label>
    <input type="text" name="cpf"
           value="<%= aluno != null ? aluno.getCpf() : "" %>" required>

    <label>Email:</label>
    <input type="email" name="email"
           value="<%= aluno != null ? aluno.getEmail() : "" %>" required>

    <label>Telefone:</label>
    <input type="text" name="telefone"
           value="<%= aluno != null ? aluno.getTelefone() : "" %>" required>

    <label>Data de Nascimento:</label>
    <input type="date" name="dataNascimento"
           value="<%= aluno != null && aluno.getDataNascimento() != null
                    ? aluno.getDataNascimento()
                    : "" %>">

    <label>ID do Plano:</label>
    <input type="number" name="idPlano"
           value="<%= aluno != null ? aluno.getIdPlano() : "" %>" required>

    <label>ID do Personal:</label>
    <input type="number" name="idPersonal"
           value="<%= aluno != null ? aluno.getIdPersonal() : "" %>" required>

    <button type="submit">Salvar</button>

</form>

<br>
<a href="index.jsp">Voltar</a>

</div>

</body>
</html>
