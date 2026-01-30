
# LocalHarvest Hub – Software Requirements Specification

**Version:** 1.0  **Date:** 2026-01-30  **System Name:** LocalHarvest Hub  **Purpose:** This SRS consolidates the scope and user‑facing requirements of LocalHarvest Hub and enumerates all user stories that guide development and testing.

---

## 1. Introduction
**Product vision.** LocalHarvest Hub is a web-based platform that connects eco‑conscious consumers to nearby farms. Customers discover farm profiles, explore and subscribe to seasonal produce boxes (once‑off/weekly/monthly), and share reviews. Farmers manage offerings and harvest schedules and track customer engagement. Administrators oversee access, listings, reviews, and usage health to ensure a vibrant, transparent, community‑driven marketplace.

**Stakeholders & roles.**
- **Customer** (consumer of produce boxes)
- **Farmer** (provider of produce boxes)
- **SysAdmin** (platform administrator)

**Assumptions & constraints.**
- Users have reliable internet access via modern browsers.
- Subscriptions can be pickup or delivery depending on farmer policy.
- Reviews are permitted only for completed deliveries/pickups within policy windows.

**Out of scope (for this release).**
- Online payment processing and disputes (handled offline or in future work).
- Route optimization and live driver tracking.
- Multi‑language UI beyond English.

---

## 2. Glossary
- **Box / Produce Box:** A curated set of seasonal items offered by a farm, with a cadence (once‑off, weekly, monthly).
- **Capacity:** Maximum number of active subscriptions/units for a box in a time window.
- **Cadence:** Frequency at which a subscription recurs.
- **Engagement:** Aggregate signals such as active subscribers, churn, ratings.

---

## 3. User Stories (Functional Requirements)
User stories are grouped by epic. Each story follows the template: *As a <role>, I want <capability>, so that <benefit>.* 

### 3.1 EP‑CUST — Customer

**US‑CUST‑001 — Register & manage customer profile**  
As a customer, I want to create or update my profile (name, location, delivery/pickup preference, dietary notes) so that I receive relevant produce boxes and communications.

**US‑CUST‑002 — Browse farm profiles**  
As a customer, I want to browse farm profiles (distance, growing practices, season highlights) so that I can learn about local producers before subscribing.

**US‑CUST‑003 — Discover produce boxes (filter & sort)**  
As a customer, I want to filter and sort available boxes by season, farm, contents, price, and pickup/delivery so that I can find options that fit my needs and budget.

**US‑CUST‑004 — View produce box details**  
As a customer, I want to open a box and see its contents, cadence (once‑off/weekly/monthly), price, capacity, and pickup/delivery notes so that I can decide whether to subscribe.

**US‑CUST‑005 — Subscribe (choose cadence & start)**  
As a customer, I want to subscribe to a produce box with my preferred cadence and start date so that I receive fresh, seasonal produce regularly.

**US‑CUST‑006 — Manage subscription (pause/skip/change/cancel)**  
As a customer, I want to pause, skip a week, change cadence, or cancel within policy so that I can adapt to my schedule and reduce waste.

**US‑CUST‑007 — Write a review for a completed delivery/pickup**  
As a customer, I want to rate freshness and delivery/pickup experience for a completed box so that the community benefits from my feedback.

**US‑CUST‑008 — Read reviews**  
As a customer, I want to read recent reviews for a farm/box so that I can make an informed decision.

---

### 3.2 EP‑FARM — Farmer

**US‑FARM‑001 — Register & manage farm profile**  
As a farmer, I want to create/update my farm profile (bio, location, practices/certifications, pickup spots, delivery radius) so that customers understand who we are and how we operate.

**US‑FARM‑002 — Create a produce box offering**  
As a farmer, I want to publish a box with title, seasonal contents, cadence (once‑off/weekly/monthly), capacity, price, and schedule so that customers can subscribe.

**US‑FARM‑003 — Update harvest schedule & quantities**  
As a farmer, I want to adjust harvest dates and available quantities so that my listings reflect real‑time availability.

**US‑FARM‑004 — Edit or suspend a produce box**  
As a farmer, I want to edit or temporarily suspend a box so that I can prevent new subscriptions during shortages or holidays.

**US‑FARM‑005 — Manage capacity (sold out state)**  
As a farmer, I want to mark a box as sold out when capacity is reached so that customers don’t over‑subscribe.

**US‑FARM‑006 — View customer engagement metrics**  
As a farmer, I want to view active subscribers, churn, average rating, and feedback so that I can improve offerings.

**US‑FARM‑007 — Reply to customer reviews**  
As a farmer, I want to post a public reply to a customer review so that I can acknowledge feedback and share context.

---

### 3.3 EP‑ADMIN — SysAdmin

**US‑ADMIN‑001 — Manage user access (warn/suspend/ban)**  
As a sysadmin, I want to activate, warn, suspend, or ban accounts with an audit trail so that I can enforce community policy fairly.

**US‑ADMIN‑002 — Moderate product listings**  
As a sysadmin, I want to approve, return, or disable listings that violate policy so that the marketplace stays trustworthy.

**US‑ADMIN‑003 — Moderate reviews**  
As a sysadmin, I want to approve, mask, or remove reviews that violate policy (e.g., PII, harassment) so that feedback remains safe and helpful.

**US‑ADMIN‑004 — View platform usage & delivery/pickup success rates**  
As a sysadmin, I want to view platform usage and delivery/pickup success rates so that I can monitor health and address systemic issues.

---

## 4. Non‑Functional Requirements (as Stories)

**US‑NFR‑PERF‑001 — Responsive discovery performance**  
As a user, I want search and box lists to load quickly so that the site feels responsive.  
**Target:** 95% of discovery requests ≤ 1.5s; 99% of box detail pages ≤ 1.0s under typical load.

**US‑NFR‑SEC‑001 — Account & data security**  
As the platform, I must protect accounts and personal data so that users can trust the system.  
**Target:** Hashed/salted passwords; role‑based access checks.

**US‑NFR‑PRIV‑001 — Privacy in user‑generated content (UGC)**  
As the platform, I must prevent exposure of personal/sensitive data in reviews so that users remain safe.  
**Target:** Server‑side checks for emails/phones/addresses in reviews; moderation queue.

---

## 5. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
