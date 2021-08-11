var flagUpdate = "";

function notNull(data) {
    if (data === 0) {
        return data;
    } else {
        return data ? data.toString().trim() : "";
    }
}


function simpanData() {
    if (flagUpdate !== "update") {
        alert("insert"); return;
        var jsondata = {
            "jnsRef": $("#jenisReferensi option:selected").text(),
            "sandi": $("#sandi").val(),
            "label": $("#label").val(),
            "namaTabel": $("#jenisReferensi").val()
        };
        $.ajax({
            url: '/technicalProject/simpanData',
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(jsondata),
            success: function (data, status, xhr) {

                var isi = data["data"];

                if (data.status == 0) {
                    swal("Sukses!", "Sukses Simpan Data", "success");
                    drawTableMasterReferensi();
                    clearForm();
                } else {
                    swal("Warning!", "Gagal Simpan Data. " + data.message, "error");
                }
            },
            error: function (xhr, status, e) {
                swal("Warning!", "500 - Internal Server Error. " + status, "error");
            }
        });
    } else {
        alert("update");
        var jsondata = {
            "idRef": $("#idReferensi").val(),
            "jnsRef": $("#jenisReferensi option:selected").text(),
            "sandi": $("#sandi").val(),
            "label": $("#label").val(),
            "namaTabel": $("#jenisReferensi").val()
        };
        $.ajax({
            url: '/technicalProject/updateData',
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(jsondata),
            success: function (data, status, xhr) {

                var isi = data["data"];

                if (data.status == 0) {
                    swal("Success!", "Success Update Master Referensi Data ", "success");
                    drawTableMasterReferensi();
                    clearForm();
                    flagUpdate = "bukan update";
                } else {
                    swal("Warning!", "Failed Update Master Referensi Data. " + data.message, "error");
                }
            },
            error: function (xhr, status, e) {
                swal("Warning!", "500 - Internal Server Error. " + status, "error");
            }
        });
    }
}


function drawDataTable() {
    
    $('#dataTable').dataTable({
        "dom": 'Bfrtip',
        "fixedHeader": true,
        "bFilter": true,
//        "bPageinate": true,
        "bInfo": true,
        "iDisplayStart": 0,
        "bProcessing": true,
//        "bServerSide": true,
        "deferRender": true,
        "destroy": true,
        "ajax": {
            "url": '/technicalProject/retrieveData',
            "type": "GET",
            "dataType": "json",
            "data": jsonData,
            "dataSrc": function (json) {
                if (json["data"] === "ERR") {
                    swal("Warning!", "Terjadi Kesalahan", "error");
                    return "";
                } else {
                    return json.data;
                }
            }
        },
        "columns": [
            {"data": "id", className: "text-left"},
            {"data": "jnsRef", className: "text-left"},
            {"data": "sandi", className: "text-left"},
            {"data": "label", className: "text-left"},
            {"data": "tglInput", className: "text-left"},
            {"data": null, "render": function (data, type, row) {
                    return "<button class='btn-success' onclick='editClick(this)'><i class='fa fa-pencil' ></i> Edit</button> " +
                            "<button class='btn-danger' onclick='deleteClick(this)'><i class='fa fa-trash' ></i> Delete</button>";
                }, className: "text-center"
            }
        ],
        initComplete: function () {
            var api = this.api();

        },
        "columnDefs": [
            {'sortable': false, 'targets': '_all'},
            {"className": "text-center"}
        ],
        "scrollX": true,
        "scrollCollapse": false
    });
    
}

