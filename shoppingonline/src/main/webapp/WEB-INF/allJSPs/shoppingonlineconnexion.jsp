<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Connexion</title>
    <!--link href="CSS/default.css" rel="stylesheet" />
    
    <script src="Scripts/jquery-2.1.4.js"></script>
    <script src="Scripts/default.js"></script-->
    <style type="text/css">
    	*{
		    margin:0;
		    padding:0;
		}
		
		aside, footer, header, hgroup, nav, div{
		    display:block;
		    box-sizing:border-box;
		}
		
		body {
		    width:100%;
		    background-color: #F8F8F8;/*hsl(252, 95%, 95%);*/
		    font-family: Cambria, 'Times New Roman', serif;
		    color:green /*#0068AC*/;
		    box-sizing:border-box;
		}
		
		header {
		    height: 126.5px;
		    /*background-image: url('../images/imageLeKoYah.png');*/
		    /*background-repeat: no-repeat;*/
		    /*background-color:rgba(255,255,255,25);*/
		    margin-top: .5px;
		    margin-left: 2px;
		    margin-right: 2px;
		    margin-bottom: 3px;
		    box-sizing: border-box;
		    border-color: olive;
		    width:auto;
		    min-width:85.25em;
		    max-width:100%;
		    box-sizing:border-box;
		}
		
		label{
		    clear:both;
		    display:block;
		    float:left;
		    width:21.5%;
		    padding:7px;
		}
		
		#headerText{
		    position:absolute;
		    top:25px;
		    left:132px; 
		    /*width:100%;*/
		    margin-top:10px;
		}
		
		h1#LShopping{
		    font-family:Impact;
		    font-size:33pt;
		    text-align:center;
		    color:darkgreen;
		    background-color:white;
		    width:6em;
		    margin-left:auto;
		    margin-right:auto;
		    margin-top:0.02em;
		    margin-bottom:.15em;
		    padding:.05em;
		    border:double;
		    border-radius:3px;
		}
		
		h2{
		    display:block;
		    font-size:24px;
		    line-height:21.5px;
		    color:hsl(24, 100%, 50%);
		}
		
		h2#intitule{
		    width:42%;
		     margin-left:auto;
		    margin-right:auto;
		    margin-top:0.15em;
		    margin-bottom:.15em;
		}
		
		h3{
		    display:block;
		    font-size:14.5px;
		    line-height:16px;
		    font-style:italic;
		}
		img{
		    background:none repeat scroll 0 0 #F8F8F8;
		    border:outset;
		}
		nav{
		    position:relative;
		    
		    float:left;
		    width:15em/*19%*/;
		    min-width:17em;
		    padding-top:5em;
		    padding-left:10px;
		    margin-top:15px;
		    
		}
		 nav a#aaccueil{
		     margin-top:3em;
		 }
		
		#main{
		    position:relative;
		    
		    margin-top:5px;
		    padding:7px;
		    float:left;
		    width:77.25em/*60%*/;
		    border:outset;
		}
		
		form fieldset legend {
			font-size:12.5pt;
			font-weight: bold;
		    text-align:center;
		    background-color:hsl(153, 100%, 50%);
			color: blue;
			width: 40%;
			padding:0.2em;
		    margin-top:0.25em;
			margin-left:auto;
		    margin-right:auto;
		    margin-bottom:0.45em;
		    border-radius:15px;
		}
		
		aside{
		    position:relative;
		    
		    float:left;
		    width: 15em/*19%*/;
		    min-width:15em;
		    padding-left:10px;
		}
		
		nav, #main, footer{
		    box-sizing:border-box;
		}
		
		footer{
		    position:relative;
		    clear:both;
		    width:auto/*136.5em100%*/;
		    height:25px;
		    font-size:10px;
		    text-align:center;
		    color:hsl(150, 50%, 90%);
		    background-color:hsl(0, 0%, 50%);
		    box-sizing:border-box;
		    min-width:85.25em;
		    max-width:100%;
		    padding-top:0.425em;
		}
		
		#container{
		    display:block;
		    margin-top:0px;    
		    min-width:85.25em;
		    max-width:100%;
		    box-sizing:border-box;
		    
		}
		
		input, select, textarea {
		    width:15.75em/*px*/;
		    height:7%/*px*/;
		    text-align:left;
		    padding:7px;
		    border:inset;
		}
		
		button, #sconnexion, #sdeconnexion{
		    height:9%/*px*/;
		    border:outset;
		    padding:4px;
		    text-align:center;
		    border-radius:10px;
		}
		
		.bouton{   
		    background-color:olive;
		    width:7.5em/*px*/;
		    border-color:green;/*hsl(119, 90%, 24%);*/
		    color:lightyellow;
		    margin-left:24%;
		}
		
		.boutonreset{   
		    background-color:olive;
		    width:7.5em/*px*/;
		    border-color:green;/*hsl(119, 90%, 24%);*/
		    color:lightyellow;
		    margin-left:.75%;
		}
		
		
		button:hover{
		    background-color: hsl(150, 50%, 90%);
		}
		
		.bouton:hover, .boutonreset:hover{
		    background-color:hsl(0, 2%, 17%);
		    border-color: hsl(0, 9%, 80%);
		    color: hsl(0, 9%, 80%);
		}
		
		button:active{
		    border:inset;
		    border-width:thick;
		    border-color: hsl(250, 50%, 50%);
		    background-color:hsl(250, 50%, 50%);
		}
		
		.bouton:active, .boutonreset:active{
		    border:inset;
		    border-width:thick;
		    color:hsl(0, 2%, 17%);
		    border-color: hsl(0, 9%, 80%);
		    background-color:hsl(0, 9%, 80%);
		}
		
		
		input, button, label, select, textarea{
		    font-family:Arial;
		    font-size:14.75pt;
		    border-width:thick;
		    border-color:hsl(250, 100%, 100%);
		    margin: 3px;
		}
		
		
		[readonly]{
		    background-color:hsl(255, 50%, 80%);
		}
		
		
		.cClear{
		    color:hsl(250, 100%, 100%);
		    background-color:hsl(0, 100%, 50%);
		    font-weight:bold;
		}
		
		.cClear:hover{
		    background-color:hsl(24, 100%, 50%);
		}
		
		form input{
		    vertical-align:top;
		}
		
		form button{
		    vertical-align:top;
		}
		
		form textarea{
		    vertical-align:top;
		}
		
		form hr{
		    vertical-align:top;
		    margin:7px;
		}
		
		
		header #headerCommandeMenu{
		    position:absolute;
		    top:.5px;
		    width:99%;
		    height:55px;
		    margin-top:.1px;
		    padding:3px;
		    border: 0.1em green solid;
		    display:block;
		    background-color:tomato;
			clear:both;
		    min-width:85.25em;
		    max-width:100%;
		    box-sizing:border-box;
		}
					
		header #headerCommandeMenu span#sconnexion{
		    position:relative;
		    top: 0.65em;
		    background-color:hsl(205, 100%, 50%);
			width: 6.9em;
			margin-left:91.5%;
			border-radius :10px;
			z-index: 11500;
		}
								
		header #headerCommandeMenu span#sconnexion a#aconnexion{
			position:relative;
			color:lightyellow;
			text-align:center;
			text-decoration:none;
			padding:0.15em;
			width: 100%;
			z-index: 11500;
		}
								
		header #headerCommandeMenu span#sdeconnexion{	
		    position:relative;
		    top: -1em;
			/*top: 1em;
			left:73.5em;*/
		    background-color:hsl(205, 100%, 50%);
			width: 6.7em;
		    margin-left:86.5%;
			border-radius :10px;
			z-index: 11500;
		}
								
		header #headerCommandeMenu span#sdeconnexion a#adeconnexion{
			position:relative;
			text-align:center;
			text-decoration:none;
		    color:lightyellow;
			padding:0.15em;
			width: 100%;
			z-index: 11500;
		}
					
		header #headerCommandeMenu span#saccueil{
			position:relative;
			top:0.6em;
		    background-color: white;
			width: 50px;
			padding:0.15em;
			text-align:center;
			height: 50px;
			border-radius :10px;
		    margin-top:0.1em;
		    margin-bottom:0.1em;
		    margin-left:0.1em;
		    box-sizing:border-box;
			z-index: 11500;
		}
					
		header #headerCommandeMenu span#saccueil a#aaccueil{
			
			color:green;
			text-align:center;
			text-decoration:none;
		    height:98%;				
			width: 98%;
			z-index: 11500;
		}
					
		header #headerCommandeMenu span#saccueil a#aaccueil img#iaccueil{
			padding:0.07em;
			color:green;
			height:50px;
			width: 41px;
			z-index: 11500;
		}
					
		header #headerCommandeMenu span#sconnexion:HOVER {
				background-color: darkgray;
				color:yellow ;
				border: 0.1em yellow solid;
				z-index: 11500;
		}
					
		header #headerCommandeMenu span#sconnexion a#aconnexion:HOVER{
				color:yellow ;
				z-index: 11500;
		}
					
		header #headerCommandeMenu span#sdeconnexion:HOVER {
				background-color: darkgray;
				color:red ;
				border: 0.1em red solid;
				z-index: 11500;
		}
					
		header #headerCommandeMenu span#sdeconnexion a#adeconnexion:HOVER{
				color:red ;
				z-index: 11500;
		}
					
		header #headerCommandeMenu span#saccueil:HOVER {
				background-color: white;
				/*border: 0.1em yellow solid;*/
				z-index: 11500;
		}
					
		header #headerCommandeMenu span#saccueil a#aaccueil:HOVER{
				color:darkred ;
				z-index: 11500;
		}
					
		header #headerCommandeMenu span#saccueil a#aaccueil img#iaccueil:HOVER{
				color:darkred ;
				z-index: 11500;
		}
		
		header span#sbasket{
			position:relative;
		    top: 4.2em;
		    background-color:navy;/*hsl(205, 100%, 50%);*/
			width: 10.5em;
			height:3.15em;
			padding:0.15em;
			margin-left:86.5%;
			border-radius :7px;
			z-index: 9500
		}
		
		header span#sbasket a#abasket{
			position:relative;
			color:orange;
			text-align:center;
			text-decoration:none;
			padding:0.5em;
			width: 100%;
			z-index: 9500;
		}
		
		header span#sbasket:HOVER{
		    height:2.5em;
			background-color: darkgray;
			color:tomato;
			border: 0.1em yellow solid;
			z-index: 9500;
		}
		
		header span#sbasket a#abasket:HOVER{
			color:tomato;
			z-index: 9500;
		}
		
		
		div#divSearch form {
			position: absolute;
			top:3.5em;
			width:55em;
			margin:auto;
			padding:0.2em;
			margin-left:21em;
			background-color:fffddd;
			border: 0.1em green solid;
			display: block;
			overflow:auto;
			border-radius :10px;
			z-index: 1001;
		}
							
		div#divSearch form fieldset {
			position: relative;
			padding:0.12%;
			margin: auto;
			border: 0.1% #0568CD solid;
			width:99%;
			display: block;
			overflow: no-display;
			border-radius :10px;
			z-index: 1002;
		}
								
		div#divSearch form fieldset legend {
			font-size:16pt;
			font-weight: bold;
			color: navy;
			width: 35%;
			padding:0.15%;
		}
				
		div#divSearch form fieldset p#pannonce {
			font-size:14.5pt;
			font-weight: bold;
			padding:0.4%;
			color: blue;
			margin-left:auto;
			margin-right:auto;
		}
					
		#motbienvenue p#info {
			font-style:italic;
			color: #E8A22B;
		}
		
		
		#main h2{
		    font-size:15.5pt;
		    display:block;
		    background-color:hsl(252, 90%, 90%);
		    text-align:center;
		    padding:0.25em;
		    margin-left:auto;
		    margin-right:auto;
		    margin-top:0;
		    margin-bottom:.7em;
		    border-radius :5px;
		    width:auto;
		}
		#main #divimagepcple{
			float:left;
			font-size:13.5pt;
			font-weight: bold;
			width:60%;
		    padding:1.5em;
		    box-sizing:border-box;
		}
		
		#main #divimagepcple img{
			margin-left:auto;
		    margin-right:auto;
		    margin-bottom: .7em;
		    width: 7em;
		    height: 5em;
		}
		
		#main #divimagepcple .ckbox{
			margin-left:.5em;
		    width: 0.75em;
		    height: 0.75em;
		}
		
		
		#main #divimagepcple label{
			margin:0;
			width: 60%;
		}
		
		#main #divimagepcple #connexionBtn {
			margin-left:1em;
			margin-bottom:0.25em;
			margin-top:0;
			margin-right:0.4em;
		}
		
		#main #divimagepcple #resetBtn{
			margin-left:0;
			margin-right:0;
			margin-top:0;
			margin-bottom:0.25em;
		}
		
		
		#main #divimagepcple #personConnexion{
			width: 12em;
		}
		
		#main #divimagepcple #personPassword{
			width: 12em;
		}
		
		
		#main #divmotbienvenue{
			float:left;
			font-size:14pt;
		    margin:1px;
		    padding:1px;
			width:37%;
		    box-sizing:border-box;
		}
		
		#main #divmotbienvenue p{
		    text-align:justify;
		    margin-left:auto;
		    margin-right:auto;
		    margin-top:.25em;
		    margin-bottom:.05em;
			display:block;
		}
		
		#main .divmain{
			/*display:inline-block;*/
		    color:navy/*#0068AC*/;
		}
		
		/*==============================================================================================================================================
		    La boîte aside
		    =======================================================================
		*/		
		aside h2{
		    font-size:25pt;
		    display:block;
		    
		    text-align:center;
		    padding:1.25em;
		    margin-left:auto;
		    margin-right:auto;
		    margin-top:1em;
		    border-radius :15px;
		}
    
    
    
    </style>
</head>
<body>
    <div id="container">
        <header>
            <div id="headerCommandeMenu">
                <span id="saccueil"><a id="aaccueil" href="<spring:url value='/' />">Home</a></span>
            </div>
            
			<span id="sbasket" class="bouton"><a id="abasket" href='<spring:url value="/showbasket"></spring:url>'>your Basket ${basketsize}</a></span>
		</header>

        <nav>
            
        </nav>
        
        <div id="main">
            <h2>${connexion}</h2>
            <hr />
            
            <div id="divimagepcple" class="divmain">
				<form action='<spring:url value="/makeconnexion"></spring:url>' method="post">
					<fieldset>
						<br /> <label for="personConnexion">Connexion name</label><br /> <input
							type="text" id="personConnexion" name="personConnexion" size="20"
							maxlength="20" size="25"
							placeholder="Enter your connexion name, ..." /><span
							id="spersonConnexion"></span><br /> <label for="personPassword">Pass
							word</label><br /> <input type="password" id="personPassword"
							name="personPassword" size="20" maxlength="20" size="25"
							placeholder="Enter your connexion name, ..." /><span
							id="spersonPassword"></span><br /> <br />
					</fieldset>

					<fieldset>
						<br />
						<button type="submit" id="connexionBtn" name="connexion"
							class="bouton">Connect</button>
						<button type="reset" id="resetBtn" name="resetBtn" class="bouton">Cancel</button>
						<br />
					</fieldset>
					<br /> <br /> <a id="anewuser"
						href="<spring:url value='/newuser' />">New user, get your
						account here !!!</a> <br />
				</form>
			</div>
            
            <div id="divmotbienvenue" class="divmain">
                
            </div>
  
        </div>
        
        <aside>
            
        </aside>
        
        <footer>
            <p>
                Copyright &copy; 2016, <a href="mail:juoud1@gmail.com">Centre de formation</a>, GNU Licence
            </p>
        </footer>
    </div>
</body>
</html>
