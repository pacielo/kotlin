<#import "template.ftl" as layout />

<@layout.page title="Welcome">
<h1>Article n°${article.id}</h1>
<p>${article.text}</p>

<h2>Commentaires :</h2>
<div class="commentaires">
<#list article.comments as comment>

    <div class="block">
        <p>${comment.text}</p>
        <#if session??>
        <form method="POST" action="/article/${article.id}/comment/${comment.id}">
            <input class="delete" type="submit" value="Delete" />
        </form>
        </#if>
    </div>
</#list>

</div>
<form method="POST" action="/article/${article.id}">
    <textarea name="comment"></textarea><br />
    <input type="submit" value="Poster un commentaire">
</form>

</@layout.page>
