<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/goods_list.css">
    <link rel="stylesheet" href="/assets/css/buyer_list.css">
    <script src="/assets/js/buyer.js"></script>
</head>

<body>
    <main>
        <h1><i class=""></i> 고객 관리</h1>
        <button id="add_goods"><i class="fas fa-plus-circle"></i> 고객 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <!-- <select id="search_type">
                        <option value="brand">브랜드</option>
                        <option value="name"
                            <c:if test="${data.type=='name'}">selected</c:if>
                        >제품명</option>
                    </select> -->
                    <input type="text" id="keyword" placeholder="이름 검색" value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>아이디(ID)</th>
                            <th>이름</th>
                            <th>생년월일</th>
                            <th>성별</th>
                            <th>주소</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                            <th>등급</th>
                            <th>상태</th>
                            <th>가입일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.total == 0}">
                            <tr>
                                <td id="nadata" colspan="12">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="b">
                            <tr>
                                <td>${b.bi_seq}</td>
                                <td>${b.bi_id}</td>
                                <td>${b.bi_name}</td>
                                <td>${b.bi_birth}</td>
                                <td class="buyer_gender">
                                    <c:if test="${b.bi_gender == 1}">남성</c:if>
                                    <c:if test="${b.bi_gender == 2}">여성</c:if>
                                </td>
                                <td>${b.bi_address}</td>
                                <td>${b.bi_phone}</td>
                                <td>${b.bi_email}</td>
                                <td class="buyer_grade">
                                    <c:if test="${b.bi_grade == 1}">일반</c:if>
                                    <c:if test="${b.bi_grade == 2}">실버</c:if>
                                    <c:if test="${b.bi_grade == 3}">골드</c:if>
                                    <c:if test="${b.bi_grade == 4}">플래티넘</c:if>
                                </td>
                                <td class="buyer_status">
                                    <c:if test="${b.bi_status == 0}">
                                        <span style="background-color: rgb(17, 226, 27)">정상</span>
                                    </c:if>
                                    <c:if test="${b.bi_status == 1}">
                                        <span style="background-color: rgb(251, 186, 64)">휴면</span>
                                    </c:if>
                                    <c:if test="${b.bi_status == 2}">
                                        <span style="background-color: rgb(255, 23, 23)">탈퇴</span>
                                    </c:if>
                                </td>
                                <td>
                                    <fmt:formatDate value="${b.bi_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                                <td>
                                    <button class="modify_btn" data-seq="${b.bi_seq}"><i
                                            class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${b.bi_seq}"><i
                                            class="fas fa-minus-circle"></i></button>
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
                        <a href="/customer?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
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
                <h2>고객 추가</h2>
                <p>고객 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="buyer_name" placeholder="고객 명">
                <input type="text" id="buyer_id" placeholder="고객 ID">
                <input type="password" id="buyer_pwd" placeholder="비밀번호">
                <input type="password" id="buyer_pwd_confirm" placeholder="비밀번호 확인">
                <input type="text" id="buyer_birth" placeholder="생년월일 (YYMMDD)">
                <select id="buyer_gender">
                    <option value="1">남성</option>
                    <option value="2">여성</option>
                </select>
                <input type="text" id="buyer_address" placeholder="고객 주소">
                <input type="text" id="buyer_phone" placeholder="전화번호 (01012345678)">
                <input type="text" id="buyer_email" placeholder="이메일 (mail@mail.com)">
                <select id="buyer_grade">
                    <option value="1">일반</option>
                    <option value="2">실버</option>
                    <option value="3">골드</option>
                    <option value="4">플래티넘</option>
                </select>
                <select id="buyer_status">
                    <option value="0">정상</option>
                    <option value="1">휴면</option>
                    <option value="2">탈퇴</option>
                </select>
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
    </main>
</body>

</html>