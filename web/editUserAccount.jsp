<%-- 
    Document   : editUserAccount
    Created on : 2016/11/25, 上午 05:13:03
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="request" class="cf.bean.UserInfo"/>
<%@ page errorPage="error.jsp"%>
<jsp:useBean id="staffInfo" class="cf.bean.StaffInfo" scope="session" />
<%
    System.out.println("test staff Info : " + staffInfo);
    if (staffInfo.getLoginId() == null) {
        response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Info</title>
    </head>
    <body>
        <script>
            if (staffInfo == null) {
                window.location = "error.jsp?msg=You have not permission to view this page !";
            }
        </script>
    <center>
        <form method="POST" action="editCustomer">
            <pre>
            <input type="hidden" name="loginId" value="<%=user.getLoginId()%>">
            Name : <%=user.getLastName()%>  <%=user.getFirstName()%>

            Sex : <%=user.getSex()%> Birthday: <%=user.getBirthday()%>

            Tel : <%=user.getTel()%>

            Address :<%=user.getAddress()%>

            Email : <%=user.getEmail()%>

            Money : <%=user.getMoney()%>

            Bonus Point : <%=user.getBonusPoints()%>

            Deposit Money : <input type="text" name="money" value="0">

            Credit Amount : <input type="text" name="credit" value="0">

            <input type="submit" name="action" value="Confirm" onclick="return confirm('Are you sure to continue ?')">   <input type="submit" name="action" value="Cancel" onclick="return confirm('Are you sure to continue ?')">
            </pre>
             
        </form>
        
        
        
        
    </center>
</body>
</html>
