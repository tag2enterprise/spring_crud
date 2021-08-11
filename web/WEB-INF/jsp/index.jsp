<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Custom fonts for this template-->
        <link href="../../technicalProject/startbootstrap-sb-admin-2-gh-pages/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="../../technicalProject/startbootstrap-sb-admin-2-gh-pages/css/sb-admin-2.min.css" rel="stylesheet">


        <style>
            .select2-container.select2-container--default.select2-container--open  {
                z-index: 5000;
            }
        </style>
    </head>

    <body>
        <div class="row">
            <div class="col-lg-7">
                <div class="p-5">
                    <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-4">Data Karyawan</h1>
                    </div>
                    <form class="user">
                        <div class="form-group row">
                            <label for="nama" class="col-sm-3 mb-3 mb-sm-0">Nama *</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="employee_name" placeholder="Nama..">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nip" class="col-sm-3 mb-3 mb-sm-0">NIP *</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="nip" placeholder="NIP..">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nip" class="col-sm-3 mb-3 mb-sm-0">Departemen *</label>
                            <div class="col-sm-6">
                                <select id="departement" name="departemen" class="select2_demo_1 form-control m-b-n-sm optJenisReferensi">
                                    <c:forEach items="${departements}" var="departement">
                                        <option value="${departement.department_id}">${departement.department_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="unit" class="col-sm-3 mb-3 mb-sm-0">Unit *</label>
                            <div class="col-sm-6">
                                <select id="unit" name="unit" class="select2_demo_1 form-control m-b-n-sm optJenisReferensi">
                                </select>
                            </div>
                        </div>

                    </form>
                    <div class="form-group row">
                        <div class="col-sm-6"></div>
                        <!--button cancel-->
                        <button id="btn_insert" class="col-sm-3 btn btn-primary btn-user btn-block">Simpan</button>
                    </div>
                    <hr>

                    <hr>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>NIP</th>
                                        <th>Nama</th>
                                        <th>Departemen</th>
                                        <th>Unit</th>
                                        <th>Aksi</th>
                                    </tr>
                                </thead>
                                <tbody id="tBody">
                                    <tr>
                                        <td>1</td>
                                        <td>98765</td>
                                        <td>Shania Putri</td>
                                        <td>Human Resource</td>
                                        <td>Payroll</td>
                                        <td><button class="btn-success" onclick="editClick(this)"><i class="fa fa-pencil" ></i> Edit</button>
                                            <button class="btn-danger" onclick="deleteClick(this)"><i class="fa fa-trash" ></i> Delete</button></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <script src="../../technicalProject/js/jquery-3.6.0.min.js" type="text/javascript"></script>
                    <script src="../../technicalProject/js/home.js" type="text/javascript"></script>
                    </body>
                    </html>
