   $(document).ready(function(){
        $("select").change(function(){
            $( "select option:selected").each(function(){
               
                if($(this).attr("value")=="lecturer"){
                    $("#streamId").hide();
                }if($(this).attr("value")=="student"){
					$("#streamId").show();
				}
               
            });
        }).change();
    });