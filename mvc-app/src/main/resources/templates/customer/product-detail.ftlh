<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Detail</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="customer" />

  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/customer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a href="/customer/products">Browse Products</a></li>
        <li class="breadcrumb-item active" aria-current="page">Product Detail</li>
      </ol>
    </nav>

    <div class="row">
      <div class="col-md-8">
        <div class="card mb-4">
          <div class="card-body">
            <h2>${produceBox.title}</h2>
            <p class="text-muted lead">From <strong>${produceBox.farm.name}</strong></p>

            <div class="row mb-4">
              <div class="col-md-6">
                <h5>Price</h5>
                <h3 class="text-success">${produceBox.price}</h3>
              </div>
              <div class="col-md-6">
                <h5>Season</h5>
                <p class="lead">${produceBox.season}</p>
              </div>
            </div>

            <h5>About This Box</h5>
            <p>${produceBox.description}</p>

            <h5>Farm Information</h5>
            <div class="card bg-light">
              <div class="card-body">
                <p><strong>Location:</strong> ${produceBox.farm.location}</p>
                <p><strong>Description:</strong> ${produceBox.farm.description}</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card sticky-top" style="top: 20px;">
          <div class="card-body">
            <h5 class="card-title">Subscribe Now</h5>
            <form action="/customer/products/${produceBox.boxId}/subscribe" method="post">
              <div class="mb-3">
                <label for="cadence" class="form-label">Cadence</label>
                <select class="form-select" id="cadence" name="cadence" required>
                  <option value="">-- Select Frequency --</option>
                  <option value="weekly">Weekly</option>
                  <option value="biweekly">Bi-weekly</option>
                  <option value="monthly">Monthly</option>
                </select>
              </div>
              <div class="mb-3">
                <label for="startDate" class="form-label">Start Date</label>
                <input type="date" class="form-control" id="startDate" name="startDate" required>
              </div>
              <button type="submit" class="btn btn-success w-100 btn-lg">Subscribe</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
