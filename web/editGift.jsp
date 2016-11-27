<%-- 
    Document   : editGift
    Created on : 2016/11/24, 下午 06:13:16
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="gift" scope="request" class="cf.bean.GiftItem"/>
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
        <title>Edit Gift</title>
    </head>
    <body>
       
    <center>
        <h1>Change Gift Details</h1>
       <br><br>
        <form method="POST" action="editGiftDetail">
            <input type="hidden" name="id" value="<%= gift.getGiftID() %>">
            <br>
            <label>Gift ID : <%= gift.getGiftID() %></label>
        <br><br>Gift Name : <input type="text" name="giftName" value="<%= gift.getName() %>">
        <br><br>Gift DescriptionS : <input type="text" name="desc" value="<%= gift.getDesc()%>" >
        <br><br>Nedded Points : <input type="text" name="needPoint" value="<%= gift.getPointRequired() %>">
        <br><br>Gift Image : <input type="text" name="img" value="<%= gift.getImgsrc() %>">
        <br><br><input type="submit" name="action" value="Confirm" onclick="return confirm('Are you sure to continue ?')">   <input type="submit" name="action" value="Cancel" onclick="return confirm('Are you sure to continue ?')">
        </form>
        
    </center>
        
    </body>
</html>
