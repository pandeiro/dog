(ns {{name}}.dev.server
  (:require [ring.middleware.file      :as file]
            [ring.middleware.file-info :as file-util]
            [ring.middleware.refresh   :as refresh]))

(defn base [handler]
  (fn [req] (handler req)))

(def handler
  (-> base
    (file/wrap-file "app")
    file-util/wrap-file-info
    (refresh/wrap-refresh ["app"])))

