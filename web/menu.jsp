<%-- 
    Document   : menu
    Created on : 2016/11/20, ?? 10:07:36
    Author     : nanasemaru
--%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<jsp:useBean id="accountType" class="java.lang.String" scope="session" />
<jsp:useBean id="userInfo" class="cf.bean.UserInfo" scope="session"/>
<jsp:useBean id="staffInfo" class="cf.bean.StaffInfo" scope="session"/>

<style>
    .nav a {
        color: #5a5a5a;
        font-size: 11px;
        font-weight: bold;
        padding: 14px 10px;
        text-transform: uppercase;
    }
    .nav-tabs > li {
        float:none;
        display:inline-block;
        zoom:1;
    }
    .nav-tabs {
        text-align:center;
    }
</style>

        <%      
            if (accountType == null || accountType.equals("")) {
        %>
        <jsp:include page="customerMenu.jsp">
            <jsp:param name="login" value="false" />
        </jsp:include>
        <%
        } else if (accountType.equalsIgnoreCase("CUSTOMER") ) {
        %>
        <jsp:include page="customerMenu.jsp">
            <jsp:param name="login" value="true" />
            
        </jsp:include>
        <%
        } else if (!accountType.equalsIgnoreCase("CUSTOMER")) {
        %>
        <jsp:include page="adminMenu.jsp" />
        <% }%>