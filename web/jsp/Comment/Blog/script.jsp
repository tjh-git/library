<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/7
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${ctx}/resources/comment/jquery-2.1.1.js"></script>
<script src="${ctx}/resources/comment/jquery-ui-1.10.4.min.js"></script>
<script src="${ctx}/resources/comment/bootstrap.min.js"></script>
<!-- 手风琴菜单 -->
<script src="${ctx}/resources/comment/jquery.metisMenu.js"></script>
<!-- 滚动条 -->
<script src="${ctx}/resources/comment/jquery.slimscroll.min.js"></script>
<!-- 导航菜单 -->
<script src="${ctx}/resources/comment/inspinia.js"></script>
<!-- 进度条 -->
<script src="${ctx}/resources/comment/pace.min.js"></script>

<script src="${ctx}/resources/js/jquery.bootstrap-duallistbox.js"></script>
<script src="${ctx}/resources/js/jquery.raty.js"></script>
<script src="${ctx}/resources/js/bootstrap-multiselect.js"></script>
<script src="${ctx}/resources/js/select2.js"></script>
<script src="${ctx}/resources/js/typeahead.jquery.js"></script>
<script type="text/javascript">
    jQuery(function($){
        var demo1 = $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox({infoTextFiltered: '<span class="label label-purple label-lg">Filtered</span>'});
        var container1 = demo1.bootstrapDualListbox('getContainer');
        container1.find('.btn').addClass('btn-white btn-info btn-bold');

        var setRatingColors = function() {
					$(this).find('.star-on-png,.star-half-png').addClass('orange2').removeClass('grey');
					$(this).find('.star-off-png').removeClass('orange2').addClass('grey');
				}
        $('.rating').raty({
            'cancel' : true,
            'half': true,
            'starType' : 'i'
            ,

             'click': function() {
						setRatingColors.call(this);
					},
             'mouseover': function() {
						setRatingColors.call(this);
					},
             'mouseout': function() {
						setRatingColors.call(this);
					}
        })//.find('i:not(.star-raty)').addClass('grey');



        //////////////////
        //select2
        $('.select2').css('width','200px').select2({allowClear:true})
        $('#select2-multiple-style .btn').on('click', function(e){
            var target = $(this).find('input[type=radio]');
            var which = parseInt(target.val());
            if(which == 2) $('.select2').addClass('tag-input-style');
            else $('.select2').removeClass('tag-input-style');
        });

        //////////////////
        $('.multiselect').multiselect({
            enableFiltering: true,
            enableHTML: true,
            buttonClass: 'btn btn-white btn-primary',
            templates: {
                button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
                ul: '<ul class="multiselect-container dropdown-menu"></ul>',
                filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
                filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
                li: '<li><a tabindex="0"><label></label></a></li>',
                divider: '<li class="multiselect-item divider"></li>',
                liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
            }
        });


        ///////////////////

        //typeahead.js
        //example taken from plugin's page at: https://twitter.github.io/typeahead.js/examples/
        var substringMatcher = function(strs) {
            return function findMatches(q, cb) {
                var matches, substringRegex;

                // an array that will be populated with substring matches
                matches = [];

                // regex used to determine if a string contains the substring `q`
                substrRegex = new RegExp(q, 'i');

                // iterate through the pool of strings and for any string that
                // contains the substring `q`, add it to the `matches` array
                $.each(strs, function(i, str) {
                    if (substrRegex.test(str)) {
                        // the typeahead jQuery plugin expects suggestions to a
                        // JavaScript object, refer to typeahead docs for more info
                        matches.push({ value: str });
                    }
                });

                cb(matches);
            }
        }

        $('input.typeahead').typeahead({
            hint: true,
            highlight: true,
            minLength: 1
        }, {
            name: 'states',
            displayKey: 'value',
            source: substringMatcher(ace.vars['US_STATES']),
            limit: 10
        });


        ///////////////


        //in ajax mode, remove remaining elements before leaving page
        $(document).one('ajaxloadstart.page', function(e) {
            $('[class*=select2]').remove();
            $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox('destroy');
            $('.rating').raty('destroy');
            $('.multiselect').multiselect('destroy');
        });

    });
</script>

<script>
    var s_url=window.location.pathname;
    var now_url = '';
    for(var i = 0;i<$("#side-menu li").length;i++){
        now_url=$("#side-menu li a").eq(i).attr("href");
        if(now_url == s_url){
            $("#side-menu a").eq(i).parent().addClass("active");
            $("#side-menu a").eq(i).parent().parent().parent().addClass("active");
            $("#side-menu a").eq(i).parent().parent().addClass("in");
        }else{
            $("#side-menu a").eq(i).parent().removeClass("active");
        }
    }
</script>
<!-- 返回顶部 -->
<script>
    jQuery(document).ready(function($){
        // browser window scroll (in pixels) after which the "back to top" link is shown
        var offset = 300,
        //browser window scroll (in pixels) after which the "back to top" link opacity is reduced
                offset_opacity = 1200,
        //duration of the top scrolling animation (in ms)
                scroll_top_duration = 700,
        //grab the "back to top" link
                $back_to_top = $('.cd-top');

        //hide or show the "back to top" link
        $(window).scroll(function(){
            ( $(this).scrollTop() > offset ) ? $back_to_top.addClass('cd-is-visible') : $back_to_top.removeClass('cd-is-visible cd-fade-out');
            if( $(this).scrollTop() > offset_opacity ) {
                $back_to_top.addClass('cd-fade-out');
            }
        });

        //smooth scroll to top
        $back_to_top.on('click', function(event){
            event.preventDefault();
            $('body,html').animate({
                        scrollTop: 0 ,
                    }, scroll_top_duration
            );
        });

    });
</script>




<script>
    function huifuss(a){
        $("#replay").val("回复"+a+"楼");
        $("#huifuyincang").val(a);
    }
    var num = 0;
    var id = $("#id").val();
    var userid = $("#userid").val();
    var now = 0;
    function replay(){
        $.ajax({
            type: "POST",
            url: "/index.php/Home/Article/getReplay",
            data: "id="+id+"&num="+num,
            success: function(msg){
                if(msg == 5){
                    $("#show").html('<i class="fa fa-arrow-down"></i>没有更多内容了');
                }else{
                    msg = eval(msg);
                    for (var i in msg)
                    {
                        now ++;
                        if(msg[i].replay !=0){
                            msg[i].content = "<span class = 'label label-info' style = 'font-size:12px;'>回复"+msg[i].replay+"楼:</span><br/><br>"+msg[i].content;
                        }
                        if(msg[i].pic ==null){
                            var pic = '/Public/Uploads/default.png';
                        }else{
                            var pic = "/Public/Uploads/"+msg[i].pic;
                        }
                        if(msg[i].uid == userid){
                            var shenfen = '<a class="btn btn-xs btn-white" href = "#huifus"onclick="huifuss('+now+')"><i class="fa fa-heart"></i> 回复 </a> <a class="btn btn-xs btn-danger"><i class="fa fa-pencil"></i> 楼主';
                        }else if(msg[i].uid !=0 && msg[i].uid != userid){
                            var shenfen = '<a class="btn btn-xs btn-white" href = "#huifus" onclick="huifuss('+now+')"><i class="fa fa-heart"></i> 回复 </a> <a class="btn btn-xs btn-warning"><i class="fa fa-pencil"></i> 本站会员';
                        }else{
                            var shenfen = '<a class="btn btn-xs btn-white" href = "#huifus" onclick="huifuss('+now+')"><i class="fa fa-heart"></i> 回复 </a> <a class="btn btn-xs btn-primary"><i class="fa fa-pencil"></i> 游客';
                        }

                        var tm = new Date(parseInt(msg[i].ctime) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");

                        $("#pinglun").append('<div class="feed-element"><a href="#" class="pull-left"><img alt="image" class="img-circle" src="'+pic+'"></a><div class="media-body "><small class="pull-right" style ="font-size:12px;">'+now+'楼</small><strong style = "font-size:15px;">'+msg[i].name+'</strong><br><small class="text-muted">'+tm+'</small><div class="well" style = "font-size:14px;">'+msg[i].content+'</div><div class="pull-right">'+shenfen+'</a></div></div></div><hr />');
                    }
                    num = num+10;
                }
            }
        });
    }
    replay();
</script>
<script src="/resource/comment/one/gt.js"></script>
<script>
    var handlerEmbed = function (captchaObj) {
        $("#popup-submit").click(function (e) {
            var validate = captchaObj.getValidate();
            if (!validate) {
                $("#notice")[0].className = "show";
                setTimeout(function () {
                    $("#notice")[0].className = "hide";
                }, 2000);
                e.preventDefault();
            }
        });
        // 将验证码加到id为captcha的元素里
        captchaObj.appendTo("#embed-captcha");
        captchaObj.onReady(function () {
            $("#wait")[0].className = "hide";
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    $.ajax({
        // 获取id，challenge，success（是否启用failback）
        url: "/index.php/Home/Base/EchoMyVerify.html", // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // 使用initGeetest接口
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
            }, handlerEmbed);
        }
    });
</script>
<script>
    $("#list-5").addClass("active open")
    $("#list-5-1").addClass("active")
</script>