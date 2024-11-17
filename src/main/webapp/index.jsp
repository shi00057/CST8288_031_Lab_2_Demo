<%-- 
    Document   : index
    Created on : Nov 17, 2024, 3:47:54 p.m.
    Author     : Guokai Shi
    Purpose    : A simple JSP page to provide a button for listing Indy 500 winners.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Simple Indy 500 Winners List</title>
    <%-- 
        The title of the page displayed in the browser tab.
        Update this if needed for a more descriptive page title. 
    --%>
</head>
<body>
    <%-- 
        This form sends a GET request to the servlet 'IndyWinnerSimpleSV'.
        The 'page' parameter is initialized to 1 to display the first page of results. 
    --%>
    <form action="IndyWinnerSimpleSV" method="GET">
        <p>Click the button to List the Indy 500 Winners</p>
        <%-- Hidden input to specify the page number for pagination. --%>
        <input type="hidden" name="page" value="1">
        <%-- Submit button to trigger the request to the servlet. --%>
        <input type="submit" value="List the Indy 500 Winners">
    </form>
</body>
</html>
