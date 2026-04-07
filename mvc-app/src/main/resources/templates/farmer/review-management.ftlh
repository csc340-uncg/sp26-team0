<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Review Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="farmer" />
  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/farmer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active" aria-current="page">Review Management</li>
      </ol>
    </nav>

    <#if RequestParameters.success??>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        Reply added successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <div class="row mb-4">
      <div class="col-md-8">
        <h2>Reviews for ${farm.name}</h2>
      </div>
      <div class="col-md-4 text-end">
        <#assign totalReviews=reviews?size>
          <div class="badge bg-primary p-3">
            <h5 class="mb-0">${totalReviews} Review<#if totalReviews !=1>s</#if>
            </h5>
          </div>
      </div>
    </div>

    <#if reviews?? && reviews?size gt 0>
      <div class="row">
        <#list reviews as review>
          <div class="col-md-12 mb-4">
            <div class="card">
              <div class="card-body">
                <div class="row mb-3">
                  <div class="col-md-8">
                    <div class="mb-2">
                      <#assign avgRating=((review.freshnessRating + review.deliveryRating + review.valueRating) /
                        3)?round>
                        <span class="badge bg-info text-dark p-2 me-2">${avgRating}/5 Stars</span>
                        <small class="text-muted">
                          by <strong>${review.subscription.customer.name}</strong>
                          (${review.subscription.customer.email})
                        </small>
                    </div>
                    <small class="text-muted d-block">
                      <i class="bi bi-calendar"></i> ${review.createdAt?datetime.iso}
                    </small>
                  </div>
                  <div class="col-md-4 text-end">
                    <div class="rating-breakdown">
                      <small class="d-block">
                        <strong>Freshness:</strong>
                        <span class="badge bg-light text-dark">${review.freshnessRating}/5</span>
                      </small>
                      <small class="d-block">
                        <strong>Delivery:</strong>
                        <span class="badge bg-light text-dark">${review.deliveryRating}/5</span>
                      </small>
                      <small class="d-block">
                        <strong>Value:</strong>
                        <span class="badge bg-light text-dark">${review.valueRating}/5</span>
                      </small>
                    </div>
                  </div>
                </div>

                <div class="mb-3">
                  <p class="card-text">${review.comment}</p>
                </div>

                <#if review.replyText??>
                  <div class="alert alert-light border-start border-4 border-success mb-3">
                    <h6 class="alert-heading mb-2">
                      <i class="bi bi-chat-left-text"></i> Your Reply
                    </h6>
                    <p class="mb-0">${review.replyText}</p>
                  </div>
                  <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal"
                    data-bs-target="#replyModal${review.reviewId}">Edit Reply</button>
                  <#else>
                    <button class="btn btn-sm btn-primary" data-bs-toggle="modal"
                      data-bs-target="#replyModal${review.reviewId}">Add Reply</button>
                </#if>
              </div>
            </div>
          </div>

          <!-- Reply Modal -->
          <div class="modal fade" id="replyModal${review.reviewId}" tabindex="-1">
            <div class="modal-dialog modal-lg">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">
                    <#if review.replyText??>Edit<#else>Add</#if> Reply to Review
                  </h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="/farmer/reviews/${review.reviewId}/reply" method="post">
                  <div class="modal-body">
                    <div class="mb-3">
                      <label class="form-label">Customer Review:</label>
                      <p class="bg-light p-3 rounded">${review.comment}</p>
                    </div>
                    <div class="mb-3">
                      <label for="replyText${review.reviewId}" class="form-label">Your Reply</label>
                      <textarea class="form-control" id="replyText${review.reviewId}" name="replyText" rows="4"
                        required>${review.replyText!}</textarea>
                      <small class="text-muted">Be professional and helpful in your response</small>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Save Reply</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </#list>
      </div>
      <#else>
        <div class="alert alert-info">
          <h5>No Reviews Yet</h5>
          <p>Your customers haven't left any reviews yet. Great reviews will help attract more customers to your farm!
          </p>
        </div>
    </#if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>