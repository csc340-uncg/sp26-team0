# LocalHarvest Hub - MVC Application

A Spring MVC web application connecting local farmers with customers for sustainable, farm-fresh produce delivery.

## Architecture Overview

This application follows the **Model-View-Controller (MVC)** pattern:

### Models (Entities)
Located in `src/main/java/com/csc340/local_harvest_hub/entity/`

- **User** - Base entity for authentication (abstract parent class)
- **Farmer** - Extends User; manages farms and produce boxes
- **Customer** - Extends User; manages subscriptions and reviews
- **Farm** - Represents a farmer's farm with location and details
- **ProduceBox** - Products offered by farmers (seasonal produce)
- **Subscription** - Customer subscriptions to produce boxes
- **Review** - Customer feedback on boxes with farmer replies
- **AuditLog** - System audit trail for tracking changes
- **SysAdmin** - Administrative user for system management
- **FarmStatistics** - Aggregated metrics for farm performance

### Views (Templates)
Located in `src/main/resources/templates/`

**Shared Components:**
- `fragments/navbar.ftlh` - Reusable navigation partial (macro-based for farmer/customer)

**Customer Views:**
- `customer/dashboard.ftlh` - Overview with subscriptions and reviews
- `customer/browse-products.ftlh` - Product grid with price/season info
- `customer/product-detail.ftlh` - Detailed product view with subscription form
- `customer/subscriptions-management.ftlh` - Manage active subscriptions
- `customer/my-reviews.ftlh` - Customer's submitted reviews with farmer replies
- `customer/review-form.ftlh` - 5-star review submission form
- `customer/profile-settings.ftlh` - Update customer profile

**Farmer Views:**
- `farmer/dashboard.ftlh` - Farm stats, latest reviews, subscription overview
- `farmer/product-management.ftlh` - Manage produce boxes with edit/delete modals
- `farmer/farm-settings.ftlh` - Update farm information
- `farmer/profile-settings.ftlh` - Update farmer profile
- `farmer/review-management.ftlh` - View all farm reviews and add replies
- `farmer/edit-box.ftlh` - Edit produce box details
- `farmer/new-box.ftlh` - Create new produce box
- `farmer/farm-setup.ftlh` - Initial farm setup

**Public Pages:**
- `home.ftlh` - Landing page with role-based CTAs
- `signin.ftlh` - Authentication page
- `signup.ftlh` - User registration page

### Controllers

**API Controllers** - RESTful endpoints for data operations:
- `AuditLogController` - Audit log management
- `CustomerController` - Customer CRUD operations
- `FarmerController` - Farmer profile operations
- `FarmController` - Farm management
- `ProduceBoxController` - Product CRUD operations
- `ReviewController` - Review management with replies
- `SubscriptionController` - Subscription lifecycle
- `SysAdminController` - System administration

**UI Controllers** - Page rendering and navigation:
- `AppUiController` - Public pages (home, auth)
- `FarmerUiController` - Farmer dashboard and all farmer views
- `CustomerUiController` - Customer dashboard and all customer views

### Services
Located in `src/main/java/com/csc340/local_harvest_hub/service/`

Business logic layer providing CRUD operations and domain-specific functionality:
- `CustomerService` - Customer registration, profile updates, account management
- `FarmerService` - Farmer registration, profile, farm ownership validation
- `FarmService` - Farm CRUD and statistics
- `ProduceBoxService` - Product inventory management
- `SubscriptionService` - Subscription lifecycle (create, update status, end dates)
- `ReviewService` - Review submission and retrieval with farm associations
- `AuditLogService` - System activity tracking
- `SysAdminService` - Administrative operations

### Repositories
Located in `src/main/java/com/csc340/local_harvest_hub/repository/`

Data access layer interfacing with the database (Spring Data JPA):
- `CustomerRepository` - Customer lookups and queries
- `FarmerRepository` - Farmer management
- `FarmRepository` - Farm data access
- `ProduceBoxRepository` - Product queries (by farm, season, etc.)
- `SubscriptionRepository` - Subscription queries (by customer, farm, status)
- `ReviewRepository` - Review queries with filtering
- `AuditLogRepository` - Audit trail access
- `SysAdminRepository` - Admin account management

## Key Features

### User Roles & Authentication
- **Customer**: Browse products, create subscriptions, leave reviews
- **Farmer**: Create/manage produce boxes, view farm reviews with customer replies, track statistics
- **Admin**: System monitoring and audit trails

### Customer Flow
1. Sign up and create customer profile
2. Browse available produce boxes from local farms
3. Create recurring subscriptions with custom dates
4. Manage subscriptions (update status, end dates)
5. Leave reviews with 5-star ratings (freshness, delivery, value)
6. View farmer responses to reviews

### Farmer Flow
1. Sign up and complete farm setup
2. Create and manage produce boxes (title, price, season, description)
3. View all subscriptions to their products
4. Monitor farm statistics and metrics
5. View customer reviews for their farm
6. Reply to customer reviews in real-time

### Navigation
All pages use a unified FreeMarker macro-based navbar that automatically adjusts based on:
- User role (farmer/customer)
- Authentication status
- Responsive design (Bootstrap 5.3.2)

## Session Management
- Uses `HttpSession` for storing `customerId` and `farmerId`
- Automatic redirect to signin for unauthenticated access to protected pages
- Session validation on all sensitive endpoints

## Database Relationships
- **One-to-Many**: Farmer → Farms, Farm → ProduceBoxes, Farmer → Reviews
- **Many-to-One**: Subscription → Customer/ProduceBox, Review → Subscription
- **Cascade Operations**: Automatic cascading for related entity changes
- **JsonIgnoreProperties**: Prevents circular reference serialization