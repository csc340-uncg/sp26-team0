<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Customer Dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="customer" />

  <div class="container mt-5">
    <h1>Welcome, ${currentCustomer.name}!</h1>
    <p>Manage your subscriptions, browse fresh produce, and support local farms.</p>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Profile Summary <span style="font-style: italic; float: right;">View and edit your information.</span></h5>
        <p class="card-text">Name: ${currentCustomer.name}</p>
        <p class="card-text">Email: ${currentCustomer.email}</p>
        <a href="/customer/profile" class="btn btn-primary">Edit Profile</a>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Subscription Overview <span style="font-style: italic; float: right;">Manage your active subscriptions.</span></h5>
        <div class="row mb-3">
          <div class="col-md-6">
            <div class="card bg-light">
              <div class="card-body">
                <h6 class="card-title">Active Subscriptions</h6>
                <h2 class="text-success">${activeSubscriptions?size}</h2>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="card bg-light">
              <div class="card-body">
                <h6 class="card-title">Total Subscriptions</h6>
                <h2 class="text-info">${totalSubscriptions}</h2>
              </div>
            </div>
          </div>
        </div>

        <#if activeSubscriptions?? && activeSubscriptions?size gt 0>
          <div class="list-group">
            <#list activeSubscriptions as subscription>
              <div class="list-group-item">
                <div class="d-flex w-100 justify-content-between align-items-start">
                  <div>
                    <h6 class="mb-1">${subscription.produceBox.title}</h6>
                    <p class="mb-1 text-muted"><small>from <strong>${subscription.produceBox.farm.name}</strong></small></p>
                    <small>
                      <span class="badge bg-primary me-2">${subscription.cadence}</span>
                      <span class="badge bg-success">Started: ${subscription.startDate?date.iso?string("MMM d, y")}</span>
                    </small>
                  </div>
                  <span class="badge bg-info text-dark">$${subscription.produceBox.price}</span>
                </div>
              </div>
            </#list>
          </div>
        <#else>
          <p class="text-muted">No active subscriptions. <a href="/customer/products">Browse products</a> to get started!</p>
        </#if>

        <a href="/customer/subscriptions" class="btn btn-primary mt-3">Manage Subscriptions</a>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Your Recent Reviews <span style="font-style: italic; float: right;">Reviews you've left for farms.</span></h5>
        <#if recentReviews?? && recentReviews?size gt 0>
          <div class="list-group">
            <#list recentReviews as review>
              <div class="list-group-item">
                <div class="d-flex w-100 justify-content-between">
                  <div>
                    <h6 class="mb-1">
                      <#assign avgRating = ((review.freshnessRating + review.deliveryRating + review.valueRating) / 3)?round>
                      <span class="badge bg-warning text-dark">${avgRating}/5 Stars</span>
                    </h6>
                    <p class="mb-1">${review.comment}</p>
                    <small class="text-muted">${review.createdAt?datetime.iso}</small>
                  </div>
                </div>
              </div>
            </#list>
          </div>
        <#else>
          <p class="text-muted">No reviews yet. Leave a review after receiving your first box!</p>
        </#if>
        <a href="/customer/reviews" class="btn btn-primary mt-3">View All Reviews</a>
      </div>
    </div>

    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Quick Actions</h5>
        <a href="/customer/products" class="btn btn-success me-2">Browse Products</a>
        <a href="/customer/subscriptions" class="btn btn-info">My Subscriptions</a>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
