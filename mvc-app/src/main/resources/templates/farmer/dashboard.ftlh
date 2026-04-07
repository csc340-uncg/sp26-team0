<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Farmer Dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="farmer" />
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
        <a href="/farmer/profile" class="btn btn-primary">Go to Profile Settings</a>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title"> Farm Details <span style="font-style: italic; float: right;"> View and edit your farm
            information.</span></h5>
        <#if currentFarmer.farm??>
          <p> Farm: ${currentFarmer.farm.name}</p>
          <p> Location: ${currentFarmer.farm.location}</p>
          <p>Desc: ${currentFarmer.farm.description} </p>
          <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
              <li class="list-group-item">Total Boxes: <span
                  class="badge text-bg-primary rounded-pill">${totalBoxes}</span></li>
              <li class="list-group-item">Total Subscriptions: <span
                  class="badge text-bg-primary rounded-pill">${totalSubscriptions}</span></li>
              <li class="list-group-item">Average Rating: <span
                  class="badge text-bg-primary rounded-pill">${averageRating}</span></li>
            </ul>
          </div>

          <a href="/farmer/farm/settings" class="btn btn-primary" style="margin: 5px;">Go to Farm Settings</a>


          <#else>
            <div class="alert alert-info">
              Please set up your farm details to start selling produce.
              <a href="/farmer/farm/setup" class="btn btn-primary btn-sm mt-2" style="margin: 5px;">Set Up Farm</a>
            </div>
        </#if>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Manage Latest Boxes <span style="font-style: italic; float: right;">Add, edit, or remove
            your
            products from the marketplace. </span></h5>
        <#if currentFarmer.farm??>

            <a href="/farmer/boxes/new" class="btn btn-primary btn-lg btn-block" style="margin: 5px;">Add New Produce Box</a>
          <div class="list-group">
            <#list currentFarmer.farm.produceBoxes?reverse[0..*2] as box >
              <div class="list-group-item">
                <p class="card-text">Box: ${box.title} - $${box.price}</p>
                <p class="card-text">Description: ${box.description}</p>
                <p class="card-text">Season: ${box.season}</p>
                <a href="/farmer/boxes/edit/${box.boxId}" class="btn btn-secondary mb-2">Edit Box</a>
                <button class="btn btn-danger mb-2" data-bs-toggle="modal" data-bs-target="#deleteModal${box.boxId}">Delete Box</button>
                <!-- Delete Confirmation Modal -->
                <div class="modal fade" id="deleteModal${box.boxId}" tabindex="-1">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title">Confirm Delete</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                      </div>
                      <div class="modal-body">
                        Are you sure you want to delete "${box.title}"?
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <a href="/farmer/boxes/delete/${box.boxId}" class="btn btn-danger">Delete</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </#list>
            <#else>
              <div class="alert alert-info">
                Please set up your farm details to start selling produce.
                <a href="/farmer/farm/setup" class="btn btn-primary btn-sm mt-2" >Set Up Farm</a>
              </div>
        </#if>
      </div>
      <a href="/farmer/products" class="btn btn-primary" style="margin: 5px;">Go to Product Management</a>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Latest Customer Reviews <span style="font-style: italic; float: right;">View customer feedback and manage your replies.</span></h5>
        <#if currentFarmer.farm??>
          <#if recentReviews?? && recentReviews?size gt 0>
            <div class="list-group">
              <#list recentReviews as review>
                <div class="list-group-item">
                  <div class="d-flex w-100 justify-content-between">
                    <h6 class="mb-1">
                      <#assign stars = ((review.freshnessRating + review.deliveryRating + review.valueRating) / 3)?round>
                      <span class="badge text-bg-info">${stars}/5 Stars</span>
                    </h6>
                    <small>${review.createdAt?datetime.iso}</small>
                  </div>
                  <p class="mb-1">${review.comment}</p>
                  <#if review.replyText??>
                    <div class="alert alert-light border-start border-4 border-success mb-0 mt-2">
                      <small class="text-muted"><strong>Your Reply:</strong></small>
                      <p class="mb-0">${review.replyText}</p>
                    </div>
                  </#if>
                </div>
              </#list>
            </div>
          <#else>
            <p class="text-muted">No reviews yet. Your reviews will appear here as customers leave feedback.</p>
          </#if>
          <a href="/farmer/reviews" class="btn btn-primary mt-3" style="margin: 5px;">Manage All Reviews</a>
        </#if>
      </div>
    </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>

</body>

</html>