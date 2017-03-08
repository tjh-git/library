<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/12
  Time: 6:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav nav-list">

    <li class="" id="list-1">
        <a href="#" class="dropdown-toggle ">
            <i class="menu-icon fa fa-list"></i>
            <span class="menu-text"> 数据统计 </span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="" id="list-1-1">
                <a href="/jsp/bsp/statistics/libraryCatalog/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    馆藏图书统计
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-1-2">
                <a href="/jsp/bsp/statistics/bookCirculate/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    图书流通统计
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-1-3">
                <a href="/jsp/bsp/statistics/bookBorrow/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    图书借阅率统计
                </a>

                <b class="arrow"></b>
            </li>

        </ul>
    </li>


    <li id="list-2">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text">
								书籍管理
							</span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">

            <li class="" id="list-2-0">
                <a href="/jsp/BookManager_new/book_search/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    书籍检索
                </a>

                <b class="arrow"></b>
            </li>
            <li class="" id="list-2-1">
                <a href="/booklogin/booklist">
                    <i class="menu-icon fa fa-caret-right"></i>
                    书籍列表
                </a>

                <b class="arrow"></b>
            </li>
            <li class="" id="list-2-2">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-caret-right"></i>

                    馆藏管理
                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="" id="list-2-2-1">
                        <a href="/booklogin/info">
                            <i class="menu-icon fa fa-leaf green"></i>
                            录入馆藏
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="" id="list-2-2-2">
                        <a href="/booklogin/bookloginlist">
                            <i class="menu-icon fa fa-leaf green"></i>
                            馆藏列表
                        </a>

                        <b class="arrow"></b>
                    </li>

                </ul>
            </li>

            <li class="" id="list-2-3">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-caret-right"></i>

                    编目管理
                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="" id="list-2-3-1">
                        <a href="/booklogin/add">
                            <i class="menu-icon fa fa-leaf green"></i>
                            录入编目
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="" id="list-2-3-2">
                        <a href="/booklogin/cataloglist">
                            <i class="menu-icon fa fa-leaf green"></i>
                            编目列表
                        </a>

                        <b class="arrow"></b>
                    </li>

                </ul>
            </li>
        </ul>
    </li>

    <li class="" id="list-3">
        <a href="#" class="dropdown-toggle ">
            <i class="menu-icon fa fa-list"></i>
            <span class="menu-text"> 会员管理 </span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="" id="list-3-1">
                <a href="/jsp/bsp/vip_new/student/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    添加学生
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-3-2">
                <a href="/jsp/bsp/vip_new/teacher/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    添加教师
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-3-3">
                <a href="/jsp/bsp/vip_new/teacherList/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    教师会员列表
                </a>

                <b class="arrow"></b>
            </li>
            <li class="" id="list-3-4">
                <a href="/jsp/bsp/vip_new/studentList/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    学生会员列表
                </a>

                <b class="arrow"></b>
            </li>
            <li class="" id="list-3-5">
                <a href="/jsp/bsp/vip_new/money/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    充值
                </a>

                <b class="arrow"></b>
            </li>
        </ul>
    </li>

    <li class="" id="list-4">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-pencil-square-o"></i>
            <span class="menu-text"> 借阅管理 </span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="" id="list-4-1">
                <a href="/jsp/bsp/borrow_new/borrow/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    图书借阅
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-4-2">
                <a href="/jsp/bsp/borrow_new/return/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    图书归还
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-4-3">
                <a href="/jsp/bsp/borrow_new/book//blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    图书借阅情况
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-4-4">
                <a href="/jsp/bsp/borrow_new/vip/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    会员借阅情况
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-4-5">
                <a href="/jsp/bsp/borrow_new/print/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    逾期不还情况
                </a>

                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="" id="list-5">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text">
								读书分享
							</span>

            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>

        <ul class="submenu">
            <li class="" id="list-5-1">
                <a href="/comment/commentlist">
                    <i class="menu-icon fa fa-caret-right"></i>
                    读书分享
                </a>

                <b class="arrow"></b>
            </li>
            <li class="" id="list-5-2">
                <a href="/comment/readstar">
                    <i class="menu-icon fa fa-caret-right"></i>
                    阅读之星
                </a>

                <b class="arrow"></b>
            </li>

        </ul>
    </li>

    <li class="" id="list-6">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text">
								系统设置
							</span>

            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>

        <ul class="submenu">
            <li class="" id="list-6-1">
                <a href="/jsp/bsp/properties/normal/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    常规参数设置
                </a>

                <b class="arrow"></b>
            </li>

            <li class="" id="list-6-2">
                <a href="/jsp/bsp/properties/school/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    学校设置
                </a>

                <b class="arrow"></b>
            </li>
            <li class="" id="list-6-3">
                <a href="/jsp/bsp/properties/publisher/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    出版社设置
                </a>

                <b class="arrow"></b>
            </li>
            <li class="" id="list-6-4">
                <a href="/jsp/bsp/properties/schoolList/blank.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    已注册学校列表
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>

</ul><!-- /.nav-list -->
