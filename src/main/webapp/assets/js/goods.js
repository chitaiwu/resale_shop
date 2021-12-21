// goods.js
$(function(){
    $(".main_menu a:nth-child(2)").addClass("active");
    // 헤더에 현재 위치 알려준다.
    $("#add_goods").click(function(){
        // alert("제품 추가 팝업 열기")
        $(".popup_wrap").addClass("open");
        $("#add_go").css("display", "inline-block");
        $("#modify_go").css("display", "none");
        $(".popup .top_area h2").html("제품 추가");
        $(".popup .top_area p").html("제품 정보를 입력해주세요");
    })
    $("#add_go").click(function(){
        if(confirm("제품을 등록하시겠습니까?") == false) return;
        let brand_name = $("#brand_name").attr("data-br-seq")
        let go_category = $("#go_category option:selected").val()
        let go_name = $("#go_name").val()
        let go_sub = $("#go_sub").val()
        let go_price = $("#go_price").val()
        let go_stock = $("#go_stock").val()

        if(brand_name == undefined) {
            alert("브랜드를 입력하세요.");
            return;
        }
        
        let data = {
            gi_bi_seq : brand_name,
            gi_gc_seq : go_category,
            gi_name : go_name,
            gi_sub : go_sub,
            gi_price : go_price,
            gi_stock : go_stock
        }
        $.ajax({
            type:"post",
            url:"/goods/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                if(r.status)
                    location.reload();
            }
        })
    })
    $("#cancel_go").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.")== false) return;
        $("#brand_name").attr("data-br-seq","");
        $("#brand_name").val("");
        $("#go_category").val("1").prop("selectd", true);
        $("#go_name").val("");
        $("#go_sub").val("");
        $("#go_price").val("");
        $("#go_stock").val("");
        
        $(".popup_wrap").removeClass("open");
    });
    $(".delete_btn").click(function(){
        if(confirm("제품을 삭제하시겠습니까?") == false) return;
        // alert( $(this).attr("data-seq") )
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/goods/delete?seq="+seq,
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        });
    });
    let modify_data_seq =0;
    $(".modify_btn").click(function(){
        // alert( $(this).attr("data-seq") )
        modify_data_seq = $(this).attr("data-seq");
        $(".popup_wrap").addClass("open");
        $("#add_go").css("display", "none");
        $("#modify_go").css("display", "inline-block");
        $(".popup .top_area h2").html("제품 수정");
        $(".popup .top_area p").html("수정할 내용을 입력해주세요");
        $.ajax({
            type:"get",
            url:"/goods/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                console.log(r);
                $("#brand_name").attr("data-br-seq",r.data.gi_bi_seq);
                $("#brand_name").val(r.data.brand_name);
                $("#go_category").val(r.data.gi_gc_seq).prop("selectd", true);
                $("#go_name").val(r.data.gi_name);
                $("#go_sub").val(r.data.gi_sub);
                $("#go_price").val(r.data.gi_price);
                $("#go_stock").val(r.data.gi_stock);
            }
        })
    })
    $("#modify_go").click(function(){
        // alert(modify_data_seq);
        if(confirm("수정하시겠습니까?") == false) return;
        let brand_name = $("#brand_name").val()
        let go_category = $("#go_category option:selected").val()
        let go_name = $("#go_name").val()
        let go_sub = $("#go_sub").val()
        let go_price = $("#go_price").val()
        let go_stock = $("#go_stock").val()
        

        let data = {
            gi_seq : modify_data_seq,
            gi_bi_seq : brand_name,
            gi_gc_seq : go_category,
            gi_name : go_name,
            gi_sub : go_sub,
            gi_price : go_price,
            gi_stock : go_stock
        }
        $.ajax({
            type:"patch",
            url:"/goods/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })
    // 조회 기능
    $("#search_btn").click(function(){
        location.href="/goods?keyword="+$("#keyword").val();
    })
    // 엔터키에 조회기능 부여
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13) {
            $("#search_btn").trigger("click");
        }
    })
    $("#search_br").click(function(){
        $(".brand_search").css("display", "block")
    })
    $("#br_search_close").click(function(){
        $(".brand_search").css("display", "")
    })
    $("#br_keyword").keyup(function(e){
        if(e.keyCode == 13) $("#br_search_btn").trigger("click");
    })
    $("#br_search_btn").click(function(){
        $.ajax({
            url:"/goods/keyword?keyword="+$("#br_keyword").val(),
            type:"get",
            success:function(r) {
                console.log(r);
                $(".search_result ul").html("");
                for(let i=0; i<r.list.length; i++) {
                    let tag =
                        '<li>'+
                            '<a href="#" data-br-seq="'+r.list[i].bi_seq+'">'+r.list[i].bi_name+'</a>'+
                        '</li>';
                    $(".search_result ul").append(tag);
                }
                $(".search_result ul a").click(function(e){
                    e.preventDefault(); // a 태그의 링크 기능 제거
                    let seq = $(this).attr("data-br-seq");
                    let name = $(this).html();

                    $("#brand_name").attr("data-br-seq", seq);
                    $("#brand_name").val(name);

                    $(".search_result ul").html("");
                    $("#br_keyword").val("");
                    $(".brand_search").css("display", "");
                })
            }
        })
    })
})