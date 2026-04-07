<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="farmer" /><div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/farmer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active" aria-current="page">Product Management</li>
      </ol>
    </nav>

    <#if RequestParameters.success??>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        Produce box updated successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <#if RequestParameters.deleted??>
      <div class="alert alert-info alert-dismissible fade show" role="alert">
        Produce box deleted successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <div class="row mb-4">
      <div class="col-md-8">
        <h2>Product Management for ${farm.name}</h2>
      </div>
      <div class="col-md-4 text-end">
        <a href="/farmer/boxes/new" class="btn btn-success btn-lg">+ Add New Box</a>
      </div>
    </div>

    <#if produceBoxes?? && produceBoxes?size gt 0>
      <div class="row">
        <#list produceBoxes as box>
          <div class="col-md-6 mb-4">
            <div class="card h-100">
              <div class="card-body">
                <h5 class="card-title">${box.title}</h5>
                <p class="card-text">${box.description}</p>
                <div class="row mb-3">
                  <div class="col-6">
                    <small class="text-muted">Price:</small>
                    <p class="h5">$${box.price}</p>
                  </div>
                  <div class="col-6">
                    <small class="text-muted">Season:</small>
                    <p class="h5">${box.season}</p>
                  </div>
                </div>
              </div>
              <div class="card-footer bg-light">
                <div class="d-flex gap-2">
                  <a href="/farmer/boxes/edit/${box.boxId}" class="btn btn-sm btn-primary flex-grow-1">Edit</a>
                  <button class="btn btn-sm btn-danger flex-grow-1" data-bs-toggle="modal" data-bs-target="#deleteModal${box.boxId}">Delete</button>
                </div>
              </div>
            </div>
          </div>

          <!-- Delete Confirmation Modal -->
          <div class="modal fade" id="deleteModal${box.boxId}" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Confirm Delete</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                  Are you sure you want to delete "${box.title}"? This action cannot be undone.
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                  <a href="/farmer/boxes/delete/${box.boxId}" class="btn btn-danger">Yes, Delete</a>
                </div>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info">
        <h5>No Produce Boxes Yet</h5>
        <p>You haven't created any produce boxes yet. Start by adding your first box!</p>
        <a href="/farmer/boxes/new" class="btn btn-success">Create First Box</a>
      </div>
    </#if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
