<%-- 
    Document   : menu
    Created on : 2016/11/20, ?? 10:07:36
    Author     : nanasemaru
--%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<jsp:useBean id="userInfo" class="cf.bean.UserInfo" scope="session" />
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

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <!--            <ul class="nav navbar-nav">
                            <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                            <li><a href="#">Link</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">One more separated link</a></li>
                                </ul>
                            </li>
                        </ul>-->
            <!--            <form class="navbar-form navbar-left">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>-->
                        
            <% if (userInfo.getLoginId() == null || userInfo.getLoginId().equals("")) {%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</a></li>
                <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Registration</a></li>
                <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
            <% } else {%>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Welcome, <%=userInfo.getFirstName()%></a></li>
                <li><a href="#">Bonus Point: <%=userInfo.getBonusPoints()%></a></li>
                <li>
                    <form method="post" action="login" class="navbar-form">
                        <input type="hidden" name="action" value="logout">

                        <input type="submit" value="Logout" name="logoutButton" class="btn btn-default">
                    </form>
                </li>
            </ul>
            <!--<li><a href=""><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>-->
            <% }%>
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
            <li><a href="">Events</a></li>
            <li><a href="">Adventures</a></li>
            <li><a href="">Promotions</a></li>
            <li><a href="">Updates</a></li>
            
        </ul>
    </div>
</div>
<div style="margin-top:10px"></div>

<!--<div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span id="search_concept">Filter by</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#contains">Contains</a></li>
                        <li><a href="#its_equal">It's equal</a></li>
                        <li><a href="#greather_than">Greather than ></a></li>
                        <li><a href="#less_than">Less than < </a></li>
                        <li class="divider"></li>
                        <li><a href="#all">Anything</a></li>
                    </ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
                </div>-->