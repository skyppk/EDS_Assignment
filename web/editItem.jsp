<%-- 
    Document   : editItem
    Created on : 2016/11/25, 上午 09:47:21
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="item" scope="request" class="cf.bean.ItemInfo"/>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item Info</title>
    </head>
    <body>
        <center>
        <form method="POST" action="editItem">
            <pre>
            <input type="hidden" name="id" value="<%=item.getId()%>">

            <img src="img/<%=item.getImg()%>">
            Item ID : <%=item.getItemId()%> 

            Item Name : <%=item.getItemName()%>

            Category : <%=item.getCategory()%>

            Designer Name : <%=item.getDesignerName()%>

            Price : <%=item.getPrice()%>

            Descriptions : <%=item.getDescriptions()%>

            Item Status : <%=item.getItemStatus()%>
            
            Change Values :

            <label>Designer Name : <input type="text" name="designerName" value="<%=item.getDesignerName()%>"></label>

            <label>Descriptions : <input type="text" name="descriptions" value="<%=item.getDescriptions()%>"></label>

            <label>Price : <input type="text" name="price" value="<%=item.getPrice()%>"></label>

            <input type="submit" name="action" value="Confirm" onclick="return confirm('Are you sure to continue ?')">   <input type="submit" name="action" value="Cancel" onclick="return confirm('Are you sure to continue ?')">
            </pre>
             
        </form>
        
        
        
        
    </center>
    </body>
</html>
