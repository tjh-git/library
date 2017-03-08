<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/7
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${ctx}/resources/js/jquery-ui.js"></script>
<script src="${ctx}/resources/js/jquery.ui.touch-punch.js"></script>
<script src="${ctx}/resources/js/date-time/bootstrap-datepicker.js"></script>
<script src="${ctx}/resources/js/jqGrid/jquery.jqGrid.js"></script>
<script src="${ctx}/resources/js/jqGrid/i18n/grid.locale-en.js"></script>
<script>
    function uploadFile(){
        $("#iframe").contents().find("#upload-input").click();
    }
</script>
<script type="text/javascript">
        jQuery(function($) {
            var grid_selector = "#grid-table";
            var pager_selector = "#grid-pager";


            var parent_column = $(grid_selector).closest('[class*="col-"]');
            //resize to fit page size
            $(window).on('resize.jqGrid', function () {
                $(grid_selector).jqGrid('setGridWidth', parent_column.width() );
            })

            //resize on sidebar collapse/expand
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





        jQuery("#grid-table").jqGrid({
            //direction: "rtl",
            url:"${ctx}/bookLogin/getBookList",
            mtype:"POST",
            datatype:"json",

            height: 550,
            colNames:['登记号','图书编号', '图书名称', "图书状态",'分册','定价','出版社','借阅次数','查看记录'],
            colModel:[
                {name:'book_id',index:'book_id', width:60, sorttype:"int", editable: true,key:true},
                {name:'bookcode',index:'bookname',width:140, sorttype:"date",unformat: pickDate},
                {name:'bookname',index:'bookname', width:260,edittype:"select"},
                {name:'borrow_status',index:'borrow_status', width:80,editoptions:{size:"20",maxlength:"30"},formatter:function(cellvalue,options,rowObject){
                    console.log(cellvalue)
                    if(cellvalue==1)
                            return "借出"
                    else return "在馆"
                }},

                {name:'have_vol',index:'have_vol', width:60,editoptions:{size:"20",maxlength:"30"},formatter:function(cellvalue,options,rowObject){
                    console.log(cellvalue)
                    switch (cellvalue){
                        case 1:
                            return "无"
                        case 2:
                            return  "上册"
                        case 3:
                            return "中册"
                        case 4:
                            return "下册"
                        case 5:
                            return "其他"
                    }
                }},

                {name:'bookprice',index:'bookprice', width:150, sortable:false,formatter:function(cellvalue, options, rowObject){
                    var temp="￥"+cellvalue
                    return temp
                }},
                {name:'publishcom',index:'publishcom', width:150, sortable:false},
                {name:'borrow_count',index:'borrow_count', width:80, sortable:false},
                {name:'myac',index:'myac', width:80, fixed:true, sortable:false, resize:false},
            ],

            rowNum:10,
            rowList:[10,20,30],
            pager : pager_selector,
            altRows: true,
            //toppager: true,

            //multikey: "ctrlKey",

            loadComplete : function() {
                var table = this;
                setTimeout(function(){
                    styleCheckbox(table);

                    updateActionIcons(table);
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            },

            editurl: "/dummy.html",//nothing is saved
            caption: "图书情况表"

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

            var jqnav = jQuery(grid_selector).jqGrid('navGrid', pager_selector,
                    {    //navbar options
                        view: true,
                        viewicon: 'ace-icon fa fa-search-plus grey',
                    })

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


        //navButtons



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
         .wrap('<label />').after('<span class="lbl align-top" />')
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
            $.jgrid.gridDestroy(grid_selector);
            $('.ui-jqdialog').remove();
        });
    });

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

        $( "#search" ).on('click', function(e) {
            e.preventDefault();

            var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i> jQuery UI Dialog</h4></div>",
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


        $( "#id-btn-dialog2" ).on('click', function(e) {
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

    $("#search_click").click(function(){
        var opts=$(".col_select").find("option:selected")
        var inpts=$(".value_input")
        obj={}
        for(var i=0;i<4;i++){
            var opt=opts[i]
            var inpt=inpts[i]
            obj[$(opt).val()]=$(inpt).val()
        }
        $("#grid-table").jqGrid('setGridParam',{
            datatype:'json',
            postData:obj,
            page:1
        }).trigger("reloadGrid")
    })
    $("#reset_click").click(function(){
        window.location=window.location.href
    })
</script>
<script>
    $("#list-2").addClass("active open")
    $("#list-2-1").addClass("active")
</script>