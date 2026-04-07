<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Leave Review</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="customer" />

  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/customer/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a href="/customer/subscriptions">My Subscriptions</a></li>
        <li class="breadcrumb-item active" aria-current="page">Leave Review</li>
      </ol>
    </nav>

    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card mb-4">
          <div class="card-body">
            <h4>Reviewing: <strong>${subscription.produceBox.title}</strong></h4>
            <p class="text-muted">from <strong>${subscription.produceBox.farm.name}</strong></p>
            <hr>
            <p class="text-muted">
              <small>Started: ${subscription.startDate?date.iso?string("MMM d, y")} | Cadence: ${subscription.cadence}</small>
            </p>
          </div>
        </div>

        <div class="card">
          <div class="card-header">
            <h5 class="mb-0"><#if existingReview??>Edit Your Review<#else>Leave Your Review</#if></h5>
          </div>
          <div class="card-body">
            <form action="/customer/subscriptions/${subscription.subscriptionId}/review" method="post">
              <div class="mb-4">
                <label class="form-label">Freshness Rating <span class="text-danger">*</span></label>
                <div class="rating-scale">
                  <div class="btn-group w-100" role="group">
                    <input type="radio" class="btn-check" name="freshnessRating" id="fresh1" value="1"
                      <#if existingReview?? && existingReview.freshnessRating == 1>checked</#if> required>
                    <label class="btn btn-outline-secondary" for="fresh1">1</label>

                    <input type="radio" class="btn-check" name="freshnessRating" id="fresh2" value="2"
                      <#if existingReview?? && existingReview.freshnessRating == 2>checked</#if>>
                    <label class="btn btn-outline-secondary" for="fresh2">2</label>

                    <input type="radio" class="btn-check" name="freshnessRating" id="fresh3" value="3"
                      <#if existingReview?? && existingReview.freshnessRating == 3>checked</#if>>
                    <label class="btn btn-outline-secondary" for="fresh3">3</label>

                    <input type="radio" class="btn-check" name="freshnessRating" id="fresh4" value="4"
                      <#if existingReview?? && existingReview.freshnessRating == 4>checked</#if>>
                    <label class="btn btn-outline-secondary" for="fresh4">4</label>

                    <input type="radio" class="btn-check" name="freshnessRating" id="fresh5" value="5"
                      <#if existingReview?? && existingReview.freshnessRating == 5>checked</#if>>
                    <label class="btn btn-outline-secondary" for="fresh5">5 (Best)</label>
                  </div>
                </div>
                <small class="text-muted">How fresh were the products?</small>
              </div>

              <div class="mb-4">
                <label class="form-label">Delivery Rating <span class="text-danger">*</span></label>
                <div class="rating-scale">
                  <div class="btn-group w-100" role="group">
                    <input type="radio" class="btn-check" name="deliveryRating" id="deliv1" value="1"
                      <#if existingReview?? && existingReview.deliveryRating == 1>checked</#if> required>
                    <label class="btn btn-outline-secondary" for="deliv1">1</label>

                    <input type="radio" class="btn-check" name="deliveryRating" id="deliv2" value="2"
                      <#if existingReview?? && existingReview.deliveryRating == 2>checked</#if>>
                    <label class="btn btn-outline-secondary" for="deliv2">2</label>

                    <input type="radio" class="btn-check" name="deliveryRating" id="deliv3" value="3"
                      <#if existingReview?? && existingReview.deliveryRating == 3>checked</#if>>
                    <label class="btn btn-outline-secondary" for="deliv3">3</label>

                    <input type="radio" class="btn-check" name="deliveryRating" id="deliv4" value="4"
                      <#if existingReview?? && existingReview.deliveryRating == 4>checked</#if>>
                    <label class="btn btn-outline-secondary" for="deliv4">4</label>

                    <input type="radio" class="btn-check" name="deliveryRating" id="deliv5" value="5"
                      <#if existingReview?? && existingReview.deliveryRating == 5>checked</#if>>
                    <label class="btn btn-outline-secondary" for="deliv5">5 (Best)</label>
                  </div>
                </div>
                <small class="text-muted">How satisfied were you with the delivery?</small>
              </div>

              <div class="mb-4">
                <label class="form-label">Value Rating <span class="text-danger">*</span></label>
                <div class="rating-scale">
                  <div class="btn-group w-100" role="group">
                    <input type="radio" class="btn-check" name="valueRating" id="val1" value="1"
                      <#if existingReview?? && existingReview.valueRating == 1>checked</#if> required>
                    <label class="btn btn-outline-secondary" for="val1">1</label>

                    <input type="radio" class="btn-check" name="valueRating" id="val2" value="2"
                      <#if existingReview?? && existingReview.valueRating == 2>checked</#if>>
                    <label class="btn btn-outline-secondary" for="val2">2</label>

                    <input type="radio" class="btn-check" name="valueRating" id="val3" value="3"
                      <#if existingReview?? && existingReview.valueRating == 3>checked</#if>>
                    <label class="btn btn-outline-secondary" for="val3">3</label>

                    <input type="radio" class="btn-check" name="valueRating" id="val4" value="4"
                      <#if existingReview?? && existingReview.valueRating == 4>checked</#if>>
                    <label class="btn btn-outline-secondary" for="val4">4</label>

                    <input type="radio" class="btn-check" name="valueRating" id="val5" value="5"
                      <#if existingReview?? && existingReview.valueRating == 5>checked</#if>>
                    <label class="btn btn-outline-secondary" for="val5">5 (Best)</label>
                  </div>
                </div>
                <small class="text-muted">Was the value worth the price?</small>
              </div>

              <div class="mb-4">
                <label for="comment" class="form-label">Your Comments <span class="text-danger">*</span></label>
                <textarea class="form-control" id="comment" name="comment" rows="5" required
                  placeholder="Share your detailed thoughts about this produce box...">${(existingReview.comment)!""}</textarea>
                <small class="text-muted">Help other customers and farmers with constructive feedback</small>
              </div>

              <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary btn-lg">
                  <#if existingReview??>Update Review<#else>Submit Review</#if>
                </button>
                <a href="/customer/subscriptions" class="btn btn-secondary btn-lg">Cancel</a>
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
