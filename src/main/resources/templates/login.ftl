<#import "template.ftl" as layout />

<@layout.page title="Welcome">
<#if error??>
<p style="color:red;">${error}</p>
</#if>
<form action="/login" method="POST">
    <input name="username" type="text" placeholder="Username"/>
    <input name="password" type="password" placeholder="Password"/>
    <input type="submit" value="Login" />
</form>
</@layout.page>
