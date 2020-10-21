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
    <#--    from表单、input、提交按钮 数据返回controller-->
    <div style="padding: 20px 80px 10px;">
        <form class="bs-example bs-example-form" role="form" method="post">
            <div class="row">
                <div class="col-lg-6">
                    <#--                    <div class="input-group">-->

                    <h4>当前日期: (${dateuser})(默认当前日期)</h4><input type="date" class="form-control" id="date" name="date">
                    <h4>去年同期: (默认去年同期)</h4><input type="date" disabled="disabled" class="form-control" id="beforedate"
                                                  name="beforedate">
                    <h4>对比天数: (最高对比天数7 最低对比天数1)</h4><input type="text" class="form-control" id="day" name="day"
                                                           placeholder="7" oninput="value=value.replace(/[^\d]/g,'')">
                    GSM无线话务量的指标值: <input type="checkbox" name="WX_004_0019" id="WX_004_0019" value="WX_004_0019"
                                         checked/><br>
                    2G数据流量(GB): <input type="checkbox" name="WX_004_1010" id="WX_004_1010" value="WX_004_1010"/><br>
                    TD无线话务量(erl): <input type="checkbox" name="WX_004_0020" id="WX_004_0020" value="WX_004_0020"/><br>
                    TD数据流量(GB): <input type="checkbox" name="WX_004_1011" id="WX_004_1011" value="WX_004_1011"/><br>
                    WLAN数据流量(GB): <input type="checkbox" name="WX_004_1012" id="WX_004_1012" value="WX_004_1012"/><br>


                    <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                确定
                            </button>
                        </span>
                    <#--                    </div>-->

                </div>
            </div>

        </form>
    </div>


    <#--    <button class="btn btn-default"  id="btn" value="导出">-->
    <#--        导出-->
    <#--    </button>-->

</div>


<form class="bs-example bs-example-form" role="form" method="post">
    <input type="submit" value="全部导出" class="btn btn-default" name="btn" id="btn">
</form>


<#if user2_0019?? && (user2_0019?size > 0) >
<#-- 不为空显示表格-->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>0019</th>
            <#list coldateArr as coldateArr>
                <td>${coldateArr}</td>
            </#list>

            <#list Comparedatecolumn_0019 as Comparedatecolumn_0019>
                <td>${Comparedatecolumn_0019}</td>
            </#list>

            <#list Comparedatecolumn_0019 as Comparedatecolumn_0019>
                <td>同比增幅</td>
            </#list>

        </tr>
        </thead>
        <tbody>
        <#list CITY_ID as CITY_ID>
            <tr>
                <td>${CITY_ID}</td>
                <#list contrastday as contrastday>
                    <td>${WX_004_0019arr_0019[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

                <#list contrastday as contrastday>
                    <td>${BeforeDate_0019[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>


                <#list contrastday as contrastday>
                    <td>${ResultsArr_0019[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

            </tr>
        </#list>
        <tr>
            <td>全省</td>
            <#list coldateArr as coldateArr>
                <td>${user2_0019[coldateArr_index]}</td>
            </#list>

            <#list CompareprovinceArr_0019 as CompareprovinceArr_0019>
                <td>${CompareprovinceArr_0019}</td>
            </#list>

            <#list ResultsArr1_0019 as ResultsArr1_0019>
                <td>${ResultsArr1_0019}</td>
            </#list>
        </tr>
        </tbody>
    </table>
</#if>

<#if user2_1010?? && (user2_1010?size > 0) >
<#-- 不为空显示表格-->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>1010</th>
            <#list coldateArr as coldateArr>
                <td>${coldateArr}</td>
            </#list>

            <#list Comparedatecolumn_1010 as Comparedatecolumn_1010>
                <td>${Comparedatecolumn_1010}</td>
            </#list>

            <#list Comparedatecolumn_1010 as Comparedatecolumn_1010>
                <td>同比增幅</td>
            </#list>

        </tr>
        </thead>
        <tbody>
        <#list CITY_ID as CITY_ID>
            <tr>
                <td>${CITY_ID}</td>
                <#list contrastday as contrastday>
                    <td>${WX_004_0019arr_1010[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

                <#list contrastday as contrastday>
                    <td>${BeforeDate_1010[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>


                <#list contrastday as contrastday>
                    <td>${ResultsArr_1010[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

            </tr>
        </#list>
        <tr>
            <td>全省</td>
            <#list coldateArr as coldateArr>
                <td>${user2_1010[coldateArr_index]}</td>
            </#list>

            <#list CompareprovinceArr_1010 as CompareprovinceArr_1010>
                <td>${CompareprovinceArr_1010}</td>
            </#list>

            <#list ResultsArr1_1010 as ResultsArr1_1010>
                <td>${ResultsArr1_1010}</td>
            </#list>
        </tr>
        </tbody>
    </table>
</#if>

<#if user2_0020?? && (user2_0020?size > 0) >
<#-- 不为空显示表格-->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>0020</th>
            <#list coldateArr as coldateArr>
                <td>${coldateArr}</td>
            </#list>

            <#list Comparedatecolumn_0020 as Comparedatecolumn_0020>
                <td>${Comparedatecolumn_0020}</td>
            </#list>

            <#list Comparedatecolumn_0020 as Comparedatecolumn_0020>
                <td>同比增幅</td>
            </#list>

        </tr>
        </thead>
        <tbody>
        <#list CITY_ID as CITY_ID>
            <tr>
                <td>${CITY_ID}</td>
                <#list contrastday as contrastday>
                    <td>${WX_004_0019arr_0020[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

                <#list contrastday as contrastday>
                    <td>${BeforeDate_0020[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>


                <#list contrastday as contrastday>
                    <td>${ResultsArr_0020[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

            </tr>
        </#list>
        <tr>
            <td>全省</td>
            <#list coldateArr as coldateArr>
                <td>${user2_0020[coldateArr_index]}</td>
            </#list>

            <#list CompareprovinceArr_0020 as CompareprovinceArr_0020>
                <td>${CompareprovinceArr_0020}</td>
            </#list>

            <#list ResultsArr1_0020 as ResultsArr1_0020>
                <td>${ResultsArr1_0020}</td>
            </#list>
        </tr>
        </tbody>
    </table>
</#if>

<#if user2_1011?? && (user2_1011?size > 0) >
<#-- 不为空显示表格-->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>1011</th>
            <#list coldateArr as coldateArr>
                <td>${coldateArr}</td>
            </#list>

            <#list Comparedatecolumn_1011 as Comparedatecolumn_1011>
                <td>${Comparedatecolumn_1011}</td>
            </#list>

            <#list Comparedatecolumn_1011 as Comparedatecolumn_1011>
                <td>同比增幅</td>
            </#list>

        </tr>
        </thead>
        <tbody>
        <#list CITY_ID as CITY_ID>
            <tr>
                <td>${CITY_ID}</td>
                <#list contrastday as contrastday>
                    <td>${WX_004_0019arr_1011[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

                <#list contrastday as contrastday>
                    <td>${BeforeDate_1011[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>


                <#list contrastday as contrastday>
                    <td>${ResultsArr_1011[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

            </tr>
        </#list>
        <tr>
            <td>全省</td>
            <#list coldateArr as coldateArr>
                <td>${user2_1011[coldateArr_index]}</td>
            </#list>

            <#list CompareprovinceArr_1011 as CompareprovinceArr_1011>
                <td>${CompareprovinceArr_1011}</td>
            </#list>

            <#list ResultsArr1_1011 as ResultsArr1_1011>
                <td>${ResultsArr1_1011}</td>
            </#list>
        </tr>
        </tbody>
    </table>
</#if>

<#if user2_1012?? && (user2_1012?size > 0) >
<#-- 不为空显示表格-->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>1012</th>
            <#list coldateArr as coldateArr>
                <td>${coldateArr}</td>
            </#list>

            <#list Comparedatecolumn_1012 as Comparedatecolumn_1012>
                <td>${Comparedatecolumn_1012}</td>
            </#list>

            <#list Comparedatecolumn_1012 as Comparedatecolumn_1012>
                <td>同比增幅</td>
            </#list>

        </tr>
        </thead>
        <tbody>
        <#list CITY_ID as CITY_ID>
            <tr>
                <td>${CITY_ID}</td>
                <#list contrastday as contrastday>
                    <td>${WX_004_0019arr_1012[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

                <#list contrastday as contrastday>
                    <td>${BeforeDate_1012[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>


                <#list contrastday as contrastday>
                    <td>${ResultsArr_1012[CITY_ID_index][contrastday_index]!'-'}</td>
                </#list>

            </tr>
        </#list>
        <tr>
            <td>全省</td>
            <#list coldateArr as coldateArr>
                <td>${user2_1012[coldateArr_index]}</td>
            </#list>

            <#list CompareprovinceArr_1012 as CompareprovinceArr_1012>
                <td>${CompareprovinceArr_1012}</td>
            </#list>

            <#list ResultsArr1_1012 as ResultsArr1_1012>
                <td>${ResultsArr1_1012}</td>
            </#list>
        </tr>
        </tbody>
    </table>
</#if>
</body>
<#--<script>-->
<#--    $(function () {-->
<#--        $("#btn").click(function () {-->
<#--            var xmlhttp;-->
<#--            if (window.XMLHttpRequest)-->
<#--            {// code for IE7+, Firefox, Chrome, Opera, Safari-->
<#--                xmlhttp=new XMLHttpRequest();-->
<#--            }-->
<#--            else-->
<#--            {// code for IE6, IE5-->
<#--                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");-->
<#--            }-->
<#--            xmlhttp.open("POST","SelectAll",true);-->
<#--            xmlhttp.send("btn=6666");-->
<#--        })-->
<#--    })-->


<#--</script>-->

</html>