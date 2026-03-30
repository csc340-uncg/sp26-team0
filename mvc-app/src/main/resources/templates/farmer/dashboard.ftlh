<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <title>Document</title>
</head>

<body>
  <div class="container mt-5">
    <h1>Farmer Dashboard</h1>
    <p>Welcome to your dashboard! Here you can manage your products, view orders, and update your profile.</p>
    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title"> Profile Summary <span style="font-style: italic; float: right;"> View and edit your
            profile information.</span></h5>
        <p class=" card-text">Email: ${currentFarmer.email}
        </p>
        <p> Bio: ${currentFarmer.bio}</p>
        <a href="#" class="btn btn-primary">Go to Profile Settings</a>
      </div>
    </div>
    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title"> Farm Details <span style="font-style: italic; float: right;"> View and edit your farm
            information.</span></h5>
        <p> Farm: ${currentFarmer.farm.name}</p>
        <p> Location: ${currentFarmer.farm.location}</p>
        <p>Desc: ${currentFarmer.farm.description} </p>
        <a href="#" class="btn btn-primary">Go to Farm Settings</a>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Manage Latest Boxes <span style="font-style: italic; float: right;">Add, edit, or remove your
            products from the marketplace. </span></h5>
        <div class="list-group">
          <#list currentFarmer.farm.produceBoxes as box>
            <div class="list-group-item">
              <p class="card-text">Box: ${box.title} - $${box.price}</p>
              <p class="card-text">Description: ${box.description}</p>
              <p class="card-text">Season: ${box.season}</p>
              <a href="#" class="btn btn-secondary mb-2">Edit Box</a>
              <a href="#" class="btn btn-danger mb-2">Delete Box</a>
            </div>
          </#list>
        </div>
        <a href="#" class="btn btn-primary">Go to Product Management</a>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>

</body>

</html>