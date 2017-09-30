<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>    
    <div id="wrapper" class="">
         <!-- import alert form  -->
	<c:if test="${not empty requestScope.status}">
		<c:import url="/WEB-INF/views/users/alert.jsp"></c:import> 
		<!-- alert message -->
		<c:choose>
			<c:when test="${requestScope.status == 1}">
				<script>
					alertMessgae( "Success,","Successfuly add the user");
	            </script>
	  		</c:when>
	  		
	  		<c:otherwise>
	        	<script>
					 alertMessgae( "ERROR OCCURED ","Failed to add the user");
				</script>
	    	</c:otherwise>
		</c:choose>
		<!-- /alert message -->
  	</c:if>
  	<!-- .import alert form  -->
        

        <div id="page-content-wrapper">
            <div id="main-content">
                <div class="content-body">
                    <div class="col-lg-12 main-box-container">
                        <div class="box">
                            <div class="box-head clearfix">
                                <h1 style="margin-bottom:12px;" class="pull-left">Manager Account  -  Register Employee</h1>
                            </div>
    <div class="box-content">
    	<form action="registerUser" method="post">
        <div class="table-container">
            <table id="edit_account" class="table is-datatable dataTable">
                <thead>
                     <tr>
                         <th>Employee Info</th>
                         <th>Content</th>
                     </tr>
                </thead>
                <tbody>
                     <tr>
                         <td>First Name</td>
                         <td><input type="text" name = "firstName" required="required" placeholder="Enter  first name"  class="form-control"></td>
                     </tr>
                     <tr>
                         <td>Last Name</td>
                         <td><input type="text" name = "lastName" required="required" placeholder="Enter  last name" class="form-control"></td>
                     </tr>
                     
                     <tr>
                         <td>Address</td>
                         <td><input type="text" name = "address" required="required" placeholder="Enter  address" class="form-control"></td>
                     </tr>
                     
                     <tr>
                         <td>ZipCode</td>
                         <td><input type="text" name = "zipcode" required="required" placeholder="Enter ZipCode" class="form-control"></td>
                     </tr>
                     
                     <tr>
                         <td>Phone Number</td>
                         <td><input type="text" name = "telephone" required="required" placeholder="Enter phone number" class="form-control"></td>
                     </tr>
                     
                     <tr>
                         <td>Email</td>
                         <td> <input name = "email" type="email"   class="form-control"  placeholder="Enter Email address" required="required" autofocus></td>
                     </tr>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
                    <button id="update_account" type="submit" class="btn btn-primary" onclick="update_account()">Add</button>
        </div>
        </form>
    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>  
</html>
