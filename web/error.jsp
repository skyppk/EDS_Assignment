<%-- 
    Document   : error
    Created on : 2016年11月25日, 上午02:47:21
    Author     : xeonyan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="js/typed.js"></script>
        <link rel="stylesheet" type="text/css" href="css/flip_table.css">
        <script>
            $(function () {
                jspErrMsg = "<%=  (exception!=null? exception.getMessage():"") %>";
                attrMsg = "<%=  (session.getAttribute("errmsg")!=null? session.getAttribute("errmsg"):"") %>";
                errMsg = decodeURI(location.search.split('msg=')[1]);
                
                setTimeout(function () {
                    $('#message').fadeIn(300);
                    $('#message').typed({
                        strings: [(jspErrMsg !="" ? jspErrMsg : (attrMsg!="" ? attrMsg : errMsg))],
                        typeSpeed: 0
                    });
                    setTimeout(function () {
                        $('#backHome').fadeIn(300);
                    }, 2000);
                    //console.log('test');
                }, 1780);
            });
        </script>
        <style>
            body{
                background: #000;
                font-family: "Courier New", fixed, monospace;
                font-size: 20px;
                letter-spacing: 1px;
            }
            .block {
                padding: 18px;
                margin-top: 20px;
                text-align: center;
                color: #32cd32;
                display: none;
            }
            a{
                text-decoration: none;
                color: #32cd32;
            }
            a:hover{
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="character">
                <div class="eye"></div>
                <div class="eye"></div>
                <div class="mouth"></div>
                <div class="arm"></div>
                <div class="arm"></div>
            </div>
            <div class="trajectory">
                <div class="curtain"></div>
            </div>
            <div class="table">
                <div class="legs"></div>
            </div>
        </div>
        <div id="message" class="block">
            
        </div>
        <div id="backHome" class="block">
            <a href="index.jsp">← BACK TO HOME</a>
        </div>
    </body>
</html>
