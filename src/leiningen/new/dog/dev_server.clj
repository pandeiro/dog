(ns {{name}}.dev.server
  (:require
   [ring.util.response        :as resp-util]
   [ring.middleware.file-info :as file-util]
   [ring.middleware.refresh   :as refresh]))

(defn base [req]
  (let [resp (or (resp-util/file-response (:uri req) {:root "app"})
                 (resp-util/not-found "Not found!"))]
    resp))

(def handler
  (-> base
    file-util/wrap-file-info
    (refresh/wrap-refresh ["app"])))

