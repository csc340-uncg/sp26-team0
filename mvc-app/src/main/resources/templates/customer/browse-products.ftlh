<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Browse Products</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="customer" />

  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/customer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active" aria-current="page">Browse Products</li>
      </ol>
    </nav>

    <h2 class="mb-4">Available Produce Boxes</h2>

    <#if produceBoxes?? && produceBoxes?size gt 0>
      <div class="row">
        <#list produceBoxes as box>
          <div class="col-md-6 col-lg-4 mb-4">
            <div class="card h-100 shadow-sm">
              <div class="card-body">
                <h5 class="card-title">${box.title}</h5>
                <p class="card-text text-muted"><small><strong>${box.farm.name}</strong></small></p>
                <p class="card-text">${box.description}</p>
                <div class="row mb-3">
                  <div class="col-6">
                    <small class="text-muted">Price:</small>
                    <h5>$${box.price}</h5>
                  </div>
                  <div class="col-6">
                    <small class="text-muted">Season:</small>
                    <p>${box.season}</p>
                  </div>
                </div>
              </div>
              <div class="card-footer bg-light">
                <a href="/customer/products/${box.boxId}" class="btn btn-primary btn-sm w-100">View Details</a>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info">
        <h5>No Products Available</h5>
        <p>There are currently no produce boxes available. Please check back soon!</p>
      </div>
    </#if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
