<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/goods_list.css">
    <script src="/assets/js/goods.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-shopping-bag"></i> 제품 관리</h1>
        <button id="add_goods"><i class="fas fa-plus-circle"></i> 제품 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="검색어 입력" value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>카테고리</th>
                            <th>브랜드</th>
                            <th>제품명</th>
                            <th>설명</th>
                            <th>가격</th>
                            <th>재고</th>
                            <th>등록일</th>
                            <th>수정일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.total == 0}">
                            <tr>
                                <td id="nadata" colspan="6">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="g">
                            <tr>
                                <td>${g.gi_seq}</td>
                                <td>${g.category_name}</td>
                                <td>${g.brand_name}</td>
                                <td>${g.gi_name}</td>
                                <td>${g.gi_sub}</td>
                                <td>${g.gi_price}</td>
                                <td>${g.gi_stock}</td>
                                <td>${g.gi_reg_dt}</td>
                                <td>${g.gi_mod_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${g.gi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${g.gi_seq}"><i class="fas fa-minus-circle"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pager_area">
                <button id="prev"><i class="fas fa-chevron-left"></i></button>
                <div class="pagers">
                    <c:forEach begin="1" end="${data.pageCnt}" var="i">
                        <a href="/goods?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="goods_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-shopping-bag"></i>
                </div>
                <h2>제품 추가</h2>
                <p>제품 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="brand_name" placeholder="브랜드 명" disabled>
                <button id="search_br">브랜드 검색</button>
                <br>
                <select id="go_category">
                    <option value="1">남성가방</option>
                    <option value="2">여성가방</option>
                    <option value="3">남성지갑</option>
                    <option value="4">여성지갑</option>
                    <option value="5">남성악세사리</option>
                    <option value="6">여성악세사리</option>
                </select> <br>
                <input type="text" id="go_name" placeholder="제품명"><br>
                <input type="text" id="go_sub" placeholder="설명"><br>
                <input type="number" id="go_price" placeholder="가격"><br>
                <input type="number" id="go_stock" placeholder="제고"><br>
            </div>
            <div class="btn_area">
                <button id="add_go">등록하기</button>
                <button id="modify_go">수정하기</button>
                <button id="cancel_go">취소하기</button>
            </div>
        </div>
    </div>
    <div class="brand_search">
        <div class="br_search_box">
            <input type="text" id="br_keyword" placeholder="예) 샤넬, 에르메스">
            <button id="br_search_btn"><i class="fas fa-search"></i></button>
        </div>
        <div class="search_result">
            <ul>

            </ul>
        </div>
        <div class="br_search_buttons">
            <button id="br_search_close">닫기</button>
        </div>
    </div>
</body>
</html>