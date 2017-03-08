<%--
  Created by IntelliJ IDEA.
  User: 17854
  Date: 2016/7/2
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resources/assets/js/dataTables/jquery.dataTables.js"></script>
<script src="${ctx}/resources/assets/js/dataTables/jquery.dataTables.bootstrap.js"></script>
<script src="${ctx}/resources/assets/js/dataTables/extensions/TableTools/js/dataTables.tableTools.js"></script>
<script src="${ctx}/resources/assets/js/dataTables/extensions/ColVis/js/dataTables.colVis.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#list-6").addClass("active");
        $("#list-6").addClass("open");
        $("#list-6-3").addClass("active");
    })
    $("#find").click(function(){
        var count=$("#simple-table").find("td").length;
        var findStr=$("#publisher_find").val();
        for(var i=1;i<=count;i++)
        {
            console.log($("#td"+i).text().trim()+"    "+findStr);
            if($("#td"+i).attr("showit")!="none"&&$("#td"+i).text().trim()==findStr)
            {
                var inn=
                        "&\n"+
                        "<div class=\"hidden-sm hidden-xs btn-group\">\n"+
                        "<button class=\"btn btn-xs btn-danger\" onclick=\"deleteit(&)\">\n"+
                        "<i class=\"ace-icon fa fa-trash-o bigger-120\"></i>\n"+
                        "</button>\n"+
                        "</div>\n";

                $("#td"+i).html(inn.replace("&",$("#td1").text().trim()).replace("&",i));
                $("#td1").html(inn.replace("&",findStr).replace("&",1));
                $("#publisher_find").val("");
                return;
            }
        }
        $("#publisher_find").val("");
        alert("未找到相关出版社")
    })
    function deleteit(index)
    {
        $("#td"+index).attr("showit","none");
        $("#td"+index).hide();
    }
    function TableToJson() {
        var count=$("#simple-table").find("td").length;
        var text="";
        for(var i=1;i<=count;i++)
        {
            if($("#td"+i).attr("showit")!="none")text+=(","+$("#td"+i).text().trim());
        }
        text+=(","+$("#publisher_add").val().trim());
        return text;
    }
    $("#add").click(function(){
        var dat=TableToJson();
        console.log(dat);
        $.ajax({
            type:"post",
            data:{list:dat},
            url:'/properties/publisher/add',
            success:function(data){
                $("#publisher_add").val("");
                alert(data);
                load();
            }
        })
    })
    function change(ye,count)
    {
        for(var i=1;i<=count/20+1;i++)
        {
            $("#fenye"+i).removeClass();
        }
        $("#fenye"+ye).addClass("active");
        for(var i=1;i<=count;i++)
        {
            if($("#td"+i).attr("showit")!="none")$("#td"+i).hide();
        }
        for(var i=20*(ye-1)+1;i<=20*ye;i++)
        {
            if($("#td"+i).attr("showit")!="none")$("#td"+i).show();
        }
    }
    function load()
    {
        $("#simple-table").children().remove();
        $("#fenye").children().remove();
        $.ajax({
            url:'/properties/publisher/getall',
            type:'post',
            success:function(data){
                var count=data.length;
                var fenyehead=
                        ("<li class=\"prev disabled\">\n"+
                        "<a href=\"#\">\n"+
                        "<i class=\"ace-icon fa fa-angle-double-left\"></i>\n"+
                        "</a>\n"+
                        "</li>\n");
                var fenyetail=
                        ("<li class=\"next\">\n"+
                        "<a href=\"#\">\n"+
                        "<i class=\"ace-icon fa fa-angle-double-right\"></i>\n"+
                        "</a>\n"+
                        "</li>\n");
                var fenyein="<li id=\"&\" onclick=\"change(&,&)\">\n"+ "<a href=\"#\">&</a>\n"+"</li>\n";
                var fenye="";
                for(var i=0;i<count/20;i++)
                {
                    fenye+=(fenyein.replace("&","fenye"+(i+1)).replace("&",i+1).replace("&",count).replace("&",i+1));
                }
                fenye=(fenyehead+fenye+fenyetail);
                $("#fenye").append(fenye);
                $("#fenye1").addClass("active");
                var all="";
                var td=
                        "<td id=\"td&\" showit=\"show\" style=\"display:none\">\n"+
                        "&\n"+
                        "<div class=\"hidden-sm hidden-xs btn-group\">\n"+
                        "<button class=\"btn btn-xs btn-danger\" onclick=\"deleteit(&)\">\n"+
                        "<i class=\"ace-icon fa fa-trash-o bigger-120\"></i>\n"+
                        "</button>\n"+
                        "</div>\n"+
                        "</td>\n"
                var temp="";
                for(var i=1;i<=count;i++)
                {
                    temp+=(td.replace("&",i).replace("&",data[i-1]).replace("&",i));
                    if(i%4==0)
                    {
                        all+=("<tr>\n"+temp+"</tr>")
                        temp="";
                    }
                }
                all+=("<tr>\n"+temp+"</tr>")
                $("#simple-table").append(all);
                for(var i=1;i<=20;i++)
                    $("#td"+i).show();
            }
        })
    }
    $(document).ready(function(){
        load();
    })

</script>