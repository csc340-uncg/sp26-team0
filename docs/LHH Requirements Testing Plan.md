**Project Name:** LocalHarvest Hub  
**Version:** 1.0  
**Date:** 2026-04-07  
**Purpose:** This document outlines comprehensive test scenarios for each functional requirement (user story) in the LocalHarvest Hub system.

## Actors
- Provider P: Farmer
- Customer C: Customer
- Service S: Produce Box

## Use Cases
#### 1. Provider: US‑FARM‑001 — Register & manage farm profile, US‑FARM‑002 — Create a produce box offering
1. Farmer P1 logs in for the first time and creates a profile.
2. P1 registers their farm and creates new boxes S1 and S2 with values for searchable criteria C1, C2, C3, C4 (C1=Title, C2=Description, C3=Price, C4=Season).  
P1 exits the app.

#### 2. Customer: US‑CUST‑001 — Register & manage profile
1. Customer C1 logs in for the first time and creates a profile.

#### 3. Customer:  US‑CUST‑001 — Register & manage profile, US-CUST‑003 — Discover produce boxes, US‑CUST‑005 — Subscribe.
1. Customer C2 logs in for the first time and creates a new profile.
2. C2 views available boxes S1 and S2.
3. C2 subscribes to S1.

#### 4. Customer: US‑CUST‑007 — Write a review
1. C2 log in and views their subscriptions.
2. C2 writes a positive review of box S1. C2 exits.

#### 5. Customer: US‑CUST‑008 — Read reviews, US‑CUST‑003 — Discover produce boxes (filter & sort), US‑CUST‑005 — Subscribe 
1. C1 logs in and modifies their profile.
2. C1 searches for available boxes by contents.
3. C1 views box S1 and the positive review.
4. C1 subscribes to S1. C1 exits.

#### 6. Provider: US‑FARM‑007 — Reply to customer reviews, US‑FARM‑006 — View customer engagement metrics, US‑FARM‑004 — Edit or suspend a box
1. Farmer P1 logs in and reads their review and replies with thanks. 
2. P1 views box subscription statistics.
3. P1 modifies the capacity of S1.
4. P1 exits.

## CROSS-CUTTING TEST SCENARIOS (Non-Functional Requirements)

### Performance Requirements

**Scenario P1: Browse boxes response time < 1.5 seconds**
- **Setup:** Server under typical load
- **Steps:**
  1. Measure response time for "Browse" page load with 10 active farms, 50+ boxes
  2. Repeat 10 times
- **Expected Outcome:** 95% of requests ≤ 1.5 seconds

**Scenario P2: Box detail page load < 1.0 second**
- **Setup:** Server under typical load
- **Steps:**
  1. Measure response time for box detail page
  2. Repeat 10 times
- **Expected Outcome:** 99% of requests ≤ 1.0 second

### Security & Privacy Requirements

**Scenario S1: Role-based access control**
- **Setup:** Customer user tries to access farmer dashboard
- **Steps:**
  1. Customer logs in
  2. Attempts to navigate to "/farmer/dashboard"
  3. Observes system response
- **Expected Outcome:**
  - Access is denied (403 Forbidden)
  - User is redirected to home or error page
  - No farmer data is exposed

**Scenario S2: Farmer cannot edit customer's review**
- **Setup:** Farmer logs in; customer has written critical review
- **Steps:**
  1. Farmer views customer review
  2. Attempts to edit or delete review (via URL or API)
- **Expected Outcome:**
  - Edit/delete actions are not available
  - API call to modify review returns 403 Forbidden
  - Only admin can moderate reviews

### Usability Requirements

**Scenario U1: New user completes first subscription in ≤ 3 minutes**
- **Setup:** New user participates in hallway test
- **Steps:**
  1. User logs in (account pre-created)
  2. User browses boxes
  3. User selects a box and subscribes
  4. Record total time
- **Expected Outcome:** Time to complete subscription ≤ 3 minutes

**Scenario U2: Farmer can create first box in ≤ 5 minutes**
- **Setup:** New farmer account; interview/walkthrough observed
- **Steps:**
  1. Farmer logs in
  2. Navigates to "Create Box"
  3. Fills in box details (title, price, contents, cadence, capacity)
  4. Submits box
  5. Record total time
- **Expected Outcome:** Time to complete ≤ 5 minutes
