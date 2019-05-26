<#macro page title>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="/static/style.css">

</head>
<body>
<header>
    <nav>
            <div>
                <a href="/">Home</a>
        <#if session??>
                <a href="/article/post">New article</a>
            </div>
            <div>
                <span>${session}</span>
                <a class="logout" href="/logout">Déconnexion</a>
            </div>
        <#else>
            </div>
            <div>
                <a class="logout" href="/login">Connexion</a>
            </div>
        </#if>
    </nav>
</header>
<section>
<#nested/>
</section>
<!-- footer section -->
</body></html>
</#macro>