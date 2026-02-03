
# Requirements – LocalHarvest Hub

**Project Name:** LocalHarvest Hub\
**Team:** Alice Beback; Bob McBobbyface\
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-01-30\
**Purpose:** This SRS consolidates the scope and user‑facing requirements of LocalHarvest Hub and enumerates all user stories that guide development and testing.

---

## 1. Overview
**Vision.** LocalHarvest Hub is a web‑based marketplace that connects eco‑conscious consumers to nearby farms. Customers discover farm profiles, explore and subscribe to seasonal produce boxes (once‑off / weekly / monthly), and share reviews. Farmers manage offerings and harvest schedules and track engagement. Administrators oversee access, listings, reviews, and usage health to keep the marketplace vibrant and trustworthy.

**Glossary**
- **Box / Produce Box:** A curated set of seasonal items offered by a farm, with a cadence (once‑off, weekly, monthly).
- **Capacity:** Maximum number of active subscriptions/units for a box in a time window.
- **Cadence:** Frequency at which a subscription recurs.
- **Engagement:** Aggregate signals such as active subscribers, churn, ratings.

**Primary Users / Roles.**
- **Customer (consumer of produce boxes)** — find local, seasonal produce; manage subscriptions; review experiences.
- **Farmer (provider of produce boxes)** — publish boxes & schedules; manage capacity; respond to reviews; view engagement.
- **SysAdmin (platform administrator)** — manage user access; moderate listings & reviews; monitor usage/delivery health.

**Scope (this semester).**
- Browse farms and seasonal produce boxes with filtering/sorting. 
- Subscribe to produce boxes (once‑off / weekly / monthly); manage subscription (pause/skip/cancel). 
- Farmer onboarding; create/edit/suspend box listings; update harvest schedules & capacity. 
- Reviews (customer write; farmer reply) with basic moderation. 
- Admin access management and moderation workflows. 

**Out of scope (deferred).**
- Online payments and refund processing. 
- Route optimization and live delivery tracking. 
- Multi‑language UI beyond English. 

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US‑CUST‑001 — Register & manage profile**  
  _Story:_ As a customer, I want to create or update my profile (name, location, pickup/delivery preference, dietary notes) so that I receive relevant offerings and communications.  
  _Acceptance:_
  ```gherkin
  Scenario: Register with valid details
    Given I am not registered
    When I sign up with required fields
    Then my customer profile is created and visible in my account
  ```

- **US‑CUST‑002 — Browse farm profiles**  
  _Story:_ As a customer, I want to browse farm profiles (distance, practices, season highlights) so that I can learn about producers before subscribing.  
  _Acceptance:_
  ```gherkin
  Scenario: Sort farms by distance
    Given multiple farms exist within 25 miles
    When I sort by distance
    Then farms appear ordered nearest first
  ```

- **US‑CUST‑003 — Discover produce boxes (filter & sort)**  
  _Story:_ As a customer, I want to filter and sort available boxes by season, farm, contents, price, and pickup/delivery so that I can find options that fit my needs and budget.  
  _Acceptance:_
  ```gherkin
  Scenario: Filter by season and sort by price
    Given there are "Spring" and "Summer" boxes from multiple farms
    When I filter by season "Spring" and sort by "Price (Low->High)"
    Then I see only Spring boxes ordered by lowest price first
  ```

- **US‑CUST‑004 — View produce box details**  
  _Story:_ As a customer, I want to open a box and see contents, cadence, price, capacity state, and pickup/delivery notes so that I can decide to subscribe.  
  _Acceptance:_
  ```gherkin
  Scenario: View box details
    Given a published box exists
    When I open its details page
    Then I see contents, cadence, price, capacity state, and pickup/delivery notes
  ```

- **US‑CUST‑005 — Subscribe (choose cadence & start)**  
  _Story:_ As a customer, I want to subscribe to a produce box with my preferred cadence and start date so that I receive fresh, seasonal produce regularly.  
  _Acceptance:_
  ```gherkin
  Scenario: Start a weekly subscription
    Given a "Weekly Veggie Box" has capacity next Friday
    When I choose "Weekly", select next Friday, and confirm terms
    Then a subscription is created with cadence and first pickup/delivery date
  ```

- **US‑CUST‑006 — Manage subscription (pause/skip/change/cancel)**  
  _Story:_ As a customer, I want to pause, skip a week, change cadence, or cancel within policy so that I can adapt to my schedule and reduce waste.  
  _Acceptance:_
  ```gherkin
  Scenario: Skip next week
    Given I have an active weekly subscription
    When I choose to skip next week
    Then next week's delivery is skipped and billing adjusts per policy
  ```

- **US‑CUST‑007 — Write a review**  
  _Story:_ As a customer, I want to rate freshness and pickup/delivery experience for a completed box so that the community benefits from my feedback.  
  _Acceptance:_
  ```gherkin
  Scenario: Eligible review within policy window
    Given I received a delivery within the last 14 days
    When I submit a rating and a comment
    Then the review is recorded and linked to the farm and box
      And it is published or queued per moderation policy
  ```

- **US‑CUST‑008 — Read reviews**  
  _Story:_ As a customer, I want to read recent reviews for a farm/box so that I can make an informed decision.  
  _Acceptance:_
  ```gherkin
  Scenario: List recent reviews
    Given reviews exist for a box
    When I open Reviews
    Then I see the most recent reviews with rating and comment
  ```

### 2.2 Provider (Farmer) Stories
- **US‑FARM‑001 — Register & manage farm profile**  
  _Story:_ As a farmer, I want to create/update my farm profile (bio, location, practices/certifications, pickup spots, delivery radius) so that customers understand our operation.  
  _Acceptance:_
  ```gherkin
  Scenario: Update farm profile
    Given I am a verified provider
    When I add or update profile details
    Then the profile is saved and visible on the farm page
  ```

- **US‑FARM‑002 — Create a produce box offering**  
  _Story:_ As a farmer, I want to publish a box with title, seasonal contents, cadence, capacity, price, and schedule so that customers can subscribe.  
  _Acceptance:_
  ```gherkin
  Scenario: Policy-compliant listing
    Given my farm profile is active
    When I enter required fields and confirm
    Then the box is created in "Published" (or "Pending") state
      And it becomes discoverable in search within 60 seconds
  ```

- **US‑FARM‑003 — Update harvest schedule & quantities**  
  _Story:_ As a farmer, I want to adjust harvest dates and available quantities so that listings reflect real‑time availability.  
  _Acceptance:_
  ```gherkin
  Scenario: Reduce capacity due to weather
    Given an upcoming week shows 20 open slots
    When I reduce capacity to 12
    Then the listing reflects the new capacity and prevents over-subscription
  ```

- **US‑FARM‑004 — Edit or suspend a box**  
  _Story:_ As a farmer, I want to edit or temporarily suspend a box so that I can prevent new subscriptions during shortages or holidays.  
  _Acceptance:_
  ```gherkin
  Scenario: Suspend listing
    Given a published box exists
    When I set its state to Suspended
    Then it is hidden from new subscriptions while existing subscribers follow policy
  ```

- **US‑FARM‑005 — Manage capacity (sold out)**  
  _Story:_ As a farmer, I want to mark a box as sold out when capacity is reached so that customers don’t over‑subscribe.  
  _Acceptance:_
  ```gherkin
  Scenario: Show sold out
    Given capacity is 0 remaining
    When a customer views the box
    Then the system shows Sold Out and disables new subscriptions
  ```

- **US‑FARM‑006 — View customer engagement metrics**  
  _Story:_ As a farmer, I want to view active subscribers, churn, average rating, and feedback so that I can improve offerings.  
  _Acceptance:_
  ```gherkin
  Scenario: View retention trend
    Given I open the engagement dashboard
    When I select a date range and a box
    Then I see active subs, churn%, and average rating for that range
  ```

- **US‑FARM‑007 — Reply to customer reviews**  
  _Story:_ As a farmer, I want to post a public reply to a customer review so that I can acknowledge feedback and share context.  
  _Acceptance:_
  ```gherkin
  Scenario: Reply to a review
    Given a published review exists for my box
    When I submit a reply
    Then the reply appears publicly beneath the review (subject to moderation)
  ```

### 2.3 SysAdmin Stories
- **US‑ADMIN‑001 — Manage user access (warn/suspend/ban)**  
  _Story:_ As a sysadmin, I want to activate, warn, suspend, or ban accounts with an audit trail so that I can enforce policy fairly.  
  _Acceptance:_
  ```gherkin
  Scenario: Suspend account with reason
    Given an account in good standing
    When I apply a suspension with reason "Policy"
    Then the account is Suspended and the action is audit-logged
  ```

- **US‑ADMIN‑002 — Moderate product listings**  
  _Story:_ As a sysadmin, I want to approve, return, or disable listings that violate policy so that the marketplace stays trustworthy.  
  _Acceptance:_
  ```gherkin
  Scenario: Approve pending listing
    Given a listing is pending moderation
    When I approve it
    Then it becomes visible in discovery and the decision is logged
  ```

- **US‑ADMIN‑003 — Moderate reviews (approve/mask/remove)**  
  _Story:_ As a sysadmin, I want to approve, mask, or remove reviews that violate policy so that feedback remains safe and helpful.  
  _Acceptance:_
  ```gherkin
  Scenario: Mask PII in a review
    Given a flagged review contains an email address
    When I choose Mask PII with reason "Privacy"
    Then the review hides the email and the action is audit-logged
  ```

- **US‑ADMIN‑004 — View platform usage & delivery/pickup success**  
  _Story:_ As a sysadmin, I want to view platform usage and delivery/pickup success rates so that I can monitor health and address systemic issues.  
  _Acceptance:_
  ```gherkin
  Scenario: View delivery success trend
    Given historical data exists
    When I open the admin analytics view
    Then I see daily active users, subscriptions, and delivery success % for a selected range
  ```

---

## 3. Non‑Functional Requirements (measurable)
- **Performance:** 95% of discovery responses ≤ **1.5s**; 99% of box detail pages ≤ **1.0s** under typical load. 
- **Availability/Reliability:** ≥ **99.5%** monthly uptime (maintenance excluded); basic retries for transient failures. 
- **Security/Privacy:** Hashed & salted passwords; role‑based access checks.
- **Usability:** New users complete first subscription in ≤ **3 minutes** in hallway tests.

---

## 4. Assumptions, Constraints, and Policies
- Modern browsers (latest Chrome/Firefox/Edge/Safari); stable connectivity. 
- Course timeline & campus infrastructure constraints apply. 
- Policies: review allowed within 14–30 days of delivery; content guidelines (no PII/harassment); cancellation rules per box terms; capacity rules prevent overbooking.

---

## 5. Milestones (course‑aligned)
- **M2 Requirements** — this file + stories opened as issues. 
- **M3 High‑fidelity prototype** — core customer/provider flows clickable. 
- **M4 Design** — architecture, schema, API outline. 
- **M5 Backend API** — key endpoints + tests. 
- **M6 Increment** — ≥2 use cases end‑to‑end. 
- **M7 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
