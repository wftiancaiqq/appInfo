$(function(){
    $(".level").change(function(){
        var $nextLevel = $(this).parents(".item").next().children(".col-md-6").children(".level");
        if($(this).attr("name") == "categoryLevel1"){
            $("#categoryLevel3").html("<option value=\"0\">--请选择--</option>");
        }
        $.ajax({
            type:"GET",//请求类型
            url:"appInfoDevController/categoryLevel",
            data:{parentId:$(this).val()},
            dataType:"json",
            success:function(data){
                if($nextLevel == null || $nextLevel == "" || $nextLevel == undefined){
                    return;
                }
                $nextLevel.html("");
                var options = "<option value=\"0\">--请选择--</option>";
                for(var i = 0; i < data.length; i++){
                    options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                }
                $nextLevel.html(options);
            },
            error:function(data){
                alert("加载分类失败！");
            }
        });
    });
	
	$("#back").on("click",function(){
		window.location.href = "/appInfoDevController/appInfoList";
	});
	
	$("#APKName").bind("blur",function(){
		//ajax后台验证--APKName是否已存在
		$.ajax({
			type:"GET",//请求类型
			url:"appInfoDevController/jsonAPKName",//请求的url
			data:{apkName:$("#APKName").val()},//请求参数
			dataType:"text",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data == "empty"){//参数APKName为空，错误提示
					$("#message").html("APKName为不能为空！");
				}else if(data == "exist"){//账号不可用，错误提示
                    $("#message").html("该APKName已存在，不能使用！");
				}else if(data == "noexist"){//账号可用，正确提示
                    $("#message").html("该APKName可以使用！");
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("请求错误！");
			}
		});
	});

});
      
      
      