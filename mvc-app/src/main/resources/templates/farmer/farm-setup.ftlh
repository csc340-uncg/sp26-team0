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

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title mb-0">Set Up Your Farm</h4>
                    </div>
                    <div class="card-body">
                        <form action="/farmer/farm/setup" method="POST">
                            <div class="mb-3">
                                <label for="farmName" class="form-label">Farm Name *</label>
                                <input type="text" class="form-control" id="name" name="name" required>
                                <div class="form-text">Enter the name of your farm as you want customers to see it.</div>
                            </div>

                            <div class="mb-3">
                                <label for="location" class="form-label">Location *</label>
                                <input type="text" class="form-control" id="location" name="location" required>
                                <div class="form-text">Enter your farm's location (city and state).</div>
                            </div>

                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <textarea class="form-control" id="description" name="description" rows="4"></textarea>
                                <div class="form-text">Describe your farm, what you grow, and what makes it special.</div>
                            </div>

                            <div class="d-flex justify-content-between">
                                <a href="/farmer/dashboard" class="btn btn-secondary">Cancel</a>
                                <button type="submit" class="btn btn-success">Create Farm</button>
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