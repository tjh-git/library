<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resources/assets/js/date-time/bootstrap-datepicker.js"></script>
<script src="${ctx}/resources/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
<script src="${ctx}/resources/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
<script src="${ctx}/resources/assets/js/jquery-ui.js"></script>
<script src="${ctx}/resources/assets/js/jquery.ui.touch-punch.js"></script>

<script type="text/javascript">
/*    $.ajax({
        type:'post',
        data:{
            page:1,
            rows:10,
            sort:'readCard',
            order:'desc',
        },
        url:'/vip/list/vipList',
        success:function (data) {
            var grid_data=data.rows;*/
function edit(){
    setTimeout(function(){
        var ids=$('#grid-table').jqGrid('getGridParam','selarrrow');
        window.location.href="/vip/list/update?id="+ids;
    },20)}
            jQuery(function($) {
                var grid_selector = "#grid-table";
                var pager_selector = "#grid-pager";
                //resize to fit page size
                $(window).on('resize.jqGrid', function () {
                    $(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
                })
                //resize on sidebar collapse/expand
                var parent_column = $(grid_selector).closest('[class*="col-"]');
                $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
                    if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
                        //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
                        setTimeout(function() {
                            $(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
                        }, 0);
                    }
                })

                //if your grid is inside another element, for example a tab pane, you should use its parent's width:
                /**
                 $(window).on('resize.jqGrid', function () {
					var parent_width = $(grid_selector).closest('.tab-pane').width();
					$(grid_selector).jqGrid( 'setGridWidth', parent_width );
				})
                 //and also set width when tab pane becomes visible
                 $('#myTab a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
				  if($(e.target).attr('href') == '#mygrid') {
					var parent_width = $(grid_selector).closest('.tab-pane').width();
					$(grid_selector).jqGrid( 'setGridWidth', parent_width );
				  }
				})
                 */
                var subgrid_data =
                        [
                            {id:"1", name:"sub grid item 1", qty: 11},
                            {id:"2", name:"sub grid item 2", qty: 3},
                            {id:"3", name:"sub grid item 3", qty: 12},
                            {id:"4", name:"sub grid item 4", qty: 5},
                            {id:"5", name:"sub grid item 5", qty: 2},
                            {id:"6", name:"sub grid item 6", qty: 9},
                            {id:"7", name:"sub grid item 7", qty: 3},
                            {id:"8", name:"sub grid item 8", qty: 8}
                        ];

                jQuery(grid_selector).jqGrid({
                    mtype:'POST',
                    //direction: "rtl",
                    url:'/vip/list/vipList',
                    //subgrid options
                    subGrid : true,
                    sortname:'readCard教师',
                    //subGridModel: [{ name : ['No','Item Name','Qty'], width : [55,200,80] }],
                    datatype: "json",
                    subGridOptions : {
                        plusicon : "ace-icon fa fa-plus center bigger-110 blue",
                        minusicon  : "ace-icon fa fa-minus center bigger-110 blue",
                        openicon : "ace-icon fa fa-chevron-right center orange"
                    },
                    //for this example we are using local data
               /*     subGridRowExpanded: function (subgridDivId, rowId) {
                        var subgridTableId = subgridDivId + "_t";
                        $("#" + subgridDivId).html("<table id='" + subgridTableId + "'></table>");
                        $("#" + subgridTableId).jqGrid({
                            subGridUrl:'/vip/list/vipList',
                            subGridType: 'json',
                       //     data: subgrid_data,
                            colNames: ['qq','email'],
                            colModel: [
                                {name:'qq',id:'qq', width:60, sorttype:"int", editable: true},
                                {name:'email',id:'email', width:60, sorttype:"int", editable: true},
                            ]
                        });
                    },*/
            //        data: grid_data,
             //       datatype: "local",








                    subGridRowExpanded: function(subgrid_id, row_id) {  //(2)子表格容器的id和需要展开子表格的行id，将传入此事件函数
                        var subgrid_table_id;
                        subgrid_table_id = subgrid_id + "_t";   //(3)根据subgrid_id定义对应的子表格的table的id

                        var subgrid_pager_id;
                        subgrid_pager_id = subgrid_id + "_pgr"  //(4)根据subgrid_id定义对应的子表格的pager的id

                        // (5)动态添加子报表的table和pager
                        $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"'class='scroll'></table><div id='"+subgrid_pager_id+"'class='scroll'></div>");
                        // (6)创建jqGrid对象
                        $("#" + subgrid_table_id).jqGrid({
                            url: "/vip/list/vipList?row_id="+row_id,  //(7)子表格数据对应的url，注意传入的contact.id参数
                                datatype: "json",
                                colNames: ['QQ号','电子邮箱','手机号','余额'],
                                colModel: [
                                        {name:"qq",index:"qq",width:130,key:true},
                                        {name:"email",index:"email",width:150},
                                        {name:'tel',index:'tel', width:150, },
                                        {name:'money',index:'money', width:100,},
                                        ],

                                });
                            },
                    height: 250,
                    colNames:[' ', '索引','借阅卡号','姓名','身份证号', '性别', '会员卡类型','加入时间','会员卡状态','教职工号','所教课程','借阅证有效期','查看记录'],
                    colModel:[
                        {name:'edit',index:'myac', width:80, fixed:true, sortable:false, resize:false},
                        {name:'id',inde:'id',hidden:true},
                        {name:'readCard',index:'readCard', width:75, sorttype:"int", editable: true},
                        {name:'name',index:'name', width:50, sorttype:"int", editable: true},
                        {name:'IDCard',index:'IDCard', width:120, sorttype:"int", editable: true},
                        {name:'gender',index:'gender',width:50, editable: true,edittype:"select",editoptions:{value:"男:男;女:女"}},
                        {name:'cardType',index:'cardType',width:60, editable: true,edittype:"select",editoptions:{value:"教师:教师;学生:学生"}},
                        {name:'addTime',index:'addTime',width:70, editable:true, sorttype:"date",unformat: pickDate},
                        {name:'status',index:'status', width:60, sorttype:"int", editable: true},
                        {name:'teachID',index:'teachID', width:80, sorttype:"int", editable: true},
                        {name:'teachClass',index:'teachClass', width:60, sorttype:"int", editable: true},
                        {name:'deadLine',index:'deadLine',width:90, editable:true, sorttype:"date",unformat: pickDate},
                        {name:'myac',index:'myac', width:80, fixed:true, sortable:false, resize:false},
                    ],

                    viewrecords : true,
                    rowNum:10,
                    rowList:[10,20,30],
                    pager : pager_selector,
                    altRows: true,
                    //toppager: true,

                    multiselect: true,
                    //multikey: "ctrlKey",
                    multiboxonly: true,

                    loadComplete : function() {
                        var table = this;
                        setTimeout(function(){
                            styleCheckbox(table);

                            updateActionIcons(table);
                            updatePagerIcons(table);
                            enableTooltips(table);
                        }, 0);
                    },

                    editurl: "/vip/list/edit",//nothing is saved
                    caption: "教师会员列表"

                    //,autowidth: true,


                    /**
                     ,
                     grouping:true,
                     groupingView : {
						 groupField : ['name'],
						 groupDataSorted : true,
						 plusicon : 'fa fa-chevron-down bigger-110',
						 minusicon : 'fa fa-chevron-up bigger-110'
					},
                     caption: "Grouping"
                     */

                });
                $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size



                //enable search/filter toolbar
                //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
                //jQuery(grid_selector).filterToolbar({});


                //switch element when editing inline
                function aceSwitch( cellvalue, options, cell ) {
                    setTimeout(function(){
                        $(cell) .find('input[type=checkbox]')
                                .addClass('ace ace-switch ace-switch-5')
                                .after('<span class="lbl"></span>');
                    }, 0);
                }
                //enable datepicker
                function pickDate( cellvalue, options, cell ) {
                    setTimeout(function(){
                        $(cell) .find('input[type=text]')
                                .datepicker({format:'yyyy-mm-dd' , autoclose:true});
                    }, 0);
                }

//*****************************************

                var jqnav = jQuery(grid_selector).jqGrid('navGrid', pager_selector,
                        {    //navbar options
                            edit: true,
                            editicon: 'ace-icon fa fa-pencil blue',
                            add: true,
                            addicon: 'ace-icon fa fa-plus-circle purple',
                            del: true,
                            delicon: 'ace-icon fa fa-trash-o red',
                            search: true,
                            searchicon: 'ace-icon fa fa-search orange',
                            refresh: true,
                            refreshicon: 'ace-icon fa fa-refresh green',
                            view: true,
                            viewicon: 'ace-icon fa fa-search-plus grey',
                        },{},{},{})

                jqnav.navButtonAdd(pager_selector, {
                    caption: "打印借阅证",
                    title:"打印借阅证",
                    buttonicon: "ace-icon fa fa-globe blue",
                    onClickButton: function () {
                        var s;
                        //多选获取
                        s = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
                        if(s.toString().length==0)
                        {
                            alert("请选择你要打印的用户");
                            return;
                        }
                        if(s.toString().indexOf(",")!=-1)
                        {
                            alert("请选择一行进行打印");
                            return;
                        }
                        var rows = $(grid_selector).jqGrid('getRowData',s);
                        if(rows.cardType=="教师")window.open("/vip/teacher/printit?id="+rows.id);
                        else window.open("/vip/student/printit?id="+rows.id)
                    },
                    position: "last"
                })
                jqnav.navButtonAdd(pager_selector, {
                    caption: "导出会员列表",
                    title:"导出会员列表",
                    buttonicon: "ace-icon fa fa-globe blue",
                    onClickButton: function () {
                        var obj = $(grid_selector).jqGrid("getRowData");
                        $.ajax({
                            url:'/vip/list/vipList',
                            type:'post',
                            data:{
                                sidx:"教师",
                                type:'all',
                                name:$("#name").val(),
                                readCard:$("#readCard").val(),
                                cardType:$("#cardType").val(),
                            },
                            success:function(data){
                                var n=encodeURIComponent("\n");
                                var str ="\ufeff";
                                str+=("借阅证卡号,姓名,身份证号,用户性别,会员卡类型,加入时间,会员状态,教职工号,所教课程,手机,QQ,常用邮箱,借阅证有效期,账户余额"+n);
                                data=data.rows;
                                var rows=data;
                                for(var i=0;i<rows.length;i++)
                                {
                                    var p="\ufeff"+rows[i].readCard+",";
                                    p+=rows[i].name+",";
                                    p+=("\ufeff"+rows[i].IDCard+",");
                                    p+=(rows[i].gender+",");
                                    p+=rows[i].cardType+",";
                                    p+="\ufeff"+rows[i].addTime+",";
                                    p+=rows[i].status+",";
                                    p+="\ufeff"+rows[i].teachID+",";
                                    p+=rows[i].teachClass+",";
                                    p+="\ufeff"+rows[i].tel+",";
                                    p+="\ufeff"+rows[i].qq+",";
                                    p+=rows[i].email+",";
                                    p+="\ufeff"+rows[i].deadLine+",";
                                    p+=rows[i].money+n;
                                    str+=p;
                                }
                                str=str.toString();
                                var uri = 'data:text/csv;charset=utf8,' + str;
                                var downloadLink = document.createElement("a");
                                downloadLink.href = uri;
                                downloadLink.download = "export.csv";
                                document.body.appendChild(downloadLink);
                                downloadLink.click();
                                document.body.removeChild(downloadLink);
                            }
                        })

                    },
                    position: "last"
                })
                //****************************************
                //navButtons
                jQuery(grid_selector).jqGrid('navGrid',pager_selector,
                        { 	//navbar options
                            edit: true,
                            editicon : 'ace-icon fa fa-pencil blue',
                            add: true,
                            addicon : 'ace-icon fa fa-plus-circle purple',
                            del: true,
                            delicon : 'ace-icon fa fa-trash-o red',
                            search: true,
                            searchicon : 'ace-icon fa fa-search orange',
                            refresh: true,
                            refreshicon : 'ace-icon fa fa-refresh green',
                            view: true,
                            viewicon : 'ace-icon fa fa-search-plus grey',
                        },
                        {
                            //edit record form
                            //closeAfterEdit: true,
                            //width: 700,
                            recreateForm: true,
                            beforeShowForm : function(e) {
                                var form = $(e[0]);
                                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                                style_edit_form(form);
                            }
                        },
                        {
                            //new record form
                            //width: 700,
                            closeAfterAdd: true,
                            recreateForm: true,
                            viewPagerButtons: false,
                            beforeShowForm : function(e) {
                                var form = $(e[0]);
                                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
                                        .wrapInner('<div class="widget-header" />')
                                style_edit_form(form);
                            }
                        },
                        {
                            //delete record form
                            recreateForm: true,
                            beforeShowForm : function(e) {
                                var form = $(e[0]);
                                if(form.data('styled')) return false;

                                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                                style_delete_form(form);

                                form.data('styled', true);
                            },
                            onClick : function(e) {
                                //alert(1);
                            }
                        },
                        {
                            //search form
                            recreateForm: true,
                            afterShowSearch: function(e){
                                var form = $(e[0]);
                                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                                style_search_form(form);
                            },
                            afterRedraw: function(){
                                style_search_filters($(this));
                            }
                            ,
                            multipleSearch: true,
                            /**
                             multipleGroup:true,
                             showQuery: true
                             */
                        },
                        {
                            //view record form
                            recreateForm: true,
                            beforeShowForm: function(e){
                                var form = $(e[0]);
                                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                            }
                        }
                )



                function style_edit_form(form) {
                    //enable datepicker on "sdate" field and switches for "stock" field
                    form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})

                    form.find('input[name=stock]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
                    //don't wrap inside a label element, the checkbox value won't be submitted (POST'ed)
                    //.addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');


                    //update buttons classes
                    var buttons = form.next().find('.EditButton .fm-button');
                    buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
                    buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
                    buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')

                    buttons = form.next().find('.navButton a');
                    buttons.find('.ui-icon').hide();
                    buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
                    buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
                }

                function style_delete_form(form) {
                    var buttons = form.next().find('.EditButton .fm-button');
                    buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
                    buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
                    buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
                }

                function style_search_filters(form) {
                    form.find('.delete-rule').val('X');
                    form.find('.add-rule').addClass('btn btn-xs btn-primary');
                    form.find('.add-group').addClass('btn btn-xs btn-success');
                    form.find('.delete-group').addClass('btn btn-xs btn-danger');
                }
                function style_search_form(form) {
                    var dialog = form.closest('.ui-jqdialog');
                    var buttons = dialog.find('.EditTable')
                    buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
                    buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
                    buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
                }

                function beforeDeleteCallback(e) {
                    var form = $(e[0]);
                    if(form.data('styled')) return false;

                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_delete_form(form);

                    form.data('styled', true);
                }

                function beforeEditCallback(e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_edit_form(form);
                }



                //it causes some flicker when reloading or navigating grid
                //it may be possible to have some custom formatter to do this as the grid is being created to prevent this
                //or go back to default browser checkbox styles for the grid
                function styleCheckbox(table) {
                    /**
                     $(table).find('input:checkbox').addClass('ace')
                     .wrap('<label />')
                     .after('<span class="lbl align-top" />')


                     $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
                     .find('input.cbox[type=checkbox]').addClass('ace')
                     .wrap('<label />').after('<span class="lbl align-top" />');
                     */
                }


                //unlike navButtons icons, action icons in rows seem to be hard-coded
                //you can change them like this in here if you want
                function updateActionIcons(table) {
                    /**
                     var replacement =
                     {
                         'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
                         'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
                         'ui-icon-disk' : 'ace-icon fa fa-check green',
                         'ui-icon-cancel' : 'ace-icon fa fa-times red'
                     };
                     $(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
                     */
                }

                //replace icons with FontAwesome icons like above
                function updatePagerIcons(table) {
                    var replacement =
                    {
                        'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
                        'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
                        'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
                        'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
                    };
                    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
                        var icon = $(this);
                        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

                        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
                    })
                }

                function enableTooltips(table) {
                    $('.navtable .ui-pg-button').tooltip({container:'body'});
                    $(table).find('.ui-pg-div').tooltip({container:'body'});
                }

                //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

                $(document).one('ajaxloadstart.page', function(e) {
                    $(grid_selector).jqGrid('GridUnload');
                    $('.ui-jqdialog').remove();
                });
      //      });
      //  }
    })
</script>
<script>
    setTimeout(function(){
        $("#search_grid-table").unbind("click")
        $( "#search_grid-table" ).on('click', function(e) {
            e.preventDefault();
            var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i> 条件查询</h4></div>",
                title_html: true,
                buttons: [
                    {
                        text: "取消",
                        "class" : "btn btn-minier",
                        click: function() {
                            $( this ).dialog( "close" );
                        }
                    },
                    {
                        text: "查询",
                        "class" : "btn btn-primary btn-minier",
                        click: function() {
                            var $grid=$('#grid-table');
                            var page = $grid.getGridParam('page'); // current page
                            var rows = $grid.getGridParam('rows'); // rows
                            var sort = $grid.getGridParam('sidx'); // sidx
                            var order = $grid.getGridParam('sord');
                            $grid.jqGrid('setGridParam', {
                                datatype:'json',
                                postData:{
                                    page:page,
                                    rows:rows,
                                    sort:sort,
                                    order:order,
                                    name:$("#name").val(),
                                    readCard:$("#readCard").val(),
                                    cardType:$("#cardType").val(),
                                }
                            }).trigger("reloadGrid");
                            $( this ).dialog( "close" );
                        }
                    }
                ]
            });

            /**
             dialog.data( "uiDialog" )._title = function(title) {
						title.html( this.options.title );
					};
             **/
        });
    },1000)

</script>
<script type="text/javascript">
    jQuery(function($) {

        $( "#datepicker" ).datepicker({
            showOtherMonths: true,
            selectOtherMonths: false,
            //isRTL:true,
            /*
             changeMonth: true,
             changeYear: true,

             showButtonPanel: true,
             beforeShow: function() {
             //change button colors
             var datepicker = $(this).datepicker( "widget" );
             setTimeout(function(){
             var buttons = datepicker.find('.ui-datepicker-buttonpane')
             .find('button');
             buttons.eq(0).addClass('btn btn-xs');
             buttons.eq(1).addClass('btn btn-xs btn-success');
             buttons.wrapInner('<span class="bigger-110" />');
             }, 0);
             }
             */
        });
        //override dialog's title function to allow for HTML titles
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function(title) {
                var $title = this.options.title || '&nbsp;'
                if( ("title_html" in this.options) && this.options.title_html == true )
                    title.html($title);
                else title.text($title);
            }
        }));
        $( "#search_grid-table" ).on('click', function(e) {
            e.preventDefault();
            var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i> 条件查询</h4></div>",
                title_html: true,
                buttons: [
                    {
                        text: "Cancel",
                        "class" : "btn btn-minier",
                        click: function() {
                            $( this ).dialog( "close" );
                        }
                    },
                    {
                        text: "OK",
                        "class" : "btn btn-primary btn-minier",
                        click: function() {
                            $( this ).dialog( "close" );
                        }
                    }
                ]
            });
            /**
             dialog.data( "uiDialog" )._title = function(title) {
						title.html( this.options.title );
					};
             **/
        });
        /* $( "#id-btn-dialog2" ).on('click', function(e) {
         e.preventDefault();

         $( "#dialog-confirm" ).removeClass('hide').dialog({
         resizable: false,
         width: '320',
         modal: true,
         title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> Empty the recycle bin?</h4></div>",
         title_html: true,
         buttons: [
         {
         html: "<i class='ace-icon fa fa-trash-o bigger-110'></i>&nbsp; Delete all items",
         "class" : "btn btn-danger btn-minier",
         click: function() {
         $( this ).dialog( "close" );
         }
         }
         ,
         {
         html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; Cancel",
         "class" : "btn btn-minier",
         click: function() {
         $( this ).dialog( "close" );
         }
         }
         ]
         });
         });
         */


        //autocomplete
        var availableTags = [
            "ActionScript",
            "AppleScript",
            "Asp",
            "BASIC",
            "C",
            "C++",
            "Clojure",
            "COBOL",
            "ColdFusion",
            "Erlang",
            "Fortran",
            "Groovy",
            "Haskell",
            "Java",
            "JavaScript",
            "Lisp",
            "Perl",
            "PHP",
            "Python",
            "Ruby",
            "Scala",
            "Scheme"
        ];
        $( "#tags" ).autocomplete({
            source: availableTags
        });

        //custom autocomplete (category selection)
        $.widget( "custom.catcomplete", $.ui.autocomplete, {
            _create: function() {
                this._super();
                this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
            },
            _renderMenu: function( ul, items ) {
                var that = this,
                        currentCategory = "";
                $.each( items, function( index, item ) {
                    var li;
                    if ( item.category != currentCategory ) {
                        ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                        currentCategory = item.category;
                    }
                    li = that._renderItemData( ul, item );
                    if ( item.category ) {
                        li.attr( "aria-label", item.category + " : " + item.label );
                    }
                });
            }
        });

        var data = [
            { label: "anders", category: "" },
            { label: "andreas", category: "" },
            { label: "antal", category: "" },
            { label: "annhhx10", category: "Products" },
            { label: "annk K12", category: "Products" },
            { label: "annttop C13", category: "Products" },
            { label: "anders andersson", category: "People" },
            { label: "andreas andersson", category: "People" },
            { label: "andreas johnson", category: "People" }
        ];
        $( "#search" ).catcomplete({
            delay: 0,
            source: data
        });


        //tooltips
        $( "#show-option" ).tooltip({
            show: {
                effect: "slideDown",
                delay: 250
            }
        });

        $( "#hide-option" ).tooltip({
            hide: {
                effect: "explode",
                delay: 250
            }
        });

        $( "#open-event" ).tooltip({
            show: null,
            position: {
                my: "left top",
                at: "left bottom"
            },
            open: function( event, ui ) {
                ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
            }
        });


        //Menu
        $( "#menu" ).menu();


        //spinner
        var spinner = $( "#spinner" ).spinner({
            create: function( event, ui ) {
                //add custom classes and icons
                $(this)
                        .next().addClass('btn btn-success').html('<i class="ace-icon fa fa-plus"></i>')
                        .next().addClass('btn btn-danger').html('<i class="ace-icon fa fa-minus"></i>')

                //larger buttons on touch devices
                if('touchstart' in document.documentElement)
                    $(this).closest('.ui-spinner').addClass('ui-spinner-touch');
            }
        });

        //slider example
        $( "#slider" ).slider({
            range: true,
            min: 0,
            max: 500,
            values: [ 75, 300 ]
        });



        //jquery accordion
        $( "#accordion" ).accordion({
            collapsible: true ,
            heightStyle: "content",
            animate: 250,
            header: ".accordion-header"
        }).sortable({
            axis: "y",
            handle: ".accordion-header",
            stop: function( event, ui ) {
                // IE doesn't register the blur when sorting
                // so trigger focusout handlers to remove .ui-state-focus
                ui.item.children( ".accordion-header" ).triggerHandler( "focusout" );
            }
        });
        //jquery tabs
        $( "#tabs" ).tabs();


        //progressbar
        $( "#progressbar" ).progressbar({
            value: 37,
            create: function( event, ui ) {
                $(this).addClass('progress progress-striped active')
                        .children(0).addClass('progress-bar progress-bar-success');
            }
        });


        //selectmenu
        $( "#number" ).css('width', '200px')
                .selectmenu({ position: { my : "left bottom", at: "left top" } })

    });
</script>
<script>
    $("#list-3").addClass("active open")
    $("#list-3-3").addClass("active")
</script>