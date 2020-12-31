<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>FreeMarker</title>
</head>
<body>
<table  border=\"5\" style="border: 1px; color: red;border-spacing: 2px; font-weight: bolder; ">
<#list tableList as table>
    <tr style="background-color: #428BCA; color:#ffffff;font-size: 60px; text-align: center ">
        <#list table as i>
            <td>
                ${i}
            </td>
        </#list>
    </tr>
</#list>
</table>

</body>
</html>