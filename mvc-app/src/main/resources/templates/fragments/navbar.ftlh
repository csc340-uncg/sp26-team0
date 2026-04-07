<#macro renderNavbar userType="customer">
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
          <#if userType == "farmer">
            <li class="nav-item">
              <a class="nav-link" href="/farmer/dashboard">Dashboard</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/farmer/products">Products</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/farmer/reviews">Reviews</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/farmer/logout">Logout</a>
            </li>
          <#elseif userType == "customer">
            <li class="nav-item">
              <a class="nav-link" href="/customer/dashboard">Dashboard</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/customer/products">Shop</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/customer/subscriptions">Subscriptions</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/customer/reviews">My Reviews</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/customer/logout">Logout</a>
            </li>
          </#if>
        </ul>
      </div>
    </div>
  </nav>
</#macro>
