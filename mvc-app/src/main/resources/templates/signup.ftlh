<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-success mb-4">
    <div class="container">
      <a class="navbar-brand" href="/">LocalHarvest Hub</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/signin">Sign In</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header">
            <ul class="nav nav-tabs card-header-tabs">
              <li class="nav-item">
                <a class="nav-link active" id="customer-tab" data-bs-toggle="tab" href="#customer">Customer</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="farmer-tab" data-bs-toggle="tab" href="#farmer">Farmer</a>
              </li>
            </ul>
          </div>
          <div class="card-body">
            <div class="tab-content">
              <div class="tab-pane fade show active" id="customer">
                <h3 class="card-title mb-4">Customer Sign Up</h3>
                <form action="/customer/signup" method="post">
                  <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                  </div>
                  <div class="mb-3">
                    <label for="name" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                  </div>
                  <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                  </div>
                  <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="passwordHash" name="passwordHash" required>
                  </div>
                  <input type="text" id="role" name="role" value="CUSTOMER" hidden>
                  <input type="text" id="status" name="status" value="ACTIVE" hidden>
                  <button type="submit" class="btn btn-success">Sign Up</button>
                </form>
              </div>
              <div class="tab-pane fade" id="farmer">
                <h3 class="card-title mb-4">Farmer Sign Up</h3>
                <form action="/farmer/signup" method="post">
                  <div class="mb-3">
                    <label for="name" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                  </div>
                  <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                  </div>
                  <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="passwordHash" name="passwordHash" required>
                  </div>
                  <div class="mb-3">
                    <label for="password" class="form-label">Bio</label>
                    <input type="text" class="form-control" id="bio" name="bio">
                  </div>
                  <input type="text" id="role" name="role" value="FARMER" hidden>
                  <input type="text" id="status" name="status" value="ACTIVE" hidden>
                  <button type="submit" class="btn btn-success">Sign Up</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>