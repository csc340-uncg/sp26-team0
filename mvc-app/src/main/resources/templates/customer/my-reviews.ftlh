<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Reviews</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="customer" />

  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/customer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active" aria-current="page">My Reviews</li>
      </ol>
    </nav>

    <h2 class="mb-4">My Reviews</h2>

    <#if reviews?? && reviews?size gt 0>
      <div class="row">
        <#list reviews as review>
          <div class="col-md-12 mb-4">
            <div class="card">
              <div class="card-body">
                <div class="row mb-3">
                  <div class="col-md-8">
                    <h5 class="card-title">
                      <#assign avgRating = ((review.freshnessRating + review.deliveryRating + review.valueRating) / 3)?round>
                      <span class="badge bg-warning text-dark">${avgRating}/5 Stars</span>
                    </h5>
                    <p class="text-muted">
                      <small>for <strong>${review.subscription.produceBox.title}</strong> from ${review.subscription.produceBox.farm.name}</small>
                    </p>
                  </div>
                  <div class="col-md-4 text-end">
                    <small class="text-muted">${review.createdAt?datetime.iso}</small>
                  </div>
                </div>

                <div class="row mb-3">
                  <div class="col-md-4">
                    <small><strong>Freshness:</strong></small>
                    <p class="badge bg-light text-dark">${review.freshnessRating}/5</p>
                  </div>
                  <div class="col-md-4">
                    <small><strong>Delivery:</strong></small>
                    <p class="badge bg-light text-dark">${review.deliveryRating}/5</p>
                  </div>
                  <div class="col-md-4">
                    <small><strong>Value:</strong></small>
                    <p class="badge bg-light text-dark">${review.valueRating}/5</p>
                  </div>
                </div>

                <div class="mb-3">
                  <p class="card-text">${review.comment}</p>
                </div>

                <#if review.replyText??>
                  <div class="alert alert-light border-start border-4 border-success">
                    <h6 class="alert-heading">
                      <i class="bi bi-chat-left-text"></i> Farm Reply
                    </h6>
                    <p class="mb-0">${review.replyText}</p>
                  </div>
                </#if>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info">
        <h5>No Reviews Yet</h5>
        <p>You haven't left any reviews yet. Leave a review after receiving your first box!</p>
        <a href="/customer/subscriptions" class="btn btn-primary">View My Subscriptions</a>
      </div>
    </#if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
