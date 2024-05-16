<!DOCTYPE html>
<html lang="en">
   <head>
      <title>Storage Facility</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
      <link rel="stylesheet" href="bootstrap/custom.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
   </head>
   <body>
       <?php
        include "connect.php";
        $query = mysqli_query($connect,"SELECT * FROM outreach WHERE status=1");

        $rs = mysqli_num_rows($query);

      ?>
      <nav class="navbar navbar-default navbar-fixed-top">
         <div class="container">
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="homepage.html"><span class="glyphicon glyphicon-home"></span> National Storage Facility</a>				
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
               <ul class="nav navbar-nav navbar-right">
                  <li>
                  </li>
                  <li>
                     <a  href="outreach.php">New Orders <span class="badge badge-info"><?php echo $rs?></span></a>
                  </li>
                  
               </ul>
            </div>
         </div>
      </nav>
      <br><br><br><br>
      <div class="container mt-3" >
         <?php
         include "connect.php";
         $query = mysqli_query($connect,"SELECT vacine_name FROM centers WHERE status=1");   
         ?>
       
        <h3>Confirm Shipment!!</h3>
		<table class="table table-striped" id="tbdata">
			<thead>
				<tr>				
				<th scope="col">Regional Hospital</th>
				<th scope="col">Vaccine Name</th>
				<th scope="col">Vaccine Qty</th>
                <th scope="col">Date</th>
                <th>Control</th>
				</tr>
			</thead>
			<tbody>
            <?php
                $query1 = mysqli_query($connect,"SELECT * FROM outreach WHERE status=1");                       
               while($rs = mysqli_fetch_array($query1)){
                   if($rs[5]==1){
                       $badge = "<badge class='badge badge-danger'>Pending</badge>";
                   }else{
                      $badge = "<badge class='badge badge-success'>Recieved</badge>";
                   }
                  echo "
                  <tr>                  
                  <td>$rs[1]</td>
                  <td>$rs[2]</td>
                  <td>$rs[3]</td>
                  <td>$rs[4]</td>
                  <td><button class='btn btn-success' value=\"$rs[0]\" onclick='confirm(this)'>CONFIRM!!</button></td>
                  </tr>
                  ";
               }

            ?>
				
			</tbody>
		</table>
		</div>
      <footer class="container-fluid text-center">
         <a href="#signUpPage" title="To Top">
         <span class="glyphicon glyphicon-chevron-up"></span>
         </a>
      </footer>
   </body>
   <script src="bootstrap/jquery.js"></script>
   <script>
       function confirm(btn_obj){
        var rowId = event.target.parentNode.parentNode.id;        
        var row = document.getElementById(rowId);
        var id = btn_obj.value;
        var formdata = new FormData();
			formdata.append('id',id);			
            formdata.append('stage','out_confirm');
            $.ajax({
                url: "crad.php",
                dataType:"text",
                type: "POST",
                data: formdata,
                cache: false,
                contentType: false,
                processData: false,
                success: function(data) {                 
                    // Success message
                    alert(data);                    
                },
                error: function() {
                    // Fail message
                   alert("Error Occured");
                },
            })
       }

       function dispatch(){          
			var v_name = $("#vname").val();
            var qty = $("#qty").val();
            var f_id = $("#facilty").val();
            var f_name = $("#facilty option:selected").text();
            if(f_id <1){
                alert("Select Category");
                return
            }
			
			if(v_name.length<1 || qty.length<1){
				alert("Please Fill the fields");
				return;
			}            

			var formdata = new FormData();
			formdata.append('vname',v_name);
			formdata.append('f_id',f_id);
			formdata.append('f_name',f_name);
            formdata.append('qty',qty);
            formdata.append('stage','storage');
            $.ajax({
                url: "crad.php",
                dataType:"text",
                type: "POST",
                data: formdata,
                cache: false,
                contentType: false,
                processData: false,
                success: function(data) {                 
                    // Success message
                    console.log(data);
                    $('#success').html("<div class='alert alert-success'>");
                    $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-success')
                        .append("<strong>Dispatch Sent Successfull!! </strong>");
                    $('#success > .alert-success')
                        .append('</div>');

                    //clear all fields
                    $('#contactForm').trigger("reset");
                },
                error: function() {
                    // Fail message
                    $('#success').html("<div class='alert alert-danger'>");
                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-danger').append("<strong>Sorry, it seems that my mail server is not responding...</strong> Could you please email me directly to <a href='mailto:me@example.com?Subject=Message_Me from myprogrammingblog.com'>me@example.com</a> ? Sorry for the inconvenience!");
                    $('#success > .alert-danger').append('</div>');
                    //clear all fields
                    $('#contactForm').trigger("reset");
                },
            })
         }
   </script>
</html>