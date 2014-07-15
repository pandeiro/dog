(ns {{name}}.app
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [{{name}}.macros :refer [resolve-config]])
  (:require [cljs.core.async :as async :refer [chan put! <! >!]]
            [reagent.core :as r :refer [render-component]]
            [secretary.core :refer [defroute] :include-macros true]
            [{{name}}.util.routing :refer [enable-routes]]
            [{{name}}.util.xhr :as xhr :refer [get-edn post-edn! put-edn!]]))

;; --- Config (imports config.edn)
(def config (resolve-config))

;; --- State
(def app-state (r/atom {:view :main}))

;; --- Routes
(defroute "/main" []
  (swap! app-state assoc :view :main))

(defroute "/options" []
  (swap! app-state assoc :view :options))

(enable-routes)

;; --- Views
(def main-view [:div#main [:h1 "Main View"]])

(def options-view [:div#options [:h1 "Options View"]])

(defn render-app []
  (let [wrapper (.getElementById js/document "wrapper")]
    (render-component (condp = (:view @app-state)
                        :main    [main-view]
                        :options [options-view])
                      wrapper)))


(defn init
  "A single entrypoint for the application"
  []
  (render-app))

(.addEventListener js/window "DOMContentLoaded" init)

