# LocalHarvest Hub - Backend API Documentation

**Version:** 1.0
**Last Updated:** March 4, 2026
**Base URL:** `http://localhost:8080/api`

---

## Table of Contents

1. [Overview](#1-overview)
2. [User Roles](#2-user-roles)
3. [UML Class Diagram](#3-uml-class-diagram)
4. [API Endpoints](#4-api-endpoints)
   - [Customer Management](#customer-management)
   - [Farmer/Provider Management](#farmerprovider-management)
   - [Farm Management](#farm-management)
   - [Produce Box Management](#produce-box-management)
   - [Subscription Management](#subscription-management)
   - [Review Management](#review-management)
   - [System Admin Management](#system-admin-management)
   - [Audit Logs](#audit-logs)
5. [Use Case Mapping](#5-use-case-mapping)

---
## 1. Overview
The LocalHarvest Hub Backend API provides a RESTful interface for managing: 

- **User Accounts**: Customer, Farmer, and SysAdmin roles
- **Farm Profiles**: Information about farms and their operations
- **Produce Boxes**: Seasonal produce offerings with pricing and capacity management
- **Subscriptions**: Customer subscriptions to produce boxes with various cadences
- **Reviews**: Customer feedback on freshness and delivery experiences
- **Audit Logs**: Administrative tracking of system actions

---
## 2. User Roles
The API supports three primary user roles:

| Role | Description | Primary Responsibilities |
|------|-------------|-------------------------|
| **CUSTOMER** | Consumer of produce boxes | Browse farms/boxes, subscribe, write reviews |
| **FARMER** | Provider of produce boxes | Create/manage boxes, view metrics, reply to reviews |
| **SYS_ADMIN** | Platform administrator | Manage access, moderate content, view analytics |

---
## 3. UML Class Diagram
![UML Class Diagram](../docs/uml-class.png)

## 4. API Endpoints
**Note:** Users are created through role-specific endpoints (`/customers`, `/farmers`, `/sysadmins`), not through a generic `/users` endpoint. This ensures proper role assignment and role-specific attributes.

### Customer Management
#### Create Customer
**Endpoint:** `POST /customers`
**Use Case:** US-CUST-001 (Register as Customer)
**Description:** Create a new customer account with profile information.

```http
POST /customers
Content-Type: application/json

{
  "email": "jane@example.com",
  "passwordHash": "hashed_password",
  "status": "ACTIVE",
  "role": "CUSTOMER",
  "name": "Jane Doe"
}
```

**Response:**
```json
{
  "userId": 1,
  "email": "jane@example.com",
  "status": "ACTIVE",
  "role": "CUSTOMER",
  "name": "Jane Doe",
  "subscriptions": [],
  "createdAt": "2026-01-15T10:30:00",
  "updatedAt": "2026-01-15T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Get All Customers
**Endpoint:** `GET /customers`
**Use Case:** Admin user management
**Description:** Retrieve all customer accounts.

```http
GET /customers
```

**Status Code:** `200 OK`

---

#### Get Customer by ID
**Endpoint:** `GET /customers/{id}`
**Use Case:** Customer profile view
**Description:** Retrieve specific customer by ID.

```http
GET /customers/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get Customer by Email
**Endpoint:** `GET /customers/email/{email}`
**Use Case:** Customer lookup
**Description:** Retrieve customer by email address.

```http
GET /customers/email/jane@example.com HTTP/1.1
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Update Customer
**Endpoint:** `PUT /customers/{id}`
**Use Case:** US-CUST-001 (Update Profile)
**Description:** Update customer profile information.

```http
PUT /customers/1
Content-Type: application/json

{
  "name": "Jane Smith",
  "status": "ACTIVE"
}
```

**Response:** Updated customer object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Customer
**Endpoint:** `DELETE /customers/{id}`
**Use Case:** Account deletion
**Description:** Delete customer account.

```http
DELETE /customers/1
```

**Status Code:** `204 No Content` or `404 Not Found`

---
### Farmer/Provider Management

#### Create Farmer
**Endpoint:** `POST /farmers`
**Use Case:** US-FARM-001 (Register as Farmer)
**Description:** Create a new farmer account.

```http
POST /farmers
Content-Type: application/json

{
  "email": "john@example.com",
  "passwordHash": "hashed_password",
  "status": "ACTIVE",
  "role": "FARMER",
  "name": "John Smith"
}
```

**Response:**
```json
{
  "userId": 2,
  "email": "john@example.com",
  "status": "ACTIVE",
  "role": "FARMER",
  "name": "John Smith",
  "farm": null,
  "createdAt": "2026-01-15T10:30:00",
  "updatedAt": "2026-01-15T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Get All Farmers
**Endpoint:** `GET /farmers`
**Use Case:** Browse providers
**Description:** Retrieve all farmer accounts.

```http
GET /farmers
```

**Status Code:** `200 OK`

---

#### Get Farmer by ID
**Endpoint:** `GET /farmers/{id}`
**Use Case:** Farmer profile view
**Description:** Retrieve specific farmer by ID.

```http
GET /farmers/2
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get Farmer by Email
**Endpoint:** `GET /farmers/email/{email}`
**Use Case:** Farmer lookup
**Description:** Retrieve farmer by email address.

```http
GET /farmers/email/john@example.com HTTP/1.1
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Update Farmer
**Endpoint:** `PUT /farmers/{id}`
**Use Case:** US-FARM-001 (Update Profile)
**Description:** Update farmer profile information.

```http
PUT /farmers/2
Content-Type: application/json

{
  "bio": "Third Generation Farmer specializing in organic vegetables. Passionate about sustainable agriculture and community engagement.",
  "status": "ACTIVE"
}
```

**Response:** Updated farmer object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Farmer
**Endpoint:** `DELETE /farmers/{id}`
**Use Case:** Account deletion
**Description:** Delete farmer account.

```http
DELETE /farmers/2
```

**Status Code:** `204 No Content` or `404 Not Found`

---
### Farm Management

#### Create Farm
**Endpoint:** `POST /farms`
**Use Case:** US-FARM-001 (Create Farm Profile)
**Description:** Create a new farm profile.

```http
POST /farms
Content-Type: application/json

{
  "farmer": {
    "userId": 2
  },
  "name": "Green Valley Farm",
  "location": "Chapel Hill, NC 27514",
  "description": "Organic family farm specializing in seasonal produce with sustainable practices."
}
```

**Response:**
```json
{
  "farmId": 5,
  "farmer": {
    "userId": 2,
    "email": "john@example.com"
  },
  "name": "Green Valley Farm",
  "location": "Chapel Hill, NC 27514",
  "description": "Organic family farm specializing in seasonal produce with sustainable practices.",
  "produceBoxes": [],
  "createdAt": "2026-01-15T10:30:00",
  "updatedAt": "2026-01-15T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Get All Farms
**Endpoint:** `GET /farms`
**Use Case:** US-CUST-002 (Browse Farm Profiles)
**Description:** Retrieve all farms.

```http
GET /farms
```

**Response:**
```json
[
  {
    "farmId": 5,
    "farmer": {...},
    "name": "Green Valley Farm",
    "location": "Chapel Hill, NC 27514",
    "description": "...",
    "produceBoxes": [...]
  }
]
```

**Status Code:** `200 OK`

---

#### Get Farm by ID
**Endpoint:** `GET /farms/{id}`
**Use Case:** US-CUST-002 (View Farm Details)
**Description:** Retrieve specific farm profile.

```http
GET /farms/5
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Update Farm
**Endpoint:** `PUT /farms/{id}`
**Use Case:** US-FARM-001 (Update Farm Profile)
**Description:** Update farm profile information.

```http
PUT /farms/5
Content-Type: application/json

{
  "description": "Updated farm description...",
  "location": "Chapel Hill, NC 27516"
}
```

**Response:** Updated farm object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Farm
**Endpoint:** `DELETE /farms/{id}`
**Use Case:** Farm removal
**Description:** Delete farm profile.

```http
DELETE /farms/5
```

**Status Code:** `204 No Content` or `404 Not Found`

---

### Produce Box Management

#### Create Produce Box
**Endpoint:** `POST /boxes`
**Use Case:** US-FARM-002 (Create Service - Produce Box)
**Description:** Create a new produce box offering.

```http
POST /boxes
Content-Type: application/json

{
  "farm": {
    "farmId": 5
  },
  "title": "Spring Veggie Box",
  "description": "Fresh spring vegetables including lettuce, spinach, peas...",
  "season": "SPRING",
  "produce": "Lettuce, Spinach, Peas, Carrots, Radishes",
  "price": 35.99,
  "capacity": 50,
  "status": "PUBLISHED",
  "pickupDeliveryNotes": "Thursday pickup or Friday delivery"
}
```

**Response:**
```json
{
  "boxId": 10,
  "farm": {
    "farmId": 5,
    "name": "Green Valley Farm"
  },
  "title": "Spring Veggie Box",
  "description": "Fresh spring vegetables including lettuce, spinach, peas...",
  "season": "SPRING",
  "produce": "Lettuce, Spinach, Peas, Carrots, Radishes",
  "price": 35.99,
  "capacity": 50,
  "status": "PUBLISHED",
  "cadence": "WEEKLY",
  "pickupDeliveryNotes": "Thursday pickup or Friday delivery",
  "createdAt": "2026-01-15T10:30:00",
  "updatedAt": "2026-01-15T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Get All Produce Boxes
**Endpoint:** `GET /boxes`
**Use Case:** US-CUST-003 (Discover Produce Boxes)
**Description:** Retrieve all available produce boxes.

```http
GET /boxes
```

**Query Parameters:**
- `season` (Optional): Filter by season (SPRING, SUMMER, FALL, WINTER)
- `price` (Optional): Filter by maximum price
- `status` (Optional): Filter by status (PUBLISHED, SUSPENDED, PENDING)

**Example with filters:**
```http
GET /boxes?season=SPRING&price=40
```

**Status Code:** `200 OK`

---

#### Get Produce Box by ID
**Endpoint:** `GET /boxes/{id}`
**Use Case:** US-CUST-004 (View Produce Box Details)
**Description:** Retrieve specific produce box with full details.

```http
GET /boxes/10
```

**Response:** See Create Produce Box endpoint

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get Produce Boxes by Farm ID
**Endpoint:** `GET /boxes/farm/{farmId}`
**Use Case:** US-CUST-003 (Browse Boxes by Farm)
**Description:** Retrieve all produce boxes from a specific farm.

```http
GET /boxes/farm/5
```

**Response:** Array of produce boxes

**Status Code:** `200 OK`

---

#### Get Produce Boxes by Status
**Endpoint:** `GET /boxes/status/{status}`
**Use Case:** US-FARM-002 (View Box Listings)
**Description:** Retrieve boxes filtered by status.

```http
GET /boxes/status/PUBLISHED
```

**Response:** Array of produce boxes with matching status

**Status Code:** `200 OK`

---

#### Update Produce Box
**Endpoint:** `PUT /boxes/{id}`
**Use Case:** US-FARM-003 (Update Harvest Schedule), US-FARM-004 (Edit/Suspend Box)
**Description:** Update produce box information, including capacity and status.

```http
PUT /boxes/10
Content-Type: application/json

{
  "capacity": 40,
  "status": "SUSPENDED",
  "updatedAt": "2026-01-15T10:30:00"
}
```

**Response:** Updated produce box object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Produce Box
**Endpoint:** `DELETE /boxes/{id}`
**Use Case:** Box removal
**Description:** Delete a produce box listing.

```http
DELETE /boxes/10 HTTP/1.1
```

**Status Code:** `204 No Content` or `404 Not Found`

---
### Subscription Management

#### Create Subscription
**Endpoint:** `POST /subscriptions`
**Use Case:** US-CUST-005 (Subscribe to Produce Box)
**Description:** Create a new subscription to a produce box.

```http
POST /subscriptions
Content-Type: application/json

{
  "customer": {
    "userId": 1
  },
  "produceBox": {
    "boxId": 10
  },
  "cadence": "WEEKLY",
  "startDate": "2026-01-20",
  "status": "ACTIVE"
}
```

**Response:**
```json
{
  "subscriptionId": 101,
  "customer": {
    "userId": 1,
    "email": "jane@example.com"
  },
  "produceBox": {
    "boxId": 10,
    "title": "Spring Veggie Box"
  },
  "cadence": "WEEKLY",
  "startDate": "2026-01-20",
  "endDate": null,
  "status": "ACTIVE",
  "createdAt": "2026-01-15T10:30:00",
  "updatedAt": "2026-01-15T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Get All Subscriptions
**Endpoint:** `GET /subscriptions`
**Use Case:** Admin subscription management
**Description:** Retrieve all subscriptions.

```http
GET /subscriptions
```

**Status Code:** `200 OK`

---

#### Get Subscription by ID
**Endpoint:** `GET /subscriptions/{id}`
**Use Case:** Subscription detail view
**Description:** Retrieve specific subscription.

```http
GET /subscriptions/101
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get Subscriptions by Customer ID
**Endpoint:** `GET /subscriptions/customer/{customerId}`
**Use Case:** US-CUST-001 (Manage Customer Subscriptions)
**Description:** Retrieve all subscriptions for a specific customer.

```http
GET /subscriptions/customer/1
```

**Response:** Array of customer subscriptions

**Status Code:** `200 OK`

---

#### Get Subscriptions by Produce Box ID
**Endpoint:** `GET /subscriptions/box/{boxId}`
**Use Case:** US-FARM-006 (View Customer Engagement Metrics)
**Description:** Retrieve all subscriptions to a specific produce box.

```http
GET /subscriptions/box/10
```

**Response:** Array of subscriptions to the box

**Status Code:** `200 OK`

---

#### Get Subscriptions by Status
**Endpoint:** `GET /subscriptions/status/{status}`
**Use Case:** Subscription status filtering
**Description:** Retrieve subscriptions filtered by status.

```http
GET /subscriptions/status/ACTIVE
```

**Response:** Array of subscriptions with matching status

**Status Code:** `200 OK`

---

#### Update Subscription
**Endpoint:** `PUT /subscriptions/{id}`
**Use Case:** US-CUST-006 (Manage Subscription - Pause/Skip/Change/Cancel)
**Description:** Update subscription cadence, status, or end date.

```http
PUT /subscriptions/101
Content-Type: application/json

{
  "cadence": "BIWEEKLY",
  "status": "PAUSED",
  "endDate": null
}
```

**Common Status Updates:**

**Pause Subscription:**
```json
{
  "status": "PAUSED"
}
```

**Cancel Subscription:**
```json
{
  "status": "CANCELLED",
  "endDate": "2026-02-15"
}
```

**Change Cadence:**
```json
{
  "cadence": "MONTHLY"
}
```

**Response:** Updated subscription object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Subscription
**Endpoint:** `DELETE /subscriptions/{id}`
**Use Case:** Subscription removal
**Description:** Delete a subscription (hard delete).

```http
DELETE /subscriptions/101
```

**Status Code:** `204 No Content` or `404 Not Found`

---
### Review Management

#### Create Review
**Endpoint:** `POST /reviews`
**Use Case:** US-CUST-007 (Write a Review)
**Description:** Create a new review for a completed subscription.

```http
POST /reviews
Content-Type: application/json

{
  "subscription": {
    "subscriptionId": 101
  },
  "freshnessRating": 5,
  "deliveryRating": 4,
  "valueRating": 5,
  "comment": "Great quality produce and reliable delivery! The vegetables were fresh and the timing was perfect."
}
```

**Response:**
```json
{
  "reviewId": 201,
  "subscription": {
    "subscriptionId": 101
  },
  "freshnessRating": 5,
  "deliveryRating": 4,
  "valueRating": 5,
  "comment": "Great quality produce and reliable delivery! The vegetables were fresh and the timing was perfect.",
  "replyText": null,
  "createdAt": "2026-01-25T10:30:00",
  "updatedAt": "2026-01-25T10:30:00"
}
```

**Validation Rules:**
- `freshnessRating`, `deliveryRating`, `valueRating`: Must be between 1 and 5
- Review can only be created within 14-30 days of subscription completion
- Each subscription can have only one review

**Status Code:** `201 Created`

---

#### Get All Reviews
**Endpoint:** `GET /reviews`
**Use Case:** US-CUST-008 (Read Reviews)
**Description:** Retrieve all reviews in the system.

```http
GET /reviews
```

**Status Code:** `200 OK`

---

#### Get Review by ID
**Endpoint:** `GET /reviews/{id}`
**Use Case:** Review detail view
**Description:** Retrieve specific review.

```http
GET /reviews/201
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get Reviews by Subscription ID
**Endpoint:** `GET /reviews/subscription/{subscriptionId}`
**Use Case:** US-CUST-008 (Read Reviews for Subscription)
**Description:** Retrieve all reviews for a specific subscription.

```http
GET /reviews/subscription/101
```

**Response:** Array of reviews for the subscription

**Status Code:** `200 OK`

---

#### Update Review
**Endpoint:** `PUT /reviews/{id}`
**Use Case:** US-FARM-007 (Reply to Reviews)
**Description:** Update review (farmer reply or re-scoring).

```http
PUT /reviews/201
Content-Type: application/json

{
  "freshnessRating": 5,
  "deliveryRating": 4,
  "valueRating": 5,
  "comment": "Updated comment...",
  "replyText": "Thank you for the wonderful review! We're glad you enjoyed the fresh vegetables."
}
```

**Response:** Updated review object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Review
**Endpoint:** `DELETE /reviews/{id}`
**Use Case:** US-ADMIN-003 (Moderate Reviews - Remove)
**Description:** Delete a review (admin moderation).

```http
DELETE /reviews/201
```

**Status Code:** `204 No Content` or `404 Not Found`

---
### System Admin Management

#### Create SysAdmin
**Endpoint:** `POST /sysadmins`
**Use Case:** Admin account creation
**Description:** Create a new system administrator account.

```http
POST /sysadmins
Content-Type: application/json

{
  "email": "admin@example.com",
  "passwordHash": "hashed_password",
  "status": "ACTIVE",
  "role": "SYS_ADMIN",
  "name": "Alice Administrator"
}
```

**Response:**
```json
{
  "userId": 3,
  "email": "admin@example.com",
  "status": "ACTIVE",
  "role": "SYS_ADMIN",
  "name": "Alice Administrator",
  "createdAt": "2026-01-15T10:30:00",
  "updatedAt": "2026-01-15T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Get All SysAdmins
**Endpoint:** `GET /sysadmins`
**Use Case:** Admin management
**Description:** Retrieve all system administrators.

```http
GET /sysadmins
```

**Status Code:** `200 OK`

---

#### Get SysAdmin by ID
**Endpoint:** `GET /sysadmins/{id}`
**Use Case:** Admin profile view
**Description:** Retrieve specific administrator.

```http
GET /sysadmins/3
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get SysAdmin by Email
**Endpoint:** `GET /sysadmins/email/{email}`
**Use Case:** Admin lookup
**Description:** Retrieve administrator by email.

```http
GET /sysadmins/email/admin@example.com HTTP/1.1
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Update SysAdmin
**Endpoint:** `PUT /sysadmins/{id}`
**Use Case:** Admin profile management
**Description:** Update administrator information.

```http
PUT /sysadmins/3
Content-Type: application/json

{
  "status": "ACTIVE",
  "name": "Alice A. Administrator"
}
```

**Response:** Updated SysAdmin object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete SysAdmin
**Endpoint:** `DELETE /sysadmins/{id}`
**Use Case:** Admin removal
**Description:** Delete administrator account.

```http
DELETE /sysadmins/3
```

**Status Code:** `204 No Content` or `404 Not Found`

---

### Audit Logs

#### Create Audit Log
**Endpoint:** `POST /audit-logs`
**Use Case:** US-ADMIN-001, US-ADMIN-002, US-ADMIN-003 (Log Administrative Actions)
**Description:** Create an audit log entry (typically done automatically by the system).

```http
POST /audit-logs
Content-Type: application/json

{
  "admin": {
    "userId": 3
  },
  "action": "SUSPENDED_USER",
  "entityType": "USER",
  "entityId": 1,
  "reason": "Policy violation",
  "details": "User violated content guidelines. Multiple complaints received."
}
```

**Response:**
```json
{
  "logId": 301,
  "admin": {
    "userId": 3,
    "email": "admin@example.com"
  },
  "action": "SUSPENDED_USER",
  "entityType": "USER",
  "entityId": 1,
  "reason": "Policy violation",
  "details": "User violated content guidelines. Multiple complaints received.",
  "createdAt": "2026-01-25T10:30:00"
}
```

**Common Action Types:**
- `CREATED_USER`: User account created
- `SUSPENDED_USER`: User account suspended
- `BANNED_USER`: User account banned
- `APPROVED_BOX`: Produce box approved
- `REJECTED_BOX`: Produce box rejected
- `SUSPENDED_BOX`: Produce box suspended
- `APPROVED_REVIEW`: Review approved
- `MASKED_REVIEW`: Review masked for PII
- `REMOVED_REVIEW`: Review removed

**Status Code:** `201 Created`

---

#### Get All Audit Logs
**Endpoint:** `GET /audit-logs`
**Use Case:** US-ADMIN-001, US-ADMIN-004 (View Admin Actions and Analytics)
**Description:** Retrieve all audit log entries.

```http
GET /audit-logs
```

**Status Code:** `200 OK`

---

#### Get Audit Log by ID
**Endpoint:** `GET /audit-logs/{id}`
**Use Case:** Log detail view
**Description:** Retrieve specific audit log entry.

```http
GET /audit-logs/301
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get Audit Logs by Admin ID
**Endpoint:** `GET /audit-logs/admin/{adminId}`
**Use Case:** US-ADMIN-004 (Track Specific Admin Actions)
**Description:** Retrieve all audit logs created by a specific administrator.

```http
GET /audit-logs/admin/3
```

**Response:** Array of audit logs by the admin

**Status Code:** `200 OK`

---

#### Get Audit Logs by Entity Type
**Endpoint:** `GET /audit-logs/entity-type/{entityType}`
**Use Case:** US-ADMIN-004 (Track Changes by Entity)
**Description:** Retrieve audit logs filtered by entity type.

```http
GET /audit-logs/entity-type/USER
```

**Response:** Array of audit logs for the entity type

**Status Code:** `200 OK`

---

#### Update Audit Log
**Endpoint:** `PUT /audit-logs/{id}`
**Use Case:** Log modification (minimal usage)
**Description:** Update audit log entry.

```http
PUT /audit-logs/301
Content-Type: application/json

{
  "details": "Updated details about the action..."
}
```

**Response:** Updated audit log object

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Audit Log
**Endpoint:** `DELETE /audit-logs/{id}`
**Use Case:** Log deletion (restricted, typically only for testing)
**Description:** Delete audit log entry.

```http
DELETE /audit-logs/301
```

**Status Code:** `204 No Content` or `404 Not Found`

---
## 5. Use Case Mapping
The API endpoints are designed to support the following SRS use cases:

### Customer Use Cases

| Use Case | Description | Related Endpoints |
|----------|-------------|-------------------|
| **US-CUST-001** | Register & manage customer profile | `POST /customers`, `PUT /customers/{id}` |
| **US-CUST-002** | Browse farm profiles | `GET /farms`, `GET /farms/{id}` |
| **US-CUST-003** | Discover produce boxes (filter & sort) | `GET /boxes`, `GET /boxes/farm/{farmId}`, `GET /boxes/status/{status}` |
| **US-CUST-004** | View produce box details | `GET /boxes/{id}` |
| **US-CUST-005** | Subscribe to produce box | `POST /subscriptions` |
| **US-CUST-006** | Manage subscription (pause/skip/change/cancel) | `PUT /subscriptions/{id}` |
| **US-CUST-007** | Write a review | `POST /reviews` |
| **US-CUST-008** | Read reviews | `GET /reviews`, `GET /reviews/subscription/{subscriptionId}` |

### Provider (Farmer) Use Cases

| Use Case | Description | Related Endpoints |
|----------|-------------|-------------------|
| **US-FARM-001** | Register & manage farm profile | `POST /farmers`, `PUT /farmers/{id}`, `POST /farms`, `PUT /farms/{id}` |
| **US-FARM-002** | Create produce box offering | `POST /boxes` |
| **US-FARM-003** | Update harvest schedule & quantities | `PUT /boxes/{id}` |
| **US-FARM-004** | Edit or suspend box | `PUT /boxes/{id}` |
| **US-FARM-005** | Manage capacity (sold out) | `PUT /boxes/{id}` |
| **US-FARM-006** | View customer engagement metrics | `GET /subscriptions/box/{boxId}` |
| **US-FARM-007** | Reply to customer reviews | `PUT /reviews/{id}` |

### SysAdmin Use Cases

| Use Case | Description | Related Endpoints |
|----------|-------------|-------------------|
| **US-ADMIN-001** | Manage user access (warn/suspend/ban) | `PUT /customers/{id}`, `PUT /farmers/{id}`, `PUT /sysadmins/{id}`, `POST /audit-logs` |
| **US-ADMIN-002** | Moderate product listings | `PUT /boxes/{id}`, `POST /audit-logs` |
| **US-ADMIN-003** | Moderate reviews (approve/mask/remove) | `DELETE /reviews/{id}`, `PUT /reviews/{id}`, `POST /audit-logs` |
| **US-ADMIN-004** | View platform usage & delivery/pickup success | `GET /audit-logs`, `GET /audit-logs/admin/{adminId}`, `GET /audit-logs/entity-type/{entityType}` |

---
