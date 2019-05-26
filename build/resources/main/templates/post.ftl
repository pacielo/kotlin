<#import "template.ftl" as layout />

<@layout.page title="Welcome">
<h1>Rédiger un article :</h1>
<form method="POST" action="/article/post">
    <textarea name="article"></textarea><br />
    <input type="submit" value="Poster l'article">
</form>
</@layout.page>
