$(document).ready(function(){
    $("#submitButton").on('click', function(){
        $.ajax({
            url : "CallAnt",
            success : function(result){
                alert('what');
            }
        })
    });
});