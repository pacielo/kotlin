<#import "template.ftl" as layout />

<@layout.page title="Welcome">
<h1>Articles :</h1>
<div>
<#list articles as article>
    <div class="block">
        <a href="/article/${article.id}">Article n°${article.id}</a>
        <#if session??>
            <form method="POST" action="/article/delete/${article.id}">
                <input class="delete" type="submit" value="Delete" />
            </form>
        </#if>
    </div>
</#list>
</div>

</@layout.page>
