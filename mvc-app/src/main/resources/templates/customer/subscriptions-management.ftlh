<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Subscriptions Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="customer" />

  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/customer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active" aria-current="page">Subscriptions Management</li>
      </ol>
    </nav>

    <#if RequestParameters.success??>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        Subscription created successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <#if RequestParameters.updated??>
      <div class="alert alert-info alert-dismissible fade show" role="alert">
        Subscription updated successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <h2 class="mb-4">My Subscriptions ${subscriptions?size}</h2>

    <#if subscriptions?? && subscriptions?size gt 0>
      <div class="row">
        <#list subscriptions as subscription>
          <div class="col-md-6 mb-4">
            <div class="card h-100">
              <div class="card-body">
                <div class="mb-3">
                  <h5 class="card-title">${subscription.produceBox.title}</h5>
                  <p class="text-muted"><small>from <strong>${subscription.produceBox.farm.name}</strong></small></p>
                </div>

                <div class="row mb-3">
                  <div class="col-6">
                    <small class="text-muted">Cadence:</small>
                    <p class="mb-0"><strong>${subscription.cadence}</strong></p>
                  </div>
                  <div class="col-6">
                    <small class="text-muted">Price:</small>
                    <p class="mb-0"><strong>$${subscription.produceBox.price}</strong></p>
                  </div>
                </div>

                <div class="row mb-3">
                  <div class="col-6">
                    <small class="text-muted">Status:</small>
                    <p class="mb-0">
                      <#if subscription.status == "ACTIVE">
                        <span class="badge bg-success">ACTIVE</span>
                      <#elseif subscription.status == "PAUSED">
                        <span class="badge bg-warning">PAUSED</span>
                      <#elseif subscription.status == "COMPLETED">
                        <span class="badge bg-info">COMPLETED</span>
                      <#else>
                        <span class="badge bg-danger">CANCELLED</span>
                      </#if>
                    </p>
                  </div>
                  <div class="col-6">
                    <small class="text-muted">Start Date:</small>
                    <p class="mb-0">${subscription.startDate?date.iso?string("MMM d, y")}</p>
                  </div>
                </div>

                <#if subscription.endDate??>
                  <div class="mb-3">
                    <small class="text-muted">End Date:</small>
                    <p class="mb-0">${subscription.endDate?date.iso?string("MMM d, y")}</p>
                  </div>
                </#if>
              </div>
              <div class="card-footer bg-light d-flex gap-2">
                <button class="btn btn-sm btn-outline-primary flex-grow-1" data-bs-toggle="modal"
                  data-bs-target="#updateModal${subscription.subscriptionId}">Update</button>
                <a href="/customer/subscriptions/${subscription.subscriptionId}/review" class="btn btn-sm btn-outline-success flex-grow-1">Leave Review</a>
              </div>
            </div>
          </div>

          <!-- Update Modal -->
          <div class="modal fade" id="updateModal${subscription.subscriptionId}" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Update Subscription</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="/customer/subscriptions/${subscription.subscriptionId}/update" method="post">
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="status${subscription.subscriptionId}" class="form-label">Status</label>
                      <select class="form-select" id="status${subscription.subscriptionId}" name="status" required>
                        <option value="ACTIVE" <#if subscription.status == "ACTIVE">selected</#if>>Active</option>
                        <option value="PAUSED" <#if subscription.status == "PAUSED">selected</#if>>Paused</option>
                        <option value="CANCELLED" <#if subscription.status == "CANCELLED">selected</#if>>Cancelled</option>
                        <option value="COMPLETED" <#if subscription.status == "COMPLETED">selected</#if>>Completed</option>
                      </select>
                    </div>
                    <div class="mb-3">
                      <label for="endDate${subscription.subscriptionId}" class="form-label">End Date (Optional)</label>
                      <input type="date" class="form-control" id="endDate${subscription.subscriptionId}" name="endDate"
                        <#if subscription.endDate??>value="${subscription.endDate?date.iso?string("MMM d, y")}"</#if>>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info">
        <h5>No Subscriptions Yet</h5>
        <p>You haven't subscribed to any produce boxes yet.</p>
        <a href="/customer/products" class="btn btn-primary">Browse Products</a>
      </div>
    </#if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
