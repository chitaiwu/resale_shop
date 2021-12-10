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
    <link rel="stylesheet" href="/assets/css/index.css">
</head>
<body>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <main>
        ${cnt}
        <h1>쇼핑몰 관리 대시보드 (Shop Management Dashboard)</h1>
        <div class="content_area">
            <div class="goods_info">
                <h2><i class="fas fa-shopping-bag"></i>제품 관리</h2>
                <p>총 카테고리 개수 : <span>${cnt.category[0]}개</span></p>
                <p>총 상품 개수 : <span>${cnt.goods[0]}개</span></p>
                <p>업데이트 날짜 : <span>2021-12-10 12:00:00</span></p>
            </div>
            <div class="customer_info">
                <h2><i class="fas fa-id-card-alt"></i>고객 관리</h2>
                <p>총 고객 수 : <span>${cnt.customer[0]}명</span></p>
                <p>플래티넘 등급 : <span>${cnt.customer[1]}명</span></p>
                <p>골드 등급 : <span>${cnt.customer[2]}명</span></p>
                <p>실버 등급 : <span>${cnt.customer[3]}명</span></p>
                <p>일반 등급 : <span>${cnt.customer[4]}명</span></p>
                <p>업데이트 날짜 : <span>2021-12-10 12:00:00</span></p>
            </div>
            <div class="review_info">
                <h2><i class="fas fa-comments"></i>후기 관리</h2>
                <p>총 후기 개수 : <span>${cnt.review[0]}개</span></p>
                <p>업데이트 날짜 : <span>2021-12-10 12:00:00</span></p>
            </div>
        </div>
    </main>
</body>
</html>