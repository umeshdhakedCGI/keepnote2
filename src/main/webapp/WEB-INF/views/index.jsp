<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.stackroute.keepnote.model.Note" %>
<%@ page import="java.util.List" %>
<%@ page import="com.stackroute.keepnote.controller.NoteController" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>


<!DOCTYPE html>
<html lang="en">

<head>
	<title>Keep-Board</title>
</head>

<body>


<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->

<c:forEach items="${noteList}" var="note">
	<div style="text-align: center">
		<tr>
			<td>Note id is : ${note.getNoteId()} |</td>
			<td> Notes title is : ${note.getNoteTitle()} |</td>
			<td> Notes content is : ${note.getNoteContent()} |</td>
			<td> Notes status is : ${note.getNoteStatus()} |</td>
			<td> Note created at : ${note.getCreatedAt()} </td>
		</tr>
	</div>
</c:forEach>

<hr>



<!-- Create a form which will have text boxes for Note title, content and status along with a Add
     button. Handle errors like empty fields -->

<form action="/add" method="post">
<%--	Note Id:<br> <input type="text" name="noteId"> <br>--%>
	Note Title:<br> <input type="text" name="noteTitle"> <br>
	Note Content:<br> <input type="text" name="noteContent"> <br>
	Note Status:<br> <input type="text" name="noteStatus"> <br> <br>
	<input type="submit" value="Add">
</form>
<br>
<p>Enter Note Id and detail to update</p>
<form action="/update" method="post">
	Note Id:<br> <input type="text" name="noteId"> <br>
	Note Title:<br> <input type="text" name="noteTitle"> <br>
	Note Content:<br> <input type="text" name="noteContent"> <br>
	Note Status:<br> <input type="text" name="noteStatus"> <br> <br>
	<input type="submit" value="Update">
</form>
<br>
<p>enter note id to delete</p>
<form action="/delete" method="post">
	Note Id:<br> <input type="text" name="noteId"> <br>
	<input type="submit" value="Delete">
</form>



<br>

<p>Get Note Title by ID</p>
<form action="/index" method="post">
	Note Id:<br> <input type="text" name="getNotebyId"> <br>
	<input type="submit" value="Get Title">
</form>
<br>
<div style="text-align: center">
<tr>
	<td>${nodebyid.getNoteId()} </td>
	<td>${nodebyid.getNoteTitle()} </td>
</tr>
</div>


</body>

</html>