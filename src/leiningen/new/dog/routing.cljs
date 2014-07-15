(ns {{sanitized}}.util.routing
  (:require [secretary.core :as secretary :include-macros true]
            [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:import goog.History))

(defn enable-routes
  "Enables history updates on hashtag change"
  []
  (let [h (History.)]
    (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
    (.setEnabled h true)))
