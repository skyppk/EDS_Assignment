<%-- 
    Document   : customerMenu
    Created on : 2016/11/23, ?? 11:33:17
    Author     : nanasemaru
--%>

<jsp:useBean id="userInfo" class="cf.bean.UserInfo" scope="session" />
<%
    String login = request.getParameter("login");
%>
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

    .btn-inverse {
        background-color: #222;
        border-color: #ccc;
        color: #fff;

        &:hover{
            color: #fff;
            background-color: #000;
        }
    }

    .inverse-dropdown>li>a{
        color: white;
    }

    .inverse-dropdown{
        background-color: #222;
        border-color: #080808;
    }
</style>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">C & F Dress</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <div class="col-sm-3 col-md-3">
                <form class="navbar-form" role="search" method="post" action="searchItem">
                    <div class="input-group">
                        <div class="input-group-btn search-panel">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span id="search_concept" >All</span> <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" id="selection">
                                <li><a>All</a></li>
                                <li><a>Dresses</a></li>
                                <li><a>Jackets</a></li>
                                <li><a>Accessories</a></li>
                                <li class="divider"></li>
                                <li><a>Designer</a></li>
                            </ul>
                        </div>
                        <input type="hidden" name="action" value="All" id="action">         
                        <input type="text" class="form-control" name="keyword" placeholder="Search...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                        </span>
                    </div>
                </form>
            </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</a></li>

                <% if (login.equals("false")) { %>

                <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Registration</a></li>
                <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <% } else {%>

                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Welcome, <%=userInfo.getFirstName()%><span class="caret"></span></a>
                    <ul class="dropdown-menu inverse-dropdown">
                        <li><a href="profile.jsp">Profile</a></li>
                        <li><a href="#">Existing Order</a></li>
                        <li><a href="#">History</a></li>

                        <!--                        <li role="separator" class="divider"></li>
                                                <li><a href="#">Separated link</a></li>
                                                <li role="separator" class="divider"></li>
                                                <li><a href="#">One more separated link</a></li>-->
                    </ul>
                </li>
                <li><a href="#">Bonus Point: <%=userInfo.getBonusPoints()%></a></li>
                <li>
                    <form method="post" action="login" class="navbar-form">
                        <input type="hidden" name="action" value="logout">

                        <input type="submit" value="Logout" name="logoutButton" class="btn btn-default btn-inverse">
                    </form>
                </li>

                <!--<li><a href=""><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>-->
                <% }%>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div style="margin-top:50px"></div>
<div class="jumbotron">
    <div class="container">
        <h1>Classic and Fashion</h1>
        <p></p>
    </div>
</div>
<div class="nav">
    <div class="container">
        <ul class="nav nav-tabs">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="product?action=Dress">Dresses</a></li>
            <li><a href="product?action=Jacket">Jackets</a></li>
            <li><a href="product?action=Accessory">Accessories</a></li>
            <li><a href="contactUs.jsp">Contact Us</a></li>
        </ul>
    </div>
</div>
<div style="margin-top:10px"></div>

<script>
    $('#selection li a').click(function(){
        $('#search_concept').text($(this).text());
        $('#action').val($(this).text());
    });
</script>