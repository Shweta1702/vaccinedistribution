<?php
include_once 'dbconnect.php';

$sql = "SELECT * FROM Vaccine";
if(! ($result = mysqli_query($conn, $sql)))
{
	
	echo "Errormessage: ".mysqli_error($conn)."\n";
}
else
{
	echo "<table>
		<tr><th>Vaccine ID</th>
		<th>Vaccine  Name</th>
		<th>Vaccine Description</th></tr>";
        <th>Manufacturer</th></tr>
        <th>Healthcare Association </th></tr>

	while($row = mysqli_fetch_array($result))
	{
		echo "<tr><td>".$row['ID']."</td><td>".$row['type']."</td><td>".$row['company']."</td></tr>";
	}
	echo "</table>";
}

mysqli_close($conn);


?>