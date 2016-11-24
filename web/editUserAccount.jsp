<%-- 
    Document   : editUserAccount
    Created on : 2016/11/25, 上午 05:13:03
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="request" class="cf.bean.UserInfo"/>
<jsp:useBean class="java.util.Vector" id="c" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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

            Credit Amount : <input type="text" name="credit" value="<%=user.getCreditAmount()%>">

            Bonus Point : <%=user.getBonusPoints()%>

            Deposit Money : <input type="text" name="money" value="<%=user.getMoney()%>">

            <input type="submit" name="action" value="Confirm" onclick="return confirm('Are you sure to continue ?')">   <input type="submit" name="action" value="Cancle" onclick="return confirm('Are you sure to continue ?')">
            </pre>
             
        </form>
        
        
        
        
    </center>
</body>
</html>
