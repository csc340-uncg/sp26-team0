<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Farm Settings</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="farmer" />

  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/farmer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active" aria-current="page">Farm Settings</li>
      </ol>
    </nav>

    <#if RequestParameters.success??>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        Farm information updated successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card">
          <div class="card-header">
            <h4 class="mb-0">Farm Settings</h4>
          </div>
          <div class="card-body">
            <form action="/farmer/farm/settings" method="post">
              <div class="mb-3">
                <label for="name" class="form-label">Farm Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${farm.name}" required maxlength="100">
              </div>
              <div class="mb-3">
                <label for="location" class="form-label">Location</label>
                <input type="text" class="form-control" id="location" name="location" value="${farm.location}" required placeholder="City, State or address">
              </div>
              <div class="mb-3">
                <label for="description" class="form-label">Farm Description</label>
                <textarea class="form-control" id="description" name="description" rows="5" placeholder="Describe your farm, practices, and specialties">${farm.description!}</textarea>
              </div>
              <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary">Save Changes</button>
                <a href="/farmer/dashboard" class="btn btn-secondary">Cancel</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
