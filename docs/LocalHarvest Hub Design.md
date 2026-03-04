
# LocalHarvest Hub - Software Design 

Version 1  
Prepared by Alice Beback\
LocalHarvest Hub\
March 2, 2026

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Product Overview](#1-product-overview)
* 2 [Actors](#2-actors)
* 3 [Use Case Model](#3-use-case-model)
* 4 [Use Case Descriptions](#4-use-case-descriptions)
  * 4.1 [Customer Use Cases](#41-customer-use-cases)
  * 4.2 [Farmer Use Cases](#42-farmer-use-cases)
  * 4.3 [System Admin Use Cases](#43-system-admin-use-cases)
* 5 [UML Class Model](#5-uml-class-model)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|  Al  |3/1      | Initial Design      |    1      |
|      |         |                     |           |
|      |         |                     |           |

## 1. Product Overview
LocalHarvest Hub is a simple, comprehensive, easy to use web app with the goal of connecting consumers to locally grown produce. Farmers and customers make use of the centralized platform to meet their needs. 
Farmers create and publish produce boxes, customers subscribe any available boxes that they are interested in, either as a one-off or recurring subscription.

---

## 2. Actors 

- **Customer** – discovers farms/boxes, subscribes, and reviews.
- **Provider (Farmer)** – registers a farm, posts boxes, views stats, replies to reviews.
- **System Administrator** – manages access, moderates listings/reviews, views usage statistics.

---

## 3. Use Case Model

## 4. Use Case Descriptions

### 4.1 Customer Use Cases

#### UC‑CUST‑001 — Register & Manage Customer Profile
**Primary Actor:** Customer  
**Goal:** Create an account and maintain profile information.  
**Stakeholders & Interests:**  
- *Customer:* quick, secure signup; control over personal data.  
- *System:* valid accounts; basic profile completeness for discovery.

**Preconditions:** Customer is not authenticated.

**Trigger:** Selects **Sign up** / **Create account**.

**Main Success Scenario:**
1. System displays registration form.  
2. Customer enters required details (e.g., name, contact, password).  
3. System validates; creates account.  
4. System prompts for optional profile fields (location, preferences).  
5. Customer updates and saves profile.  
6. System confirms and authenticates the user.  

**Extensions:**  
- 2a. Weak/invalid password → System explains rule; customer retries.  
- 3a. Email already registered → System suggests sign‑in or reset.  

**Postconditions (Success):** Account and profile exist; user is signed in.  
**Minimal Guarantees:** No partial or invalid data persisted on failure.  
**Special Requirements:** Usable, accessible form; password policy; (optional) email verification per SRS.  

---

#### UC‑CUST‑002 — Browse Farm Profiles
**Primary Actor:** Customer  
**Goal:** View farm profiles to learn about producers and offerings.  
**Preconditions:** None (public browse allowed).  
**Trigger:** Customer chooses **Browse farms**.  
**Main Success Scenario:**
1. System shows a list of farms with summary info.  
2. Customer opens a farm profile.  
3. System shows farm details (description, coverage, available boxes).  
4. Customer navigates to other profiles or back to the list.  

**Extensions:**   
- 1a. No farms available → System shows an empty state with guidance.  

**Postconditions:** None (view‑only).

**Special Requirements:** Mobile‑friendly cards; fast loading.  

---

#### UC‑CUST‑003 — Discover Produce Boxes (Filter & Sort)
**Primary Actor:** Customer  
**Goal:** Find relevant produce boxes using filters (e.g., contents, price) and sort options.  
**Preconditions:** At least one active box exists.  
**Trigger:** Customer opens **Discover boxes** and applies filters/sorts.  
**Main Success Scenario:**
1. System displays the boxes catalog.  
2. Customer sets filters (contents, price range) and sort criteria.  
3. System updates the list accordingly.  
4. Customer opens a box detail page.  
5. System shows contents, price, schedule/cadence, and farm.  

**Extensions:**   
- 2a. No results → System suggests clearing/adjusting filters.  

**Postconditions:** None (until a subscription is started).  
**Special Requirements:** Responsive filtering; resilient to empty results.  

---

#### UC‑CUST‑004 — Subscribe to a Produce Box
**Primary Actor:** Customer  
**Goal:** Start a subscription to a produce box (e.g., one‑off, weekly, monthly).  
**Preconditions:** Customer is authenticated; selected box is open for subscription.  
**Trigger:** Clicks **Subscribe** on a box detail page.  
**Main Success Scenario:**
1. System presents subscription options (cadence, start date, quantity).  
2. Customer selects options and confirms.  
3. System validates availability and terms.  
4. System creates the subscription and confirms next delivery.  

**Extensions:**   
- 3a. Selected cadence unavailable → System suggests alternatives/dates.  

**Postconditions:** Active subscription linked to the customer and box.  
**Special Requirements:** Transparent pricing/renewal terms and cancellation policy.  

---

#### UC‑CUST‑005 — Write a Review for a Subscribed Box
**Primary Actor:** Customer  
**Goal:** Rate and review a box they have received.  
**Preconditions:** Customer is authenticated and has at least one fulfilled delivery for that box.  
**Trigger:** From subscription history, selects **Write review**.  
**Main Success Scenario:**
1. System shows review form (rating + comments).  
2. Customer submits review.  
3. System validates (length, profanity, eligibility).  
4. System posts the review to the box/farm profile.  

**Extensions:**   
- 3a. Ineligible (no fulfilled orders) → System explains rule.  

**Postconditions:** Review visible per moderation settings; stored with audit trail.  
**Special Requirements:** Abuse/fraud controls; provider reply thread supported.  

---

### 4.2 Farmer Use Cases

#### UC‑PROV‑001 — Register & Manage Provider Profile
**Primary Actor:** Provider (Farmer)  
**Goal:** Create/manage farm profile (contact info, coverage, bio).  
**Preconditions:** Provider is not authenticated as a provider (or converting a customer account).  
**Trigger:** Selects **Sign up as Farmer** or **Convert to Provider**.  
**Main Success Scenario:**
1. System shows provider registration form.  
2. Provider enters farm details; submits.  
3. System validates and creates provider profile.  
4. Provider may update profile fields later (hours, coverage, notes).  

**Extensions:**   
- 2a. Missing/invalid fields → System explains and requests fixes.  

**Postconditions:** Provider profile exists and is linked to the user account.  
**Special Requirements:** Separation of customer vs provider roles/permissions.  

---

#### UC‑PROV‑002 — Create & Manage Produce Boxes (Services)
**Primary Actor:** Provider (Farmer)  
**Goal:** Post/edit seasonal produce boxes with contents, price, and schedules.  
**Preconditions:** Provider is authenticated; provider profile is active.  
**Trigger:** Selects **New box** or **Edit** on an existing box.  
**Main Success Scenario:**
1. System shows a form for box details (name, contents, cadence, price).  
2. Provider completes fields and saves.  
3. System validates (price numeric, schedule valid).  
4. System publishes (or updates) the box to the catalog.  

**Extensions:**   
- 3a. Invalid price/schedule → System highlights field and explains.  
- 4a. Save as draft → Not visible until published.  

**Postconditions:** Box record exists/updated; catalog reflects visibility.  
**Special Requirements:** (Optional) image uploads; change log/version notes.  

---

#### UC‑PROV‑003 — View Customer Statistics
**Primary Actor:** Provider (Farmer)  
**Goal:** See customer engagement/retention trends for their boxes.  
**Preconditions:** Provider is authenticated; at least one active or historical box.  
**Trigger:** Opens **Analytics / Statistics**.  
**Main Success Scenario:**
1. System shows key metrics (active subscribers, retention, churn trend).  
2. Provider filters by date range or box line.  
3. System updates metrics accordingly; provider can export if supported.  

**Extensions:**   
- 1a. Insufficient data → System shows placeholders or guidance.  

**Postconditions:** None (insight only).  
**Special Requirements:** Accurate, timely data; readable charts.  

---

#### UC‑PROV‑004 — Reply to Customer Reviews
**Primary Actor:** Provider (Farmer)  
**Goal:** Respond to reviews to acknowledge feedback and resolve issues.  
**Preconditions:** Provider authenticated; at least one review exists on their boxes.  
**Trigger:** Clicks **Reply** on a specific review.  
**Main Success Scenario:**
1. System displays a reply editor.  
2. Provider submits a reply.  
3. System posts the reply beneath the review and (optionally) notifies the reviewer.  

**Extensions:**   
- 2a. Inappropriate content → System blocks and explains policy.  

**Postconditions:** Reply visible per moderation rules; audit trail retained.  
**Special Requirements:** Abuse filters; provider can edit/delete own reply per policy.  

---

### 4.3 System Admin Use Cases

#### UC‑ADMIN‑001 — Manage User Access (Warnings/Bans)
**Primary Actor:** System Administrator  
**Goal:** Warn, suspend, or ban users who violate policy.  
**Preconditions:** Admin authenticated with appropriate privileges.  
**Trigger:** Admin opens a user account and selects an enforcement action.  
**Main Success Scenario:**
1. System displays user account overview and history.  
2. Admin selects action (warn/suspend/ban) and enters a reason.  
3. System records action, updates status, and notifies the user (if applicable).  

**Extensions:**  
- 2a. User appeals → System records appeal and routes for review.  

**Postconditions:** Updated user status; audit log captured.  
**Special Requirements:** Role‑based access control (RBAC); full auditability.  

---

#### UC‑ADMIN‑002 — Moderate Services (Listings)
**Primary Actor:** System Administrator  
**Goal:** Review and moderate posted boxes/services for compliance.  
**Preconditions:** Listings exist and/or have been reported.  
**Trigger:** Admin opens **Moderation queue** for services.  
**Main Success Scenario:**
1. System presents pending/reported listings.  
2. Admin approves, requests changes, or removes a listing.  
3. System applies decision and notifies the provider when needed.  

**Extensions:**  
- 2a. Repeat violations → System suggests escalated actions.  

**Postconditions:** Catalog reflects moderation decisions; logs retained.  

---

#### UC‑ADMIN‑003 — Moderate Reviews
**Primary Actor:** System Administrator  
**Goal:** Enforce content policy on reviews (e.g., remove/flag).  
**Preconditions:** Reviews exist and are queued or reported.  
**Trigger:** Admin opens **Reviews moderation**.  
**Main Success Scenario:**
1. System shows flagged/pending reviews with reasons.  
2. Admin approves, masks, removes, or requests edits.  
3. System updates visibility and sends any required notifications.  

**Postconditions:** Policy‑compliant review corpus; audit log updated.  

---

#### UC‑ADMIN‑004 — View Platform Usage Statistics
**Primary Actor:** System Administrator  
**Goal:** Monitor platform health and usage (e.g., delivery success, growth).  
**Preconditions:** Analytics pipeline available.  
**Trigger:** Admin opens **Platform analytics**.  
**Main Success Scenario:**
1. System shows dashboards (traffic, active users, delivery success rates).  
2. Admin filters by date, user segment, or geography.  
3. System updates views; Admin may export reports.  

**Postconditions:** None (insight only).  
**Special Requirements:** Data quality SLAs; export to CSV/PDF.  

---

## 5 UML Class Model
