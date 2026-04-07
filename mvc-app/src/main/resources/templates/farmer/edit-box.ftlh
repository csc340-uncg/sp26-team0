<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Produce Box</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="farmer" />
    </div>
  </nav>

  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/farmer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a href="/farmer/products">Product Management</a></li>
        <li class="breadcrumb-item active" aria-current="page">Edit Produce Box</li>
      </ol>
    </nav>

    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card">
          <div class="card-header">
            <h4 class="mb-0">Edit Produce Box</h4>
          </div>
          <div class="card-body">
            <form action="/farmer/boxes/edit/${produceBox.boxId}" method="post">
              <div class="mb-3">
                <label for="title" class="form-label">Box Title</label>
                <input type="text" class="form-control" id="title" name="title" value="${produceBox.title}" required
                  maxlength="100">
              </div>
              <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="4"
                  required>${produceBox.description}</textarea>
              </div>
              <div class="mb-3">
                <label for="price" class="form-label">Price ($)</label>
                <input type="number" class="form-control" id="price" name="price" value="${produceBox.price}" required
                  step="0.01" min="0">
              </div>
              <div class="mb-3">
                <label for="capacity" class="form-label">Capacity</label>
                <input type="number" class="form-control" id="capacity" name="capacity" value="${produceBox.capacity}"
                  required>
              </div>
              <div class="mb-3">
                <label for="season" class="form-label">Season</label>
                <select class="form-select" id="season" name="season" required>
                  <option value="">-- Select Season --</option>
                  <option value="SPRING" <#if produceBox.season=="SPRING">selected</#if>>Spring</option>
                  <option value="SUMMER" <#if produceBox.season=="SUMMER">selected</#if>>Summer</option>
                  <option value="FALL" <#if produceBox.season=="FALL">selected</#if>>Fall</option>
                  <option value="WINTER" <#if produceBox.season=="WINTER">selected</#if>>Winter</option>
                </select>
              </div>
              <div class="mb-3">
                <select class="custom-select" id="status" name="status" required>
                  <option selected>Status</option>
                  <option value="ACTIVE">ACTIVE</option>
                  <option value="INACTIVE">INACTIVE</option>
                </select>
              </div>
              <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary">Save Changes</button>
                <a href="/farmer/products" class="btn btn-secondary">Cancel</a>
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