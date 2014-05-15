(ns {{sanitized}}.dev-server
  (:require [clojure.string            :as s]
            [ring.util.response        :as resp-util]
            [ring.middleware.file-info :as file-util]
            [ring.middleware.refresh   :as refresh]
            [clojure.core.async :refer [go chan <! put!]])
  (:import [java.util Date]))

(def requests (chan))

(def ^:dynamic *debug* (System/getenv "DEBUG"))

(defn log [req resp]
  (let [{:keys [remote-addr uri request-method]} req]
    (apply str (interpose " - " [remote-addr
                                 (.toString (Date.))
                                 (:status resp)
                                 (str (s/upper-case (name request-method)) " "
                                      uri)]))))

(defn handle-files [req]
  (let [resp (or (resp-util/file-response (:uri req) {:root "app"})
                 (resp-util/not-found "Not found!"))]
    (put! requests (log req resp))
    resp))

(def handler
  (-> handle-files
    file-util/wrap-file-info
    (refresh/wrap-refresh ["app"])))

(go (while true
      (let [req (<! requests)]
        (if *debug* (println req)))))
