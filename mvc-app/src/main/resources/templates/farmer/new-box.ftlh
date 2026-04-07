<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="farmer" />

  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/farmer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active" aria-current="page">New Produce Box</li>
      </ol>
    </nav>

    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card">
          <div class="card-header">
            <h4 class="mb-0">Create New Produce Box</h4>
          </div>
          <div class="card-body">
            <form action="/farmer/boxes/new" method="post">
              <div class="mb-3">
                <label for="name" class="form-label">Box Title</label>
                <input type="text" class="form-control" id="title" name="title" required maxlength="100"
                  placeholder="e.g., Summer Vegetable Box">
              </div>
              <div class="mb-3">
                <label for="name" class="form-label">Capacity</label>
                <input type="text" class="form-control" id="capacity" name="capacity" required maxlength="100"
                  placeholder="How many boxes are available for this season?">
              </div>

              <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="4"
                  placeholder="Describe what's included in the box..."></textarea>
              </div>
              <div class="mb-3">
                <label for="description" class="form-label">Produce</label>
                <textarea class="form-control" id="produce" name="produce" rows="3"
                  placeholder="Comma separated list of produce items"></textarea>
              </div>

              <div class="mb-3">
                <label for="price" class="form-label">Price ($)</label>
                <input type="number" class="form-control" id="price" name="price" required min="0.01" step="0.01"
                  placeholder="29.99">
              </div>

              <div class="mb-3">
                <select class="custom-select" id="season" name="season" required>
                  <option selected>Season</option>
                  <option value="SUMMER">SUMMER</option>
                  <option value="WINTER">WINTER</option>
                  <option value="FALL">FALL</option>
                  <option value="SPRING">SPRING</option>
                </select>
              </div>

              <div class="mb-3">
                <select class="custom-select" id="status" name="status" required>
                  <option selected>Status</option>
                  <option value="ACTIVE">ACTIVE</option>
                  <option value="INACTIVE">INACTIVE</option>
                </select>
              </div>

              <div class="d-grid gap-2">
                <button type="submit" class="btn btn-success">Create Box</button>
                <a href="/farmers/dashboard" class="btn btn-outline-secondary">Cancel</a>
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