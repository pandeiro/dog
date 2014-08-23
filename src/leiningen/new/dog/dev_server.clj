(ns {{name}}.dev-server
  (:require [clojure.string            :as s]
            [ring.util.response        :as resp-util]
            [ring.middleware.file      :as file]
            [ring.middleware.file-info :as file-util]
            [ring.middleware.refresh   :as refresh]
            [clojure.core.async :refer [go chan <! put!]])
  (:import [java.util Date]))

(defn base [handler]
  (fn [req] (handler req)))

(def handler
  (-> base
    (file/wrap-file "app")
    file-util/wrap-file-info
    (refresh/wrap-refresh ["app"])))

