// buyer.js
$(function(){
    $(".main_menu a:nth-child(3)").addClass("active");

    $("#search_btn").click(function(){
        location.href="/customer?keyword="+$("#keyword").val();
    })
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13) {
            $("#search_btn").trigger("click");
        }
    })

    $("#add_goods").click(function(){
        $(".popup_wrap").addClass("open");
        $("#add_go").css("display", "inline-block");
        $("#modify_go").css("display", "none");
        $(".popup .top_area h2").html("고객 추가");
        $(".popup .top_area p").html("고객 정보를 입력해주세요");
    })
    $("#add_go").click(function(){
        if(confirm("고객 정보를 등록하시겠습니까?") == false) return;
        let buyer_id = $("#buyer_id").val()
        let buyer_pwd = $("#buyer_pwd").val()
        let buyer_name = $("#buyer_name").val()
        let buyer_birth = $("#buyer_birth").val()
        let buyer_gender =  $("#buyer_gender option:selected").val()
        let buyer_address = $("#buyer_address").val()
        let buyer_phone = $("#buyer_phone").val()
        let buyer_email = $("#buyer_email").val()
        let buyer_grade = $("#buyer_grade option:selected").val()
        let buyer_status = $("#buyer_status option:selected").val()

        let buyer_pwd_confirm = $("#buyer_pwd_confirm").val()

        if(buyer_pwd == '') {
            alert("비밀번호를 입력해주세요.")
            return;
        }
        if(buyer_pwd != buyer_pwd_confirm) {
            alert("비밀번호가 일치하지 않습니다.")
            return;
        }

        let data = {
            bi_id : buyer_id,
            bi_pwd : buyer_pwd, 
            bi_name : buyer_name,
            bi_birth : buyer_birth,
            bi_gender : buyer_gender,
            bi_address : buyer_address,
            bi_phone : buyer_phone,
            bi_email : buyer_email,
            bi_grade : buyer_grade,
            bi_status : buyer_status,
        }
        $.ajax({
            url:"/customer/add",
            type:"post",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(e) {
                alert(e.message)
                if(e.status)
                    location.reload();
            }
        })    
    })
    $("#cancel_go").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.")== false) return;
        $("#buyer_name").val("");
        $("#buyer_id").val("");
        $("#buyer_pwd").val("");
        $("#buyer_pwd_confirm").val("");
        $("#buyer_birth").val("");
        $("#buyer_gender").val("1").prop("selectd", true);
        $("#buyer_address").val("");
        $("#buyer_phone").val("");
        $("#buyer_email").val("");
        $("#buyer_grade").val("1").prop("selectd", true);
        $("#buyer_status").val("0").prop("selectd", true);
        
        $(".popup_wrap").removeClass("open");
    });
    $(".delete_btn").click(function(){
        if(confirm("고객 정보를 삭제하시겠습니까?") == false) return;
        // alert( $(this).attr("data-seq") )
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/customer/delete?seq="+seq,
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
        $(".popup .top_area h2").html("고객 정보 수정");
        $(".popup .top_area p").html("수정할 내용을 입력해주세요");
        $.ajax({
            type:"get",
            url:"/customer/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                console.log(r);
                $("#buyer_name").val(r.data.bi_name);
                $("#buyer_id").val(r.data.bi_id);
                $("#buyer_pwd").val("********").prop("disabled", true);;
                $("#buyer_pwd_confirm").val("********").prop("disabled", true);;
                $("#buyer_birth").val(r.data.bi_birth)
                $("#buyer_gender").val(r.data.bi_gender).prop("selectd", true);
                $("#buyer_address").val(r.data.bi_address);
                $("#buyer_phone").val(r.data.bi_phone);
                $("#buyer_email").val(r.data.bi_email);
                $("#buyer_grade").val(r.data.bi_grade).prop("selectd", true);
                $("#buyer_status").val(r.data.bi_status).prop("selectd", true);
            }
        })
    })
    $("#modify_go").click(function(){
        if(confirm("수정하시겠습니까?") == false) return;
        let data = {
            bi_seq : modify_data_seq,
            bi_id : $("#buyer_id").val(),
            bi_pwd : $("#buyer_pwd").val(),
            bi_name : $("#buyer_name").val(),
            bi_birth : $("#buyer_birth").val(),
            bi_gender :  $("#buyer_gender option:selected").val(),
            bi_address : $("#buyer_address").val(),
            bi_phone : $("#buyer_phone").val(),
            bi_email : $("#buyer_email").val(),
            bi_grade : $("#buyer_grade option:selected").val(),
            bi_status : $("#buyer_status option:selected").val()
        }
        $.ajax({
            url:"/customer/update",
            type:"patch",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message)
                if(r.status)
                    location.reload();
            }
        })
    })
})