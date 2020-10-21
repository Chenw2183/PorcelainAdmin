<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<#--绿色顶部栏-->
<div class="alert alert-success">

    <#--显示当前日期 或者 显示用户输入的日期-->
    当前选择的日期：${nowdate}

    <#--    from表单、input、提交按钮 数据返回controller-->
    <div style="padding: 20px 0px 10px;">
        <form class="bs-example bs-example-form" role="form" method="post">
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group">
                        <input type="date" class="form-control" id="date" name="date">
                        <input type="text" class="form-control" id="day" name="day" placeholder="对比天数">

                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                确定
                            </button>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </div>

</div>


<#--freemarker判断查询的数据是否为空-->
<#if all?? && (all?size > 0) >

<#-- 不为空显示表格-->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>GSM无线话务量的指标值</th>
            <#list coldateArr as coldateArr>
                <td>${coldateArr}</td>
            </#list>
        </tr>
        </thead>
        <tbody>
        <tr>
            <#-- 591-福州，592-厦门，593-宁德，594-莆田，595-泉州，596-漳州，597-龙岩，598-三明，599-南平-->
            <td>福州</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[0][i]!'-'}</td>
            </#list>

        </tr>
        <tr>
            <td>厦门</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[1][i]!'-'}</td>
            </#list>

        </tr>
        <tr>
            <td>宁德</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[2][i]!'-'}</td>
            </#list>
        </tr>
        <tr>
            <td>莆田</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[3][i]!'-'}</td>
            </#list>

        </tr>
        <tr>
            <td>泉州</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[4][i]!'-'}</td>
            </#list>

        </tr>
        <tr>
            <td>漳州</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[5][i]!'-'}</td>
            </#list>

        </tr>
        <tr>
            <td>龙岩</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[6][i]!'-'}</td>
            </#list>

        </tr>
        <tr>
            <td>三明</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[7][i]!'-'}</td>
            </#list>

        </tr>
        <tr>
            <td>南平</td>
            <#list 0..6 as i>
                <td>${WX_004_0019arr[8][i]!'-'}</td>
            </#list>

        </tr>
        <tr>
            <td>全省</td>
            <#list 0..6 as i>
                <td>${user2[i]!'-'}</td>
            </#list>
        </tr>
        </tbody>
    </table>

<#--    判断日期输入的格式是否正确-->
<#elseif dateTF == "false">
    <div class="alert alert-warning">
        <a href="#" class="close" data-dismiss="alert">
            &times;
        </a>
        <strong>您日期输入错误</strong>！！
    </div>
<#else>

<#--    为空显示没有数据-->

    <div class="alert alert-warning">
        <a href="#" class="close" data-dismiss="alert">
            &times;
        </a>
        <strong>当前没有数据展示</strong>！！
    </div>
</#if>

</body>
</html>