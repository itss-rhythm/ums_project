

<%!
		String roll,pass,phone,address,email;
%>
<%		
		roll=(String)application.getAttribute("roll");
		pass=(String)application.getAttribute("pass");
		phone=(String)application.getAttribute("phone");
		address=(String)application.getAttribute("address");
		email=(String)application.getAttribute("email");

%>



<head>
  <link rel="stylesheet" href="stylesheet.css">

<style>
  span {
      position: absolute;
      left : 350px;
      font-size:95px;
      color: white;
  }
</style>

</head>


<div style="background-color: #3BB9FF" border-radius:100px; border: #3BB9FF;>
	<img src="ymca_logo.png" width = "200" height="200" alt="J.C. Bose University of Science and 		Technology, YMCA">
        <span> J.C. Bose University of Science and Technology </span>
</div>

<style>
    body {
	background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1nPFasRgAu0hTQTR1bk3gBA4NnWL5xfXqovCd79Xjs-H3dM1u8Q");
       	background-repeat:no-repeat;
       	background-size:cover;
    } 

</style>

<font size="7" color="white">	
	
	 <table style="width:50%" cellpadding="8px" text="white" align="center" border="10px" bgcolor="white">
  <tr>
    <th>PARAMETER</th>
    <th>Data Saved</th>
  </tr>
  <tr>
    <td>Roll</td>
    <td><%= roll %></td>
  </tr>
  <tr>
    <td>Password</td>
    <td><%= pass %></td>
  </tr>
  <tr>
    <td>Phone</td>
    <td><%= phone %></td>
  </tr>
  <tr>
    <td>Address</td>
    <td><%= address %></td>
  </tr>
  <tr>
    <td>Email</td>
    <td><%= email %></td>
  </tr>
</table> 
</font>

<a class="anchor2" href="student.html">LOGOUT??</a>
	






<!1.https://upload.wikimedia.org/wikipedia/en/a/ae/J.C._Bose_University_of_Science_and_Technology%2C_YMCA_logo.png 2.https://i.ibb.co/bBGRF4q/Whats-App-Image-2019-08-26-at-22-18-27.jpg>