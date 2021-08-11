/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function simpanData() {
    alert("ok");
    $.ajax({
        url: '../web/employee?employee_id='+$("#nip").val()
                +'&employee_name='+$("#employee_name").val()
                +'&department_id='+$("#departement").val()
                +'&unit_id='+$("#unit").val(),
        type: 'POST',
        contentType: 'application/json',
        success: function (data) {
            alert("okkkkkkkk");
        }
    });
}

function getListUnit(department_id) {
    $.ajax({
        url: '../web/unit?department_id=' + department_id,
        type: 'GET',
        contentType: 'application/json',
        success: function (data) {
            var dataJson = JSON.parse(data);
            var add_dropdown = "";
            $.each(dataJson.data, function (index, value) {
                add_dropdown += "<option value='" + value.unit_id + "'>" + value.unit_name + "</option>";
            });
            $("#unit").append(add_dropdown);
        }
    });
}

$(document).ready(function () {
    $("#departement").change(function () {
        getListUnit($(this).val());
    });
    
    $("#btn_insert").click(function(){
        simpanData(); 
    });
    
});

