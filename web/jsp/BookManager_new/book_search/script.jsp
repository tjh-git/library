<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resources/assets/js/jquery-ui.custom.js"></script>
<script src="${ctx}/resources/assets/js/jquery.ui.touch-punch.js"></script>
<script src="${ctx}/resources/assets/js/chosen.jquery.js"></script>
<script src="${ctx}/resources/assets/js/fuelux/fuelux.spinner.js"></script>
<script src="${ctx}/resources/assets/js/date-time/bootstrap-datepicker.js"></script>
<script src="${ctx}/resources/assets/js/date-time/bootstrap-timepicker.js"></script>
<script src="${ctx}/resources/assets/js/date-time/moment.js"></script>
<script src="${ctx}/resources/assets/js/date-time/daterangepicker.js"></script>
<script src="${ctx}/resources/assets/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/assets/js/bootstrap-colorpicker.js"></script>
<script src="${ctx}/resources/assets/js/jquery.knob.js"></script>
<script src="${ctx}/resources/assets/js/jquery.autosize.js"></script>
<script src="${ctx}/resources/assets/js/jquery.inputlimiter.1.3.1.js"></script>
<script src="${ctx}/resources/assets/js/jquery.maskedinput.js"></script>
<script src="${ctx}/resources/assets/js/bootstrap-tag.js"></script>
<script src="${ctx}/resources/assets/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/resources/assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="${ctx}/resources/assets/js/dataTables.tableTools.min.js"></script>
<script src="${ctx}/resources/assets/js/dataTables.colVis.min.js"></script>
<script>
    $("#search").click(function(){
        $.ajax({
            type:"post",
            url:"/bookLogin/getBookByName",
            data:{
                name:$("#readCard").val(),
            },
            success:function(data){
                $("#table-body").find("tr").remove()
                let rows=data.rows
                console.log(rows)
                for(let index in rows){
                    let bookname=rows[index].bookname
                    let printtime=rows[index].printtimes
                    let edittime=rows[index].editiontimes
                    let position=rows[index].book_position
                    let book_pos_code=rows[index].book_pos_code
                    let book_id=rows[index].book_id
                    let str='<tr ><td >'+bookname+'</td><td >'+edittime+'</td><td >'+printtime+'</td><td >'+position+'</td><td >'+book_pos_code+'</td><td><button onclick="getcomment('+book_id+')" class="btn btn-xs btn-warning"> <i class="ace-icon fa fa-flag bigger-120"></i> </button></td> </tr>'
                    console.log(str)
                    $("#table-body").append(str)
                }
                $("#table").show()
            }
        })
    })
</script>
<script>
    $("#list-2").addClass("active open")
    $("#list-2-0").addClass("active")
</script>